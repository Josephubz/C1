package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class Steps extends BaseClass {

    @Before
    public void setup() throws IOException {

        //Added Logger
        logger = Logger.getLogger("Cucumber");
        PropertyConfigurator.configure("Props/log4j.properties");

        //Reading properties file
        configProp = new Properties();
        FileInputStream configPropFile = new FileInputStream("Props/config.properties");
        configProp.load(configPropFile);

        String br = configProp.getProperty("browser");

        if (br.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromePath"));
            driver = new ChromeDriver();
        } else if (br.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", configProp.getProperty("firefoxPath"));
            driver = new FirefoxDriver();
        }

        logger.info("********** Launching Browser **********");

        driver.manage().window().maximize();
    }


    @Given("User launch Chrome browser")
    public void launch_chrome_browser() {


        lp = new LoginPage(driver);

    }

    @When("User opens URL {string}")

    public void user_opens_url(String URL) {
        logger.info("********** Launching URL **********");
        driver.get(URL);
    }

    @And("User Enters Email as {string} and password as {string}")
    public void user_enters_email_as_and_password_as(String email, String passw) {
        logger.info("********** Setting Email and Password **********");
        lp.setUserName(email);
        lp.setPassword(passw);

    }

    @And("Click to LOG IN button")
    public void click_to_log_in_button() {
        logger.info("********** Click on Log In Button **********");
        lp.clickLogin();

    }

    @Then("Page title should be {string}")
    public void page_title_should_be(String title) throws InterruptedException {

        Thread.sleep(2000);
        if (driver.getPageSource().contains("Login was unsuccessful.")) {
            driver.close();
            Assert.assertTrue(false);
        } else {
            Assert.assertEquals(title, driver.getTitle());
        }
    }

    @When("User click on log out link")
    public void user_click_on_log_out_link() {

        lp.clickLogout();

    }

    @And("Close Browser")
    public void close_browser() {
        driver.quit();
    }

    //Customer Add features steps
    @Then("User can view Dashboard")
    public void user_can_view_dashboard() {
        // Assert.assertTrue(driver.getPageSource().contains("Dashboard"));
        cst = new AddCustomerPage(driver);
        Assert.assertEquals("Dashboard / nopCommerce administration", cst.getPageTitle());
    }

    @When("User click on customer Menu")
    public void user_click_on_customer_menu() {
        cst.click_onCustomer();
    }

    @And("Click on customer Menu Item")
    public void click_on_customer_menu_item() {
        cst.click_onCustomerItem();
    }

    @And("Click on Add new button")
    public void click_on_add_new_button() {
        cst.click_addNew();
    }

    @Then("User can view Add new Customer page")
    public void user_can_view_add_new_customer_page() {
        Assert.assertTrue(driver.getPageSource().contains("Add a new customer"));
    }

    @When("User enter customer info")
    public void user_enter_customer_info() throws InterruptedException {
        cst.set_email(randomestring() + "@gmail.com");
        cst.set_password("qwe123qwe");
        cst.set_name("Aslan");
        cst.set_LastName("Animals");
        cst.setGender("Male");
        cst.setDob("5/12/1990");
        cst.set_companyName("Wild");
        cst.set_managerOfVendor("Vendor 2");
        cst.set_customerRoles("Guests");
        cst.set_adminComment("Into The Wild");
    }

    @And("Click on save button")
    public void click_on_save_button() {
        cst.click_save();
    }

    @Then("User can view confirmation message {string}")
    public void user_can_view_confirmation_message(String msg) throws InterruptedException {
        cst.pageSourceContains(msg);
        Thread.sleep(3000);
    }

    //Search Customer by email

    @And("Enter customer email")
    public void enter_customer_email() {
        sp = new SearchCustomerPage(driver);
        sp.setSearchEmail("admin@yourStore.com");

    }

    @When("User click on search button")
    public void user_click_on_search_button() throws InterruptedException {
        sp.clickSearch();
        Thread.sleep(3000);

    }

    @Then("User should find email in the Search table")
    public void user_should_find_email_in_the_search_table() {
        boolean status = sp.searchCustomerByEmail("admin@yourStore.com");
        // boolean status = sp.searchResult("admin@yourStore.com");
        Assert.assertTrue(status);

    }

    //Search Customer by name and lastName

    @When("Enter customer name and lastName")
    public void enter_customer_name_and_last_name() {
        sp = new SearchCustomerPage(driver);
        sp.addName("John");
        sp.addLastName("Smith");
    }

    @Then("User should find name and lastName in the Search table")
    public void user_should_find_name_and_last_name_in_the_search_table() {
        sp.searchByName("John Smith");
        // Assert.assertTrue(status);
    }
}
