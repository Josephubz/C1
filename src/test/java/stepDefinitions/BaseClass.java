package stepDefinitions;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;
import utilities.WaitHelper;

import java.util.Properties;

public class BaseClass {
    public WebDriver driver;
    public LoginPage lp;
    public AddCustomerPage cst;
    public SearchCustomerPage sp;
    public static Logger logger;
    public Properties configProp;


    //Generating random string for email id
    public static String randomestring(){
        return (RandomStringUtils.randomAlphabetic(8));
    }
}
