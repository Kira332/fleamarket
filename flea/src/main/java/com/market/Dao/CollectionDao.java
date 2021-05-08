package com.market.Dao;

import com.market.pojo.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Repository
public interface CollectionDao extends JpaRepository<Collection,Integer> {
    List<Collection> findAllByUserId(String userId);

}
