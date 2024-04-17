package com.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.Entity.CashData;

@Repository
public interface CashDataRepo extends JpaRepository<CashData,Integer>
{
	@Query(value="SELECT * FROM cash_data WHERE MONTH(DATETIME)=MONTH(CURDATE()) and serviceId=:serviceId ORDER BY score DESC LIMIT 10",nativeQuery=true)
	public List<CashData> getCashWinners(@Param("serviceId")String serviceId);
	
	@Query(value="SELECT * FROM cash_data WHERE MONTH(DATETIME)=MONTH(CURDATE()) AND ani=:ani AND serviceId=:serviceId",nativeQuery=true)
	public CashData matchAlready(@Param("ani")String ani,@Param("serviceId")String serviceId);
}