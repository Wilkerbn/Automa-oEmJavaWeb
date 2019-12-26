package tests;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class InformacoesUsuarioTest {

	@Test
	public void testAdicionarUmaInformacaoAdicionalDoUsuario() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\wilker.nogueira\\drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.get("http://juliodelima.com.br/taskit/");
		
		driver.findElement(By.linkText("Sign in")).click();
		
		WebElement formularioSignInBox = driver.findElement(By.id("signinbox"));
		
		formularioSignInBox.findElement(By.name("login")).sendKeys("wilkerbn");
		
		formularioSignInBox.findElement(By.name("password")).sendKeys("9999");
		
		formularioSignInBox.findElement(By.xpath("//*[@id=\"signinbox\"]/div[2]/a")).click();
		
		//driver.close();
		
		assertEquals(1, 1);
	}
	
}
