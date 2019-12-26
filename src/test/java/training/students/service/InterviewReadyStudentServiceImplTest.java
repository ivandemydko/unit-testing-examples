package training.students.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import training.students.domain.Student;
import training.students.domain.filters.CompanyStudentFilter;
import training.students.repository.CompanyStudentRepository;


@ExtendWith(MockitoExtension.class)
class InterviewReadyStudentServiceImplTest {

    @Mock
    private CompanyStudentRepository repository;

    private Collection<CompanyStudentFilter> interviewReadyFilters;

    private InterviewReadyStudentService interviewReadyStudentService;

    @DisplayName("TimeOut test.Retrieve All Students ready for interview with method substitution")
    @Test
    void retrieveAllStudentReadyForInterview() {
        interviewReadyFilters = mock(Collection.class);
        when(interviewReadyFilters.size()).thenReturn(1);
        interviewReadyStudentService = new InterviewReadyStudentServiceImplStub(repository, interviewReadyFilters);

        assertTimeout(Duration.ofMillis(1000), () -> {
            //write your test logic here
            interviewReadyStudentService.getAllReadyForInterview();
        });
    }


    @DisplayName("No Student ready for interview")
    @Test
    void whenNoStudentReadyForInterviewThenResultListIsEmpty() {
        interviewReadyFilters = mock(Collection.class);
        when(repository.getAll()).thenReturn(Collections.EMPTY_SET);
        interviewReadyStudentService = new InterviewReadyStudentServiceImplStub(repository, interviewReadyFilters);

        Collection<Student> studentReadyForInterview = interviewReadyStudentService.getAllReadyForInterview();

        assertEquals(0, studentReadyForInterview.size());
    }

    @DisplayName("All Students ready for interview")
    @Test
    void whenOnlyAllStudentReadyForInterview() {
        CompanyStudentFilter filter = mock(CompanyStudentFilter.class);
        when(filter.apply(any())).thenReturn(allSuccessfulStudentList());
        interviewReadyFilters = Arrays.asList(filter);

        when(repository.getAll()).thenReturn(allSuccessfulStudentList());
        interviewReadyStudentService = new InterviewReadyStudentServiceImplStub(repository, interviewReadyFilters);


        Collection<Student> studentsReadyForInterview = interviewReadyStudentService.getAllReadyForInterview();

        assertEquals(2, studentsReadyForInterview.size());
    }

    private Collection<Student> allSuccessfulStudentList() {
        return new HashSet<>(Arrays.asList(new Student("Peter", 5, 4),
            new Student("Sam", 5, 5)));
    }

    @DisplayName("Only one student is ready for interview")
    @Test
    void whenOnlyOneStudentReadyForInterview() {
        CompanyStudentFilter filter = mock(CompanyStudentFilter.class);
        when(filter.apply(any())).thenReturn(Collections.singleton(new Student("Sam", 5, 5)));
        interviewReadyFilters = Arrays.asList(filter);

        when(repository.getAll()).thenReturn(new HashSet<>(Arrays.asList(new Student("Peter", 3, 4),
            new Student("Sam", 5, 5))));


        interviewReadyStudentService = new InterviewReadyStudentServiceImplStub(repository, interviewReadyFilters);

        Collection<Student> studentsReadyForInterview = interviewReadyStudentService.getAllReadyForInterview();

        assertEquals(1, studentsReadyForInterview.size());
    }

    class InterviewReadyStudentServiceImplStub extends InterviewReadyStudentServiceImpl  {

        public InterviewReadyStudentServiceImplStub(final CompanyStudentRepository repository,
            final Collection<CompanyStudentFilter> interviewReadyFilters) {
            super(repository, interviewReadyFilters);
        }

        @Override
        void notifyRemoteServiceAboutGreatStudents(Collection<Student> students) {

        }
    }

// https://habr.com/ru/post/444982/

}