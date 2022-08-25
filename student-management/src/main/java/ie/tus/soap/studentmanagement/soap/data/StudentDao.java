package ie.tus.soap.studentmanagement.soap.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ie.tus.soap.studentmanagement.soap.bean.Student;
import ie.tus.students.GetStudentDetailsRequest;
import ie.tus.students.GetStudentDetailsResponse;
import ie.tus.students.StudentDetails;

public enum StudentDao {

	instance;
	
	private Map<Integer, Student> studentsMap = new HashMap<Integer, Student>();
	
	private StudentDao() {
		
		Student student1 = new Student(1, "Joe Bloggs", "Athlone");
		studentsMap.put(1, student1);
		
		Student student2 = new Student(2, "Mary Bloggs", "Longford");
		studentsMap.put(2, student2);
		
		Student student3 = new Student(3, "Billy Bloggs", "Roscommon");
		studentsMap.put(3, student3);		
	}
	
	public Student getStudent(int id) {
		return studentsMap.get(id);
	}
	
	public List<Student> getAllStudents(){
		List<Student> books = new ArrayList<Student>();
		books.addAll(studentsMap.values());
		return books;
	}
	
    public int deleteStudent(int id) {
    	// return 1 for success, 0 for failure
        if (studentsMap.remove(id) != null) {
            return 1;
        } else {
        	return 0;
        }
    } 
}


