package team.spring.trip.course.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import team.spring.trip.course.service.CourseService;

@RestController
@RequestMapping(value="course")
public class CourseController {
	
	@Autowired
	private CourseService courseservice;
	
	Logger log = LogManager.getLogger("case3");
	
	@GetMapping
	public Map<String,Object> course(@RequestParam(value="answer", required=false) String answer){
		Map<String,Object> map = new HashMap<>();
		System.out.println(answer);
		char[] arr = answer.toCharArray();
		for(char c : arr) {
			System.out.print(c);
		}
		while(answer.contains("\n\n")) {
			answer = answer.replace("\n\n", "\n");
		}
		String[] lines = answer.split("\n");
		ArrayList<String> list = new ArrayList<>();
		for(String s : lines) {
			if(s.length()>0) {
				list.add(s);
			}
		}
		String[] names = new String[list.size()];
		double[] latitudes = new double[list.size()];
		double[] longitudes = new double[list.size()];
		log.debug("분할 시작");
		for (int i = 0; i < list.size(); i++) {
		    String[] parts = list.get(i).split(" : ");
		    System.out.println(parts[0]);
		    System.out.println(parts[1]);
		    names[i] = parts[0];
		    String[] latLong = parts[1].split(",");
		    latitudes[i] = Double.parseDouble(latLong[0]);
		    longitudes[i] = Double.parseDouble(latLong[1]);
		}
		for(String s : names) {
			System.out.println(s);
		}
		for(double d : latitudes) {
			System.out.println("위도" + d);
		}
		for(double d : longitudes) {
			System.out.println("경도" + d);
		}
		map.put("names",names);
		map.put("latitudes",latitudes);
		map.put("longitudes",longitudes);
		return map;
	}
}
