package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.ArrayList;

public class CourseService {

    private ArrayList<Course> courses;

    public CourseService() {
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public Course addCourse(String courseName, String description, int durationInWeeks) {
        int id = IdGenerator.getNextCourseId();
        Course course = new Course(id, courseName, description, durationInWeeks);
        courses.add(course);
        return course;
    }

    public Course findCourseById(int id) throws EntityNotFoundException {
        for (Course course : courses) {
            if (course.getId() == id) {
                return course;
            }
        }
        throw new EntityNotFoundException("Course with ID " + id + " not found.");
    }

    public ArrayList<Course> listAllCourses() {
        return new ArrayList<>(courses);
    }

    public void activateCourse(int id) throws EntityNotFoundException {
        Course course = findCourseById(id);
        course.setActive(true);
    }

    public void deactivateCourse(int id) throws EntityNotFoundException {
        Course course = findCourseById(id);
        course.setActive(false);
    }
}
