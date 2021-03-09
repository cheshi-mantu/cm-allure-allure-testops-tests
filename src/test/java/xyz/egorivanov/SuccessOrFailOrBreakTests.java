package xyz.egorivanov;

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
    @DisplayName("This test throws AssertionFailedError sometimes")
    @Description("If test is to fail then throw an exception, otherwise don't")
    @Severity(SeverityLevel.TRIVIAL)
    public void throwingAssertionFailedError() {
        parameter("ReasonToLive", isTestAFailure);
        step ("Check if test needs to fail, then fail", () -> {
            attachAsText("isTestAFailure",isTestAFailure+"");
            if (isTestAFailure < 3 ) {
                throw new AssertionFailedError("Assertion failed error, expected one but got two!", 1, isTestAFailure);
            }
        });
    }

    @Test
    @DisplayName("Assert generated value against 3 and throw exception")
    @Description("Generated random int is tested against 3 and if less then test will fail")
    @Severity(SeverityLevel.NORMAL)
    public void assertEqualsWithFailTest() {
        parameter("ReasonToLive", isTestAFailure);
        step ("Check if test needs to fail, then fail", () -> {
            attachAsText("isTestAFailure",isTestAFailure+"");
            if (isTestAFailure < 3 ) {
                throw new AssertionFailedError("Assertion failed error, expected one but got the other!", ">3", isTestAFailure);
            }
        });
    }
    @Test
    @DisplayName("Assert generated value against 3 and throw runtime exception if less")
    @Description("Generated random int is tested against 3 and if less then runtime exception will be thrown")
    @Severity(SeverityLevel.CRITICAL)
    public void compareGeneratedWithFixedAndThrowRuntimeExceptionTest() {
        parameter("ReasonToLive", isTestAFailure);
        step ("Check if test needs to fail, then fail", () -> {
            attachAsText("isTestAFailure",isTestAFailure+"");
            if (isTestAFailure < 8 ) {
                throw new RuntimeException("Reason to live not found");
            }
        });
    }
    @Test
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

}