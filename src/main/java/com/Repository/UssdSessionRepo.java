package com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.TblUssdSession;

@Repository
public interface UssdSessionRepo extends JpaRepository<TblUssdSession, Integer>
{
	
	TblUssdSession findByAni(String ani);
}
