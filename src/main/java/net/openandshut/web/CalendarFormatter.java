package net.openandshut.web;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class CalendarFormatter implements Formatter<Calendar> {
  final String defaultDateFormat = "dd.MM.yyyy HH:mm";

  @Override
  public String print(Calendar object, Locale locale) {
    return new SimpleDateFormat(defaultDateFormat).format(object.getTime());
  }

  @Override
  public Calendar parse(String text, Locale locale) throws ParseException {
    Calendar calendar = new GregorianCalendar();
    calendar.setTime((new SimpleDateFormat(defaultDateFormat)).parse(text));
    return calendar;
  }
}