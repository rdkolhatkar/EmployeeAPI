package com.ratnakar.EmployeeAPI.simulation

import com.typesafe.config.{Config, ConfigFactory}
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration.DurationInt
class EmployeeAPI extends Simulation {

  val conf: Config = ConfigFactory.load()
  val appBaseUrl: String = conf.getString("app_base_url")
  println("****** Started Script With app base url: "+appBaseUrl+"********")
  val header = Map{
    "Content-Type" -> "application/json"
  }

  println("**** Before Actual Call ****")
  object Post {
    val post = scenario("Performance Testing For EmployeeAPI")
      .exec(http("EmployeeAPI request").post("/addEmployee")
      .headers(header)
        .body(StringBody("{\n    \"employee_name\" : \"${employee_name}\",\n    \"employee_id\" : \"${employee_id}\",\n    \"job_role\" : \"${job_role}\"\n}"))
      .asJson.check(status.in(200, 201, 202))
      .check(status.not(400), status.not(500))
      .check(bodyString.saveAs("myResponse"))
      ).pause(400.milliseconds)
  }
  println("**** After Actual Call ****")
  val httpConf = http.baseUrl(appBaseUrl)
  val scn = scenario("API Request").feed(csv("Data.csv").circular)
    .exec(Post.post)
    .exec{session =>
      println("**** Session Output ****")
      println(session("myResponse").as[String])
      session
    }
  setUp(
    scn.inject(
      atOnceUsers(1)
    )
  ).assertions(forAll.responseTime.max.lte(5000)).protocols(httpConf)
}
