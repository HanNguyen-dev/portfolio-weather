package com.portfolio.weather.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("servlet")
    public String getIpAddress(HttpServletRequest request) {
        return request.getRemoteAddr();
    }

    @GetMapping("forward")
    public String getForwarded(@RequestHeader(value = "X-Forwarded-For", required = false) String forwarded) {
        return forwarded;
    }

    @GetMapping("headone")
    public String getHeadOne(@RequestHeader(value = "X-Head-One", required = false) String headOne) {
        return headOne;
    }

    @GetMapping("headtwo")
    public String getHeadTwo(@RequestHeader(value = "X-Head-Two", required = false) String headTwo) {
        return headTwo;
    }

}