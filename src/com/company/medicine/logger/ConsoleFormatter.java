package com.company.medicine.logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

public class ConsoleFormatter extends SimpleFormatter{
	
	@Override
	public synchronized String format(LogRecord record) {
		StringBuffer result = new StringBuffer();
		result.append("Console: ");
		result.append(calcDate(record.getMillis()));
		result.append(", ");
		result.append(record.getSourceClassName());
		result.append(", ");
		result.append(record.getLevel());
		result.append(", ");
		result.append(record.getMessage());
		result.append("\n");
		
		return result.toString();
	}
	
	private String calcDate(long millisecs) {
	    SimpleDateFormat date_format = new SimpleDateFormat("yyyy.MM.dd HH:mm");
	    Date resultdate = new Date(millisecs);
	    return date_format.format(resultdate);

	  }


}
