package ie.tus.soap.studentmanagement.soap;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ie.tus.students.GetStudentDetailsRequest;
import ie.tus.students.GetStudentDetailsResponse;
import ie.tus.students.StudentDetails;


@Endpoint    // register the class with Spring WS as a Web Service Endpoint
public class StudentDetailsEndpoint {

		// The incoming request, @RequestPayload, is GetStudentDetailsRequest in the specified namespace
		@PayloadRoot(namespace = "http://tus.ie/students", localPart = "GetStudentDetailsRequest")
		@ResponsePayload   // this method returns a value to be mapped to the response payload
		public GetStudentDetailsResponse processCourseDetailsRequest(@RequestPayload GetStudentDetailsRequest request) {
			GetStudentDetailsResponse response = new GetStudentDetailsResponse();
			
			StudentDetails studentDetails = new StudentDetails();
			studentDetails.setId(request.getId());
			studentDetails.setName("Joe Bloggs");
			studentDetails.setAddress("Athlone");
			
			response.setStudentDetails(studentDetails);
			
			return response;		
		}
}

// Notes:
// @Endpoint – registers the class with Spring WS as a Web Service Endpoint
// @PayloadRoot – defines the handler method according to the namespace and localPart attributes
// @ResponsePayload – indicates that this method returns a value to be mapped to the response payload
// @RequestPayload – indicates that this method accepts a parameter to be mapped from the incoming request


