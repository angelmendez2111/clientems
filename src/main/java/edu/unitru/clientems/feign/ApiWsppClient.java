package edu.unitru.clientems.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@FeignClient(name = "apiWsppClient", url = "http://localhost:3002")
public interface ApiWsppClient {

    @PostMapping("/send-message")
    ResponseEntity<String> sendMessage(MessageRequest request);
}
