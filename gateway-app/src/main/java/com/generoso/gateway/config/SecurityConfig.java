package com.generoso.gateway.config;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.config.PropertiesRouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity.CsrfSpec;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher.MatchResult;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class SecurityConfig {

    @Autowired
    private PropertiesRouteDefinitionLocator routeDefinition;

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
        http.csrf(CsrfSpec::disable);

        http.authorizeExchange(authorizeExchangeSpec ->
                authorizeExchangeSpec.matchers(exchange ->
                                RegexRequestMatcher.matches(".*/private/.*", exchange)).permitAll()
                        .anyExchange().authenticated()
        );

        http.oauth2Login(Customizer.withDefaults());
        http.oauth2Client(Customizer.withDefaults());
        return http.build();
    }

    private static class RegexRequestMatcher {

        private static final Cache<String, Pattern> CACHE = CacheBuilder.newBuilder().build();
        private static final ConcurrentMap<String, Pattern> PATTERNS = CACHE.asMap();

        public static Mono<ServerWebExchangeMatcher.MatchResult> matches(String regex, ServerWebExchange exchange) {
            return matches(regex, exchange.getRequest().getURI().getPath());
        }

        public static Mono<MatchResult> matches(String regex, String uri) {
            var pattern = getPattern(regex);
            var matcher = pattern.matcher(uri);
            var matches = matcher.matches();
            return Mono.just(matches)
                    .flatMap(m -> Boolean.TRUE.equals(m) ?
                            ServerWebExchangeMatcher.MatchResult.match()
                            : ServerWebExchangeMatcher.MatchResult.notMatch());
        }

        private static Pattern getPattern(String regex) {
            return PATTERNS.computeIfAbsent(regex, Pattern::compile);
        }
    }
}
