package util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;


/**
 * User: u6613739
 * Date: 2019/5/22
 * Time: 0:36
 * Description:
 */
public class UUIDUtils
{

    /**
     *  record the last machine milliseconds
     */
    private static long hostTimeLast = 0;

    /**
     *   record the number of hits in one milliseconds
     */
    private static int sameTimeNumber = 0;

    /**
     *   record the host ip in byte[]
     */
    private static byte[] hostAddress = new byte[4];

    /**
     *  get host ip
     */
    static
    {
        try
        {
            hostAddress = InetAddress.getLocalHost().getAddress();
        }
        catch (UnknownHostException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * generate a UUID
     *
     * @return String
     */
    public static synchronized byte[] generateUUID() throws Exception
    {
        byte[] uuid = new byte[21];
        String timeString;

        long fastTime = Calendar.getInstance().getTimeInMillis();
        if (hostTimeLast == fastTime)
        {
            sameTimeNumber++;
            if (sameTimeNumber == 10000)
            {
                sameTimeNumber = 0;
                Thread.sleep(1);
                fastTime = Calendar.getInstance().getTimeInMillis();
                hostTimeLast = fastTime;
            }
        }
        else
        {
            sameTimeNumber = 0;
            hostTimeLast = fastTime;
        }

        if (sameTimeNumber < 10)
        {
            timeString = fastTime + "000" + sameTimeNumber;
        }
        else if (sameTimeNumber < 100)
        {
            timeString = fastTime + "00" + sameTimeNumber;
        }
        else if (sameTimeNumber < 1000)
        {
            timeString = fastTime + "0" + sameTimeNumber;
        }
        else
        {
            timeString = fastTime + "" + sameTimeNumber;
        }
        byte[] timeByte = timeString.getBytes();

        int hostAddressLength = hostAddress.length;
        int timeByteLength = timeByte.length;
        System.arraycopy(hostAddress, 0, uuid, 0, hostAddressLength);
        System.arraycopy(timeByte, 0, uuid, hostAddressLength, timeByteLength);

        return uuid;
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {

    }
}
