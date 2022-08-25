package ie.tus.soap.studentmanagement.soap;

import java.util.List;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ie.tus.soap.studentmanagement.soap.bean.Student;
import ie.tus.soap.studentmanagement.soap.data.StudentDao;
import ie.tus.students.DeleteStudentDetailsRequest;
import ie.tus.students.DeleteStudentDetailsResponse;
import ie.tus.students.GetAllStudentDetailsRequest;
import ie.tus.students.GetAllStudentDetailsResponse;
import ie.tus.students.GetStudentDetailsRequest;
import ie.tus.students.GetStudentDetailsResponse;
import ie.tus.students.StudentDetails;


@Endpoint    // register the class with Spring WS as a Web Service Endpoint
public class StudentDetailsEndpoint {

		// The incoming request, @RequestPayload, is GetStudentDetailsRequest in the specified namespace
		@PayloadRoot(namespace = "http://tus.ie/students", localPart = "GetStudentDetailsRequest")
		@ResponsePayload   // this method returns a value to be mapped to the response payload
		public GetStudentDetailsResponse processStudentDetailsRequest(@RequestPayload GetStudentDetailsRequest request) {
			GetStudentDetailsResponse response = new GetStudentDetailsResponse();
			
			// Get a student using the new Data Access object
			Student student = StudentDao.instance.getStudent(request.getId());
			
			StudentDetails studentDetails = new StudentDetails();
			studentDetails.setId(student.getId());
			studentDetails.setName(student.getName());
			studentDetails.setAddress(student.getAddress());
			
			response.setStudentDetails(studentDetails);
			
			return response;		
		}	
		
		@PayloadRoot(namespace = "http://tus.ie/students", localPart = "GetAllStudentDetailsRequest")
		@ResponsePayload   // this method returns a value to be mapped to the response payload
		public GetAllStudentDetailsResponse processAllStudentDetailsRequest(@RequestPayload GetAllStudentDetailsRequest request) {
			GetAllStudentDetailsResponse response = new GetAllStudentDetailsResponse();
			
			// Get a student using the new Data Access object
			List<Student> students = StudentDao.instance.getAllStudents();
			
			for(Student stud : students) {
				StudentDetails studentDetails = new StudentDetails();
				studentDetails.setId(stud.getId());
				studentDetails.setName(stud.getName());
				studentDetails.setAddress(stud.getAddress());
				
				response.getStudentDetails().add(studentDetails);
			}
				
			return response;		
		}
		
		@PayloadRoot(namespace = "http://tus.ie/students", localPart = "DeleteStudentDetailsRequest")
		@ResponsePayload   // this method returns a value to be mapped to the response payload
		public DeleteStudentDetailsResponse deleteStudentDetailsRequest(@RequestPayload DeleteStudentDetailsRequest request) {
			DeleteStudentDetailsResponse response = new DeleteStudentDetailsResponse();
			
			// Get a student using the new Data Access object
			int status = StudentDao.instance.deleteStudent(request.getId());
			response.setStatus(status);
				
			return response;		
		}
}

// Notes:
// @Endpoint – registers the class with Spring WS as a Web Service Endpoint
// @PayloadRoot – defines the handler method according to the namespace and localPart attributes
// @ResponsePayload – indicates that this method returns a value to be mapped to the response payload
// @RequestPayload – indicates that this method accepts a parameter to be mapped from the incoming request


