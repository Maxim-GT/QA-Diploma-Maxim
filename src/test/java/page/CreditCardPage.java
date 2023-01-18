package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class CreditCardPage {
    @FindBy(css="[class='button button_view_extra button_size_m button_theme_alfa-on-white']")
    private SelenideElement purchaseOnCreditButton;
    @FindBy(css="input[placeholder='0000 0000 0000 0000']")
    private SelenideElement cardNumberField;
    @FindBy(css="[class='input__control'][placeholder='08']")
    private SelenideElement monthNumberField;
    @FindBy(css="[class='input__control'][placeholder='22'] ")
    private SelenideElement yearNumberField;
    @FindBy(css="form div:nth-child(3) .input__control")
    private SelenideElement nameField;
    @FindBy(css="[class='input__control'][placeholder='999']")
    private SelenideElement cvcField;
    @FindBy(css="form div:nth-child(4) .button__content")
    private SelenideElement continueButton;
    @FindBy(css="div[class='notification notification_visible notification_status_ok notification_has-closer notification_stick-to_right notification_theme_alfa-on-white']")
    private SelenideElement successfulMessage;

    public CreditCardPage validCardData (DataHelper.CardInfo info){
        purchaseOnCreditButton.click();
        cardNumberField.setValue(info.getCardNumber());
        monthNumberField.setValue(info.getMonth());
        yearNumberField.setValue(info.getYear());
        nameField.setValue(info.getName());
        cvcField.setValue(info.getCvc());
        continueButton.click();
        successfulMessage.shouldBe(visible);
        return page(CreditCardPage.class);
    }
}
