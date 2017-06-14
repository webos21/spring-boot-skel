package com.gmail.webos21.spring.skel.db.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gmail.webos21.spring.skel.db.domain.IdVal;

@Repository
public interface IdValRepository extends JpaRepository<IdVal, String> {

}
