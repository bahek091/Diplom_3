import io.restassured.response.ValidatableResponse;
import org.api.UserAPI;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.model.UserData;
import org.model.UserGenerator;
import org.pageobjects.LoginPage;
import org.pageobjects.ProfilePage;

public class LogoutTest extends BaseTest{
    protected String accessToken;
    protected String refreshToken;
    protected UserAPI userAPI = new UserAPI();
    protected UserData userData;
    private LoginPage loginPage;
    private ProfilePage profilePage;

    @Before
    public void createUser(){
        userData = UserGenerator.getRandomUser();
        ValidatableResponse response = userAPI.createUser(userData)
                .log().all();

        accessToken = response.extract().path(UserAPI.ACCESS_TOKEN_FIELD);
        refreshToken = response.extract().path(UserAPI.REFRESH_TOKEN_FIELD);

        loginPage = new LoginPage(driver);
        loginPage.loginToApplication(userData);
        profilePage = new ProfilePage(driver);
        loginPage.enterToProfileButtonClick();

    }

    @After
    public void deleteUser(){
        userAPI.logoutAndDeleteUser(accessToken, refreshToken);
    }

    @Test
    public void checkLogoutTest(){
        profilePage.exitButtonClick();
        Assert.assertEquals(LoginPage.LOGIN_URI,
                loginPage.getCurrentPageIRL());

    }

}
