package egc.decide.io.cabinatelegram;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import egc.decide.io.cabinatelegram.bot.DecideIOBot;
import egc.decide.io.cabinatelegram.bot.actions.DecideBotException;
import egc.decide.io.cabinatelegram.session.SessionService;
import egc.decide.io.cabinatelegram.session.model.BotState;
import egc.decide.io.cabinatelegram.session.model.UserSession;

public class BaseTest {
	
	@Autowired
	DecideIOBot bot;
	
	@Autowired
	SessionService sessionService;
	
	static final Integer mockUserId = 99999;
	
	// Mocks
	static Message message;
	static Update update;
	
	static {
		User user = new User(mockUserId, "Usuario", false, "Prueba", "usuarioPrueba", "es");
		Chat chat = new Chat();
		message = new Message();
		update = new Update();
		
		//La API de telegram para Java pone todos los campos privados y
		//sin setters, asi que hay que usar reflexion			
		try {
			FieldUtils.writeField(chat, "id", 12345l, true);
			FieldUtils.writeField(message, "from", user, true);
			FieldUtils.writeField(message, "chat", chat, true);
			FieldUtils.writeField(update, "message", message, true);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	BotApiMethod<?> act(String msg) throws IllegalAccessException, DecideBotException {
		FieldUtils.writeField(message, "text", msg, true);
		
		return bot.act(update);			
	}
	
	void expectContains(BotApiMethod<?> response, String expected) {
		Assert.assertTrue(response instanceof SendMessage);
		
		SendMessage message = (SendMessage) response;
		Assert.assertTrue("La respuesta no tenía el texto esperado",
				message.getText().contains(expected));
	}
	
	void expectContainsAndMarkup(BotApiMethod<?> response, String expected) {
		Assert.assertTrue(response instanceof SendMessage);
		
		SendMessage message = (SendMessage) response;
		Assert.assertTrue("La respuesta no tenía el texto esperado",
				message.getText().contains(expected));
		Assert.assertNotNull("La respuesta tenía el texto esperado, pero no el markup esperado",
				message.getReplyMarkup());
	}
	
	void state(int state) {
		sessionService.get(mockUserId).state(state);
	}
	
	UserSession session() {
		return sessionService.get(mockUserId);
	}
	
	void clearSession() {
		sessionService.get(mockUserId).setDecideToken(null);
		sessionService.get(mockUserId).setDecideUser(null);
		sessionService.get(mockUserId).state(BotState.ANONYMOUS);
	}

}
