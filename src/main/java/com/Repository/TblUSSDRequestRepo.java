package com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.TblRequestUSSD;

@Repository
public interface TblUSSDRequestRepo extends JpaRepository<TblRequestUSSD, Long>
{

}
