package egc.decide.io.cabinatelegram.session.model;

import egc.decide.io.cabinatelegram.rest.model.DecideUser;
import egc.decide.io.cabinatelegram.rest.model.Voting;

public class UserSession {

	Integer telegramUserId;
	String decideToken;
	int state = BotState.ANONYMOUS;
	DecideUser decideUser;
	Voting actualVoting;

	public UserSession(Integer telegramUserId) {
		this.setTelegramUserId(telegramUserId);
	}

	public Integer getTelegramUserId() {
		return telegramUserId;
	}

	public void setTelegramUserId(Integer telegramUserId) {
		this.telegramUserId = telegramUserId;
	}

	public String getDecideToken() {
		return decideToken;
	}

	public void setDecideToken(String decideToken) {
		this.decideToken = decideToken;
	}

	public int state() {
		return state;
	}

	public void state(int state) {
		this.state = state;
	}

	public DecideUser getDecideUser() {
		return decideUser;
	}

	public void setDecideUser(DecideUser decideUser) {
		this.decideUser = decideUser;
	}

	public Voting getActualVoting() {
		return actualVoting;
	}

	public void setActualVoting(Voting voting) {
		this.actualVoting = voting;
	}

}
