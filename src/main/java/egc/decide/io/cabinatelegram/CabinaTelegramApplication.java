package egc.decide.io.cabinatelegram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import egc.decide.io.cabinatelegram.bot.DecideIOBot;

@SpringBootApplication
public class CabinaTelegramApplication {

	public static void main(String[] args) {
		SpringApplication.run(CabinaTelegramApplication.class, args);
		
		initBot();
	}

	private static void initBot() {
		ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new DecideIOBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
	}
}
