package com.anhvu.foody.api;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

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
import com.anhvu.foody.service.CityService;

@RestController
@RequestMapping("city")
public class CityController {

	@Autowired
	private CityService cityService;

	@RequestMapping(value = "/getall", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public List<City> getAll() {
		return (List<City>) cityService.findAll();
	}
	
	@RequestMapping(value="/get-by-id/{id}",method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE})
	public City getById(@PathVariable("id")Integer id){
		
		System.out.println(id);
		return cityService.findOne(id);
		
	}
	
	@RequestMapping(value = "/page", params = { "page", "name" },method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public  Page<City> pageCity(@RequestParam(value="page")int page,@RequestParam(value="name")String name) {
		
		return cityService.getCity(name,page);
	}

	/*@RequestMapping(value = "/getdata", method = RequestMethod.GET)
	private String getData() throws IOException {
		String result = "";
		try {

			File htmlFile = new File("C:/Users/binhb/Desktop/123.html");
			Document doc = Jsoup.parse(htmlFile, "UTF-8");
			Elements lis = doc.select("li");
			Element ul = doc.select("ul").first();
			Elements liItem = doc.select("li");
			for (Element li : liItem) {
				Element a = li.getElementsByTag("a").first();
				// String a = li.getElementsByTag("a").first().attr("href");
				String dataId = (a.attr("data-id"));
				String url = a.attr("href").replace("/", "");
				url = url.replace("%20", "-");
				String name = a.getElementsByTag("label").text();
				String count = a.getElementsByTag("span").text();

				// System.out.println(url + " " + name + " " + count + " " +
				// dataId);
				City city = new City();
				city.setName(name);
				city.setCount(Integer.parseInt(count.replace(".", "")));
				city.setUrl(url);
				city.setDataId(Integer.parseInt(dataId));
				city.toString();
				if (cityService.findByName(name).size() == 0)
					cityService.save(city);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result.toString();
	}*/
}
