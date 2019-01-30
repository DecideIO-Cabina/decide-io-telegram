package egc.decide.io.cabinatelegram.bot.actions;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import egc.decide.io.cabinatelegram.bot.util.BotUtils;
import egc.decide.io.cabinatelegram.rest.client.DecideVotingClient;
import egc.decide.io.cabinatelegram.rest.model.Option;
import egc.decide.io.cabinatelegram.rest.model.Question;
import egc.decide.io.cabinatelegram.rest.model.Voting;
import egc.decide.io.cabinatelegram.session.model.BotState;
import egc.decide.io.cabinatelegram.session.model.UserSession;

@Component
public class VoteAction implements DecideBotAction {

	@Autowired
	DecideVotingClient decideVotingClient;
	
	@Autowired
	MainMenuAction mainMenuAction;

	private static final Log log = LogFactory.getLog(VoteAction.class);

	@Override
	public BotApiMethod<?>[] act(Update update, UserSession userSession) throws DecideBotException {
		switch (userSession.state()) {

		case BotState.WAITING_FOR_VOTINGID:

			Integer id = Integer.parseInt(update.getMessage().getText());
			if (id.equals(null)) {
				userSession.state(BotState.MAIN_MENU);
				throw new DecideBotException("Lo siento, el id introducido no es correcto");
			} else {
				return new BotApiMethod<?>[] {getVoting(id, update, userSession)};
			}
		case BotState.VOTING:
			Integer number = Integer.parseInt(update.getMessage().getText());
			return vote(update, userSession, number);

		default:
			return new BotApiMethod<?>[] {getVotingId(update, userSession)};

		}
	}

	private BotApiMethod<?> getVotingId(Update update, UserSession userSession) {
		userSession.state(BotState.WAITING_FOR_VOTINGID);

		return new SendMessage().setChatId(update.getMessage().getChatId()).setReplyMarkup(new ForceReplyKeyboard())
				.setText("Introduce el id de la votación:");
	}

	private BotApiMethod<?> getVoting(int votingId, Update update, UserSession userSession) throws DecideBotException {

		userSession.state(BotState.VOTING);

		Voting[] voting = decideVotingClient.getVoting(votingId);

		if (voting.length == 0) {
			userSession.state(BotState.MAIN_MENU);
			throw new DecideBotException("Lo siento, esa votación no existe");
		}
		userSession.setActualVoting(voting[0]);

		Question question = voting[0].getQuestion();

		ArrayList<Option> options = new ArrayList<Option>(question.getOptions());
		
		ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
		keyboard.setOneTimeKeyboard(true);
		keyboard.setResizeKeyboard(true);

		List<KeyboardRow> rows = new ArrayList<>();

		String opciones = "";
		for (Option o : options) {
			opciones = opciones + o.getNumber() + ". " + o.getOption() + "\n";
			rows.add(BotUtils.createKeyboardRow(o.getNumber().toString()));
		}
		
		
		keyboard.setKeyboard(rows);

		return new SendMessage().setChatId(update.getMessage().getChatId()).setReplyMarkup(keyboard)
				.setText("----------" + voting[0].getName() + "----------\n" + voting[0].getDesc() + "\n"
						+ question.getDesc() + "\n" + "Seleccione el número de la opción elegida:\n" + opciones);
	}

	private BotApiMethod<?>[] vote(Update update, UserSession userSession, Integer optionNumber)
			throws DecideBotException {
		userSession.state(BotState.MAIN_MENU);
		try {
			decideVotingClient.vote(optionNumber, userSession.getActualVoting(), userSession.getDecideUser().getId(), userSession.getDecideToken());

			userSession.state(BotState.MAIN_MENU);
			
			String texto = "Su voto ha sido registrado, gracias por participar en la votación";

			return new BotApiMethod<?>[] {
				new SendMessage().setChatId(update.getMessage().getChatId()).setText(texto),
				mainMenuAction.act(update, userSession)[0]};
		} catch (HttpClientErrorException e) {
			log.error(e.getMessage());
			if (e.getRawStatusCode() == 401)
				throw new DecideBotException("Lo siento, no puede participar en esta votación");
			else
				throw new DecideBotException("Lo siento, no he podido conectar con Decide");
		}

	}

}
