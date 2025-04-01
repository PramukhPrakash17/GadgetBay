package com.pramukh.shoppingapplication.apigateway.Routes;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class Routes {

    @Bean
    public RouterFunction<ServerResponse> productServiceRoute() {
        return GatewayRouterFunctions.route("product_service")
                .route(RequestPredicates.path("/api/product/create").and(RequestPredicates.method(HttpMethod.POST)),
                HandlerFunctions.http("http://localhost:8080"))

                .route(RequestPredicates.path("/api/product/getall").and(RequestPredicates.method(HttpMethod.GET)),
                HandlerFunctions.http("http://localhost:8080"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> OrderService() {
        return GatewayRouterFunctions.route("order_service")
                .route(RequestPredicates.path("/api/order/placeorder").and(RequestPredicates.method(HttpMethod.POST)),
                HandlerFunctions.http("http://localhost:8081")).build();
    }

    @Bean
    public RouterFunction<ServerResponse> InventoryService() {
        return GatewayRouterFunctions.route("inventory_service")
                .route(RequestPredicates.path("/api/inventory/checkinventory").and(RequestPredicates.method(HttpMethod.GET)),
                        HandlerFunctions.http("http://localhost:8082")).build();
    }


}