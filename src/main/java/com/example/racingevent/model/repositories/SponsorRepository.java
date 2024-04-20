package com.example.racingevent.model.repositories;

import com.example.racingevent.model.entity.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SponsorRepository extends JpaRepository<Sponsor, Long> {
}
