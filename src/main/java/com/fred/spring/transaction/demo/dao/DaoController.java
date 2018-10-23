package com.fred.spring.transaction.demo.dao;

import com.fred.spring.transaction.demo.trans.Foo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Fred
 */
@RestController
public class DaoController {

    private final MovieFinder movieFinder;

    @Autowired
    DaoController(MovieFinder movieFinder) {
        this.movieFinder = movieFinder;
    }

    @RequestMapping("/movieFinder")
    public String movieFinder() {
        this.movieFinder.execute();
        return "movieFinder";
    }
}
