package training.students.domain.filters;

import training.students.domain.Student;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

public class WithinLabSufficientMarkFilter implements CompanyStudentFilter {

    @Override
    public Collection<Student> apply(Collection<Student> students) {
        return students.stream()
                .filter(s -> s.getWithinLabMark() > 3)
                .collect(toList());
    }
}
