package api


import io.gatling.core.structure._
import config.BaseHelpers._
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object homePage {

  def getLocalhost(): ChainBuilder = {                           //name:localhost
    exec(
      http("Get localhost")
        .get(HomePageHost)
        .check(regex("viewport"))
    )
  }

  def getContentBoxes(): ChainBuilder = {
    exec(
      http("Get content/boxes")
        .get(HomePage+ "api/v1/content/boxes/headerMessage")
        .queryParam("lang", "en")
    )
  }




  def featuredItem(): ChainBuilder = {
    exec(
      http("Featured Item")                                                        //name:Featured item
        .get(HomePage+ "api/v1/products/group/FEATURED_ITEM")
        .queryParam("store", "DEFAULT")
        .queryParam("lang", "en")
        .check(jsonPath("$.products[*].id").findAll.saveAs("allProductsID"))
        .check(jsonPath("$.products[*].id").findRandom.saveAs("randomProductID"))
    )
  }









  }
