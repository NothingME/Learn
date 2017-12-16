package mine.zhan.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Creator: zhanqian 17/12/16 上午8:39
 * Description:
 */
@RestController
@RequestMapping("student")
public class StudentController {

    @RequestMapping("hello")
    public String hello(@RequestParam("name") String name) {
        return "hello " + name;
    }


}
