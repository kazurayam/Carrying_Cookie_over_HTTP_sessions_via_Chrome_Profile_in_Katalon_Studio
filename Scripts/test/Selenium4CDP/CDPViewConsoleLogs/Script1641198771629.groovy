import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v96.log.Log;

import com.kms.katalon.core.webui.driver.DriverFactory

// https://www.qed42.com/insights/coe/quality-assurance/selenium-4-api-chrome-devtools

System.setProperty("webdriver.chrome.driver", DriverFactory.getChromeDriverPath())
ChromeDriver driver = new ChromeDriver();
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

// try to view DevTools Console Logs
DevTools devTools = driver.getDevTools();
devTools.createSession()
devTools.send(Log.enable())

devTools.addListener(Log.entryAdded(), { logEntry ->
	println("-------------------------------------------")
	println("Request ID = " + logEntry.getNetworkRequestId())
	println("URL = "        + logEntry.getUrl())
	println("Source = "     + logEntry.getSource())
	println("Level = "      + logEntry.getLevel())
	println("Text = "       + logEntry.getText())
	println("Timestamp = "  + logEntry.getTimestamp())
	println("-------------------------------------------");
})

driver.get("https://www.qed42.com/404");

driver.quit()
