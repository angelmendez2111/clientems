package edu.unitru.clientems.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import java.util.Map;

@FeignClient(name = "mockyClient", url = "https://run.mocky.io")
public interface MockyClient {
    @GetMapping("/v3/0bbc0ff0-c498-4212-9239-868f6d2ea251")
    ResponseEntity<Map<String, Integer>> obtenerMonto();
}
