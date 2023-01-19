package tests;

//import SQLData.SQLHelper;
import SQLData.SQLHelper;
import data.DataHelper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import page.CreditCardPage;
import page.DebitCardPage;

//import static SQLData.SQLHelper.cleanDatabase;
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
    void shouldSuccessfullyFillDebitCardForm() {
        var applicationFormPage = open("http://localhost:8080", DebitCardPage.class);
        var info = DataHelper.getValidCardData();
        var paymentID = SQLHelper.getPaymentID();
        var status = SQLHelper.getDebitCardStatus(String.valueOf(paymentID));
        applicationFormPage.validCardData(info);
        Assertions.assertEquals(new DataHelper.Status("APPROVED"), status);
    }

//    @Test
//    void shouldSuccessfullyFillCreditCardForm() {
//        var applicationFormPage = open("http://localhost:8080", CreditCardPage.class);
//        var info = DataHelper.getValidCardData();
//        applicationFormPage.validCardData(info);
//    }

//    @Test
//    void shouldDeclineSendingDebitCardForm() {
//        var applicationFormPage = open("http://localhost:8080", DebitCardPage.class);
//        var info = DataHelper.getBlockedCardData();
//        applicationFormPage.invalidCardData(info);
//    }

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
