package training.students.service;

import java.util.Collection;

import training.students.domain.Student;

public interface InterviewReadyStudentService {
    Collection<Student> getAllReadyForInterview();
}
