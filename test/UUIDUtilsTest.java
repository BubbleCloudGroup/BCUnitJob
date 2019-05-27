import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import util.UUIDUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * User: u6613739
 * Date: 2019/5/27
 * Time: 21:38
 * Description:
 */
public class UUIDUtilsTest
{
    @Rule
    public Timeout globalTimeout = Timeout.millis(2000);

    @Test
    public void timeLimitTest()
    {
        List<String> list = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++)
        {
            String a = UUIDUtils.generateUUID().toString();
            list.add(a);
        }
        long endTime = System.currentTimeMillis();

        System.out.println("the current time span is ：" + (endTime - startTime) + "ms");
    }
}
