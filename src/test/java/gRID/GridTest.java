package gRID;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class GridTest {
	@Test
	public void GridBrowserlaunch() throws MalformedURLException {
		DesiredCapabilities cap =DesiredCapabilities.firefox();
		cap.setPlatform(Platform.WINDOWS);
		URL url= new URL("http://55.55.52.152:4444/grid/console");
		WebDriver driver=new RemoteWebDriver(url, cap);
		driver.get("http://google.com");

	}

}
