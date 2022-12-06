import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.example.Authorization;
import org.example.Generator;
import org.example.Registration;
import org.example.Transitions;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.example.URL.*;
@DisplayName("Проверки переходов по страницам")
public class TransitionsTest {
    private WebDriver driver;
    private String name;
    private String password;
    private String email;

    public void setData(){
        Generator generator = new Generator();
        name = generator.name();
        password = generator.password();
        email = generator.email();
    }

    public void register(){
        driver.get(URL_REGISTER);
        Registration objRegistration = new Registration(driver);
        objRegistration.register(name, email, password);
        objRegistration.clickBtnRegisters();
    }

    public void login(){
        Authorization objAuthorization = new Authorization(driver);
        driver.get(URL_HOME);
        objAuthorization.clickBtnHomeLogin();
        objAuthorization.login(email, password);
        objAuthorization.clickBtnLogin();
    }

    @Before
    public void setDriver(){
        WebDriverManager.chromedriver().setup();
        //System.setProperty("webdriver.chrome.driver", "C:\\WebDriverbin\\bin\\chromedriver\\chromedriver.exe");
        /** Подключение Яндекс браузера*/
        //System.setProperty("C:\\WebDriverbin\\bin\\yandexdriver\\yandexdriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));

    }

    @Test
    @DisplayName("Переход на страницу Личный кабинет")
    public void transitionsForPersonalAreaTest(){
        setData();
        register();
        login();
        Transitions objTTransitions = new Transitions(driver);
        objTTransitions.clickBtnPersonalArea();
        Assert.assertTrue("Ошибка при переходе на страницу - Личный кабинет", objTTransitions.checkBtnSave());
    }

    @Test
    @DisplayName("Переход по клику Конструктор")
    public void transitionsForConstructorTest(){
        setData();
        register();
        login();
        Transitions objTTransitions = new Transitions(driver);
        objTTransitions.clickBtnPersonalArea();
        objTTransitions.clickBtnConstructor();
        Assert.assertTrue("Ошибка при переходе со страницы - Личный кабинет на Конструктор", objTTransitions.btnOrders());
    }

    @Test
    @DisplayName("Переход по логотипу")
    public void transitionsLogoTest(){
        setData();
        register();
        login();
        Transitions objTTransitions = new Transitions(driver);
        objTTransitions.clickBtnPersonalArea();
        objTTransitions.clickBtnLogo();
        Assert.assertTrue("Ошибка при переходе со страницы - Личный кабинет на Конструктор", objTTransitions.btnOrders());
    }

    /**Раздел Конструктор*/

    @Test
    @DisplayName("Переход по Булки")
    public void transitionsChapterBulkiTest(){
        Transitions objTTransitions = new Transitions(driver);
        driver.get(URL_HOME);
        objTTransitions.clickBtnChapter("Булки");
        Assert.assertTrue("Ошибка при переходе со страницы - Личный кабинет на Конструктор", objTTransitions.titleChapter("Булки"));
    }

    @Test
    @DisplayName("Переход по Соусы")
    public void transitionsChapterSouseTest(){
        Transitions objTTransitions = new Transitions(driver);
        driver.get(URL_HOME);
        objTTransitions.clickBtnChapter("Соусы");
        Assert.assertTrue("Ошибка при переходе со страницы - Личный кабинет на Конструктор", objTTransitions.titleChapter("Соусы"));
    }

    @Test
    @DisplayName("Переход по Начинка")
    public void transitionsChapterFillingTest(){
        Transitions objTTransitions = new Transitions(driver);
        driver.get(URL_HOME);
        objTTransitions.clickBtnChapter("Начинки");
        Assert.assertTrue("Ошибка при переходе со страницы - Личный кабинет на Конструктор", objTTransitions.titleChapter("Начинки"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
