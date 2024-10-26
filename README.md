**Objective:**
 Develop a simple 3-tier rule engine application(Simple UI, API and Backend, Data) to determine
 user eligibility based on attributes like age, department, income, spend etc.The system can use
 Abstract Syntax Tree (AST) to represent conditional rules and allow for dynamic
 creation,combination, and modification of these rules.
 
** Prerequisites**
Java 11
Maven 3.6+
Spring Boot 2.7.4

**Project Structure**
Driver.java: Main driver class that demonstrates the functionality by creating, combining, and evaluating rules.
Node.java: Model class representing each node of the AST, either as an operand (condition) or an operator (AND/OR).
RuleEngineController.java: REST controller that exposes API endpoints to handle rule creation, combination, and evaluation.
RuleEngineControllerTest.java: Unit test class for the controller, validating the endpoints using MockMvc.
RuleEngine.java: Core business logic to parse, combine, and evaluate the rules.

**Running the Application**
Clone this repository
To start the application, run command:- " mvn spring-boot:run "

**API Endpoints**
The RuleEngine service exposes three main endpoints:

Create Rule - POST /api/rules/create_rule

Description: Parses a single rule string and creates an AST node.
Request Body: { "rule_string": "age > 30 AND department == 'Sales'" }
Response: AST node representing the rule.
Combine Rules - POST /api/rules/combine_rules

Description: Combines multiple rule strings into a single AST node.
Request Body: { "rules": ["age > 30 AND department == 'Sales'", "salary > 50000 OR experience > 5"] }
Response: AST node representing the combined rule.
Evaluate Rule - POST /api/rules/evaluate_rule

Description: Evaluates a rule (AST node) against a set of user attributes.
Request Body: { "ast": "((age > 30 AND department == 'Sales') OR (age < 25 AND department == 'Marketing'))", "data": { "age": 35, "department": "Sales", "salary": 60000, "experience": 3 } }
Response: Boolean result of the evaluation.

**Compatibility**
This project setup is compatible with the following requirements:

Spring Boot 2.7.4 for dependency management and server configurations.
Java 11 is set in pom.xml and ensures compatibility with the project's libraries and Spring Boot.
JUnit and MockMvc are used for unit testing, providing a robust testing environment.
AST Parsing and Evaluation functions are demonstrated, allowing flexible rule management.
