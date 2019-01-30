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
public class DecideListVotingClient {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${decide.base.url}")
	String decideBaseUrl;
	
	public Integer[] lista(int userID) {
		
		Integer[] list = new Arraylist();
		
	
		list = restTemplate
				.getForObject(decideBaseUrl + "/census/user/?user_id{"+ userID +"}",
						Arraylist());
		
		return list;
	}
	


}
