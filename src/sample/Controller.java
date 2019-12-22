package sample;
import javafx.event.ActionEvent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Controller {

    public Button start;

    public void startBtn(ActionEvent actionEvent) {
        DataManager dataManager = new DataManager("http://api.testyudt.com/?api_key=a94691e0b90a4d866b99a28b8d377808&start_app=config");
        if (dataManager.getNetStatus()) {
            System.out.println("Net OK");
            ArrayList<TestListModel> tests = dataManager.getData();
            for (int x = 0; x < tests.size(); x++) {
                System.out.println(tests.get(x).testDesc);
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "WARNING.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
}
