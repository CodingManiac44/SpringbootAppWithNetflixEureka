package phoenix.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class APIGatewayCustomFilter implements GlobalFilter {
    Logger logger = LoggerFactory.getLogger(APIGatewayCustomFilter.class);
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("Global Pre Filter executed ");
        logger.info("Request Headers = " + exchange.getRequest().getHeaders());
        //return chain.filter(exchange);
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            logger.info("Global Post Filter executed: " + exchange.getResponse().getStatusCode());
        }));
    }
}
