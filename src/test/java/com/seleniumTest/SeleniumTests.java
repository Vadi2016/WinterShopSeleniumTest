package com.seleniumTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class SeleniumTests {
    private WebDriver driver;


    @BeforeSuite
    public void init() {
        driver = new OperaDriver();

    }


    @Test
    public void testAddNewProductForm() {
        driver.get("localhost:8189/wintermarket/");
        WebElement login = driver.findElement(By.xpath(".//input[@name = 'username']"));
        login.sendKeys("admin");
        WebElement pass = driver.findElement(By.xpath(".//input[@name = 'password']"));
        pass.sendKeys("100");
        WebElement btn = driver.findElement(By.xpath(".//button[@type = 'submit']"));
        btn.click();
        driver.get("localhost:8189/wintermarket/products/edit/0");
        WebElement header = driver.findElement(By.xpath(".//h4/span"));
        System.out.println(header.getText());
        WebElement title = driver.findElement(By.xpath(".//input[@name = 'title']"));
        title.sendKeys("Panasonic TV");
        WebElement addProdBtn = driver.findElement(By.xpath(".//button[@name = 'addProduct']"));
        addProdBtn.click();
        WebElement errorPrice = driver.findElement(By.xpath(".//input[@name = 'price']//following::small"));
        Assert.assertEquals(errorPrice.getText(), "минимальное значение 0");
        System.out.println(errorPrice.getText());
        WebElement upload = driver.findElement(By.xpath(".//input[@name = 'file']"));
        upload.sendKeys("./images/3.png");
        Assert.assertEquals(upload.getAttribute("value"), "C:\\fakepath\\3.png");
    }



    @AfterSuite
    public void quit() {
        driver.quit();
    }


}
