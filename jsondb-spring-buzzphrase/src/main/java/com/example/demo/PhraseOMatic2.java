package com.example.demo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import io.jsondb.JsonDBTemplate;


public class PhraseOMatic2 {
   public static String makePhrase() throws FileNotFoundException {
	   
		// Actual location on disk for database files, process should have read-write
		// permissions to this folder
		String dbFilesLocation = "./src/main/resources/jsdata";

		// Java package name containing your POJO data classes, usually the one we are in...
		String baseScanPackage = "com.example.demo";
	   
		JsonDBTemplate jsonDBonDisk = new JsonDBTemplate(dbFilesLocation, baseScanPackage);

 
		List<String> wordList1 = jsonDBonDisk.findAll("firstphrases");
		List<String> wordList2 = jsonDBonDisk.findAll("secondphrases");	
		List<String> wordList3 = jsonDBonDisk.findAll("thirdphrases");

		// find out how many words are in each list
		int oneLength = wordList1.size();
		int twoLength = wordList2.size();
		int threeLength = wordList3.size();

		/*
		 * int oneLength = wordListOne.length; int twoLength = wordListTwo.length; int
		 * threeLength = wordListThree.length;
		 */

		// generate three random numbers, to pull random words from each list
		int rand1 = (int) (Math.random() * oneLength);
		int rand2 = (int) (Math.random() * twoLength);
		int rand3 = (int) (Math.random() * threeLength);

		// now build a phrase

		String phrase = wordList1.get(rand1) + " " + wordList2.get(rand2) + " " + wordList3.get(rand3) + "." ;

		// String phrase = wordListOne[rand1] + " " + wordListTwo[rand2] + " " +
		// wordListThree[rand3] + ".";
		// now return it

		return ("What we need is a " + phrase);

	}
  }






