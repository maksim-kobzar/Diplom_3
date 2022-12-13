package org.example;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Transitions {
    private WebDriver driver;

    public Transitions(WebDriver driver) {
        this.driver = driver;
    }

    private By btnPersonalArea = By.xpath("//a[@href=\"/account\"]");
    private By btnSave = By.xpath("//button[text()=\"Сохранить\"]");
    private By btnConstructor = By.xpath("//p[text()=\"Конструктор\"]/..");
    private By btnOrders = By.xpath("//button[text()=\"Оформить заказ\"]");
    private By btnLogo = By.className("AppHeader_header__logo__2D0X2");
    @Step("Появление кнопки Оформить заказ")
    public boolean btnOrders(){
        return driver.findElement(btnOrders).isDisplayed();
    }
    @Step("Клик по кнопке Личный кабинет")
    public void clickBtnPersonalArea(){
        driver.findElement(btnPersonalArea).click();
    }
    @Step("Клик по логотипу")
    public void clickBtnLogo(){
        driver.findElement(btnLogo).click();
    }
    @Step("Клик по кнопке Конструктор")
    public void clickBtnConstructor(){
        driver.findElement(btnConstructor).click();
    }
    @Step("Отображение кнопки Сохранить")
    public boolean checkBtnSave(){
        return driver.findElement(btnSave).isDisplayed();
    }
    @Step("Клик по пункту меню")
    public void clickBtnChapter(String chapter){
        driver.findElement(By.xpath("//span[text()=\"" + chapter + "\"]/..")).click();
    }
    @Step("Отображение пункта меню")
    public String titleChapter(String chapter){
        return driver.findElement(By.xpath("//span[text()=\"" + chapter + "\"]/..")).getAttribute("class");
    }
}
