package com.anderiana.avanade.repository;

import com.anderiana.avanade.entity.Batalha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatalhaRepository extends JpaRepository<Batalha, Long> {
}


