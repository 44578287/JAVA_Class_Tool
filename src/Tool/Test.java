package Tool;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Test {
    public static void HttpTool(URL url) {
        try {
            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 设置请求方法为GET
            connection.setRequestMethod("GET");

            // 获取响应状态码
            int responseCode = connection.getResponseCode();
            System.out.println("响应代码: " + responseCode);

            // 读取响应内容
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // 输出响应内容
            System.out.println("响应内容:" + response);

            // 关闭连接
            connection.disconnect();

        } catch (Exception e) {

        }
    }
}
