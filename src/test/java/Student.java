import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Crazy.X
 */
@Data
@AllArgsConstructor
public class Student {

    private String userName;

    private Integer age;

    private String sex;

    private LocalDateTime localDateTime;
}
