package tests;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class InformacoesUsuarioTest {
	private WebDriver driver;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\wilker.nogueira\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("http://WWW.juliodelima.com.br/taskit");
	}

	@Test
	public void testAdicionarUmaInformacaoAdicionalDoUsuario() throws Exception {

		driver.findElement(By.linkText("Sign in")).click();

		WebElement formularioSignInBox = driver.findElement(By.id("signinbox"));

		formularioSignInBox.findElement(By.name("login")).sendKeys("julio0001");

		formularioSignInBox.findElement(By.name("password")).sendKeys("123456");

		// Thread.sleep(2000);
		formularioSignInBox.findElement(By.linkText("SIGN IN")).click();
		
		driver.findElement(By.className("me")).click();
		
		driver.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
		
		driver.findElement(By.xpath("//*[@id=\"moredata\"]/div[2]/button")).click();

		WebElement popupAddMoreData = driver.findElement(By.id("addmoredata"));
		
		//Acessa o combobox e seleciona o texto que o usuário visualiza. 
		WebElement campoType = popupAddMoreData.findElement(By.name("type"));
		new Select(campoType).selectByVisibleText("Phone");
		
		popupAddMoreData.findElement(By.name("contact")).sendKeys("+551166666666");
		
		popupAddMoreData.findElement(By.linkText("SAVE")).click();

	}
	
	@After
	public void tearDown() {
		// driver.quit();
	}

}
