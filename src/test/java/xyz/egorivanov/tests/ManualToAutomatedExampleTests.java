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
@Feature("Automation of existing manual tests")
@Tag("tutorial")
@Owner("egorivanov")
class ManualToAutomatedExampleTests extends TestBase {

    @Test
    public void automationOfManualTestCase() {
    }

}