package dev.stormgui.pope_api.controller;

import dev.stormgui.pope_api.controller.utils.CustomPageImpl;
import dev.stormgui.pope_api.model.dto.PopeResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
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
        ResponseEntity<CustomPageImpl<PopeResponse>> response = restTemplate.exchange(
                getURL() + "?page=0&size=10",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getTotalElements())
                .isEqualTo(10);
    }

    @Test
    void shouldReturnStatus404WhenResourceNotFound() {
        var url = getURL() + "/3000";
        ResponseEntity<Object> response =  restTemplate.getForEntity(url, Object.class);
        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.NOT_FOUND);

    }

    private String getURL() {
        return "http://localhost:" + port + "/api/v1/popes";
    }
}
