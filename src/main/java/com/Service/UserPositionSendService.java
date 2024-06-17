package com.Service;

import java.util.ArrayList;
import java.util.List;
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
	
	public AirtimePositionResponse sendUserPositionForDaily(String userAni) {
        try {
            Optional<List<Object[]>> optionalData = repo.getPositionScoreWise();

            List<AirtimeData> rankedAirtimeData = new ArrayList<>();
            if (optionalData.isPresent()) {
                List<Object[]> rawData = optionalData.get();
                int position = 1;
                for (Object[] record : rawData) {
                    String ani = (String) record[0];
                    int score = ((Number) record[1]).intValue();
                    System.out.println("ani"+ani);
                    rankedAirtimeData.add(new AirtimeData(ani, score, position++));
                }

                for (AirtimeData response : rankedAirtimeData) {
                    if (response.getAni().equalsIgnoreCase(userAni)) {
                        return sendResponse(response.getAni(), response.getScore(), response.getPosition());
                        
                    }
                }

                // User ANI not found in the ranked data
                return sendResponse(userAni, 0, 0);
            } else {
                // No data available for the current date
                return sendResponse(userAni, 0, 0);
            }
        } catch (Exception e) {
            System.err.println("An error occurred while fetching user position: " + e.getMessage());
            throw new RuntimeException("An error occurred while fetching user position", e);
        }
    }
	
	public AirtimePositionResponse sendUserPositionForMonthly(String userAni) {
        try {
            Optional<List<Object[]>> optionalData = repo.getPositionScoreMonthly();

            List<AirtimeData> rankedAirtimeData = new ArrayList<>();
            if (optionalData.isPresent()) {
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
            } else {
                // No data available for the current date
                return sendResponse(userAni, 0, 0);
            }
        } catch (Exception e) {
            System.err.println("An error occurred while fetching user position: " + e.getMessage());
            throw new RuntimeException("An error occurred while fetching user position", e);
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
