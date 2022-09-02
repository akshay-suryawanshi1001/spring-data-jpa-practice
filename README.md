# spring-data-jpa-practice
1. @SequenceGenerator(name = "student_seq", sequenceName = "student_seq", allocationSize = 1)    //create customer sequence
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
   
2. @Table(name = "student", uniqueConstraints = @UniqueConstraint(name="emailid_unique", columnNames = "email_address") )

3. 	@Column(name = "email_address", nullable = false)

4. @Embeddable  ---annotate the pojo with this which we want to embed in another table, and the then use this pojo as member type in parent class and annotat it with @embeddable
e.g.
public class Student {
	@embedded
	private Guardian guardian;
	}

@embeddable
public class Guardian{
}

5. we can use JPQL queries to find the customer data from a table, JPQL queries are written based on the pojo name and fieldname
select s from Student s where s.studenId = ?1     ---> ?1 is the first parameter
@Query("select s.firstName from Student s where s.emailId = ?1 ") 

6. native query
@Query(value = "select * from Student where email_id = ?1 " , nativeQuery = true)    //Native query
	Student getStudentByEmailUsingNativeQuery(String email);
	
7. @Query(value = "select * from Student where email_id = :emailId " , nativeQuery = true)    //Native named param
	Student getStudentByEmailUsingNamedParam(@Param("emailId") String email);
	
8. @Modifying
	@Transactional
	
9. cascade = CascadeType.ALL

10. data fetching type
a. eager fetching: when there is relationship between 2 tables, and we fetch parent table data then child table data will also be fetched
b. lazy fetching: we fetch parent table data then only that will be fetched, unless we specifically ask for it

11. //now course_material cannot exist without course table so here we need to add courseId as the foreign key from course table
	@OneToOne(cascade = CascadeType.ALL, //this eventually mean save the course first and then save coursematerial
			fetch = FetchType.LAZY)  
	@JoinColumn(
			name = "course_id" ,    /* foreign key column name in this table*/
			referencedColumnName = "courseId"  /*coulmn name from the course table which we want to refer here*/
	)
	private Course course;
	
	we can make this mapping bidirectional by 
	@OneToOne(mappedBy = "course") //membername from the @joincloumn class
	private CourseMaterial courseMaterial;
	
12.@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "course_student_mapping",   /* name of the new mapping table to be created*/
	joinColumns = @JoinColumn(name = "course_id",    /*name of the column containing id of course table*/
			referencedColumnName = "courseId"),     //name of the member from Course class to put in course_id
	inverseJoinColumns = @JoinColumn(name = "student_id",  /*name of the column containing id of student table*/
			referencedColumnName = "studentId")
	)              
	private List<Student> students;