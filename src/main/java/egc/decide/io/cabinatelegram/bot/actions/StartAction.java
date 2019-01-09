package egc.decide.io.cabinatelegram.bot.actions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import egc.decide.io.cabinatelegram.bot.util.BotUtils;
import egc.decide.io.cabinatelegram.session.model.BotState;
import egc.decide.io.cabinatelegram.session.model.UserSession;

@Component
public class StartAction implements DecideBotAction {

	@Override
	public BotApiMethod<?> act(Update update, UserSession userSession) throws DecideBotException {
		switch(update.getMessage().getText()) {
		case "Iniciar sesión":
			return login(update, userSession);
		case "Ayuda":
			throw new DecideBotException("Lo sentimos, esta acción no está implementada aún");
		default:
			return firstTimeMenu(update);
		}
	}

	private BotApiMethod<?> firstTimeMenu(Update update) {
		ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
		keyboard.setOneTimeKeyboard(true);
		keyboard.setResizeKeyboard(true);
		
		List<KeyboardRow> rows = new ArrayList<>();
		rows.add(BotUtils.createKeyboardRow("Iniciar sesión", "Ayuda"));
		keyboard.setKeyboard(rows);
		
		return new SendMessage().setChatId(update.getMessage().getChatId())
				.setReplyMarkup(keyboard)
				.setText("Bienvenido a la cabina de votación de Telegram para Decide");
	}
	
	private BotApiMethod<?> login(Update update, UserSession userSession) {
		userSession.state(BotState.WAITING_FOR_USERNAME);
		
		return new SendMessage().setChatId(update.getMessage().getChatId())
				.setReplyMarkup(new ForceReplyKeyboard())
				.setText("Introduce tu nombre de usuario de Decide:");
	}

}
