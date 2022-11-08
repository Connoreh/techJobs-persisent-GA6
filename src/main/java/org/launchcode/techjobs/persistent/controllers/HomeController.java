package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping
public class HomeController {

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private SkillRepository skillRepository;

    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("title", "My Jobs");
        model.addAttribute("id", employerRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());

        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {

        model.addAttribute("title", "Add Job");
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());
//        model.addAttribute("job", jobRepository.findAll());
        model.addAttribute(new Job());
        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                       Errors errors, Model model, @RequestParam int employerId, @RequestParam List<Integer> skills) {

        if (errors.hasErrors()) {
//            System.out.println(errors);
            model.addAttribute("title", "Add Job");
            return "add";
        }

//        Optional<Skill> result =  skillRepository.findById(skillId);
//        Skill employer = result.get();
//        model.addAttribute("title", "Add Skill to: " + employer.getName());
//        model.addAttribute("skills", skillRepository.findAll());
//        model.addAttribute("job",employer);
//        model.addAttribute(new Skill());

        Optional<Employer> employerResult = employerRepository.findById(employerId);
        if (employerResult.isPresent()) {
            Employer optEmployer = employerResult.get();
            newJob.setEmployer(optEmployer);
        }

//        Optional<Skill> skillResult = skillRepository.findAllById(skills);
//        Iterable<Skill> skillResult = skillRepository.findAllById(skills);
//        if (employerResult.isPresent()) {

//        Employer employer = (Employer) employerResult.get();
//        model.addAttribute("employer", employer);
            List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
//        newJob.setSkills(skillResult);
            newJob.setSkills(skillObjs);
//        newJob.setSkills(skillObjs);

//    }
//        newJob.setEmployer();result.toString());

//        if(employerId == null){
//                model.addAttribute("title","Jobs");
//                model.addAttribute("job", employerRepository.findAll());
//        } else {
//            Optional<Employer> result = employerRepository.findById(employerId);
//            if(result.isEmpty()){
//                model.addAttribute("title", "Invalid Employer ID: " + employerId);
//            } else {
//                Employer employer = result.get();
//                model.addAttribute("Jobs with employer: " + employer.getName());
//                model.addAttribute("employerId", employer.getJobs());
//            }
//        }
//        Optional<Employer> result = employerRepository.findById(employerId);
//        Employer employer = result.get();
//        System.out.println(result.isPresent());
//        model.addAttribute("title", "Jobs with employer: " + employer.getName());
//        model.addAttribute("employerId" + employer.getName());
//        model.addAttribute("employerJobs" + employer.getJobs());
//        if (newJob == null) {
//            model.addAttribute("title", "Employer");
//            model.addAttribute("employer", employerRepository.findAll());
//        }
////        model.addAttribute("employer", employerRepository.findById(employerId));
//        else { Optional<Employer> employer = employerRepository.findById(employerId);
//            if (employer.isEmpty()) {
//                model.addAttribute("title", "Invalid Category ID: " + employerId);
//            } else {
//                Employer category = employer.get();
//                model.addAttribute("title", "Employer in category: " + category.getName());
//                model.addAttribute("employer", category.getName());
//            }
//        }
//        employerRepository.save();
//        Optional<Employer> newJobsetEmployer(Employer employerId);
//        <Employer> employer = newJob.setEmployer(employerRepository.findById(employerId));
//        newJob.setEmployer(employerRepository.findById(employerId));
//        model.addAttribute("title", "Employer");
//        model.addAttribute("employerId", )
            jobRepository.save(newJob);

//        }
//        employerRepository.
        return "redirect:";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {
        Optional optJob = jobRepository.findById(jobId);
        if (optJob.isPresent()){
            Job job = (Job) optJob.get();
            model.addAttribute("job", job);
            return "/view";
        }
        return "view";
    }


}
