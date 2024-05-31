package demo;
import jakarta.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String name, DOB, className, division, gender;
    @ManyToOne()
    @JoinColumn(name="course_id")
    private Course course;
    public Student(int id, String name, String DOB, String className, String division, String gender, Course course) {
		super();
		this.id = id;
		this.name = name;
		this.DOB = DOB;
		this.className = className;
		this.division = division;
		this.gender = gender;
		this.course = course;
	}
    public Student() {}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDOB() {
		return DOB;
	}
	public void setDOB(String DOB) {
		this.DOB=DOB;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", DOB=" + DOB + ", className=" + className + ", division="
				+ division + ", gender=" + gender + ", course=" + course + "]";
	}
   
}
