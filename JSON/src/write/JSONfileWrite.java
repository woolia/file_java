package write;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import read.JSON_entity;

public class JSONfileWrite {

	public static void main(String[] args) {
		
		
		// JSON 파일 쓰기
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		/*
		Gson 객체를 생성하는 방법은 2가지가 있다.
		new Gson();
		new GsonBuilder.create(); => GsonBuilder 클래스는 Gson 인스턴스를 여러가지 셋팅을 사용해 생성하는데 사용할 수 있다.
		
		 new GsonBuilder().setPrettyPrinting().create();와 같이 생성하면 
		 json양식이 깔끔하게 보이게 되고 
		 new Gson();으로 생성하게 되면 
		 보통의 Json 처럼 직렬로 보이게 됩니다.
		 */
		
		// json entity Object 생성
		JSON_entity entity = new JSON_entity();
		
		// entity Object 값 세팅
		// setId
		entity.setId(1);
		
		// setStudents
		List<Map<String,Object>> students = new ArrayList<>();
		students.add(getStudentMap(10, "Tom1"));
		students.add(getStudentMap(20, "Jerry1"));
		entity.setStudents(students);
		
		// setSubject
		Map<String,Object> subject = getSubjectMap("JavaScript", "Jackson");
		entity.setSubject(subject);
		
		// 이렇게 entity 객체에 json 값들을 세팅해서 넣는다.
		
		// => entity 객체를 json 파일에 쓰기
		try {
			FileWriter fileWriter = new FileWriter("C:\\Users\\zecra\\OneDrive\\바탕 화면\\메모 파일\\test데이터폴더\\testjson1.json");
			gson.toJson(entity, fileWriter);
			
			//  Gson에서는 데이터를 만들때는 toJson(object) 메서드를 사용한다.
			// 일반적인 gson.toJson(Class 객체); 를 하게되면 그냥 클래스 객체를 json 형식으로 바꿔주는 것을 의미한다.
			System.out.println("toJson(Object) : " + gson.toJson(entity));
			
			// 그런데 위에 gson.toJson(Class 객체, FileWriter) 하게 되면 해당 경로로 해당 entity 형태의 json 파일을 생성하게 해준다.
			// 예시로 이미 FileWriter 객체로 filePath(저장할 경로) 를 설정하였고
			// gson.toJson(entity) 로 entity 클래스를 json 형식으로 만들었다.
			// gson.toJson(entity, fileWriter); 하게 되면 entity를 json 형식으로 변환한 데이터를 fileWriter 로 작성하는것 
			
			fileWriter.flush();
			fileWriter.close();
			// fileWriter 가 사용된 이후에는 반드시 flush(); 와 close(); 해야 메모리 소비를 줄일 수 있다.
			// 여기서 fileWriter 는 finally 구역에서 진행되지 않아 그냥 try 문 마지막에 삽입ㄴ
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 아래는 그냥 값을 넣기 위한 함수일 뿐
	public static Map<String, Object> getStudentMap(long age, String name) {
		Map<String, Object> student = new HashMap<>();
		student.put("age", age);
		student.put("name", name);
		return student;
		}
	
	public static Map<String, Object> getSubjectMap(String name, String professor) {
		Map<String, Object> subject = new HashMap<>();
		subject.put("name", name);
		subject.put("professor", professor);
		return subject;
		}
	
}
