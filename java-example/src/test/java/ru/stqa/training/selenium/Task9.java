package ru.stqa.training.selenium;

import com.google.common.collect.Ordering;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;
import java.util.ArrayList;
import java.util.List;

public class Task9 {
    private WebDriver driver;
    private WebDriverWait wait;

    private boolean checkOrdering(WebDriver driver, By locator) {
        List<WebElement> elements = new ArrayList<>();
        List<String> names = new ArrayList<>();

        elements = driver.findElements(locator);
        for (WebElement e : elements)
            names.add(e.getText());

        return Ordering.natural().isOrdered(names);
    }

    @Before
    public void  start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 3/*seconds*/);

        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @Test
    public void Task9_1() {
        By countries, regions;
        WebElement link, nZones;
        int num, size;

        // a
        countries = By.cssSelector("table.dataTable tr.row > td:nth-of-type(5) > a");
        Assert.assertTrue(checkOrdering(driver, countries));

        // b
        size = driver.findElements(By.cssSelector("table.dataTable tr.row")).size();
        for (int i = 2; i <= size; i++) {

            nZones = driver.findElement(By.cssSelector("table.dataTable tr.row:nth-of-type(" + i + ") > td:nth-of-type(6)"));
            try {
                num = Integer.parseInt(nZones.getAttribute("textContent").trim());
            } catch (NumberFormatException ex) {
                continue;
            }

            if (num > 0) {
                link = nZones.findElement(By.xpath("./ ../td[5]//a"));
                link.click();
                wait.until(titleIs("Edit Country | My Store"));

                regions = By.cssSelector("table.dataTable tr:not([class='header']) input[name $= '[name]']");
                Assert.assertTrue(checkOrdering(driver, regions));

                driver.navigate().back();
            }
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
