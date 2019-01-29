package egc.decide.io.cabinatelegram.rest.client;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
		HashMap<String, Integer> votingId = new HashMap<String, Integer>();
		votingId.put("id", 1);

		voting = restTemplate.getForObject(decideBaseUrl + "/voting/", Voting[].class, votingId);

		return voting;

	}

	public void vote(Integer vote, Voting voting, Integer userId) {

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

		// TODO revisar el LoginResponse.class, la API de postprocesado no deja claro
		// que devuelve la llamada
		restTemplate.postForObject(decideBaseUrl + "/store/", body, LoginResponse.class, new HashMap<String, String>());

	}

}
