import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: u6613739
 * Date: 2019/2/28
 * Time: 10:04
 * Description:
 */
public class LogMannager
{
    public static void logSuccess(String message,boolean isSuccess)
    {
        log(EnumExceptionType.SUCCESS,message,isSuccess,null);
    }

    public static void logError(String message, Exception error)
    {
        log(EnumExceptionType.ERROR,message,false,error);
    }

    public static void logWarnning(String message, Exception warnning)
    {
        log(EnumExceptionType.WARNNING,message,true,warnning);
    }

    public static void logFatal(String message, Exception fatal)
    {
        log(EnumExceptionType.FATAL,message,true,fatal);
    }

    public static void logMessage(String message,boolean isSuccess,Exception e)
    {
        log(EnumExceptionType.UNKOWN,message,true,e);
    }
    private static synchronized void log(EnumExceptionType exceptionType, String message, boolean isSuccess, Exception e)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getDate());
        stringBuilder.append("-----");
        stringBuilder.append(exceptionType);
        stringBuilder.append("-----");
        stringBuilder.append(isSuccess?"value: true":"value: false");
        stringBuilder.append(".    ");
        stringBuilder.append(message);
        stringBuilder.append("\r\n");
        stringBuilder.append(e.getMessage());

        System.out.println(stringBuilder);
    }
    private static String getDate()
    {
        Date curData=new Date();
        System.out.println(curData);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String curDateStr=simpleDateFormat.format(curData);
        return curDateStr;
    }
}
