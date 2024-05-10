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
@Table(name="tbl_subscription")
public class TblSubscription 
{

	private String ani;
	private String mode;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String amount;
	private LocalDateTime sub_date_time;
	private LocalDateTime next_billed_date;
	private LocalDateTime last_billed_date;
	private String status;
	private String pack;
}
