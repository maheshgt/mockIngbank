package com.ing.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ing.bank.entity.FundTransfer;

@Repository
public interface FundRepository extends JpaRepository<FundTransfer, Long> {
	
	

}
