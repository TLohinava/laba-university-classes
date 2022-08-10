package doc;

import people.Student;

public class PassbookEntry<T> {

    private Student student;
    private String subject;
    private T mark;

    public PassbookEntry(String subject, T mark) {
        this.subject = subject;
        this.mark = mark;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public T getMark() {
        return mark;
    }

    public void setMark(T mark) {
        this.mark = mark;
    }
}
