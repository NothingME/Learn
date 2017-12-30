package mine.zhan.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Creator: zhanqian 17/12/15 下午9:51
 * Description:
 */
@Entity
@Table(name = "STUDENT")
public class Student {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    /**
     * 姓名
     */
    @Column(name = "NAME", nullable = false)
    private String name;

    /**
     * 年龄
     */
    @Column(name = "AGE", nullable = false)
    private Integer age;

    /**
     * 邮箱
     */
    @Column(name = "EMAIL")
    private String email;

    /**
     * 毕业时间
     */
    @Column(name = "GRADUATION_TIME")
    private Date graduationTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getGraduationTime() {
        return graduationTime;
    }

    public void setGraduationTime(Date graduationTime) {
        this.graduationTime = graduationTime;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", graduationTime=" + graduationTime +
                '}';
    }
}
