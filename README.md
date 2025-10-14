# 🧑‍💼 Employee API

A simple RESTful Spring Boot application for managing employee records, including creation, retrieval, and deletion. The API interacts with a MySQL database and uses Spring Data JPA for persistence.

---

## 🚀 Features

- Add new employees
- Fetch all employees
- Delete employee by ID
- Input validation and basic duplicate checks
- JPA/Hibernate integration with MySQL

---

## 🛠️ Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Gradle
- JUnit 5 & Mockito

---

## 🏗️ Project Structure

📁 EmployeeAPI/
┣ 📂 src/
┃ ┣ 📂 main/
┃ ┃ ┣ 📂 java/com/ratnakar/EmployeeAPI/
┃ ┃ ┃ ┣ 📂 controller/       # REST endpoints
┃ ┃ ┃ ┣ 📂 model/            # Entity classes
┃ ┃ ┃ ┣ 📂 repository/       # JPA interfaces
┃ ┃ ┃ ┣ 📂 service/          # Business logic
┃ ┃ ┃ ┗ 📂 setup/            # Exception & response handling
┃ ┃ ┗ 📂 resources/
┃ ┃     ┗ 📝 application.properties
┃ ┗ 📂 test/                 # JUnit + Mockito tests
┣ ⚙️ build.gradle
┗ 📘 README.md

```

## ⚙️ Prerequisites

- Java 17+
- MySQL Database (running on `localhost:3306`)
- Gradle
- IDE like IntelliJ IDEA or VSCode

---

## 📥 Setup & Installation

1. **Clone the repository**
```bash
git clone https://github.com/<your-username>/EmployeeAPI.git
cd EmployeeAPI
```

Configure MySQL

Update src/main/resources/application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/employees
spring.datasource.username=your_username
spring.datasource.password=your_password

2. **Build the project**
```bash
./gradlew build
```
3. **Run the application**
```bash
./gradlew bootRun
```
4. **Access the API**
   - Open your browser or use a tool like Postman to access:
     - `GET http://localhost:8080/api/employees` - Fetch all employees
     - `POST http://localhost:8080/api//addEmployee` - Add a new employee
     - `DELETE http://localhost:8080/api/employee/delete/{id}` - Delete an employee by ID
     - `GET http://localhost:8080/api/employees/{id}` - Fetch an employee by ID

## 🧪 Running Tests
Run the following command to execute unit tests:
```bash
./gradlew test
```
📡 API Endpoints
| Method | Endpoint                             | Description              |
| ------ | ------------------------------------ | ------------------------ |
| POST   | `/addEmployee`                       | Add a new employee       |
| GET    | `/api/employees`                     | Get all employee records |
| DELETE | `/api/employee/delete/{employee_id}` | Delete employee by ID    |

🔄 Sample Request (POST /addEmployee)
```json

{
  "employee_id": "EMP001",
  "employee_name": "Alice Smith",
  "job_role": "QA Engineer"
}
```
🔄 Sample Response (GET /api/employees)

```json
[
  {
    "employee_id": "EMP001",
    "employee_name": "Alice Smith",
    "job_role": "QA Engineer"
  },
  {
    "employee_id": "EMP002",
    "employee_name": "Bob Johnson",
    "job_role": "Software Developer"
  }
]
```
💡 Future Enhancements (Ideas)
1) Add Swagger/OpenAPI documentation
2) Add PUT API for employee updates
3) Implement global exception handling
4) Connect with a frontend (React/Angular)
