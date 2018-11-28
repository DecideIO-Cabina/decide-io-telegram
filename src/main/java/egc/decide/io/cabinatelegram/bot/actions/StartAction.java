package egc.decide.io.cabinatelegram.bot.actions;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartAction implements DecideBotAction {

	@Override
	public BotApiMethod<?> act(Update update) throws DecideBotException {
		return new SendMessage().setChatId(update.getMessage().getChatId()).setText("Hay que implementar esta acción"); //TODO
	}	

}
