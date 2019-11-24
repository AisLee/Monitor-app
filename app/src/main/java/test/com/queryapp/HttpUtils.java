//package test.com.queryapp;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.util.Map;
//
///**
// * Created by Administrator on 2018/6/2.
// */
//
//public class HttpUtils {
//
//    private final static String PATH = "http://192.168.1.102:8080//SerialportToMysql/output.jsp";
//    private static URL url;
//
//    public HttpUtils(){
//
//    }
//
//    public static int sendMessage(Map<String,String>params,String encode){
//
//        StringBuffer strBuffer = new StringBuffer();
//
//        if (null != params && !params.isEmpty()){
//            for (Map.Entry<String, String> entry : params.entrySet()){
//                strBuffer.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue())).append("&");
//            }
//            ///< 删除多余的&
//            strBuffer.deleteCharAt(strBuffer.length() - 1);
//        }
//
//        try {
//            url = new URL(PATH);
//            if(null != url){
//                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
////                if(null == urlConnection){
////                    return -1;
////                }
//                urlConnection.setConnectTimeout(3000);
//                urlConnection.setRequestMethod("POST"); //向网络传输一部分数据
//                urlConnection.setDoInput(true);//表示从服务器获取数据
//                urlConnection.setDoOutput(true);//表示向服务器发送数据
//
//                byte[] data = strBuffer.toString().getBytes();
//
//                //设置请求体的是文本类型
//                urlConnection.setRequestProperty("Content-Type", "");
//                urlConnection.setRequestProperty("Content-Length", String.valueOf(data.length));
//
//                //获得输出流
//                OutputStream outputStream = urlConnection.getOutputStream();
//                outputStream.write(data);
//                outputStream.close();
//
//                //获得服务器的响应结果和状态码
//                int responseCode = urlConnection.getResponseCode();
//
//                if(200 == responseCode){
//                    return changeInputStream(urlConnection.getInputStream(),encode);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return -1;
//    }
//
//    private static int changeInputStream(InputStream inputStream, String encode) {
//
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        byte[] data = new byte[1024];
//        int len = 0;
//        String result = "";
//        if(null != inputStream){
//            try {
//                while((len = inputStream.read(data)) != -1){
//                    outputStream.write(data,0,len);
//                }
//                result = new String(outputStream.toByteArray(),encode);
//
//                len = Integer.parseInt(result.substring(0,1));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return len;
//    }
//
//}
