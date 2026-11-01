
package com.parabank.automation.tests;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;
import com.parabank.automation.pages.LoginPage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;



public class LoginTest extends BaseTest {

    @Test
    public void openParaBank() {

        LoginPage loginPage = new LoginPage(page);

        loginPage.open();

        assertTrue(
                page.title().contains("ParaBank"),
                "ParaBank page did not open correctly"
        );

        System.out.println("ParaBank opened successfully.");
    }

    @Test
    public void validLogin() {

        LoginPage loginPage = new LoginPage(page);

        loginPage.open();
        loginPage.login("john", "demo");

        Locator welcomeMessage =
                page.getByText("Welcome John Smith");

        welcomeMessage.waitFor();

        assertTrue(
                welcomeMessage.isVisible(),
                "Login was not successful"
        );

        System.out.println("Valid login test passed.");
    }

    @Test
    public void invalidLogin() {

        LoginPage loginPage = new LoginPage(page);

        loginPage.open();
        loginPage.login("wronguser", "wrongpassword");

        Locator errorMessage = page.locator(
                "div[id='rightPanel'] p.error"
        );

        errorMessage.waitFor();

        assertTrue(
                loginPage.getErrorMessage().contains(
                        "The username and password could not be verified"
                ),
                "Expected login error was not displayed"
        );

        System.out.println("Invalid login test passed.");
    }

    @Test
    public void verifyAccountsOverview() {

        LoginPage loginPage = new LoginPage(page);

        loginPage.open();
        loginPage.login("john", "demo");

        Locator heading = page.getByRole(
                AriaRole.HEADING,
                new com.microsoft.playwright.Page.GetByRoleOptions()
                        .setName("Accounts Overview")
        );

        heading.waitFor();

        assertTrue(
                heading.isVisible(),
                "Accounts Overview heading was not displayed"
        );

        assertTrue(
                page.locator("table").isVisible(),
                "Account table was not displayed"
        );

        System.out.println("Accounts Overview test passed.");
    }
}
