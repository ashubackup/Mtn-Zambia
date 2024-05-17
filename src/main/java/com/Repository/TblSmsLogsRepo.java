package com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.TblSmsLogs;


@Repository
public interface TblSmsLogsRepo extends JpaRepository<TblSmsLogs, Integer>{

}
