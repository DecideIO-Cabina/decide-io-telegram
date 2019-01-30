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

import egc.decide.io.cabinatelegram.rest.model.LoginResponse;
import egc.decide.io.cabinatelegram.rest.model.Option;
import egc.decide.io.cabinatelegram.rest.model.Voting;

@Service
public class DecideVotingClient {

	@Autowired
	RestTemplate restTemplate;

	@Value("${decide.base.url}")
	String decideBaseUrl;

	public Voting[] getVoting(int id) {

		Voting[] voting = null;

		voting = restTemplate.getForObject(decideBaseUrl + "/voting/?id=" + id, Voting[].class,
				new HashMap<String, Integer>());

		return voting;

	}

	public void vote(Integer vote, Voting voting, Integer userId, String token) {

		MultiValueMap<String, Integer> body = new LinkedMultiValueMap<String, Integer>();
		body.add("voting_id", voting.getId());
		body.add("voter_id", userId);
		for (Option o : voting.getQuestion().getOptions()) {
			if (o.getNumber().equals(vote)) {
				body.add(o.getOption(), 1);
			} else {
				body.add(o.getOption(), 0);
			}

		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Token " + token);
		
		HttpEntity<MultiValueMap<String, Integer>> request = new HttpEntity<>(body, headers);
		
		restTemplate.postForObject(decideBaseUrl + "/store/", request, LoginResponse.class, new HashMap<String, String>());
	}

}
