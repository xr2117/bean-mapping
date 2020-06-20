import org.crazy.utils.BeanMapping;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author Crazy.X
 */
public class BeanMappingDemo {

    /**
     * 自动映射相同对象字段
     * 返回实例对象
     */
    @Test
    public void fun1() {
        Student student = new Student("kobe", 18, LocalDateTime.now());
        Man man = BeanMapping.toBean(student, Man.class);
        System.out.println(man);
    }

    /**
     * 自动映射相同对象字段
     * 手动弥补不同字段
     * 返回实例对象
     */
    @Test
    public void fun2() {
        Student student = new Student("kobe", 18, LocalDateTime.now());
        Man man = BeanMapping.toBean(student, Man.class, (s, t) -> t.setAge(String.valueOf(s.getAge())));
        System.out.println(man);
    }

    /**
     * 自动映射相同对象字段
     * 手动弥补不同字段
     * 返回实例对象List
     */
    @Test
    public void fun3() {
        Student s1 = new Student("kobe", 18, LocalDateTime.now());
        Student s2 = new Student("james", 18, LocalDateTime.now());
        List<Man> men = BeanMapping.toList(Arrays.asList(s1, s2), Man.class, (s, t) -> t.setAge(String.valueOf(s.getAge())));
        System.out.println(men);
    }

    /**
     * 自动映射相同对象字段
     * 手动弥补不同字段
     * 返回实例对象Set
     */
    @Test
    public void fun4() {
        Student s1 = new Student("kobe", 28, LocalDateTime.now());
        Student s2 = new Student("james", 18, LocalDateTime.now());
        Student s3 = new Student("kobe", 28, LocalDateTime.now());
        Set<Man> men = BeanMapping.toSet(Arrays.asList(s1, s2, s3), Man.class, (s, t) -> t.setAge(String.valueOf(s.getAge())));
        System.out.println(men);
    }
}
