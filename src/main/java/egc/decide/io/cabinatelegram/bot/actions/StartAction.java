package egc.decide.io.cabinatelegram.bot.actions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import egc.decide.io.cabinatelegram.bot.util.BotUtils;
import egc.decide.io.cabinatelegram.session.model.UserSession;

@Component
public class StartAction implements DecideBotAction {

	@Override
	public BotApiMethod<?> act(Update update, UserSession userSession) throws DecideBotException {
		if (StringUtils.isEmpty(userSession.getDecideToken())) {
			ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
			keyboard.setOneTimeKeyboard(true);
			keyboard.setResizeKeyboard(true);
			
			List<KeyboardRow> rows = new ArrayList<>();
			rows.add(BotUtils.createKeyboardRow("Iniciar sesión", "Ayuda"));
			
			return new SendMessage().setChatId(update.getMessage().getChatId())
					.setReplyMarkup(new ReplyKeyboardRemove())
					.setText("Bienvenido a la cabina de votación de Telegram para Decide");
		} else {
			return null; //TODO: Menu para usuarios ya logeados
		}
	}

}
