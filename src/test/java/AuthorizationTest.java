import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.example.Authorization;
import org.example.Methods_Api.API_Method;
import org.example.Methods_Api.UserGenerator;
import org.example.Registration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.example.URL.*;

@DisplayName("Проверки для авторизации")
public class AuthorizationTest {
    private WebDriver driver;
    private String name;
    private String password;
    private String email;
    API_Method api_method = new API_Method();
    UserGenerator userGenerator = new UserGenerator();
    private void setData(){
        email = userGenerator.getEmail();
        password = userGenerator.getPassword();
        name = userGenerator.getName();
    }

    public void login(){
        Authorization objAuthorization = new Authorization(driver);
        driver.get(URL_HOME);
        objAuthorization.clickBtnHomeLogin();
        objAuthorization.login(email, password);
        objAuthorization.clickBtnLogin();
    }

    public void register(){
        driver.get(URL_REGISTER);
        Registration objRegistration = new Registration(driver);
        objRegistration.register(name, email, password);
        objRegistration.clickBtnRegisters();
    }

    @Before
    public void setDriver(){
        WebDriverManager.chromedriver().setup();
        //System.setProperty("webdriver.chrome.driver", "C:\\WebDriverbin\\bin\\chromedriver\\chromedriver.exe");
        /**Подключение Яндекс браузера*/
        //System.setProperty("C:\\WebDriverbin\\bin\\yandexdriver\\yandexdriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
        setData();
        register();
    }

    @Test
    @DisplayName("Проверка входа в аккаунт с главной странице")
    public void loginHomePage(){
        Authorization objAuthorization = new Authorization(driver);
        driver.get(URL_HOME);
        objAuthorization.clickBtnHomeLogin();
        objAuthorization.login(email, password);
        objAuthorization.clickBtnLogin();
        Assert.assertTrue("Ошибка при входе в аккаунт", objAuthorization.btnOrders());
    }

    @Test
    @DisplayName("Вход в аккаунт через кнопку Личный кабинет")
    public void loginBtnPersonalAreaTest(){
        Authorization objAuthorization = new Authorization(driver);
        driver.get(URL_HOME);
        objAuthorization.clickBtnPersonalArea();
        objAuthorization.login(email, password);
        objAuthorization.clickBtnLogin();
        System.out.println(objAuthorization.btnOrders());
        Assert.assertTrue("Ошибка при входе в аккаунт", objAuthorization.btnOrders());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginBtnLoginForRegisterTest(){
        Authorization objAuthorization = new Authorization(driver);
        driver.get(URL_REGISTER);
        objAuthorization.clickBtnLoginFromRegister();
        objAuthorization.login(email, password);
        objAuthorization.clickBtnLogin();
        Assert.assertTrue("Ошибка при входе в аккаунт", objAuthorization.btnOrders());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginBtnLoginForRecoveryTest(){
        Authorization objAuthorization = new Authorization(driver);
        driver.get(URL_FORGOT);
        objAuthorization.clickBtnLoginForRecovery();
        objAuthorization.login(email, password);
        objAuthorization.clickBtnLogin();
        System.out.println(objAuthorization.btnOrders());
        Assert.assertTrue("Ошибка при входе в аккаунт", objAuthorization.btnOrders());
    }

    /** Выход из аккаунта */
    @Test
    @DisplayName("Выход из аккаунта")
    public void logout(){
        Authorization objAuthorization = new Authorization(driver);
        login();
        objAuthorization.clickBtnPersonalArea();
        objAuthorization.clickBtnLogout();
        Assert.assertTrue("Ошибка при выходи из аккаунта", objAuthorization.checkTitle());
    }

    @After
    public void userDelete(){
        driver.quit();
        ValidatableResponse responseToken = api_method.authorizationUser(UserGenerator.getAuthorization());
        String token = responseToken.extract().path("accessToken");

        if (token != null){
            ValidatableResponse responseDelete = api_method.deleteUser(token);
            responseDelete.assertThat().statusCode(SC_ACCEPTED);
        }
    }
}