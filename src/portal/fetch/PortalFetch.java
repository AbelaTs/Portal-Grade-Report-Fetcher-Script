/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portal.fetch;

/**
 *
 * @author Abela
 */
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
public class PortalFetch {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Abela\\Documents\\NetBeansProjects\\Selenium\\src\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        String PortalUrl = "https://portal.aait.edu.et/";
        driver.get(PortalUrl);

        WebElement userName = driver.findElement(By.id("UserName"));
        WebElement password = driver.findElement(By.id("Password"));
        WebElement signingButton = driver.findElement(By.id("home")).findElement(By.className("btn"));

        userName.sendKeys("ATR/2391/09");
        password.sendKeys("####");
        signingButton.click();


        String title = driver.getTitle();
        int titleLength = driver.getTitle().length();
        System.out.println("Page title : " + title);
        System.out.println("Title Length : "+ titleLength);
        String actualUrl = driver.getCurrentUrl();

        driver.findElement(By.id("m2")).click();
        driver.navigate().to("https://portal.aait.edu.et/Grade/GradeReport");
        //Fetch the grade report

        String value  = driver.findElement(By.className("table")).getText();

        ExportToWordFile(value);

        Verify(actualUrl, PortalUrl, driver);
    }
    //Method for exporting grade report in to text file
     public static void ExportToWordFile(String gradeReport){
        try (PrintWriter writer = new PrintWriter("GradeReport.txt")) {
            writer.println(gradeReport);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void Verify(String actualUrl, String url, WebDriver driver){
        if (actualUrl.equals(url)){
            System.out.println("Verification Successful - The correct Url is opened.");
        } else {
            System.out.println("Verification Failed - An incorrect Url is opened.");
            //In case of Fail, you like to print the actual and expected URL for the record purpose
            System.out.println("Actual URL is : " + actualUrl);
            System.out.println("Expected URL is : " + url);
        }

        String pageSource = driver.getPageSource();
        int pageSourceLength = pageSource.length();
        System.out.println("Total length of the Page Source is : " + pageSourceLength);
    }

   
    
}
