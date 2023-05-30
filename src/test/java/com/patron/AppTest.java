package com.patron;

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
    private final WebDriver driver = new ChromeDriver();;
    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    private WebElement searchResults;

        @Given("I am logged in as an admin")
        public void loginAsAdmin(){
        String url = "https://opensource-demo.orangehrmlive.com";
        driver.get(url);


        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']")));
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='password']")));
        WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));

        username.sendKeys("admin");
        password.sendKeys("admin123");
        loginButton.click();
        wait.until(ExpectedConditions.stalenessOf(loginButton));
        }

        @When("I add a new employee with name {string}")
        public void addUser(String employeeName){
        WebElement searchBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search']")));
        searchBar.sendKeys("PIM");

        WebElement directoryTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='PIM']")));
        directoryTab.click();

        WebElement addEmployee = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(., 'Add Employee')]")));
        addEmployee.click();

        WebElement firstname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='firstName']")));
        firstname.sendKeys("James");

        WebElement lastname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='lastName']")));
        lastname.sendKeys("Bond");

        WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
        save.click();

    }

    @Then("The new employee should be added with name {string}")
    public void searchEmployee(String employeeName){

        WebElement searchBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search']")));
        searchBar.sendKeys("PIM");

        WebElement directoryTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='PIM']")));
        directoryTab.click();

        WebElement employeeList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(., 'Employee List')]")));
        employeeList.click();

        WebElement searchName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Type for hints...']")));
        searchName.sendKeys(employeeName);

        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
        search.click();

        searchResults = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='oxd-table-card']")));

        Assert.assertTrue(searchResults.isDisplayed());


    }

    @When("I search for the employee with name {string} in Directory")
    public void searchEmployeeDirectory(String employeeName){
        WebElement searchBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search']")));
        searchBar.sendKeys("Directory");

        WebElement directoryTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Directory']")));
        directoryTab.click();

        WebElement searchNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Type for hints...']")));
        searchNameInput.sendKeys(employeeName);

        By autocompleteOptionLocator = By.xpath("//div[@role='option' and contains(@class, 'oxd-autocomplete-option')]//span[contains(text(), 'James  Bond')]");
        WebElement autocompleteOption = wait.until(ExpectedConditions.elementToBeClickable(autocompleteOptionLocator));
        autocompleteOption.click();

        WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
        searchButton.click();

        By directoryCardLocator = By.xpath("//div[contains(@class, 'oxd-grid-item')]");
        searchResults = wait.until(ExpectedConditions.visibilityOfElementLocated(directoryCardLocator));

    }

    @Then("I should see the search results")
    public void verifySearchResults() {
        Assert.assertTrue(searchResults.isDisplayed());
    }


    @When("I delete the employee with name {string}")
    public void deleteEmployee(String employeeName){
        WebElement searchBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search']")));
        searchBar.sendKeys("PIM");

        WebElement directoryTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='PIM']")));
        directoryTab.click();

        WebElement employeeList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(., 'Employee List')]")));
        employeeList.click();

        WebElement searchNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Type for hints...']")));
        searchNameInput.sendKeys(employeeName);

        By autocompleteOptionLocator = By.xpath("//div[@role='option' and contains(@class, 'oxd-autocomplete-option')]//span[contains(text(), 'James  Bond')]");
        WebElement autocompleteOption = wait.until(ExpectedConditions.elementToBeClickable(autocompleteOptionLocator));
        autocompleteOption.click();

        WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
        searchButton.click();

        WebElement deleteUser = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='oxd-icon bi-trash']")));

        int maxRetries = 3;
        int retryCount = 0;

        while (retryCount < maxRetries) {
            try {
                if (deleteUser.isDisplayed()) {
                    deleteUser.click();

                    WebElement delete = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='oxd-icon bi-trash oxd-button-icon']")));

                    delete.click();

                    searchButton.click();
                }
                break;
            } catch (StaleElementReferenceException e) {
                retryCount++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }


    }
    @Then("the employee should no longer be listed")
    public void verifyEmployeeNotListed() {
        WebElement searchBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search']")));
        searchBar.sendKeys("PIM");

        WebElement directoryTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='PIM']")));
        directoryTab.click();

        WebElement employeeList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(., 'Employee List')]")));
        employeeList.click();

        WebElement searchNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Type for hints...']")));
        searchNameInput.sendKeys("James Bond");

        WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
        searchButton.click();

        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='oxd-table-card']")));
            Assert.assertTrue(true); // Employee is not listed
        } catch (NoSuchElementException | TimeoutException e) {
            Assert.fail("Employee is still listed");
        }
    }

    @After
    public void teardown() {
        driver.close();
    }

}
