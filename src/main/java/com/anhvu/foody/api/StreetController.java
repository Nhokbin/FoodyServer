package com.anhvu.foody.api;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anhvu.foody.entity.District;
import com.anhvu.foody.entity.Street;
import com.anhvu.foody.service.CityService;
import com.anhvu.foody.service.DistrictService;
import com.anhvu.foody.service.StreetService;


@RestController
@RequestMapping("street")
public class StreetController {
	@Autowired
	private StreetService streetService;
	


	@RequestMapping(value = "/getall", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public List<Street> getAll() {
		return (List<Street>) streetService.findAll();
	}
	
	

	@RequestMapping(value = "/getdata", method = RequestMethod.GET)
	private String getData() throws IOException {
		String result = "";
		try {
			Integer id = 64;
			File htmlFile = new File("C:/Users/binhb/Desktop/123.html");
			Document doc = Jsoup.parse(htmlFile, "UTF-8");
			Element ul = doc.select("ul").first();
			Elements lis = ul.select("li");
		
			for (Element li: lis) {
				System.out.println(li.getElementsByTag("a").attr("title"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result.toString();
	}
}
