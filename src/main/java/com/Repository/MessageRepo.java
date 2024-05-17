package com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.TblMessage;

@Repository
public interface MessageRepo extends JpaRepository<TblMessage, Integer>
{

	public TblMessage findByType(String type);
}
