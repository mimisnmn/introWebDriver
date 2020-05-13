package intro;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class YahooSearchMethods {

	static WebDriver driver; // creación del objero driver

	public static void main(String[] args) {
		setUp("chrome", "http://www.yahoo.com"); //llamado a la función de setUp psando los parametros de navegador y url

		searchYahoo("Selenium"); // llamado a función searchYahoo especificando el parametro de tema con el valor selenium
		
		clickLink("Selenium - Web Browser Automation");// Hace llamado a función clickLink con parametro Selenium - Web Browser Automation

		selectPopUp();

		clickLink("Download");// Hace llamado a función clickLink con parametro Download
		
		closeBrowser(); //cerrar navegador

	}

	private static void selectPopUp() {
		ArrayList<String> windowIds = new ArrayList<String>(driver.getWindowHandles());
		System.out.println("Number of windows: " + windowIds.size());

		for(String windowId: driver.getWindowHandles()) { //duda
			driver.switchTo().window(windowId);
		}
		
	}

	private static void clickLink(String linkText) {
		WebElement link = driver.findElement(By.linkText(linkText)); // encontrar elemento link especificando valor por linktext
		link.click(); // dar click en elemento
		
	}

	private static void searchYahoo(String topic) {
		//busqueda
		WebElement searchBox = driver.findElement(By.id("header-search-input")); // Elemento de busqueda, se especifica la función para cuadro de texto de buscar y se especifica por id
		WebElement searchButton = driver.findElement(By.id("header-desktop-search-button"));// Elemento de busqueda, se especifica la función para boton de busqueda y se especifica por id

		searchBox.clear(); //limpiar elemento search searchBox
		searchBox.sendKeys(topic); //se setea el valor del parametro topic searchBox
		searchButton.click(); //Dar click en el botón


	}

	private static void setUp(String browser, String url) { //abrir navegador chrome y posisionar en página yahoo, se pasan valores de navegador y url
		switch(browser) {
		case "chrome":
			//System.setProperty("webdriver.chrome.driver", "/usr/jnavarro/test");
			driver = new ChromeDriver();
			break;
		case "firefox":
			
			driver = new FirefoxDriver();
			break;
		case "ie":
			driver = new InternetExplorerDriver();
			break;
		default:
			System.out.println("Ese browser no existe");
			System.exit(-1);
		}
		driver.manage().timeouts().implicitlyWait(30,  TimeUnit.SECONDS);
		driver.get(url);

	}
	
	private static void closeBrowser() {
		driver.close(); // cerrar navegador
		
	}

}
