package com.patron;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class App 
{
    public static void main( String[] args )
    {
        WebDriver driver = new ChromeDriver();
        String url = "https://opensource-demo.orangehrmlive.com";
        driver.get(url);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']")));
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='password']")));
        WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));


        username.sendKeys("admin");
        password.sendKeys("admin123");
        loginButton.click();
        wait.until(ExpectedConditions.stalenessOf(loginButton));


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

        driver.close();

/*
        WebElement newSearchBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search']")));
        newSearchBar.sendKeys("Directory");

        WebElement newDirectoryTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Directory']")));
        newDirectoryTab.click();

        WebElement addUser = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='orangehrm-header-container']//button[contains(., 'Add')]")));
        addUser.click();

        WebElement adminDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='oxd-select-wrapper'])[1]")));
        adminDropdown.click();

        WebElement adminOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='oxd-select-wrapper'])[1]//span[contains(text(), 'Admin')]")));
        adminOption.click();

        WebElement enableDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='oxd-select-wrapper'])[2]")));
        enableDropdown.click();

        WebElement enableOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='oxd-select-wrapper'])[2]//span[contains(text(), 'Enabled')]")));
        enableOption.click();

        WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Type for hints...']")));
        name.sendKeys("Orange Test");

        WebElement autocompleteOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'oxd-autocomplete-dropdown')]//span[contains(text(), 'Orange  Test')]")));
        autocompleteOption.click();

        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'oxd-input-group')]//input[contains(@class, 'oxd-input--active')]")));
        usernameField.sendKeys("James Bond");

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[contains(@class, 'oxd-form-row')]//input[contains(@type, 'password')])[1]")));
        passwordField.sendKeys("password123");

        WebElement passwordField2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[contains(@class, 'oxd-form-row')]//input[contains(@type, 'password')])[2]")));
        passwordField2.sendKeys("password123");

        WebElement save = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
        save.click();


        //WebElement searchEmployee = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Type for hints...']")));
        //searchEmployee.sendKeys("James Bond");

 */
    }
}
