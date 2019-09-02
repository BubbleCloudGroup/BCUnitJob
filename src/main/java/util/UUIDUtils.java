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
public class UUIDUtils {


    private static  long hostTimeLast=0;
    private static int sameTimeNumber=0;
    private static String hostAddress;

    static {
        try {
            String tempHostAddress = InetAddress.getLocalHost().getHostAddress();
            int pos1 = tempHostAddress.indexOf(".");
            int pos2 = tempHostAddress.indexOf(".", pos1 + 1);
            int pos3 = tempHostAddress.indexOf(".", pos2 + 1);

            int number;
            number = tempHostAddress.substring(0, pos1).length();
            if (number == 1) {
                hostAddress = "00" + tempHostAddress.substring(0, pos1);
            } else if (number== 2) {
                hostAddress = "0" + tempHostAddress.substring(0, pos1);
            } else {
                hostAddress = tempHostAddress.substring(0, pos1);
            }
            number = tempHostAddress.substring(pos1 + 1, pos2).length();
            if (number == 1) {
                hostAddress = hostAddress + "00" + tempHostAddress.substring(pos1 + 1, pos2);
            } else if (number == 2) {
                hostAddress = hostAddress + "0" + tempHostAddress.substring(pos1 + 1, pos2);
            } else {
                hostAddress = hostAddress + tempHostAddress.substring(pos1 + 1, pos2);
            }
            number = tempHostAddress.substring(pos2 + 1, pos3).length();
            if (number == 1) {
                hostAddress = hostAddress + "00" + tempHostAddress.substring(pos2 + 1, pos3);
            } else if (number == 2) {
                hostAddress = hostAddress + "0" + tempHostAddress.substring(pos2 + 1, pos3);
            } else {
                hostAddress = hostAddress + tempHostAddress.substring(pos2 + 1, pos3);
            }
            number = tempHostAddress.length() - (pos3 + 1);
            if (number == 1) {
                hostAddress = hostAddress + "00" + tempHostAddress.substring(pos3 + 1);
            } else if (number == 2) {
                hostAddress = hostAddress + "0" + tempHostAddress.substring(pos3 + 1);
            } else {
                hostAddress = hostAddress + tempHostAddress.substring(pos3 + 1);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    /**
     * generate a UUID
     *
     * @return String
     */
    public static synchronized  String generateUUID() {
        String uuidString;
        long fastTime = Calendar.getInstance().getTimeInMillis();
        if (hostTimeLast == fastTime) {
            sameTimeNumber++;
        } else {
            sameTimeNumber = 0;
            hostTimeLast = fastTime;
        }
        uuidString = hostAddress + fastTime + sameTimeNumber;
        return uuidString;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
