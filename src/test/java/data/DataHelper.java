package data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    private static Faker faker = new Faker(new Locale("en"));
    private static String cardNumber = "4444 4444 4444 4441";
    private static String blockedCardNumber = "4444 4444 4444 4442";

    private static String month = generateDate("MM", 0, 0);
    private static String year = generateDate("yy", 3, 0);

    private static String invalidYear = generateDate("yy", 0, 1);
    private static String cvc = faker.numerify("###");
    private static String name = faker.name().name().toUpperCase();

    private DataHelper() {
    }

    static String generateDate(String pattern, int years, int minusYears) {
        return LocalDate.now().plusYears(years).minusYears(minusYears).format(DateTimeFormatter.ofPattern(pattern));
    }


    public static CardInfo getValidCardData() {
        return new CardInfo(cardNumber, month, year, cvc, name);
    }

    public static CardInfo getBlockedCardData() {
        return new CardInfo(blockedCardNumber, month, year, cvc, name);
    }

    public static CardInfo getCardDataWithOneFigureInCardNumber() {
        return new CardInfo("3", month, year, cvc, name);
    }

    public static CardInfo getCardDataWithFifteenFiguresInCardNumber() {
        return new CardInfo("4444 4444 4444 444", month, year, cvc, name);
    }

    public static CardInfo getCardDataWithSeventeenFiguresInCardNumber() {
        return new CardInfo("4444 4444 4444 4441 5", month, year, cvc, name);
    }

    public static CardInfo getCardDataWithOneFigureInMonth() {
        return new CardInfo(cardNumber, "1", year, cvc, name);
    }

    public static CardInfo getCardDataWithThreeFiguresInMonth() {
        return new CardInfo(cardNumber, "015", year, cvc, name);
    }

    public static CardInfo getCardDataWithInvalidMonth() {
        return new CardInfo(cardNumber, "15", year, cvc, name);
    }

    public static CardInfo getCardDataWithOneFigureInYear() {
        return new CardInfo(cardNumber, month, "1", cvc, name);
    }

    public static CardInfo getCardDataWithThreeFigureInYear() {
        return new CardInfo(cardNumber, month, "234", cvc, name);
    }

    public static CardInfo getCardDataWithInvalidYear() {
        return new CardInfo(cardNumber, month, invalidYear, cvc, name);
    }

    public static CardInfo getCardDataWithOneFigureInCVC() {
        return new CardInfo(cardNumber, month, invalidYear, "1", name);
    }

    public static CardInfo getCardDataWithTwoFiguresInCVC() {
        return new CardInfo(cardNumber, month, invalidYear, "12", name);
    }

    public static CardInfo getCardDataWithFourFiguresInCVC() {
        return new CardInfo(cardNumber, month, invalidYear, "1234", name);
    }

    public static CardInfo getCardDataWithRussianName() {
        Faker faker = new Faker(new Locale("ru"));
        return new CardInfo(cardNumber, month, invalidYear, cvc, faker.name().fullName());
    }

    public static CardInfo getCardDataWithLowerCaseName() {
        return new CardInfo(cardNumber, month, invalidYear, cvc, faker.name().fullName().toLowerCase());
    }


    @Value
    public static class CardInfo {
        String cardNumber;
        String month;
        String year;
        String cvc;
        String name;
    }

    @Value
    public static class Status {
        String status;
    }

    @Value
    public static class PaymentID {
        String paymentID;
    }

}
