package com.market.controller;

import com.market.pojo.Evaluation;
import com.market.result.Result;
import com.market.result.ResultFactory;
import com.market.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EvaluationController {
    @Autowired
    EvaluationService evaluationService;

    //评论商品
    @PostMapping("/user/evaluation")
    public Result evaluate(@RequestBody Evaluation evaluation){
        evaluationService.saveEvaluation(evaluation);
        return ResultFactory.buildSuccessResult("评论商品成功", "");
    }

    //打开一个用户的评论列表
    @PostMapping("/user/myevaluations")
    public Result evaluationList(String sellerId){
        List<Evaluation> evaluationList=evaluationService.findAllEvaluationBySellerId(sellerId);
        return ResultFactory.buildSuccessResult("打开评论列表成功", evaluationList);
    }


}
