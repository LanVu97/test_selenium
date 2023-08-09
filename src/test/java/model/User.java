package model;

import java.util.Random;

public class User {
	private String firstName;
	private String lastName;
	private String email;
	private String telephone;
	private String password;
	private String passwordConfirm;
	private Boolean shouldSubscribe;
	private Boolean agreePrivacyPolicy;

	public User() {
	}

	public User(String firstName, String lastName, String email, String telephone, String password,
			String passwordConfirm, Boolean shouldSubscribe, Boolean agreePrivacyPolicy) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.telephone = telephone;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
		this.shouldSubscribe = shouldSubscribe;
		this.agreePrivacyPolicy = agreePrivacyPolicy;
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public Boolean getShouldSubscribe() {
		return shouldSubscribe;
	}

	public void setShouldSubscribe(Boolean shouldSubscribe) {
		this.shouldSubscribe = shouldSubscribe;
	}

	public Boolean getAgreePrivacyPolicy() {
		return agreePrivacyPolicy;
	}

	public void setAgreePrivacyPolicy(Boolean agreePrivacyPolicy) {
		this.agreePrivacyPolicy = agreePrivacyPolicy;
	}

	public String createEmail() {

		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(1000);

		return "username" + randomInt + "@gmail.com";

	}

}