package com.ratnakar.EmployeeAPI.stepdefinitions;


import com.ratnakar.EmployeeAPI.setup.EmployeeApiSetup;
import com.ratnakar.EmployeeAPI.util.DbConnection;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.sql.SQLException;

import static io.restassured.RestAssured.given;

public class StepDefinitions {

    EmployeeApiSetup employeeApiSetup = new EmployeeApiSetup();
    DbConnection dbConnection = new DbConnection();


    @Given("{string}, {string}, {string} as {string}, {string}, {string}")
    public void as(String employee_name, String employee_id, String job_role, String EmpName, String EmpId, String JobRole) throws IOException {
        employeeApiSetup.setJsonBodyData(EmpName, EmpId, JobRole);
    }

    @When("API is invoked")
    public void api_is_invoked() throws JsonProcessingException {
        employeeApiSetup.invokeApi();
    }

    @Then("Status response code is {string}")
    public void status_response_code_is(String string) {
        employeeApiSetup.validateApiResponse();
        employeeApiSetup.validateResponseCode();
    }

    @And("employeelist table should be updated with details for {string}")
    public void employeelistTableShouldBeUpdatedWithDetailsFor(String EmpId) throws SQLException, ClassNotFoundException {
        dbConnection.getDbConnection();
        dbConnection.executeDbQuery(EmpId);
        dbConnection.closeDbConnection();
    }
}
