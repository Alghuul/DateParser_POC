package com.fr.fiftyfive.exercise.dateparser;


import java.util.Date;
import java.util.HashMap;


import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zoho.hawking.HawkingTimeParser;
import com.zoho.hawking.datetimeparser.configuration.HawkingConfiguration;
import com.zoho.hawking.language.english.model.DatesFound;


class DateParser {
	
	
	private static HashMap<DateTime,Integer> extractedDates = new HashMap<>();
	

	

  public static HashMap<DateTime,Integer> getDates(DatesFound datesFound) {
	  
	  datesFound.getParserOutputs().forEach(v -> extractedDates.put(v.getDateRange().getEnd(),v.getDateRange().getEnd().getYear()));
	    
	return extractedDates;
	  
  }
  
  
  
  
  

  public static void main(String[] args) throws Exception {
    HawkingTimeParser parser = new HawkingTimeParser();
    String inputText = "Marvin Lee Minsky at the Mathematics Genealogy Project; 20 May 2014\r\n"
    		+ "\r\n"
    		+ "Marvin Lee Minsky at the AI Genealogy Project. {reprint 18 September 2011)\r\n"
    		+ "\r\n"
    		+ "\"Personal page for Marvin Minsky\". web.media.mit.edu. Retrieved 23 June 2016.\r\n"
    		+ "\r\n"
    		+ "Admin (January 27, 2016). \"Official Alcor Statement Concerning Marvin Minsky\".\r\n"
    		+ "\r\n"
    		+ "	Alcor Life Extension Foundation. Retrieved 2016-04-07.\r\n"
    		+ "\r\n"
    		+ "\"IEEE Computer Society Magazine Honors Artificial Intelligence Leaders\".\r\n"
    		+ "\r\n"
    		+ "	DigitalJournal.com. August 24, 2011. Retrieved September 18, 2011.\r\n"
    		+ "\r\n"
    		+ "	Press release source: PRWeb (Vocus).\r\n"
    		+ "\r\n"
    		+ "\"Dan David prize 2014 winners\". May 15, 2014. Retrieved May 20, 2014.";
    HawkingConfiguration hawkingConfiguration = new HawkingConfiguration();
    hawkingConfiguration.setFiscalYearStart(1);
    hawkingConfiguration.setFiscalYearEnd(1);
    hawkingConfiguration.setTimeZone("IST");
    Date referenceDate = new Date();
    DatesFound datesFound = null;
//    try {
//      datesFound = parser.parse(inputText, referenceDate, hawkingConfiguration, "eng"); //No I18N
//    } catch (Exception e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
//    assert datesFound != null;
//    LOGGER.info("DATES FOUND ::  "+ datesFound.toString());
    datesFound = parser.parse(inputText, referenceDate, hawkingConfiguration, "eng");
    DateParser.getDates(datesFound).forEach((k,v) -> System.out.println(v));
  }







}