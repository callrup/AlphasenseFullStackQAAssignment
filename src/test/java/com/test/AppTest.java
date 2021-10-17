package com.test;

import com.alphasense.base.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import com.alphasense.base.App;
import com.aventstack.extentreports.Status;
import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
import com.alphasense.pojoClasses.*;
import com.alphasense.base.*;
import static com.alphasense.base.Constants.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;

public class AppTest 
    
{
	WebDriver driver;
 App objPet;
 
 PetsStoreController petsController;
 Pet pet = new Pet.Builder()
         .withId(RandomStringUtils.randomNumeric(1))
         .withName("My pet").build();
 
 @BeforeClass
 public void beforeClass() {
     petsController = new PetsStoreController();
 }
 
	@BeforeTest
	public void beforeTest() throws IOException {
		String driverPath = System.getProperty("user.dir") 
					+ File.separator
					+ "drivers"
					+ File.separator
					+ "chromedriver.exe"; // add .exe for windows
System.setProperty("webdriver.chrome.driver", driverPath);
	driver = new ChromeDriver();
	driver.get(BASE_URL);
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	}

	@Test(priority=0)
	public void testAut() throws InterruptedException, IOException {
		ExtentTestManager.getTest().log(Status.INFO, "Hellooo started UI Test");
		objPet = new App(driver);
	
		File inFile = new File("src\\test\\java\\resources\\jsons\\pet.txt");
	    StringBuilder targetString = new StringBuilder("");
	    try {
	        FileReader fr = new FileReader(inFile);
	        BufferedReader br = new BufferedReader(fr);
	
	        String s = null;
	        while ((s = br.readLine()) != null) {
	            targetString.append(s);
	            br.close();
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    objPet.clickAddPetApi();
	    objPet.clickTryitOut();
	    //WebDriverWait wait = new WebDriverWait(driver,10);
	   objPet.textAreaAppend();
	    driver.findElement(By.xpath("//textarea[@class='body-param__text']")).sendKeys(targetString);
	    objPet.clickExecuteButton();
	    
	}
	
	@Test(priority = 1)
    public void verifyNewPet() {
		ExtentTestManager.getTest().log(Status.INFO, "Verify newly added Pet");
        Pet petResponse = petsController.findPet(pet);
        assertThat(petResponse, is(samePropertyValuesAs(pet)));
    }
	
	   @Test(priority = 2)
	    public void deletePetAndDoCheck() {
		   ExtentTestManager.getTest().log(Status.INFO, "Delete and check");
	        petsController.deletePet(pet);
	        petsController.verifyPetDeleted(pet);
	    }

	@AfterTest
	public void afterTest() {
	driver.close();
	}

	
	



	
	
	
}
