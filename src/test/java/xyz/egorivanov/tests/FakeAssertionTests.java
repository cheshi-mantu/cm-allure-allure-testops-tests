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

@Epic("Allure TestOps demo tests")
@Feature("UI orders processing and billing")
@Tag("web_tests")
@Owner("egorivanov")
@Lead("Trr Pumba")
class FakeAssertionTests extends TestBase {

    @Test
    @Layer("web")
    @Microservice("billing")
    @DisplayName("Assertion")
    @Story("If test is to fail then throw an exception, otherwise don't")
    @TmsLink("AES-804")
    @Severity(SeverityLevel.TRIVIAL)
    public void assertRandpomAgainstFive() {
        parameter("FailThreshold", isTestAFailure);
        step ("Check if test needs to fail, then fail", () -> {
            attachAsText("isTestAFailure",isTestAFailure+"");
            assert(5 > isTestAFailure);
        });
    }



}