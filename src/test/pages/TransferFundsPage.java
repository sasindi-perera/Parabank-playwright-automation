
package com.parabank.automation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class TransferFundsPage {

    private final Page page;

    public TransferFundsPage(Page page) {
        this.page = page;
    }

    public void open() {
        page.getByRole(
                com.microsoft.playwright.options.AriaRole.LINK,
                new Page.GetByRoleOptions()
                        .setName("Transfer Funds")
        ).click();

        page.locator("#amount").waitFor();
    }

    public void enterAmount(String amount) {
        page.locator("#amount").fill(amount);
    }

    public void selectFromAccount(String accountId) {
        page.locator("#fromAccountId")
                .selectOption(accountId);
    }

    public void selectToAccount(String accountId) {
        page.locator("#toAccountId")
                .selectOption(accountId);
    }

    public void clickTransfer() {
        page.locator("input[value='Transfer']").click();
    }

    public Locator getConfirmation() {
        return page.getByText("Transfer Complete!");
    }
}
