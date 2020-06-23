import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Crazy.X
 */
@Data
public class Man  implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userName;

    private String age;

    private String gender;

    private LocalDateTime localDateTime;
}
