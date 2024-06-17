package com.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.AirtimeCallBackEntity;

@Repository
public interface AirtimeCallBackRepo extends JpaRepository<AirtimeCallBackEntity, Integer>
{
	List<AirtimeCallBackEntity> findByStatusAndType(String status, String type);
}
