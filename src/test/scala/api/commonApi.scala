package api

import config.BaseHelpers._
import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

object commonApi {
  def healthPing(): ChainBuilder = {
    exec(
      http("health/ping")
        .get(HomePage + "actuator/health/ping")
        .check(jsonPath("$.status").is("UP"))

    )
  }

  def getContentBoxes(): ChainBuilder = {
    exec(
      http("Get content/boxes")
        .get(HomePage + "api/v1/content/boxes/headerMessage")
        .queryParam("lang", "en")
        .check(status.is(404))
    )
  }

  def getCategory(): ChainBuilder = {
    exec(
      http("Get category")
        .get(HomePage + "api/v1/category")
        .queryParam("store", "DEFAULT")
        .queryParam("lang", "en")
        .queryParam("page", "0")
        .queryParam("count", "20")
    )
  }

  def getContentPages(): ChainBuilder = {
    exec(
      http("Get content/pages")
        .get(HomePage + "api/v1/content/pages")
        .queryParam("store", "DEFAULT")
        .queryParam("lang", "en")
        .queryParam("page", "0")
        .queryParam("count", "20")
    )
  }

  def postPrice(id: String = "1"): ChainBuilder = {
    exec(
      http("Post Price")                           //name:price/ Repeat twice
        .post(HomePage + s"api/v1/product/$id/price/")
        .body(StringBody("""{"options":[]}""")).asJson


    )
  }

  def getStoreDefault(): ChainBuilder = {
    exec(
      http("Get store/DEFAULT") //name:default
        .get(HomePage + "api/v1/store/DEFAULT")

    )
  }

}
