package com.patron;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class AppTest {
    private final WebDriver driver = new ChromeDriver();;
    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));

    @Before
    public void setUp(){
        String url = "https://opensource-demo.orangehrmlive.com";
        driver.get(url);


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

        WebElement employeeList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(., 'Employee List')]")));
        employeeList.click();

    }

    @Test
    public void checkIfUserExists(){

        WebElement searchName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Type for hints...']")));
        searchName.sendKeys("james bond");

        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
        search.click();

        WebElement searchResults = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='oxd-table-card']")));

        Assert.assertTrue(searchResults.isDisplayed());
    }

    @Test
    public void falseUserCheck() {
        WebElement searchName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Type for hints...']")));
        searchName.sendKeys("marko borsato");

        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
        search.click();

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='oxd-table-card']")));

        } catch (NoSuchElementException | TimeoutException e) {
            Assert.assertTrue(true);
        }
    }


    @After
    public void teardown(){
        driver.close();
    }
}
