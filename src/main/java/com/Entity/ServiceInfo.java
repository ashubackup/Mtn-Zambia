package com.Entity;
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
@Table(name="service_info")
public class ServiceInfo 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String game;
	@Column(name="gameId")
	private String gameid;
	private String status;
	private String imageUrl;
	private String gameUrl;//This is the demo one - without check
	private String gameUrlLive;
	private String subUrl;
	@Column(columnDefinition = "TEXT")
	private String description;
}