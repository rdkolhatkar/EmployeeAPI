package com.ratnakar.EmployeeAPI.setup;

import com.ratnakar.EmployeeAPI.model.RequestBody;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class EmployeeApiSetup {
    private ObjectMapper mapper = new ObjectMapper();
    RequestBody requestBody = new RequestBody();
    ValidatableResponse apiResponse;

    EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
    String appBaseUrl = EnvironmentSpecificConfiguration.from(variables).getProperty("baseUrl");
//    String appBaseUrl = "http://localhost:8090";
    String EmployeeApiEndpoint = "/addEmployee";
    String EmployeeApiURl = appBaseUrl+EmployeeApiEndpoint;

    public void setJsonBodyData(String EmpName, String EmpId, String JobRole) throws IOException {
        String strJsonFile = "E:\\Cognizant Internship\\API Assessment\\EmployeeAPI\\EmployeeAPI\\src\\bdd\\resources\\Json\\Body.json";
        requestBody = mapper.readValue(new File(strJsonFile), RequestBody.class);
        requestBody.setEmployee_name(EmpName);
        requestBody.setEmployee_id(EmpId);
        requestBody.setJob_role(JobRole);
    }

    public void invokeApi() throws JsonProcessingException {
        apiResponse = given().log().all().relaxedHTTPSValidation().contentType(ContentType.JSON)
                .body(mapper.writeValueAsString(requestBody)).when().post(EmployeeApiURl).then().log().all();
    }

   public void validateApiResponse(){
        ResponseBody response = apiResponse.extract().response().body();
       System.out.println(response);
   }

   public void validateResponseCode(){
       int responseCode = apiResponse.extract().statusCode();
       System.out.println(responseCode);
   }


}
