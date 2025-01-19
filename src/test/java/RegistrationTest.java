import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.model.UserData;
import org.model.UserGenerator;
import org.pageobjects.LoginPage;
import org.pageobjects.RegisterPage;

public class RegistrationTest extends BaseTest {
    private UserData userData;
    private RegisterPage registerPage;

    @Before
    public void setUp(){
        userData = UserGenerator.getRandomUser();
        registerPage = new RegisterPage(driver);
        registerPage.openRegisterPage();
    }

    @Test
    @DisplayName("Register new user")
    public void shouldRegisterNewUserTest(){
        registerPage.fillRegisterForm(userData);
        registerPage.clickRegistrationButton();
        Assert.assertEquals(LoginPage.LOGIN_URI, registerPage.getCurrentPageURL());
    }

    @Test
    @DisplayName("Show error on incorrect password")
    public void shouldShowErrorMessageForIncorrectPasswordTest(){
        userData.setPassword(userData.getPassword().substring(0,3));
        registerPage.fillRegisterForm(userData);
        Assert.assertTrue(registerPage.checkPasswordErrorField());
    }
}
