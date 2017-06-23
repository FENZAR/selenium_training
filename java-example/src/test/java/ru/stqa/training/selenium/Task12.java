package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class Task12 {
    public WebDriver driver;

    @Before
    public  void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.findElement(By.cssSelector("ul#box-apps-menu > li:nth-child(2) > a")).click();
        driver.findElement(By.xpath("//a[contains(text(), 'Add New Product')]")).click();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

    private void selectThis(WebDriver driver, String cssSelector, String txt) {
        WebElement e;
        e = driver.findElement(By.cssSelector(cssSelector));
        Select sel = new Select(e);
        sel.selectByVisibleText(txt);
    }

    private void selectThis(WebDriver driver, String cssSelector, Integer index) {
        WebElement e;
        e = driver.findElement(By.cssSelector(cssSelector));
        Select sel = new Select(e);
        sel.selectByIndex(index);
    }

    private void selectThisJS(WebDriver driver, String cssSelector, Integer index) {
        WebElement select = driver.findElement(By.cssSelector(cssSelector));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript(String.format("arguments[0].selectedIndex = %d", index), select);
    }

    public void attachFile(WebDriver driver, By locator, String path) {
        WebElement input = driver.findElement(locator);
        File file = new File(path);
        input.sendKeys(file.getAbsolutePath());
    }

    public void setDatepicker(WebDriver driver, String cssSelector, String date)
    {
        new WebDriverWait(driver, 10).until(
                (WebDriver d) -> d.findElement(By.cssSelector(cssSelector)).isDisplayed());
        driver.findElement(By.cssSelector(cssSelector)).sendKeys(date);
    }

    public void setKeys(WebDriver driver, String cssSelector, String key) {
        WebElement e;
        e = driver.findElement(By.cssSelector(cssSelector));
        e.clear();
        e.sendKeys(key);
    }

    @Test
    public void Task12() {
        /* General */
        // Status
        driver.findElement(
                By.xpath("//div[@id = 'tab-general']//label[contains(text(), 'Enabled')]//input[@value = 1]")).click();
        // Name
        setKeys(driver, "div#tab-general input[name = 'name[en]']", "TestDuck");
        // Code
        setKeys(driver, "div#tab-general input[name = 'code']", "TestCode");
        // Categories
        driver.findElement(By.cssSelector("input[data-name = 'Rubber Ducks']")).click();
        // Default Category
        selectThis(driver, "select[name = 'default_category_id']", "Rubber Ducks");
        // Product Groups
        driver.findElement(By.cssSelector("input[value = '1-1']")).click();
        // Quantity
        setKeys(driver, "input[name = 'quantity']", "5");
        // Quantity Unit
        selectThis(driver, "select[name = 'quantity_unit_id']", 1);
        // Delivery Status
        selectThis(driver, "select[name = 'delivery_status_id']", 1);
        // Sold Out Status
        selectThis(driver, "select[name = 'sold_out_status_id']", "Temporary sold out");
        // Upload Images
        attachFile(driver, By.cssSelector("input[name = 'new_images[]']"), "src/test/resources/big-logo.png");
        // Date Valid From
        setDatepicker(driver, "input[name = date_valid_from]", "21/06/2017");
        // Date Valid To
        setDatepicker(driver, "input[name = 'date_valid_to']", "21/06/2017");

        /* Information */
        driver.findElement(By.cssSelector("div.tabs li:nth-child(2) > a")).click();
        // Manufacturer
        //selectThis(driver, "select[name = 'manufacturer_id']", 1);
        selectThisJS(driver, "select[name = 'manufacturer_id']", 1);
        // Supplier
        selectThis(driver, "select[name = 'supplier_id']", 0);
        // Keywords
        setKeys(driver, "input[name = 'keywords']", "someKeywords");
        // Short Description
        setKeys(driver, "input[name = 'short_description[en]']", "Short Description");
        // Description
        setKeys(driver, "div.trumbowyg-editor", "Long Description");
        // Head Title
        setKeys(driver, "input[name = 'head_title[en]']", "Head Title");
        // Meta Description
        setKeys(driver, "input[name = 'meta_description[en]']", "Meta Description");

        /* Prices */
        driver.findElement(By.cssSelector("div.tabs li:nth-child(4) > a")).click();
        // Purchase Price
        setKeys(driver, "input[name = 'purchase_price']", "99");
        selectThis(driver, "select[name = 'purchase_price_currency_code']", "US Dollars");
        // Tax Class
        selectThis(driver, "select[name = 'tax_class_id']", 0);
        // Price USD
        setKeys(driver, "input[name = 'prices[USD]']", "9999,9");
        // Price Incl. Tax (?) USD
        setKeys(driver, "input[name = 'gross_prices[USD]']", "99,99");
        // Price EUR
        setKeys(driver, "input[name = 'prices[EUR]']", "10000,01");
        // Price Incl. Tax (?) EUR
        setKeys(driver, "input[name = 'gross_prices[EUR]']", "10000,05");

        // Save
        driver.findElement(By.cssSelector("button[name = 'save']")).click();

        driver.findElement(By.xpath("//a[contains(text(), 'TestDuck')]"));
    }
}
