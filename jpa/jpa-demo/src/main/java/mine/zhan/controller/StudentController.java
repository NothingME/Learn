package mine.zhan.controller;

import mine.zhan.dao.StudentRepository;
import mine.zhan.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Creator: zhanqian 17/12/16 上午8:39
 * Description:
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @RequestMapping("hello")
    public String hello(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @RequestMapping("findByName")
    public List<Student> findByName(@RequestParam("name") String name) {
//        List<Student> students = studentRepository.findByName(name);
//        return students;
        return null;
    }

}
