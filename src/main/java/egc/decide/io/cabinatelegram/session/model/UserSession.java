package egc.decide.io.cabinatelegram.session.model;

public class UserSession {
	
	Integer telegramUserId;
	String decideToken;
	int state = State.ANONYMOUS;
	
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

}
