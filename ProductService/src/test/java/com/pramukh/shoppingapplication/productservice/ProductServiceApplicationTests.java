package com.pramukh.shoppingapplication.productservice;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Matches;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
        System.out.println("MongoDB Container URL: " + mongoDBContainer.getReplicaSetUrl());
    }

    static {
        mongoDBContainer.start();
    }

    @Test
    void createProduct() {
        String requestBody = """
                {
                    "productName":"IPhone",
                    "productDescription" :"Its a good Phone",
                    "productPrice" : 123567.23
                }
                """;
        System.out.println("Request Body: " + requestBody);

         RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/product/create")
                .then()
                .statusCode(201)
                .body("id", Matchers.notNullValue())
                .body("productName", Matchers.equalTo("IPhone"))
                .body("productDescription", Matchers.equalTo("Its a good Phone"))
                .body("productPrice", Matchers.equalTo(123567.23f));

    }

}
