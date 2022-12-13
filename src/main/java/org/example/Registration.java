package org.example;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Registration {
    private WebDriver driver;

    public Registration(WebDriver driver) {
        this.driver = driver;
    }

    private By fieldName = By.xpath("//label[text ()=\"Имя\"]/../input");
    private By fieldEmail = By.xpath("//label[text ()=\"Email\"]/../input");
    private By fieldPassword = By.xpath("//input[@name=\"Пароль\"]");
    private By buttonRegister = By.xpath("//button[text()=\"Зарегистрироваться\"]");
    private By errorText = By.xpath("//p[text()=\"Некорректный пароль\"]");
    @Step("Заполнение поле Имя")
    public void setFieldName(String name){
        driver.findElement(fieldName).sendKeys(name);
    }
    @Step("Заполнение поле email")
    public void setFieldEmail(String email){
        driver.findElement(fieldEmail).sendKeys(email);
    }
    @Step("Заполнение поле password")
    public void setFieldPassword(String password){
        driver.findElement(fieldPassword).sendKeys(password);
    }
    @Step("Кликк по кнопве Регистрации")
    public void clickBtnRegisters(){
        driver.findElement(buttonRegister).click();
    }
    @Step("Отображение сообщение с ошибкой о невалидном значении в поле пароль")
    public boolean getErrorText(){
       return driver.findElement(errorText).isDisplayed();
    }
    @Step("Заполнение полей формы регистрации")
    public void register(String name, String email, String password){
        setFieldName(name);
        setFieldPassword(password);
        setFieldEmail(email);
    }

}
