package com.example.vclassserver.repository;

import com.example.vclassserver.entity.vclassuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<vclassuser,Integer> {

    @Query("select u from vclassuser u where u.username=?1")
    List<vclassuser> findUserByUsername(String username);

}
