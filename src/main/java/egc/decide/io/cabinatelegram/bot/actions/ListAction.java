package egc.decide.io.cabinatelegram.bot.actions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;

import egc.decide.io.cabinatelegram.bot.util.SingleButtonKeyboard;
import egc.decide.io.cabinatelegram.rest.client.DecideAuthenticationClient;
import egc.decide.io.cabinatelegram.rest.model.DecideUser;
import egc.decide.io.cabinatelegram.session.model.BotState;
import egc.decide.io.cabinatelegram.session.model.UserSession;

@Component
public class ListAction implements DecideBotAction {
	
	@Autowired
	DecideListVotingClient decideListVotingClient;

	@Autowired
	DecideVotingClient decideVotingClient;
	
	private static final Log log = LogFactory.getLog(ListAction.class);
	

	@Override
	public BotApiMethod<?> act(Update update, UserSession userSession) throws DecideBotException {
		switch (userSession.state()) {
		//saber quien esta logueado
		int id = userSession.getTelegramUserId();
		//debemos de estar en el menu principal ya logueado
		case BotState.MAIN_MENU:
			return this.getListVotings(id,update, userSession);
		
		default:
			throw new DecideBotException();
		
		}		
	}

	private BotApiMethod<?> getListVotings(int userID, Update update, UserSession userSession) {
		//sacamos la lista de las id de los votings del usuario a votar, 
			Integer[] lista = decideListVotingClient.lista(userID)
					
			String opciones = "Puedes votar en: \n";
			
			for (Integer votingID : lista) {
				
				Voting[] voting = decideVotingClient.getVoting(votingID);
				
				opciones = opciones + voting[0].getName() + ",con ID: " + voting[0].getID() + "\n";
			}

			return new SendMessage().setChatId(update.getMessage().getChatId()).setReplyMarkup(new ForceReplyKeyboard())
					.setText(opciones);
		}
	}
