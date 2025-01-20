import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.api.UserAPI;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.model.UserData;
import org.model.UserGenerator;
import org.pageobjects.LoginPage;
import org.pageobjects.MainPage;
import org.pageobjects.RegisterPage;

public class LoginTest extends BaseTest{
    protected String accessToken;
    protected String refreshToken;
    protected UserAPI userAPI = new UserAPI();
    protected UserData userData;
    private LoginPage loginPage;
    private MainPage mainPage;


    @Before
    public void createUser(){
        userData = UserGenerator.getRandomUser();
        ValidatableResponse response = userAPI.createUser(userData);

        accessToken = response.extract().path(UserAPI.ACCESS_TOKEN_FIELD);
        refreshToken = response.extract().path(UserAPI.REFRESH_TOKEN_FIELD);

        loginPage = new LoginPage(driver);
        mainPage = openMainPage(driver);
    }

    @After
    public void deleteUser(){
        userAPI.logoutAndDeleteUser(accessToken, refreshToken);
    }

    @Test
    @DisplayName("Login from main page with account button")
    public void checkLoginFromMainPageTest(){
        MainPage mainPage = openMainPage(driver);
        mainPage.enterToAccountButtonClick();
        loginPage.inputLoginCredentials(userData);
        loginPage.enterButtonClick();
        Assert.assertTrue(loginPage.createOrderButtonIsDisplayed());
    }

    @Test
    @DisplayName("Login from main page with profile button")
    public void checkLoginFromAccountButtonTest(){
        mainPage.enterToProfileButtonClick();
        loginPage.inputLoginCredentials(userData);
        loginPage.enterButtonClick();
        Assert.assertTrue(loginPage.createOrderButtonIsDisplayed());
    }

    @Test
    @DisplayName("Login from registration form")
    public void checkLoginFromRegistrationFormTest(){
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openRegisterPage();
        registerPage.enterButtonClick();

        loginPage.inputLoginCredentials(userData);
        loginPage.enterButtonClick();
        Assert.assertTrue(loginPage.createOrderButtonIsDisplayed());

    }

    @Test
    @DisplayName("Login from password reset form")
    public void checkLoginFromPasswordResetFormTest(){
        mainPage.openForgotPasswordPage();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.enterButtonClick();

        loginPage.inputLoginCredentials(userData);
        loginPage.enterButtonClick();
        Assert.assertTrue(loginPage.createOrderButtonIsDisplayed());
    }

}
