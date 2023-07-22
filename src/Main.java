import Tool.Network;
import com.alibaba.fastjson2.JSONObject;

import java.net.MalformedURLException;

// 按两次 Shift 打开“随处搜索”对话框并输入 `show whitespaces`，
// 然后按 Enter 键。现在，您可以在代码中看到空格字符。
public class Main {
    public static void main(String[] args) throws MalformedURLException {
        //Test.HttpTool(new URL("https://google.com"));
        //System.out.println(Network.HttpMode.GET.toString());
        //String Temp = Network.HttpRequestString("http://100.127.129.46:5177/User/GetUserList", Network.HttpMode.GET,null);
        Register_Input Data = new Register_Input();
        Data.setAccount("123");
        Data.setMail("123@1132.cas");
        Data.setPassword("96545684");
        Data.setGroups(1);
        String OutData = JSONObject.toJSONString(Data);

        String Temp = Network.HttpRequestString("http://100.127.129.46:5177/User/Register", Network.HttpMode.POST, null, OutData, Network.ContentType.Json);
        System.out.println(Temp);
        Register_Return ReturnData = JSONObject.parseObject(Temp, Register_Return.class);

        System.out.println("Code:" + ReturnData.getCode());
        System.out.println("Msg:" + ReturnData.getMsg());

    }
}
