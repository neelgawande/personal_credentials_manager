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

    // This constructor will be called after creation of a *NEW* admin.
    public Admin(int id, String userName, String passwd){
        this.userId = id;
        this.userName = userName;
        this.passwd = passwd;
    }

    // add a new admin

    public static Admin createNewAdmin(int id, String userName, String passwd) throws SQLException {
        Statement statement = null;
        statement = JDBCConnectivity.establishConnection();
        String sql = String.format("INSERT INTO admins values (%d, '%s', '%s');", id, userName, passwd);
        int resultSet = statement.executeUpdate(sql);
        if(resultSet > 0) {
            return new Admin(id, userName, passwd);
        }
        return null;
    }

}
