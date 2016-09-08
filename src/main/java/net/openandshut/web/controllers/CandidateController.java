package net.openandshut.web.controllers;

import net.openandshut.entities.Candidate;
import net.openandshut.services.CandidateService;
import net.openandshut.web.validators.CandidateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.math.BigInteger;

@Controller
public class CandidateController {

  @Resource
  private CandidateService candidateService;

  @Autowired
  private CandidateValidator validator;

  @InitBinder
  private void initBinder(WebDataBinder binder) {
    binder.setValidator(validator);
  }

  @GetMapping(value = {"/","/error"})
  public String index(Model model) {
    model.addAttribute("list",candidateService.findAllNotDeleted());
    return "index";
  }

  @GetMapping("/all")
  public ModelAndView findAllNotDeleted(Model model) {
    model.addAttribute("list",candidateService.findAllNotDeleted());
    return new ModelAndView("index");
  }

  @GetMapping("/allevendeleted")
  public ModelAndView findAll(Model model) {
    model.addAttribute("list",candidateService.findAll());
    return new ModelAndView("evendeleted");
  }

  @GetMapping("/candidate/{id}")
  public Candidate findOne(@PathVariable(value = "id") BigInteger id) {
    return candidateService.findOne(id);
  }

  @PostMapping("/candidate")
  public String save(@Validated @ModelAttribute Candidate candidate) {
    candidateService.save(candidate);
    return "result";
  }

  @GetMapping("/candidate")
  public String getNew(Model model) {
    model.addAttribute("candidate", new Candidate());
    return "candidate";
  }

  @PostMapping("/candidate/")
  public ModelAndView delete(@RequestParam(value="id", required=true) BigInteger id, Model model) {
    candidateService.delete(id);
    model.addAttribute("list",candidateService.findAllNotDeleted());
    return new ModelAndView("index");
  }

}
