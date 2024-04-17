package com.Entity;
import java.time.LocalDateTime;
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
@Table(name="cash_data")
public class CashData 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String ani;
	private LocalDateTime dateTime;
	private LocalDateTime updateDateTime;
	private String game;
	private String gameId;
	private String name;
	private Integer score;
	private String status;
	private String service;
	private String serviceId;
}