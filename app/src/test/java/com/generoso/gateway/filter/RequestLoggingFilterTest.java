package com.generoso.gateway.filter;

import ch.qos.logback.classic.Level;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatusCode;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.http.server.reactive.MockServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.generoso.gateway.utils.LogUtils.assertMessageWasInLogs;
import static com.generoso.gateway.utils.LogUtils.getListAppenderForClass;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RequestLoggingFilterTest {

    @Test
    void shouldIncludeLogLines() {
        // Arrange
        var listAppender = getListAppenderForClass(RequestLoggingFilter.class);
        var response = new MockServerHttpResponse();
        response.setStatusCode(HttpStatusCode.valueOf(200));

        var request = MockServerHttpRequest.get("/example").build();
        var exchange = mock(ServerWebExchange.class);

        when(exchange.getRequest()).thenReturn(request);
        when(exchange.getResponse()).thenReturn(response);

        var chain = mock(WebFilterChain.class);
        when(chain.filter(any())).thenReturn(Mono.empty());

        // Act
        StepVerifier.create(new RequestLoggingFilter().filter(exchange, chain))
                .expectComplete()
                .verify();

        // Assert
        assertMessageWasInLogs(listAppender, "Incoming request GET /example", Level.INFO);
        assertMessageWasInLogs(listAppender, "Returning request with status code: 200 OK", Level.INFO);
    }
}
