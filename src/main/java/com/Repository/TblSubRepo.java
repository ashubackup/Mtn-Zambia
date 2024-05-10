package com.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Entity.TblSubscription;

@Repository
public interface TblSubRepo extends JpaRepository<TblSubscription, Integer>
{
	List<TblSubscription> findByAni(String ani);
	
	@Query(value="Select * from tbl_subscription where ani=:ani",nativeQuery=true)
	public TblSubscription getUserByAni(@Param("ani") String ani);
}
