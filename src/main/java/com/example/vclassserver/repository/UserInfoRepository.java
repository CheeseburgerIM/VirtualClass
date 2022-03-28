package com.example.vclassserver.repository;

import com.example.vclassserver.entity.vcuser_info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserInfoRepository extends JpaRepository<vcuser_info,Integer> {

    @Query("select i from vcuser_info i where i.username=?1")
    List<vcuser_info> getUserInfoByUsername(String username);

}
