package xyz.egorivanov.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


@Epic("Allure TestOps demo tests")
@Feature("Automation of existing manual tests")
@Tag("tutorial")
@Owner("egorivanov")
class ManualToAutomatedExampleTests extends TestBase {

    @Test
    public void automationOfManualTestCase() {
    }

}