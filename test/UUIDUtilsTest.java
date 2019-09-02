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
    //@Rule
    //public Timeout globalTimeout = Timeout.millis(2000);

    @Test
    public void timeLimitTest() throws Exception
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

        for(String item:list)
        {
            System.out.println(item);
        }
    }


    @Test
    public void test1()
    {
        String a = "12345677890";
        String b= "";
        for(int i=0;i<10000;i++)
        {
            b=b+a;
        }
        System.out.println(b);
    }
    @Test
    public void test2()
    {
        String a= "12345677890";
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<10000;i++)
        {
            sb.append(a);
        }
        System.out.println(sb.toString());
    }

}
