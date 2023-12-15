package com.diets.user.domain;


import java.sql.Timestamp;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class User {

	private int id;
	
	@NonNull
	private String userId;
	@NonNull
	private String password;
	@NonNull
	private String name;
	@NonNull
	private String age;
	@Nonnull
	private String height;
	@NonNull
	private String weight;
	@NonNull
	private int gender;
	
	private String kcal;
	
	private String fat;
	
	private String protein;
	
	private String carbs;
	
	private Timestamp createdAt;
	
}
