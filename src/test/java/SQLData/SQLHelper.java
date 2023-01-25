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
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
    }

     @SneakyThrows
    public static DataHelper.PaymentID getPaymentID() {
        var codeSQL = "SELECT payment_id FROM order_entity order by created desc limit 1";
        try (var conn = getConn()) {
            var paymentID = runner.query(conn, codeSQL, new ScalarHandler<String>());
            return new DataHelper.PaymentID(paymentID);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @SneakyThrows
    public static DataHelper.Status getDebitCardStatus(String paymentID) {
        String status = null;
        String codeSQL = "SELECT status FROM payment_entity where transaction_id = ?;";
        try (var conn = getConn()) {
            PreparedStatement selectPreparedStatement = conn.prepareStatement(codeSQL);
            selectPreparedStatement.setString( 1, paymentID);
            try (var rs = selectPreparedStatement.executeQuery()) {
                if (rs.next()) {
                    status = rs.getString("status");
                }
            }
        }
        return new DataHelper.Status(status);
    }

    public static DataHelper.CreditID getCreditID() {
        var codeSQL = "SELECT payment_id FROM order_entity order by created desc limit 1";
        try (var conn = getConn()) {
            var result = runner.query(conn, codeSQL, new ScalarHandler<String>());
            return new DataHelper.CreditID(result);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static DataHelper.Status getCreditCardStatus (String creditID) {
        String status = null;
        String codeSQL = "SELECT status FROM credit_request_entity where bank_id = ?;";
        try (var conn = getConn()) {
            PreparedStatement selectPreparedStatement = conn.prepareStatement(codeSQL);
            selectPreparedStatement.setString( 1, creditID);
            try (var rs = selectPreparedStatement.executeQuery()) {
                if (rs.next()) {
                    status = rs.getString("status");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
