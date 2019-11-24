//package test.com.queryapp;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.ProtocolException;
//import java.net.URL;
//
///**
// * Created by Administrator on 2018/6/2.
// */
//
//public class WebService {
//
//    public static String executeHttpGet(){
//        HttpURLConnection connection = null;
//        InputStream is =null;
//
//        String path = "http://192.168.1.102:8080//SerialportToMysql/";
//
//        try {
//            connection = (HttpURLConnection) new URL(path).openConnection();
//            connection.setConnectTimeout(3000);
//            connection.setReadTimeout(3000);
//            connection.setDoInput(true);
//            connection.setDoOutput(true);
//            connection.setRequestMethod("GET");
//            connection.setRequestProperty("Charset","utf-8");
//            if(connection.getResponseCode() == 200){
//                is = connection.getInputStream();
//                return parseInfo(is);
//            }
//        } catch (ProtocolException e) {
//            e.printStackTrace();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if(connection != null){
//                connection.disconnect();
//            }
//            if(is != null){
//                try {
//                    is.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        return null;
//    }
//
//    private static String parseInfo(InputStream inputStream) throws Exception {
//        byte[] data = read(inputStream);
//        return new String(data,"utf-8");
//    }
//
//    private static byte[] read(InputStream inputStream) throws Exception {
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        byte[] buffer = new byte[1024];
//        int len = 0;
//        while ((len = inputStream.read(buffer)) != -1) {
//            outputStream.write(buffer, 0, len);
//        }
//        inputStream.close();
//        return outputStream.toByteArray();
//    }
//}
//
