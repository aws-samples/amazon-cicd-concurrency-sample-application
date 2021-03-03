package com.example.concurrencysample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Concurrency {
    private final long id;
	private final String content;
	private Date date;
	private String today = null;
    private DateFormat myFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
    private DateFormat todayFormat = new SimpleDateFormat("dd MMM yyyy");
	
	public Concurrency(long id, String content, Date date) {
		this.id = id;
		this.content = content;
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			objectMapper.setDateFormat(myFormat);
			Date r = Util.getRandomDate();
			Date todayDate = todayFormat.parse( date.toString() );
			if(Util.isSameDay( Util.getRandomDate() , todayDate)) {
				today = r.toString();
			}
		} catch (ParseException e) {
//			e.printStackTrace();
		}
	}
	
	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
	public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}