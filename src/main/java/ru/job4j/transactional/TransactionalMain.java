package ru.job4j.transactional;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class TransactionalMain {

    @Transactional
    public void inner() {

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void outer() {
        inner();
    }
}
