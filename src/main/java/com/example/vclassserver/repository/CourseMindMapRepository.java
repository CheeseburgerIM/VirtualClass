package com.example.vclassserver.repository;

import com.example.vclassserver.embedded.vcourse_mindMap_pk;
import com.example.vclassserver.entity.vcourse_mindMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CourseMindMapRepository extends JpaRepository<vcourse_mindMap,Integer> {

    @Query("select m from vcourse_mindMap m where m.id.cid=?1")
    List<vcourse_mindMap> getAllMindMapNodeByCid(String cid);

    @Transactional
    @Modifying
    @Query("delete from vcourse_mindMap where id.cid=?1")
    void deleteAllMindMapNodeByCid(String cid);

    @Query("select m from vcourse_mindMap m where m.id=?1")
    List<vcourse_mindMap> findMindMapNodeById(vcourse_mindMap_pk id);

}
