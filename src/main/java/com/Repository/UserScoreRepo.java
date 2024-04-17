package com.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Entity.UserScore;

@Repository
public interface UserScoreRepo extends JpaRepository<UserScore,Integer>
{

}