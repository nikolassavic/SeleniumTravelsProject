package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TravelsLogin extends BasicPage {
    private By email = By.name("email");
    private By password = By.name("password");
    private By rememberMe = By.className("checkbox");
    private By forgetPassword = By.id("link-forgot");
    private By loginButton = By.tagName("button");

    public TravelsLogin(WebDriver webDriver) {
        super(webDriver);
    }

    private void setEmail(String email){
        this.getWebDriver().findElement(this.email).sendKeys(email);
    }

    private void setPassword(String password){
        this.getWebDriver().findElement(this.password).sendKeys(password);
    }


    public void login(String email,String password){
        this.setEmail(email);
        this.setPassword(password);
        this.getWebDriver().findElement(this.rememberMe).click();
        this.getWebDriver().findElement(this.loginButton).click();
    }


}
