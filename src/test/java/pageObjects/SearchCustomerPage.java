package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitHelper;

import java.time.Duration;
import java.util.List;

public class SearchCustomerPage {
     public WebDriver ldriver;
     WaitHelper waithelper;
     public SearchCustomerPage(WebDriver rdriver){
         ldriver = rdriver;
         PageFactory.initElements(rdriver,this);
         waithelper = new WaitHelper(ldriver);

     }

     @FindBy (how = How.ID, using = "SearchEmail")
     @CacheLookup
     WebElement  Email;


     @FindBy (id = "SearchFirstName")
     @CacheLookup
     WebElement Name;

     @FindBy (id = "SearchLastName")
     @CacheLookup
     WebElement lastName;

     @FindBy (id = "search-customers")
     @CacheLookup
     WebElement searchButton;

     @FindBy (xpath = "//table[@id='customers-grid']")
     WebElement table;

     @FindBy (xpath = "//table[@id='customers-grid']//tbody/tr")
     List <WebElement> tableRows;

     @FindBy(xpath = "//table[@id='customers-grid']//tbody/tr/td")
     List <WebElement> tableColumns;

     public void setSearchEmail (String email){

          Email.clear();
          Email.sendKeys(email);
     }

     public void clickSearch(){
          searchButton.click();
     }

     public boolean searchCustomerByEmail(String email){
          boolean flag = false;
          for (int i = 1; i<= tableRows.size();i++){
               String emailId = table.findElement
                       (By.xpath("//table[@id='customers-grid']//tbody/tr["+i+"]/td[2]")).getText();
               if(emailId.equals(email)){
                    flag = true;
               }
          }
          return flag;
     }

     public boolean searchResult(String email){
          boolean flag = false;
          for(WebElement elm:tableColumns){
               if(elm.getText().equals(email)){
                    System.out.println("Email is found");
                    flag = true;
                    break;
               }
          }
          return flag;
     }

     public void addName (String name){
          Name.sendKeys("John");
     }

     public void addLastName (String lName){
          lastName.sendKeys(lName);
     }

     public boolean searchByName (String fAndLName){
          boolean flag = false;
          for(int i = 1;i<= tableRows.size();i++){
               String name = table.findElement
                       (By.xpath("//table[@id='customers-grid']//tbody/tr["+i+"]/td[3]")).getText();
               String [] fn = fAndLName.split(" ");
               if(fn[0].equals("John") && fn[1].equals("Smith")){
                    flag = true;
               }
          }
          return flag;
     }

}
