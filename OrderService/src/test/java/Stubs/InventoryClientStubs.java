package Stubs;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class InventoryClientStubs {

    public static void stubInventoryCall(String skucode, Integer quantity) {
        // Stub the call to the inventory service
        stubFor(get(urlEqualTo("/api/inventory/checkinventory?skucode=" + skucode + "&quantity=" + quantity))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withStatus(200)
                .withBody("true")));
    }
}
