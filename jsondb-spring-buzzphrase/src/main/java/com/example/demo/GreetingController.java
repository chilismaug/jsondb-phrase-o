package com.example.demo;

import org.springframework.stereotype.Controller;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {
	
	@Value("#{phraseBean.makePhrase()}")
	private String phrase;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) throws FileNotFoundException  {
        model.addAttribute("name", name);
        model.addAttribute("phrase", PhraseOMatic2.makePhrase() );
        return "greeting";
    }

}
