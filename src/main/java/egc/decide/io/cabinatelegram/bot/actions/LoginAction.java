package egc.decide.io.cabinatelegram.bot.actions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;

import egc.decide.io.cabinatelegram.rest.client.DecideLoginClient;
import egc.decide.io.cabinatelegram.session.model.BotState;
import egc.decide.io.cabinatelegram.session.model.UserSession;

@Component
public class LoginAction implements DecideBotAction {
	
	@Autowired
	DecideLoginClient decideLoginClient;

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
		userSession.setDecideUsername(update.getMessage().getText());
		userSession.state(BotState.WAITING_FOR_PASSWORD);
		
		return new SendMessage().setChatId(update.getMessage().getChatId())
				.setReplyMarkup(new ForceReplyKeyboard())
				.setText("Introduce tu contraseña de Decide:");
	}

	private BotApiMethod<?> getPassword(Update update, UserSession userSession) {
		System.out.println(userSession.getDecideUsername());
		System.out.println(update.getMessage().getText());
		
		userSession.state(BotState.ANONYMOUS);
		decideLoginClient.login(userSession.getDecideUsername(), update.getMessage().getText());
		
		return null; //TODO
	}

}
