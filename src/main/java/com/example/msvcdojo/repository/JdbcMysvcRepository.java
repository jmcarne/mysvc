package com.example.msvcdojo.repository;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by Josep Maria on 06/07/2016.
 */
public class JdbcMysvcRepository {
    private JdbcTemplate jdbcTemplate;

    public JdbcMysvcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


}
