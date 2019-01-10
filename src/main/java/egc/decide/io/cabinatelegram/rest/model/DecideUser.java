package egc.decide.io.cabinatelegram.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DecideUser {
	
	Integer id;
	String username;
	@JsonProperty("first_name")
	String firstName;
	@JsonProperty("last_name")
	String lastName;
	String email;
	@JsonProperty("is_staff")
	Boolean isStaff;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getIsStaff() {
		return isStaff;
	}
	public void setIsStaff(Boolean isStaff) {
		this.isStaff = isStaff;
	}	

}
