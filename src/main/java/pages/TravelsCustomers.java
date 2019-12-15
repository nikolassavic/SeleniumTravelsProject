package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TravelsCustomers extends BasicPage {

    private By editButton = By.cssSelector("[title='Edit']");
    private By firstName = By.name("fname");
    private By lastName = By.name("lname");
    private By email = By.name("email");
    private By mobile = By.name("mobile");
    private By country = By.className("select2-choice");
    private By address1 = By.name("address1");
    private By status = By.name("status");
    private By newsletter = By.className("checkbox");
    private By submit = By.cssSelector(".panel-footer button");

    private WebElement input;

    private By search = By.className("xcrud-search-toggle");
    private By searchInput = By.className("xcrud-searchdata");
    private By go = By.cssSelector("[data-search='1']");
    private By firstRow = By.className("xcrud-row td");

    public TravelsCustomers(WebDriver webDriver) {
        super(webDriver);
    }

    public By getStatus() {
        return status;
    }

    public void clickEditButton(){
        getWebDriver().findElements(editButton).get(3).click();
    }

    public void enterFirstName(String firstName) {
        this.input = getWebDriver().findElement(this.firstName);
        input.clear();
        input.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        this.input = getWebDriver().findElement(this.lastName);
        input.clear();
        input.sendKeys(lastName);
    }

    public void enterEmail(String email) {
        this.input = getWebDriver().findElement(this.email);
        input.clear();
        input.sendKeys(email);
    }

    public void enterMobile(String mobile) {
        this.input = getWebDriver().findElement(this.mobile);
        input.clear();
        input.sendKeys(mobile);
    }

    public void enterCountry(String country){
        this.input = getWebDriver().findElement(this.country);
        input.click();
        WebElement input2 = getWebDriver().findElement(By.className("select2-input"));
        input2.sendKeys(country);
        input2.sendKeys(Keys.ENTER);
    }

    public void enterAddress1(String address) {
        this.input = getWebDriver().findElement(this.address1);
        input.clear();
        input.sendKeys(address);
    }

    public void clickNewsletter() {
        getWebDriver().findElement(this.newsletter).click();
    }

    public void clickSubmit() {
        getWebDriver().findElement(this.submit).click();
    }

    public void clickSearch() {
        getWebDriver().findElement(search).click();
    }

    public void enterInSearchInput(String data) {
        getWebDriver().findElement(searchInput).sendKeys(data);
    }

    public void clickGo() {
        getWebDriver().findElement(go).click();
    }

    public String findName(){
        return getWebDriver().findElements(firstRow).get(3).getText();
    }
}
