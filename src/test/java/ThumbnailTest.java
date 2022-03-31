import com.braindata.api.ApiApplication;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author gongchangyou
 * @version 1.0
 * @date 2022/3/15 9:41 上午
 */

@Slf4j
@SpringBootTest(classes= ApiApplication.class)
public class ThumbnailTest {

    private final String dir = "/Users/gongchangyou/Downloads/";

    @Test
    void single() {
        try {
            Thumbnails.of(new File(dir+"1643523559576.jpg"))
                    .size(360, 360)
                    .toFile(new File(dir+"1643523559576-thumbnail.jpg"));

        } catch (IOException e) {
            log.error("error", e);
        }
    }

    @Test
    void list() {
        try {
            Thumbnails.of(Arrays.stream(new File(dir).listFiles())
                            .filter(f -> f.getName().contains("jpg"))
                            .collect(Collectors.toList())
                            .toArray(new File[0])
                    )
                    .size(640, 480)
                    .outputFormat("jpg")
                    .toFiles(Rename.PREFIX_DOT_THUMBNAIL);
        } catch (IOException e) {
            log.error("error", e);
        }
    }
}