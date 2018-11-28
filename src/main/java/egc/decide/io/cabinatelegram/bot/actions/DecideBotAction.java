package egc.decide.io.cabinatelegram.bot.actions;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public interface DecideBotAction {
	
	BotApiMethod<?> act(Update update) throws DecideBotException;

}
