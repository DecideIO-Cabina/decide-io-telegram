package egc.decide.io.cabinatelegram.bot;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import egc.decide.io.cabinatelegram.bot.actions.DecideBotException;
import egc.decide.io.cabinatelegram.bot.actions.StartAction;
import egc.decide.io.cabinatelegram.bot.actions.LoginAction;
import egc.decide.io.cabinatelegram.session.SessionService;
import egc.decide.io.cabinatelegram.session.model.BotState;
import egc.decide.io.cabinatelegram.session.model.UserSession;


@Component
public class DecideIOBot extends TelegramLongPollingBot {
	
	@Autowired
	SessionService sessionService;

	@Autowired
	StartAction startAction;
	
	@Autowired
	LoginAction loginAction;

	private static final Log log = LogFactory.getLog(DecideIOBot.class);
	
	@Override
	public void onUpdateReceived(Update update) {
		log.debug("Recibido mensaje de " + update.getMessage().getFrom().getFirstName() + " ("
				+ update.getMessage().getFrom().getUserName() + ")");
		log.debug(update.getMessage().getText());

		if (update.hasMessage() && update.getMessage().hasText()) {

			try {
				execute(act(update));
			} catch (TelegramApiException e) {
				log.error("Se ha producido un error respondiendo a " + update.getMessage().getText(), e);
			} catch (DecideBotException e) {
				try {
					execute(new SendMessage().setChatId(update.getMessage().getChatId())
							.setText(e.getMessage()));					
					
					execute(new SendMessage().setChatId(update.getMessage().getChatId())
							.setText("Prueba con /start"));
				} catch (TelegramApiException e1) {
					e1.printStackTrace();
				}
			}

		}
	}

	public BotApiMethod<?> act(Update update) throws DecideBotException {
		BotApiMethod<?> result = null;
		UserSession userSession = sessionService.get(update.getMessage().getFrom().getId());

		switch (userSession.state()) {

		case BotState.ANONYMOUS:
			result = startAction.act(update, userSession);
			break;
		case BotState.WAITING_FOR_USERNAME:
		case BotState.WAITING_FOR_PASSWORD:
			result = loginAction.act(update, userSession);
			break;
		case BotState.MAIN_MENU:
			userSession.state(BotState.ANONYMOUS);
			throw new DecideBotException("Lo siento, esta acción no está implementada aún");
		default:
			userSession.state(BotState.ANONYMOUS);
			throw new DecideBotException("Lo siento, no te he entendido");
		}
		
		return result;
	}

	@Override
	public String getBotUsername() {
		return "DecideIOBot";
	}

	@Override
	public String getBotToken() {
		return "632133854:AAFtn9VWO7-2YtCC3PxWijvsstdgbh6Uke0";
	}

}
