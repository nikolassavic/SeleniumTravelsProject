package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TravelsCarsExtras extends BasicPage {

    private By addButton = By.cssSelector("[data-task='create']");
    private By name = By.name("cHRfZXh0cmFzLmV4dHJhc190aXRsZQ--");
    private By status = By.tagName("select");
    private By price = By.cssSelector("[data-pattern='numeric']");
    private By thumb = By.className("xcrud-upload");
    private By save = By.cssSelector("[data-after='list']");

    private By search = By.className("xcrud-search-toggle");
    private By searchInput = By.className("xcrud-searchdata");
    private By go = By.cssSelector("[data-search='1']");
    private By firstRow = By.cssSelector("xcrud-row td");

    public TravelsCarsExtras(WebDriver webDriver) {
        super(webDriver);
    }

    public By getName() {
        return name;
    }

    public By getPrice() {
        return price;
    }

    public By getStatus() {
        return status;
    }

    public void clickAdd() {
        getWebDriver().findElement(addButton).click();
    }

    public void clickName() {
        getWebDriver().findElement(name).click();
    }

    public void clickPrice() {
        getWebDriver().findElement(price).click();
    }

    public void clickSave() {
        getWebDriver().findElement(save).click();
    }

    public void uploadImage() {
        String imgUrl = "C:\\Users\\Nikola\\Documents\\Nikola_QABootcamp\\8. Projekat\\phptravels\\src\\main\\resources\\1.jpg";
        getWebDriver().findElement(thumb).sendKeys(imgUrl);
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
