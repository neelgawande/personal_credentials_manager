import java.sql.*;

public class Admin  {
    private int userId;
    private String userName;
    private String passwd;

    public int getUserId() {
        return userId;
    }
    public String getPasswd() {
        return passwd;
    }

    public String getUserName() {
        return userName;
    }

    // Constructor
    public Admin(int id, String passwd) throws SQLException {
        Statement statement = null;
        statement = JDBCConnectivity.establishConnection();
        String sql = String.format("SELECT * FROM admins WHERE id=%d", id);
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()) {
            this.userId = resultSet.getInt("id");
            this.userName = resultSet.getString("username");
            this.passwd = resultSet.getString("passwd");
        }
    }
    public Admin(String userName, String passwd) throws SQLException {
        Statement statement = null;
        statement = JDBCConnectivity.establishConnection();
        String sql = String.format("SELECT * FROM admins WHERE username='%s'", userName);
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()) {
            this.userId = resultSet.getInt("id");
            this.userName = resultSet.getString("username");
            this.passwd = resultSet.getString("passwd");
        }
    }

}
