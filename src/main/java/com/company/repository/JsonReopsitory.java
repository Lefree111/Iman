package com.company.repository;

import com.company.dto.JsonDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JsonReopsitory extends JpaRepository<JsonDTO, Integer> {
    Optional<JsonDTO> findById(Integer id);
}
