package mine.zhan.dao;

import mine.zhan.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Creator: zhanqian 17/12/15 下午9:51
 * Description:
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
