package com.skills.SkillBoost.service;

import com.skills.SkillBoost.model.Difficulty;
import com.skills.SkillBoost.model.Region;
import com.skills.SkillBoost.model.Tour;
import com.skills.SkillBoost.model.TourPackage;
import com.skills.SkillBoost.repository.TourPackageRepository;
import com.skills.SkillBoost.repository.TourRepository;
import org.springframework.stereotype.Service;

@Service
public class TourService {
    private final TourPackageRepository tourPackageRepository;
    private final TourRepository tourRepository;

    public TourService(TourPackageRepository tourPackageRepository, TourRepository tourRepository) {
        this.tourPackageRepository = tourPackageRepository;
        this.tourRepository = tourRepository;
    }

    public Tour createTour(String tourPackageName, String title,
                           String description, String blurb, Integer price, String duration,
                           String bullets, String keywords, Difficulty difficulty, Region region) {

        TourPackage tourPackage = tourPackageRepository.findByName(tourPackageName)
                .orElseThrow(() -> new RuntimeException("Tour Package not found for id:" + tourPackageName));
        return tourRepository.save(new Tour(title, description, blurb,
                price, duration, bullets, keywords, tourPackage, difficulty, region));
    }

    public long total() {
        return tourRepository.count();
    }
}
