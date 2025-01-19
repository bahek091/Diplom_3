import io.restassured.response.ValidatableResponse;
import lombok.extern.java.Log;
import org.api.UserAPI;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.model.UserData;
import org.model.UserGenerator;
import org.pageobjects.LoginPage;
import org.pageobjects.MainPage;
import org.pageobjects.ProfilePage;
import org.pageobjects.RegisterPage;

public class NavigationTest extends BaseTest{
    protected String accessToken;
    protected String refreshToken;
    protected UserAPI userAPI = new UserAPI();
    protected UserData userData;
    private MainPage mainPage;
    private LoginPage loginPage;


    @Before
    public void createUser(){
        userData = UserGenerator.getRandomUser();
        ValidatableResponse response = userAPI.createUser(userData)
                .log().all();

        accessToken = response.extract().path(UserAPI.ACCESS_TOKEN_FIELD);
        refreshToken = response.extract().path(UserAPI.REFRESH_TOKEN_FIELD);

        loginPage = new LoginPage(driver);
        loginPage.loginToApplication(userData);

        mainPage = new MainPage(driver);
    }

    @After
    public void deleteUser(){
        userAPI.logoutAndDeleteUser(accessToken, refreshToken);
    }

    @Test
    public void checkRedirectFromMainPageToAccountTest(){
        mainPage.enterToProfileButtonClick();
        Assert.assertEquals(ProfilePage.PROFILE_PAGE, mainPage.getCurrentPageIRL());
    }

    @Test
    public void checkRedirectFromProfileToConstructorTest(){
        mainPage.enterToProfileButtonClick();
        mainPage.constructorButtonClik();
        Assert.assertEquals(MainPage.MAIN_PAGE_URL, mainPage.getCurrentPageIRL());
    }

    @Test
    public void checkRedirectFromProfileToLogoTest(){
        mainPage.enterToProfileButtonClick();
        mainPage.logoButtonClick();
        Assert.assertEquals(MainPage.MAIN_PAGE_URL, mainPage.getCurrentPageIRL());
    }


}
