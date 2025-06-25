package ui_tests;

import dto.UserLombok;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.SignUpPage;
import utils.HeaderMenuItem;
import utils.TestNGListener;

import static pages.BasePage.*;
import static utils.RandomUtils.*;
@Listeners(TestNGListener.class)
public class SignUpTests extends ApplicationManager {
    HomePage homePage;
    SignUpPage signUpPage;

    @BeforeMethod
    public void goToLoginPage() {
        homePage = new HomePage(getDriver());
        signUpPage = clickButtonsOnHeader(HeaderMenuItem.SIGN_UP);

    }

    @Test
    public void singUpPositiveTest() {
        UserLombok user = UserLombok.builder()
                .firstName("Michael")
                .lastName("Aseev")
                .username(generateEmail(7))
                .password("Password123!")
                .build();
        signUpPage.typeSingUpForm(user);
        signUpPage.clickCheckBox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validatePopUpMessage("Registered"));

    }

    @Test
    public void singUpNegativeTest_WOcheckBox() {
        UserLombok user = UserLombok.builder()
                .firstName("Michael")
                .lastName("Aseev")
                .username(generateEmail(7))
                .password("Password123!")
                .build();
        signUpPage.typeSingUpForm(user);
        signUpPage.clickBtnYalla();
        Assert.assertFalse(signUpPage.btnYallaIsEnabled());

    }

    @Test
    public void singUpNegativeTest_WOname() {
        UserLombok user = UserLombok.builder()
                .firstName("")
                .lastName("Aseev")
                .username(generateEmail(7))
                .password("Password123!")
                .build();
        signUpPage.typeSingUpForm(user);
        signUpPage.clickCheckBox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validateErrorMessage("Name is required"));

    }

    @Test
    public void singUpNegativeTest_WOLastName() {
        UserLombok user = UserLombok.builder()
                .firstName("Michael")
                .lastName("")
                .username(generateEmail(7))
                .password("Password123!")
                .build();
        signUpPage.typeSingUpForm(user);
        signUpPage.clickCheckBox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validateErrorMessage("Last name is required"));

    }

    @Test
    public void singUpNegativeTest_WOEmail() {
        UserLombok user = UserLombok.builder()
                .firstName("Michael")
                .lastName("Aseev")
                .username("")
                .password("Password123!")
                .build();
        signUpPage.typeSingUpForm(user);
        signUpPage.clickCheckBox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validateErrorMessage("Email is required"));

    }

    @Test
    public void singUpNegativeTest_WOPassword() {
        UserLombok user = UserLombok.builder()
                .firstName("Michael")
                .lastName("Aseev")
                .username(generateEmail(7))
                .password("")
                .build();
        signUpPage.typeSingUpForm(user);
        signUpPage.clickCheckBox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validateErrorMessage("Password is required"));

    }

}
