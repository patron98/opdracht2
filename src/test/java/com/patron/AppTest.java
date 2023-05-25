package com.patron;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class AppTest {
    private WebDriver driver;

    @Before
    public void setUp(){
        driver = new ChromeDriver();
        String url = "https://opensource-demo.orangehrmlive.com";
        driver.get(url);

        WebElement username = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement login = driver.findElement(By.cssSelector("button[type='submit']"));

        username.sendKeys("admin");
        password.sendKeys("admin123");
        login.click();




    }
}
