package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.Set;

public class Task14 {
    public WebDriver driver;

    private boolean isElementPresentM(WebElement e, By locator) {
        try {
            e.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    @Before
    public  void start() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("unexpectedAlertBehaviour", "accept");
        driver = new ChromeDriver(caps);
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.cssSelector("td#content a.button")).click();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

    @Test
    public void Task14() {
        WebElement element;
        String mainWindow = driver.getWindowHandle();
        String newWindow;
        Set<String> oldWindows;
        WebDriverWait wait = new WebDriverWait(driver, 10/*seconds*/);

        List<WebElement> links = driver.findElements(By.cssSelector("form > table:not([class = 'dataTable']) tr"));
        for (int i = 1; i <= links.size(); i++){
            element = driver.findElement(By.cssSelector("form > table:not([class = 'dataTable']) tr:nth-child(" + i +")"));
            if (isElementPresentM(element, By.cssSelector("a[href ^= 'http']"))){
                element.findElement(By.cssSelector("a[href ^= 'http']")).click();
                wait.until(ExpectedConditions.numberOfWindowsToBe(2));

                oldWindows = driver.getWindowHandles();
                oldWindows.remove(mainWindow);
                newWindow = oldWindows.iterator().next().toString();

                driver.switchTo().window(newWindow);
                driver.close();
                driver.switchTo().window(mainWindow);
            }
            else continue;

        }
    }
}
