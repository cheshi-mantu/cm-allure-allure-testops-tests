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
        step("Setting up all the tests", () -> {
            System.out.println("This is running before all the tests");
        });
        step("Setup results", () -> {
            attachAsText("fooText", "@BeforeAll completed successfully");
        });

    }
    @BeforeEach
    public void setUpEachTest() {
        step("This step decides if a test fill fail based on random value", () -> {
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
            AttachmentsHelper.attachAsText("After each text 1", "three");
            AttachmentsHelper.attachAsText("After each text 2", "two");
            AttachmentsHelper.attachAsText("After each text 3", "One!");
        });
    }
}