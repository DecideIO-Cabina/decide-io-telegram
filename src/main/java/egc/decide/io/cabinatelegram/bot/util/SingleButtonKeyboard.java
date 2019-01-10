package egc.decide.io.cabinatelegram.bot.util;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

public class SingleButtonKeyboard extends ReplyKeyboardMarkup {

	private static final long serialVersionUID = 6319215057971009726L;
	
	public SingleButtonKeyboard(String button) {
		super();
		
		setOneTimeKeyboard(true);
		setResizeKeyboard(true);		
		List<KeyboardRow> rows = new ArrayList<>();
		rows.add(BotUtils.createKeyboardRow(button));
		setKeyboard(rows);
	}

}
