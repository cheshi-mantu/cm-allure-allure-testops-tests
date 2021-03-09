package xyz.egorivanov.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import xyz.egorivanov.examples.Layer;
import xyz.egorivanov.examples.Microservice;

import static helpers.AttachmentsHelper.attachAsText;
import static io.qameta.allure.Allure.parameter;
import static io.qameta.allure.Allure.step;

@Epic("Allure TestOps demo tests")
@Feature("Elements")
@Tag("various_tests")
@Owner("egorivanov")
class SuccessOrFailOrBreakAPITests extends TestBase {

    @Test
    @Layer("API")
    @Microservice("billing")
    @DisplayName("Fake API test with possible assertion error")
    @Description("We get isTestAFailure from @BeforeEach method and throw assertion erorr if less than 1")
    @Severity(SeverityLevel.TRIVIAL)
    public void throwingAssertionFailedError() {
        parameter("ReasonToLive", isTestAFailure);
        step ("Storing the text attachment", () -> {
            attachAsText("isTestAFailure",isTestAFailure+"");
            if (isTestAFailure < 1 ) {
                throw new AssertionFailedError("Assertion failed error, expected one but got two!", 1, isTestAFailure);
            }
        });
    }

    @Test
    @Layer("API")
    @Microservice("store_order")
    @DisplayName("Assert generated value against 1 and throw exception if less")
    @Description("Get random value from @BeforeEach and then compare with 1, if less then throw assertion error")
    @Severity(SeverityLevel.NORMAL)
    public void assertEqualsWithFailTest() {
        parameter("ReasonToLive", isTestAFailure);
        step ("Storing the text attachment", () -> {
            attachAsText("isTestAFailure",isTestAFailure+"");
            if (isTestAFailure < 1 ) {
                throw new AssertionFailedError("Assertion failed error, expected one but got the other!", ">3", isTestAFailure);
            }
        });
    }
    @Test
    @Layer("API")
    @Microservice("store_order")
    @DisplayName("Assert generated value against 1 and throw runtime exception if less")
    @Description("Get random value from @BeforeEach and then compare with 1, if less then throw runtime exception error")
    @Severity(SeverityLevel.CRITICAL)
    public void compareGeneratedWithFixedAndThrowRuntimeExceptionTest() {
        parameter("ReasonToLive", isTestAFailure);
        step ("Storing the text attachement", () -> {
            attachAsText("isTestAFailure",isTestAFailure+"");
            if (isTestAFailure < 1 ) {
                throw new RuntimeException("Reason to live not found");
            }
        });
    }
    @Test
    @Layer("API")
    @Microservice("process_order")
    @DisplayName("Assert generated value against 1 and throw runtime exception if less")
    @Description("Generated random int is tested against 5, this test will be a flaky one")
    @Severity(SeverityLevel.MINOR)
    @Flaky
    public void flakyRandomizedTest() {
        parameter("ReasonToLive", isTestAFailure);
        step ("Storing the text attachment", () -> {
            attachAsText("isTestAFailure",isTestAFailure+"");
        });
        step ("Check if test needs to fail, then fail", () -> {
            assert(isTestAFailure>5);
        });
    }
    @Test
    @Layer("API")
    @Microservice("process_order")
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