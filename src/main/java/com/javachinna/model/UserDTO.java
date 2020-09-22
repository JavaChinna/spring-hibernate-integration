package com.javachinna.model;

import java.math.BigInteger;

import lombok.Data;

@Data
public class UserDTO {
	private BigInteger id;
	private String name;
	private String email;
}
