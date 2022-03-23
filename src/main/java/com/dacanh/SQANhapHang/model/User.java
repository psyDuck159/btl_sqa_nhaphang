package com.dacanh.SQANhapHang.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import javax.persistence.*;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.GenerationType.IDENTITY;

@Entity @Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id @GeneratedValue(strategy = IDENTITY)
	private Integer id;
	private String name;
	private String username;
	private String password;
	@ManyToMany(fetch = EAGER)
	private Collection<Role> roles = new ArrayList<>();

}
