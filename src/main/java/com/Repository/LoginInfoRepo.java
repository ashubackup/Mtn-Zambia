package com.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Entity.LoginInfo;

@Repository
public interface LoginInfoRepo extends JpaRepository<LoginInfo,Integer> 
{
	public LoginInfo findByStatusAndType(String status,String type);
}