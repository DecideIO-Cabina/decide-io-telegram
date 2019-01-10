package egc.decide.io.cabinatelegram.bot.util;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

public final class BotUtils {
	
	static public KeyboardRow createKeyboardRow(String... keys) {
		KeyboardRow row = new KeyboardRow();
		
		for (String key : keys)
			row.add(new KeyboardButton(key));
		
		return row;
	}

}
