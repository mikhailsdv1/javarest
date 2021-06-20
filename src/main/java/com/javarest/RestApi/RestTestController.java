package com.javarest.RestApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("rest")
@CrossOrigin
public class RestTestController {

    Logger logger = LoggerFactory.getLogger(RestTestController.class);

    //http://localhost:8090/rest/ping
    @GetMapping("/ping")
    public String ping() {
        return "test";
    }


    //http://localhost:8090/rest/create-test-data
    @GetMapping(value = {"/create-test-data"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createTestData(@RequestParam(name = "print-only", required = false) String printOnly) throws IOException {
        logger.info("about to create indexes if needed");
        return "";
    }


}