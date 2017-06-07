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

public class Task8 {
    private WebDriver driver;

    @Before
    public void  start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @Test
    public void Task8() {
        driver.get("http://localhost/litecart/en/");
        List<WebElement> sticks;
        List<WebElement> ducks = driver.findElements(By.cssSelector("di.middle > div.content > div.box ul > li"));
        for (WebElement e : ducks){
            sticks = e.findElements(By.cssSelector("div[class^='sticker']"));
            Assert.assertTrue(sticks.size() == 1);
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
