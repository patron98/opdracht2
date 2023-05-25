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

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(5));
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']")));
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='password']")));
        WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));


        username.sendKeys("admin");
        password.sendKeys("admin123");
        loginButton.click();
        wait.until(ExpectedConditions.stalenessOf(loginButton));


        WebElement searchBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search']")));
        searchBar.sendKeys("directory");

        WebElement directoryTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Directory']")));
        directoryTab.click();
    }
}
