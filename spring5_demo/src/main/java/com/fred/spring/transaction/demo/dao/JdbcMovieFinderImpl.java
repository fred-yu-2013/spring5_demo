package com.fred.spring.transaction.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

/**
 * Repository代表存储层的注解
 * @author Fred
 */
@Repository("movieFinder")
public class JdbcMovieFinderImpl implements MovieFinder {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void init(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void execute() {
        int rowCount = this.jdbcTemplate.queryForObject("select count(*) from search_key", Integer.class);
        System.out.println("RESULT: " + rowCount);
    }

}
