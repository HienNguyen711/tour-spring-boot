package com.example.ec.project;

import com.example.ec.project.domain.Difficulty;
import com.example.ec.project.domain.Region;
import com.example.ec.project.services.TourPackageService;
import com.example.ec.project.services.TourService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;

import static com.example.ec.project.ProjectApplication.TourFromFile.importTours;


@SpringBootApplication
public class ProjectApplication implements CommandLineRunner{

	//main method
	@Autowired
	private TourPackageService tourPackageService;

	@Autowired
	private TourService tourService;

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}



	//run


	@Override
	public void run(String... strings) throws Exception {
		// generate fake dump data for tourPackage from tourPackageService


		//Create the default tour packages
		tourPackageService.createTourPackage("BC", "Backpack Cal");
		tourPackageService.createTourPackage("CC", "California Calm");
		tourPackageService.createTourPackage("CH", "California Hot springs");
		tourPackageService.createTourPackage("CY", "Cycle California");
		tourPackageService.createTourPackage("DS", "From Desert to Sea");
		tourPackageService.createTourPackage("KC", "Kids California");
		tourPackageService.createTourPackage("NW", "Nature Watch");
		tourPackageService.createTourPackage("SC", "Snowboard Cali");
		tourPackageService.createTourPackage("TC", "Taste of California");
		System.out.println("Number of tours packages =" + tourPackageService.total());


		//Persist the Tours to the database
		importTours().forEach(t-> tourService.createTour(
				t.title,
				t.description,
				t.blurb,
				Integer.parseInt(t.price),
				t.length,
				t.bullets,
				t.keywords,
				t.packageType,
				Difficulty.valueOf(t.difficulty),
				Region.findByLabel(t.region)));
		System.out.println("Number of tours =" + tourService.total());



	}


	//TODO HELPER FILE

	/**
	 * HELPER CLASS TO IMPORT THE RECORDS IN THE DATA.JSON
	 */
	static class TourFromFile {
		//attributes as listed in the .json file


		/**
		 * open the data.json file , unmarshal every entry into a tourfromfile object
		 *
		 * return a list of tourFromFile objects
		 * throw IOException if object mapper unable to open file
		 */

		//attributes as listed in the .json file
		private String packageType, title, description, blurb, price, length, bullets, keywords,  difficulty, region;

		/**
		 * Open the ExploreCalifornia.json, unmarshal every entry into a TourFromFile Object.
		 *
		 * @return a List of TourFromFile objects.
		 * @throws IOException if ObjectMapper unable to open file.
		 */
		static List<TourFromFile> importTours() throws IOException {
			return new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY).
					readValue(TourFromFile.class.getResourceAsStream("/data.json"),new TypeReference<List<TourFromFile>>(){});
		}


	}





}
