package scenarios


import config.BaseHelpers._
import io.gatling.core.structure._
import io.gatling.core.Predef._
import io.gatling.http.Predef._







object ShopDemo {


  private val TableAndChairAddingToCart = System.getProperty("tableAndChair","50").toDouble
  private val EventualBuyersPercent = System.getProperty("eventual","30").toDouble


  def scnShopDemo: ScenarioBuilder = {
    scenario ("Buy Table or Chair")
      .exec(flushHttpCache)
      .exec(flushCookieJar)
      .exitBlockOnFail(
        group("Open home page"){
          exec(api.homePage.getLocalhost())
            .exec(api.commonApi.healthPing())
            .exec(api.commonApi.getContentBoxes())
            .exec(api.commonApi.getCategory())
            .exec(api.commonApi.getContentPages())
            .exec(api.commonApi.getStoreDefault())
            .exec(api.homePage.featuredItem())
            .foreach("${allProductsID}", "id"){
              exec(api.commonApi.postPrice("${id}"))
                .exec(api.commonApi.postPrice("${id}"))
            }
            .exec(thinkTimer())
        }
          .group("Open Tab with tables"){
            exec(api.commonApi.healthPing())
              .exec(api.commonApi.getContentBoxes())
              .exec(api.commonApi.getCategory())
              .exec(api.commonApi.getContentPages())
              .exec(api.commonApi.getStoreDefault())
              .exec(api.tablesTab.getCategoryStore())
              .exec(api.tablesTab.getCategoryManufacturers())
              .exec(api.tablesTab.getProductsStore())
              .foreach("${allProductsID}", "id") {
                exec(api.commonApi.postPrice("${id}"))

              }
              .exec(api.tablesTab.getCategoryVariants())
              .exec(thinkTimer())
          }
          .group("Click on table"){
            exec(api.commonApi.healthPing())
              .exec(api.commonApi.getContentBoxes())
              .exec(api.commonApi.getCategory())
              .exec(api.commonApi.getContentPages())
              .exec(api.commonApi.getStoreDefault())
              .exec(api.selectTable.getProductsReviews())
              .exec(api.selectTable.getProductsStoreDefault())
              .foreach("${allProductsID}", "id") {
                  exec(api.commonApi.postPrice("${id}"))
                }
                .exec(thinkTimer())
          }
          .group("Add table to cart"){
            exec(api.addToCartButton.addToCart())
              .exec(api.addToCartButton.getCartID())
              .exec(thinkTimer())
          }
          .randomSwitch(TableAndChairAddingToCart->group(s"Open tab with chairs $TableAndChairAddingToCart") {
            exec(api.commonApi.healthPing())
              .exec(api.commonApi.getContentBoxes())
              .exec(api.commonApi.getCategory())
              .exec(api.commonApi.getContentPages())
              .exec(api.commonApi.getStoreDefault())
              .exec(api.chairsTab.getProductsStoreDefault())
              .foreach("${allProductsID}", "id") {
                exec(api.commonApi.postPrice("${id}"))
              }
              .exec(api.chairsTab.getCategoryStoreDefault())
              .exec(api.chairsTab.getCategoryManufacturers())
              .exec(api.chairsTab.getCategoryVariants())
              .exec(thinkTimer())
          }

            .group("Select Random Chair") {
              exec(api.commonApi.healthPing())
                .exec(api.commonApi.getContentBoxes())
                .exec(api.commonApi.getCategory())
                .exec(api.commonApi.getContentPages())
                .exec(api.selectRandomChair.getProductsDefault("${randomProductID}"))
                .exec(api.selectRandomChair.getProductsReviews("${randomProductID}"))
                .exec(api.commonApi.getStoreDefault())
                .exec((api.commonApi.postPrice("${randomProductID}")))

                .exec(thinkTimer())
            }

            .group ("Add chair to cart") {
              exec(api.addToCartButton.getCartID())
                .exec(api.addToCartButton.addToCart())
                .exec(thinkTimer())
            }

            .randomSwitch(
              EventualBuyersPercent -> group (s"Checkout $EventualBuyersPercent"){
                exec(api.commonApi.healthPing())
                  .exec(api.commonApi.getContentBoxes())
                  .exec(api.commonApi.getStoreDefault())
                  .exec(api.commonApi.getCategory())
                  .exec(api.commonApi.getContentPages())
                  .exec(api.checkOut.getShippingCountry())
                  repeat(2) {
                    exec(api.checkOut.getZonesCode())
                  }
                  .exec(api.checkOut.getCartWithID())
                  .exec(api.checkOut.getConfig())
                  .exec(api.checkOut.getTotal())
//                  .exec(api.checkOut.postShipping())
                  .exec(thinkTimer())
              }

            )
          )

      )

  }

}
