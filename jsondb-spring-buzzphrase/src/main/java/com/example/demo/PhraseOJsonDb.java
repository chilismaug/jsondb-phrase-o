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

		int colnSize = 0;

		// Actual location on disk for database files, process should have read-write
		// permissions to this folder
		String dbFilesLocation = "./src/main/resources/jsdata";

		// Java package name containing your POJO data classes, usually the one we are in...
		String baseScanPackage = "com.example.demo";

		JsonDBTemplate jsonDBonDisk = new JsonDBTemplate(dbFilesLocation, baseScanPackage);

		// first, drop old and create new data collections
		if (jsonDBonDisk.collectionExists("firstphrases")) {
			jsonDBonDisk.dropCollection("firstphrases");
		}
		if (jsonDBonDisk.collectionExists("secondphrases")) {
			jsonDBonDisk.dropCollection("secondphrases");
		}
		if (jsonDBonDisk.collectionExists("thirdphrases")) {
			jsonDBonDisk.dropCollection("thirdphrases");
		}
		jsonDBonDisk.createCollection(FirstPhrase.class);
		jsonDBonDisk.createCollection(SecondPhrase.class);
		jsonDBonDisk.createCollection(ThirdPhrase.class);
		

		// Add documents into collections

		FirstPhrase thing1 = new FirstPhrase();
		thing1.setId("1");
		thing1.setPhrase("My Favorite");
		jsonDBonDisk.insert(thing1);		

		thing1.setId("2");
		thing1.setPhrase("Onceler El Secondo");
		jsonDBonDisk.insert(thing1);
 
		thing1.setId("3");
		thing1.setPhrase("Ecto-tastic");
		jsonDBonDisk.upsert(thing1);
		
		thing1.setId("4");
		thing1.setPhrase("Fa Fa Ce La");
		jsonDBonDisk.upsert(thing1);

		thing1.setId("5");
		thing1.setPhrase("BAR-TO-FOO");
		jsonDBonDisk.upsert(thing1);

		thing1.setId("6");
		thing1.setPhrase("Iko Iko");
		jsonDBonDisk.upsert(thing1);

		thing1.setId("7");
		thing1.setPhrase("WallaWallaBingBang");
		jsonDBonDisk.upsert(thing1);

		thing1.setId("8");
		thing1.setPhrase("Fopp Shop");
		jsonDBonDisk.upsert(thing1);

		thing1.setId("9");
		thing1.setPhrase("Number 9");
		jsonDBonDisk.insert(thing1);

		thing1.setId("10");
		thing1.setPhrase("Artisanal");
		jsonDBonDisk.insert(thing1);
		
		SecondPhrase thing2 = new SecondPhrase();
		thing2.setId("1");
		thing2.setPhrase("I am Second the 1st");
		jsonDBonDisk.insert(thing2);

		thing2.setId("2");
		thing2.setPhrase("Secondo the II");
		jsonDBonDisk.insert(thing2);
 
		thing2.setId("3");
		thing2.setPhrase("Dualistic Dichotomizer");
		jsonDBonDisk.upsert(thing2);
		
		thing2.setId("4");
		thing2.setPhrase("Fa Fa Ce La C'est Deux");
		jsonDBonDisk.upsert(thing2);
		
		ThirdPhrase thing3 = new ThirdPhrase();
		thing3.setId("1");
		thing3.setPhrase("Three is me");
		jsonDBonDisk.insert(thing3);

		thing3.setId("2");
		thing3.setPhrase("Thriceler El Secondo");
		jsonDBonDisk.insert(thing3);
 
		thing3.setId("3");
		thing3.setPhrase("Trifecto-tastic");
		jsonDBonDisk.upsert(thing3);
		
		thing3.setId("4");
		thing3.setPhrase("Da Da Da");
		jsonDBonDisk.upsert(thing3);
		

		// Find a document when you know the Id
		thing1 = jsonDBonDisk.findById("1", FirstPhrase.class);
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
