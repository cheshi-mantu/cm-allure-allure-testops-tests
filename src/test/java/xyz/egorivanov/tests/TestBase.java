package xyz.egorivanov.tests;

import helpers.AttachmentsHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static helpers.AttachmentsHelper.attachAsText;
import static helpers.Environment.failTest;
import static io.qameta.allure.Allure.step;
import static utils.RandomUtils.getRandomInt;

public class TestBase {
    public int isTestAFailure = 10;
    @BeforeAll
    public static void setUpBeforeAll() {
        step("Before all with sout", () -> {
            System.out.println("This is running before all the tests");
        });
        step("Before all with a txt attachment", () -> {
            attachAsText("fooText", "@BeforeAll completed successfully");
        });
        step("Another step in Before all with txt attach", () -> {
            attachAsText("fooText", "@BeforeAll completed successfully");
        });
    }
    @BeforeEach
    public void setUpEachTest() {
        step("Before each with a random value", () -> {
            if (failTest < 0 ) {
                isTestAFailure = getRandomInt(0, 10);
            }
            else {
                isTestAFailure = failTest;
            }
            attachAsText("Test fail threshold", failTest + "");
            attachAsText("Is thes a failure value", isTestAFailure + "");
        });

        step("Randomizer will help us to define if test will fail", () -> {
            System.out.println("Will the assert fail? - " + isTestAFailure);
        });
        step("Setup results", () -> {
            attachAsText("fooText", "@BeforeEach completed successfully");
        });

    }

    @AfterEach
    public void afterEach(){
        step("System out to console after each test", () -> {
        });
    }
}