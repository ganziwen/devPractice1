package characteristic;

import lombok.Data;
import org.testng.annotations.Test;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2022/2/10-20:03
 */
public class Inherit {
    @Test
    public void testInherit() {
        Teacher teacher = new Teacher("老师的名字", 30, "特级教师");
        Student student = new Student("学生的名字", 16, "高三");
        System.out.println(teacher.teach());
        System.out.println(student.stu());
    }
}


class Person {
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    String name;
    int age;
}

class Teacher extends Person {
    public Teacher(String name, int age, String grade) {
        super(name, age);
        this.grade = grade;
    }

    private String grade;

    public String teach() {
        return String.format("teacher name is:%s,grade is :%s,age is:%s", this.name, this.grade, this.age);
    }
}

class Student extends Person {
    private String className;

    public Student(String name, int age, String className) {
        super(name, age);
        this.className = className;
    }

    public String stu() {
        return String.format("students name is:%s,class is :%s,age is:%s", this.name, this.className, this.age);
    }
}