package com.OnlyApp.OnlyApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.OnlyApp.OnlyApp.Models.AccountDetails;

public interface AccountRepository extends JpaRepository<AccountDetails, Integer> {

	AccountDetails findByEmail(String email);
	
}
