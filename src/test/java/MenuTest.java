import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.TravelsMenu;
import pages.TravelsLogin;
import utils.ExcelUtil;

import java.util.List;

public class MenuTest extends BasicTest {

    @BeforeTest
    public void setup() throws InterruptedException {
        ExcelUtil excelUtil = new ExcelUtil();
        excelUtil.setExcel("C:\\Users\\Nikola\\Documents\\Nikola_QABootcamp\\8. Projekat\\phptravels\\src\\main\\resources\\data.xlsx");
        excelUtil.setWorkSheet(0);

        String email = excelUtil.getData(1, 0);
        String password = excelUtil.getData( 1, 1);
        excelUtil.closeExcel();

        super.setup();
        webDriver.get(this.baseURL+"/admin");
        Thread.sleep(2000);

        TravelsLogin travelsLogin = new TravelsLogin(webDriver);
        travelsLogin.login(email,password);
        Thread.sleep(2000);
    }

    @Test
    public void sideMenu() throws InterruptedException {
        TravelsMenu travelsMenu = new TravelsMenu(webDriver);
        List<WebElement> webElementList = travelsMenu.getSideBarList();

        webElementList.get(0).click();
        Assert.assertEquals(webDriver.getTitle(), "Dashboard");

        webElementList = travelsMenu.getSideBarList();
        webElementList.get(1).click();
        Assert.assertEquals(webDriver.getTitle(), "Updates");

        webElementList = travelsMenu.getSideBarList();
        webElementList.get(2).click();
        Assert.assertEquals(webDriver.getTitle(), "Modules");

        webElementList = travelsMenu.getSideBarList();
        webElementList.get(26).click();
        List<WebElement> tourList = webDriver.findElements(By.cssSelector("#Tours a"));
        Thread.sleep(2000);
        tourList.get(1).click();
        Assert.assertEquals(webDriver.getTitle(), "Add Tour");
        webDriver.navigate().back();

        webElementList = travelsMenu.getSideBarList();
        webElementList.get(48).click();
        Assert.assertTrue(webDriver.getTitle().contains("Coupon"));

        webElementList = travelsMenu.getSideBarList();
        webElementList.get(49).click();
        Assert.assertTrue(webDriver.getTitle().contains("Newsletter"));

        webElementList = travelsMenu.getSideBarList();
        webElementList.get(50).click();
        Assert.assertTrue(webDriver.getTitle().contains("Booking"));
    }
}
