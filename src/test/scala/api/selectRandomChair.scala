package api
import config.BaseHelpers._
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._


object selectRandomChair {

  def getProductsDefault(productId: String = "50"): ChainBuilder = {
    exec(
      http("Get products/DEFAULT")
        .get(HomePage+ s"api/v1/products/$productId")
        .queryParam("lang", "en")
        .queryParam("store", "DEFAULT")
//        .check(jsonPath("$.id").saveAs("usedProductsID")) // I will execute the script by adding random saved product id, and then I will save this randomly chosen product id to use in further queries

    )
  }

  def getProductsReviews(productId: String = "50"): ChainBuilder = {
    exec(
      http("Get products/reviews")
        .get(HomePage + s"api/v1/products/$productId/reviews")
        .queryParam("store", "DEFAULT")
    )
  }



}
