package com.tindpt;

import javax.swing.JTextField;

public class MappingField {
	public JTextField textfield;
	public int index;
	public String defaultText;
	public MappingField(int i,JTextField t,String text) {
		index=i;
		textfield=t;
		defaultText=text;
	}
	public MappingField(int i,JTextField t) {
		index=i;
		textfield=t;
		defaultText="";
	}
}
