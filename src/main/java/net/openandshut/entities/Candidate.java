package net.openandshut.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Objects;

@Entity
@Table(name = "CANDIDATE")
public class Candidate {

  @JsonProperty
  @Id
  @GeneratedValue( strategy = GenerationType.AUTO)
  private BigInteger id;

  @NotNull
  @Size(min=1, max=255)
  @JsonProperty
  @Column
  private String name;

  @JsonProperty
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  @Column
  @Temporal(value = TemporalType.TIMESTAMP)
  private Calendar date;

  @JsonProperty
  @Column
  private boolean deleted;

  public BigInteger getId() {
    return id;
  }

  public void setId(BigInteger id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Calendar getDate() {
    return date;
  }

  public void setDate(Calendar date) {
    this.date = date;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Candidate candidate = (Candidate) o;
    return Objects.equals(name, candidate.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Candidate{");
    sb.append("id=").append(id);
    sb.append(", name='").append(name).append('\'');
    sb.append(", date=").append(date);
    sb.append('}');
    return sb.toString();
  }
}
