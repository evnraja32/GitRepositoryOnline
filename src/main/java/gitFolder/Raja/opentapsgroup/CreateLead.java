package opentapsgroup;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateLead extends OpentapsGroupWrapper{
  
	
  @Test(dataProvider = "CreateLeadData",groups="Smoke")
  public void createLead(String loginName, String pass,String cmpny, String fname, String lname) {
	  System.out.println("Loged in as "+loginName+" Password "+pass+" fname "+fname+" lname: "+lname+" cmpny "+cmpny);
	  
  }
  
  @DataProvider(name = "CreateLeadData")
  public Object[][] createLeadData(){
	  Object data[][] = new Object[2][5];
	  
	  //row1
	  data[0][0] = "DemoSalesManager"; data[0][1] = "crmsfa"; data[0][2] = "cmp1"; data[0][3] = "fname1"; data[0][4]="lname1"; 

	  //row2
	  data[1][0] = "DemoSalesManager"; data[1][1] = "crmsfa"; data[1][2] = "cmp2"; data[1][3] = "fname2"; data[1][4]="lname2";
	  
	  
	  return data;
  }
}
