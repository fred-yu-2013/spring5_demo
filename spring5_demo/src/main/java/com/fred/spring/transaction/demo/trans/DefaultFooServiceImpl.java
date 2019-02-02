package com.fred.spring.transaction.demo.trans;

import com.fred.spring.transaction.demo.dao.MovieFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Fred
 */
@Service("fooService")
public class DefaultFooServiceImpl implements FooService {

    /**
     * Integrate with DAO, then with JDBC.W
     */
    @Autowired
    private MovieFinder movieFinder;

    @Override
    public Foo getFoo(String fooName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Foo getFoo(String fooName, String barName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void insertFoo(Foo foo) {
        throw new UnsupportedOperationException();
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void updateFoo(Foo foo) {
        this.movieFinder.execute();
    }

}
