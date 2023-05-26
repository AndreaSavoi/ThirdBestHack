package com.example.thirdbesthack;

import java.util.ArrayList;
import java.util.List;

public class NoFlag {

    private List<String> naviNoFlag = new ArrayList<String>();
    String flagString;

    public void NoFlag(){
        for(int i = 1; i < DummyCsv.rowCount; i++) {
            flagString = DummyCsv.data[i][6];
            if(flagString.isEmpty()) {
                naviNoFlag.add(DummyCsv.data[i][0]);
            }
        }
    }

    public List<String> getListNaviNoFlag() {
        return naviNoFlag;
    }
}
