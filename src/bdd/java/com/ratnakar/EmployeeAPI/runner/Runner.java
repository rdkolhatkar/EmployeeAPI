package com.ratnakar.EmployeeAPI.runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/bdd/resources/Features/Test.feature"},
        tags = "@Test",
        glue = {"com.ratnakar.EmployeeAPI.stepdefinitions"}
)
public class Runner {
}
