package testCases;

import org.testng.Assert;
import org.testng.annotations.*;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{

	@Test(groups={"sanity", "master"})
	public void verify_account_registration() {

		logger.info("************* Verify acount registration : TC001_AccountRegistrationTest *************");
		
		try {
		HomePage homePage = new HomePage(driver);
		homePage.clickMyAccount();
		logger.info("Clickied on myaccount : TC001_AccountRegistrationTest");
		homePage.clickRegister();
		logger.info("Clickied on register : TC001_AccountRegistrationTest");
		
		AccountRegistrationPage accRegPage = new AccountRegistrationPage(driver);
		logger.info("Providing customer details : TC001_AccountRegistrationTest");
		
		accRegPage.setFirstName(randomString().toUpperCase());
		accRegPage.setLastName(randomString().toUpperCase());
		accRegPage.setEmail(randomString()+"@gmail.com");
		accRegPage.setTelephone(randomNumber());
		
		String password=randomAlphaNumaric();
		
		accRegPage.setPassword(password);
		accRegPage.setConfirmPassword(password);
		
		accRegPage.setPrivacyPolicy();
		accRegPage.clickContinue();

		logger.info("Validating expected message : TC001_AccountRegistrationTest");
		String actual_Message = accRegPage.getConfirmationMsg();
		Assert.assertEquals(actual_Message, "Your Account Has Been Created!");
		logger.info("************* Executed successfully : TC001_AccountRegistrationTest *************");
		driver.close();
		}
		catch(Exception e) {
			logger.error("Test failed: TC001_AccountRegistrationTest");
			logger.debug("Debug logs..");
			Assert.fail();
		}

	}

}
