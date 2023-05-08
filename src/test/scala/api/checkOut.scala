package api

import api.addToCartButton.CartId
import config.BaseHelpers
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._
import config.BaseHelpers._

object checkOut {

  def getShippingCountry(): ChainBuilder = {
    exec(
      http("Get shipping/country")
        .get(HomePage + "api/v1/shipping/country")
        .queryParam("store", "DEFAULT")
        .queryParam("lang", "en")

    )
  }

  def getZonesCode(): ChainBuilder = {
    exec(
      http("Get zones/code")
        .get(HomePage + "api/v1/zones/?code=")
    )
  }

  def getCartWithID(): ChainBuilder = {
    exec(
      http("Click checkout button from cart icon 1")
        .get(HomePage + "api/v1/cart/${CartId}")
        .queryParam("store", "DEFAULT")

    )
  }

  def getConfig(): ChainBuilder = {
    exec(
      http("Get config")
        .get(HomePage + "api/v1/config/")
    )
  }

  def getTotal(): ChainBuilder = {
    exec(
      http("Get total")
        .get(HomePage + "api/v1/cart/${CartId}/total/")

        )
  }

//  def postShipping(): ChainBuilder = {
//    exec(
//      http("Post shipping")
//        .post(HomePage + String.format("api/v1/cart/${%s}/shipping", CartId))
//    )
//  }


}
