package xyz.egorivanov.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
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
    @Layer("core")
    @Microservice("ServiceDesk")
    @DisplayName("Assertion of random value against 5")
    @Story("If test is to fail then throw an exception, otherwise don't")
//    @TmsLink("AES-804")
    @Severity(SeverityLevel.TRIVIAL)
    public void assertRandpomAgainstFive() {
        parameter("RandomValue", isTestAFailure);
        parameter("Fixed to compare with", 5);
        step ("Check if test needs to fail, then fail", () -> {
            attachAsText("Random value",isTestAFailure+"");
            assert(5 < isTestAFailure);
        });
    }
    @Test
    @Tag("test_tests")
    @AllureId("11211")
    @DisplayName("This is to be imported to IntelliJ IDEA")
    @Layer("web")
    @Microservice("ServiceDesk")
    @Story("Allure and IDEA")
    @Severity(SeverityLevel.CRITICAL)
    public void importFromAlluretestOps() {
        step("Open IntelliJ IDEA");
        step("Create a new method");
        step("Right click on method's name and import");
        step("Provide this case's ID (11211)");
        step("Clean the build folder");
        step("Run the test");
        step("Upload the results");
    }
}