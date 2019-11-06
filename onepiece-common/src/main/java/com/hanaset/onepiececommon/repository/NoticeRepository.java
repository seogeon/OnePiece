package com.hanaset.onepiececommon.repository;

import com.hanaset.onepiececommon.entity.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeEntity, Long> {

    @Query(value = "SELECT Max(notice_id) from Onepiece.TB_OP_NOTICE where exchange = ?1", nativeQuery = true)
    Optional<Integer> getMaxNoticeId(String noticeExchange);
}
