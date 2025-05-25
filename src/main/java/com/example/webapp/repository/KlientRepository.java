package com.example.webapp.repository;

import com.example.webapp.model.Klient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KlientRepository extends JpaRepository<Klient, Long> {

    @Query("SELECT k FROM Klient k " +
            "WHERE LOWER(k.imie) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(k.nazwisko) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR k.numerTelefonu LIKE CONCAT('%', :query, '%')")
    List<Klient> search(@Param("query") String query);

    List<Klient> findAllByOrderByImieAsc(); // domyślne sortowanie alfabetyczne
}
