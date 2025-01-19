import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.api.UserAPI;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.model.UserData;
import org.model.UserGenerator;
import org.pageobjects.MainPage;

@RunWith(Parameterized.class)
public class ConstructorTest extends BaseTest{
    protected String accessToken;
    protected String refreshToken;
    protected UserAPI userAPI = new UserAPI();
    protected UserData userData;
    private MainPage mainPage;
    private final String tabName;

    public ConstructorTest(String tabName){
        this.tabName = tabName;
    }

    @Parameterized.Parameters(name = "tabName = {0}")
    public static Object[][] getData() {
        return new Object[][]{
                {"Булки"},
                {"Соусы"},
                {"Начинки"}
        };
    }

    @Before
    public void createUser(){
        userData = UserGenerator.getRandomUser();
        ValidatableResponse response = userAPI.createUser(userData)
                .log().all();

        accessToken = response.extract().path(UserAPI.ACCESS_TOKEN_FIELD);
        refreshToken = response.extract().path(UserAPI.REFRESH_TOKEN_FIELD);

        mainPage = openMainPage(driver);
    }

    @After
    public void deleteUser(){
        userAPI.logoutAndDeleteUser(accessToken, refreshToken);
    }
    @Test
    @DisplayName("Check that tabs are switching")
    public void checkTabsWorksTest(){
        mainPage.selectConstructorTab(tabName);
        String activeTab = mainPage.getTextForCurrentTab();
        Assert.assertEquals(activeTab, tabName);
    }

}
