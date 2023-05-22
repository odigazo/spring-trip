package team.spring.trip.course.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import team.spring.trip.course.service.CourseService;

@RestController
@RequestMapping(value = "course")
public class CourseController {

	@Autowired
	private CourseService courseservice;

	Logger log = LogManager.getLogger("case3");

	@GetMapping(value = "/schedule")
	public Map<String, Object> schedule(@RequestParam(value = "answer", required = false) String answer) {

		System.out.println("원본" + answer);
		
		while (answer.contains("\n\n")) {
			answer = answer.replace("\n\n", "\n");
		}
		String[] lines = answer.split("\n");
		List<String> list = new ArrayList<>();
		for (String s : lines) {
			if (s.length() > 2) {
				list.add(s);
//				System.out.println(s);
			}
		}
		Map<String, List<String>> tripmap = new TreeMap<>();
		String day="";
		for (String s : list) {
			System.out.println(s + "검사");
			if(s.contains(" - ")) {
				String[] tmp = s.split(" - ");
				if (tmp.length > 1) {
					day=tmp[0];
					if (tripmap.containsKey(tmp[0])) {
						tripmap.get(tmp[0]).add(tmp[1]);
					} else {
						List<String> tmplist = new ArrayList<>();
						tmplist.add(tmp[1]);
						tripmap.put(tmp[0], tmplist);
					}
				}
			}else {
				System.out.println(day + "일차 짤림");
				List<String> tmplist = new ArrayList<>();
				tripmap.put(s, tmplist);
				day = s;
				tripmap.get(day).add(s);
			}
			
		}

		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("tripmap", tripmap);

		return map;
	}

	@GetMapping(value = "/map")
	public Map<String, Object> course(@RequestParam(value = "answer", required = false) String answer) {
		Map<String, Object> map = new HashMap<>();
		System.out.println(answer);
//		char[] arr = answer.toCharArray();
//		for (char c : arr) {
//			System.out.print(c);
//		}
		while (answer.contains("\n\n")) {
			answer = answer.replace("\n\n", "\n");
		}
		String[] lines = answer.split("\n");
		ArrayList<String> list = new ArrayList<>();
		for (String s : lines) {
			if (s.length() > 10) {
				list.add(s);
			}
		}
		String[] names = new String[list.size()];
		double[] latitudes = new double[list.size()];
		double[] longitudes = new double[list.size()];
		for (int i = 0; i < list.size(); i++) {
			String[] parts = list.get(i).split(" : ");
			System.out.println(parts[parts.length-1]);
			names[i] = parts[1];
			latitudes[i] = courseservice.getlat(names[i]);
			longitudes[i] = courseservice.getlong(names[i]);
		}


		map.put("names", names);
		map.put("latitudes", latitudes);
		map.put("longitudes", longitudes);
		return map;
	}

	@GetMapping(value = "/getfacts")
	public String facts(@RequestParam(value = "addr", required = false) String addr) {

		List<String> list = courseservice.getAddr(addr);
		log.debug("#######################"+addr);
		StringBuilder sb = new StringBuilder();
		int num = 0;
		boolean[] check = new boolean[list.size()];
		int min = Math.min(12, list.size());
		Random random = new Random();
		for(int i=0;i<min;i++) {
			int tmp = random.nextInt(min);
			if(!check[tmp]) {
				check[tmp]=true;
				sb.append(list.get(tmp));
				sb.append(", ");
			}else {
				i--;
			}
		}
//		for (String s : list) {
//			sb.append(s);
//			sb.append(", ");
//			num++;
//			if (num == 12)
//				break;
//		}
		sb.deleteCharAt(sb.length() - 1);
		log.debug(sb.toString());
		return sb.toString();
	}
}
