package egc.decide.io.cabinatelegram.bot.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import egc.decide.io.cabinatelegram.bot.util.BotUtils;
import egc.decide.io.cabinatelegram.rest.client.DecideVotingClient;
import egc.decide.io.cabinatelegram.rest.model.Voting;
import egc.decide.io.cabinatelegram.session.model.BotState;
import egc.decide.io.cabinatelegram.session.model.UserSession;

@Component
public class VotingListAction implements DecideBotAction {

	@Autowired
	DecideVotingClient decideVotingClient;
	
	@Override
	public BotApiMethod<?>[] act(Update update, UserSession userSession) throws DecideBotException {
		Collection<Voting> votings = decideVotingClient.getVotings(userSession.getDecideUser().getId()); 
		
		ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
		keyboard.setOneTimeKeyboard(true);
		keyboard.setResizeKeyboard(true);

		List<KeyboardRow> rows = new ArrayList<>();
		String opciones = "";

		for (Voting v : votings) {
			rows.add(BotUtils.createKeyboardRow(v.getId().toString()));
			opciones = opciones + v.getId() + ". " + v.getName() + "\n";
		}
		
		keyboard.setKeyboard(rows);
		
		userSession.state(BotState.WAITING_FOR_VOTINGID);
		
		return new BotApiMethod<?>[] {
			new SendMessage().setChatId(update.getMessage().getChatId()).setReplyMarkup(keyboard)
			.setText("Seleccione el número de la votación en la que desea participar:\n" + opciones)
		};
				
	}

}
