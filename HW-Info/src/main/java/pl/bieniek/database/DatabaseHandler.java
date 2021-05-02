package pl.bieniek.database;

import pl.bieniek.Admin;

import java.sql.*;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPassword);

        return dbConnection;
    }

    public void SendInfo(){
    }

    public ResultSet getAdmin(Admin admin) {
        ResultSet resultSet = null;

        if(!admin.getUsername().equals("") || !admin.getPassword().equals("")) {
            String query = "SELECT * FROM " + Const.ADMIN_TABLE + " WHERE " + Const.USERNAME
                    + "=?" + " AND " + Const.PASSWORD + "=?";

            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
                preparedStatement.setString( 1, admin.getUsername());
                preparedStatement.setString( 2, admin.getPassword());

                resultSet = preparedStatement.executeQuery();
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

        } else {
            System.out.println("Please enter username and password");
        }
        return resultSet;
    }
}