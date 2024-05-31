package demo;
import java.util.Set;

import jakarta.persistence.*;

@Entity
public class Course {
    
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String name;
    @OneToMany(mappedBy="course", cascade=CascadeType.ALL)
    private Set<Student> students;
    public Course(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
    public Course() {}
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
	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + "]";
	}
	
   
}
