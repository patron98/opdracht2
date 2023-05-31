package com.patron.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddEmployeePage {
    private final WebDriverWait wait;

    private final By searchBar = By.xpath("//input[@placeholder='Search']");
    private final By directoryTab = By.xpath("//span[text()='PIM']");
    private final By addEmployeeButton = By.xpath("//a[contains(., 'Add Employee')]");
    private final By firstNameInput = By.xpath("//input[@name='firstName']");
    private final By lastNameInput = By.xpath("//input[@name='lastName']");
    private final By saveButton = By.xpath("//button[@type='submit']");

    public AddEmployeePage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void addEmployee(String firstName, String lastName) {
        WebElement searchBarElement = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBar));
        searchBarElement.sendKeys("PIM");

        WebElement directoryTabElement = wait.until(ExpectedConditions.visibilityOfElementLocated(directoryTab));
        directoryTabElement.click();

        WebElement addEmployeeButtonElement = wait.until(ExpectedConditions.visibilityOfElementLocated(addEmployeeButton));
        addEmployeeButtonElement.click();

        WebElement firstNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
        WebElement lastNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameInput));
        WebElement saveButtonElement = wait.until(ExpectedConditions.visibilityOfElementLocated(saveButton));

        firstNameElement.sendKeys(firstName);
        lastNameElement.sendKeys(lastName);
        saveButtonElement.click();
    }
}
