package utils;

import com.github.tomakehurst.wiremock.WireMockServer;

import java.util.concurrent.ThreadLocalRandom;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class Mocks {

    public static WireMockServer startFlagMock() {
        WireMockServer wireMockServer = new WireMockServer(options().dynamicPort());
        String variant = ThreadLocalRandom.current().nextBoolean() ? "A" : "B";
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
