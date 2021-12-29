package read;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class JSONfileRead {

	/*

{
	"id": 1,
	"students": [
	{
		"id": 123,
		"name": "Tom"
	},
	{
		"id": 124,
		"name": "Jerry"
	}
	],
		"subject": {
		"name": "Java",
		"professor": "Anna"
	}
}
	 */
	
	public static void main(String[] args) {
		
		try {
			// FileReader 생성
			FileReader reader = new FileReader("C:\\Users\\zecra\\OneDrive\\바탕 화면\\메모 파일\\test데이터폴더\\testjson.json");
			// Reader reader = new FileReader("C:\\Users\\zecra\\OneDrive\\바탕 화면\\메모 파일\\test데이터폴더\\testjson.json");
		
			// Json 파일을 읽어서, 객체로 반환
			Gson gson = new Gson();
			JSON_entity entity = gson.fromJson(reader, JSON_entity.class);
			
			// 객체를 출력
			// [id=1, students=[{id=123.0, name=Tom}, {id=124.0, name=Jerry}], subject={name=Java, professor=Anna}]
			System.out.println("========= entity =========");
			System.out.println(entity); // toString() 을 해서 객체 데이터 정보를 한번에 볼 수 있다.
			
			System.out.println("========= json key 값 구하기 (id)=========");
			int id = entity.getId();
			System.out.printf("id : %d \n" , id);
			
			// key , value 값 구하는 로직
			
			List<Map<String,Object>> students = entity.getStudents();
			// foreach + Ctrl + Space 하면 가까운 리스트를 foreach 문으로 만들어줌
			
			System.out.println("========= key , value 값 구하는 로직 =========");
			for (Map<String, Object> map : students) {
				for(String key : map.keySet()) {
					System.out.println("key : " + key);
					Object value = map.get(key);
					System.out.println("value : " + value);
				}
			}
			
			System.out.println("========= json안의 리스트 key 값 구하기 (students) =========");
			for (Map<String, Object> map : students) {
				Double _age = (Double) map.get("age"); // json 안의 값이 숫자는 Double 형으로 기본 casting 되는 것같다.
				int age = _age.intValue(); // Double => int : .intValue() 사용	
				
				String name = (String) map.get("name");
				System.out.println("age : " + age + " name : " + name);
			}
			
			System.out.println("========= json안의 map key 값 구하기 (subject) =========");
			Map<String,Object> subject = entity.getSubject();
			
			System.out.println("class : " + subject.get("class"));
			System.out.println("professor : " + subject.get("professor"));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
