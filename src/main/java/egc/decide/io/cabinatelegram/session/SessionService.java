package egc.decide.io.cabinatelegram.session;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import egc.decide.io.cabinatelegram.session.model.UserSession;

@Service
public class SessionService {
	
	Map<Integer, UserSession> users = new HashMap<>();
	
	public UserSession get(Integer telegramUserId) {
		UserSession result;
		
		result = users.get(telegramUserId);		
		if (result == null){
			result = new UserSession(telegramUserId);
			users.put(telegramUserId, result);
		}
		
		return result;
	}

}
