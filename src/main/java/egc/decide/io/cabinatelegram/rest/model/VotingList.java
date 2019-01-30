package egc.decide.io.cabinatelegram.rest.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VotingList {
	
	@JsonProperty("list_of_votings_id")
	private List<Integer> listOfVotingsId;

	@JsonProperty("list_of_votings_id")
	public List<Integer> getListOfVotingsId() {
		return listOfVotingsId;
	}

	public void setListOfVotingsId(List<Integer> listOfVotingsId) {
		this.listOfVotingsId = listOfVotingsId;
	}

}
