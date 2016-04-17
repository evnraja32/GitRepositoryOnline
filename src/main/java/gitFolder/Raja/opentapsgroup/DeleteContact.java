package opentapsgroup;

import org.testng.annotations.Test;

public class DeleteContact extends OpentapsGroupWrapper{
	@Test(groups="Sanity",dependsOnMethods="opentapsgroup.CreateContact.createContact")
	public void deleteContact() {
		System.out.println("Delete Contact");
	}
}
