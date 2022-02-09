package com.example.springboot.entity;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface AccountTransactRepository extends CrudRepository<AccountTransact, Long> {

    @Query(value = "select new com.example.springboot.entity.AccountTransactWithInfo(a.accountNumber, a.accountName, b.transcationID, b.deposit, b.withdrawal)" +
            " FROM AccountTransact b JOIN b.accountInfo a WHERE a.accountNumber = :acctNumber")
    public List<AccountTransactWithInfo> getTransactRecord(@Param("acctNumber")Integer accountNumber);


    @Query(value = "select new com.example.springboot.entity.AccountBalance(b.accountNumber, sum(a.deposit) - sum(a.withdrawal))" +
            "  FROM AccountTransact a JOIN a.accountInfo b where b.accountNumber = :acctNumber")
    public List<AccountBalance> getAccountBalance(@Param("acctNumber") Integer accountNumber);


}
