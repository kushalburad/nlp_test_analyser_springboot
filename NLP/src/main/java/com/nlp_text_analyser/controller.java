package com.nlp_text_analyser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class controller {

    @Autowired
    public text_analyser text_analyser;

    @GetMapping("/text_analyser")
    public String word_counterForm(Model model) {
        model.addAttribute("word_counter", new text_analyser());
        return "text_analyser";
    }

    @PostMapping("/text_analyser")
    public String word_counterSubmit(@RequestParam(value = "file", required = false) MultipartFile file,
                                     @ModelAttribute text_analyser word_counter, Model model) throws IOException {
        model.addAttribute("word_counter", word_counter);
        return "result";
    }

}