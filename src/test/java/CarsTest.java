import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.BasicPage;
import pages.TravelsCars;
import pages.TravelsMenu;
import pages.TravelsLogin;
import utils.ExcelUtil;

import java.util.List;

public class CarsTest extends BasicTest {

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
        Thread.sleep(2000);
    }

    @BeforeMethod
    public void goToCarsPage() throws InterruptedException {
        TravelsMenu travelsMenu = new TravelsMenu(webDriver);
        BasicPage basicPage = new BasicPage(webDriver);

        List<WebElement> webElementList = travelsMenu.getSideBarList();
        webElementList.get(32).click();

        List<WebElement> carsList = travelsMenu.getSideBarCars();
        Thread.sleep(2000);

        carsList.get(0).click();

        //close Chat iframe
        basicPage.killAbraham();
    }

    @Test
    public void numberOfCars() throws InterruptedException {
        TravelsCars travelsCars = new TravelsCars(webDriver);

        travelsCars.clickButtonAll();
        Thread.sleep(2000);

        List<WebElement> cars = travelsCars.getCarsRows();
        Thread.sleep(2000);

        Assert.assertEquals(cars.size(), 10);
    }

    @Test
    public void numberOfOrders() {
        TravelsCars travelsCars = new TravelsCars(webDriver);

        List<WebElement> orderList = travelsCars.getOrderColumn();
        int count = 0;
        for (WebElement element : orderList) {
            count += Integer.parseInt(element.getAttribute("Value"));
//            Assert.assertTrue(Integer.parseInt(element.getAttribute("Value")) > 50);
        }

        Assert.assertTrue(count > 50);
    }

    @Test
    public void uploadImage() throws InterruptedException {
        TravelsCars travelsCars = new TravelsCars(webDriver);

        travelsCars.clickUpload();
        travelsCars.uploadImage();
        Thread.sleep(3000);

        List<WebElement> images = travelsCars.getImageList();

        Assert.assertTrue(images.get(0).getAttribute("href").contains("1.jpg"));
    }
}
