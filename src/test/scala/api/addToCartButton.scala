package api

import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._
import config.BaseHelpers._

object addToCartButton extends BaseApi {

  def addToCart(): ChainBuilder = {
    exec(
      http("Add to cart button")
        .post(HomePage + "api/v1/cart")
        .body(StringBody("""{"attributes":[],"product":"table1","quantity":2}""")).asJson
        .check(jsonPath("$.code").saveAs("CartId"))
        .queryParam("store","DEFAULT")

    )
  }

  def getCartID(): ChainBuilder = {
    exec(
      http("Get cart/id")
        .get(HomePage + "api/v1/cart/${CartId}")
        .queryParam("lang", "en")
    )
  }




}
