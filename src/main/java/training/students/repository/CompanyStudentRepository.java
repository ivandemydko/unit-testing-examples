package training.students.repository;

import java.util.Collection;

import training.students.domain.Student;

public interface CompanyStudentRepository {
    Collection<Student> getAll();
}
