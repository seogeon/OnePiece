package com.hanaset.onepiececommon.repository;

import com.hanaset.onepiececommon.entity.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeEntity, Long> {

    @Query(value = "SELECT Max(notice_id) from Onepiece.TB_OP_NOTICE INNER JOIN TB_OP_EXCHANGE TOE on TB_OP_NOTICE.exchange_code = TOE.code where TOE.code = ?1", nativeQuery = true)
    Optional<BigDecimal> getMaxNoticeId(String noticeExchange);

    @Query(value = "SELECT Max(notice_id) from Onepiece.TB_OP_NOTICE INNER JOIN TB_OP_EXCHANGE TOE on TB_OP_NOTICE.exchange_code = TOE.code where TOE.code = ?1 and kind =?2", nativeQuery = true)
    Optional<BigDecimal> getMaxNoticeIdAndKind(String noticeExchange, String noiceKind);

}
