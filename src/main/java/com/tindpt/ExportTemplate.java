package com.tindpt;



import javax.swing.JOptionPane;
import java.util.*; 
import java.io.Writer;
import java.nio.file.Files;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
public class ExportTemplate {
	public static void export(Profile profile) throws Exception{
		
		// -----------------------hanh kiem---------------------------------
		profile.exportHK();
		// -----------------------lylich---------------------------------
		profile.exportLL();
		// -----------------------lylich1---------------------------------
		profile.exportLL1();
		// -----------------------xinviec---------------------------------
		profile.exportDSV();
		
		JOptionPane.showMessageDialog(null, "Hoan Thanh", "InfoBox: " + "Thong Bao", JOptionPane.INFORMATION_MESSAGE);
		
	}
}
//a.add(thangki)
//				temp.add(textField_7.getText()); //thangki
//bo xung 35 vao lylich1,donxv,hanhkiem