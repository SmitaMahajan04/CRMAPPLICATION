package com.comcast.crm.practicetest;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;

public class InvoiceTestForRetryAnalyzer {

	@Test(retryAnalyzer = com.comcast.crm.listenerutility.RetryListenerImplimentation.class)
	public void activateSim() {
		System.out.println("Execute createInvoiceTest");
		// String actTitle=driver.getTitle();
		Assert.assertEquals("", "login");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
	}
}
