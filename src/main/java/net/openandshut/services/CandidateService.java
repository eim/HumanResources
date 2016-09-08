package net.openandshut.services;

import net.openandshut.dao.CandidateRepository;
import net.openandshut.entities.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Logger;

@Service
public class CandidateService {


  private static final Logger log = Logger.getLogger(CandidateService.class.getName());

  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

  @Autowired
  private CandidateRepository candidateRepository;

  @Transactional
  public Candidate save(Candidate candidate) {
    return candidateRepository.save(candidate);
  }

  public List<Candidate> findAllNotDeleted() {
    return candidateRepository.findAllNotDeleted();
  }

  public List<Candidate> findAll() {
    return candidateRepository.findAll();
  }

  public Candidate findOne(BigInteger id) {
    return candidateRepository.findOne(id);
  }

  @Transactional
  public Candidate delete(BigInteger id) {
    Candidate candidate = findOne(id);
    if (candidate != null) {
      candidate.setDeleted(true);
      return candidateRepository.save(candidate);
    }
    return null;
  }

  @Bean
  public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
    return new PropertySourcesPlaceholderConfigurer();
  }

  /**
   * Scheduled task
   */
  @Scheduled(cron = "${cron}")
  public void reportCurrentTime() {
    Calendar currentDate = new GregorianCalendar();
    currentDate.add(Calendar.MONTH,-3);
    log.info(String.format("The time is now: %15s", dateFormat.format(new Date())));
    for(Candidate candidate: candidateRepository.findAllOrderByDate()) {
      if (currentDate.after(candidate.getDate())) {
        log.info(String.format("Candidate will be deleted. Id: %5d name: %15s date: %15s.",candidate.getId(),candidate.getName(), dateFormat.format(new Date(candidate.getDate().getTimeInMillis()))));
        delete(candidate.getId());
      } else {
        break;
      }
    }
  }

}
