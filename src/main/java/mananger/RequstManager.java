package mananger;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import sun.net.www.http.HttpClient;

import java.io.Closeable;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * User: u6613739
 * Date: 2019/3/25
 * Time: 13:16
 * Description:
 */
public class RequstManager
{
    /**
     * Success code of http request
     */
    private static final int SUCCESS_CODE = 200;


    public static String sendPostRequest(String url, List<NameValuePair> nameValuePairList) throws IOException
    {
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response;
        HttpPost httpPost = new HttpPost(url);
        StringEntity stringEntity = new UrlEncodedFormEntity(nameValuePairList, "UTF-8");
        httpPost.setEntity(stringEntity);
        httpPost.setHeader(new BasicHeader("Content-Type","application/x-www-form-urlencoded; charset=utf-8"));
        httpPost.addHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));
        response = client.execute(httpPost);
        return dealResponse(response);
    }
    public static String sendGetRequest(String url, List<NameValuePair> nameValuePairList) throws IOException
    {
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response;
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader(new BasicHeader("Content-Type","application/x-www-form-urlencoded; charset=utf-8"));
        httpGet.addHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));
        response = client.execute(httpGet);
        return dealResponse(response);
    }
    public static String sendDeleteRequest(String url, List<NameValuePair> nameValuePairList) throws IOException
    {
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response;
        HttpDelete httpDelete = new HttpDelete(url);
        httpDelete.setHeader(new BasicHeader("Content-Type","application/x-www-form-urlencoded; charset=utf-8"));
        httpDelete.addHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));
        response = client.execute(httpDelete);
        return dealResponse(response);
    }
    public static String sendPutRequest(String url, List<NameValuePair> nameValuePairList) throws IOException
    {
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response;
        HttpPut httpPut = new HttpPut(url);
        httpPut.setHeader(new BasicHeader("Content-Type","application/x-www-form-urlencoded; charset=utf-8"));
        httpPut.addHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));
        response = client.execute(httpPut);
        return dealResponse(response);


        return false;
    }

    private static String dealResponse(CloseableHttpResponse response) throws IOException
    {
        int statusCode = response.getStatusLine().getStatusCode();
        switch (statusCode)
        {
            case SUCCESS_CODE:
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity,"UTF-8");
                return result;
            default:return null;
        }
    }
}
