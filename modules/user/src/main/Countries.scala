package lila.user

import scala._
import scala.collection.breakOut

sealed trait Country {
  def code: String
  def name: String
  def shortName: String
}

case class C(code: String, name: String) extends Country {
  def shortName = name
}

case class CC(code: String, name: String, shortName: String) extends Country

object Countries {

  val all = List(
    C("AD", "Andorra"),
    CC("AE", "United Arab Emirates", "UAE"),
    C("AF", "Afghanistan"),
    C("AG", "Antigua and Barbuda"),
    C("AL", "Albania"),
    C("AM", "Armenia"),
    C("AO", "Angola"),
    C("AR", "Argentina"),
    C("AT", "Austria"),
    C("AU", "Australia"),
    C("AZ", "Azerbaijan"),
    C("BA", "Bosnia-Herzegovina"),
    C("BB", "Barbados"),
    C("BD", "Bangladesh"),
    C("BE", "Belgium"),
    C("BF", "Burkina Faso"),
    C("BG", "Bulgaria"),
    C("BH", "Bahrain"),
    C("BI", "Burundi"),
    C("BJ", "Benin"),
    C("BN", "Brunei"),
    C("BO", "Bolivia"),
    C("BR", "Brazil"),
    C("BS", "Bahamas"),
    C("BT", "Bhutan"),
    C("BW", "Botswana"),
    C("BY", "Belarus"),
    C("BZ", "Belize"),
    C("CA", "Canada"),
    C("CD", "Congo (Democratic Rep.)"),
    C("CF", "Central African Republic"),
    C("CG", "Congo (Brazzaville)"),
    C("CH", "Switzerland"),
    C("CI", "Cote d'Ivoire"),
    C("CL", "Chile"),
    C("CM", "Cameroon"),
    C("CN", "China"),
    C("CO", "Colombia"),
    C("CR", "Costa Rica"),
    C("CU", "Cuba"),
    C("CV", "Cape Verde"),
    C("CY", "Cyprus"),
    C("CZ", "Czech Republic"),
    C("DE", "Germany"),
    C("DJ", "Djibouti"),
    C("DK", "Denmark"),
    C("DM", "Dominica"),
    C("DO", "Dominican Republic"),
    C("DZ", "Algeria"),
    C("EC", "Ecuador"),
    C("EE", "Estonia"),
    C("EG", "Egypt"),
    C("EH", "Western Sahara"),
    C("ER", "Eritrea"),
    C("ES", "Spain"),
    C("ET", "Ethiopia"),
    C("FI", "Finland"),
    C("FJ", "Fiji"),
    C("FM", "Micronesia"),
    C("FO", "Faroe Islands"),
    C("FR", "France"),
    C("GA", "Gabon"),
    CC("GB", "United Kingdom", "UK"),
    C("GB-ENG", "England"),
    C("GB-SCT", "Scotland"),
    C("GB-WLS", "Wales"),
    C("GD", "Grenada"),
    C("GE", "Georgia"),
    C("GG", "Guernsey"),
    C("GH", "Ghana"),
    C("GL", "Greenland"),
    C("GM", "Gambia"),
    C("GN", "Guinea"),
    C("GQ", "Equatorial Guinea"),
    C("GR", "Greece"),
    C("GT", "Guatemala"),
    C("GW", "Guinea-Bissau"),
    C("GY", "Guyana"),
    C("HK", "Hong Kong"),
    C("HN", "Honduras"),
    C("HR", "Croatia"),
    C("HT", "Haiti"),
    C("HU", "Hungary"),
    C("ID", "Indonesia"),
    C("IE", "Ireland"),
    C("IL", "Israel"),
    C("IM", "Isle of Man"),
    C("IN", "India"),
    C("IQ", "Iraq"),
    C("IR", "Iran"),
    C("IS", "Iceland"),
    C("IT", "Italy"),
    C("JE", "Jersey"),
    C("JM", "Jamaica"),
    C("JO", "Jordan"),
    C("JP", "Japan"),
    C("KE", "Kenya"),
    C("KG", "Kyrgyzstan"),
    C("KH", "Cambodia"),
    C("KI", "Kiribati"),
    C("KM", "Comoros"),
    C("KN", "Saint Kitts and Nevis"),
    C("KP", "North Korea"),
    C("KR", "South Korea"),
    C("KW", "Kuwait"),
    C("KY", "Cayman Islands"),
    C("KZ", "Kazakhstan"),
    C("LA", "Laos"),
    C("LB", "Lebanon"),
    C("LC", "Saint Lucia"),
    C("LI", "Liechtenstein"),
    C("LK", "Sri Lanka"),
    C("LR", "Liberia"),
    C("LS", "Lesotho"),
    C("LT", "Lithuania"),
    C("LU", "Luxembourg"),
    C("LV", "Latvia"),
    C("LY", "Libya"),
    C("MA", "Morocco"),
    C("MD", "Moldova"),
    C("ME", "Montenegro"),
    C("MG", "Madagascar"),
    C("MH", "Marshall Islands"),
    C("MK", "Macedonia"),
    C("ML", "Mali"),
    C("MM", "Myanmar"),
    C("MN", "Mongolia"),
    C("MO", "Macao"),
    C("MR", "Mauritania"),
    C("MT", "Malta"),
    C("MU", "Mauritius"),
    C("MV", "Maldives"),
    C("MW", "Malawi"),
    C("MX", "Mexico"),
    C("MY", "Malaysia"),
    C("MC", "Monaco"),
    C("MZ", "Mozambique"),
    C("NA", "Namibia"),
    C("NE", "Niger"),
    C("NG", "Nigeria"),
    C("NI", "Nicaragua"),
    C("NL", "Netherlands"),
    C("NO", "Norway"),
    C("NP", "Nepal"),
    C("NR", "Nauru"),
    C("NZ", "New Zealand"),
    C("OM", "Oman"),
    C("PA", "Panama"),
    C("PE", "Peru"),
    C("PG", "Papua New Guinea"),
    C("PH", "Philippines"),
    C("PK", "Pakistan"),
    C("PL", "Poland"),
    C("PS", "Palestine"),
    C("PT", "Portugal"),
    C("PW", "Palau"),
    C("PY", "Paraguay"),
    C("QA", "Qatar"),
    C("RO", "Romania"),
    C("RS", "Serbia"),
    C("RU", "Russia"),
    C("RW", "Rwanda"),
    C("SA", "Saudi Arabia"),
    C("SB", "Solomon Islands"),
    C("SC", "Seychelles"),
    C("SD", "Sudan"),
    C("SE", "Sweden"),
    C("SG", "Singapore"),
    C("SI", "Slovenia"),
    C("SK", "Slovakia"),
    C("SL", "Sierra Leone"),
    C("SM", "San Marino"),
    C("SN", "Senegal"),
    C("SO", "Somalia"),
    C("SR", "Suriname"),
    C("ST", "Sao Tome and Principe"),
    C("SV", "El Salvador"),
    C("SY", "Syria"),
    C("SZ", "Swaziland"),
    C("TD", "Chad"),
    C("TG", "Togo"),
    C("TH", "Thailand"),
    C("TJ", "Tajikistan"),
    C("TL", "Timor-Leste"),
    C("TM", "Turkmenistan"),
    C("TN", "Tunisia"),
    C("TO", "Tonga"),
    C("TR", "Turkey"),
    C("TT", "Trinidad and Tobago"),
    C("TV", "Tuvalu"),
    C("TW", "Taiwan"),
    C("TZ", "Tanzania"),
    C("UA", "Ukraine"),
    C("UG", "Uganda"),
    CC("US", "United States", "USA"),
    C("UY", "Uruguay"),
    C("UZ", "Uzbekistan"),
    C("VC", "Saint Vincent and the Grenadines"),
    C("VE", "Venezuela"),
    C("VN", "Vietnam"),
    C("VU", "Vanuatu"),
    C("WS", "Samoa"),
    C("YE", "Yemen"),
    C("ZA", "South Africa"),
    C("ZM", "Zambia"),
    C("ZW", "Zimbabwe")
  ).sortBy(_.name)

  val allPairs = all map { c => c.code -> c.name }

  val map: Map[String, Country] = all.map { c => c.code -> c }(breakOut)

  val codeSet = map.keySet

  def info(code: String): Option[Country] = map get code
}
