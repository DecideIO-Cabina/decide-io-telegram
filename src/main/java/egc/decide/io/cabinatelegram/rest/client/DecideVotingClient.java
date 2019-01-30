package egc.decide.io.cabinatelegram.rest.client;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import egc.decide.io.cabinatelegram.rest.model.Cipher;
import egc.decide.io.cabinatelegram.rest.model.LoginResponse;
import egc.decide.io.cabinatelegram.rest.model.Voting;
import egc.decide.io.cabinatelegram.rest.model.VotingList;
import egc.decide.io.cabinatelegram.rest.model.VotingStore;

@Service
public class DecideVotingClient {

	@Autowired
	RestTemplate restTemplate;

	@Value("${decide.base.url}")
	String decideBaseUrl;
	
	public Collection<Voting> getVotings(Integer userId) {
		List<Voting> votings = new ArrayList<>();
		
		VotingList votingList = restTemplate.getForObject(decideBaseUrl + "/census/user/?user_id=" + userId, VotingList.class,
				new HashMap<String, Integer>());
		
		for (Integer id : votingList.getListOfVotingsId()) {
			votings.add(getVoting(id)[0]);
		}
		
		return votings;
	}

	public Voting[] getVoting(int id) {

		Voting[] voting = null;

		voting = restTemplate.getForObject(decideBaseUrl + "/voting/?id=" + id, Voting[].class,
				new HashMap<String, Integer>());

		return voting;

	}

	public void vote(Integer vote, Voting voting, Integer userId, String token) {
		
		BigInteger[] v = Cipher.encrypt(new BigInteger(voting.getPubKey().getP()), new BigInteger(voting.getPubKey().getG()),
				new BigInteger(voting.getPubKey().getY()), BigInteger.valueOf(vote));
		
		
		VotingStore votingStore = new VotingStore(v[0], v[1], voting.getId(), userId, token);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Token " + token);
		
		HttpEntity<VotingStore> request = new HttpEntity<>(votingStore, headers);
		
		restTemplate.postForObject(decideBaseUrl + "/store/", request, LoginResponse.class, new HashMap<String, String>());
	}

}
