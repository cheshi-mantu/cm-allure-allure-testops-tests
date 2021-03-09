package xyz.egorivanov.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static helpers.Environment.failTest;
import static io.qameta.allure.Allure.step;
import static utils.RandomUtils.getRandomInt;

public class TestBase {
    public int isTestAFailure = 10;
    @BeforeAll
    public static void setUp() {
        step("System out to console before all tests", () -> {
            System.out.println("This is running before all the tests");
        });
    }
    @BeforeEach
    public void setUpEachTest() {
        if (failTest < 0 ) {
            isTestAFailure = getRandomInt(0, 10);
        }
        else {
            isTestAFailure = failTest;
        }

        step("Randomizer will help us to define if test will fail", () -> {
            System.out.println("Will the assert fail? - " + isTestAFailure);
        });
    }

    @AfterEach
    public void afterEach(){
        step("System out to console after each test", () -> {
        });
    }
}