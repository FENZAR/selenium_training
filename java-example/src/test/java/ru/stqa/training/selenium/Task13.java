package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.NoSuchElementException;

public class Task13 {
    public WebDriver driver;

    @Before
    public  void start() {
        driver = new ChromeDriver();
        driver.get("http://localhost/litecart/en/");
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

    private boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    private void selectThis(WebDriver driver, By locator, Integer index) {
        WebElement e;
        e = driver.findElement(locator);
        Select sel = new Select(e);
        sel.selectByIndex(index);
    }

    @Test
    public void Task13() {
        WebElement element;
        By locator, locatorW;
        String DuckName;
        WebDriverWait wait = new WebDriverWait(driver, 5/*seconds*/);

        for (int i = 0; i < 3; i++) {
            driver.findElement(By.cssSelector("div#box-most-popular a:first-child")).click();
            element = driver.findElement(By.cssSelector("div#cart > a.content > span.quantity"));

            locator = By.cssSelector("select[name = 'options[Size]']");
            if(isElementPresent(driver, locator)) {
                selectThis(driver, locator, 1);
            }
            driver.findElement(By.cssSelector("button[name = 'add_cart_product']")).click();
            wait.until(ExpectedConditions.textToBePresentInElement(element, Integer.toString(i + 1)));

            driver.findElement(By.cssSelector("div#site-menu-wrapper a")).click();
        }

        driver.findElement(By.xpath("//a[contains(text(), 'Checkout »')]")).click();

        // Remove
        locatorW = By.cssSelector("form[name = 'cart_form'] > div a > strong");
        while (isElementPresent(driver, locatorW)) {
            DuckName = driver.findElement(locatorW).getText();
            locator = By.xpath("//table[@class = 'dataTable rounded-corners']//td[contains(text(), '" + DuckName + "')]");
            element = driver.findElement(locator);
            driver.findElement(By.cssSelector("button[name = 'remove_cart_item']")).click();
            wait.until(ExpectedConditions.stalenessOf(element));
        }
    }
}
