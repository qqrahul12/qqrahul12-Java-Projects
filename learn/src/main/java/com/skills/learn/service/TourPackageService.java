package com.skills.learn.service;

import java.util.List;

import com.skills.learn.model.TourPackage;
import com.skills.learn.repository.TourPackageRepository;
import org.springframework.stereotype.Service;

@Service
public class TourPackageService {
    private TourPackageRepository tourPackageRepository;

    public TourPackageService(TourPackageRepository tourPackageRepository) {
        this.tourPackageRepository = tourPackageRepository;
    }

    public TourPackage createTourPackage(String code, String name) {
        return tourPackageRepository.findById(code)
                .orElse(tourPackageRepository.save(new TourPackage(code, name)));
    }

    public List<TourPackage> lookupAll() {
        return tourPackageRepository.findAll();
    }

    public long total() {
        return tourPackageRepository.count();
    }
}
