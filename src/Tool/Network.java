package Tool;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 网络处里类
 */
public class Network {
    public static String HttpRequestString(String Url, HttpMode HttpMode, String Cookies) {
        String ReturnData;
        try {
            URL urlObject = new URL(Url);

            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
            //设置请求方式
            connection.setRequestMethod(HttpMode.toString());
            //设置Cookie
            connection.setRequestProperty("Cookie", Cookies);

            // 获取响应状态码
            //int responseCode = connection.getResponseCode();

            // 读取响应内容
            ReturnData = GetReturnString(connection);
        } catch (Exception e) {
            //System.out.print(e.getMessage());
            System.err.println(e.getMessage());
            return null;
        }
        return ReturnData;
    }

    public static String HttpRequestString(String Url, HttpMode HttpMode, String Cookies, String Data, String Type) {
        String ReturnData;
        try {
            URL urlObject = new URL(Url);

            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
            //设置请求方式
            connection.setRequestMethod(HttpMode.toString());
            //设置Cookie
            connection.setRequestProperty("Cookie", Cookies);
            //设置输出
            connection.setDoOutput(true);
            //设置数据类型
            connection.setRequestProperty("Content-Type", Type);
            //发送数据
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(Data);
            writer.flush();
            writer.close();
            // 获取响应状态码
            //int responseCode = connection.getResponseCode();

            // 读取响应内容
            ReturnData = GetReturnString(connection);
        } catch (Exception e) {
            //System.out.print(e.getMessage());
            System.err.println(e.getMessage());
            return null;
        }
        return ReturnData;
    }

    public static String HttpRequestString(String Url, HttpMode HttpMode, String Cookies, String Data, ContentType ContentType) {
        String Type = null;
        switch (ContentType) {
            case File:
                Type = "application/octet-stream";
                break;
            case Json:
                Type = "application/json";
                break;
            case String:
                Type = "text/html";
                break;
        }
        return HttpRequestString(Url, HttpMode, Cookies, Data, Type);
    }

    @NotNull
    private static String GetReturnString(HttpURLConnection connection) throws IOException {
        String ReturnData;
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        ReturnData = response.toString();
        return ReturnData;
    }

    /**
     * Http请求模式
     */
    public enum HttpMode {
        GET,
        POST,
        PUT,
        DELETE,
        PATCH
    }

    /**
     * Http发送内容
     */
    public enum ContentType {
        Json,
        File,
        String
    }
}
