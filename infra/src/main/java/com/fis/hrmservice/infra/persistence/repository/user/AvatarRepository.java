package com.fis.hrmservice.infra.persistence.repository.user;

import com.fis.hrmservice.infra.persistence.entity.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Long> {

  @Query("SELECT a FROM Avatar a WHERE a.user.id = :userId")
  Avatar findAvatarByUserId(@Param("userId") Long userId);

  @Query("""
       SELECT a
       FROM Avatar a
       WHERE a.user.id IN :userIds
       """)
  List<Avatar> findByUserUserIdIn(@Param("userIds") List<Long> userIds);
}