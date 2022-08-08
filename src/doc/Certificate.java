package doc;

import people.Student;

public abstract class Certificate implements IShred {

    private Student student;
    private int certScore;

    public Certificate(int certScore) {
        this.certScore = certScore;
    }

    public abstract void stamp();

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getCertScore() {
        return certScore;
    }

    public void setCertScore(int certScore) {
        this.certScore = certScore;
    }
}