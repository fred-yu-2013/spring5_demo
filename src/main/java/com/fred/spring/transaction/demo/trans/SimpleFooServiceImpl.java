package com.fred.spring.transaction.demo.trans;

import com.fred.spring.transaction.demo.dao.MovieFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author Fred
 */
@Service("fooServiceWithTransactionTemplate")
public class SimpleFooServiceImpl implements FooService {

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public Foo getFoo(String fooName) {
        return null;
    }

    @Override
    public Foo getFoo(String fooName, String barName) {
        return null;
    }

    @Override
    public void insertFoo(Foo foo) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            // the code in this method executes in a transactional context
            @Override
            public void doInTransactionWithoutResult(TransactionStatus status) {
                throw new UnsupportedOperationException();
            }
        });
    }

    @Override
    public void updateFoo(Foo foo) {

    }
}
