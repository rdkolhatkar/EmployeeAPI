package com.ratnakar.EmployeeAPI.runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"E:\\Cognizant Internship\\API Assessment\\EmployeeAPI\\EmployeeAPI\\src\\bdd\\resources\\Features\\Test.feature"},
        tags = "@Test",
        glue = {"com.ratnakar.EmployeeAPI.stepdefinitions"}
)
public class Runner {
}
