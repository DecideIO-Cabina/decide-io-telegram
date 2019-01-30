package egc.decide.io.cabinatelegram;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.telegram.telegrambots.ApiContextInitializer;

import egc.decide.io.cabinatelegram.bot.actions.DecideBotException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CabinaTelegramApplicationTests extends BaseTest {
	
	static final String testUsername = "votante";
	static final String testPassword = "contraseña";
	
	static {
		ApiContextInitializer.init();
	}

	@Before
	public void reset() {
		clearSession();
	}
	
	@Test
	public void loginTest() throws IllegalAccessException, DecideBotException {
		expectContainsAndMarkup(act("/start"), "Bienvenido");
		
		expectContainsAndMarkup(act("Iniciar sesión"), "Introduce tu nombre");
		
		expectContainsAndMarkup(act(testUsername), "Introduce tu contraseña");
		
		expectContains(act(testPassword), "Votante");
	}
	
	@Test(expected=DecideBotException.class)
	public void loginFailureTest() throws IllegalAccessException, DecideBotException {
		expectContainsAndMarkup(act("/start"), "Bienvenido");
		
		expectContainsAndMarkup(act("Iniciar sesión"), "Introduce tu nombre");
		
		expectContainsAndMarkup(act("asdasdasdasdad"), "Introduce tu contraseña");
		
		expectContains(act("asdasdadasd"), "no son correctos");
	}

}
