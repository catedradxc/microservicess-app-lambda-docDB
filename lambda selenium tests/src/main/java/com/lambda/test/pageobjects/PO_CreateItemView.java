package com.lambda.test.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_CreateItemView extends PO_NavView{

    static public void fillForm(WebDriver driver, String namep, String price) {
        WebElement name = driver.findElement(By.name("name"));
        name.click();
        name.clear();
        name.sendKeys(namep);
        WebElement priceElement = driver.findElement(By.name("price"));
        priceElement.click();
        priceElement.clear();
        priceElement.sendKeys(price);
        //Pulsar el boton de Alta.
        By boton = By.id("add");
        driver.findElement(boton).click();
    }

}
