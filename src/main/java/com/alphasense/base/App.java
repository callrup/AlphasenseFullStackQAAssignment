package com.alphasense.base;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.WebDriver;
public class App 
{
	WebDriver driver;

    By addPetStore = By.xpath("//div[normalize-space()='Add a new pet to the store']");
    By addPetTryitOutButton = By.xpath("//button[@class='btn try-out__btn']");
    By addPetTextAreaPayload = By.xpath("//textarea[@class='body-param__text']");
    By executeButton = By.cssSelector("[id='operations-pet-addPet'] button[class='btn execute opblock-control__btn']");
    
    public App(WebDriver driver){

        this.driver = driver;

    }
    
    
  //Click on login button

    public void clickAddPetApi(){

            driver.findElement(addPetStore).click();
}
    
    public void clickTryitOut()
    {
    	driver.findElement(addPetTryitOutButton).click();
    }
    
    
    public void clickExecuteButton()
    {
    	driver.findElement(executeButton).click();
    }
    
    public void textAreaAppend()
    {
    	driver.findElement(addPetTextAreaPayload).clear();
    }
}



