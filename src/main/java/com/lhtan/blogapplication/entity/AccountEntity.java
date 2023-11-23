package com.lhtan.blogapplication.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "account")
@Getter
@Setter
@NoArgsConstructor
public class AccountEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column
	private String email;
	
	@Column
	private String password;
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	@Column
	LocalDateTime createdAt;
	
	@Column
    LocalDateTime updatedAt;
	
	@OneToMany(mappedBy = "account")
	private List<PostEntity> posts;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "account_authority", 
		joinColumns = {@JoinColumn(name = "account_id", referencedColumnName = "id")},
		inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})
	private Set<AuthorityEntity> authorities = new HashSet<AuthorityEntity>();
}
