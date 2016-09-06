package net.openandshut.dao;

import net.openandshut.entities.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface CandidateRepository extends JpaRepository<Candidate, BigInteger> {

  @Transactional(readOnly = true)
  @Query("select ce from Candidate ce where ce.deleted = false")
  public List<Candidate> findAllNotDeleted();

  @Transactional(readOnly = true)
  @Query("select ce from Candidate ce where ce.deleted = false order by ce.date asc")
  public List<Candidate> findAllOrderByDate();
}
