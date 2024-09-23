package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;


public class LoginTest extends Base {
	
	public WebDriver driver;
	LoginPage loginPage;
	
	
	public LoginTest()
	{
		super();
	}
	@BeforeMethod
	public void setUP()
	{
		driver = intializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homepage= new HomePage(driver);
		loginPage = homepage.navigateToLoginPage();
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
	@Test(priority = 1,dataProvider ="validCredentialsSupplier")
	public void verfiyLoginWithValidCredentials(String email, String password)
	{

		
		AccountPage accountPage= loginPage.login(email, password);
		Assert.assertTrue(accountPage.getDisplayStatusOfAccountInformationOption(),"Account page not displayed");
	}
	
	@DataProvider(name="validCredentialsSupplier")
	public Object[][] supplyTestData()
	{
		Object[][] data= Utilities.getTestDataFromExcel("Login");
		return data;
	}
	
	@Test(priority = 2)
	public void verfiyLoginWithInvalidCredential()
	{
		loginPage.login(Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("invalidPassword"));
//		String actualWarningMessage = loginPage.retieveEmailPasswordNotMatchingWarningText();
//		String expectedWarningMessage= dataProp.getProperty("emailPasswordNoMatchWarning");
		
		Assert.assertTrue(loginPage.retieveEmailPasswordNotMatchingWarningText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")), "expected warning message not desplayed");
	}
	
	@Test(priority = 3)
	public void verifyLoginWithInvalidEmailandValidPassword()
	{
		loginPage.login(Utilities.generateEmailWithTimeStamp(), prop.getProperty("validpassword"));
				
//		String actualWarningMessage = loginPage.retieveEmailPasswordNotMatchingWarningText();
//		String expectedWarningMessage= dataProp.getProperty("emailPasswordNoMatchWarning");
//		
		Assert.assertTrue(loginPage.retieveEmailPasswordNotMatchingWarningText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")), "expected warning message not desplayed");
	}
	
	@Test(priority = 4)
	public void verifyLoginWithValidEmailAndInvalidPassword()
	{
		loginPage.login(prop.getProperty("validemail"), dataProp.getProperty("invalidPassword"));
//		String actualWarningMessage = loginPage.retieveEmailPasswordNotMatchingWarningText();
//		String expectedWarningMessage= dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(loginPage.retieveEmailPasswordNotMatchingWarningText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")), "expected warning message not desplayed");
	}
	
	
	@Test(priority = 5)
	public void verifyLoginWithoutProvidingCredentials()
	{
		loginPage.clickOnLoginButtion();
				
//		String actualWarningMessage = loginPage.retieveEmailPasswordNotMatchingWarningText();
//		String expectedWarningMessage= dataProp.getProperty("emailPasswordNoMatchWarning");		
	
		Assert.assertTrue(loginPage.retieveEmailPasswordNotMatchingWarningText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")), "expected warning message not desplayed");
	}
	
	
	

}
