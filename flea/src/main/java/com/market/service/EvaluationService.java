package com.market.service;

import com.market.Dao.EvaluationDao;
import com.market.pojo.Evaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class EvaluationService {

    @Autowired
    EvaluationDao evaluationDao;

    public void saveEvaluation(Evaluation evaluation){
        if(evaluation.getContext()==null){
            evaluation.setContext("默认好评");
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        evaluation.setTime(timestamp);
        evaluationDao.save(evaluation);
    }

    public List<Evaluation> findAllEvaluationBySellerId(String openId){
        return evaluationDao.findAllBySellerId(openId);
    }

}
