
package com.parabank.automation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class BillPayPage {

    private final Page page;

    public BillPayPage(Page page) {
        this.page = page;
    }

    public void open() {
        page.getByRole(
                com.microsoft.playwright.options.AriaRole.LINK,
                new Page.GetByRoleOptions()
                        .setName("Bill Pay")
        ).click();

        page.locator("input[name='payee.name']").waitFor();
    }

    public void enterPayeeDetails(
            String name,
            String address,
            String city,
            String state,
            String zipCode,
            String phone
    ) {
        page.locator("input[name='payee.name']").fill(name);
        page.locator("input[name='payee.address.street']").fill(address);
        page.locator("input[name='payee.address.city']").fill(city);
        page.locator("input[name='payee.address.state']").fill(state);
        page.locator("input[name='payee.address.zipCode']").fill(zipCode);
        page.locator("input[name='payee.phoneNumber']").fill(phone);
    }

    public void enterAccountDetails(
            String accountNumber,
            String amount
    ) {
        page.locator("input[name='payee.accountNumber']")
                .fill(accountNumber);

        page.locator("input[name='verifyAccount']")
                .fill(accountNumber);

        page.locator("input[name='amount']")
                .fill(amount);
    }

    public void selectFromAccount(String accountId) {
        page.locator("select[name='fromAccountId']")
                .selectOption(accountId);
    }

    public void sendPayment() {
        page.getByRole(
                com.microsoft.playwright.options.AriaRole.BUTTON,
                new Page.GetByRoleOptions()
                        .setName("Send Payment")
        ).click();
    }

    public Locator getConfirmation() {
        return page.getByText("Bill Payment Complete");
    }

}
