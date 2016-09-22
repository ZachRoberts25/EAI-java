package javaprogram;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
public class main {

	public static void main(String[] args) throws IOException  {
		for(int i = 0; i < websiteCrawler(35).toArray().length; i++){
			System.out.println(websiteCrawler(35).toArray()[i]);
		}
	}
	
	public static List<String> websiteCrawler(int numberOfWebsites) throws IOException{
		List<String> new_array = new ArrayList<String>();
		if(numberOfWebsites < 26){
		Document doc = Jsoup.connect("http://www.alexa.com/topsites/global;0").get();
		Element body = doc.body();
		String[] array = body.select("p.desc-paragraph").select("a[href]").text().split(" ");
		String[] smallerArray = Arrays.copyOfRange(array, 0, numberOfWebsites);
		for(int i = 0; i < smallerArray.length; i++){
			new_array.add(smallerArray[i]);
		}
		}else{
			List<String> new_array1 = new ArrayList<String>();
			double numberOfPages = Math.ceil((numberOfWebsites / 25.0));
			for(int i = 0; i < numberOfPages; i++){
				Document doc = Jsoup.connect("http://www.alexa.com/topsites/global;" + i).get();
				Element body = doc.body();
				String[] array = body.select("p.desc-paragraph").select("a[href]").text().split(" ");
				for(int x=0; x < array.length; x++){
					new_array1.add(array[x]);
				}
			}
			String[] arr1 = {}; 
			arr1 = new_array1.toArray(arr1); 
			String[] arr = Arrays.copyOfRange(arr1, 0, numberOfWebsites);
			
			for(int i=0; i < arr.length; i++){
				new_array.add(arr[i]);
			}
		}
		
		return new_array;
		
	}
	
}
