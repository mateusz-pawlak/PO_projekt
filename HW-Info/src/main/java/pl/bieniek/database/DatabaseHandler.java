package pl.bieniek.database;

import pl.bieniek.Admin;
import pl.bieniek.Employee;

import java.sql.*;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPassword);

        return dbConnection;
    }

    public void sendInfo(){
    }

    public void getInfo(){
        String query = "SELECT * FROM " + Const.INFO_TABLE + " WHERE " + Const.INFO_NAME + "+?";
    }

    public void clearDatabase() throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM " + Const.INFO_TABLE;
        Statement st = getDbConnection().createStatement();
        st.executeQuery(query);
    }

    public void deleteUser(){
        String query = "DELETE FROM " + Const.INFO_TABLE + " WHERE " + Const.INFO_NAME + "+?";
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

    //Returns row with specified name from INFO_TABLE. Null if there isn't any.
    public ResultSet checkName (Employee employee){
        ResultSet resultSet = null;
        String query = "SELECT * FROM " + Const.INFO_TABLE + " WHERE " + Const.INFO_NAME + "=?";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setString( 1, employee.getName());
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return resultSet;
    }

    //Adds new record to table
    public void addNewInfo(Employee employee){
        try{
            Statement statement = getDbConnection().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            );
            ResultSet set = statement.getResultSet();
            set.moveToInsertRow();
            set.updateString(Const.INFO_ID, null);
            set.updateString(Const.INFO_NAME, employee.getName());
            set.updateString(Const.OS, employee.getOs());
            set.updateString(Const.CPU, employee.getCpu());
            set.updateString(Const.GPU, employee.getGpu());
            set.updateString(Const.RAM, employee.getRam());
            set.insertRow();
            set.moveToCurrentRow();

        }catch(SQLException | ClassNotFoundException throwables){
            throwables.printStackTrace();
        }
    }

    //Update row passed in parameter.
    public void updateInfo(ResultSet set , Employee employee){
        try {
            set.updateString(Const.OS, employee.getOs());
            set.updateString(Const.CPU, employee.getCpu());
            set.updateString(Const.GPU, employee.getGpu());
            set.updateString(Const.RAM, employee.getRam());
            set.updateRow();
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
    }
}
