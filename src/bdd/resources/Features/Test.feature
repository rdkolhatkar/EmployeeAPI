Feature: Validation of Employee API

  @Test
  Scenario Outline: verify Employee API gives status code 201 created when API is invoked with valid credentials
    Given "employee_name", "employee_id", "job_role" as "<EmpName>", "<EmpId>", "<JobRole>"
    When API is invoked
    Then Status response code is "201"
    And employeelist table should be updated with details for "<EmpId>"
    Examples:
      | EmpName | EmpId | JobRole |
      | Ram     | A123  | SDET    |
