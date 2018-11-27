package egc.decide.io.cabinatelegram.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class DecideIOBot extends TelegramLongPollingBot {
	
	

	@Override
	public void onUpdateReceived(Update update) {
		System.out.println("Recibido mensaje de "+update.getMessage().getFrom().getFirstName()+" ("+update.getMessage().getFrom().getUserName()+")");
		System.out.println(update.getMessage().getText());
		if (update.hasMessage() && update.getMessage().hasText()) {
			
			SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
					.setChatId(update.getMessage().getChatId()).setText("Hola amigos de EGC");
			try {
				execute(message); // Call method to send the message
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		}
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
