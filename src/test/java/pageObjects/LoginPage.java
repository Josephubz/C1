package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitHelper;

import java.time.Duration;

public class LoginPage {

    public WebDriver ldriver;

    WaitHelper waithelper;

    public LoginPage (WebDriver rdriver){
        ldriver = rdriver;
        PageFactory.initElements(rdriver,this);
        waithelper = new WaitHelper(rdriver);
    }

    @FindBy(id="Email")
    @CacheLookup
    WebElement txtEmail;

    @FindBy(id="Password")
    @CacheLookup
    WebElement txtPassword;

    @FindBy(xpath = "//button[normalize-space()='Log in']")
    @CacheLookup
    WebElement  btnLogin;

    @FindBy(linkText = "Logout")
    @CacheLookup
    WebElement lnkLogout;

    public void setUserName (String uname){
        txtEmail.clear();
        txtEmail.sendKeys(uname);
    }

    public void setPassword (String pwd){
        txtPassword.clear();
        txtPassword.sendKeys(pwd);
    }

    public void clickLogin(){
        btnLogin.click();
    }

    public void clickLogout(){
        /*JavascriptExecutor js = (JavascriptExecutor) ldriver;
        js.executeScript("arguments[0].click();",lnkLogout);*/
        WebElement a= waithelper.WaitForElement(lnkLogout, Duration.ofSeconds(30));
        a.click();
    }



}
