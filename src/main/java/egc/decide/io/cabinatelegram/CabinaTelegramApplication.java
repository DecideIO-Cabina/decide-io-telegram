package egc.decide.io.cabinatelegram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class CabinaTelegramApplication {

	public static void main(String[] args) {
		ApiContextInitializer.init();
		
		SpringApplication.run(CabinaTelegramApplication.class, args);
	}
	
}
