package com.kazurayam.ks.clientsidestorage

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

import java.nio.file.Files
import java.nio.file.Path

import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeOptions

import com.kazurayam.junit4ks.IgnoreRest
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * @author kazurayam
 *
 */
@RunWith(JUnit4.class)
public class ChromeDriverFactoryTest {

	@Before
	void setup() {}


	@Test
	void test_getUserDataDirectory() {
		Path userDataDirectory = ChromeDriverFactory.getChromeUserDataDirectory()
		assertThat(Files.exists(userDataDirectory), is(true))
	}


	@Test
	void test_getChromeDriverPath() {
		Path chromeDriverPath = ChromeDriverFactory.getChromeDriverPath()
		assertThat(chromeDriverPath, is(notNullValue()))
		println "chromeDriverPath=\"${chromeDriverPath.toString()}\""
		assertTrue(Files.exists(chromeDriverPath))
	}


	@Test
	void test_myChromeOptions() {
		ChromeDriverFactory cdFactory = new ChromeDriverFactory()
		Map<String, Object> chromePreferences = cdFactory.getChromePreferencesResolver().resolveChromePreferences()
		ChromeOptions cp = cdFactory.getChromeOptionsResolver().resolveChromeOptions(chromePreferences)
		String cpJson = cp.toJsonText()
		//println "#test_defaultChromeOpitons cp=${cpJson}"
		assertTrue(cpJson.length() > 0)
	}


	@Test
	void test_openChromeDriver() {
		ChromeDriverFactory cdFactory = new ChromeDriverFactory()
		WebDriver driver = cdFactory.openChromeDriver()
		assertThat(driver, is(notNullValue()))
		DriverFactory.changeWebDriver(driver)
		WebUI.navigateToUrl('http://demoaut.katalon.com/')
		WebUI.delay(3)
		WebUI.closeBrowser()
	}


	@Test
	void test_openChromeDriverWithProfile() {
		ChromeDriverFactory cdFactory = new ChromeDriverFactory()
		WebDriver driver = cdFactory.openChromeDriverWithProfile('Katalon')
		assertThat(driver, is(notNullValue()))
		DriverFactory.changeWebDriver(driver)
		WebUI.navigateToUrl('http://demoaut.katalon.com/')
		WebUI.delay(3)
		WebUI.closeBrowser()
	}


	@Test
	void test_openChromeDriverWithProfileDirectory() {
		ChromeDriverFactory cdFactory = new ChromeDriverFactory()
		WebDriver driver = cdFactory.openChromeDriverWithProfileDirectory('Default')
		assertThat(driver, is(notNullValue()))
		DriverFactory.changeWebDriver(driver)
		WebUI.navigateToUrl('http://demoaut.katalon.com/')
		WebUI.delay(3)
		WebUI.closeBrowser()
	}
}