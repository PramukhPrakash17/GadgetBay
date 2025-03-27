package com.pramukh.shoppingapplication.inventoryservice;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.queryParam;
@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventoryServiceApplicationTests {
    @ServiceConnection
    static MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest");
    static {
        mySQLContainer.start();
    }
    @LocalServerPort
    int port;
    @BeforeEach
    void setUp() {
        System.out.println("Port: " + port);
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;

    }
    @Test
    void Instock() {


        var response = RestAssured.given()
                .queryParam("skucode", "iphone_15")
                .queryParam("quantity", "90")
                .when()
                .get("/api/inventory/checkinventory")
                .then()
                .statusCode(200)
                .extract()
                .as(Boolean.class);

        assertThat(response, Matchers.is(true));

    }

    @Test
    void OutOfstock() {
//        String requestBody = """
//                {
//                        "skucode":"iphone_16",
//                        "quantity":"500"
//                }
//                """;
//        System.out.println("Request Body: " + requestBody);

        var response = RestAssured.given()
                .queryParam("skucode", "iphone_15")
                .queryParam("quantity", "1500")
                .when()
                .get("/api/inventory/checkinventory")
                .then()
                .statusCode(200)
                .extract()
                .as(Boolean.class);

        assertThat(response, Matchers.is(false));

    }

    @Test
    void notfound() {


        var response = RestAssured.given()
                .queryParam("skucode", "samsung")
                .queryParam("quantity", "500")
                .when()
                .get("/api/inventory/checkinventory")
                .then()
                .statusCode(200)
                .extract()
                .as(Boolean.class);

        assertThat(response, Matchers.is(false));

    }

}
