package com.Repository;
import java.util.List;	
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.Entity.Prizes;

@Repository
public interface PrizesRepo extends JpaRepository<Prizes,Integer>
{
	public List<Prizes> findByTypeAndStatus(String type,String status);
	
	@Query(value="SELECT * FROM prizes WHERE STATUS='1' AND TYPE='airtime' LIMIT :lim",nativeQuery=true)
	public List<Prizes> getPrizesToPay(@Param("lim")Integer lim);
}