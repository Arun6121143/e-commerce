package com.apigateway.apigatewayecommerce.filter;

import com.apigateway.apigatewayecommerce.config.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory {

    @Autowired
    private RouteValidator validator;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RestTemplate restTemplate;
    @Override
    public GatewayFilter apply(Object config) {
        return ((exchange, chain) ->{
            if(validator.isSecured.test(exchange.getRequest())){
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new RuntimeException("missing authorization");
                }
                String authHeaders = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if(authHeaders!=null && authHeaders.startsWith("Bearer ")){
                      authHeaders=authHeaders.substring(7);
                }
                try{
//                    restTemplate.getForObject("http://jwt-service/auth/validate?token"+authHeaders,String.class);
                    jwtService.validateToken(authHeaders);
                }
                catch (Exception e){
                    throw new RuntimeException("unauthorized access");
                }
            }
            return chain.filter(exchange);
        });
    }
}
