package xyz.egorivanov.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;


@Epic("Allure TestOps demo tests")
@Feature("Automation of existing manual tests")
@Tag("tutorial")
@Owner("egorivanov")
class ManualToAutomatedExampleTests extends TestBase {

    @Test
    @AllureId("11361")
    @DisplayName("Automation of manual test cases - Allure TestOps to code")
    @Tag("smoke")
    @Feature("Manual tests automation in IntelliJ IDEA")
    @Epic("Tests automation")
    @Owner("egorivanov")
    public void automationOfManualTestCase() {
        step("Open webpage https://somehost.url", ()->{
            //open("https://somehost.url");
        });
        step("Click log-in button", ()->{
            //locate and click log-in button
        });
        step("Fill-in logon form", () -> {
            step("Fill username field with attached data");
        });
        step("Click log-in button");
        step("Check if log in is successful", () -> {
            step("Title should contain username");
            step("Users avatar should be present");
            step("Menu should contain additional items (see sceenshot)");
        });
    }

}