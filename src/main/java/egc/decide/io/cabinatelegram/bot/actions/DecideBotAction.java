package egc.decide.io.cabinatelegram.bot.actions;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import egc.decide.io.cabinatelegram.session.model.UserSession;

public interface DecideBotAction {
	
	BotApiMethod<?> act(Update update, UserSession userSession) throws DecideBotException;

}
