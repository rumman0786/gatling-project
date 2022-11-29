package computerdatabase;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.FeederBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class LogginSimulation extends Simulation {

    String baseUrl = "https://rumman-laptop.therapbd.net:7001";

    String loginName = "zihad";
    String password = "therap321#";
    String providerCode = "SQA-TH";

//    FeederBuilder.Batchable<String> feeder =
//            csv("login.csv").random();// 1, 2
//
//    ChainBuilder login = exec(http("login-get").get("/auth/login"))
//            .feed(feeder)
//            .exec(http("login-post")
//                    .post("/auth/login")
//                    .formParam("loginName", "#{username}")
//                    .formParam("providerCode", "#{providerCode}")
//                    .formParam("password", password));

    ChainBuilder loggerTest = exec(http("logger-test").get("/bt/loggerTest").check(status().is(200)));

    HttpProtocolBuilder httpProtocol = http
            .baseUrl(baseUrl);
//            .contentTypeHeader("application/x-www-form-urlencoded")
//            .acceptEncodingHeader("gzip, deflate, br")
//            .connectionHeader("keep-alive")
//            .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
//            .userAgentHeader(
//                    "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36"
//            );

    ScenarioBuilder loggerScenario = scenario("loggerScenario").exec(loggerTest);

    {
        setUp(loggerScenario.injectOpen(rampUsers(100).during(10)))
                .protocols(httpProtocol);
    }
}