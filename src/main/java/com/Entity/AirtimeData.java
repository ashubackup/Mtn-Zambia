package com.Entity;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="airtime_data")
public class AirtimeData 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String ani;
	private LocalDateTime dateTime;
	private String game;
	private String gameId;
	private String name;
	private Integer score;
	private String status;
	private String serviceId;
	private String service;
	@Column(columnDefinition="TEXT")
	private String request;
	@Column(columnDefinition="TEXT")
	private String response;
}