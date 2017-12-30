package mine.zhan;

import mine.zhan.dao.StudentRepository;
import mine.zhan.model.Student;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Creator: zhanqian 17/12/17 下午8:38
 * Description: JPA增删改查测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CRUDTest {

    @Resource
    private StudentRepository studentRepository;

    @Test
    @Ignore
    public void batchAdd() {
        String[] names = {"Ellen", "Alice", "Bob", "Cythia", "Def", "Guage"};
        int[] ages = {17, 18, 19, 20, 21, 22};
        for (int i = 0; i < 6; i++) {
            Student student = new Student();
            student.setAge(ages[i]);
            student.setName(names[i]);
            student.setGraduationTime(new Date());
            studentRepository.save(student);
        }
    }

    @Test
    public void getByName() {
//       Student students = studentRepository.findByName("Bob");

//       students.forEach((e)-> System.out.println(e.toString()));
    }
}
