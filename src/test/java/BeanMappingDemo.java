import org.crazy.utils.BeanMapping;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * @author Crazy.X
 */
public class BeanMappingDemo {

    /**
     * 自动映射相同对象字段
     * 返回实例对象
     */
    @Test
    public void toBeanTest() {
        Student student = new Student("kobe", 18, "男", LocalDateTime.now());
        Man man1 = BeanMapping.toBean(student, Man.class);
        Man man2 = BeanMapping.toBean(student, Man.class, (s, t) -> t.setGender(s.getSex()));
        System.out.println(man1);
        System.out.println(man2);
    }

    /**
     * 自动映射相同对象字段
     * 手动弥补不同字段
     * 返回实例对象
     */
    @Test
    public void toJsonBeanTest() {
        Student student = new Student("kobe", 18, "男", LocalDateTime.now());
        String s1 = BeanMapping.toJsonBean(student, Man.class);
        String s2 = BeanMapping.toJsonBean(student, Man.class, (s, t) -> t.setGender(s.getSex()));
        System.out.println(s1);
        System.out.println(s2);
    }

    /**
     * 自动映射相同对象字段
     * 手动弥补不同字段
     * 返回实例对象List
     */
    @Test
    public void toListTest() {
        Student s1 = new Student("kobe", 18, "男", LocalDateTime.now());
        Student s2 = new Student("james", 18, "男", LocalDateTime.now());
        List<Man> men1 = BeanMapping.toList(Arrays.asList(s1, s2), Man.class);
        List<Man> men2 = BeanMapping.toList(Arrays.asList(s1, s2), Man.class, (s, t) -> t.setAge(String.valueOf(s.getAge())));
        System.out.println(men1);
        System.out.println(men2);
    }

    /**
     * 自动映射相同对象字段
     * 手动弥补不同字段
     * 返回实例对象List
     */
    @Test
    public void toJsonListTest() {
        Student s1 = new Student("kobe", 18, "男", LocalDateTime.now());
        Student s2 = new Student("james", 18, "男", LocalDateTime.now());
        String s3 = BeanMapping.toJsonList(Arrays.asList(s1, s2), Man.class);
        String s4 = BeanMapping.toJsonList(Arrays.asList(s1, s2), Man.class, (s, t) -> t.setAge(String.valueOf(s.getAge())));
        System.out.println(s3);
        System.out.println(s4);
    }


    /**
     * 自动映射相同对象字段
     * 手动弥补不同字段
     * 返回实例对象Set
     */
    @Test
    public void fun4() {
        Student s1 = new Student("kobe", 28, "男", LocalDateTime.now());
        Student s2 = new Student("james", 18, "男", LocalDateTime.now());
        Student s3 = new Student("kobe", 28, "男", LocalDateTime.now());
        String s4 = BeanMapping.toJsonSet(Arrays.asList(s1, s2, s3), Man.class);
        String s5 = BeanMapping.toJsonSet(Arrays.asList(s1, s2, s3), Man.class, (s, t) -> t.setAge(String.valueOf(s.getAge())));
        System.out.println(s4);
        System.out.println(s5);
    }
}
