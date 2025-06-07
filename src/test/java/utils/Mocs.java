package utils;

import com.github.tomakehurst.wiremock.WireMockServer;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class Mocs {

    public static WireMockServer startFlagMock(String variant) {
        WireMockServer wireMockServer = new WireMockServer(options().dynamicPort());
        wireMockServer.start();

        configureFor("localhost", wireMockServer.port());

        stubFor(get(urlEqualTo("/feature-flags"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"login_variant\":\"" + variant + "\"}")));

        System.setProperty("flag.api.url", "http://localhost:" + wireMockServer.port());
        return wireMockServer;
    }
}
