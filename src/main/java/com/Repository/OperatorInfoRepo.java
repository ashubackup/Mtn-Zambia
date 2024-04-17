package com.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Entity.OperatorInfo;

@Repository
public interface OperatorInfoRepo extends JpaRepository<OperatorInfo,Integer>
{
	public OperatorInfo findByStatusAndServiceId(String status,String serviceId);
}