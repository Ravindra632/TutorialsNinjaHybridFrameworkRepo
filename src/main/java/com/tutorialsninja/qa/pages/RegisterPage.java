package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage 
{
	WebDriver driver;
	
	@FindBy(id="input-firstname")
	private WebElement firstNameField;
	
	@FindBy(id="input-lastname")
	private WebElement lastNameField;
	
	@FindBy(id="input-email")
	private WebElement emailAddressField;
	
	@FindBy(id="input-telephone")
	private WebElement telephoneField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(id="input-confirm")
	private WebElement passwordConfirmField;
	
	@FindBy(name="agree")
	private WebElement privacyPolicyField;
	
	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueButtion;
	
	@FindBy(xpath = "//input[@name='newsletter'][@value='1']")
	private WebElement yesNewsLetterOption;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateEmailAddressWarning;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement privacyPolicyWarning;
	
	@FindBy(xpath="//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarning;
	
	@FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarning;
	
	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	private WebElement emailWarning;
	
	@FindBy(xpath="//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarning;
	
	@FindBy(xpath="//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarning;
	
	public RegisterPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterFirstNameText(String firstNameText)
	{
		firstNameField.sendKeys(firstNameText);
	}
	
	public void enterLastNameText(String lastNameText)
	{
		lastNameField.sendKeys(lastNameText);
	}
	
	public void enterEmailAddress(String emailText)
	{
		emailAddressField.sendKeys(emailText);
	}
	
	public void enterTelephoneNumber(String telephoneNumberText)
	{
		telephoneField.sendKeys(telephoneNumberText);
	}
	
	public void enterPassword(String passwordText)
	{
		passwordField.sendKeys(passwordText);
	}
	
	public void enterConfirmPassword(String confirmPasswordText)
	{
		passwordConfirmField.sendKeys(confirmPasswordText);
	}
	
	public void selectPrivacyPolicy()
	{
		privacyPolicyField.click();
	}
	
	public AccountSuccessPage clickOnContinueButtion()
	{
		continueButtion.click();
		return new AccountSuccessPage(driver);
	}
	
	public void selectNewsLetterOption()
	{
		yesNewsLetterOption.click();
	}
	
	public String retrieveDuplicateEmailAddressWarning()
	{
		return duplicateEmailAddressWarning.getText();
	}
	
	public String retievePrivacyPolicyWarning()
	{
		String privacyPolicyWarningText= privacyPolicyWarning.getText();
		return privacyPolicyWarningText;
	}
	
	public String retrieveFirstNameWarning()
	{
		String firstNameWarningText = firstNameWarning.getText();
		return firstNameWarningText;
	}
	
	public String retrieveLastNameWarning()
	{
		return lastNameWarning.getText();
	}
	
	public String retrieveEmailWarning()
	{
		return emailWarning.getText();
	}
	
	public String retrieveTelephoneWarningText()
	{
		return telephoneWarning.getText();
	}
	
	public String retrievePasswordWarningText()
	{
		return passwordWarning.getText();
	}
	
	public AccountSuccessPage registerWithMandatoryFields(String firstNameText, String lastNameText, String emailText, String telephoneNumberText, String passwordText, String confirmPasswordText)
	{
		firstNameField.sendKeys(firstNameText);
		lastNameField.sendKeys(lastNameText);
		emailAddressField.sendKeys(emailText);
		telephoneField.sendKeys(telephoneNumberText);
		passwordField.sendKeys(passwordText);
		passwordConfirmField.sendKeys(confirmPasswordText);
		privacyPolicyField.click();
		continueButtion.click();
		return new AccountSuccessPage(driver);

	}
	
	public AccountSuccessPage registerWithAllFields(String firstNameText, String lastNameText, String emailText, String telephoneNumberText, String passwordText, String confirmPasswordText)
	{
		firstNameField.sendKeys(firstNameText);
		lastNameField.sendKeys(lastNameText);
		emailAddressField.sendKeys(emailText);
		telephoneField.sendKeys(telephoneNumberText);
		passwordField.sendKeys(passwordText);
		passwordConfirmField.sendKeys(confirmPasswordText);
		yesNewsLetterOption.click();
		privacyPolicyField.click();
		continueButtion.click();
		return new AccountSuccessPage(driver);

	}
	
	public boolean displayStatusOfWarningMessages(String expectedprivacyPolicyWarning, String expectedfirstNameWarning, String expectedlastNameWarning, String expectedEmailWarning, String expectedtelephoneWarning, String expectedpasswordWarning)
	{
		String actualPrivacyPolicyWarningText= privacyPolicyWarning.getText();
		boolean privacyPolicyWarningStatus= actualPrivacyPolicyWarningText.contains(expectedprivacyPolicyWarning);
		
		String actualFirstNameWarningText = firstNameWarning.getText();
		boolean firstNameWarningStatus = actualFirstNameWarningText.equals(expectedfirstNameWarning);
		
		String actualLastNameWarningText = lastNameWarning.getText();
		boolean lastNameWarningStatus = actualLastNameWarningText.equals(expectedlastNameWarning);
	
		String actualEmailWarningText = emailWarning.getText();
		boolean emailWarningStatus = actualEmailWarningText.equals(expectedEmailWarning);
		
		String actualTelephoneWarningText = telephoneWarning.getText();
		boolean telephoneWarningStatus = actualTelephoneWarningText.equals(expectedtelephoneWarning);
		
		String actualPasswordWarningText = passwordWarning.getText();
		boolean passwordWarningStatus = actualPasswordWarningText.equals(expectedpasswordWarning);
		
		return privacyPolicyWarningStatus && firstNameWarningStatus && lastNameWarningStatus && emailWarningStatus && telephoneWarningStatus && passwordWarningStatus;
	}
}
