package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import utils.PropertiesReader;

import java.time.LocalDate;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        setDriver(driver);
       // driver.get("https://ilcarro.web.app/search");
        driver.get(PropertiesReader.getProperty("start.properties", "baseUrl"));
        PageFactory.initElements(
                new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//a[text()= 'Log in' ]")
    WebElement btnLoginHeader;
    // WebElement btnLoginHeader = driver.findElement(By.xpath("//a[text()= 'Log in' ]"));

    @FindBy(id = "city")
    WebElement inputCity;
    @FindBy(id = "dates")
    WebElement inputDates;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnYalla;


    //========= calendar =========
    @FindBy(xpath = "//button[@aria-label='Choose month and year']")
    WebElement btnMonthYear;

    public void clickBtnLoginHeader() {
        btnLoginHeader.click();
    }

    //     7/10/2025 - 8/30/2025
    public void typeSearchForm(String city, LocalDate starDate, LocalDate endDate) {
        inputCity.sendKeys(city);
        inputDates.sendKeys(dateToString(starDate) + " - " + dateToString(endDate));
        removeDisabledBtnSearch();
        btnYalla.click();
    }

    private String dateToString(LocalDate date) {
        return (date.getMonthValue()) + "/" + date.getDayOfMonth() + "-" + date.getYear();
    }

    public void typeSearchFormCalendar(String city, LocalDate starDate, LocalDate endDate) {
        inputCity.sendKeys(city);
        inputDates.click();
        typeYearMonthDay(starDate);
        typeYearMonthDay(endDate);
        removeDisabledBtnSearch();
        btnYalla.click();
    }

    private void typeYearMonthDay(LocalDate date) {
        btnMonthYear.click();
        String year = Integer.toString(date.getYear());
        driver.findElement(By.xpath("//div[contains(text(),'"+year+"')]")).click();
        String month = date.getMonth().toString();
        System.out.println(month.substring(0,3));
        driver.findElement(By.xpath("//div[contains(text(),'"+month.substring(0,3) +"')]")).click();
        String day = String.valueOf(date.getDayOfMonth());
        driver.findElement(By.xpath("//div[contains(text(),'"+day+"')]")).click();
    }

}
