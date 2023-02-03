package SQLData;

import data.DataHelper;
import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.*;

public class SQLHelper {
    private static QueryRunner runner = new QueryRunner();

    private SQLHelper() {
    }

    @SneakyThrows
    private static Connection getConn() {
        var URL = System.getProperty("db.url");
        var login = System.getProperty("login");
        var password = System.getProperty("password");
        var connection = DriverManager.getConnection(URL, login, password);
        return connection;
    }

    @SneakyThrows
    public static DataHelper.PaymentID getPaymentID() {
        var codeSQL = "SELECT payment_id FROM order_entity order by created desc limit 1";
        var conn = getConn();
        var paymentID = runner.query(conn, codeSQL, new ScalarHandler<String>());
        return new DataHelper.PaymentID(paymentID);
    }

    @SneakyThrows
    public static DataHelper.Status getDebitCardStatus(String paymentID) {
        String status = null;
        var codeSQL = "SELECT status FROM payment_entity where transaction_id = ?;";
        var conn = getConn();
        var selectPreparedStatement = conn.prepareStatement(codeSQL);
        selectPreparedStatement.setString(1, paymentID);
        var rs = selectPreparedStatement.executeQuery();
        if (rs.next()) {
            status = rs.getString("status");
        }
        return new DataHelper.Status(status);
    }

    @SneakyThrows
    public static DataHelper.CreditID getCreditID() {
        var codeSQL = "SELECT payment_id FROM order_entity order by created desc limit 1";
        var conn = getConn();
        var creditID = runner.query(conn, codeSQL, new ScalarHandler<String>());
        return new DataHelper.CreditID(creditID);
    }

    @SneakyThrows
    public static DataHelper.Status getCreditCardStatus(String creditID) {
        String status = null;
        var codeSQL = "SELECT status FROM credit_request_entity where bank_id = ?;";
        var conn = getConn();
        var selectPreparedStatement = conn.prepareStatement(codeSQL);
        selectPreparedStatement.setString(1, creditID);
        var rs = selectPreparedStatement.executeQuery();
        if (rs.next()) {
            status = rs.getString("status");
        }
        return new DataHelper.Status(status);
    }

    @SneakyThrows
    public static void cleanDatabase() {
        var connection = getConn();
        runner.execute(connection, "DELETE FROM credit_request_entity");
        runner.execute(connection, "DELETE FROM order_entity");
        runner.execute(connection, "DELETE FROM payment_entity");
    }
}
