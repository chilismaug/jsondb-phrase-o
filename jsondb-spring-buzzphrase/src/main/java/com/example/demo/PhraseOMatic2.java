package com.example.demo;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import io.jsondb.JsonDBTemplate;


@Component("phraseBean")
public class PhraseOMatic2 {
   public static String makePhrase() throws FileNotFoundException {
	   
		// Actual location on disk for database files, process should have read-write
		// permissions to this folder
		String dbFilesLocation = "./src/main/resources/jsdata";

		// Java package name containing your POJO data classes, usually the one we are in...
		String baseScanPackage = "com.example.demo";
	   
		JsonDBTemplate jsonDBonDisk = new JsonDBTemplate(dbFilesLocation, baseScanPackage);
 
		List<Object> wordList1 = jsonDBonDisk.findAll("firstphrases"); 
		List<Object> wordList2 = jsonDBonDisk.findAll("secondphrases");	
		List<Object> wordList3 = jsonDBonDisk.findAll("thirdphrases");

		// find out how many words are in each list
		int oneLength = wordList1.size();
		int twoLength = wordList2.size();
		int threeLength = wordList3.size();

		// generate three random numbers, to pull random words from each list
		int rand1 = (int) (Math.random() * oneLength);
		int rand2 = (int) (Math.random() * twoLength);
		int rand3 = (int) (Math.random() * threeLength);
		
		String rand1Id = String.valueOf(rand1);
		String rand2Id = String.valueOf(rand2);
		String rand3Id = String.valueOf(rand3);

		// now build a phrase
		String phrase = jsonDBonDisk.findById(rand1Id, FirstPhrase.class).getPhrase() + " "
				+ jsonDBonDisk.findById(rand2Id, SecondPhrase.class).getPhrase() + " "
				+ jsonDBonDisk.findById(rand3Id, ThirdPhrase.class).getPhrase();

//		String phrase = wordList1.get(rand1). + " " + wordList2.get(rand2) + " " + wordList3.get(rand3)  + "." ;

		return ("What we need is a " + phrase);

	}
  }






