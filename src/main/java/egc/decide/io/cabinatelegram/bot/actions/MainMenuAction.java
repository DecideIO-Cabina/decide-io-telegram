package egc.decide.io.cabinatelegram.bot.actions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import egc.decide.io.cabinatelegram.bot.util.BotUtils;
import egc.decide.io.cabinatelegram.session.model.UserSession;

@Component
public class MainMenuAction implements DecideBotAction {

	@Autowired
	VoteAction voteAction;
	
	@Autowired
	StartAction startAction;

	@Override
	public BotApiMethod<?> act(Update update, UserSession userSession) throws DecideBotException {
		switch (update.getMessage().getText()) {

		case "Ver votaciones públicas":
		case "Ver mis votaciones":
		case "Votar por id":
			return voteAction.act(update, userSession);
		case "Cerrar sesión":
			userSession.clear();
			return startAction.act(update, userSession);
		default:
			return mainMenu(update);

		}
	}

	private BotApiMethod<?> mainMenu(Update update) {
		ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
		keyboard.setOneTimeKeyboard(true);
		keyboard.setResizeKeyboard(true);

		List<KeyboardRow> rows = new ArrayList<>();
		rows.add(BotUtils.createKeyboardRow("Ver votaciones públicas"));
		rows.add(BotUtils.createKeyboardRow("Ver mis votaciones"));
		rows.add(BotUtils.createKeyboardRow("Votar por id"));
		rows.add(BotUtils.createKeyboardRow("Cerrar sesión"));
		keyboard.setKeyboard(rows);

		return new SendMessage().setChatId(update.getMessage().getChatId()).setReplyMarkup(keyboard)
				.setText("Bienvenido a la cabina de votación de Telegram para Decide");
	}

}
