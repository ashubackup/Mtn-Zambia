package com.Entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_unsubscription")
public class TblUnsubsciption {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String ani;
	private String amount;
	private LocalDateTime processDateTime;
	private LocalDateTime subDateTime;
	private LocalDateTime unsubDateTime;
	private String m_act;
	private String m_deact;
	private String pack;
	private String service;
	private String status;
}
