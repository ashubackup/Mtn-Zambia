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
@Table(name="tbl_sub_logs")
public class sub_api_logs 
{

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Integer id;
	private String ani;
	@Column(columnDefinition = "TEXT")
	private String request;
	@Column(columnDefinition = "TEXT")
	private String response;
	private LocalDateTime datetime;
	private String type;
}
