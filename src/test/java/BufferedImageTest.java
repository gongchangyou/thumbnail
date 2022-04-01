import com.braindata.api.ApiApplication;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author gongchangyou
 * @version 1.0
 * @date 2022/3/15 9:41 上午
 */

@Slf4j
@SpringBootTest(classes= ApiApplication.class)
public class BufferedImageTest {

    private final String dir = "/Users/gongchangyou/Downloads/";

    @Test
    void createImage() {
        val start = System.currentTimeMillis();
        BufferedImage thumbImage = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = thumbImage.createGraphics();
        File file = new File(dir+ "数据中心.png");
        try {
            Image src = javax.imageio.ImageIO.read(file);
            g.drawImage(src.getScaledInstance(1000 ,1000,Image.SCALE_SMOOTH), 0, 0, null);
//            g.setColor(Color.red);
//            g.fill(new Ellipse2D.Float(0, 0, 100, 100));
            val random = new Random();
            for(int i = 0; i <=10000;i++) {
                val x = random.nextInt(1000);
                val y = random.nextInt(1000);
                Shape shape = new Rectangle2D.Double(x, y, 1, 1);
                g.setColor(Color.BLACK);
                g.fill(shape);
            }
//            g.dispose();

            val path = dir + "432.png";
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(path));

            var end = System.currentTimeMillis();
            log.info("cost1={}",end - start);
            String formatName = path.substring(path.lastIndexOf(".") + 1);
            ImageIO.write(thumbImage, /*"GIF"*/ formatName /* format desired */ , new File(path) /* target */ );

            end = System.currentTimeMillis();
            log.info("cost2={}",end - start);
            //关闭输出流
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}