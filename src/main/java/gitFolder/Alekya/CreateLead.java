package realcode;

import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

public class CreateLead extends WrapperRealCode{
	int i = 1;
	@Test(dataProvider = "CreateLeadData",groups="Smoke") 
	public void createLead(String cmpny, String fname, String lname,String dataSource,String campID,String email,String cell) throws InterruptedException {
		
		findWebElementByLinkText("Create Lead");
		clickOnElement();
		
		//==========================================
		findWebElementByID("createLeadForm_companyName");
		sendKeysToWebElement(cmpny);//1

		findWebElementByID("createLeadForm_firstName");
		sendKeysToWebElement(fname);//2

		findWebElementByID("createLeadForm_lastName");
		sendKeysToWebElement(lname);//3

		findWebElementByID("createLeadForm_dataSourceId");
		selectDdElementByVisibleText(dataSource);//4

		findWebElementByID("createLeadForm_marketingCampaignId");
		selectDdElementByVisibleText(campID);//5

		findWebElementByID("createLeadForm_primaryEmail");
		sendKeysToWebElement(email);//6

		findWebElementByID("createLeadForm_primaryPhoneNumber");
		sendKeysToWebElement(cell);//7

		findWebElementByClassName("smallSubmit");
		clickOnElement();
		//=====================
		Thread.sleep(10000);
	}


	@AfterMethod
	public void verifyCreatedLead() {
		System.out.println(getCurrentPageURL().split("=")[1]);
	}
	
	
	 @DataProvider(name = "CreateLeadData")
	  public Object[][] createLeadData(){
		  Object data[][] = new Object[2][7];
	/*	  
		  //row1
		  data[0][0] = "DemoSalesManager"; data[0][1] = "crmsfa"; data[0][2] = "cmp1"; data[0][3] = "fname1"; data[0][4]="lname1"; 

		  //row2
		  data[1][0] = "DemoSalesManager"; data[1][1] = "crmsfa"; data[1][2] = "cmp2"; data[1][3] = "fname2"; data[1][4]="lname2";
		  */
		  
		  for (int row = 1 ; row <= 2 ; row++){
			  for(int column = 3 ; column <= 9 ; column ++ ){
				  data[row-1][column] = readExcel("TestData_main", "CreateLead", row, column);
			  }
		  }
		  
		  
		  return data;
	  }

}
