package com.amr.codes.erkeny.model.models.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ClientRegisterFailure {

	@SerializedName("password")
	private List<String> password;

	@SerializedName("mobile")
	private List<String> mobile;

	@SerializedName("email")
	private List<String> email;

	@SerializedName("name")
	private List<String> name;


	public List<String> getName() {
		return name;
	}

	public void setName(List<String> name) {
		this.name = name;
	}

	public void setPassword(List<String> password){
		this.password = password;
	}

	public List<String> getPassword(){
		return password;
	}

	public void setMobile(List<String> mobile){
		this.mobile = mobile;
	}

	public List<String> getMobile(){
		return mobile;
	}

	public void setEmail(List<String> email){
		this.email = email;
	}

	public List<String> getEmail(){
		return email;
	}

	@Override
 	public String toString(){
		return 
			"ClientRegisterFailure{" +
			"password = '" + password + '\'' + 
			",mobile = '" + mobile + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}