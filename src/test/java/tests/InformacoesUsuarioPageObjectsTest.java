package tests;


import static org.junit.Assert.assertEquals;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import pages.LoginPage;
import suporte.Web;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformacoesUsuarioTestData.csv")
public class InformacoesUsuarioPageObjectsTest {
	private WebDriver driver;

	@Before
	public void setUp() {
		driver = Web.createChrome();
	}

	@Test
	public void testAdicionarUmaInformacaoAdicionalDoUsuario(
			@Param(name="login")String login,
			@Param(name="senha")String senha,
			@Param(name="tipo")String tipo,
			@Param(name="contato")String contato,
			@Param(name="mensagem")String mensagemEsperada
	) throws Exception
	{
		
		String textoToast = new LoginPage(driver)
				.clicarSignIn()
				.fazerLogin(login, senha)
				.clicarMe()
				.clicarAbaMoreDataAboutYou()
				.clicarBotaoAddMoreDataAboutYou()
				.adicionarContato(tipo, contato)
				.capturarTextoToast();
		
		assertEquals(mensagemEsperada, textoToast);
				
	}

	@After
	public void tearDown() {
		driver.quit();
	}
}
