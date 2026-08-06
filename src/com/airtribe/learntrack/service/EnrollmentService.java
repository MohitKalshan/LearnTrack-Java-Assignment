package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.util.IdGenerator;

import java.time.LocalDate;
import java.util.ArrayList;

public class EnrollmentService {

    private ArrayList<Enrollment> enrollments;
    private StudentService studentService;
    private CourseService courseService;

    public EnrollmentService(StudentService studentService, CourseService courseService) {
        this.enrollments = new ArrayList<>();
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public Enrollment enrollStudent(int studentId, int courseId) throws EntityNotFoundException {
        // Validate that both student and course exist (will throw if not found)
        studentService.findStudentById(studentId);
        courseService.findCourseById(courseId);

        int id = IdGenerator.getNextEnrollmentId();
        String today = LocalDate.now().toString(); // yyyy-MM-dd format
        Enrollment enrollment = new Enrollment(id, studentId, courseId, today);
        enrollments.add(enrollment);
        return enrollment;
    }

    public ArrayList<Enrollment> getEnrollmentsForStudent(int studentId) {
        ArrayList<Enrollment> result = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentId() == studentId) {
                result.add(enrollment);
            }
        }
        return result;
    }

    public void updateEnrollmentStatus(int enrollmentId, EnrollmentStatus status)
            throws EntityNotFoundException {
        Enrollment enrollment = findEnrollmentById(enrollmentId);
        enrollment.setStatus(status);
    }

    public ArrayList<Enrollment> listAllEnrollments() {
        return new ArrayList<>(enrollments);
    }

    public Enrollment findEnrollmentById(int id) throws EntityNotFoundException {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getId() == id) {
                return enrollment;
            }
        }
        throw new EntityNotFoundException("Enrollment with ID " + id + " not found.");
    }
}
