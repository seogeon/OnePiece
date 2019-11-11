package com.hanaset.onepiececommon.repository;

import com.hanaset.onepiececommon.entity.ExchangeEntity;
import com.hanaset.onepiececommon.model.NoticeExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends JpaRepository<ExchangeEntity, NoticeExchange> {
}
