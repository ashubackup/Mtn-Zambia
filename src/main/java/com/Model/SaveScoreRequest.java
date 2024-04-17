package com.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveScoreRequest 
{
	private String ani;
	private String gameId;
	private String score;
	private String serviceId;
}