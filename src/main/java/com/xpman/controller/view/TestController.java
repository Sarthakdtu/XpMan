package com.xpman.controller.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/test")
    public String testBootstrap() {
        logger.info("Request received");
        return "test";
    }
}
