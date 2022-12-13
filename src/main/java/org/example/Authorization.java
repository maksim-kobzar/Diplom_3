package org.example;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Authorization {
    private WebDriver driver;

    public Authorization(WebDriver driver) {
        this.driver = driver;
    }

    private By title = By.xpath("//h2[text()=\"Вход\"]");
    private By btnHomeLogin = By.xpath("//button[text()=\"Войти в аккаунт\"]");
    private By fieldEmail = By.xpath("//label[text ()=\"Email\"]/../input");
    private By fieldPassword = By.xpath("//input[@name=\"Пароль\"]");
    private By btnLogin = By.xpath("//button[text()=\"Войти\"]");
    private By btnOrders = By.xpath("//button[text()=\"Оформить заказ\"]");
    private By btnPersonalArea = By.xpath("//a[@href=\"/account\"]");
    private By btnLoginFromRegister = By.xpath("//a[@href=\"/login\"]");
    private By btnBtnLoginForRecovery = By.xpath("//a[@href=\"/login\"]");
    private By btnLogout = By.xpath("//button[text()=\"Выход\"]");

    @Step("Клик по кнопке Выйти")
    public void clickBtnLogout(){
        driver.findElement(btnLogout).click();
    }
    @Step("Появление заголовка страницы входа")
    public boolean checkTitle(){
        return driver.findElement(title).isDisplayed();
    }
    @Step("Клик по кнопке Выйти в аккаунт на домашней странице")
    public void clickBtnHomeLogin(){
        driver.findElement(btnHomeLogin).click();
    }
    @Step("Ввод значения в поле email")
    public void setFieldEmail(String email){
        driver.findElement(fieldEmail).sendKeys(email);
    }
    @Step("Ввод значения в поле password")
    public void setFieldPassword(String password){
        driver.findElement(fieldPassword).sendKeys(password);
    }
    @Step("Клик по кнопке Войти")
    public void clickBtnLogin(){
        driver.findElement(btnLogin).click();
    }
    @Step("Клик по кнопке Личный кабинет")
    public void clickBtnPersonalArea(){
        driver.findElement(btnPersonalArea).click();
    }
    @Step("Клик по кнопке Войти из личного кабинета")
    public void clickBtnLoginFromRegister(){
        driver.findElement(btnLoginFromRegister).click();
    }
    @Step("Клик по кнопке Войти из формы регистрации")
    public void clickBtnLoginForRecovery(){
        driver.findElement(btnBtnLoginForRecovery).click();
    }
    @Step("Заполнение полей формы входа")
    public void login(String email, String password){
        setFieldPassword(password);
        setFieldEmail(email);
    }
    @Step("Отображение кнопки Заказать")
    public boolean btnOrders(){
        return driver.findElement(btnOrders).isDisplayed();
    }
}
