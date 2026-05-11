package com.tindpt;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class Profile {

    private static final String DEFAULT_SEPARATOR = ",";
    private static final String NEW_LINE = "\r\n";
    private static final String TEXT_PATH = "d:\\";
    private static final String DSV_NAME = "xinviec.txt";
    private static final String HK_NAME = "hanhkiem.txt";
    private static final String LL_NAME = "lylich.txt";
    private static final String HKOCEO_NAME = "hanhkiemoceo.txt";
    private static final String LL1_NAME = "lylich1.txt";
    private static final String[] ATTRNAMEARRAY = {
        "hvt",
        "sinhngay",
        "sinhthang",
        "sinhnam",
        "fullsinh",
        "noisinh",
        "cmnd",
        "ngaycap",
        "thangcap",
        "namcap",
        "ngaycapfull",
        "captai",
        "thuongtru",
        "ap",
        "xa",
        "huyen",
        "tinh",
        "dantoc",
        "tongiao",
        "trinhdo",
        "tencha",
        "tuoicha",
        "nghecha",
        "hienocha",
        "tenme",
        "tuoime",
        "ngheme",
        "hienome",
        "ac1",
        "ac2",
        "ac3",
        "ngheac",
        "hienoac",
        "ngayki",
        "thangki",
        "namki"};
    private static final ArrayList<String> ATTRNAMELIST = new ArrayList<String>(Arrays.asList(ATTRNAMEARRAY));
    private static final int[] HK_LIST = {1, 4, 7, 11, 12, 14, 15, 16, 17, 34, 35, 36};
    private static final int[] SV_LIST = {12, 7, 1, 10, 8, 6, 4, 2, 3, 9, 13, 20, 15, 34, 35, 36};
    private static final int[] LL_LIST = {19, 18, 27, 32, 29, 30, 31, 26, 21, 24, 25, 33, 28, 13, 12, 11, 7, 6, 2, 23, 22, 3, 4, 1, 20};
    private static final int[] HKOCEO_LIST = {19, 18, 27, 32, 29, 30, 31, 26, 21, 24, 25, 33, 28, 13, 12, 11, 7, 6, 2, 23, 22, 3, 4, 1, 20, 34, 35, 36};
    private static final int[] LL1_LIST = {1, 34, 15, 35, 36};
    private static final int[] KEY_LIST = {1, 2};
    public ArrayList<String> profile;

    public String Concat(ArrayList<String> list) {
        return String.join(",", list);
    }

    public void store2DB() throws SQLException {
        ArrayList<String> key_list = new ArrayList<String>();
        for (int index : KEY_LIST) {
            key_list.add(profile.get(index - 1));
        }
        String key = Concat(key_list);
        String value = Concat(profile);
        DatabaseController.insertWithStatement(key, value);

    }

    public String getSpecifiedField(int[] num_list, ArrayList<String> key_list, ArrayList<String> val_list) {
        ArrayList<String> temp_key_list = new ArrayList<String>();
        ArrayList<String> temp_value_list = new ArrayList<String>();
        for (int index : num_list) {
            temp_key_list.add(key_list.get(index - 1));
            String value = val_list.get(index - 1);
            if (value.isEmpty()) {
                value = "\" \"";
            }
            temp_value_list.add(value);
        }
        return Concat(temp_key_list) + NEW_LINE + Concat(temp_value_list);
    }

    public Profile(ArrayList<String> infoarray) {
        this.profile = infoarray;
    }

    public void exportDSV() throws IOException {
        Writer out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(TEXT_PATH + DSV_NAME), "UTF8"));
        String content = getSpecifiedField(SV_LIST, ATTRNAMELIST, this.profile);
        out.write(content);
        out.close();
    }

    public void exportHK() throws IOException {
        Writer out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(TEXT_PATH + HK_NAME), "UTF8"));
        String content = getSpecifiedField(HK_LIST, ATTRNAMELIST, this.profile);
        out.write(content);
        out.close();
    }

    public void exportLL() throws IOException {
        Writer out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(TEXT_PATH + LL_NAME), "UTF8"));
        String content = getSpecifiedField(LL_LIST, ATTRNAMELIST, this.profile);
        out.write(content);
        out.close();
    }

    public void exportHKOCEO() throws IOException {
        Writer out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(TEXT_PATH + HKOCEO_NAME), "UTF8"));
        String content = getSpecifiedField(HKOCEO_LIST, ATTRNAMELIST, this.profile);
        out.write(content);
        out.close();
    }

    public void exportLL1() throws IOException {
        Writer out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(TEXT_PATH + LL1_NAME), "UTF8"));
        String content = getSpecifiedField(LL1_LIST, ATTRNAMELIST, this.profile);
        out.write(content);
        out.close();
    }

}
