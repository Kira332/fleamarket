package com.market.Dao;

import com.market.pojo.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationDao extends JpaRepository<Evaluation,Integer> {
    List<Evaluation> findAllBySellerId(String sellId);
}
