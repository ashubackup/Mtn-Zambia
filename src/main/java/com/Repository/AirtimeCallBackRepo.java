package com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.AirtimeCallBackEntity;

@Repository
public interface AirtimeCallBackRepo extends JpaRepository<AirtimeCallBackEntity, Integer>
{

}
