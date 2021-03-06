package lila.relation

import akka.actor._
import com.typesafe.config.Config
import scala.concurrent.duration._

import lila.common.PimpedConfig._

final class Env(
    config: Config,
    db: lila.db.Env,
    hub: lila.hub.Env,
    onlineUserIds: lila.memo.ExpireSetMemo,
    lightUserApi: lila.user.LightUserApi,
    followable: String => Fu[Boolean],
    system: ActorSystem,
    asyncCache: lila.memo.AsyncCache.Builder,
    scheduler: lila.common.Scheduler
) {

  private val settings = new {
    val CollectionRelation = config getString "collection.relation"
    val ActorNotifyFreq = config duration "actor.notify_freq"
    val ActorName = config getString "actor.name"
    val MaxFollow = config getInt "limit.follow"
    val MaxBlock = config getInt "limit.block"
  }
  import settings._

  private[relation] val coll = db(CollectionRelation)

  lazy val api = new RelationApi(
    coll = coll,
    actor = hub.actor.relation,
    bus = system.lilaBus,
    timeline = hub.actor.timeline,
    reporter = hub.actor.report,
    followable = followable,
    asyncCache = asyncCache,
    maxFollow = MaxFollow,
    maxBlock = MaxBlock
  )

  val online = new OnlineDoing(
    api,
    lightUser = lightUserApi.sync,
    onlineUserIds
  )

  private[relation] val actor = system.actorOf(Props(new RelationActor(
    lightUser = lightUserApi.sync,
    api = api,
    online = online
  )), name = ActorName)

  scheduler.once(15 seconds) {
    scheduler.message(ActorNotifyFreq) {
      actor -> actorApi.ComputeMovement
    }
  }
}

object Env {

  lazy val current = "relation" boot new Env(
    config = lila.common.PlayApp loadConfig "relation",
    db = lila.db.Env.current,
    hub = lila.hub.Env.current,
    onlineUserIds = lila.user.Env.current.onlineUserIdMemo,
    lightUserApi = lila.user.Env.current.lightUserApi,
    followable = lila.pref.Env.current.api.followable _,
    system = old.play.Env.actorSystem,
    asyncCache = lila.memo.Env.current.asyncCache,
    scheduler = lila.common.PlayApp.scheduler
  )
}
