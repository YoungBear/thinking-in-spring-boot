package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;


@SpringBootApplication
@RestController
public class DemoApplication {

    @RequestMapping("/")
    public String index() {
        return "Welcome, My Buddy!";
    }

    @Bean
    public RouterFunction<ServerResponse> helloWorld() {
        return route(GET("/hello-world"),
                request -> ok().body(Mono.just("Hello, World"), String.class)
        );
    }

    @EventListener(WebServerInitializedEvent.class)
    public void onWebServerReady(WebServerInitializedEvent event) {
        System.out.println("当前 WebServer 实现类为： " + event.getWebServer().getClass().getName());
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
