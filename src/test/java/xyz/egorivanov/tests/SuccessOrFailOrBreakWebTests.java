package xyz.egorivanov.tests;

import xyz.egorivanov.examples.Layer;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.opentest4j.AssertionFailedError;
import xyz.egorivanov.examples.Lead;
import xyz.egorivanov.examples.Microservice;

import static helpers.AttachmentsHelper.attachAsText;

import static io.qameta.allure.Allure.parameter;
import static io.qameta.allure.Allure.step;

@Epic("Allure TestOps demo tests")
@Feature("UI orders processing and billing")
@Tag("web_tests")
@Owner("egorivanov")
@Lead("Trr Pumba")
class SuccessOrFailOrBreakWebTests extends TestBase {

    @Test
    @Layer("web")
    @Microservice("billing")
    @DisplayName("Fake web test throws AssertionFailedError sometimes")
    @Story("If test is to fail then throw an exception, otherwise don't")
    @Severity(SeverityLevel.TRIVIAL)
    public void throwingAssertionFailedError() {
        parameter("FailThreshold", isTestAFailure);
        step ("Check if test needs to fail, then fail", () -> {
            attachAsText("isTestAFailure",isTestAFailure+"");
            if (isTestAFailure < 2 ) {
                throw new AssertionFailedError("Assertion failed error, expected one but got two!", 1, isTestAFailure);
            }
        });
    }

    @Test
    @Layer("web")
    @Microservice("store_order")
    @DisplayName("Fake web test asserts generated value against 1 and throws exception")
    @Story("Generated random int is tested against 3 and if less then test will fail")
    @Severity(SeverityLevel.NORMAL)
    public void assertEqualsWithFailTest() {
        parameter("FailThreshold", isTestAFailure);
        step ("Attach random value as text to the step", () -> {
            attachAsText("isTestAFailure",isTestAFailure+"");
        });
        step ("Check if test needs to fail, then fail", () -> {
            attachAsText("isTestAFailure",isTestAFailure+"");
            if (isTestAFailure < 1 ) {
                throw new AssertionFailedError("Assertion failed error, expected one but got the other!", ">3", isTestAFailure);
            }
        });
    }
    @Test
    @Layer("web")
    @Microservice("process_order")
    @DisplayName("Assert generated value against 1 and throw runtime exception if less")
    @Story("Generated random int is tested against 3 and if less then runtime exception will be thrown")
    @Severity(SeverityLevel.CRITICAL)
    public void compareGeneratedWithFixedAndThrowRuntimeExceptionTest() {
        parameter("FailThreshold", isTestAFailure);
        step ("Attach random value as text to the step", () -> {
            attachAsText("isTestAFailure",isTestAFailure+"");
            if (isTestAFailure < 1 ) {
                throw new RuntimeException("FailThreshold less than 1");
            }
        });
    }
    @Test
    @Layer("web")
    @Microservice("billing")
    @DisplayName("Assert generated value against 5 and throw runtime exception if less")
    @Story("Generated random int is tested against 3 and if less then runtime exception will be thrown")
    @Severity(SeverityLevel.MINOR)
    @Flaky
    public void flakyRandomizedTest() {
        parameter("FailThreshold", isTestAFailure);
        step ("Attach random value as text to the step", () -> {
            attachAsText("isTestAFailure",isTestAFailure+"");
        });
        step ("Check if test needs to fail, then fail", () -> {
            assert(isTestAFailure>5);
        });
    }
    @Test
    @Layer("web")
    @Microservice("process_order")
    @DisplayName("This test will always pass")
    @Story("Generated random int is tested against 3 and if less then runtime exception will be thrown")
    @Severity(SeverityLevel.MINOR)
    public void goodTestWhichPasses() {
        parameter("FailThreshold", isTestAFailure);
        step ("Attach random value as text to the step", () -> {
            attachAsText("isTestAFailure",isTestAFailure+"");

        });
        step ("Assertion will always pass", () -> {
            assert(isTestAFailure<11);
        });
    }

}