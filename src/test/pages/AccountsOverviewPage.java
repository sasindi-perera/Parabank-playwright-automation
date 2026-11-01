
package com.parabank.automation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class AccountsOverviewPage {

    private final Page page;

    public AccountsOverviewPage(Page page) {
        this.page = page;
    }

    public Locator getHeading() {
        return page.getByRole(
                AriaRole.HEADING,
                new Page.GetByRoleOptions()
                        .setName("Accounts Overview")
        );
    }

    public Locator getAccountTable() {
        return page.locator("table");
    }

    public void waitForHeading() {
        getHeading().waitFor();
    }
}
