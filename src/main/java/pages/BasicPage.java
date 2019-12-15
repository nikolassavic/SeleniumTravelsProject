package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class BasicPage {
    private WebDriver webDriver;

    public BasicPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void killAbraham(){
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) getWebDriver();
        javascriptExecutor.executeScript("document.querySelector('#chat-widget-container').style.display = 'none';");
//        + "document.querySelector('#livechat-eye-catcher').style.display = 'none';");
    }
}
