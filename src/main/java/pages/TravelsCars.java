package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TravelsCars extends BasicPage {

    private By carsTableRows = By.cssSelector(".xcrud-row");
    private By orderColumn = By.cssSelector(".form-control.input-sm");
    private By buttonAll = By.cssSelector("[data-limit='all']");
    private By dropZone = By.cssSelector(".dz-hidden-input");
    private By images = By.cssSelector("td a");

    public TravelsCars(WebDriver webDriver) {
        super(webDriver);
    }

    public List<WebElement> getCarsRows() {
        return getWebDriver().findElements(this.carsTableRows);
    }

    public List<WebElement> getOrderColumn() {
        return getWebDriver().findElements(this.orderColumn);
    }

    public void clickButtonAll() {
        getWebDriver().findElement(buttonAll).click();
    }

    public void clickUpload() {
        getCarsRows().get(0).findElements(By.cssSelector("a")).get(1).sendKeys(Keys.RETURN);
    }

    public void uploadImage() {
        String imgUrl = "C:\\Users\\Nikola\\Documents\\Nikola_QABootcamp\\8. Projekat\\phptravels\\src\\main\\resources\\1.jpg";
        getWebDriver().findElement(dropZone).sendKeys(imgUrl);
    }

    public List<WebElement> getImageList(){
        return getWebDriver().findElements(images);
    }
}
