package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLServerDataProvider {
    private Connection connection;

    public void open() {
        String strServer = "Chien\\EXPRESS";
        String strDatabase = "QLsach";
        String strUserName = "sa";
        String strPass = "sa";
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

        try {
            Class.forName(driver);
            String connectionURL = "jdbc:sqlserver://" + strServer + ":1433;databaseName=" + strDatabase + ";user="
                    + strUserName + ";password=" + strPass;
            connection = DriverManager.getConnection(connectionURL);
            System.out.println("Kết nối thành công!");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SQLServerDataProvider.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Kết nối thất bại!");
        }
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Đã đóng kết nối!");
            } catch (SQLException ex) {
                Logger.getLogger(SQLServerDataProvider.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Lỗi khi đóng kết nối!");
            }
        }
    }

    public ResultSet executeQuery(String sql) {
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(SQLServerDataProvider.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Lỗi khi thực thi truy vấn!");
        }
        return resultSet;
    }

    public int executeUpdate(String sql) {
        int n = -1;
        try {
            Statement statement = connection.createStatement();
            n = statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(SQLServerDataProvider.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Lỗi khi thực thi cập nhật!");
        }
        return n;
    }

    public Connection getConnection() {
        return this.connection;
    }
}
