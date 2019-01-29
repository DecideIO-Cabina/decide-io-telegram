package egc.decide.io.cabinatelegram.rest.client;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import egc.decide.io.cabinatelegram.rest.model.Voting;

@Service
public class DecideVotingClient {

	@Autowired
	RestTemplate restTemplate;

	@Value("${decide.base.url}")
	String decideBaseUrl;

	public Voting[] getVoting(int id) {

		Voting[] voting = null;
		HashMap<String, Integer> ids = new HashMap<String, Integer>();
		ids.put("id", 1);

		voting = restTemplate.getForObject(decideBaseUrl + "/voting/", Voting[].class, ids);

		return voting;

	}
	
	
}
