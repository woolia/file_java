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
			// FileReader ����
			FileReader reader = new FileReader("C:\\Users\\zecra\\OneDrive\\���� ȭ��\\�޸� ����\\test����������\\testjson.json");
			// Reader reader = new FileReader("C:\\Users\\zecra\\OneDrive\\���� ȭ��\\�޸� ����\\test����������\\testjson.json");
		
			// Json ������ �о, ��ü�� ��ȯ
			Gson gson = new Gson();
			JSON_entity entity = gson.fromJson(reader, JSON_entity.class);
			
			// ��ü�� ���
			// [id=1, students=[{id=123.0, name=Tom}, {id=124.0, name=Jerry}], subject={name=Java, professor=Anna}]
			System.out.println("========= entity =========");
			System.out.println(entity); // toString() �� �ؼ� ��ü ������ ������ �ѹ��� �� �� �ִ�.
			
			System.out.println("========= json key �� ���ϱ� (id)=========");
			int id = entity.getId();
			System.out.printf("id : %d \n" , id);
			
			// key , value �� ���ϴ� ����
			
			List<Map<String,Object>> students = entity.getStudents();
			// foreach + Ctrl + Space �ϸ� ����� ����Ʈ�� foreach ������ �������
			
			System.out.println("========= key , value �� ���ϴ� ���� =========");
			for (Map<String, Object> map : students) {
				for(String key : map.keySet()) {
					System.out.println("key : " + key);
					Object value = map.get(key);
					System.out.println("value : " + value);
				}
			}
			
			System.out.println("========= json���� ����Ʈ key �� ���ϱ� (students) =========");
			for (Map<String, Object> map : students) {
				Double _age = (Double) map.get("age"); // json ���� ���� ���ڴ� Double ������ �⺻ casting �Ǵ� �Ͱ���.
				int age = _age.intValue(); // Double => int : .intValue() ���	
				
				String name = (String) map.get("name");
				System.out.println("age : " + age + " name : " + name);
			}
			
			System.out.println("========= json���� map key �� ���ϱ� (subject) =========");
			Map<String,Object> subject = entity.getSubject();
			
			System.out.println("class : " + subject.get("class"));
			System.out.println("professor : " + subject.get("professor"));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
