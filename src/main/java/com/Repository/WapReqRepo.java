package com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.WapRequest;

@Repository
public interface WapReqRepo extends JpaRepository<WapRequest, Integer>
{
	
}
