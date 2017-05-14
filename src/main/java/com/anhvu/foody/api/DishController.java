package com.anhvu.foody.api;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anhvu.foody.entity.Category;
import com.anhvu.foody.entity.City;
import com.anhvu.foody.entity.District;
import com.anhvu.foody.entity.Dish;
import com.anhvu.foody.entity.Review;
import com.anhvu.foody.entity.Street;
import com.anhvu.foody.entity.User;
import com.anhvu.foody.service.CategoryService;
import com.anhvu.foody.service.CityService;
import com.anhvu.foody.service.DishService;
import com.anhvu.foody.service.DistrictService;
import com.anhvu.foody.service.ReviewService;
import com.anhvu.foody.service.StreetService;
import com.anhvu.foody.service.UserService;
import com.anhvu.foody.util.StringHelper;

@RestController
@RequestMapping("dish")
public class DishController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CityService cityService;

	@Autowired
	private DistrictService districtService;

	@Autowired
	private StreetService streetService;

	@Autowired
	private UserService userService;

	@Autowired
	private DishService dishService;

	@RequestMapping(value = "/getall", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public List<Dish> getAll() {
		return (List<Dish>) dishService.findAll();
	}

	@RequestMapping(value = "/get-by-params", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_FORM_URLENCODED_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public List<Dish> getByParams(@RequestBody String params) {
		System.out.println(params);

		JSONObject object = new JSONObject(params);
		int page = object.getInt("page");
		switch (object.getString("type")) {
		case "by-city":
			return (List<Dish>) dishService.findByCity(Integer.parseInt(object.getString("cityId")), page);
		case "by-district":
			return (List<Dish>) dishService.findByDistrict(Integer.parseInt(object.getString("cityId")), page);
		default:
			break;
		}

		return null;
	}

	@RequestMapping(value = "/get-by-options", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_FORM_URLENCODED_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public List<Dish> getByOption(@RequestBody String params) {

		System.out.println(params);
		JSONObject object = new JSONObject(params);
		int page = object.getInt("page");
		switch (Integer.parseInt(object.getString("options"))) {
		case 1:
			System.out.println(dishService.findByNew(page));
			return dishService.findByNew(page);
		case 3:
			return dishService.findByHot(page);
	
		default:
			break;
		}
		return null;
	}
	
	@RequestMapping(value = "/get-by-categories", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_FORM_URLENCODED_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public List<Dish> getByCategory(@RequestBody String params) {

		System.out.println(params);
		JSONObject object = new JSONObject(params);
		int page = object.getInt("page");
		int categoryId = object.getInt("categoryId");
		switch (Integer.parseInt(object.getString("option"))) {
		case 1:
			return dishService.findByCategoryNew(categoryId,page);
		case 3:
			return dishService.findByHot(page);
	
		default:
			return null;
		}
	}

	@RequestMapping(value = "/getdata", method = RequestMethod.GET)
	private String getData() throws IOException {
		OutputStreamWriter writer = new OutputStreamWriter(
				new FileOutputStream("C:/Users/binhb/Desktop/data.txt", true), "UTF-8");
		BufferedWriter fbw = new BufferedWriter(writer);
		byte[] buf = new byte[1024];

		City hcm = cityService.findOne(1);
		List<Category> categories = (List<Category>) categoryService.findAll();

		String doc = null;
		String result = null;
		Map<String, String> header = new HashMap();

		header.put("Accept", "application/json, text/javascript, */*; q=0.01");
		header.put("Accept-Encoding", "gzip, deflate, br");
		header.put("Accept-Language", "vi-VN,vi;q=0.8,fr-FR;q=0.6,fr;q=0.4,en-US;q=0.2,en;q=0.2");
		header.put("Connection", "keep-alive");
		header.put("Content-Length", "73");
		header.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		header.put("Cookie",
				"flg=vn; fbm_395614663835338=base_domain=.foody.vn; FOODY.AUTH.UDID=6148d4b8-4eca-4e44-b902-05d41c426320; _v1EmaticSolutions=%5B%22b1d21c35-f385-11e6-aa53-0242ac110002%22%2C1489684476637%2C%5B%22A%22%2C%22%5Cn%E1%BB%9E%20%C4%91%C3%A2u%5Cn%22%2C1%2C%22places%22%5D%5D; _v1EmaticSolutionsEI=%7B%22c_3582_1%22%3A%5B1%2C1487505468796%2C0%5D%2C%22c_4558_1%22%3A%5B0%2C1490093419839%2C0%5D%7D; FOODY.AUTH=8824CE5845E6B5274496E854E17C7C0F294600EB500DEF047092302CB38A259E5825E399E912D50613ADAEEE896BB6054C9CA087B1B5B1EE45AA327D24768AF8C43F4CBC32F4E3D9011FFB8106CD7B2B3B35323368B6204DFE8DE4A179CF5C5F960F0B8D2D4A495CB06B092D5633FC9E5AA323F02336D26DE8699D57787F67F53C0CB9F2C4361151F0795702D3F961C1747DBC7DE29A94647AEBB05F9EDA47E0C39E29F4A82FA5BFE9094F36CBA99C06226B98410956F1A6CD3ED29BABDD1FC6BF999B6A70D5FA7A009674F4C08A4AFBC14DD888B7485E582CC9F75F80B8962F; floc=217; gcat=food; hideCatfishBanner=true; fd.sk=; _gat=1; _gat_ads=1; fbsr_395614663835338=V6EsrbRnvTtmBThLvIueDRk-ffp27ZzF35SonrVlF10.eyJhbGdvcml0aG0iOiJITUFDLVNIQTI1NiIsImNvZGUiOiJBUUE1aUM0czNwa3p5QklNQWYxNkpzQWJyQ2w0dWkweHZHSEk1a3AzY3VMNENZSDFWMTZOYy1jNkF1ZzhMSmE1ZVFvd1pad1VZaV9yTmlCX01qMDFwOS1RWlZLRHdXRklJUjVHQzgxbXNPbC1VeDdERXdFM3g1dzNpMTBBclhXY0gxV1U0YVlFVXFtYkhSTl9IXy1NR3ZUS0VJQ3Z1andGOGNWZENXcHFlc3Q1Q3NYNjg0b2Y5eW5GbEFMQUdiQUt6dFdpTG5pcURpTUZXYk5oZGFDTm4zZEFOVHo4dVhSRHdJcWIya3BjNlZFdC1VUGhRZHJyWnRvTnMtb24wMzdQblA1MEVKZDlvMEs5TlJaakp1cFhfRm5fb21qVVBqS09TbVpUVXE2cG9iRjBGTG1lQlM2d1VXQm1KdkFEY0czdUNsMHgySjF2U00wZlFFODF3OXNTb0c1NyIsImlzc3VlZF9hdCI6MTQ5MzgxMzYxMSwidXNlcl9pZCI6IjU3NTQwNDQ5MjU2MjAyNSJ9; _ga=GA1.2.1628289182.1475762467; _gid=GA1.2.1552958583.1493813657; _gat_web_tracker=1");
		header.put("Host", "www.foody.vn");
		header.put("Origin", "https://www.foody.vn");
		header.put("Referer", "https://www.foody.vn/ho-chi-minh/hinh-anh");
		header.put("User-Agent",
				"Mozilla/5.0 (iPad; CPU OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1");
		header.put("X-Requested-With", "XMLHttpRequest");

		List<District> districts = hcm.getDistricts();
		try {
			for (District district : districts) {
				for (Category category : categories) {
					int lastId = 0;
					for (int a = 0; a < 5; a++) {
						if (category.getType() == 1) {
							doc = Jsoup
									.connect("https://www.foody.vn/Gallery/GetMoreLatestGalleryResIndexItem?districtId="
											+ district.getValue() + "&categoryId=" + category.getValue()
											+ "&categoryGroupId=1" + "&cuisineId=0" + "&count=12" + "&lastId=" + lastId)
									.headers(header).ignoreContentType(true).post().body().text();

						} else {
							if (category.getId() == 29) {
								category.setValue(1);
							} else if (category.getId() == 30) {
								category.setValue(4);
							}
							doc = Jsoup
									.connect("https://www.foody.vn/Gallery/GetMoreLatestGalleryResIndexItem?districtId="
											+ district.getValue() + "&categoryId=0" + "&categoryGroupId=1"
											+ "&cuisineId=" + category.getValue() + "&count=12" + "&lastId=" + lastId)
									.headers(header).ignoreContentType(true).post().body().text();

						}
						JSONObject object = new JSONObject(doc);
						if(!object.getBoolean("success"))
							continue;
						lastId = object.getInt("LastId");
						JSONArray array = object.getJSONArray("data");
						int count = array.length();
						
						System.out.println(doc);
						
						for (int i = 0; i < count; i++) {
							JSONObject jsonDish = array.getJSONObject(i);
							Dish dish = new Dish();
							try {
								if (dishService.findByValue(jsonDish.getInt("ResId")) != null)
									continue;
								dish.setName(jsonDish.getString("Name"));
								dish.setIsAds(jsonDish.getBoolean("IsAds"));
								dish.setAddress(jsonDish.getString("ResAddress"));
								dish.setResName(jsonDish.getString("ResName"));
								dish.setValue(jsonDish.getInt("ResId"));
								dish.setImage(dish.getValue() + "");
								
								Street street;
								String nameStreet = StringHelper.getStreetName(dish.getAddress());
								if (nameStreet == null) {
									continue;
								}
								street = streetService
										.findOne(StringHelper.ToUnsignString(nameStreet + " " + district.getName()));
								if (street == null) {
									street = new Street();
									street.setName(nameStreet);
									street.setId(StringHelper.ToUnsignString(nameStreet + " " + district.getName()));
									street.setDistrict(district);
									streetService.save(street);
								}

								User user = userService.findByValue(jsonDish.getInt("UserId"));
								if (user == null) {
									user = new User();
									user.setName(jsonDish.getString("UserFullName"));
									user.setGender(jsonDish.getString("UserGender"));
									user.setValue(jsonDish.getInt("UserId"));
									user.setAvatar(user.getValue() + "");
									
									userService.save(user);
									URL url = new URL(jsonDish.getString("UserAvatar"));
									InputStream inputStream = new BufferedInputStream(url.openStream());
									ByteArrayOutputStream out = new ByteArrayOutputStream();
									int n = 0;
									while ((n = inputStream.read(buf)) != -1) {
										out.write(buf, 0, n);
									}
									out.close();
									inputStream.close();
									byte[] response = out.toByteArray();
									FileOutputStream fos = new FileOutputStream(
											"D:/project/FoodyServer/src/main/resources/static/image/avatar/"
													+ user.getAvatar());
									fos.write(response);
									fos.close();
								}

								dish.setUserDish(user);
								dish.setStreetDish(street);
								
								dishService.save(dish);
								URL url = new URL(jsonDish.getString("PhotoUrl"));
								InputStream inputStream = new BufferedInputStream(url.openStream());
								ByteArrayOutputStream out = new ByteArrayOutputStream();

								int n = 0;
								while ((n = inputStream.read(buf)) != -1) {
									out.write(buf, 0, n);
								}
								out.close();
								inputStream.close();
								byte[] response = out.toByteArray();

								FileOutputStream fos = new FileOutputStream(
										"D:/project/FoodyServer/src/main/resources/static/image/dish/" + dish.getValue()
												+ ".jpg");
								fos.write(response);
								fos.close();
								result += "UPDATE dishs SET dishs.category_id = "+category.getId()+" WHERE dishs.id = "+dish.getId()+";";
								
							} catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
							}

						}
					}

				}

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		fbw.write(result);
		fbw.newLine();
		fbw.close();
		writer.close();
		return doc;
	}

}
