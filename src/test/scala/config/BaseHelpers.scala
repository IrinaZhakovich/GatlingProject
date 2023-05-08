package config

import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef.http
import io.gatling.http.protocol.HttpProtocolBuilder

object BaseHelpers {

  val HomePage = "http://localhost:8080/"
  val HomePageHost = "http://localhost/"

  def thinkTimer (Min: Int = 2, Max: Int = 5) : ChainBuilder = {
pause(Min,Max)
  }


  val httpProtocol: HttpProtocolBuilder = http
    .acceptHeader("*/*")
    .acceptEncodingHeader("gzip, deflate, br")
    .acceptLanguageHeader("en-GB,en-US;q=0.9,en;q=0.8")
    .upgradeInsecureRequestsHeader("1")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36")


}
