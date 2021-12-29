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
		
		
		// JSON ���� ����
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		/*
		Gson ��ü�� �����ϴ� ����� 2������ �ִ�.
		new Gson();
		new GsonBuilder.create(); => GsonBuilder Ŭ������ Gson �ν��Ͻ��� �������� ������ ����� �����ϴµ� ����� �� �ִ�.
		
		 new GsonBuilder().setPrettyPrinting().create();�� ���� �����ϸ� 
		 json����� ����ϰ� ���̰� �ǰ� 
		 new Gson();���� �����ϰ� �Ǹ� 
		 ������ Json ó�� ���ķ� ���̰� �˴ϴ�.
		 */
		
		// json entity Object ����
		JSON_entity entity = new JSON_entity();
		
		// entity Object �� ����
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
		
		// �̷��� entity ��ü�� json ������ �����ؼ� �ִ´�.
		
		// => entity ��ü�� json ���Ͽ� ����
		try {
			FileWriter fileWriter = new FileWriter("C:\\Users\\zecra\\OneDrive\\���� ȭ��\\�޸� ����\\test����������\\testjson1.json");
			gson.toJson(entity, fileWriter);
			
			//  Gson������ �����͸� ���鶧�� toJson(object) �޼��带 ����Ѵ�.
			// �Ϲ����� gson.toJson(Class ��ü); �� �ϰԵǸ� �׳� Ŭ���� ��ü�� json �������� �ٲ��ִ� ���� �ǹ��Ѵ�.
			System.out.println("toJson(Object) : " + gson.toJson(entity));
			
			// �׷��� ���� gson.toJson(Class ��ü, FileWriter) �ϰ� �Ǹ� �ش� ��η� �ش� entity ������ json ������ �����ϰ� ���ش�.
			// ���÷� �̹� FileWriter ��ü�� filePath(������ ���) �� �����Ͽ���
			// gson.toJson(entity) �� entity Ŭ������ json �������� �������.
			// gson.toJson(entity, fileWriter); �ϰ� �Ǹ� entity�� json �������� ��ȯ�� �����͸� fileWriter �� �ۼ��ϴ°� 
			
			fileWriter.flush();
			fileWriter.close();
			// fileWriter �� ���� ���Ŀ��� �ݵ�� flush(); �� close(); �ؾ� �޸� �Һ� ���� �� �ִ�.
			// ���⼭ fileWriter �� finally �������� ������� �ʾ� �׳� try �� �������� ���Ԥ�
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// �Ʒ��� �׳� ���� �ֱ� ���� �Լ��� ��
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
