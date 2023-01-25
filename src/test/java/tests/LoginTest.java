package tests;

//import SQLData.SQLHelper;

import SQLData.SQLHelper;
import data.DataHelper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import page.CreditCardPage;
import page.DebitCardPage;

import static SQLData.SQLHelper.cleanDatabase;
import static com.codeborne.selenide.Selenide.open;

public class LoginTest {

//    @BeforeEach
//    void setUp() {
//        open("http://localhost:8080/");
//    }


//    @AfterAll
//    static void tearDown() {
//        cleanDatabase();
//    }

    @Test
    void shouldApproveDebitCardPayment() {
        var applicationFormPage = open("http://localhost:8080", DebitCardPage.class);
        var info = DataHelper.getValidCardData();
        var paymentID = SQLHelper.getPaymentID();
        var status = SQLHelper.getDebitCardStatus(paymentID.getPaymentID());
        applicationFormPage.CardData(info);
        applicationFormPage.checkIfCardIsSuccessful();
        Assertions.assertEquals(new DataHelper.Status("APPROVED"), status);
    }

    //баг в появлении уведомления об успешной оплате
    @Test
    void shouldBlockDebitCardPayment() {
        var applicationFormPage = open("http://localhost:8080", DebitCardPage.class);
        var info = DataHelper.getBlockedCardData();
        var paymentID = SQLHelper.getPaymentID();
        var status = SQLHelper.getDebitCardStatus(paymentID.getPaymentID());
        applicationFormPage.CardData(info);
        applicationFormPage.checkIfCardIsInvalid();
        Assertions.assertEquals(new DataHelper.Status("DECLINED"), status);
    }
    @Test
    void shouldApproveCreditCardPayment() {
        var applicationFormPage = open("http://localhost:8080", CreditCardPage.class);
        var info = DataHelper.getValidCardData();
        var creditID = SQLHelper.getCreditID();
        var status = SQLHelper.getCreditCardStatus(creditID.getCreditID());
        applicationFormPage.CardData(info);
        applicationFormPage.checkIfCardIsValid();
        Assertions.assertEquals(new DataHelper.Status("APPROVED"), status);
    }

    @Test
    void shouldBlockCreditCardPayment() {
        var applicationFormPage = open("http://localhost:8080", DebitCardPage.class);
        var info = DataHelper.getBlockedCardData();
        var paymentID = SQLHelper.getCreditID();
        var status = SQLHelper.getCreditCardStatus(paymentID.getCreditID());
        applicationFormPage.CardData(info);
        applicationFormPage.checkIfCardIsInvalid();
        Assertions.assertEquals(new DataHelper.Status("DECLINED"), status);
    }
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
        applicationFormPage.CardData(info);
        applicationFormPage.warnIfDataIsInvalid();
    }

    @Test
    void shouldWarnIfFifteenFiguresInCardNumber() {
        var applicationFormPage = open("http://localhost:8080", DebitCardPage.class);
        var info = DataHelper.getCardDataWithFifteenFiguresInCardNumber();
        applicationFormPage.CardData(info);
        applicationFormPage.warnIfDataIsInvalid();
    }

    @Test
    void shouldWarnIfOneFiguresInMonth() {
        var applicationFormPage = open("http://localhost:8080", DebitCardPage.class);
        var info = DataHelper.getCardDataWithOneFigureInMonth();
        applicationFormPage.CardData(info);
        applicationFormPage.warnIfDataIsInvalid();
    }

    @Test
    void shouldWarnIfThreeFiguresInMonth() {
        var applicationFormPage = open("http://localhost:8080", DebitCardPage.class);
        var info = DataHelper.getCardDataWithOneFigureInMonth();
        applicationFormPage.CardData(info);
        applicationFormPage.warnIfDataIsInvalid();
    }

    @Test
    void shouldWarnIfInvalidNumberInMonth() {
        var applicationFormPage = open("http://localhost:8080", DebitCardPage.class);
        var info = DataHelper.getCardDataWithInvalidMonth();
        applicationFormPage.CardData(info);
        applicationFormPage.warnIfDataIsInvalid();
    }

    @Test
    void shouldWarnIfOneFigureInYear() {
        var applicationFormPage = open("http://localhost:8080", DebitCardPage.class);
        var info = DataHelper.getCardDataWithOneFigureInYear();
        applicationFormPage.CardData(info);
        applicationFormPage.warnIfDataIsInvalid();
    }

    @Test
    void shouldWarnIfInvalidNumberInYear() {
        var applicationFormPage = open("http://localhost:8080", DebitCardPage.class);
        var info = DataHelper.getCardDataWithInvalidYear();
        applicationFormPage.CardData(info);
        applicationFormPage.warnIfDataIsInvalid();
    }

    @Test
    void shouldNotSendWithoutName() {
        var applicationFormPage = open("http://localhost:8080", DebitCardPage.class);
        var info = DataHelper.getCardDataWithoutName();
        applicationFormPage.CardData(info);
        applicationFormPage.warnIfDataIsInvalid();
    }
    // баг в возможности отправить форму с именем нижнего регистра
    @Test
    void shouldWarnIfNameInLowerCase() {
        var applicationFormPage = open("http://localhost:8080", DebitCardPage.class);
        var info = DataHelper.getCardDataWithLowerCaseName();
        applicationFormPage.CardData(info);
        applicationFormPage.warnIfDataIsInvalid();
    }
    // Баг в возможности отправить форму с именем на русском
    @Test
    void shouldWarnIfInvalidLanguageOfName() {
        var applicationFormPage = open("http://localhost:8080", DebitCardPage.class);
        var info = DataHelper.getCardDataWithRussianName();
        applicationFormPage.CardData(info);
        applicationFormPage.warnIfDataIsInvalid();
    }
    // баг в отправке несуществующего cvc
    @Test
    void shouldWarnIfNonexistentFiguresInCVC() {
        var applicationFormPage = open("http://localhost:8080", DebitCardPage.class);
        var info = DataHelper.getCardDataWithNonexistentCVC();
        applicationFormPage.CardData(info);
        applicationFormPage.warnIfDataIsInvalid();
    }

    @Test
    void shouldWarnIfOneFigureInCVC() {
        var applicationFormPage = open("http://localhost:8080", DebitCardPage.class);
        var info = DataHelper.getCardDataWithOneFigureInCVC();
        applicationFormPage.CardData(info);
        applicationFormPage.warnIfDataIsInvalid();
    }

    @Test
    void shouldWarnIfTwoFiguresInCVC() {
        var applicationFormPage = open("http://localhost:8080", DebitCardPage.class);
        var info = DataHelper.getCardDataWithTwoFiguresInCVC();
        applicationFormPage.CardData(info);
        applicationFormPage.warnIfDataIsInvalid();
    }



//    @Test
//    void shouldWarnIfInvalidUser() {
//        var loginPage = open("http://localhost:9999", LoginPage.class);
//        var authInfo = DataHelper.generateRandomUser();
//        loginPage.validLogin(authInfo);
//        loginPage.errorNotificationVisibility();
//    }
//
//    @Test
//    void shouldBlockSystem() {
//        var loginPage = open("http://localhost:9999", LoginPage.class);
//        var authInfoForSystemBlock = DataHelper.getInfoForSystemBlock();
//        var status = SQLHelper.getUserStatus();
//        loginPage.invalidLogin(authInfoForSystemBlock);
//        Assertions.assertEquals(new DataHelper.Status("blocked"), status);
//    }
}
