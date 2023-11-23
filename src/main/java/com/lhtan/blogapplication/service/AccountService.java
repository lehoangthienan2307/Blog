package com.lhtan.blogapplication.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lhtan.blogapplication.entity.AccountEntity;
import com.lhtan.blogapplication.entity.AuthorityEntity;
import com.lhtan.blogapplication.repository.AccountRepository;
import com.lhtan.blogapplication.repository.AuthorityRepository;

@Service
public class AccountService {
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private AuthorityRepository authorityRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	public AccountEntity save(AccountEntity account)
	{
		if (account.getId() == null) {
            if (account.getAuthorities().isEmpty()) {
                Set<AuthorityEntity> authorities = new HashSet<>();
                authorityRepository.findById("ROLE_USER").ifPresent(authorities::add);
                account.setAuthorities(authorities);
            }
            account.setCreatedAt(LocalDateTime.now());
        }
        account.setUpdatedAt(LocalDateTime.now());
        account.setPassword(passwordEncoder.encode(account.getPassword()));
		return accountRepository.save(account);
	}
	
	public Optional<AccountEntity> findByEmail(String email)
	{
		return accountRepository.findOneByEmail(email);
	}
}
