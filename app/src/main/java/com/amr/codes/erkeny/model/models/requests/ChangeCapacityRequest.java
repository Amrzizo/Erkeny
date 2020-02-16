package com.amr.codes.erkeny.model.models.requests;


import com.google.gson.annotations.SerializedName;


public class ChangeCapacityRequest{

	@SerializedName("branch_id")
	private int branchId;

	@SerializedName("token")
	private String token;

	@SerializedName("capacity")
	private int capacity;

	public void setBranchId(int branchId){
		this.branchId = branchId;
	}

	public int getBranchId(){
		return branchId;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}

	public void setCapacity(int capacity){
		this.capacity = capacity;
	}

	public int getCapacity(){
		return capacity;
	}

	@Override
 	public String toString(){
		return 
			"ChangeCapacityRequest{" + 
			"branch_id = '" + branchId + '\'' + 
			",token = '" + token + '\'' + 
			",capacity = '" + capacity + '\'' + 
			"}";
		}
}