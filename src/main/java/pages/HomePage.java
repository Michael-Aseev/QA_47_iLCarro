package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.time.LocalDate;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        setDriver(driver);
        driver.get("https://ilcarro.web.app/search");
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//a[text()= 'Log in' ]")
    WebElement btnLoginHeader;
    // WebElement btnLoginHeader = driver.findElement(By.xpath("//a[text()= 'Log in' ]"));

    @FindBy(id = "city")
    WebElement inputCity;
    @FindBy(id = "dates")
    WebElement inputDates;

    public void clickBtnLoginHeader() {
        btnLoginHeader.click();
    }

    public void typeSearchForm(String city, LocalDate starDate, LocalDate endDate) {
        inputCity.sendKeys(city);
        inputDates.sendKeys(dateToString(starDate) + " / " + dateToString(endDate));
    }

    private String dateToString(LocalDate date) {
        return (date.getMonthValue()) + "/" + date.getDayOfMonth() + "/" + date.getYear();
    }

}
