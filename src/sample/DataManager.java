package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class DataManager {
    private String url;
    private Boolean status = false;

    public DataManager(String url_) {
        this.url = url;
        this.checkNetConnect();
    }

    private String checkNetConnect() {
        try {
            URL url = new URL("https://www.google.com");
            URLConnection connection = url.openConnection();
            connection.connect();
            System.out.println("Internet is connected");
            this.status = true;
        } catch (MalformedURLException e) {
            System.out.println("Internet is not connected");
            this.status = false;
        } catch (IOException e) {
            System.out.println("Internet is not connected");
            this.status = false;
        }
        return "ala ma kota";
    }

    public ArrayList<TestListModel> getData () {
        List<TestListModel> testList = new ArrayList<TestListModel>();
        try {
            URL url = new URL("http://api.testyudt.com/?api_key=a94691e0b90a4d866b99a28b8d377808&start_app=config");
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder sb = new StringBuilder();
            String str = "";
            while (null != (str = br.readLine())) {
                sb.append(str);
            }
            JSONArray jsonArray = new JSONArray(sb.toString());

           for (int x = 0; x < jsonArray.length(); x++) {
               TestListModel testListModel = new TestListModel();

                JSONObject jsonObject = new JSONObject(jsonArray.getString(x));
                testListModel.testName  = jsonObject.getString("test-name");
                testListModel.testDesc = jsonObject.getString("test-description");
                testListModel.url = jsonObject.getString("url");
                testListModel.time = jsonObject.getInt("time");
                testListModel.threshold = jsonObject.getInt("threshold");
                testListModel.testPool = jsonObject.getInt("test-pool");

               testList.add(testListModel);
           }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return (ArrayList<TestListModel>) testList;
    }

    public Boolean getNetStatus () {
        return this.status;
    }
}
