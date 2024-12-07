package com.skills.SkillBoost;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skills.SkillBoost.model.Difficulty;
import com.skills.SkillBoost.model.Region;
import com.skills.SkillBoost.service.TourPackageService;
import com.skills.SkillBoost.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static com.skills.SkillBoost.util.Pattern.printHalfPyramid;

@SpringBootApplication
public class SkillBoostApplication implements CommandLineRunner {

	private final String TOUR_IMPORT_FILE = "ExploreCalifornia.json";

	@Autowired
	private TourPackageService tourPackageService;

	@Autowired
	private TourService tourService;

	public static void main(String[] args) {
		SpringApplication.run(SkillBoostApplication.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println("Hello World");
		Scanner scanner = new Scanner(System.in);
		String a = scanner.nextLine();
		System.out.println("You entered: " + a);
		int[] b = Arrays.stream(a.split(" ")).mapToInt(Integer::parseInt).toArray();
		System.out.println("Sum is: " + (b[0] + b[1]));
		printHalfPyramid(5);
	}

	/**
	 * Initialize all the known tour packages
	 */
	private void createTourAllPackages() {
		tourPackageService.createTourPackage("BC", "Backpack Cal");
		tourPackageService.createTourPackage("CC", "California Calm");
		tourPackageService.createTourPackage("CH", "California Hot springs");
		tourPackageService.createTourPackage("CY", "Cycle California");
		tourPackageService.createTourPackage("DS", "From Desert to Sea");
		tourPackageService.createTourPackage("KC", "Kids California");
		tourPackageService.createTourPackage("NW", "Nature Watch");
		tourPackageService.createTourPackage("SC", "Snowboard Cali");
		tourPackageService.createTourPackage("TC", "Taste of California");
	}

	/**
	 * Create tour entities from an external file
	 */
	private void createToursFromFile(String fileToImport) throws IOException {
		TourFromFile.read(fileToImport).forEach(t ->
				tourService.createTour(
						t.packageName(),
						t.title(),
						t.description(),
						t.blurb(),
						t.price(),
						t.length(),
						t.bullets(),
						t.keywords(),
						Difficulty.valueOf(t.difficulty()),
						Region.findByLabel(t.region())
				)
		);
	}

	/*
	 * Helper to import ExploreCali.json
	 */
	record TourFromFile(String packageName, String title, String description,
						String blurb, Integer price, String length, String bullets,
						String keywords, String difficulty, String region) {
		static List<TourFromFile> read(String fileToImport) throws IOException {
			return new ObjectMapper().readValue(new File(fileToImport),
					new TypeReference<List<TourFromFile>>() {});
		}
	}

}
