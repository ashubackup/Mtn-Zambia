package com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.TblUnsubsciption;

@Repository
public interface UnsubRepo extends JpaRepository<TblUnsubsciption, Integer>
{

}
