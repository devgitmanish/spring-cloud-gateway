package com.spring.cloud.gateway.filter;

import com.spring.cloud.gateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {

            if(validator.isSecured.test(exchange.getRequest())){
                /* Header contain token or not */
                if(!exchange.getRequest().getHeaders()
                        .containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new RuntimeException("missing authrorization header");
                }
                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if(authHeader!=null && authHeader.startsWith("Bearer")){
                    authHeader= authHeader.substring(7);
                }
                /* Rest Call to validate token */
                try{
                    //restTemplate.getForObject("http://localhost:9095/auth/validate?token"+authHeader, String.class);
                    jwtUtil.validateToken(authHeader);
                }catch (Exception ex){
                    System.out.println("Invalid access ..... ! ");
                    throw new RuntimeException("unauthorized access : invalid token");
                }
            }
            return chain.filter(exchange);
        }));
    }
    public static class Config{
    }
}
