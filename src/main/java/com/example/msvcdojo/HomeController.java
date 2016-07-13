package com.example.msvcdojo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Josep Maria on 06/07/2016.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping("/")
    String home() {
        return "Hello World";
    }
}
