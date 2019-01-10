package egc.decide.io.cabinatelegram.bot.actions;

public class DecideBotException extends Exception {

	private static final long serialVersionUID = 8365582363022471428L;
	
	public DecideBotException() {
	}

	public DecideBotException(String msg) {
		super(msg);
	}

	public DecideBotException(String msg, Throwable e) {
		super(msg, e);
	}

}
