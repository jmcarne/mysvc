package com.example.msvcdojo.model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Josep Maria on 06/07/2016.
 */
@RestController
@RequestMapping("/health")
public class HealthController {

    @RequestMapping("/status")
    String healthController() {

        //getConnection();
        //sql
        //jdbcTemplate
        //closeConn();

        return "Health Status Oracle BBDD";
    }
}
