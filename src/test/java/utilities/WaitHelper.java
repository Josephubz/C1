package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class    WaitHelper {
    public WebDriver driver;

    public WaitHelper(WebDriver driver){
        this.driver = driver;
    }

    public WebElement WaitForElement (WebElement element, Duration durationOfSeconds ){
        WebDriverWait wait = new WebDriverWait(driver, durationOfSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(element));

        return element;
    }




}
