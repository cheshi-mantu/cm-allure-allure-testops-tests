package xyz.egorivanov.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import xyz.egorivanov.examples.Layer;
import xyz.egorivanov.examples.Lead;
import xyz.egorivanov.examples.Microservice;

import static helpers.AttachmentsHelper.attachAsText;
import static io.qameta.allure.Allure.parameter;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Allure TestOps demo tests")
@Feature("Orders processing and billing")
@Tag("api_tests")
@Owner("egorivanov")
@Lead("Trr Chacha")

class SuccessOrFailOrBreakAPITests extends TestBase {

    @Test
    @Layer("api")
    @Microservice("billing")
    @DisplayName("Fake API test with possible assertion error")
    @Story("We get isTestAFailure from @BeforeEach method and throw assertion erorr if less than 1")
    @Severity(SeverityLevel.TRIVIAL)
    public void throwingAssertionFailedError() {
        parameter("FailThreshold", isTestAFailure);
        step ("Storing the text attachment", () -> {
            attachAsText("isTestAFailure",isTestAFailure+"");
            if (isTestAFailure < 1 ) {
                throw new AssertionFailedError("Assertion failed error, expected one but got two!", 1, isTestAFailure);
            }
        });
    }

    @Test
    @Layer("api")
    @Microservice("store_order")
    @DisplayName("Fake API test to assert generated value against 1 and throw exception if less")
    @Story("Get random value from @BeforeEach and then compare with 1, if less then throw assertion error")
    @Severity(SeverityLevel.NORMAL)
    public void assertEqualsWithFailTest() {
        parameter("FailThreshold", isTestAFailure);
        step ("Storing the text attachment", () -> {
            attachAsText("isTestAFailure",isTestAFailure+"");
            if (isTestAFailure < 1 ) {
                throw new AssertionFailedError("Assertion failed error, expected one but got the other!", ">3", isTestAFailure);
            }
        });
    }
    @Test
    @Layer("api")
    @Microservice("store_order")
    @DisplayName("Fake API test to Assert generated 1 against 1 and pass the test")
    @Story("Get random value from @BeforeEach and then compare with 1, if less then throw runtime exception error")
    @Severity(SeverityLevel.CRITICAL)
    public void compareGeneratedWithFixedAndThrowRuntimeExceptionTest() {
        parameter("FailThreshold", isTestAFailure);
        step ("Storing the text attachement", () -> {
            attachAsText("isTestAFailure",isTestAFailure+"");
        });
        step ("Useless assertion 1 against 1", () -> {
            assertEquals(1,1);
        });
    }
    @Test
    @Layer("api")
    @Microservice("process_order")
    @DisplayName("Fake API test to Assert generated value against 1 and throw runtime exception if less")
    @Story("Generated random int is tested against 5, this test will be a flaky one")
    @Severity(SeverityLevel.MINOR)
    @Flaky
    public void flakyRandomizedTest() {
        parameter("FailThreshold", isTestAFailure);
        step ("Storing the text attachment", () -> {
            attachAsText("isTestAFailure",isTestAFailure+"");
        });
        step ("Check if test needs to fail, then fail", () -> {
            assert(isTestAFailure>5);
        });
    }
    @Test
    @Layer("api")
    @Microservice("process_order")
    @DisplayName("Fake API test to always pass as we'll trick the assertion")
    @Story("Generated random int is tested against 3 and if less then runtime exception will be thrown")
    @Severity(SeverityLevel.MINOR)
    @Flaky
    public void goodTestWhichPasses() {
        parameter("FailThreshold", isTestAFailure);
        step ("Attaching isTestAFailure as text to the test results", () -> {
            attachAsText("isTestAFailure",isTestAFailure+"");

        });
        step ("Assertion will always pass", () -> {
            assert(isTestAFailure < 11);
        });
    }

}