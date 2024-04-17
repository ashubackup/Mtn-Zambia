package com.Repository;
import java.util.List;	
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Entity.ServiceInfo;

@Repository
public interface ServiceInfoRepo extends JpaRepository<ServiceInfo,Integer>
{
	public ServiceInfo findByGameidAndStatus(String gameId,String status);
	public List<ServiceInfo> findByStatus(String status);
}