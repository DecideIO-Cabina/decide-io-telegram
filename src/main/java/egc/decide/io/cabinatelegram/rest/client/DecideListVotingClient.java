package egc.decide.io.cabinatelegram.rest.client;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DecideListVotingClient {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${decide.base.url}")
	String decideBaseUrl;
	//segun su metodo devuelve una lista con los id de las votaciones, por lo que lo transformo en un array de integers
	public ArrayList<Integer> lista(int userID) {
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
	
		list = restTemplate
				.getForObject(decideBaseUrl + "/census/user/?user_id{"+ userID +"}",
						ArrayList.class);
		
		return list;
	}
	

	


}
