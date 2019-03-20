package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import io.jsondb.JsonDBTemplate;

@Component
public class Bootstrapper implements CommandLineRunner {

    @Override
    public void run(String... strings) throws Exception {

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
		if (jsonDBonDisk.collectionExists("thirdphrases")) {
			jsonDBonDisk.dropCollection("thirdphrases");
		}

		// Creating a collection if it does not exist
		jsonDBonDisk.createCollection(FirstPhrase.class);
		jsonDBonDisk.createCollection(SecondPhrase.class);
		jsonDBonDisk.createCollection(ThirdPhrase.class);

		// Save a document into a collection
		FirstPhrase instance2 = new FirstPhrase();
		instance2.setId("2");
		instance2.setPhrase("FirstPhrase Value in Instance 2, Id 2");
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

		// Save a document into a collection
		SecondPhrase thing1 = new SecondPhrase();
		thing1.setId("1");
		thing1.setPhrase("I am Second");
		jsonDBonDisk.insert(thing1);
		jsonDBonDisk.save(thing1, SecondPhrase.class);

		// Save a document into a collection, same instance var?
		thing1.setId("2");
		thing1.setPhrase("I am 101, No make that 2");
		jsonDBonDisk.insert(thing1);
		jsonDBonDisk.save(thing1, SecondPhrase.class);

		// Save a document into a collection, same instance var?
		thing1.setId("3");
		thing1.setPhrase("I am 4");
		jsonDBonDisk.insert(thing1);
		jsonDBonDisk.save(thing1, SecondPhrase.class);

		// Save a document into a collection, same instance var? yep
		thing1.setId("4");
		thing1.setPhrase("Hokey-Dokey-Schmokey");
		jsonDBonDisk.insert(thing1);
		jsonDBonDisk.save(thing1, SecondPhrase.class);

		// Save a document into a collection
		ThirdPhrase thingthang = new ThirdPhrase();
		thingthang.setId("1");
		thingthang.setPhrase("One Hand Clapping");
		jsonDBonDisk.upsert(thingthang );

		thingthang.setId("2");
		thingthang.setPhrase("Dos Crickets");
		jsonDBonDisk.upsert(thingthang );

		thingthang.setId("3");
		thingthang.setPhrase("Three Legs Crossing");
		jsonDBonDisk.upsert(thingthang );

    }
}
