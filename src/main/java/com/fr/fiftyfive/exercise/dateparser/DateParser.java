package com.fr.fiftyfive.exercise.dateparser;
/*Hawking is a natural language date parser written in Java for more info go to https://github.com/zoho/hawking
*  This Code was written on the demand of 55
*	author: IHSANE Mohamed Amine
*/
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.joda.time.DateTime;

import com.zoho.hawking.HawkingTimeParser;
import com.zoho.hawking.datetimeparser.configuration.HawkingConfiguration;
import com.zoho.hawking.language.english.model.DatesFound;






class DateParser {
	
	
	private static Map<DateTime,Integer> extractedDates = new HashMap<>();
	

	
	/* @Method : getDates function extract the dates parsed using the HAwking API to a Map
	 * @param : datesFound the dates found using the  HawkingTimeParser.parse Method
	 * @return : sortedMap sorted Map of dates as keys and Years as values
	 * 
	 * */
  public static Map<DateTime,Integer> getDates(DatesFound datesFound) {
	  
	  datesFound.getParserOutputs().forEach(v -> extractedDates.put(v.getDateRange().getEnd(),v.getDateRange().getEnd().getYear()));
	   Map<DateTime, Integer> sortedMap = extractedDates.entrySet().stream()
			   .sorted(Map.Entry.comparingByKey())
			   .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,
					   (oldValue,newValue)-> oldValue, LinkedHashMap::new));
	   sortedMap.entrySet()
	      .stream()
	      .collect(Collectors.groupingBy(
	              Map.Entry::getValue,
	              HashMap::new,
	              Collectors.mapping(Map.Entry::getKey, Collectors.toList())));;
	return sortedMap;
	  
  }
  
  /* @Method : getDates function extract the dates parsed using the HAwking API to a Map
	 * @param : sorted Map of dates as keys and Years as values
	 * @return : reverseMap with Years as keys and an arrayList as Values 
	 * 
	 * */
  
  public static Map<Integer, ArrayList<DateTime>> getReverseMap(Map<DateTime,Integer> map){
	  
	  Map<Integer, ArrayList<DateTime>> reverseMap = new HashMap<>(
			    map.entrySet().stream()
			        .collect(Collectors.groupingBy(Map.Entry::getValue)).values().stream()
			        .collect(Collectors.toMap(
			                item -> item.get(0).getValue(),
			                item -> new ArrayList<DateTime>(
			                    item.stream()
			                        .map(Map.Entry::getKey)
			                        .collect(Collectors.toList())
			                ))
			        ));
	 
	return reverseMap;


	  
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
    com.zoho.hawking.language.english.model.DatesFound datesFound = null;
//    try {
//      datesFound = parser.parse(inputText, referenceDate, hawkingConfiguration, "eng"); //No I18N
//    } catch (Exception e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
    //assert datesFound != null;
    datesFound = parser.parse(inputText, referenceDate, hawkingConfiguration, "eng");
    Map<Integer, ArrayList<DateTime>> resultMap = DateParser.getReverseMap(DateParser.getDates(datesFound));
    for (Map.Entry<Integer, ArrayList<DateTime>> entry : resultMap.entrySet()) {
        System.out.print(entry.getKey());
        System.out.println(':');
        entry.getValue().forEach(v -> System.out.println("\t-"+v.getMonthOfYear()+"\n\t\t-"+v.getDayOfMonth()+'('+(entry.getValue().indexOf(v)+1)+')'));
    }
  }







}