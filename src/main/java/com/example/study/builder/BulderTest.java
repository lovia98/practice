package com.example.study.builder;

import org.junit.Test;

public class BulderTest {

    @Test(expected = IllegalStateException.class)
    public void when_none_require_then_exception() {
        new Person.Builder().build();
    }

    @Test
    public void test() {
        Person junee = new Person.Builder()
            .firstName("han")
            .lastName("juhee")
            .address("ss")
            .addressDetail("dd")
            .zipCode("aaaa")
            .phoneNumber("123123")
            .build();
    }
}
