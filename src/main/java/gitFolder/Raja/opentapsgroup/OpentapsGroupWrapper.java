package opentapsgroup;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class OpentapsGroupWrapper {

	@BeforeMethod
	public void loginClickOnCreateLead() {
		System.out.println("Click and login to CRM => Clicked on Create Lead");
	}


	@BeforeClass
	@Parameters("browser")
	public void launchBrowser(String browser) {
		System.out.println("Browser has launched in : "+browser);
	}

	@AfterClass
	public void closeBrowser() {
		System.out.println("Browser closed");
	}

}
