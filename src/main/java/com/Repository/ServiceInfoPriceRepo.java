package com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.ServiceInfoPrice;

@Repository
public interface ServiceInfoPriceRepo extends JpaRepository<ServiceInfoPrice, Integer>
{
	ServiceInfoPrice findByPack(String pack);
}
