package page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ApplicationFormPage {
    @FindBy(css="[class='heading heading_size_l heading_theme_alfa-on-white']")
    private SelenideElement heading;
    @FindBy(css="[class='button button_size_m button_theme_alfa-on-white']")
    private SelenideElement purchaseButton;
    @FindBy(css="[class='button button_size_m button_theme_alfa-on-white']")
    private SelenideElement purchaseOnCreditButton;
    @FindBy(css="[class='input__control'][placeholder='0000 0000 0000 0000']")
    private SelenideElement cardNumberField;
    @FindBy(css="[class='input__control'][placeholder='08']")
    private SelenideElement monthNumberField;
    @FindBy(css="[class='input__control'][placeholder='22'] ")
    private SelenideElement yearNumberField;
    @FindBy(css="[class='input input_type_text input_view_default input_size_m input_width_available input_has-label input_theme_alfa-on-white']")
    private SelenideElement nameField;
    @FindBy(css="[class='input__control'][placeholder='999']")
    private SelenideElement cvcField;


    public ApplicationFormPage(){
        heading.shouldBe(visible);
    }
}