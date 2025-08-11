package com.cdac;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class UrbanCompanyApplicationTests {

    @Test
    void contextLoads() {
        // This test will pass if the Spring application context loads successfully
    }

    @Test
    void applicationStarts() {
        // This test verifies that the application can start without errors
        Application.main(new String[]{});
    }
} 