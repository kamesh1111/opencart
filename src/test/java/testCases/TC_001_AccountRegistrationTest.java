package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass
{
	//public WebDriver driver;
	
	@Test(groups= {"regression","master"})
	public void verify_account_registration() throws InterruptedException
	{
		
		logger.info("***starting TC_001_AccountRegistrationTest ***");
		logger.debug("Application log started...");
		
		try
		{
			HomePage hp=new HomePage(driver);
			hp.clickMyAccount();
			logger.info("clicked on MyAccount link");
			hp.clickRegister();
			logger.info("clicked on Registration link");
			
			logger.info("Entering customer details...");
			
			AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
			
			regpage.setFirstName(randomString().toUpperCase());
			//Thread.sleep(10000);
			regpage.setLastName(randomString().toUpperCase());
			regpage.setEmail(randomString()+"@gmail.com"); //randomly generted the email
			regpage.setTelephone(randomNumber());
			
			String password=randomAlphaNumeric();
			
			regpage.setPassword(password);
			regpage.setConfirmPassword(password);
			
			regpage.setPrivacyPolicy();
			//Thread.sleep(5000);
			regpage.clickContinue();
			logger.info("clicked on continue...");
			
			String confmsg=regpage.getConfirmationMsg();
			
			logger.info("validating expected message...");
			
			if(confmsg.equals("Your Account Has Been Created!"))
			{
				logger.info("test passed...");
				Assert.assertTrue(true);
			}
			else
			{
				logger.error("test failed...");
				Assert.fail();
			}
			//Assert.assertEquals(confmsg, "Your Account Has Been Created!");
			
		}
		catch(Exception e)
		{
			logger.error("test failed...");
			Assert.fail();
		}
		
		logger.debug("Application log ended...");
		
		logger.info("***finished TC_001_AccountRegistrationTest ***");
		
		
	}
	

}
