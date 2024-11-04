package ru.darin.testList.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class NetworkUtilsTest {

    @Test
    @Timeout(value = 2)
    void getConnectionShouldReturnFasterThanOneSecond() {
//        Assertions.assertTimeout(Duration.ofSeconds(0), ()->NetworkUtils.getConnection());
        NetworkUtils.getConnection();
    }
}