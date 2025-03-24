package com.pramukh.shoppingapplication.orderservice;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.TestcontainersConfiguration;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderServiceApplicationTests {

    @ServiceConnection
    static MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest");

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
        System.out.println("MySQL Container URL: " + mySQLContainer.getJdbcUrl());
    }

    static {
        mySQLContainer.start();
    }

    @Test
    void createOrder() {
        String requestBody = """
                {
                        "skucode":"iphone 17 Pro MAX",
                        "price":"1700000.45",
                        "quantity":"150"
                }
                """;
        System.out.println("Request Body: " + requestBody);

        RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/order/placeorder")
                .then()
                .statusCode(201)
                .body("id", Matchers.notNullValue())
                .body("skucode", Matchers.equalTo("iphone 17 Pro MAX"))
                .body("price", Matchers.equalTo(1700000.45f))
                .body("quantity", Matchers.equalTo(150));

    }

}
