package com.ing.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ing.bank.entity.FundTransfer;

@Repository
public interface FundRepository extends JpaRepository<FundTransfer, Long> {
	
	@Query(value = "select * from ingbank.fundtransfer where from_account = :accountNo order by id desc limit 10",nativeQuery = true)
	public List<FundTransfer> getMyTransactions(@Param("accountNo") Long accountNo);	

}
