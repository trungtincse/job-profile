package com.tindpt;

import java.awt.EventQueue;
import java.awt.*;
import java.awt.datatransfer.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import org.apache.commons.lang3.StringEscapeUtils;

public class App extends JFrame {
	Map<String, MappingField> data_dict = new HashMap<String, MappingField>();
	Date d = new Date();
	Calendar calendar = Calendar.getInstance();
	int dayint = calendar.get(Calendar.DATE);
	String day = (dayint < 10) ? "0" + String.valueOf(dayint) : String.valueOf(dayint);
	int monthint = calendar.get(Calendar.MONTH) + 1;
	String month = (monthint < 10) ? "0" + String.valueOf(monthint) : String.valueOf(monthint);
	private static MyInterator it = null;
	private String old = "";
	private String old1 = "";
	private String ap = "";
	private String xa = "";
	private String huyen = "";
	private String tinh = "";
	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField;
	private JLabel lblSinhNgy;
	private JLabel lblThang;
	private JTextField textField_2;
	private JLabel lblNm;
	private JTextField textField_3;
	private JLabel lblCmnd;
	private JTextField textField_4;
	private JLabel lblNguynQun;
	private JTextField textField_5;
	private JLabel lblThngTr;
	private JTextField textField_6;
	private JLabel lblDnTc;
	private JTextField txtKinh;
	private JLabel lblTnGio;
	private JTextField txtPha;
	private JLabel lblNgy;
	private JTextField textField_9;
	private JLabel label_2;
	private JTextField textField_10;
	private JLabel label_3;
	private JTextField textField_11;
	private JLabel lblCpNgy;
	private JLabel lblTi;
	private JTextField txtAnGiang_1;
	private JLabel lblTrnhHc;
	private JTextField textField_13;
	private JLabel lblHVTn;
	private JTextField textField_14;
	private JLabel lblTui;
	private JTextField textField_15;
	private JLabel lblNgh;
	private JTextField txtLmRung;
	private JLabel lblNiHin;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField txtNiTr;
	private JTextField textField_20;
	private JLabel label_1;
	private JTextField textField_21;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel lblHVTn_1;
	private JLabel lblHVTn_2;
	private JTextField textField_22;
	private JLabel lblHVTn_3;
	private JTextField textField_23;
	private JLabel lblHVTn_4;
	private JTextField textField_24;
	private JLabel lblNghchoTt;
	private JTextField txtLmThu;
	private JLabel lblchoTt;
	private JTextField textField_26;
	private JButton btnXong;
	private JLabel lblNgyK;
	private JTextField textField_27;
	private JLabel lblThng;
	private JLabel lblNm_1;
	private JTextField textField_28;
	private JTextField txtAnGiang;
	private JTextField textField_7;
	JButton btnNgiSau;
	JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
					frame.setVisible(true);
					it = DatabaseController.selectWithStatement(-1, 20);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public App() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 726, 635);
		contentPane = new JPanel();

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		textField_1 = new JTextField();
		textField_1.setBounds(169, 46, 387, 20);
		textField_1.setColumns(10);

		JLabel label = new JLabel("H\u1ECD  v\u00E0 t\u00EAn:");
		label.setBounds(15, 52, 109, 14);

		textField = new JTextField();
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				new Thread(() -> {
					String key = data_dict.get("hvt").textfield.getText().trim() + ","
							+ data_dict.get("sinhngay").textfield.getText().trim();
					try {
						MyInterator myInterator = DatabaseController.findWithStatement(key);
						String value = null;
						if ((value = myInterator.getFirst()) != null) {
							String[] data = value.split(",");
							for (Map.Entry<String, MappingField> entry : data_dict.entrySet()) {
								MappingField field = entry.getValue();
								field.textfield.setText(data[field.index - 1]); // sai lam cho index phai la STT
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}).start();
			}
		});

		textField.setBounds(169, 72, 40, 20);
		textField.setColumns(10);

		lblSinhNgy = new JLabel("Sinh ng\u00E0y");
		lblSinhNgy.setBounds(15, 78, 109, 14);

		lblThang = new JLabel("Th\u00E1ng");
		lblThang.setBounds(221, 74, 55, 15);

		textField_2 = new JTextField();
		textField_2.setBounds(270, 72, 40, 20);
		textField_2.setColumns(10);
		contentPane.setLayout(null);
		contentPane.add(textField);
		contentPane.add(lblSinhNgy);
		contentPane.add(lblThang);
		contentPane.add(textField_2);
		contentPane.add(label);
		contentPane.add(textField_1);
		lblNm = new JLabel("N\u0103m");
		lblNm.setBounds(320, 75, 31, 14);
		contentPane.add(lblNm);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(363, 72, 69, 20);
		contentPane.add(textField_3);

		lblCmnd = new JLabel("CCCD:");
		lblCmnd.setBounds(440, 74, 69, 14);
		contentPane.add(lblCmnd);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(497, 72, 155, 20);
		contentPane.add(textField_4);

		lblNguynQun = new JLabel("Nguy\u00EAn qu\u00E1n:");
		lblNguynQun.setBounds(15, 108, 136, 14);
		contentPane.add(lblNguynQun);

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(169, 106, 483, 20);
		contentPane.add(textField_5);

		lblThngTr = new JLabel("Th\u01B0\u1EDDng tr\u00FA:");
		lblThngTr.setBounds(15, 135, 92, 14);
		contentPane.add(lblThngTr);

		textField_6 = new JTextField();
//		textField_6.addCaretListener(new CaretListener() {
//			public void caretUpdate(CaretEvent e) {
//				ap=textField_6.getText();
//				textField_17.setText(ap);
//			}
//		});
		textField_6.setColumns(10);
		textField_6.setBounds(169, 132, 188, 20);
		contentPane.add(textField_6);

		lblDnTc = new JLabel("D\u00E2n T\u1ED9c:");
		lblDnTc.setBounds(15, 162, 72, 14);
		contentPane.add(lblDnTc);

		txtKinh = new JTextField();
		txtKinh.setText("Kinh");
		txtKinh.setColumns(10);
		txtKinh.setBounds(169, 159, 150, 20);
		contentPane.add(txtKinh);

		lblTnGio = new JLabel("T\u00F4n Gi\u00E1o:");
		lblTnGio.setBounds(395, 162, 80, 14);
		contentPane.add(lblTnGio);

		txtPha = new JTextField();
		txtPha.setText("Ph\u1EADt");
		txtPha.setColumns(10);
		txtPha.setBounds(502, 159, 150, 20);
		contentPane.add(txtPha);

		lblNgy = new JLabel("ng\u00E0y");
		lblNgy.setBounds(58, 203, 49, 14);
		contentPane.add(lblNgy);

		textField_9 = new JTextField();
//		textField_9.addFocusListener(new FocusAdapter() {
//			@Override
//			public void focusGained(FocusEvent e) {
//				JTextField[] fields= {textField_6,textField_28,txtThoiSn,txtAnGiang};
//				ArrayList<String> string_list=new ArrayList<String>();
//				for (JTextField jTextField : fields) {
//					if(!jTextField.getText().isEmpty())
//					string_list.add(jTextField.getText());
//				}
//				String merged=String.join(" - ", string_list);
//				textField_17.setText(merged);
//				textField_18.setText(merged);
//				textField_26.setText(merged);
//			}
//		});
		textField_9.setColumns(10);
		textField_9.setBounds(97, 200, 40, 20);
		contentPane.add(textField_9);

		label_2 = new JLabel("Th\u00E1ng");
		label_2.setBounds(140, 203, 49, 15);
		contentPane.add(label_2);

		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(188, 200, 40, 20);
		contentPane.add(textField_10);

		label_3 = new JLabel("N\u0103m");
		label_3.setBounds(238, 203, 38, 14);
		contentPane.add(label_3);

		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(271, 200, 69, 20);
		contentPane.add(textField_11);

		lblCpNgy = new JLabel("C\u1EA5p ng\u00E0y:");
		lblCpNgy.setBounds(10, 187, 114, 14);
		contentPane.add(lblCpNgy);

		lblTi = new JLabel("T\u1EA1i");
		lblTi.setBounds(353, 203, 21, 14);
		contentPane.add(lblTi);

		txtAnGiang_1 = new JTextField();
		txtAnGiang_1.setText("An Giang");

		txtAnGiang_1.setColumns(10);
		txtAnGiang_1.setBounds(386, 200, 89, 20);
		contentPane.add(txtAnGiang_1);

		lblTrnhHc = new JLabel("Tr\u00ECnh \u0111\u1ED9 h\u1ECDc v\u1EA5n");
		lblTrnhHc.setBounds(10, 234, 141, 27);
		contentPane.add(lblTrnhHc);

		textField_13 = new JTextField();
		textField_13.setColumns(10);
		textField_13.setBounds(140, 238, 69, 20);
		contentPane.add(textField_13);

		lblHVTn = new JLabel("H\u1ECD  v\u00E0 t\u00EAn ba:");
		lblHVTn.setBounds(15, 272, 109, 14);
		contentPane.add(lblHVTn);

		textField_14 = new JTextField();
		textField_14.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				JTextField[] fields = { textField_6, textField_28, txtThoiSn, txtAnGiang };
				ArrayList<String> string_list = new ArrayList<String>();
				for (JTextField jTextField : fields) {
					if (!jTextField.getText().isEmpty())
						string_list.add(jTextField.getText());
				}
				String merged = String.join(" - ", string_list);
				textField_17.setText(merged);
				textField_18.setText(merged);
				textField_26.setText(merged);
			}
		});
		textField_14.setColumns(10);
		textField_14.setBounds(140, 270, 349, 20);
		contentPane.add(textField_14);

		lblTui = new JLabel("Tu\u1ED5i :");
		lblTui.setBounds(15, 300, 55, 14);
		contentPane.add(lblTui);

		textField_15 = new JTextField();
		textField_15.setColumns(10);
		textField_15.setBounds(140, 295, 141, 20);
		contentPane.add(textField_15);

		lblNgh = new JLabel("Ngh\u1EC1:");
		lblNgh.setBounds(300, 297, 55, 14);
		contentPane.add(lblNgh);

		txtLmRung = new JTextField();
		txtLmRung.setText("L\u00E0m ru\u1ED9ng");
		txtLmRung.setColumns(10);
		txtLmRung.setBounds(347, 294, 143, 20);
		contentPane.add(txtLmRung);

		lblNiHin = new JLabel("N\u01A1i \u1EDF hi\u1EC7n t\u1EA1i:");
		lblNiHin.setBounds(15, 330, 109, 14);
		contentPane.add(lblNiHin);

		textField_17 = new JTextField();
		textField_17.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				textField_18.setText(textField_17.getText());
				textField_26.setText(textField_17.getText());
			}
		});

		textField_17.setColumns(10);
		textField_17.setBounds(141, 324, 349, 20);
		contentPane.add(textField_17);

		textField_18 = new JTextField();
		textField_18.setColumns(10);
		textField_18.setBounds(141, 409, 349, 20);
		contentPane.add(textField_18);

		txtNiTr = new JTextField();
		txtNiTr.setText("N\u1ED9i tr\u1EE3");
		txtNiTr.setColumns(10);
		txtNiTr.setBounds(347, 379, 143, 20);
		contentPane.add(txtNiTr);

		textField_20 = new JTextField();
		textField_20.setColumns(10);
		textField_20.setBounds(141, 351, 349, 20);
		contentPane.add(textField_20);

		label_1 = new JLabel("Ngh\u1EC1:");
		label_1.setBounds(300, 382, 55, 14);
		contentPane.add(label_1);

		textField_21 = new JTextField();
		textField_21.setColumns(10);
		textField_21.setBounds(140, 380, 141, 20);
		contentPane.add(textField_21);

		label_4 = new JLabel("N\u01A1i \u1EDF hi\u1EC7n t\u1EA1i:");
		label_4.setBounds(15, 415, 109, 14);
		contentPane.add(label_4);

		label_5 = new JLabel("Tu\u1ED5i :");
		label_5.setBounds(15, 385, 55, 14);
		contentPane.add(label_5);

		lblHVTn_1 = new JLabel("H\u1ECD  v\u00E0 t\u00EAn m\u1EB9:");
		lblHVTn_1.setBounds(15, 357, 109, 14);
		contentPane.add(lblHVTn_1);

		lblHVTn_2 = new JLabel("H\u1ECD  v\u00E0 t\u00EAn anh ch\u1ECB 1:");
		lblHVTn_2.setBounds(15, 446, 181, 14);
		contentPane.add(lblHVTn_2);

		textField_22 = new JTextField();
		textField_22.setColumns(10);
		textField_22.setBounds(189, 440, 166, 20);
		contentPane.add(textField_22);

		lblHVTn_3 = new JLabel("H\u1ECD  v\u00E0 t\u00EAn anh ch\u1ECB 2:");
		lblHVTn_3.setBounds(15, 471, 156, 14);
		contentPane.add(lblHVTn_3);

		textField_23 = new JTextField();
		textField_23.setColumns(10);
		textField_23.setBounds(189, 465, 166, 20);
		contentPane.add(textField_23);

		lblHVTn_4 = new JLabel("H\u1ECD  v\u00E0 t\u00EAn anh ch\u1ECB 3:");
		lblHVTn_4.setBounds(15, 502, 156, 14);
		contentPane.add(lblHVTn_4);

		textField_24 = new JTextField();
		textField_24.setColumns(10);
		textField_24.setBounds(189, 496, 166, 20);
		contentPane.add(textField_24);

		lblNghchoTt = new JLabel("Ngh\u1EC1 (cho t\u1EA5t c\u1EA3):");
		lblNghchoTt.setBounds(370, 446, 166, 14);
		contentPane.add(lblNghchoTt);

		txtLmThu = new JTextField();
		txtLmThu.setText("L\u00E0m thu\u00EA");
		txtLmThu.setColumns(10);
		txtLmThu.setBounds(535, 444, 130, 20);
		contentPane.add(txtLmThu);

		lblchoTt = new JLabel("Hi\u1EC7n \u1EDF:");
		lblchoTt.setBounds(368, 471, 120, 14);
		contentPane.add(lblchoTt);

		textField_26 = new JTextField();
		textField_26.setColumns(10);
		textField_26.setBounds(440, 469, 193, 20);
		contentPane.add(textField_26);

		btnXong = new JButton("Xong");
		btnXong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> temp = new ArrayList<String>();
				temp.add(textField_1.getText().trim());
				temp.add(textField.getText().trim());
				temp.add(textField_2.getText());
				temp.add(textField_3.getText());// sinh nam
				if (textField_2.getText().isEmpty() || textField.getText().isEmpty() || textField_3.getText().isEmpty())
					temp.add(textField_3.getText());
				else
					temp.add(textField.getText() + "/" + textField_2.getText() + "/" + textField_3.getText()); // full
																												// sinh
				temp.add(StringEscapeUtils.escapeCsv(textField_5.getText())); // noi sinh
				temp.add(textField_4.getText()); // cmnd
				temp.add(textField_9.getText()); // ngay cap
				temp.add(textField_10.getText());
				temp.add(textField_11.getText());
				temp.add(textField_9.getText() + "/" + textField_10.getText() + "/" + textField_11.getText()); // ngaycapfull
				temp.add(txtAnGiang_1.getText());// cap tai
				if (textField_6.getText().isEmpty())
					temp.add(textField_28.getText() + " - " + txtThoiSn.getText() + " - " + txtAnGiang.getText());// thuong
																													// tru
				else
					temp.add(textField_6.getText() + " - " + textField_28.getText() + " - " + txtThoiSn.getText()
							+ " - " + txtAnGiang.getText());// thuong tru
				temp.add(textField_6.getText());// ap
				temp.add(textField_28.getText());// xa
				temp.add(txtThoiSn.getText());// huyen
				temp.add(txtAnGiang.getText());// tinh
				temp.add(txtKinh.getText());// dantoc
				temp.add(txtPha.getText());// tongiao
				temp.add(textField_13.getText());// trinhdo
				temp.add(textField_14.getText());
				temp.add(textField_15.getText());
				temp.add(txtLmRung.getText());
				temp.add(textField_17.getText());// cha
				temp.add(textField_20.getText());
				temp.add(textField_21.getText());
				temp.add(txtNiTr.getText());
				temp.add(textField_18.getText());// me
				if (textField_22.getText().isEmpty())
					temp.add("");
				else
					temp.add(textField_22.getText());// ac
				if (textField_23.getText().isEmpty())
					temp.add("");
				else
					temp.add(textField_23.getText());// ac
				if (textField_24.getText().isEmpty())
					temp.add("");
				else
					temp.add(textField_24.getText());// ac
				temp.add(txtLmThu.getText());
				temp.add(textField_26.getText());// hienoac
				temp.add(textField_27.getText());// ki
				temp.add(textField_7.getText()); // thangki
				Calendar cal = Calendar.getInstance();
				int year = cal.get(Calendar.YEAR);
				temp.add(String.valueOf(year));
				// ---------------------------//
				try {
					Profile profile = new Profile(temp);
					ExportTemplate.export(profile);
					profile.store2DB();
					it = DatabaseController.selectWithStatement(-1, 20);
					it.current -= 1;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnXong.setBounds(247, 558, 89, 23);
		contentPane.add(btnXong);

		lblNgyK = new JLabel("Ng\u00E0y k\u00ED:");
		lblNgyK.setBounds(188, 532, 63, 14);
		contentPane.add(lblNgyK);

		textField_27 = new JTextField();
		textField_27.setText(day);
		textField_27.setColumns(10);
		textField_27.setBounds(269, 530, 40, 20);
		contentPane.add(textField_27);

		lblThng = new JLabel("Th\u00E1ng");
		lblThng.setBounds(324, 533, 50, 15);
		contentPane.add(lblThng);

		lblNm_1 = new JLabel("N\u0103m 20__");
		lblNm_1.setBounds(464, 532, 72, 14);
		contentPane.add(lblNm_1);

		textField_28 = new JTextField();
//		textField_28.addCaretListener(new CaretListener() {
//			public void caretUpdate(CaretEvent e) {
//				String temp="";
//				xa=textField_28.getText();
//				if(!ap.isEmpty()) temp=" - ";
//				old=ap+temp+xa;
//				textField_17.setText(old+" - "+huyen+" - "+tinh);
//			}
//		});
		textField_28.setColumns(10);
		textField_28.setBounds(363, 132, 146, 20);
		contentPane.add(textField_28);

		txtAnGiang = new JTextField();
		txtAnGiang.setText("An Giang");

		tinh = txtAnGiang.getText();
		txtAnGiang.setColumns(10);
		txtAnGiang.setBounds(517, 132, 135, 20);
		contentPane.add(txtAnGiang);

		textField_7 = new JTextField();
		textField_7.setText(month);
		textField_7.setColumns(10);
		textField_7.setBounds(392, 530, 40, 20);
		contentPane.add(textField_7);

		JButton btnLam = new JButton("T\u1EA1o m\u1EDBi");
		btnLam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Map.Entry<String, MappingField> entry : data_dict.entrySet()) {
					MappingField field = entry.getValue();
					field.textfield.setText(field.defaultText);
				}
				it.current = it.list.size();
				if (it.canNext())
					btnNgiSau.setVisible(true);
				else
					btnNgiSau.setVisible(false);
				if (it.canBack())
					button.setVisible(true);
				else
					button.setVisible(false);

			}
		});
		btnLam.setBounds(592, 11, 89, 23);
		contentPane.add(btnLam);

		button = new JButton("<< Ng\u01B0\u1EDDi tr\u01B0\u1EDBc");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String value = null;
				if ((value = it.previous()) != null) {
					String[] data = value.split(",");
					for (Map.Entry<String, MappingField> entry : data_dict.entrySet()) {
						MappingField field = entry.getValue();
						field.textfield.setText(data[field.index - 1]); // sai lam cho index phai la STT
					}
				}
				if (it.canNext())
					btnNgiSau.setVisible(true);
				else
					btnNgiSau.setVisible(false);
				if (it.canBack())
					button.setVisible(true);
				else
					button.setVisible(false);
			}
		});
		button.setBounds(18, 11, 143, 23);
		contentPane.add(button);

		btnNgiSau = new JButton("Ng\u01B0\u1EDDi sau >>");
		btnNgiSau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String value = null;
				if ((value = it.next()) != null) {
					String[] data = value.split(",");
					for (Map.Entry<String, MappingField> entry : data_dict.entrySet()) {
						MappingField field = entry.getValue();
						field.textfield.setText(data[field.index - 1]);
					}
				}
				if (it.canNext())
					btnNgiSau.setVisible(true);
				else
					btnNgiSau.setVisible(false);
				if (it.canBack())
					button.setVisible(true);
				else
					button.setVisible(false);
			}
		});
		btnNgiSau.setBounds(174, 11, 155, 23);
		btnNgiSau.setVisible(false);
		contentPane.add(btnNgiSau);

		data_dict.put("hvt", new MappingField(1, textField_1));
		data_dict.put("sinhngay", new MappingField(2, textField));
		data_dict.put("sinhthang", new MappingField(3, textField_2));
		data_dict.put("sinhnam", new MappingField(4, textField_3));
		// data_dict.put("fullsinh",new MappingField(5, textField));
		data_dict.put("noisinh", new MappingField(6, textField_5));
		data_dict.put("cmnd", new MappingField(7, textField_4));
		data_dict.put("ngaycap", new MappingField(8, textField_9));
		data_dict.put("thangcap", new MappingField(9, textField_10));
		data_dict.put("namcap", new MappingField(10, textField_11));
		// data_dict.put("ngaycapfull",new MappingField(11, textField));
		data_dict.put("captai", new MappingField(12, txtAnGiang_1, txtAnGiang_1.getText()));
		// data_dict.put("thuongtru",new MappingField(13, textField));
		data_dict.put("ap", new MappingField(14, textField_6));
		data_dict.put("xa", new MappingField(15, textField_28));
		data_dict.put("tinh", new MappingField(17, txtAnGiang, txtAnGiang.getText()));
		data_dict.put("dantoc", new MappingField(18, txtKinh, txtKinh.getText()));
		data_dict.put("tongiao", new MappingField(19, txtPha, txtPha.getText()));
		data_dict.put("trinhdo", new MappingField(20, textField_13));
		data_dict.put("tencha", new MappingField(21, textField_14));
		data_dict.put("tuoicha", new MappingField(22, textField_15));
		data_dict.put("nghecha", new MappingField(23, txtLmRung, txtLmRung.getText()));
		data_dict.put("hienocha", new MappingField(24, textField_17));
		data_dict.put("tenme", new MappingField(25, textField_20));
		data_dict.put("tuoime", new MappingField(26, textField_21));
		data_dict.put("ngheme", new MappingField(27, txtNiTr, txtNiTr.getText()));
		data_dict.put("hienome", new MappingField(28, textField_18));
		data_dict.put("ac1", new MappingField(29, textField_22));
		data_dict.put("ac2", new MappingField(30, textField_23));
		data_dict.put("ac3", new MappingField(31, textField_24));
		data_dict.put("ngheac", new MappingField(32, txtLmThu, txtLmThu.getText()));
		data_dict.put("hienoac", new MappingField(33, textField_26));
		data_dict.put("ngayki", new MappingField(34, textField_27, textField_27.getText()));
		data_dict.put("thangki", new MappingField(35, textField_7, textField_7.getText()));

		JButton btnNewButton = new JButton("Copy từ CCCD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Create a clipboard object
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

					// Get the content from the clipboard
					String clipboardText = (String) clipboard.getData(DataFlavor.stringFlavor);

					// Print the content
					String[] parts = clipboardText.split("\n");
					if (parts.length >= 7) {
						String cccd = parts[0].substring("Số CCCD:".length());
						String fullname = parts[2].substring("Họ và tên:".length());
						String fulldob = parts[4].substring("Ngày sinh:".length());
						String address = parts[5].substring("Nơi thường trú:".length());
						String cccdissuedDate = parts[6].substring("Ngày cấp CCCD:".length());
						textField_1.setText(fullname.toUpperCase());
						String[] fulldob_parts = fulldob.split("/");
						String[] address_parts = address.split(", ");
						String[] cccdissuedDate_parts = cccdissuedDate.split("/");
						textField.setText(fulldob_parts[0]);
						textField_2.setText(fulldob_parts[1]);
						textField_3.setText(fulldob_parts[2]);
						textField_6.setText(address_parts[0]);
						textField_28.setText(address_parts[1]);
						txtThoiSn.setText(address_parts[2]);
						txtAnGiang.setText(address_parts[3]);
						textField_9.setText(cccdissuedDate_parts[0]);
						textField_10.setText(cccdissuedDate_parts[1]);
						textField_11.setText(cccdissuedDate_parts[2]);
						textField_4.setText(cccd);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(426, 9, 155, 25);
		contentPane.add(btnNewButton);
	}
}
