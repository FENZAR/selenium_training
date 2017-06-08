package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class Task7 {
    private WebDriver driver;

    private boolean isElementPresent(WebDriver driver, By locator){
        try{
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex){
            return false;
        }
    }

    @Before
    public void  start() {
        driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @Test
    public void Task7() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        List<WebElement> mainMenu = driver.findElements(By.id("box-apps-menu li"));
        WebElement m;
        for (int i = 1; i <= mainMenu.size(); i++){
            m = driver.findElement(By.cssSelector("ul#box-apps-menu > li:nth-of-type(" + i + ")"));
            m.click();
            Assert.assertTrue(isElementPresent(driver, By.tagName("h1")));

            // subMenu
            if (isElementPresent(driver, By.xpath("//ul[@class = 'docs']"))) {
                List<WebElement> subMenu = driver.findElements(By.xpath("//ul[@class = 'docs']/li"));
                WebElement t;
                for (int j = 1; j <= subMenu.size(); j++) {
                    t = driver.findElement(By.cssSelector("ul.docs > li:nth-of-type(" + j + ")"));
                    t.click();
                    Assert.assertTrue(isElementPresent(driver, By.tagName("h1")));
                }
            }
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
