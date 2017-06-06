package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class LoginTestFF {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void  start() {
        //FirefoxOptions options = new FirefoxOptions();

       // options.setBinary(new FirefoxBinary(new File("C:\\Program Files\\Nightly\\firefox.exe")));
       // options.setProfile(new FirefoxProfile());

        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void loginTestFF() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("remember_me")).click();
        driver.findElement(By.name("login")).click();
        wait.until(elementToBeClickable(By.className("logotype")));
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
