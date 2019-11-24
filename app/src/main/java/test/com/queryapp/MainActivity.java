package test.com.queryapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView Temperature;
    private TextView Water;
    private TextView Fire;
    private TextView Smoke;
    private TextView Light;

    private Switch fan;
    private Switch light;
    private Switch water;
    private Switch auto;

    private Button Flash;

    private String PATH = "http://192.168.43.67:8080//SerialportToMysql/add";
    private String pathTemp;

    private final String read = "read";
    private final String open = "open";
    private final String close = "close";

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Temperature = findViewById(R.id.edTemperature);
        Water = findViewById(R.id.edWater);
        Fire = findViewById(R.id.edFire);
        Smoke = findViewById(R.id.edSmoke);
        Light = findViewById(R.id.edLight);

        fan = findViewById(R.id.swFan);
        light = findViewById(R.id.swLight);
        water = findViewById(R.id.swWater);
        auto = findViewById(R.id.swAuto);

        Flash = findViewById(R.id.btnFlash);

        Flash.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String[] temper = new String []{"26.5","20.6","24.5","28.0","30.1","27.6"};
                String[] water = new String[]{"76.5","70.6","75.9","69.4","65.8","73.2"};
                String[] str = new String[]{"有","无"};

                final int[] a = new int[1];
                final int[] b = new int[1];
                final int[] c = new int[1];

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        a[0] = (int)Math.random()*6;
                        b[0] = (int)Math.random()*6;
                        c[0] = (int)Math.random()*2;

                    }
                }).start();

                Temperature.setText(temper[a[0]]);
                Water.setText(water[b[0]]);
                Fire.setText(str[c[0]]);
                Smoke.setText(str[c[0]]);
                Light.setText(str[c[0]]);

            }
        });

    }

    public void writeDatabase(final String path){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(10000);
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setUseCaches(false);
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("Charset", "utf-8");
                    connection.connect();

                    if(connection.getResponseCode() == 200)
                        System.out.println("写入成功");
                    connection.disconnect();

                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }


    public void onSwitchClicked(View view){
        switch(view.getId()) {
            case R.id.swFan:
                if(fan.isChecked()) {
                    pathTemp = PATH + "?s1=" + open;
                    writeDatabase(pathTemp);
                    pathTemp = "";
                }else {
                    pathTemp = PATH + "?s1=" + close;
                    writeDatabase(pathTemp);
                    pathTemp = "";
                }
                break;
            case R.id.swLight:
                if(light.isChecked()) {
                    pathTemp = PATH + "?s2=" + open;
                    writeDatabase(pathTemp);
                    pathTemp = "";
                }else {
                    pathTemp = PATH + "?s2=" + close;
                    writeDatabase(pathTemp);
                    pathTemp = "";
                }
                break;
            case R.id.swWater:
                if(water.isChecked()) {
                    pathTemp = PATH + "?s3=" + open;
                    writeDatabase(pathTemp);
                    pathTemp = "";
                }else {
                    pathTemp = PATH + "?s3=" + close;
                    writeDatabase(pathTemp);
                    pathTemp = "";
                }
                break;
            case R.id.swAuto:
                if(!auto.isChecked()) {
                    fan.setVisibility(fan.VISIBLE);
                    light.setVisibility(light.VISIBLE);
                    water.setVisibility(water.VISIBLE);
                    pathTemp = PATH + "?s4=" + open;
                    writeDatabase(pathTemp);
                    pathTemp = "";
                }else {
                    fan.setVisibility(fan.INVISIBLE);
                    light.setVisibility(light.INVISIBLE);
                    water.setVisibility(water.INVISIBLE);
                    pathTemp = PATH + "?s4=" + close;
                    writeDatabase(pathTemp);
                    pathTemp = "";
                }
                break;
            default:
                break;
        }
    }

    public void readDatabase(final String path){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(10000);
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setUseCaches(false);
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("Charset", "utf-8");
                    connection.connect();
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    byte[] data = new byte[1024];
                    int len;
                    InputStream is = connection.getInputStream();
                    while ((len = is.read(data)) != -1)
                        outputStream.write(data,1,len);

                    List<String> mlists = new ArrayList<>();
                    byte[] data1 = outputStream.toByteArray();
                    JSONArray array = new JSONArray(new String(data1));
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject item = array.getJSONObject(i);
                        String s1 = item.getString("wendu");
                        String s2 = item.getString("shidu");
                        String s3 = item.getString("huoyan");
                        String s4 = item.getString("yanwu");
                        String s5 = item.getString("guangzhao");
                        mlists.add(s1);
                        mlists.add(s2);
                        mlists.add(s3);
                        mlists.add(s4);
                        mlists.add(s5);
                    }

                    if(connection.getResponseCode() == 200) {
                        System.out.println(mlists.isEmpty());
                        System.out.println("读取成功");
                    }
                    connection.disconnect();

                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }


}

