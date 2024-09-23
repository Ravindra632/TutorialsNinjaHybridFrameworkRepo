package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base{
	
	public WebDriver driver;
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;
	
	public RegisterTest()
	{
		super();
	}
	@BeforeMethod
	public void setUp()
	{

		driver = intializeBrowserAndOpenApplicationURL("edge");
		
		HomePage homePage = new HomePage(driver);
		registerPage = homePage.navigateToRegisterPage();
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	@Test(priority = 1)
	public void verifyRegisteringAnAccountWithMandatoryFields()
	{
		accountSuccessPage = registerPage.registerWithMandatoryFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephoneNumber"), prop.getProperty("validpassword"), prop.getProperty("validpassword"));
			
		//String actualSuccessHeading = accountSuccessPage.retieveAccountSuccessPageHeading(); 
		Assert.assertEquals(accountSuccessPage.retieveAccountSuccessPageHeading(), dataProp.getProperty("accountSuccessfullyCreatedHeading"),"account success page is not displayed");
	}
	
	
	@Test(priority = 2)
	public void verifyRegisteringAnAccountByProvidingAllFields()
	{
		accountSuccessPage = registerPage.registerWithAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephoneNumber"), prop.getProperty("validpassword"), prop.getProperty("validpassword"));
		//accountSuccessPage = registerPage.registerWithAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephoneNumber"), prop.getProperty("validpassword"), prop.getProperty("validpassword"));
			
		//String actualSuccessHeading = accountSuccessPage.retieveAccountSuccessPageHeading(); 
		Assert.assertEquals(accountSuccessPage.retieveAccountSuccessPageHeading(), dataProp.getProperty("accountSuccessfullyCreatedHeading"),"account success page is not displayed");
	}
	
	@Test(priority = 3)
	public void verifyRegisteringAnAccountWithExistingEmailAddress()
	{
		registerPage.registerWithAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), prop.getProperty("validemail"), dataProp.getProperty("telephoneNumber"), prop.getProperty("validpassword"), prop.getProperty("validpassword"));

		//String actualWarning = registerPage.retrieveDuplicateEmailAddressWarning();
		
		Assert.assertTrue(registerPage.retrieveDuplicateEmailAddressWarning().contains(dataProp.getProperty("duplicateEmailWarning")), "warning message of duplicate email address is not displayed" );
		
	}
	
	@Test(priority = 4)
	public void verifyRegisteringAccountWithoutFillingAnyFields()
	{
		registerPage.clickOnContinueButtion();
		Assert.assertTrue(registerPage.displayStatusOfWarningMessages(dataProp.getProperty("privacyPolicyWarning"), dataProp.getProperty("firstNameWarning"), dataProp.getProperty("lastNameWarning"), dataProp.getProperty("emailWarning"), dataProp.getProperty("telephoneWarning"), dataProp.getProperty("passwordWarning")), "warning message(s) is not displayed");
	}
	

}
