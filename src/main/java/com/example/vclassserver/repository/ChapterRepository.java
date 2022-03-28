package com.example.vclassserver.repository;

import com.example.vclassserver.entity.vcourse_chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ChapterRepository extends JpaRepository<vcourse_chapter,Integer> {

    @Query("select c from vcourse_chapter c where c.chid=?1")
    List<vcourse_chapter> findChapterById(String chid);

    @Query("select c from vcourse_chapter c where c.vcourse.cid=?1 order by c.chnum")
    List<vcourse_chapter> findAllChapterByCourseId(String cid);

    @Transactional
    @Modifying
    @Query("delete from vcourse_chapter c where c.vcourse.cid=?1")
    void deleteAllChapterByCourseId(String cid);

}
