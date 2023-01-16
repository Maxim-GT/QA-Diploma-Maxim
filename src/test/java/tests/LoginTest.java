package tests;

import SQLData.SQLHelper;
import data.DataHelper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.LoginPage;

import static SQLData.SQLHelper.cleanDatabase;
import static com.codeborne.selenide.Selenide.open;

public class LoginTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:8080/");
    }


    @AfterAll
    static void tearDown() {
        cleanDatabase();
    }

    @Test
    void shouldSuccessfullyLogin() {
        var loginPage = open("http://localhost:8080", LoginPage.class);
        var authInfo = DataHelper.getAuthInfoWithTestData();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verifyVerificationPageVisibility();
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode.getCode());
    }

    @Test
    void shouldWarnIfInvalidUser() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.generateRandomUser();
        loginPage.validLogin(authInfo);
        loginPage.errorNotificationVisibility();
    }

    @Test
    void shouldBlockSystem() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfoForSystemBlock = DataHelper.getInfoForSystemBlock();
        var status = SQLHelper.getUserStatus();
        loginPage.invalidLogin(authInfoForSystemBlock);
        Assertions.assertEquals(new DataHelper.Status("blocked"), status);
    }
}
