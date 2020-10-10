package repositories;

import models.Student;

import java.util.List;

public interface StudentsDao extends CrudDao<Student, Long> {
    List<Student> findAllStudentsWithNameLikePattern(String pattern);
}
