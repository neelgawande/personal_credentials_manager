import java.sql.*;

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
    public static boolean authenticateUser(int id, String password) throws SQLException{
        Statement statement = null;
        statement = JDBCConnectivity.establishConnection();
        String sql = String.format("SELECT * FROM users WHERE id = %d", id);
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

    public static boolean verifyAdmin(String username) throws SQLException{
        Statement statement = null;
        statement = JDBCConnectivity.establishConnection();
        String sql = String.format("SELECT * FROM admins WHERE username = '%s'", username);
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            if(resultSet.getString("username").equals(username)) {
                return true;
            }
        }
        return false;
    }
    public static boolean verifyAdminPassword(String username, String password) throws SQLException{
        Statement statement = null;
        statement = JDBCConnectivity.establishConnection();
        String sql = String.format("SELECT * FROM admins WHERE username = '%s'", username);
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            if(resultSet.getString("passwd").equals(password)) {
                return true;
            }
        }
        return false;
    }
    public static boolean verifyUser(int id) throws SQLException{
        Statement statement = null;
        statement = JDBCConnectivity.establishConnection();
        String sql = String.format("SELECT * FROM users WHERE id = %d", id);
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            if(resultSet.getInt("id") == id ) {
                return true;
            }
        }
        return false;
    }
    public static boolean verifyUserPassword(int id, String password) throws SQLException{
        Statement statement = null;
        statement = JDBCConnectivity.establishConnection();
        String sql = String.format("SELECT * FROM users WHERE id = %d", id);
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            if(resultSet.getString("passwd").equals(password)) {
                return true;
            }
        }
        return false;
    }

}
