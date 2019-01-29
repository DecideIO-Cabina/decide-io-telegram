package egc.decide.io.cabinatelegram.rest.model;

import java.util.List;

public class Question {

	String desc;
	List<Option> options;

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

}
