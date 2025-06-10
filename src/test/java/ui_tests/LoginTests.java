package ui_tests;

import dto.UserLombok;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import static utils.RandomUtils.*;

public class LoginTests extends ApplicationManager {
    HomePage homePage;
    LoginPage loginPage;

    @BeforeMethod
    public void goToLoginPage(){
        homePage = new HomePage(getDriver());
        homePage.clickBtnLoginHeader();
        loginPage = new LoginPage(getDriver());
    }




    @Test
    public void loginPositiveTests(){
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm("mamon300396@gmail.com","Ercbdn300396$");

    }

    @Test
    public void loginPositiveTests_lombok(){
        UserLombok user = UserLombok.builder()
                .username("mamon300396@gmail.com")
                .password("Ercbdn300396$")
                .build();
        loginPage.typeLoginForm(user.getUsername(), user.getPassword());
        Assert.assertTrue(loginPage.validatePopUpMessage("Logged in success"), "loginPositiveTest_lombok");

    }

    @Test
    public  void loginNegativeTest_unregUser(){
        UserLombok user = UserLombok.builder()
                .username(generateEmail(10))
                .password("Ercbdn300396$")
                .build();
        loginPage.typeLoginForm(user.getUsername(), user.getPassword());
        Assert.assertTrue(loginPage.validatePopUpMessage("Login or Password incorrect"), "loginNegativeText_unregUser");

    }

    @Test
    public void loginNegativTest_EmptyPassword(){
        UserLombok user = UserLombok.builder()
                .username("mamon300396@gmail.com")
                .password("")
                .build();
        loginPage.typeLoginForm(user.getUsername(), user.getPassword());
        Assert.assertTrue(loginPage.validateMessageErrorPassword(), "loginNegativeTest_EmptyPassword");
    }
}
