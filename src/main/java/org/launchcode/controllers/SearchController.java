package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    @RequestMapping(value = "results", method = RequestMethod.POST)
    public String searchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {

        // use findByCandV for searchterms other than all
        if (!searchType.equalsIgnoreCase("all")){
            ArrayList<HashMap<String, String>> jobs = JobData.findByColumnAndValue(searchType,searchTerm);
            boolean isJobsEmpty = jobs.isEmpty();
            int jobSize = jobs.size();
            model.addAttribute("title", "Jobs with " + searchType + ": " + searchTerm);
            model.addAttribute("jobs", jobs);
            model.addAttribute("isJobsEmpty", isJobsEmpty);
            model.addAttribute("jobSize", jobSize);
            model.addAttribute("columns", ListController.columnChoices);


        }else{
            ArrayList<HashMap<String,String>> jobs = JobData.findByValue(searchTerm);
            boolean isJobsEmpty = jobs.isEmpty();
            int jobSize = jobs.size();
            model.addAttribute("title", "Jobs with " + searchType + ": " + searchTerm);
            model.addAttribute("jobs", jobs);
            model.addAttribute("isJobsEmpty", isJobsEmpty);
            model.addAttribute("jobSize", jobSize);
            model.addAttribute("columns", ListController.columnChoices);


        }return "search";
    }
}
