package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;

import java.util.ArrayList;

public class StudentService {

    private ArrayList<Student> students;

    public StudentService() {
        this.students = new ArrayList<>();
    }
    
    public void addStudent(Student student) {
        students.add(student);
    }

    public Student addStudent(String firstName, String lastName, String email, String batch) {
        int id = IdGenerator.getNextStudentId();
        Student student;
        if (InputValidator.isNonEmptyString(email)) {
            student = new Student(id, firstName, lastName, email, batch);
        } else {
            // Uses the constructor overload without email
            student = new Student(id, firstName, lastName, batch);
        }
        students.add(student);
        return student;
    }

    public Student findStudentById(int id) throws EntityNotFoundException {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        throw new EntityNotFoundException("Student with ID " + id + " not found.");
    }

    public ArrayList<Student> listAllStudents() {
        return new ArrayList<>(students);
    }

    public void deactivateStudent(int id) throws EntityNotFoundException {
        Student student = findStudentById(id);
        student.setActive(false);
    }

    public void updateStudent(int id, String firstName, String lastName, String email, String batch)
            throws EntityNotFoundException {
        Student student = findStudentById(id);
        if (InputValidator.isNonEmptyString(firstName)) {
            student.setFirstName(firstName);
        }
        if (InputValidator.isNonEmptyString(lastName)) {
            student.setLastName(lastName);
        }
        if (InputValidator.isNonEmptyString(email)) {
            student.setEmail(email);
        }
        if (InputValidator.isNonEmptyString(batch)) {
            student.setBatch(batch);
        }
    }

    public void removeStudent(int id) throws EntityNotFoundException {
        Student student = findStudentById(id);
        students.remove(student);
    }
}
