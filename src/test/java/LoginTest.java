import org.testng.Assert;
import org.testng.annotations.Test;
import pages.TravelsLogin;
import utils.ExcelUtil;

public class LoginTest extends BasicTest {


    @Test
    public void login() throws InterruptedException {
        ExcelUtil excelUtil = new ExcelUtil();
        excelUtil.setExcel("C:\\Users\\Nikola\\Documents\\Nikola_QABootcamp\\8. Projekat\\phptravels\\src\\main\\resources\\data.xlsx");
        excelUtil.setWorkSheet(0);

        String email = excelUtil.getData(1, 0);
        String password = excelUtil.getData(1, 1);
        excelUtil.closeExcel();

        webDriver.get(baseURL + "/admin");
        Thread.sleep(2000);

        TravelsLogin travelsLogin = new TravelsLogin(webDriver);
        travelsLogin.login(email, password);
        Thread.sleep(5000);

        Assert.assertEquals(webDriver.getTitle(), "Dashboard");
    }
}
