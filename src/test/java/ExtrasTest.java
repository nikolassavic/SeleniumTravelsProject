import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.TravelsCarsExtras;
import pages.TravelsMenu;
import pages.TravelsLogin;
import utils.ExcelUtil;

import java.util.List;

public class ExtrasTest extends BasicTest {

    @BeforeTest
    public void setup() throws InterruptedException {
        ExcelUtil excelUtil = new ExcelUtil();
        excelUtil.setExcel("C:\\Users\\Nikola\\Documents\\Nikola_QABootcamp\\8. Projekat\\phptravels\\src\\main\\resources\\data.xlsx");
        excelUtil.setWorkSheet(0);

        String email = excelUtil.getData(1, 0);
        String password = excelUtil.getData(1, 1);
        excelUtil.closeExcel();

        super.setup();
        webDriver.get(this.baseURL + "/admin");
        Thread.sleep(2000);

        TravelsLogin travelsLogin = new TravelsLogin(webDriver);
        travelsLogin.login(email, password);
        Thread.sleep(7000);
    }

    @BeforeMethod
    public void goToCarsPage() throws InterruptedException {
        TravelsMenu travelsMenu = new TravelsMenu(webDriver);
//        BasicPage basicPage = new BasicPage(webDriver);

        List<WebElement> webElementList = travelsMenu.getSideBarList();
        Thread.sleep(2000);

        webElementList.get(32).click();
        List<WebElement> carsList = travelsMenu.getSideBarCars();
        Thread.sleep(2000);

        carsList.get(1).click();

        //close Chat iframe
//        basicPage.killAbraham();
    }

    @Test
    public void addExtrasToCar() throws InterruptedException {
        TravelsCarsExtras travelsCarsExtras = new TravelsCarsExtras(webDriver);

        travelsCarsExtras.clickAdd();
        Thread.sleep(8000);

        travelsCarsExtras.clickName();
        webDriver.findElement(travelsCarsExtras.getName()).sendKeys("Varburg");

        Select select = new Select(webDriver.findElement(travelsCarsExtras.getStatus()));
        select.selectByIndex(1);

        travelsCarsExtras.clickPrice();
        webDriver.findElement(travelsCarsExtras.getPrice()).sendKeys("1000");

        travelsCarsExtras.uploadImage();
        Thread.sleep(8000);

        travelsCarsExtras.clickSave();
        Thread.sleep(3000);

        travelsCarsExtras.clickSearch();
        Thread.sleep(3000);

        travelsCarsExtras.enterInSearchInput("Varburg");
        Thread.sleep(3000);

        travelsCarsExtras.clickGo();
        Thread.sleep(3000);

        Assert.assertTrue(travelsCarsExtras.findName().contains("Varburg"));
    }

    @Test
    public void addLettersInPrice() throws InterruptedException {
        TravelsCarsExtras travelsCarsExtras = new TravelsCarsExtras(webDriver);

        travelsCarsExtras.clickAdd();
        Thread.sleep(5000);

        travelsCarsExtras.clickName();
        webDriver.findElement(travelsCarsExtras.getName()).sendKeys("Varburg");

        travelsCarsExtras.clickPrice();
        WebElement letters = webDriver.findElement(travelsCarsExtras.getPrice());
        letters.sendKeys("limuzina");
        Thread.sleep(3000);

        Assert.assertFalse(letters.getText().contains("limuzina"));

    }
}
