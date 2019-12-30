package tests;

import static org.junit.Assert.assertEquals;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import suporte.Generator;
import suporte.Screenshot;
import suporte.Web;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformacoesUsuarioTestData.csv")
public class InformacoesUsuarioTest {
	private WebDriver driver;
	
	@Rule
	public TestName test = new TestName();

	@Before
	public void setUp() throws Exception {
		
		//Page Object referenciando aos comandos Web alocados na pasta Suporte 
		driver = Web.createChrome();

		WebElement formularioSignInBox = driver.findElement(By.id("signinbox"));

		formularioSignInBox.findElement(By.name("login")).sendKeys("julio0001");

		formularioSignInBox.findElement(By.name("password")).sendKeys("123456");

		//Thread.sleep(2000);
		formularioSignInBox.findElement(By.linkText("SIGN IN")).click();
		
		driver.findElement(By.className("me")).click();
		
		driver.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
	}

	@Test
	public void testAdicionarUmaInformacaoAdicionalDoUsuario(@Param(name="tipo")String tipo, @Param(name="contato")String contato, @Param(name="mensagem")String mensagemEsperada) throws Exception {

		//Clicar no botão através do xpath
		driver.findElement(By.xpath("//*[@id=\"moredata\"]/div[2]/button")).click();
		
		//Identificar a popup onde está o formulário de id addmoredata
		WebElement popupAddMoreData = driver.findElement(By.id("addmoredata"));
		
		//Acessa o combobox e seleciona o texto que o usuário visualiza. 
		WebElement campoType = popupAddMoreData.findElement(By.name("type"));
		new Select(campoType).selectByVisibleText(tipo);
		
		// no campo de name "contact" digitar "551166666666"
		popupAddMoreData.findElement(By.name("contact")).sendKeys(contato);
		
		// Clicar no link de text "SAVE" que está na popup
		popupAddMoreData.findElement(By.linkText("SAVE")).click();
		
		//Na mensagem de if"toast-container" validar que o texto é "Your contact has been added!"
		WebElement mensagemPop = driver.findElement(By.id("toast-container"));
		String mensagem = mensagemPop.getText();
		assertEquals(mensagemEsperada, mensagem);
		
		//Tira foto do teste após a mensagem.
		String screenshotArquivo = "/Users/wilker.nogueira/test-report/taskit/" + Generator.dataHoraParaArquivo() + test.getMethodName() + ".png";
		Screenshot.tirar(driver, screenshotArquivo); 

	}
	
	@Test
	public void removerUmContatoDeUmUsuario() {
		
		//Clicar no elemento pelo seu xpath //span[text()='+551166666666']/following-sibling::a comando para localizar elemento
		WebElement excluirTelefone = driver.findElement(By.xpath("//span[text()='+551166666666']/following-sibling::a"));
		excluirTelefone.click();
		
		//Confirmar a janela javascript
		driver.switchTo().alert().accept();
		
		//Validar que a mensagem apresentada foi Rest in peace, dear phone!
		WebElement mensagemPop = driver.findElement(By.id("toast-container"));
		String mensagem = mensagemPop.getText();
		assertEquals("Rest in peace, dear phone!", mensagem);
		
		String screenshotArquivo = "/Users/wilker.nogueira/test-report/taskit/" + Generator.dataHoraParaArquivo() + test.getMethodName() + ".png";
		Screenshot.tirar(driver, screenshotArquivo); 
		
		//Aguardar até 10 segundos para que a janela desapareça
		WebDriverWait aguardar = new WebDriverWait(driver, 10);
		aguardar.until(ExpectedConditions.stalenessOf(mensagemPop));
		
		//Fazer logout
		driver.findElement(By.linkText("Logout")).click();
		
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}

}
