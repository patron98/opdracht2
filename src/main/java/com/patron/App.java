package com.patron;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Hello world!
 *
 */
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
        searchBar.sendKeys("admin");

        WebElement directoryTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Admin']")));
        directoryTab.click();

        WebElement addUser = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='orangehrm-header-container']//button[contains(., 'Add')]")));
        addUser.click();

        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='oxd-select-text--after']//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow']")));
        dropdown.click();

        WebElement adminOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='oxd-select-dropdown']//div[@class='oxd-select-option']//span[contains(., 'Admin')]")));
        adminOption.click();



        //WebElement searchEmployee = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Type for hints...']")));
        //searchEmployee.sendKeys("James Bond");
    }
}
