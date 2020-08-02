package lk.techtalks.consul.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/")
@RefreshScope
public class GreetingController {

    @Value("${my.property.conf}")
    private String conf;

    @GetMapping
    public String greet() throws UnknownHostException {
        return "Hello , "+conf+ " from " + InetAddress.getLocalHost().getHostName();
    }

}
