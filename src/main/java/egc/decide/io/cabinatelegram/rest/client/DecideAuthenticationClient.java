package egc.decide.io.cabinatelegram.rest.client;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import egc.decide.io.cabinatelegram.rest.model.DecideUser;
import egc.decide.io.cabinatelegram.rest.model.LoginResponse;

@Service
public class DecideAuthenticationClient {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${decide.base.url}")
	String decideBaseUrl;
	
	public String login(String username, String password) {
		String token = null;
		
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("username", username);
		body.add("password", password);
		
		token = restTemplate
				.postForObject(decideBaseUrl + "/authentication/login/",
				body, LoginResponse.class, new HashMap<String, String>())
				.getToken();
		
		return token;
	}
	
	public DecideUser getUser(String token) {
		DecideUser result = null;
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Token " + token);
		
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("token", token);
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
		
		result = restTemplate
				.postForObject(decideBaseUrl + "/authentication/getuser/",
				request, DecideUser.class, new HashMap<String, String>());
		
		
		return result;
	}

}
