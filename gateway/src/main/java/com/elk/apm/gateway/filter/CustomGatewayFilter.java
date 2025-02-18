package com.elk.apm.gateway.filter;

import co.elastic.apm.api.ElasticApm;
import co.elastic.apm.api.Span;
import co.elastic.apm.api.Transaction;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 * @author mojib.haider
 * @since 2/12/25
 */
@Component
public class CustomGatewayFilter extends AbstractGatewayFilterFactory<CustomGatewayFilter.Config> {

    public CustomGatewayFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            Transaction transaction = ElasticApm.currentTransaction();
            Span span = ElasticApm.currentSpan();

            String traceParentHeaderValue = null;

            // Manually construct the Elastic-Apm-Traceparent header
            String traceId = transaction.getTraceId();
            String spanId = span.getId();  //Use span id instead of transaction id.
            // The last part is flags, 01 means sampled.
            traceParentHeaderValue = "00-" + traceId + "-" + spanId + "-01";  // Correct format!

            ServerHttpRequest.Builder requestBuilder = exchange.getRequest().mutate();

            requestBuilder.header("Elastic-Apm-Traceparent", traceParentHeaderValue);

            ServerHttpRequest modifiedRequest = requestBuilder.build();

            span.injectTraceHeaders(requestBuilder::header);

            return chain.filter(exchange.mutate().request(modifiedRequest).build());
        };

//            Transaction transaction = ElasticApm.currentTransaction();
//            Span span = ElasticApm.currentSpan();
//
//            System.out.println("Transaction id: " + transaction.getId() + " Span id: " + span.getId());
//
//            Transaction newTransaction = ElasticApm.startTransaction();
//            Span newSpan = ElasticApm.currentSpan();
//
//            System.out.println("Transaction id: " + newTransaction.getId() + " Span id: " + newSpan.getId());
//
//            ServerHttpRequest request = exchange.getRequest();
//
//            System.out.println("Custom gateway filter");
//
//            return chain.filter(exchange.mutate()
//                    .request(request)
//                    .build());
//        };
    }

    public static class Config{

    }
}
