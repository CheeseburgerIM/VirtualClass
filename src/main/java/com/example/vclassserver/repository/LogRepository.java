package com.example.vclassserver.repository;

import com.example.vclassserver.entity.vclog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LogRepository extends JpaRepository<vclog,Integer> {

    @Query("select l from vclog l where l.lid=?1")
    List<vclog> findLogByContent(String lid);

    @Query("select l from vclog l where l.cid=?1 order by l.timestamp desc")
    List<vclog> findAllLogOrderByTimeDesc(String cid);

    @Query(value = "select * from vclog where cid=?1 order by timestamp desc limit 100",nativeQuery = true)
    List<vclog> findTopHundredLogOrderByTimeDesc(String cid);

}
