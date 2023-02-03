package tests;

//import SQLData.SQLHelper;

import SQLData.SQLHelper;
import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import page.DebitCardPage;
import static com.codeborne.selenide.Selenide.open;

public class PurchaseOnDebitCardTests {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

//    @AfterAll
//    static void tearDown() {
//        cleanDatabase();
//    }

    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Should approve debit card payment")
    @Test
    void shouldApproveDebitCardPayment() {
        var applicationFormPage = open("http://localhost:8080", DebitCardPage.class);
        var info = DataHelper.getValidCardData();
        var paymentID = SQLHelper.getPaymentID();
        var status = SQLHelper.getDebitCardStatus(paymentID.getPaymentID());
        applicationFormPage.cardData(info);
        applicationFormPage.checkIfCardIsSuccessful();
        Assertions.assertEquals(new DataHelper.Status("APPROVED"), status);
    }

    //баг в появлении уведомления об успешной оплате
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Should block debit card payment")
    @Test
    void shouldBlockDebitCardPayment() {
        var applicationFormPage = open("http://localhost:8080", DebitCardPage.class);
        var info = DataHelper.getBlockedCardData();
        var paymentID = SQLHelper.getPaymentID();
        var status = SQLHelper.getDebitCardStatus(paymentID.getPaymentID());
        applicationFormPage.cardData(info);
        applicationFormPage.checkIfCardIsInvalid();
        Assertions.assertEquals(new DataHelper.Status("DECLINED"), status);
    }

    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Should warn if fields are empty")
    @Test
    void shouldWarnIfFieldsAreEmpty() {
        var applicationFormPage = open("http://localhost:8080", DebitCardPage.class);
        applicationFormPage.checkIfFieldsAreEmpty();
        applicationFormPage.warnIfDataIsInvalid();
    }
    @Test
    void shouldWarnIfOneFigureInCardNumber() {
        var applicationFormPage = open("http://localhost:8080", DebitCardPage.class);
        var info = DataHelper.getCardDataWithOneFigureInCardNumber();
        applicationFormPage.cardData(info);
        applicationFormPage.warnIfDataIsInvalid();
    }

    @Test
    void shouldWarnIfFifteenFiguresInCardNumber() {
        var applicationFormPage = open("http://localhost:8080", DebitCardPage.class);
        var info = DataHelper.getCardDataWithFifteenFiguresInCardNumber();
        applicationFormPage.cardData(info);
        applicationFormPage.warnIfDataIsInvalid();
    }

    @Test
    void shouldWarnIfOneFiguresInMonth() {
        var applicationFormPage = open("http://localhost:8080", DebitCardPage.class);
        var info = DataHelper.getCardDataWithOneFigureInMonth();
        applicationFormPage.cardData(info);
        applicationFormPage.warnIfDataIsInvalid();
    }

    @Test
    void shouldWarnIfThreeFiguresInMonth() {
        var applicationFormPage = open("http://localhost:8080", DebitCardPage.class);
        var info = DataHelper.getCardDataWithOneFigureInMonth();
        applicationFormPage.cardData(info);
        applicationFormPage.warnIfDataIsInvalid();
    }

    @Test
    void shouldWarnIfInvalidNumberInMonth() {
        var applicationFormPage = open("http://localhost:8080", DebitCardPage.class);
        var info = DataHelper.getCardDataWithInvalidMonth();
        applicationFormPage.cardData(info);
        applicationFormPage.warnIfDataIsInvalid();
    }

    @Test
    void shouldWarnIfOneFigureInYear() {
        var applicationFormPage = open("http://localhost:8080", DebitCardPage.class);
        var info = DataHelper.getCardDataWithOneFigureInYear();
        applicationFormPage.cardData(info);
        applicationFormPage.warnIfDataIsInvalid();
    }

    @Test
    void shouldWarnIfInvalidNumberInYear() {
        var applicationFormPage = open("http://localhost:8080", DebitCardPage.class);
        var info = DataHelper.getCardDataWithInvalidYear();
        applicationFormPage.cardData(info);
        applicationFormPage.warnIfDataIsInvalid();
    }

    @Test
    void shouldNotSendWithoutName() {
        var applicationFormPage = open("http://localhost:8080", DebitCardPage.class);
        var info = DataHelper.getCardDataWithoutName();
        applicationFormPage.cardData(info);
        applicationFormPage.warnIfDataIsInvalid();
    }
    // баг в возможности отправить форму с именем нижнего регистра
    @Test
    void shouldWarnIfNameInLowerCase() {
        var applicationFormPage = open("http://localhost:8080", DebitCardPage.class);
        var info = DataHelper.getCardDataWithLowerCaseName();
        applicationFormPage.cardData(info);
        applicationFormPage.warnIfDataIsInvalid();
    }

    // Баг в возможности отправить форму с именем на русском
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Should warn if invalid language on name is used")
    @Test
    void shouldWarnIfInvalidLanguageOfName() {
        var applicationFormPage = open("http://localhost:8080", DebitCardPage.class);
        var info = DataHelper.getCardDataWithRussianName();
        applicationFormPage.cardData(info);
        applicationFormPage.warnIfDataIsInvalid();
    }
    // баг в отправке несуществующего cvc
    @Test
    void shouldWarnIfNonexistentFiguresInCVC() {
        var applicationFormPage = open("http://localhost:8080", DebitCardPage.class);
        var info = DataHelper.getCardDataWithNonexistentCVC();
        applicationFormPage.cardData(info);
        applicationFormPage.warnIfDataIsInvalid();
    }

    @Test
    void shouldWarnIfOneFigureInCVC() {
        var applicationFormPage = open("http://localhost:8080", DebitCardPage.class);
        var info = DataHelper.getCardDataWithOneFigureInCVC();
        applicationFormPage.cardData(info);
        applicationFormPage.warnIfDataIsInvalid();
    }

    @Test
    void shouldWarnIfTwoFiguresInCVC() {
        var applicationFormPage = open("http://localhost:8080", DebitCardPage.class);
        var info = DataHelper.getCardDataWithTwoFiguresInCVC();
        applicationFormPage.cardData(info);
        applicationFormPage.warnIfDataIsInvalid();
    }

}
