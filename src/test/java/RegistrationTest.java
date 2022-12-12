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
import static org.example.URL.URL_REGISTER;
@DisplayName("Проверки для регистрации")
public class RegistrationTest {
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

    @Before
    public void setDriver(){
        WebDriverManager.chromedriver().setup();
        //System.setProperty("webdriver.chrome.driver", "C:\\WebDriverbin\\bin\\chromedriver\\chromedriver.exe");
        //System.setProperty("C:\\WebDriverbin\\bin\\yandexdriver\\yandexdriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
        setData();
    }

    @Test
    @DisplayName("Создание нового пользователя")
    public void registerTest(){
        driver.get(URL_REGISTER);
        Registration objRegistration = new Registration(driver);
        Authorization objAuthorization = new Authorization(driver);
        objRegistration.register(name, email, password);
        objRegistration.clickBtnRegisters();
        Assert.assertTrue("Ошибка при регистрации", objAuthorization.checkTitle());
    }

    @Test
    @DisplayName("Создание пользователя с неправильным паролем")
    public void registerErrorTextTest(){
        driver.get(URL_REGISTER);
        Registration objRegistration = new Registration(driver);
        objRegistration.register(name, email, "12345");
        Assert.assertTrue("Не отображается сообщение об ошибки при вводе пароля > 6 символов", objRegistration.getErrorText());
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
