package simulations
import io.gatling.core.Predef._
import config.BaseHelpers._
import scenarios.ShopDemo.scnShopDemo

class PerfTestSimulation extends Simulation {
setUp(
  //scnShopDemo.inject(atOnceUsers(1))
//  scnShopDemo.inject(atOnceUsers(System.getProperty("ShopDemoUsers", "1").toInt))
    scnShopDemo.inject(rampUsers(1).during(15))
).protocols(httpProtocol)

}
