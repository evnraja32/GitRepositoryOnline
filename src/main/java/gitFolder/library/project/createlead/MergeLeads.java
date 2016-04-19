package createlead;

import org.junit.Test;

public class MergeLeads extends ProjectWrapper {
	
	@Test
	public void  testCase4MergeLeads() throws InterruptedException{

		launchApp("firefox", "http://demo1.opentaps.org/");

		findWebElementByID("username");
		sendKeysToWebElement("DemoSalesManager");

		findWebElementByID("password");
		sendKeysToWebElement("crmsfa");

		findWebElementByCssSelector("input.decorativeSubmit");
		clickOnElement();

		findWebElementByLinkText("CRM/SFA");
		clickOnElement();

		Thread.sleep(10000);

		findWebElementByLinkText("Leads");
		clickOnElement();

		Thread.sleep(10000);

		findWebElementByLinkText("Merge Leads");
		clickOnElement();

		//========================
		String leadName[] = {"From Lead","To Lead"};

		for(int i = 0 ; i <= 1 ; i++ ){
			findWebElementByXpath("//td[@class='titleCell']/span[text()='"+leadName[i]+"']/following::img");
			clickOnElement();

			prepareAllWindowHandlers();

			switchToLastWindow();

			Thread.sleep(5000);

			findWebElementByName("id");
			String data = readExcel("TestData_main","Merge", 1, i).getStringCellValue();
			sendKeysToWebElement(data+"\n");

			Thread.sleep(5000);

			findWebElementByLinkText(data);
			clickOnElement();

			switchToPrimaryWindow();
		}

		findWebElementByLinkText("Merge");
		clickOnElement();

		switchToWindowAlert();
		manageAlert("accept");

		Thread.sleep(10000);

		findWebElementByLinkText("Find Leads");
		clickOnElement();

		Thread.sleep(10000);

		findWebElementByName("id");
		String data = readExcel("TestData_main","Merge", 1, 0).getStringCellValue();
		sendKeysToWebElement(data+"\n");

		Thread.sleep(10000);

		findWebElementByClassName("x-paging-info");
		if(getWebElement().getText().equals("No records to display")){
			System.out.println("The item "+data+" is not displayed");
		}else if (getWebElement().getText().contains("Displaying records")) {
			System.out.println("The item "+data+" is displayed");
		}

				closeTheBrowser();
		//		System.out.println(driver.getTitle());


	}


}










