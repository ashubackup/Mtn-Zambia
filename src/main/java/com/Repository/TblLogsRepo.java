package com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.sub_api_logs;

@Repository
public interface TblLogsRepo extends JpaRepository<sub_api_logs, Integer>
{

}
