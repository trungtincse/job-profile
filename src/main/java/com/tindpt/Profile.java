package com.tindpt;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class Profile {

    private static final String NEW_LINE = "\r\n";
    private static final String TEXT_PATH = "d:\\";
    private static final String DSV_NAME = "xinviec.txt";
    private static final String HK_NAME = "hanhkiem.txt";
    private static final String LL_NAME = "lylich.txt";
    private static final String HKOCEO_NAME = "hanhkiemoceo.txt";
    private static final String LL1_NAME = "lylich1.txt";

    private static final Field[] HK_LIST = {
        Field.HVT, Field.SINHNAM, Field.CMND, Field.NGAYCAPFULL,
        Field.CAPTAI, Field.AP, Field.XA, Field.HUYEN, Field.TINH,
        Field.NGAYKI, Field.THANGKI, Field.NAMKI
    };
    private static final Field[] SV_LIST = {
        Field.CAPTAI, Field.CMND, Field.HVT, Field.NAMCAP, Field.NGAYCAP,
        Field.NOISINH, Field.SINHNAM, Field.SINHNGAY, Field.SINHTHANG,
        Field.THANGCAP, Field.THUONGTRU, Field.TRINHDO, Field.XA,
        Field.NGAYKI, Field.THANGKI, Field.NAMKI
    };
    private static final Field[] LL_LIST = {
        Field.TONGIAO, Field.DANTOC, Field.NGHEME, Field.NGHEAC,
        Field.AC1, Field.AC2, Field.AC3, Field.TUOIME, Field.TENCHA,
        Field.HIENOCHA, Field.TENME, Field.HIENOAC, Field.HIENOME,
        Field.THUONGTRU, Field.CAPTAI, Field.NGAYCAPFULL, Field.CMND,
        Field.NOISINH, Field.SINHNGAY, Field.NGHECHA, Field.TUOICHA,
        Field.SINHTHANG, Field.SINHNAM, Field.HVT, Field.TRINHDO
    };
    private static final Field[] HKOCEO_LIST = {
        Field.TONGIAO, Field.DANTOC, Field.NGHEME, Field.NGHEAC,
        Field.AC1, Field.AC2, Field.AC3, Field.TUOIME, Field.TENCHA,
        Field.HIENOCHA, Field.TENME, Field.HIENOAC, Field.HIENOME,
        Field.THUONGTRU, Field.CAPTAI, Field.NGAYCAPFULL, Field.CMND,
        Field.NOISINH, Field.SINHNGAY, Field.NGHECHA, Field.TUOICHA,
        Field.SINHTHANG, Field.SINHNAM, Field.HVT, Field.TRINHDO,
        Field.NGAYKI, Field.THANGKI, Field.NAMKI
    };
    private static final Field[] LL1_LIST = {
        Field.HVT, Field.NGAYKI, Field.XA, Field.THANGKI, Field.NAMKI
    };

    public EnumMap<Field, String> profile;

    public Profile(EnumMap<Field, String> data) {
        this.profile = data;
    }

    public String getSpecifiedField(Field[] fields) {
        List<String> keys = new ArrayList<>();
        List<String> values = new ArrayList<>();
        for (Field field : fields) {
            keys.add(field.name().toLowerCase());
            String value = profile.getOrDefault(field, "");
            if (value.isEmpty()) {
                value = "\" \"";
            }
            values.add(value);
        }
        return String.join(",", keys) + NEW_LINE + String.join(",", values);
    }

    public void store2DB() throws SQLException {
        String key = profile.getOrDefault(Field.HVT, "") + "," + profile.getOrDefault(Field.SINHNGAY, "");
        List<String> allValues = new ArrayList<>();
        for (Field field : Field.values()) {
            allValues.add(profile.getOrDefault(field, ""));
        }
        DatabaseController.insertWithStatement(key, String.join(",", allValues));
    }

    private void exportTo(String filename, Field[] fields) throws IOException {
        Writer out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(TEXT_PATH + filename), "UTF8"));
        out.write(getSpecifiedField(fields));
        out.close();
    }

    public void exportDSV() throws IOException { exportTo(DSV_NAME, SV_LIST); }
    public void exportHK() throws IOException { exportTo(HK_NAME, HK_LIST); }
    public void exportLL() throws IOException { exportTo(LL_NAME, LL_LIST); }
    public void exportHKOCEO() throws IOException { exportTo(HKOCEO_NAME, HKOCEO_LIST); }
    public void exportLL1() throws IOException { exportTo(LL1_NAME, LL1_LIST); }
}
