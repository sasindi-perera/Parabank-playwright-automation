
package com.parabank.automation.pages;

import com.microsoft.playwright.Page;

public class LoginPage {

    private final Page page;

    // Locators
    private final String usernameField = "input[name='username']";
    private final String passwordField = "input[name='password']";
    private final String loginButton = "input[value='Log In']";

    // Constructor
    public LoginPage(Page page) {
        this.page = page;
    }

    // Open the ParaBank application
    public void open() {
        page.navigate(
                "https://parabank.parasoft.com/parabank/index.htm",
                new Page.NavigateOptions()
                        .setWaitUntil(
                                com.microsoft.playwright.options.WaitUntilState.DOMCONTENTLOADED
                        )
                        .setTimeout(60000)
        );
    }

    // Enter username and password, then log in
    public void login(String username, String password) {
        page.locator(usernameField).fill(username);
        page.locator(passwordField).fill(password);
        page.locator(loginButton).click();
    }

    // Get the welcome message
    public String getWelcomeMessage() {
        return page.getByText("Welcome John Smith").innerText();
    }

    // Get the login error message
    public String getErrorMessage() {
        return page.locator(
                "div[id='rightPanel'] p.error"
        ).innerText();
    }
}
