public class StudentClassData {

    String student_id;
    String student_name;

    int student_age;

    String student_course;


    public StudentClassData(String id, String name, int age, String course) {
        this.student_id = id;
        this.student_name = name;
        this.student_age = age;
        this.student_course = course;
    }


    public String getStudent_id() {
        return this.student_id;
    }

    public String getStudent_name() {
        return this.student_name;
    }

    public int getStudent_age() {
        return this.student_age;
    }

    public String getStudent_course() {
        return this.student_course;
    }


}
