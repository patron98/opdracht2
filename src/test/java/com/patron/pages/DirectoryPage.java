package com.patron.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DirectoryPage {
    private final WebDriverWait wait;

    private final By searchBar = By.xpath("//input[@placeholder='Search']");
    private final By directoryTab = By.xpath("//span[text()='Directory']");
    private final By searchNameInput = By.xpath("//input[@placeholder='Type for hints...']");
    private final By autocompleteOption = By.xpath("//div[@role='option' and contains(@class, 'oxd-autocomplete-option')]//span[contains(text(), 'James  Bond')]");
    private final By searchButton = By.xpath("//button[@type='submit']");
    private final By searchResults = By.xpath("//div[contains(@class, 'oxd-grid-item')]");

    public DirectoryPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void searchEmployeeDirectory(String employeeName) {
        WebElement searchBarElement = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBar));
        searchBarElement.sendKeys("Directory");

        WebElement directoryTabElement = wait.until(ExpectedConditions.visibilityOfElementLocated(directoryTab));
        directoryTabElement.click();

        WebElement searchNameInputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(searchNameInput));
        searchNameInputElement.sendKeys(employeeName);

        WebElement autocompleteOptionElement = wait.until(ExpectedConditions.elementToBeClickable(autocompleteOption));
        autocompleteOptionElement.click();

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
