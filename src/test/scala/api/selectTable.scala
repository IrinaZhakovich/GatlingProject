package api
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._
import config.BaseHelpers._



object selectTable {

  def getProductsReviews(): ChainBuilder = {
    exec(
      http("Get products/reviews")
        .get(HomePage + "api/v1/products/1/reviews")
        .queryParam("store", "DEFAULT")
//        .header("Accept", "application/json, text/plain, */*")
    )
  }
  def getProductsStoreDefault(): ChainBuilder = {
    exec(
      http("Get products/store=DEFAULT")
        .get(HomePage+ "api/v1/products/1")
        .queryParam("lang", "en")
        .queryParam("store", "DEFAULT")
        .check(jsonPath("$.id").findAll.saveAs("allProductsID"))
    )
  }










}
