import java.sql.*;
import java.util.*;

public class Authentication {
    public static boolean authenticate(String username, String password) throws SQLException{
        Statement statement = null;
        statement = JDBCConnectivity.establishConnection();
        String sql = String.format("SELECT * FROM admins WHERE username = '%s'", username);
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            if(resultSet.getString("passwd").equals(password)) {
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }
    public static boolean authenticate(int id, String password) throws SQLException{
        Statement statement = null;
        statement = JDBCConnectivity.establishConnection();
        String sql = String.format("SELECT * FROM admins WHERE id = %d", id);
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            if(resultSet.getString("passwd").equals(password)) {
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

}
