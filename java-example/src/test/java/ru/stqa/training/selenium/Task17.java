package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Task17 {
    public WebDriver driver;

    @Before
    public  void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

    @Test
    public void Task17() {
        WebElement element;
        String cssSelector = "form[name = 'catalog_form'] > table.dataTable tr.row";
        List<WebElement> elements =
                driver.findElements(By.cssSelector(cssSelector));
        for (int i = 5; i <= elements.size() + 3; i++) {
            driver.findElement(By.cssSelector(cssSelector + ":nth-child(4) > td:nth-child(3) > a")).click();
            element = driver.findElement(By.cssSelector(cssSelector + ":nth-child(" + i + ") > td:nth-child(3) > a"));
            element.click();
            driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        }
        Assert.assertTrue(driver.manage().logs().get("browser").getAll().isEmpty());
    }
}
