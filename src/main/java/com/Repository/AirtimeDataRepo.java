package com.Repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;	
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.Entity.AirtimeData;
import com.Model.AirtimePositionResponse;

@Repository
public interface AirtimeDataRepo extends JpaRepository<AirtimeData,Integer>
{
	@Query(value="SELECT * FROM airtime_data WHERE ani=:ani AND DATE(DATETIME)=CURDATE() and serviceId=:serviceId",nativeQuery=true)
	public AirtimeData getAlreadyAddedUser(@Param("ani")String ani,@Param("serviceId")String serviceId);
	
	@Query(value="SELECT * FROM airtime_data WHERE DATE(DATETIME)=CURDATE() and serviceId=:serviceId ORDER BY score DESC LIMIT 10",nativeQuery=true)
	public List<AirtimeData> getAirtimeWinners(@Param("serviceId")String serviceId);
	
	@Query(value="SELECT * FROM airtime_data WHERE DATE(DATETIME)=CURDATE() AND serviceId=:serviceId ORDER BY score DESC LIMIT 1",nativeQuery=true)
	public AirtimeData getTodaysTopScorer(@Param("serviceId")String serviceId);
	
	@Query(value="SELECT * FROM airtime_data WHERE DATE(DATETIME)=DATE(SUBDATE(NOW(),1))",nativeQuery=true)
	public List<AirtimeData> getMonthlyData();
	
	@Query(value="SELECT * FROM airtime_data WHERE DATE(DATETIME)=DATE(SUBDATE(NOW(),1)) AND STATUS='0' AND serviceId=:serviceId\r\n"
			+ "ORDER BY score DESC LIMIT 10\r\n"
			+ "",nativeQuery=true)
	public List<AirtimeData> getDailyAirtimeNumbers(@Param("serviceId")String serviceId);
	
	 
    @Query(value = "SELECT ani, score FROM airtime_data WHERE DATE(datetime) = CURDATE() ORDER BY score DESC", nativeQuery = true)
    Optional<List<Object[]>> getPositionScoreDaily();
    
    @Query(value = "SELECT ani, score FROM cash_data WHERE MONTH(DATETIME) = MONTH(NOW()) ORDER BY score DESC;", nativeQuery = true)
    Optional<List<Object[]>> getPositionScoreMonthly();
	
}