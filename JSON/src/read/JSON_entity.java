package read;

import java.util.List;
import java.util.Map;

public class JSON_entity {

	private int id;
	// "id": 1
	private List<Map<String,Object>> students;
	/*
	"students": [
		{
			"id": 123,
			"name": "Tom"
		},
		{
			"id": 124,
			"name": "Jerry"
		}
	]
	 */
	private Map<String,Object> subject;
	/*
	"subject": {
		"name": "Java",
		"professor": "Anna"
	}
	 */
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public List<Map<String, Object>> getStudents() {
		return students;
	}
	public void setStudents(List<Map<String, Object>> students) {
		this.students = students;
	}
	
	
	public Map<String, Object> getSubject() {
		return subject;
	}
	public void setSubject(Map<String, Object> subject) {
		this.subject = subject;
	}
	
	
	@Override
	public String toString() {
		return "JSON_entity [id=" + id + ", students=" + students + ", subject=" + subject + "]";
	}
}
