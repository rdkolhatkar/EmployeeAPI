package com.ratnakar.EmployeeAPI.util;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;

import java.sql.*;

public class DbConnection {

    private Connection dbConnection;

    EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();

    String url = EnvironmentSpecificConfiguration.from(variables).getProperty("spring.datasource.url");

    String username = EnvironmentSpecificConfiguration.from(variables).getProperty("spring.datasource.username");

    String password = EnvironmentSpecificConfiguration.from(variables).getProperty("spring.datasource.password");


    public Connection getDbConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String connectionUrl = url;
        dbConnection = DriverManager.getConnection(connectionUrl, username, password);

        return getDbConnection();
    }

    public void closeDbConnection() throws SQLException {
        dbConnection.close();
    }

    public ResultSet executeDbQuery(String EmpId) throws SQLException {
        Statement statement = dbConnection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM employees.employeelist WHERE employee_id = " + "'" + EmpId + "'");
        System.out.println(result);

        return result;
    }

}
