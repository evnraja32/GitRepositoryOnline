package opentapsgroup;

import org.testng.annotations.Test;

public class CreateContact extends OpentapsGroupWrapper{
  @Test(groups="Smoke")
  public void createContact() {
	  System.out.println("Create Contact");
  }
}
