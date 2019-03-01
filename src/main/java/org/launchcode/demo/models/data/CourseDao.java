package org.launchcode.demo.models.data;

import org.launchcode.demo.models.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CourseDao extends CrudRepository<Course, Integer> {
}
