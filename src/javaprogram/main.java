package javaprogram;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class main {
	public static void main(String[] args) {
		System.out.println(websiteCrawler(22));
    }	
public static List<String> websiteCrawler(int numberOfWebsites){
	List<String> list = new ArrayList<String>();
	URL url;
	try {
	    // get URL content
	    String a="http://www.alexa.com/topsites/global;";
	    if(numberOfWebsites <= 0){
	    	return null;
	    }
	    else if(numberOfWebsites < 26){
	    	a += 0;
		    url = new URL(a);
		    URLConnection conn = url.openConnection();
		    // open the stream and put it into BufferedReader
		    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		    String inputLine;
		    List<String> list1 = new ArrayList<String>();
		    while ((inputLine = br.readLine()) != null) {
		        if(inputLine.contains("<li class=\"site-listing\">")){ 
		        	String[] string = inputLine.split("<a href=\"/siteinfo/");
		        	String[] string1 = string[1].split("\">");
		        	list1.add(string1[0]);
		        }
		    }
		    for(int i = 0; i < numberOfWebsites; i++){
		    	list.add(list1.get(i));
		    }
		    br.close();
	    	
	    }else{
	    	double numberOfPages = Math.ceil((numberOfWebsites / 25.0));
	    	List<String> list1 = new ArrayList<String>();
	    	for(int i = 0; i < numberOfPages; i++){
	    		a += i;
	    		url = new URL(a);
			    URLConnection conn = url.openConnection();
			    // open the stream and put it into BufferedReader
			    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			    String inputLine;
			    while ((inputLine = br.readLine()) != null) {
			    	if(inputLine.contains("<li class=\"site-listing\">")){ 
			        	String[] string = inputLine.split("<a href=\"/siteinfo/");
			        	String[] string1 = string[1].split("\">");
			        	list1.add(string1[0]);
			    	}
			    }
			 br.close();
	    	}
	        for(int i = 0; i < numberOfWebsites; i++){
		    	list.add(list1.get(i));
		    }
	    }
	} catch (MalformedURLException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return list;
}
}






