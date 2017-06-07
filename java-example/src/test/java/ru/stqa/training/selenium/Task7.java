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
import java.util.concurrent.TimeUnit;

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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void Task7() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        List<WebElement> mainMenu = driver.findElements(By.id("box-apps-menu li"));
        WebElement m, e, u, s;
        for (int i = 1; i <= mainMenu.size(); i++){
            m = driver.findElement(By.cssSelector("ul#box-apps-menu > li:nth-of-type(" + i + ")"));
            m.click();

            Assert.assertTrue(isElementPresent(driver,By.tagName("h1")));

            try {
                e = driver.findElement(By.cssSelector("ul#box-apps-menu > li.selected[id = 'app-']"));
                u = e.findElement(By.cssSelector("ul.docs"));    // Зависает тут, если нет списка 2го уровня
                List<WebElement> subMenu = u.findElements(By.tagName("li"));

                WebElement t;
                for (int j = 1; j <= subMenu.size(); j++) {
                    t = driver.findElement(By.cssSelector("ul.docs > li:nth-of-type(" + j + ")"));
                    t.click();

                    Assert.assertTrue(isElementPresent(driver, By.tagName("h1")));
                }

            } catch (NoSuchElementException ex){
                continue;
            }
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
