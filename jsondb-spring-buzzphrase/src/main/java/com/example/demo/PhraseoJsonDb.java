package com.example.demo;

import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import io.jsondb.JsonDBTemplate;

@Component
public class PhraseoJsonDb implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		main(args);
	}

	public static void main(String[] args) {

		// Location on disk for database files; process should have read-write permissions to this folder
		String dbFilesLocation = "./src/main/resources/jsdata";

		// Java package name containing your POJO data classes; usually the one we are in...
		String baseScanPackage = "com.example.demo";

		JsonDBTemplate jsonDBonDisk = new JsonDBTemplate(dbFilesLocation, baseScanPackage);
		// drop collection to start clean
		if (jsonDBonDisk.collectionExists("firstphrases")) {
			jsonDBonDisk.dropCollection("firstphrases");
		}
		if (jsonDBonDisk.collectionExists("secondphrases")) {
			jsonDBonDisk.dropCollection("secondphrases");
		}

		// Creating collection 
		jsonDBonDisk.createCollection(FirstPhrase.class);
		jsonDBonDisk.createCollection(SecondPhrase.class);


		// Save a document into a collection
		FirstPhrase thing1 = new FirstPhrase();
		thing1.setId("2");
		thing1.setPhrase("Instance 2, Id 2");
		jsonDBonDisk.insert(thing1);
 
		thing1 = new FirstPhrase();
		thing1.setId("10");
		thing1.setPhrase("Artisanal");
		jsonDBonDisk.insert(thing1);
 
		thing1 = new FirstPhrase();
		thing1.setId("1");
		thing1.setPhrase("My Favorite");
		jsonDBonDisk.insert(thing1);
 
		thing1 = new FirstPhrase();
		thing1.setId("3");
		thing1.setPhrase("Ecto-tastic");
		jsonDBonDisk.upsert(thing1);
 
		thing1 = new FirstPhrase();
		thing1.setId("4");
		thing1.setPhrase("Fa Fa Ce La");
		jsonDBonDisk.upsert(thing1);
 
		thing1 = new FirstPhrase();
		thing1.setId("5");
		thing1.setPhrase("BAR-TO-FOO");
		jsonDBonDisk.upsert(thing1);
 
		thing1 = new FirstPhrase();
		thing1.setId("6");
		thing1.setPhrase("Iko Iko");
		jsonDBonDisk.upsert(thing1);
 
		thing1 = new FirstPhrase();
		thing1.setId("7");
		thing1.setPhrase("WallaWallaBingBang");
		jsonDBonDisk.upsert(thing1);
 
		thing1 = new FirstPhrase();
		thing1.setId("8");
		thing1.setPhrase("Fopp Shop");
		jsonDBonDisk.upsert(thing1);
		
		
		// Save a document into a collection
		SecondPhrase thing2 = new SecondPhrase();
		thing2.setId("1");
		thing2.setPhrase("I am Second");
		jsonDBonDisk.insert(thing2);
		
		thing2 = new SecondPhrase();
		thing2.setId("2");
		thing2.setPhrase("De Doo Da Da");
		jsonDBonDisk.insert(thing2);
		
		thing2 = new SecondPhrase();
		thing2.setId("3");
		thing2.setPhrase("Mako Mako");
		jsonDBonDisk.insert(thing2);
		
		thing2 = new SecondPhrase();
		thing2.setId("4");
		thing2.setPhrase("Fahrfegnuven");
		jsonDBonDisk.insert(thing2);

		int colctnSize = 0;


		// fetch all them thingers what ya got
		System.out.println();
		System.out.println("------- find id value gt 0 in firstphrases colctn-----");

		String jxQuery2 = String.format("/.[id>'%s']", "0");
		List<FirstPhrase> firstphrases = jsonDBonDisk.find(jxQuery2, FirstPhrase.class);

		for (FirstPhrase item : firstphrases) {
			System.out.println("Id: " + item.getId() + ", " + "Phrase: " + item.getPhrase());
			colctnSize += 1;
			System.out.println("colctnSize is : " + colctnSize);
		}
		System.out.println();
		/* 		
		//	Find a document when you know the Id
		thing1 = jsonDBonDisk.findById("1", FirstPhrase.class);
		System.out.println();
		System.out.println("------- find qry by id value eq 1 ------");
		System.out.println("and print phrase: " + thing1.getPhrase());		
		*/
		
		// find out how many words are in list
		int oneLength = jsonDBonDisk.getCollection(FirstPhrase.class).size();
		System.out.println("colctn oneLength is : " + oneLength);
		int twoLength = jsonDBonDisk.getCollection(SecondPhrase.class).size();
		int threeLength = jsonDBonDisk.getCollection(ThirdPhrase.class).size();

		// generate three random numbers, to pull random words from each list
		int rand1 = (int) (1 + Math.random() * oneLength);
		int rand2 = (int) (1 + Math.random() * twoLength);
		int rand3 = (int) (1 + Math.random() * threeLength);

		System.out.println("rand3 is : " + rand3);

		String rand1Id = String.valueOf(rand1);
		String rand2Id = String.valueOf(rand2);
		String rand3Id = String.valueOf(rand3);

		// now build a phrase and tidy the text 

		System.out.println("\n FirstPhrase.class is: " +  FirstPhrase.class.toString() );

		String phrase = jsonDBonDisk.findById(rand1Id, FirstPhrase.class).getPhrase() + " "
				+ jsonDBonDisk.findById(rand2Id, SecondPhrase.class).getPhrase() + " "
				+ jsonDBonDisk.findById(rand3Id, ThirdPhrase.class).getPhrase();
		System.out.println("What we need is some more " + phrase.replace("\"", ""));
		System.out.println(".");

	}
}
