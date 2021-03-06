package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Task10 {
    private WebDriver driver;
    private WebDriver driver2;

    public static final String USERNAME = "semyongoryaev1";
    public static final String AUTOMATE_KEY = "BieEsfgFh2qtWNGW5eaC";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";


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
    public void TaskIE() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
        caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        driver = new InternetExplorerDriver(caps);
        Task10();
    }

    //@Test
    public  void Task10() {
        String name, name2, regPrice, regPrice2, actPrice, actPrice2;

        Dimension regSize, actSize;
        WebElement link, regular, action;

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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
        name2 = driver.findElement(By.cssSelector("div.box  h1.title")).getAttribute("textContent");
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
