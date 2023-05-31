package com.patron;

import com.patron.pages.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class AppTest {
    private final WebDriver driver;
    private final LoginPage loginPage;
    private final DirectoryPage directoryPage;
    private final EmployeeListPage employeeListPage;
    private final AddEmployeePage addEmployeePage;
    private final DeleteEmployeePage deleteEmployeePage;

    public AppTest() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        directoryPage = new DirectoryPage(driver);
        employeeListPage = new EmployeeListPage(driver);
        addEmployeePage = new AddEmployeePage(driver);
        deleteEmployeePage = new DeleteEmployeePage(driver);
    }

    @Given("I am logged in as an admin")
    public void loginAsAdmin() {
        loginPage.login("admin", "admin123");
    }

    @When("I add a new employee with name {string} {string}")
    public void addUser(String firstName, String lastName) {
        addEmployeePage.addEmployee(firstName, lastName);
    }

    @Then("The new employee should be added with name {string}")
    public void searchEmployee(String employeeName) {
        employeeListPage.searchEmployee(employeeName);
        Assert.assertTrue(employeeListPage.isSearchResultDisplayed());
    }

    @When("I search for the employee with name {string} in Directory")
    public void searchEmployeeDirectory(String employeeName) {
        directoryPage.searchEmployeeDirectory(employeeName);
    }

    @Then("I should see the search results")
    public void verifySearchResults() {
        Assert.assertTrue(directoryPage.isSearchResultDisplayed());
    }

    @When("I delete the employee with name {string}")
    public void deleteEmployee(String employeeName) {
        deleteEmployeePage.deleteEmployee(employeeName);
    }


    @Then("the employee should no longer be listed")
    public void verifyEmployeeNotListed() {
        Assert.assertFalse(employeeListPage.isSearchResultDisplayed());
    }

    @After
    public void teardown() {
        driver.close();
    }

}
