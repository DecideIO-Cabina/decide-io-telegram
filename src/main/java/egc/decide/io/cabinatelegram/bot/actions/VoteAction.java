package egc.decide.io.cabinatelegram.bot.actions;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;

import egc.decide.io.cabinatelegram.rest.model.DecideUser;
import egc.decide.io.cabinatelegram.session.model.BotState;
import egc.decide.io.cabinatelegram.session.model.UserSession;

@Component
public class VoteAction implements DecideBotAction {

	@Override
	public BotApiMethod<?> act(Update update, UserSession userSession) throws DecideBotException {
		switch (userSession.state()) {

		case BotState.WAITING_FOR_VOTINGID:
			Integer id = Integer.getInteger(update.getMessage().getText());
			if (id == null) {
				throw new DecideBotException("Lo siento, el id introducido no es correcto");
			} else {
				return this.vote(id);
			}
		default:
			return this.getVotingId(update, userSession);

		}
	}

	private BotApiMethod<?> getVotingId(Update update, UserSession userSession) {
		userSession.state(BotState.WAITING_FOR_VOTINGID);

		return new SendMessage().setChatId(update.getMessage().getChatId()).setReplyMarkup(new ForceReplyKeyboard())
				.setText("Introduce el id de la votación:");
	}

	private BotApiMethod<?> vote(int votingId) {
		return null;
	}

}
