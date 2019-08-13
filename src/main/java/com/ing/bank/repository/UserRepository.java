package com.ing.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ing.bank.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByaccountNo(Long accountNo);
	
	
	List<User> findByaccountNoNotLike(Long accountNo);

}
