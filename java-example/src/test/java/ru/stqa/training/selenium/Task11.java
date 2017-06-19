package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Task11 {
    public WebDriver driver;

    @Before
    public  void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart/en/");
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

    @Test
    public void Task11() {
        WebElement curCtrl;
        Actions actions = new Actions(driver);

        String Email, Password;
        Random rand = new Random();
        Integer cur;

        curCtrl = driver.findElement(By.cssSelector("form[name = 'login_form'] a"));
        curCtrl.click();

        cur = rand.nextInt(100000);
        Email = "email" + cur.toString() + "@gmail.com";
        Password = "12345" + cur.toString();

        curCtrl = driver.findElement(By.cssSelector("form[name = 'customer_form'] input[name = 'firstname']"));
        curCtrl.sendKeys("FirstName" + cur.toString());
        curCtrl = driver.findElement(By.cssSelector("form[name = 'customer_form'] input[name = 'lastname']"));
        curCtrl.sendKeys("LastName" + cur.toString());
        curCtrl = driver.findElement(By.cssSelector("form[name = 'customer_form'] input[name = 'address1']"));
        curCtrl.sendKeys("Some address" + cur.toString());
        curCtrl = driver.findElement(By.cssSelector("form[name = 'customer_form'] input[name = 'postcode']"));
        curCtrl.sendKeys("12345");
        curCtrl = driver.findElement(By.cssSelector("form[name = 'customer_form'] input[name = 'city']"));
        curCtrl.sendKeys("City" + cur.toString());
        curCtrl = driver.findElement(By.cssSelector("form[name = 'customer_form'] input[name = 'email']"));
        curCtrl.sendKeys(Email);
        curCtrl = driver.findElement(By.cssSelector("form[name = 'customer_form'] input[name = 'password']"));
        curCtrl.sendKeys(Password);
        curCtrl = driver.findElement(By.cssSelector("form[name = 'customer_form'] input[name = 'confirmed_password']"));
        curCtrl.sendKeys(Password);
        curCtrl = driver.findElement(By.cssSelector("form[name = 'customer_form'] input[name = 'phone']"));
        curCtrl.sendKeys("+1" + cur.toString());

        curCtrl = driver.findElement(By.cssSelector("form[name = 'customer_form'] span.select2-selection__rendered"));
        curCtrl.click();
        curCtrl = driver.findElement(By.cssSelector("span[class = 'select2-search select2-search--dropdown']"));

        actions.moveToElement(curCtrl);
        actions.sendKeys("United States" + Keys.ENTER);
        actions.build().perform();

        curCtrl = driver.findElement(By.cssSelector("button[name = 'create_account']"));
        curCtrl.click();

        // logout
        curCtrl = driver.findElement(By.cssSelector("div#box-account ul.list-vertical > li:nth-child(4) > a"));
        curCtrl.click();

        // login
        curCtrl = driver.findElement(By.cssSelector("form[name = 'login_form'] input[name = 'email']"));
        curCtrl.sendKeys(Email);
        curCtrl = driver.findElement(By.cssSelector("form[name = 'login_form'] input[name = 'password']"));
        curCtrl.sendKeys(Password);

        curCtrl = driver.findElement(By.cssSelector("form[name = 'login_form'] button[name = 'login']"));
        curCtrl.click();

        // logout
        curCtrl = driver.findElement(By.cssSelector("div#box-account ul.list-vertical > li:nth-child(4) > a"));
        curCtrl.click();
    }
}
