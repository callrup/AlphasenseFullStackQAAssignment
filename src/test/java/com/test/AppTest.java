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




public class AppTest 
    
{
	WebDriver driver;
 App objPet;
	@BeforeTest
	public void beforeTest() throws IOException {
		String driverPath = System.getProperty("user.dir") 
					+ File.separator
					+ "drivers"
					+ File.separator
					+ "chromedriver"; // add .exe for windows
System.setProperty("webdriver.chrome.driver", driverPath);
	driver = new ChromeDriver();
	driver.get("http://petstore.swagger.io/");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	}

	@Test(priority=0)
	public void testAut() throws InterruptedException, IOException {
		ExtentTestManager.getTest().log(Status.INFO, "Hellooo started test1");
		objPet = new App(driver);
	
		File inFile = new File("src\\test\\java\\resources\\jsons\\pet.txt");
	    StringBuilder targetString = new StringBuilder("");
	    try {
	        FileReader fr = new FileReader(inFile);
	        BufferedReader br = new BufferedReader(fr);
	
	        String s = null;
	        while ((s = br.readLine()) != null) {
	            targetString.append(s);
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

	@AfterTest
	public void afterTest() {
	driver.close();
	}

	
	



	
	
	
}
