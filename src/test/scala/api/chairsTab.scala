package api

import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._
import config.BaseHelpers._

object chairsTab {

  def getProductsStoreDefault(): ChainBuilder = {
    exec(
      http("Get products/store=DEFAULT")
        .get(HomePage + "api/v1/products")
        .queryParam("store", "DEFAULT")
        .queryParam("lang", "en")
        .queryParam("page", "0")
        .queryParam("count", "15")
        .queryParam("category", "51")
        .check(jsonPath("$.products[*].id").findAll.saveAs("allProductsID"))
    )
  }
  def getCategoryStoreDefault(): ChainBuilder = {
    exec(
      http("Get category/store=DEFAULT")
        .get(HomePage + "api/v1/category/51")
        .queryParam("store", "DEFAULT")
        .queryParam("lang", "en")
    )
  }



  def getCategoryManufacturers(): ChainBuilder = {
    exec(
      http("Get category/51/manufacturers")
        .get(HomePage + "api/v1/category/51/manufacturers")
        .queryParam("store", "DEFAULT")
        .queryParam("lang", "en")
    )
  }

  def getCategoryVariants(): ChainBuilder = {
    exec(
      http("Get category/variants")
        .get(HomePage + "api/v1/category/51/variants")
        .queryParam("store", "DEFAULT")
        .queryParam("lang", "en")
        .check(status.is(404))
    )
  }



}
