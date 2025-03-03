package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AccountRegistrationPage extends BasePage {
	
	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}

	// Locators
	@FindBy(how = How.XPATH, using = "//input[@id='input-firstname']")
	WebElement txtFirstname;

	@FindBy(how = How.XPATH, using = "//input[@id='input-lastname']")
	WebElement txtLastname;

	@FindBy(how = How.XPATH, using = "//input[@id='input-email']")
	WebElement txtEmail;

	@FindBy(how = How.XPATH, using = "//input[@id='input-telephone']")
	WebElement txtTelephone;

	@FindBy(how = How.XPATH, using = "//input[@id='input-password']")
	WebElement txtPassword;

	@FindBy(how = How.XPATH, using = "//input[@id='input-confirm']")
	WebElement txtConfirmPassword;

	@FindBy(how = How.XPATH, using = "//input[@name='agree']")
	WebElement chkPolicy;

	@FindBy(how = How.XPATH, using = "//input[@value='Continue']")
	WebElement btnContinue;

	@FindBy(how = How.XPATH, using = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;

	// a[normalize-space()='Continue']

	// Action methods
	public void setFirstName(String fname) {
		txtFirstname.sendKeys(fname);
	}

	public void setLastName(String lname) {
		txtLastname.sendKeys(lname);
	}

	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}

	public void setTelephone(String tel) {
		txtTelephone.sendKeys(tel);
	}

	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}

	public void setConfirmPassword(String pwd) {
		txtConfirmPassword.sendKeys(pwd);
	}

	public void setPrivacyPolicy() {
		chkPolicy.click();
	}

	public void clickContinue() {
		btnContinue.click();
	}

	public String getConfirmationMsg() {
		try {
			return (msgConfirmation.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}

	}
	

}
