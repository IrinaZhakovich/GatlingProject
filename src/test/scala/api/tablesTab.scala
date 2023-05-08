package api

import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._
import config.BaseHelpers._

object tablesTab {
  def getCategoryStore (): ChainBuilder = {
    exec(
      http("Get category/store")
        .get(HomePage+ "api/v1/category/50?store=DEFAULT&lang=en")
        .queryParam("store", "DEFAULT")
        .queryParam("lang", "en")

    )
  }

  def getProductsStore(): ChainBuilder = {
    exec(
      http("Get products/store")
        .get(HomePage + "api/v1/products")
        .queryParam("store", "DEFAULT")
        .queryParam("lang", "en")
        .queryParam("page", "0")
        .queryParam("count", "15")
        .queryParam("category", "50")
        .check(jsonPath("$.products[*].id").findAll.saveAs("allProductsID"))


    )
  }



  def getCategoryManufacturers(): ChainBuilder = {
    exec(
      http("Get category/manufacturers")
        .get(HomePage + "api/v1/category/50/manufacturers")
        .queryParam("store", "DEFAULT")
        .queryParam("lang", "en")
    )
  }



  def getCategoryVariants(): ChainBuilder = {
    exec(
      http("Get Category/variants")
        .get(HomePage + "api/v1/category/50/variants")
        .queryParam("lang", "en")
        .queryParam("store", "DEFAULT")
        .check(status.is(404))
    )
  }


}
