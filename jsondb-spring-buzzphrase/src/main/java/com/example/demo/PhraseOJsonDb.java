package com.example.demo;

import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import io.jsondb.JsonDBTemplate;


@Component
public class PhraseOJsonDb implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		main(args);
	}

	public static void main(String[] args) {

	   	// Actual location on disk for database files, process should have read-write
			// permissions to this folder
			String dbFilesLocation = "./src/main/resources/jsdata";

			// Java package name containing your POJO data classes, usually the one we are in...
			String baseScanPackage = "com.example.demo";

			JsonDBTemplate jsonDBonDisk = new JsonDBTemplate(dbFilesLocation, baseScanPackage);

		int collSize = 0;

		com.example.demo.Bootstrapper

		// Find a document when you know the Id

		com.example.demo.FirstPhrase instance9 = jsonDBonDisk.findById("1", FirstPhrase.class);
		System.out.println();
		System.out.println("------- find qry by id value eq 11  ------");
		System.out.println("and print phrase: " + thing1.getPhrase());

		// fetch all them thingers what ya got
		System.out.println();
		System.out.println("------- find id value gt 0 -----");

		String jxQuery2 = String.format("/.[id>'%s']", "0");
		List<FirstPhrase> instances = jsonDBonDisk.find(jxQuery2, FirstPhrase.class);

		for (FirstPhrase item : instances) {
			System.out.println("Id: " + item.getId() +", "+ "Phrase: " + item.getPhrase() );
			colnSize += 1;
			System.out.println("collSize is : " + colnSize);
		}
		System.out.println();
		// find out how many words are in list
		int oneLength = jsonDBonDisk.getCollection(FirstPhrase.class).size();
		System.out.println("oneLength is : " + oneLength);
		int twoLength = jsonDBonDisk.getCollection(SecondPhrase.class).size();
		int threeLength = jsonDBonDisk.getCollection(ThirdPhrase.class).size();

		// generate three random numbers, to pull random words from each list
		int rand1 = (int)(1 + Math.random() * oneLength);
		int rand2 = (int)(1 + Math.random() * twoLength);
		int rand3 = (int)(1 + Math.random() * threeLength);

		System.out.println("rand3 is : " + rand3);


		String rand1Id =  String.valueOf(rand1);
		String rand2Id =  String.valueOf(rand2);
		String rand3Id =  String.valueOf(rand3);

		// now build a phrase and tidy the text removing extra spaces piecemeal

		String phrase = jsonDBonDisk.findById(rand1Id,FirstPhrase.class).getPhrase()
				+ " " + jsonDBonDisk.findById(rand2Id,SecondPhrase.class).getPhrase()
				+ " " + jsonDBonDisk.findById(rand3Id,ThirdPhrase.class).getPhrase();
		System.out.println("What we need is some more " + phrase.replace("\"", ""));

	}
}
