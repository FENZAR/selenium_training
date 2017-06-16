package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.Color;

import static java.lang.System.in;

public class Task10 {
    private WebDriver driver;

    private boolean checkColor(WebElement e, boolean IsGray) {
        String color;
        String[] rgb;
        Integer r,g,b;

        color = e.getCssValue("color");
        color = color.replace("rgba", "rgb");

        rgb = color.replace("rgb(", "").replace(")", "").split(",");
        r = Integer.parseInt(rgb[0].trim());
        g = Integer.parseInt(rgb[1].trim());
        b = Integer.parseInt(rgb[2].trim());

        if (IsGray) return r == g & g == b;
        else return g == 0 & b == 0;
    }

    /*@Before
    public void start() {
        driver = new ChromeDriver();
        driver.get("http://localhost/litecart/en/");
    }*/

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

    @Test
    public void TaskChrome() {
        driver = new ChromeDriver();
        Task10();
    }

    @Test
    public void TaskFirefox() {
        FirefoxOptions options = new FirefoxOptions().setLegacy(false);
        driver = new FirefoxDriver(options);
       Task10();
    }

    @Test
    public void TaskIE() {
        driver = new InternetExplorerDriver();
        Task10();
    }

    //@Test
    public  void Task10() {
        String name, name2, regPrice, regPrice2, actPrice, actPrice2;

        Dimension regSize, actSize;
        WebElement link, regular, action;

        driver.get("http://localhost/litecart/en/");

        //MainPage
        name = driver.findElement(By.cssSelector("div#box-campaigns div.name")).getAttribute("textContent");

        regular = driver.findElement(By.cssSelector("div#box-campaigns s.regular-price"));
        regPrice = regular.getAttribute("textContent");
        regSize = regular.getSize();

        action =  driver.findElement(By.cssSelector("div#box-campaigns strong.campaign-price"));
        actPrice = action.getAttribute("textContent");
        actSize = action.getSize();

        // в)
        Assert.assertTrue(checkColor(regular, true));
        Assert.assertTrue(regular.getAttribute("tagName").equalsIgnoreCase("s"));

        // г) можно считать, что "красный" цвет это такой, у которого в RGBa представлении каналы G и B имеют нулевые значения
        Assert.assertTrue(checkColor(action, false));
        Assert.assertTrue(action.getAttribute("tagName").equalsIgnoreCase("strong"));

        // д)
        Assert.assertTrue(regSize.getHeight() < actSize.getHeight());

        link = driver.findElement(By.cssSelector("div#box-campaigns a.link"));
        link.click();

        //SecondPage
        // а)
        name2 = driver.findElement(By.cssSelector("div#box-product  h1")).getAttribute("textContent");
        Assert.assertTrue(name.equals(name2));

        // б)
        regular = driver.findElement(By.cssSelector("div.information s.regular-price"));
        regPrice2 = regular.getAttribute("textContent");
        Assert.assertTrue(regPrice.equals(regPrice2));

        action = driver.findElement(By.cssSelector("div.information strong.campaign-price"));
        actPrice2 = action.getAttribute("textContent");
        Assert.assertTrue(actPrice.equals(actPrice2));

        // в)
        Assert.assertTrue(checkColor(regular, true));
        Assert.assertTrue(regular.getAttribute("tagName").equalsIgnoreCase("s"));

        // г)
        Assert.assertTrue(checkColor(action, false));
        Assert.assertTrue(action.getAttribute("tagName").equalsIgnoreCase("strong"));

        // д)
        Assert.assertTrue(regSize.getHeight() < actSize.getHeight());
    }
}
