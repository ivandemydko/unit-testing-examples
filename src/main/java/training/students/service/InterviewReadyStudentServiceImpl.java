package training.students.service;

import lombok.SneakyThrows;
import training.students.domain.Student;
import training.students.domain.filters.CompanyStudentFilter;
import training.students.repository.CompanyStudentRepository;

import java.util.Collection;
import java.util.HashSet;

public class InterviewReadyStudentServiceImpl implements InterviewReadyStudentService {

    private final CompanyStudentRepository repository;
    private final Collection<CompanyStudentFilter> interviewReadyFilters;


    public InterviewReadyStudentServiceImpl(CompanyStudentRepository repository,
        Collection<CompanyStudentFilter> interviewReadyFilters) {
        this.repository = repository;
        this.interviewReadyFilters = interviewReadyFilters;
    }

    @Override
    public Collection<Student> getAllReadyForInterview() {
        Collection<Student> all = repository.getAll();
        if (interviewReadyFilters.size() == 0) {
            return all;
        }

        HashSet<Student> ready = interviewReadyFilters.stream()
                .map(filter -> filter.apply(all))
                .map(HashSet::new)
                .reduce((s1, s2) -> new HashSet<Student>() {{
                    addAll(s1);
                    addAll(s2);
                }})
                .orElseGet(HashSet::new);

        notifyRemoteServiceAboutGreatStudents(ready);
        return ready;
    }


    //bad-designed method, but it is here for a reason!
    @SneakyThrows
    void notifyRemoteServiceAboutGreatStudents(Collection<Student> students) {
        Thread.sleep(2000);
        //imagine that it actually happens
    }

}
