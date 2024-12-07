package com.skills.SkillBoost.repository;

import com.skills.SkillBoost.model.TourPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TourPackageRepository extends JpaRepository<TourPackage, String> {
    Optional<TourPackage> findByName(String name);
}
