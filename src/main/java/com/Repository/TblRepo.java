package com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.TblCallBack;

@Repository
public interface TblRepo extends JpaRepository<TblCallBack, Integer>
{

	
}
