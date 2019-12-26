package tests;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class InformacoesUsuarioTest {

	@Test
	public void testAdicionarUmaInformacaoAdicionalDoUsuario() throws Exception {
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\wilker.nogueira\\drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.get("http://WWW.juliodelima.com.br/taskit");
		
		driver.findElement(By.linkText("Sign in")).click();
		
		WebElement formularioSignInBox = driver.findElement(By.id("signinbox"));
		
		formularioSignInBox.findElement(By.name("login")).sendKeys("julio0001");
		
		formularioSignInBox.findElement(By.name("password")).sendKeys("123456");
		
		Thread.sleep(2000);
		formularioSignInBox.findElement(By.linkText("SIGN IN")).click();
		
		//driver.close();
		
		
	}
	
}
