package dtos;

public class StudentFormData {
    private int age;
    private int course;
    private String name;
    private String surname;
    private boolean deducted;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isDeducted() {
        return deducted;
    }

    public void setDeducted(String deducted) {
        this.deducted   = (deducted.equals("on"));
    }
}
