package com.example.vclassserver.repository;

import com.example.vclassserver.entity.vcfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface FileRepository extends JpaRepository<vcfile,Integer> {

    @Query("select f from vcfile f where f.cid=?1")
    List<vcfile> getAllFileByCourseId(String cid);

    @Query("select f from vcfile f where f.fid=?1")
    List<vcfile> getFileById(String fid);

    @Transactional
    @Modifying
    @Query("delete from vcfile where fid=?1")
    void deleteFileById(String fid);

}
