package com.Repository;
import java.util.List;	
import org.springframework.data.jpa.repository.JpaRepository;	
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.Entity.AirtimeData;

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
}