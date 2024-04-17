package com.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Entity.TermsData;

@Repository
public interface TermsDataRepo extends JpaRepository<TermsData,Integer>
{
	public TermsData findByStatusAndTypeAndServiceId(String status,String type,String serviceId);
}