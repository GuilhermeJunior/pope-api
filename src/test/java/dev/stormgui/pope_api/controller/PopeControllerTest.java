package dev.stormgui.pope_api.controller;

import dev.stormgui.pope_api.model.dto.PopeResponse;
import dev.stormgui.pope_api.model.dto.PopesResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PopeControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldTestGetByIdEndpoint() {
        var url = getURL() + "/1";
        ResponseEntity<PopeResponse> response = restTemplate.getForEntity(url, PopeResponse.class);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);
    }

    @Test
    void shouldTestGetAllEndpoint() {
        ResponseEntity<PopesResponse> response = restTemplate.getForEntity(getURL(), PopesResponse.class);

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().popes())
                .hasSize(266);
    }

    private String getURL() {
        return "http://localhost:" + port + "/api/v1/popes";
    }
}
