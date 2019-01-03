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

		int collSize = 0;

		// Actual location on disk for database files, process should have read-write
		// permissions to this folder
		String dbFilesLocation = "./src/main/resources/jsdata";

		// Java package name containing your POJO data classes, usually the one we are in...
		String baseScanPackage = "com.example.demo";

		JsonDBTemplate jsonDBonDisk = new JsonDBTemplate(dbFilesLocation, baseScanPackage);

		// start some data explorations
		// first, start fresh
		if (jsonDBonDisk.collectionExists("firstphrases")) {
			jsonDBonDisk.dropCollection("firstphrases");
		}
		if (jsonDBonDisk.collectionExists("secondphrases")) {
			jsonDBonDisk.dropCollection("secondphrases");
		}

		// Creating a collection if it does not exist
		jsonDBonDisk.createCollection(FirstPhrase.class);
		jsonDBonDisk.createCollection(SecondPhrase.class);

		// Save a document into a collection
		SecondPhrase thing1 = new SecondPhrase();
		thing1.setId("1");
		thing1.setPhrase("I am Second");
		jsonDBonDisk.insert(thing1);
		jsonDBonDisk.save(thing1, SecondPhrase.class);

		// Save a document into a collection
		FirstPhrase instance2 = new FirstPhrase();
		instance2.setId("2");
		instance2.setPhrase("Instance 2, Id 2");
		jsonDBonDisk.insert(instance2);
		jsonDBonDisk.save(instance2, FirstPhrase.class);

		// Inserting a document into a collection
		FirstPhrase instance10 = new FirstPhrase();
		instance10.setId("10");
		instance10.setPhrase("Artisanal");
		jsonDBonDisk.insert(instance10);

		// Inserting a document into a collection
		FirstPhrase instance1 = new FirstPhrase();
		instance1.setId("1");
		instance1.setPhrase("My Favorite");
		jsonDBonDisk.insert(instance1);

		// Upsert a document into a collection
		FirstPhrase instance3 = new FirstPhrase();
		instance3.setId("3");
		instance3.setPhrase("Ecto-tastic");

		jsonDBonDisk.upsert(instance3);

		// Upsert a document into a collection
		FirstPhrase instance4 = new FirstPhrase();
		instance4.setId("4");
		instance4.setPhrase("Fa Fa Ce La");
		jsonDBonDisk.upsert(instance4);

		// Upsert a document into a collection
		FirstPhrase instance5 = new FirstPhrase();
		instance5.setId("5");
		instance5.setPhrase("BAR-TO-FOO");
		jsonDBonDisk.upsert(instance5);

		// Upsert a document into a collection
		FirstPhrase instance6 = new FirstPhrase();
		instance6.setId("6");
		instance6.setPhrase("Iko Iko");
		jsonDBonDisk.upsert(instance6);

		// Upsert a document into a collection
		FirstPhrase instance7 = new FirstPhrase();
		instance7.setId("7");
		instance7.setPhrase("WallaWallaBingBang");
		jsonDBonDisk.upsert(instance7);

		// Upsert a document into a collection
		FirstPhrase instance8 = new FirstPhrase();
		instance8.setId("8");
		instance8.setPhrase("Fopp Shop");
		jsonDBonDisk.upsert(instance8);

		// Find a document when you know the Id
		FirstPhrase instance9 = jsonDBonDisk.findById("1", FirstPhrase.class);
		System.out.println();
		System.out.println("------- find qry by id value eq 11  ------");
		System.out.println("and print phrase: " + instance9.getPhrase());

		// fetch all them thingers what ya got
		System.out.println();
		System.out.println("------- find id value gt 0 -----");

		String jxQuery2 = String.format("/.[id>'%s']", "0");
		List<FirstPhrase> instances = jsonDBonDisk.find(jxQuery2, FirstPhrase.class);

		for (FirstPhrase item : instances) {
			System.out.println("Id: " + item.getId() +", "+ "Phrase: " + item.getPhrase() );
			collSize += 1;
			System.out.println("collSize is : " + collSize);
		}
		System.out.println();

		// find out how many words are in list
		int oneLength = collSize ;

		System.out.println("oneLength is : " + oneLength);

		// generate three random numbers, to pull random words from each list
		int rand1 = (int)(1 + Math.random() * oneLength);
		int rand2 = (int)(1 + Math.random() * oneLength);
		int rand3 = (int)(1 + Math.random() * oneLength);

		System.out.println("rand3 is : " + rand3);


		String rand1Id =  String.valueOf(rand1);
		String rand2Id =  "1";
		String rand3Id =  String.valueOf(rand3);

		// now build a phrase and tidy the text removing extra spaces piecemeal

		String phrase = jsonDBonDisk.findById(rand1Id,FirstPhrase.class).getPhrase()
				+ " " + jsonDBonDisk.findById(rand2Id,SecondPhrase.class).getPhrase()
				+ " " + jsonDBonDisk.findById(rand3Id,FirstPhrase.class).getPhrase();
		System.out.println("What we need is some more " + phrase.replace("\"", ""));

	}
}
