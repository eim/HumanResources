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
  public void delete(BigInteger id) {
    Candidate candidate = findOne(id);
    if (candidate != null) {
      candidate.setDeleted(true);
      candidateRepository.save(candidate);
    }
  }

  @Bean
  public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
    return new PropertySourcesPlaceholderConfigurer();
  }

  @Scheduled(cron = "${cron}")
  public void reportCurrentTime() {
    Calendar currentDate = new GregorianCalendar();
    log.info(String.format("The time is now: %15s", dateFormat.format(new Date())));
    int diff = 0;
    for(Candidate candidate: candidateRepository.findAllOrderByDate()) {
      diff = diffInMonths(candidate.getDate(),currentDate);
      log.info(String.format("Id: %5d name: %15s date: %15s diff in months: %5d",candidate.getId(),candidate.getName(), dateFormat.format(new Date(candidate.getDate().getTimeInMillis())),diff));
      if (diff >= 3) {
        delete(candidate.getId());
      } else {
        break;
      }
    }
  }

  private int diffInMonths(Calendar candidateDate, Calendar currentCalendar) {
    int diffYear = currentCalendar.get(Calendar.YEAR) - candidateDate.get(Calendar.YEAR);
    return diffYear * 12 + currentCalendar.get(Calendar.MONTH) - candidateDate.get(Calendar.MONTH);
  }

}
