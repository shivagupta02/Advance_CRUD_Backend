package com.OnlyApp.OnlyApp.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.OnlyApp.OnlyApp.Models.AccountDetails;
import com.OnlyApp.OnlyApp.repo.AccountRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	@Autowired
	private AccountRepository accountRepo;
	

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		AccountDetails account=accountRepo.findByEmail(email);
		if(email==null)
		{
			throw new UsernameNotFoundException("Employee Email not found with this email"+" "+email);
		}
		
		return account;
	}
	


}
