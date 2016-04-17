package opentapsgroup;

import org.testng.annotations.Test;

public class DeleteLead extends OpentapsGroupWrapper{
	@Test(groups="Sanity",dependsOnMethods="opentapsgroup.CreateLead.createLead")
	public void deleteLead() {
		System.out.println("Delete Lead");
	}
}
