package com.example.msvcdojo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Josep Maria on 06/07/2016.
 */
@RestController
@RequestMapping("/")
public class HomeController {

    @RequestMapping("/")
    String home() {
        return "Hello World";
    }
}
