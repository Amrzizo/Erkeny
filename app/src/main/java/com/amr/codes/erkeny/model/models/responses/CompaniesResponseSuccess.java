package com.amr.codes.erkeny.model.models.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class CompaniesResponseSuccess{

	@SerializedName("image")
	private String image;

	@SerializedName("user_id")
	private int userId;

	@SerializedName("id")
	private int id;

	@SerializedName("branches")
	private List<BranchesItem> branches;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setBranches(List<BranchesItem> branches){
		this.branches = branches;
	}

	public List<BranchesItem> getBranches(){
		return branches;
	}

	@Override
 	public String toString(){
		return 
			"CompaniesResponseSuccess{" + 
			"image = '" + image + '\'' + 
			",user_id = '" + userId + '\'' + 
			",id = '" + id + '\'' + 
			",branches = '" + branches + '\'' + 
			"}";
		}
}