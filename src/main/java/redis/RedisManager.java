package redis;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * User: u6613739
 * Date: 2019/3/14
 * Time: 16:30
 * Description:
 *
 */
public class RedisManager
{
    static Jedis jedis;

    private synchronized void connect(String host, int dbIndex)
    {
        //TODO: use properties to fix host.
        jedis = new Jedis(host);
        jedis.connect();
        jedis.select(dbIndex);
    }

    private synchronized static void close()
    {
        jedis.close();
    }

    private synchronized static void switchDataBase(int dbIndex)
    {
        if (! jedis.isConnected())
        {
            jedis.connect();
        }
        jedis.select(dbIndex);
    }

    public synchronized static String getValue(String host, int dbIndex, String key)
    {
        String value = jedis.get(key);
        jedis.disconnect();
        return value;
    }

    public synchronized static boolean setValue(String host, int dbIndex, String key, String value)
    {
        String result = jedis.set(key, value);
        jedis.disconnect();
        if (result == "0")
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public synchronized static boolean addValue(String host, int dbIndex, String key, String value)
    {
        Long result = jedis.setnx(key, value);
        jedis.disconnect();
        if (result == 0L)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public static boolean deleteValue(String host, int dbIndex, String key)
    {
        Long result = jedis.del(key);
        jedis.disconnect();
        if (result == 0L)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public synchronized static boolean deleteAllValue(String host, int dbIndex)
    {
        Set<String> keys = jedis.keys("*");
        for (String key : keys)
        {
            jedis.del(key);
        }
        jedis.disconnect();
        return true;
    }


    public static long getListLength(String key)
    {
        return jedis.llen(key);
    }
}
