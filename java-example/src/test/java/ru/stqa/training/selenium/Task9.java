package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class Task9 {
    private WebDriver driver;

    @Before
    public void  start() {
        driver = new ChromeDriver();
    }

    @Test
    public void Task9() {
        List<String> countries = new ArrayList<>();
        List<String> regions = new ArrayList<>();

        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");



    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
