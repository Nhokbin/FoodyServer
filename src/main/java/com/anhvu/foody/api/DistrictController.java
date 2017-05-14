package com.anhvu.foody.api;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anhvu.foody.entity.City;
import com.anhvu.foody.entity.District;
import com.anhvu.foody.service.CityService;
import com.anhvu.foody.service.DistrictService;

@RestController
@RequestMapping("district")
public class DistrictController {

	@Autowired
	private DistrictService districtService;
	
	@Autowired
	private CityService cityService;

	@RequestMapping(value = "/getall", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public List<District> getAll() {
		return (List<District>) districtService.findAll();
	}
	
	@RequestMapping(value = "/page", params = { "page", "name" },method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public  List<District> pageDistrict(@RequestParam(value="page")int page,@RequestParam(value="name")String name) {
		
		return districtService.getDistrict(name,page).getContent();
	}
	
	@RequestMapping(value="/get-by-city/{id}",method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE})
	public List<District> getByCity(@PathVariable("id")Integer id){
		
		System.out.println(id);
		return districtService.findByCity(id);
		
	}
	
	/*@RequestMapping(value = "/getdata", method = RequestMethod.GET)
	private String getData() throws IOException {
		String result = "";
		try {
			Integer id = 64;
			File htmlFile = new File("C:/Users/binhb/Desktop/123.html");
			Document doc = Jsoup.parse(htmlFile, "UTF-8");
			Element lis = doc.select("select").first();
			Elements options = doc.select("option");
		
			for (Element option : options) {
				Integer value = Integer.parseInt(option.attr("value"));
				String name = option.text();
				// String a = li.getElementsByTag("a").first().attr("href");
				//System.out.println(value + " " + name);
				District district = new District();
				district.setName(name);
				district.setValue(value);
				district.setCity(cityService.findOne(id));
				System.out.println(district.toString());
				districtService.save(district);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result.toString();
	}*/
}
