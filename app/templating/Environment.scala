package lila.app
package templating

import scala.concurrent.duration._

import ornicar.scalalib
import play.twirl.api.Html

import lila.api.Env.{ current => apiEnv }

object Environment
  extends scalaz.syntax.ToIdOps
  with scalaz.std.OptionInstances
  with scalaz.std.OptionFunctions
  with scalaz.std.StringInstances
  with scalaz.syntax.std.ToOptionIdOps
  with scalalib.OrnicarMonoid.Instances
  with scalalib.Zero.Instances
  with scalalib.OrnicarOption
  with lila.BooleanSteroids
  with lila.OptionSteroids
  with lila.JodaTimeSteroids
  with StringHelper
  with JsonHelper
  with AssetHelper
  with RequestHelper
  with DateHelper
  with NumberHelper
  with PaginatorHelper
  with FormHelper
  with SetupHelper
  with AiHelper
  with GameHelper
  with UserHelper
  with ForumHelper
  with I18nHelper
  with SecurityHelper
  with TeamHelper
  with AnalysisHelper
  with TournamentHelper
  with SimulHelper
  with ChessgroundHelper {

  type FormWithCaptcha = (play.api.data.Form[_], lila.common.Captcha)

  def netDomain = apiEnv.Net.Domain
  def netBaseUrl = apiEnv.Net.BaseUrl
  lazy val isGloballyCrawlable = apiEnv.Net.Crawlable

  def isProd = apiEnv.isProd

  def apiVersion = lila.api.Mobile.Api.currentVersion

  def explorerEndpoint = apiEnv.ExplorerEndpoint

  def tablebaseEndpoint = apiEnv.TablebaseEndpoint

  def contactEmail = apiEnv.Net.Email

  def contactEmailLink = Html(s"""<a href="mailto:$contactEmail">$contactEmail</a>""")

  def globalCasualOnlyMessage = Env.setup.CasualOnly option {
    "Due to temporary maintenance on the servers, only casual games are available."
  }

  def reportNbUnprocessed: Int =
    lila.report.Env.current.api.nbUnprocessed.awaitOrElse(10.millis, 0)

  def NotForKids[Html](f: => Html)(implicit ctx: lila.api.Context) =
    if (ctx.kid) Html("") else f

  def signalBars(v: Int) = Html {
    val bars = (1 to 4).map { b =>
      s"""<i${if (v < b) " class=\"off\"" else ""}></i>"""
    } mkString ""
    val title = v match {
      case 1 => "Poor connection"
      case 2 => "Decent connection"
      case 3 => "Good connection"
      case _ => "Excellent connection"
    }
    s"""<signal data-hint="$title" class="q$v hint--top">$bars</signal>"""
  }
}
