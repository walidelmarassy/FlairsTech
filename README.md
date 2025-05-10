# 🥒 Cucumber E2E Test - OrangeHRM  

## 📌 Overview  
This project automates an end-to-end (E2E) test scenario using Cucumber and Selenium for the OrangeHRM demo application. It verifies user login, admin record creation, and deletion functionalities.  

## 🚀 Test Scenario  
The automated test performs the following steps:  

1. Navigate to [OrangeHRM Demo](https://opensource-demo.orangehrmlive.com/)  
2. Enter `"Admin"` as the username  
3. Enter `"admin123"` as the password  
4. Click the **Login** button  
5. Click the **Admin** tab in the left-side menu  
6. Retrieve the number of existing records  
7. Click the **Add** button  
8. Fill in the required user details  
9. Click the **Save** button  
10. Verify that the number of records increased by 1  
11. Search for the newly added user  
12. Delete the new user  
13. Verify that the number of records decreased by 1  

## 🛠️ Technologies Used  
- **Cucumber** - Behavior-driven testing framework  
- **Selenium WebDriver** - Browser automation  
- **Java** - Programming language for automation scripts  
- **JUnit/TestNG** - Test execution  
- **Maven** - Dependency management  

## 🏗️ Installation & Setup  
To set up this project locally, follow these steps:  

```sh
# Clone the repository  
git clone https://github.com/your-username/your-repo.git  
cd your-repo  

# Install dependencies  
mvn clean install  


▶️ Running the Tests

mvn test

📌 Project Structure

📂 src/test/java  
 ├── 📂 stepDefinitions  # Step definitions for Cucumber scenarios  
 ├── 📂 features         # Cucumber feature files  
 ├── 📂 runners         # Test runner classes  
 ├── 📂 pages           # Page Object Model (POM) for UI elements  


📬 Contact
For inquiries, reach out:
📧 Email: walidelmarassy@gmail.com
