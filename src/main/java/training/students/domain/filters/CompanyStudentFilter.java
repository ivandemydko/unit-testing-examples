package training.students.domain.filters;

import training.students.domain.Student;

import java.util.Collection;
import java.util.function.UnaryOperator;

public interface CompanyStudentFilter extends UnaryOperator<Collection<Student>> {

}

