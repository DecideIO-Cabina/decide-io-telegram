package egc.decide.io.cabinatelegram.bot.actions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;

import egc.decide.io.cabinatelegram.bot.util.SingleButtonKeyboard;
import egc.decide.io.cabinatelegram.rest.client.DecideAuthenticationClient;
import egc.decide.io.cabinatelegram.rest.model.DecideUser;
import egc.decide.io.cabinatelegram.session.model.BotState;
import egc.decide.io.cabinatelegram.session.model.UserSession;

@Component
public class LoginAction implements DecideBotAction {
	
	private static final Log log = LogFactory.getLog(LoginAction.class);
	
	@Autowired
	DecideAuthenticationClient decideAuthenticationClient;

	@Override
	public BotApiMethod<?> act(Update update, UserSession userSession) throws DecideBotException {
		switch (userSession.state()) {
		
		case BotState.WAITING_FOR_USERNAME:
			return getUsername(update, userSession);
		case BotState.WAITING_FOR_PASSWORD:
			return getPassword(update, userSession);
		default:
			throw new DecideBotException();
		
		}		
	}

	private BotApiMethod<?> getUsername(Update update, UserSession userSession) {
		DecideUser tempUser = new DecideUser();
		tempUser.setUsername(update.getMessage().getText());
		userSession.setDecideUser(tempUser);
		userSession.state(BotState.WAITING_FOR_PASSWORD);
		
		return new SendMessage().setChatId(update.getMessage().getChatId())
				.setReplyMarkup(new ForceReplyKeyboard())
				.setText("Introduce tu contraseña de Decide:");
	}

	private BotApiMethod<?> getPassword(Update update, UserSession userSession) throws DecideBotException {

		try {
			String token = decideAuthenticationClient.login(userSession.getDecideUser().getUsername(), update.getMessage().getText());
			userSession.setDecideToken(token);
			
			DecideUser decideUser = decideAuthenticationClient.getUser(token);
			userSession.setDecideUser(decideUser);
			
			String greeting = StringUtils.isEmpty(decideUser.getFirstName()) ? 
					decideUser.getUsername() : decideUser.getFirstName() + " " + decideUser.getLastName();
			
			userSession.state(BotState.MAIN_MENU);
					
			return new SendMessage().setChatId(update.getMessage().getChatId())
					.setReplyMarkup(new SingleButtonKeyboard("Comenzar"))
					.setText("Bienvenido, " + greeting);
		} catch (HttpClientErrorException e) {
			userSession.state(BotState.ANONYMOUS);
			log.error(e.getMessage());
			if (e.getRawStatusCode() == 400)
				throw new DecideBotException("Lo siento, los datos de inicio de sesión no son correctos");
			else
				throw new DecideBotException("Lo siento, no he podido conectar con Decide");
		}
	}

}
