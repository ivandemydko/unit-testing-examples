package training.students.service;

import java.util.Collection;

import lombok.SneakyThrows;
import training.students.domain.Student;

public class NotificationService {
    @SneakyThrows
    public void notifyRemoteServiceAboutGreatStudents(Collection<Student> students) {
        Thread.sleep(2000);
        //imagine that it actually happens
    }

}
