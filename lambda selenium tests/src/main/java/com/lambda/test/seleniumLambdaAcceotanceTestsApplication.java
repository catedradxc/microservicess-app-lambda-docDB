package com.lambda.test;

import com.lambda.test.pageobjects.PO_CreateCustomerView;
import com.lambda.test.pageobjects.PO_CreateItemView;
import com.lambda.test.pageobjects.PO_NavView;
import com.lambda.test.pageobjects.PO_View;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class seleniumLambdaAcceotanceTestsApplication {

    static String Geckodriver = "C:/Users/juann/Desktop/serverless/apps microservicios serverless/aws-lambda-dynamoDB-microservice-app/lambda selenium tests/driver/geckodriver.exe";
    static String PathFirefox = "C:\\Program Files\\Firefox Developer Edition\\firefox.exe";
    static WebDriver driver = getDriver(PathFirefox, Geckodriver);
    static String URL = "http://spa-microservices-app-persisted.s3-website.eu-central-1.amazonaws.com/";

    public static WebDriver getDriver(String PathFirefox, String Geckodriver) {
        System.setProperty("webdriver.firefox.bin", PathFirefox);
        System.setProperty("webdriver.gecko.driver", Geckodriver);
        System.setProperty("webdriver.chrome.driver", Geckodriver);
        driver = new FirefoxDriver();
        return driver;
    }

    @BeforeEach
    public void setUp(){
        driver.navigate().to(URL);
    }

    //Después de cada prueba se borran las cookies del navegador
    @AfterEach
    public void tearDown(){
        driver.manage().deleteAllCookies();
    }

    //Antes de la primera prueba
    @BeforeAll
    static public void begin() {

    }

    //Al finalizar la última prueba
    @AfterAll
    static public void end() {
        //Cerramos el navegador al finalizar las pruebas
        driver.quit();
    }

    //

    //PR01. Shows the list of items
    @Test
    @Order(1)
    public void PR01() {
        String checkText = "Microservices app";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    //PR02. Create a new item
    @Test
    @Order(2)
    public void PR02() {
        String checkText = "Microservices app";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());

        result = PO_View.checkElementBy(driver, "id", "create-item");
        result.get(0).click();
        PO_CreateItemView.fillForm(driver, "test", "10.0");

        result = PO_View.checkElementBy(driver, "id", "test-delete");
        result.get(0).click();

        checkText = "Microservices app";
        result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    //PR02. Delete an item
    @Test
    @Order(3)
    public void PR03() {
        String checkText = "Microservices app";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());

        result = PO_View.checkElementBy(driver, "id", "create-item");
        result.get(0).click();
        PO_CreateItemView.fillForm(driver, "test", "10.0");

        result = PO_View.checkElementBy(driver, "id", "test-delete");
        result.get(0).click();

        checkText = "Microservices app";
        result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    //PR02. List customers
    @Test
    @Order(4)
    public void PR04() {
        String checkText = "Microservices app";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());

        PO_NavView.clickOption(driver, "customer", "text", "Customer service");


        checkText = "Customer service";
        result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    //PR02. Create a new customer
    @Test
    @Order(5)
    public void PR05() {
        String checkText = "Microservices app";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());

        PO_NavView.clickOption(driver, "customer", "text", "Customer service");

        result = PO_View.checkElementBy(driver, "id", "create-customer");
        result.get(0).click();
        PO_CreateCustomerView.fillForm(driver, "test", "test",
                "test@email.com", "test", "test");

        result = PO_View.checkElementBy(driver, "id", "test-delete");
        result.get(0).click();

        checkText = "Customer service";
        result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    //PR02. Delete a customer
    @Test
    @Order(6)
    public void PR06() {
        String checkText = "Microservices app";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());

        PO_NavView.clickOption(driver, "customer", "text", "Customer service");

        result = PO_View.checkElementBy(driver, "id", "create-customer");
        result.get(0).click();
        PO_CreateCustomerView.fillForm(driver, "test", "test",
                "test@email.com", "test", "test");

        result = PO_View.checkElementBy(driver, "id", "test-delete");
        result.get(0).click();

        checkText = "Microservices app";
        result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    //PR02. List orders
    @Test
    @Order(7)
    public void PR07() {
        String checkText = "Microservices app";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());

        PO_NavView.clickOption(driver, "order", "text", "Order service");


        checkText = "Order service";
        result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    //PR02. Create a new order
    @Test
    @Order(8)
    public void PR08() {
        String checkText = "Microservices app";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());

        PO_NavView.clickOption(driver, "order", "text", "Order service");

        result = PO_View.checkElementBy(driver, "id", "create-order");
        result.get(0).click();
        result = PO_View.checkElementBy(driver, "id", "addline");
        result.get(0).click();
        result = PO_View.checkElementBy(driver, "id", "crear");
        result.get(0).click();

        result = PO_View.checkElementBy(driver, "id", "0-delete");
        result.get(0).click();

        checkText = "Microservices app";
        result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    //PR02. Delete an order
    @Test
    @Order(9)
    public void PR09() {
        String checkText = "Microservices app";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());

        PO_NavView.clickOption(driver, "order", "text", "Order service");

        result = PO_View.checkElementBy(driver, "id", "create-order");
        result.get(0).click();
        result = PO_View.checkElementBy(driver, "id", "addline");
        result.get(0).click();
        result = PO_View.checkElementBy(driver, "id", "crear");
        result.get(0).click();

        result = PO_View.checkElementBy(driver, "id", "0-delete");
        result.get(0).click();

        checkText = "Microservices app";
        result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }
}
