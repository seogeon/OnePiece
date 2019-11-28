package com.hanaset.onepiececommon.repository;

import com.hanaset.onepiececommon.entity.ExchangeWalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeWalletRepository extends JpaRepository<ExchangeWalletEntity, Long> {
}
