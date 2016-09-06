package net.openandshut.controllers;

import net.openandshut.entities.Candidate;
import net.openandshut.services.CandidateService;
import net.openandshut.validators.CandidateValidator;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/dudes")
public class CandidateController {

  @Resource
  private CandidateService candidateService;

//  @Resource
//  private CandidateValidator candidateValidator;

  @GetMapping("/all")
  public List<Candidate> findAllNotDeleted() {
    return candidateService.findAllNotDeleted();
  }

  @GetMapping("/allevendeleted")
  public List<Candidate> findAll() {
    return candidateService.findAll();
  }

  @GetMapping("/{id}")
  public Candidate findOne(@PathVariable(value = "id") BigInteger id) {
    return candidateService.findOne(id);
  }

  @PostMapping("/")
  public Candidate save(@RequestBody(required = true) Candidate candidate) {
    return candidateService.save(candidate);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable(value = "id") BigInteger id) {
    candidateService.delete(id);
  }

}
