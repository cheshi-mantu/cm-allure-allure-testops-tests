package xyz.egorivanov;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static io.qameta.allure.Allure.step;
import static utils.RandomUtils.getRandomInt;

public class TestBase {
    public int isTestAFailure = 0;
    @BeforeAll
    public static void setUp() {
        step("System out to console before all tests", () -> {
            System.out.println("This is running before all the tests");
        });
    }
    @BeforeEach
    public void setUpEachTest() {
        isTestAFailure = getRandomInt(0,10);
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