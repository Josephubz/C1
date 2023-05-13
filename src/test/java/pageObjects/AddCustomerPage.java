package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utilities.WaitHelper;

import java.time.Duration;

public class AddCustomerPage {

    WaitHelper waithelper;

    public WebDriver ldriver;

    public AddCustomerPage (WebDriver rdriver){
        ldriver = rdriver;
        PageFactory.initElements(rdriver,this);
        waithelper = new WaitHelper(rdriver);
    }

    By lnkCustomers_menu = By.xpath("//a[@href='#']//p[contains(text(),'Customers')]");
    By getLnkCustomers_menuItem = By.xpath("//a[@href='/Admin/Customer/List']//p[contains(text(),'Customers')]");

    By btnAddNew = By.xpath("//a[normalize-space()='Add new']");

    By txtEmail = By.xpath("//input[@id='Email']");
    By txtPassword = By.xpath("//input[@id='Password']");

    By txtCustomerRoles = By.xpath("//div[@class='input-group-append input-group-required']");
    By listItemAdministrator = By.xpath("//li[normalize-space()='Administrators']");
    By listItemGuests = By.xpath("//li[normalize-space()='Guests']");
    By listItemRegistered = By.xpath("//li[contains(text(),'Registered')]");
    By listItemVendors = By.xpath("//li[contains(text(),'Vendors')]");
    By removeRegistered = By.xpath("//span[@title='delete']");

    By drpmgrOfVendor = By.xpath("//select[@id='VendorId']");


    By rdGenderMale = By.xpath("//input[@id='Gender_Male']");
    By rdGenderFemale = By.xpath("//input[@id='Gender_Female']");

    By txtFirstName = By.xpath("//input[@id='FirstName']");
    By txtLastName = By.xpath("//input[@id='LastName']");

    By txtDob = By.xpath("//input[@id='DateOfBirth']");

    By txtCompanyName = By.xpath("//input[@id='Company']");

    By txtAdminComment = By.xpath("//textarea[@id='AdminComment']");

    By btnSave = By.xpath("//button[@name='save']");

    //Action Methods

    public String getPageTitle(){
        return ldriver.getTitle();
    }

    public void pageSourceContains(String text){
        Assert.assertTrue(ldriver.getPageSource().contains(text));

    }

    public void click_onCustomer(){
        waithelper.WaitForElement(ldriver.findElement(lnkCustomers_menu), Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) ldriver;
        js.executeScript("arguments[0].click();", ldriver.findElement(lnkCustomers_menu));
        //ldriver.findElement(lnkCustomers_menu).click();
    }

    public void click_onCustomerItem(){
        waithelper.WaitForElement(ldriver.findElement(getLnkCustomers_menuItem), Duration.ofSeconds(30));
        ldriver.findElement(getLnkCustomers_menuItem).click();
    }

    public void click_addNew(){
        ldriver.findElement(btnAddNew).click();
    }

    public void set_name (String name){
        ldriver.findElement(txtFirstName).sendKeys(name);
    }

    public void set_LastName (String lastName){
        ldriver.findElement(txtLastName).sendKeys(lastName);
    }

    public void set_email (String email){
        ldriver.findElement(txtEmail).sendKeys(email);
    }

    public void set_password (String password){
        ldriver.findElement(txtPassword).sendKeys(password);
    }

    public void set_companyName (String companyName){
        ldriver.findElement(txtCompanyName).sendKeys(companyName);
    }

    public void set_customerRoles (String role) {

        ldriver.findElement(removeRegistered).click();

        ldriver.findElement(txtCustomerRoles).click();

        WebElement listItem = null;

        if(role.equals("Administrators")){
            listItem = ldriver.findElement(listItemAdministrator);
        } else if (role.equals("Guests")) {
            listItem = ldriver.findElement(listItemGuests);
        } else if (role.equals("Registered")) {
            listItem = ldriver.findElement(listItemRegistered);
        } else if (role.equals("Vendors")) {
            ldriver.findElement(listItemVendors);
        }else {
            listItem = ldriver.findElement(listItemGuests);
        }
        listItem.click();

     /*   JavascriptExecutor js = (JavascriptExecutor) ldriver;
        js.executeAsyncScript("arguments[0].click();", listItem);*/

    }

    public void set_managerOfVendor(String value){
        ldriver.findElement(drpmgrOfVendor).click();
        Select drp = new Select(ldriver.findElement(drpmgrOfVendor));
        drp.selectByVisibleText(value);

    }

    public void setGender (String gender){

        if (gender.equals("Male")){
            ldriver.findElement(rdGenderMale);
        } else if (gender.equals("Female")) {
            ldriver.findElement(rdGenderFemale);
        }else{
            ldriver.findElement(rdGenderMale);
        }
    }

    public void set_adminComment (String adminComment){
        ldriver.findElement(txtAdminComment).sendKeys(adminComment);
    }

    public void click_save(){
        ldriver.findElement(btnSave).click();
    }

    public void setDob (String dob){
        ldriver.findElement(txtDob).sendKeys(dob);
    }



}
