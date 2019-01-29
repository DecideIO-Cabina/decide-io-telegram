package egc.decide.io.cabinatelegram.rest.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Voting {

	Integer id;
	String name;
	String desc;
	Question question;
	@JsonProperty("start_date")
	Date startDate;
	@JsonProperty("end_date")
	Date endDate;
	@JsonProperty("pub_key")
	String pubKey;
	List<Auth> auths;
	String tally;
	String postproc;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getPubKey() {
		return pubKey;
	}

	public void setPubKey(String pubKey) {
		this.pubKey = pubKey;
	}

	public List<Auth> getAuths() {
		return auths;
	}

	public void setAuths(List<Auth> auths) {
		this.auths = auths;
	}

	public String getTally() {
		return tally;
	}

	public void setTally(String tally) {
		this.tally = tally;
	}

	public String getPostproc() {
		return postproc;
	}

	public void setPostproc(String postproc) {
		this.postproc = postproc;
	}

}
