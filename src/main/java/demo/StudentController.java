package demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentRepo sr;
    
    @Autowired
    private CourseRepo cr;
    
    @PostMapping 
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) { 
      Optional<Course> course = cr.findById(student.getCourse().getId());
      if (!course.isPresent()) { 
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
     student.setCourse(course.get()); 
     Student savedStudent = sr.save(student);
     return new ResponseEntity<>(savedStudent, HttpStatus.CREATED); 
     }
    
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
    	List<Student> students = sr.findAll();
    	return new ResponseEntity<>(students, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
    	Optional<Student> student = sr.findById(id);
    	return student.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student updatedStudent) {
    	Optional<Student> existingStudent = sr.findById(id);
    	if(existingStudent.isPresent()) {
    		Student student = existingStudent.get();
    		student.setName(updatedStudent.getName());
    		student.setDOB(updatedStudent.getDOB());
    		student.setClassName(updatedStudent.getClassName());
    		student.setDivision(updatedStudent.getDivision());
    		student.setGender(updatedStudent.getGender());
    		Student savedStudent = sr.save(student);
        	return new ResponseEntity<>(savedStudent, HttpStatus.OK);
    	}
    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable int id) {
    	Optional<Student> student = sr.findById(id);
    	if(student.isPresent()) {
    		sr.deleteById(id);
    		return new ResponseEntity<>(HttpStatus.OK);
    	}
    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
}
