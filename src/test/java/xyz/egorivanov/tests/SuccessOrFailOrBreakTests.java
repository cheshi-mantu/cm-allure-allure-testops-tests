package xyz.egorivanov.tests;

import xyz.egorivanov.examples.Layer;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.opentest4j.AssertionFailedError;

import static helpers.AttachmentsHelper.attachAsText;

import static io.qameta.allure.Allure.parameter;
import static io.qameta.allure.Allure.step;

@Epic("Allure TestOps demo tests")
@Feature("Elements")
@Tag("various_tests")
@Owner("egorivanov")
class SuccessOrFailOrBreakTests extends TestBase {

    @Test
    @Layer("web")
    @DisplayName("This test throws AssertionFailedError sometimes")
    @Description("If test is to fail then throw an exception, otherwise don't")
    @Severity(SeverityLevel.TRIVIAL)
    public void throwingAssertionFailedError() {
        parameter("ReasonToLive", isTestAFailure);
        step ("Check if test needs to fail, then fail", () -> {
            attachAsText("isTestAFailure",isTestAFailure+"");
            if (isTestAFailure < 2 ) {
                throw new AssertionFailedError("Assertion failed error, expected one but got two!", 1, isTestAFailure);
            }
        });
    }

    @Test
    @Layer("web")
    @DisplayName("Assert generated value against 2 and throw exception")
    @Description("Generated random int is tested against 3 and if less then test will fail")
    @Severity(SeverityLevel.NORMAL)
    public void assertEqualsWithFailTest() {
        parameter("ReasonToLive", isTestAFailure);
        step ("Check if test needs to fail, then fail", () -> {
            attachAsText("isTestAFailure",isTestAFailure+"");
            if (isTestAFailure < 2 ) {
                throw new AssertionFailedError("Assertion failed error, expected one but got the other!", ">3", isTestAFailure);
            }
        });
    }
    @Test
    @Layer("web")
    @DisplayName("Assert generated value against 1 and throw runtime exception if less")
    @Description("Generated random int is tested against 3 and if less then runtime exception will be thrown")
    @Severity(SeverityLevel.CRITICAL)
    public void compareGeneratedWithFixedAndThrowRuntimeExceptionTest() {
        parameter("ReasonToLive", isTestAFailure);
        step ("Check if test needs to fail, then fail", () -> {
            attachAsText("isTestAFailure",isTestAFailure+"");
            if (isTestAFailure < 1 ) {
                throw new RuntimeException("Reason to live not found");
            }
        });
    }
    @Test
    @Layer("web")
    @DisplayName("Assert generated value against 5 and throw runtime exception if less")
    @Description("Generated random int is tested against 3 and if less then runtime exception will be thrown")
    @Severity(SeverityLevel.MINOR)
    @Flaky
    public void flakyRandomizedTest() {
        parameter("ReasonToLive", isTestAFailure);
        step ("Check if test needs to fail, then fail", () -> {
            attachAsText("isTestAFailure",isTestAFailure+"");
        });
        step ("Check if test needs to fail, then fail", () -> {
            assert(isTestAFailure>5);
        });
    }
    @Test
    @Layer("web")
    @DisplayName("This test will always pass as we'll trick the assertion")
    @Description("Generated random int is tested against 3 and if less then runtime exception will be thrown")
    @Severity(SeverityLevel.MINOR)
    @Flaky
    public void goodTestWhichPasses() {
        parameter("ReasonToLive", isTestAFailure);
        step ("Attaching isTestAFailure as text to the test results", () -> {
            attachAsText("isTestAFailure",isTestAFailure+"");

        });
        step ("Assertion will always pass", () -> {
            assert(isTestAFailure<11);
        });
    }

}