/****
 * This is the TestSuite Base for Company Setup configuration.
 * This Class extends the Test Base Class
 * Class has Before & After Suite method to connect/Disconnect Database
 * Class has Before Suite method to get Company setup jersey Client.
 * This is a must file for Company setup testNg script to execute & should not be deleted.
 */
package com.qa.automation.falconRestAPI.testsuite;

import java.util.Properties;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * 
 *         SampletestSuiteBase class holds the services common for all the
 *         scripts in the suite
 */
public class TestSuiteBase {

	/**
	 * Properties File holds common values which are used across all the scripts
	 */
	public static Properties CONFIG = null;

	/*
	 * Any instantiation is required may be done using constructor
	 */
	public TestSuiteBase() {

	}

	/*
	 * BeforeSuite process identified here Passing following parameters is must
	 * in the suite execution process
	 * 
	 * Sample code provided
	 */

	@BeforeSuite
	public void preSetup() {
		// USE THIS METHOD TO WRITE PRESETUP OPERATIONS IF ANY TO DO BEFORE
		// SUITE RUNS
	}

	@AfterSuite
	public void teardown() {
		// USE THIS METHOD TO WRITE OPERATIONS IF ANY TO DO AFTER SUITE
	}

}