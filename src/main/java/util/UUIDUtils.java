package util;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * User: u6613739
 * Date: 2019/5/22
 * Time: 0:36
 * Description:
 */
public class UUIDUtils {


    private static  String hostTimeLast="";
    private static int sameTimeNumber=0;

    /**
     * generate a UUID
     *
     * @return BigInteger
     */
    public synchronized static BigInteger generateUUID() {
        String uuidString="";
        try{
            String hostAddress=InetAddress.getLocalHost().getHostAddress();
            String hostTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            if(hostTimeLast.equals(hostTime)) {
                sameTimeNumber++;
            }
            else {
                sameTimeNumber=0;
                hostTimeLast=hostTime;
            }
            uuidString=hostAddress+hostTime+sameTimeNumber;
            uuidString=uuidString.replaceAll("[^0-9]", "");
        }catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return new BigInteger(uuidString,10);
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
