package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class DebitCardPage {
    @FindBy(css="[class='heading heading_size_l heading_theme_alfa-on-white']")
    private SelenideElement heading;
    @FindBy(css="[class='button button_size_m button_theme_alfa-on-white']")
    private SelenideElement purchaseButton;
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
    @FindBy(css=".notification_status_ok .notification__content")
    private SelenideElement successfulMessage;
    @FindBy(css="div[class='notification notification_visible notification_status_error notification_has-closer notification_stick-to_right notification_theme_alfa-on-white']")
    private SelenideElement errorMessage;

    public DebitCardPage validCardData (DataHelper.CardInfo info){
        purchaseButton.click();
        cardNumberField.setValue(info.getCardNumber());
        monthNumberField.setValue(info.getMonth());
        yearNumberField.setValue(info.getYear());
        nameField.setValue(info.getName());
        cvcField.setValue(info.getCvc());
        continueButton.click();
        return page(DebitCardPage.class);
    }

    public DebitCardPage invalidCardData (DataHelper.CardInfo info){
        purchaseButton.click();
        cardNumberField.setValue(info.getCardNumber());
        monthNumberField.setValue(info.getMonth());
        yearNumberField.setValue(info.getYear());
        nameField.setValue(info.getName());
        cvcField.setValue(info.getCvc());
        continueButton.click();
        errorMessage.shouldBe(visible);
        return page(DebitCardPage.class);
    }
}


