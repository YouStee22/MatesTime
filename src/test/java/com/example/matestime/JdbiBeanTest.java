package com.example.matestime;

import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class JdbiBeanTest {

    @Autowired
    private Jdbi jdbi;

    @Test
    public void testJdbiBeanExists() {
        assertNotNull(jdbi);
    }
}

