import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.BasicPage;
import pages.TravelsCustomers;
import pages.TravelsMenu;
import pages.TravelsLogin;
import utils.ExcelUtil;

import java.util.List;

public class CustomersTest extends BasicTest {

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
    public void goToCustomersPage() throws InterruptedException {
        TravelsMenu travelsMenu = new TravelsMenu(webDriver);
//        BasicPage basicPage = new BasicPage(webDriver);

        List<WebElement> webElementList = travelsMenu.getSideBarList();
        Thread.sleep(2000);

        webElementList.get(13).click();
        List<WebElement> accountsList = travelsMenu.getSideBarAccounts();
        Thread.sleep(2000);

        accountsList.get(2).click();

        //close Chat iframe
//        basicPage.killAbraham();
    }

    @Test
    public void editConsumer() throws InterruptedException {
        BasicPage basicPage = new BasicPage(webDriver);
        TravelsCustomers travelsCustomers = new TravelsCustomers(webDriver);

        travelsCustomers.clickEditButton();
        Thread.sleep(3000);

        travelsCustomers.enterFirstName("Dragan, Jove");
        travelsCustomers.enterLastName("Torbica");
        travelsCustomers.enterEmail("statidecko@radis.com");
        travelsCustomers.enterMobile("064123456");
        travelsCustomers.enterCountry("Bosnia and Herzegovina");
        travelsCustomers.enterAddress1("Detelinara");

        Select selectStatus = new Select(webDriver.findElement(travelsCustomers.getStatus()));
        selectStatus.selectByIndex(1);

        travelsCustomers.clickNewsletter();
        Thread.sleep(2000);

        basicPage.killAbraham();

        travelsCustomers.clickSubmit();
        Thread.sleep(3000);

        travelsCustomers.clickSearch();
        travelsCustomers.enterInSearchInput("Torbica");

        travelsCustomers.clickGo();
        Thread.sleep(4000);

        Assert.assertTrue(travelsCustomers.findName().contains("Torbica"));
    }
}
