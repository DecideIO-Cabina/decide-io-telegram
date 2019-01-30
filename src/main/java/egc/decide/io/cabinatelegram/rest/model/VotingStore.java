package egc.decide.io.cabinatelegram.rest.model;

import java.math.BigInteger;

public class VotingStore {
	
	private Vote vote;
	private Integer voting;
	private Integer voter;
	private String token;
	
	public VotingStore(BigInteger a, BigInteger b, Integer voting, Integer voter, String token) {
		this.vote = new Vote(a, b);
		this.voting = voting;
		this.voter = voter;
		this.token = token;
	}
	
	public Vote getVote() {
		return vote;
	}



	public void setVote(Vote vote) {
		this.vote = vote;
	}



	public Integer getVoting() {
		return voting;
	}



	public void setVoting(Integer voting) {
		this.voting = voting;
	}



	public Integer getVoter() {
		return voter;
	}



	public void setVoter(Integer voter) {
		this.voter = voter;
	}



	public String getToken() {
		return token;
	}



	public void setToken(String token) {
		this.token = token;
	}

	public class Vote {
		private BigInteger a;
		private BigInteger b;
		
		public Vote(BigInteger a, BigInteger b) {
			this.a = a;
			this.b = b;
		}
		
		public BigInteger getA() {
			return a;
		}
		public void setA(BigInteger a) {
			this.a = a;
		}
		public BigInteger getB() {
			return b;
		}
		public void setB(BigInteger b) {
			this.b = b;
		}		
		
	}

}
