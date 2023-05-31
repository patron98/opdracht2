package com.patron.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EmployeeListPage {
    private final WebDriverWait wait;

    private final By searchBar = By.xpath("//input[@placeholder='Search']");
    private final By directoryTab = By.xpath("//span[text()='PIM']");
    private final By employeeListLink = By.xpath("//a[contains(., 'Employee List')]");
    private final By searchNameInput = By.xpath("//input[@placeholder='Type for hints...']");
    private final By searchButton = By.xpath("//button[@type='submit']");
    private final By searchResults = By.xpath("//div[@class='oxd-table-card']");

    public EmployeeListPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void searchEmployee(String employeeName) {
        WebElement searchBarElement = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBar));
        searchBarElement.sendKeys("PIM");

        WebElement directoryTabElement = wait.until(ExpectedConditions.visibilityOfElementLocated(directoryTab));
        directoryTabElement.click();

        WebElement employeeListLinkElement = wait.until(ExpectedConditions.visibilityOfElementLocated(employeeListLink));
        employeeListLinkElement.click();

        WebElement searchNameInputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(searchNameInput));
        searchNameInputElement.sendKeys(employeeName);

        WebElement searchButtonElement = wait.until(ExpectedConditions.visibilityOfElementLocated(searchButton));
        searchButtonElement.click();


    }

    public boolean isSearchResultDisplayed() {
        try {
            WebElement searchResultsElement = wait.until(ExpectedConditions.visibilityOfElementLocated(searchResults));
            return searchResultsElement.isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }
}
