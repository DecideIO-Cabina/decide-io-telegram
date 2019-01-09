package egc.decide.io.cabinatelegram.rest.client;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import egc.decide.io.cabinatelegram.rest.model.LoginResponse;

@Service
public class DecideLoginClient {
	
	@Autowired
	RestTemplate restTemplate;
	
	public String login(String username, String password) {
		String token = null;
		
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("username", username);
		body.add("password", password);
		
		LoginResponse response = restTemplate
				.postForObject("https://decideiopostproc.herokuapp.com/authentication/login/",
				body, LoginResponse.class, new HashMap<String, String>());
		
		System.out.println(response.getToken());
		
		return token;
	}

}
