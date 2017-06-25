package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class Task13 {
    public WebDriver driver;

    @Before
    public  void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart/en/");
    }
/*
    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
*/

    public boolean isElementPresent(WebDriver driver, By locator) {
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
        WebElement e;
        Integer cnt;
        By locator;
        WebDriverWait wait = new WebDriverWait(driver, 5/*seconds*/);

        for (int i = 0; i < 3; i++) {
            driver.findElement(By.cssSelector("div#box-most-popular a:first-child")).click();
            e = driver.findElement(By.cssSelector("div#cart > a.content > span.quantity"));

            locator = By.cssSelector("select[name = 'options[Size]']");
            Assert.assertTrue(isElementPresent(driver, locator));
            //if(isElementPresent(driver, locator)) {
            //    selectThis(driver, locator, 1);
            //}
            driver.findElement(By.cssSelector("button[name = 'add_cart_product']")).click();
           // wait.until(ExpectedConditions.textToBePresentInElement(e, Integer.toString(i + 1)));

            driver.findElement(By.cssSelector("div#site-menu-wrapper a")).click();
        }

        //driver.findElement(By.xpath("//a[contains(text(), 'Checkout »')]")).click();

    }
}
