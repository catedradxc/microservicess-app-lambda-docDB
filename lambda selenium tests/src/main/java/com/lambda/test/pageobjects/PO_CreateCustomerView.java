package com.lambda.test.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_CreateCustomerView {

    static public void fillForm(WebDriver driver, String namep, String firstnamep,
                                String emailp, String streetp, String cityp) {
        WebElement name = driver.findElement(By.name("name"));
        name.click();
        name.clear();
        name.sendKeys(namep);
        WebElement lastname = driver.findElement(By.name("firstname"));
        lastname.click();
        lastname.clear();
        lastname.sendKeys(firstnamep);
        WebElement email = driver.findElement(By.name("email"));
        email.click();
        email.clear();
        email.sendKeys(emailp);
        WebElement street = driver.findElement(By.name("street"));
        street.click();
        street.clear();
        street.sendKeys(streetp);
        WebElement city = driver.findElement(By.name("city"));
        city.click();
        city.clear();
        city.sendKeys(cityp);
        //Pulsar el boton de Alta.
        By boton = By.id("add");
        driver.findElement(boton).click();
    }
}
