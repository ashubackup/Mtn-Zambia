package com.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.AirtimeData;
import com.Model.AirtimePositionResponse;
import com.Repository.AirtimeDataRepo;
@Service
public class UserPositionSendService {
	
	@Autowired
	private AirtimeDataRepo repo;
	
	public Map<String, AirtimePositionResponse> sendUserPosition(String userAni) {
		Map<String, AirtimePositionResponse> response = new HashMap<>();
        try {
        	Optional<List<Object[]>> optionalDataDaily = repo.getPositionScoreDaily();
        	 if (optionalDataDaily.isPresent()) {
             	response.put("Daily", checkRanked(optionalDataDaily, userAni));
             } else {
                 // No data available for the current date
             	response.put("Daily",sendResponse(userAni, 0, 0));
             }
        	
            Optional<List<Object[]>> optionalDataMonthly = repo.getPositionScoreMonthly();
            if (optionalDataMonthly.isPresent()) {
            	response.put("Monthly", checkRanked(optionalDataMonthly, userAni));
            } else {
                // No data available for the current month
            	response.put("Monthly",sendResponse(userAni, 0, 0));
            }
            
        } catch (Exception e) {
            System.err.println("An error occurred while fetching user position: " + e.getMessage());
            //throw new RuntimeException("An error occurred while fetching user position", e);
            response.put("Daily",sendResponse(userAni, 0, 0));
            response.put("Monthly",sendResponse(userAni, 0, 0));
        }
        return response;
    }
	
	
	public AirtimePositionResponse checkRanked(Optional<List<Object[]>> optionalData, String userAni)
	{
		try {
            List<AirtimeData> rankedAirtimeData = new ArrayList<>();
            List<Object[]> rawData = optionalData.get();
            int position = 1;
            for (Object[] record : rawData) {
                String ani = (String) record[0];
                int score = ((Number) record[1]).intValue();
                rankedAirtimeData.add(new AirtimeData(ani, score, position++));
            }

            for (AirtimeData response : rankedAirtimeData) {
                if (response.getAni().equalsIgnoreCase(userAni)) {
                    return sendResponse(response.getAni(), response.getScore(), response.getPosition());
                }
            }

            // User ANI not found in the ranked data
            return sendResponse(userAni, 0, 0);
        } catch (Exception e) {
            System.err.println("An error occurred while fetching user position: " + e.getMessage());
            return sendResponse(userAni, 0, 0);
        }
	}
	
	public AirtimePositionResponse sendResponse(String userAni, Integer score, Integer position) {
		return AirtimePositionResponse.builder()
                .ani(userAni)
                .score(score)
                .position(position)
                .build();
		
	}
}
