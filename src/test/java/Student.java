import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Crazy.X
 */
@Data
@AllArgsConstructor
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userName;

    private Integer age;

    private String sex;

    private LocalDateTime localDateTime;
}
