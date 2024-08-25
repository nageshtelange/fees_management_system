/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.fees_management_system;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class UpdateFeesDetails extends javax.swing.JFrame {

    /**
     * Creates new form AddFees
     */
    public void displayCashFirst()
    {
        lbl_dd_no.setVisible(false);
        txt_dd_no.setVisible(false);
        lbl_cheque_no.setVisible(false);
        txt_cheque_no.setVisible(false);
        lbl_rec_name.setVisible(true);
        txt_rec_name.setVisible(true);
    }
    
   public String updateData() {
    String status = "";
    try {
        int receiptno = Integer.parseInt(txt_receipt_no.getText());
        String sname = txt_rec_name.getText();
        String rollno = txt_roll_no.getText();
        String paymentmode = combo_mode_of_pay.getSelectedItem().toString();
        String chequeno = txt_cheque_no.getText();
        String bankname = txt_bank_name1.getText();
        String ddno = txt_dd_no.getText();
        String coursename = combo_mode_payment1.getSelectedItem().toString();
        String gstn = lblGSTN.getText();
        float total = Float.parseFloat(txt_total.getText());
        SimpleDateFormat sd1 = new SimpleDateFormat("yyyy-MM-dd");
        String date = sd1.format(date_c.getDate());
        float amount = Float.parseFloat(txt_amount1.getText());

        String twords = txt_total_in_words.getText();
        String remark = txt_remark.getText();
        int year1 = Integer.parseInt(txtFromYear.getText());
        int year2 = Integer.parseInt(txtToYear.getText());

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/fees_mgmsdb?zeroDateTimeBehavior=CONVERT_TO_NULL";
        try (Connection con = DriverManager.getConnection(url, "root", "JoeBiden@1752")) {
            String sql = "update fees_details set student_name=?, roll_no=?, payment_mode=?, cheque_no=?, bank_name=?, "
            + "dd_no=?, course_name=?, gstin=?, total_amount=?, date=?, amount=?, total_in_words=?, remark=?, year1=?, year2=? "
            + "where reciept_no=?";
            try (PreparedStatement st = con.prepareStatement(sql)) {
                
                st.setString(1, sname);
                st.setString(2, rollno);
                st.setString(3, paymentmode);
                st.setString(4, chequeno);
                st.setString(5, bankname);
                st.setString(6, ddno);
                st.setString(7, coursename);
                st.setString(8, gstn);
                st.setFloat(9, total);
                st.setString(10, date);
                st.setFloat(11, amount);
               
                st.setString(12, twords);
                st.setString(13, remark);
                st.setInt(14, year1);
                st.setInt(15, year2);
                st.setInt(16, receiptno);

                int c = st.executeUpdate();
                if (c == 1) {
                    status = "success";
                } else {
                    status = "failed";
                }
            }
        }
    } catch (NumberFormatException e) {
        // Handle the case where the amount is not a valid float
        e.printStackTrace();
        status = "Invalid amount format";
    } catch (Exception e1) {
        e1.printStackTrace();
        status = "Failed due to exception: " + e1.getMessage();
    }
    return status;
}
    
    public UpdateFeesDetails() {
        initComponents();
        displayCashFirst();
        FillComboBox();
        int r=GetRecieptNumber();
        txt_receipt_no.setText(Integer.toString(r));
        setRecords();
    }
    


public class NumberToWordsConverter {

	public static final String[] units = { "", "One", "Two", "Three", "Four",
			"Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve",
			"Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
			"Eighteen", "Nineteen" };

	public static final String[] tens = { 
			"", 		// 0
			"",		// 1
			"Twenty", 	// 2
			"Thirty", 	// 3
			"Forty", 	// 4
			"Fifty", 	// 5
			"Sixty", 	// 6
			"Seventy",	// 7
			"Eighty", 	// 8
			"Ninety" 	// 9
	};

	public static String convert(final int n) {
		if (n < 0) 
                {
			return "Minus " + convert(-n);
		}

		if (n < 20) 
                {
			return units[n];
		}

		if (n < 100) {
			return tens[n / 10] + ((n % 10 != 0) ? " " : "") + units[n % 10];
		}

		if (n < 1000) {
			return units[n / 100] + " Hundred" + ((n % 100 != 0) ? " " : "") + convert(n % 100);
		}

		if (n < 100000) {
			return convert(n / 1000) + " Thousand" + ((n % 10000 != 0) ? " " : "") + convert(n % 1000);
		}

		if (n < 10000000) {
			return convert(n / 100000) + " Lakh" + ((n % 100000 != 0) ? " " : "") + convert(n % 100000);
		}

		return convert(n / 10000000) + " Crore" + ((n % 10000000 != 0) ? " " : "") + convert(n % 10000000);
	}

	public static void main(final String[] args) {
            Scanner sc=new Scanner(System.in);
            System.out.println("Enter the Amount : ");
		int n=sc.nextInt();

		
		System.out.println( convert(n)+ " Only");
	
	}
}
           boolean validation()
    {
        if(txt_rec_name.getText().equals(""))
       {
          JOptionPane.showMessageDialog(this,"Please enter receiver name" +" First");
          return false;
       }
        
         if(date_c.getDate()==null)
       {
          JOptionPane.showMessageDialog(this,"Please enter date");
          return false;
       }
         
         if(txt_roll_no.getText().equals("") || txt_roll_no.getText().matches("[0-9]+")==false)
       {
          JOptionPane.showMessageDialog(this,"Please enter roll no (in Number)" );
          return false;
       }
         if(combo_mode_of_pay.getSelectedItem().toString().equalsIgnoreCase("cheque"))
         {
            if(txt_cheque_no.getText().equals(""))
           {
              JOptionPane.showMessageDialog(this,"Enter chqoue No.");
              return false;
           }
            if(txt_bank_name1.getText().equals(""))
            {
              JOptionPane.showMessageDialog(this,"Please enter bank number");
              return false;
            }
          
         }
         
         if(combo_mode_of_pay.getSelectedItem().toString().equalsIgnoreCase("DD"))
         {
            if(txt_dd_no.getText().equals(""))
           {
              JOptionPane.showMessageDialog(this,"Enter DD No.");
              return false;
           }
            if(txt_bank_name1.getText().equals(""))
            {
              JOptionPane.showMessageDialog(this,"Please enter bank number");
              return false;
            }
          
         }
        
        return true;
    }
           
          public void FillComboBox() {
    try {
         Class.forName("com.mysql.cj.jdbc.Driver");
          String url = "jdbc:mysql://localhost:3306/fees_mgmsdb?zeroDateTimeBehavior=CONVERT_TO_NULL";
           Connection con = DriverManager.getConnection(url, "root", "JoeBiden@1752");
           String sql="select cname from course";
           PreparedStatement st=con.prepareStatement(sql);
           ResultSet rs=st.executeQuery(sql);
           while(rs.next())
           {
              combo_mode_payment1.addItem(rs.getString("cname"));
           }
          
    }
    catch(Exception e)
    {
        e.getStackTrace();
    }
          }
          
          public int GetRecieptNumber()
          {
              int rno=0;
              try
              {
                  Class.forName("com.mysql.cj.jdbc.Driver");
                  String url = "jdbc:mysql://localhost:3306/fees_mgmsdb?zeroDateTimeBehavior=CONVERT_TO_NULL";
                  Connection con = DriverManager.getConnection(url, "root", "JoeBiden@1752");
                  
                  PreparedStatement st=con.prepareStatement("select max(reciept_no)from fees_details");
                  ResultSet rs=st.executeQuery();
                  if(rs.next()==true)
                  {
                     rno=rs.getInt(1);
                  }
              }
              catch(Exception e)
              {
                  e.getStackTrace();
              }
              return rno+1;
          }
          
          
          public void setRecords()
          {
              try
              {
                  Class.forName("com.mysql.cj.jdbc.Driver");
                  String url = "jdbc:mysql://localhost:3306/fees_mgmsdb?zeroDateTimeBehavior=CONVERT_TO_NULL";
                  Connection con = DriverManager.getConnection(url, "root", "JoeBiden@1752");
                  PreparedStatement st=con.prepareStatement("SELECT * FROM fees_details ORDER BY reciept_no DESC LIMIT 1");
                  ResultSet rs = st.executeQuery();
                  rs.next();
                  txt_receipt_no.setText(rs.getString("reciept_no"));
                  combo_mode_of_pay.setSelectedItem(rs.getString("payment_mode"));
                  txt_cheque_no.setText(rs.getString("cheque_no"));
                  txt_dd_no.setText(rs.getString("dd_no"));
                  txt_bank_name1.setText(rs.getString("bank_name"));
                  txt_rec_name.setText(rs.getString("student_name"));
                  date_c.setDate(rs.getDate("date"));
                  combo_mode_payment1.setSelectedItem(rs.getString("course_name"));
                  txt_roll_no.setText(rs.getString("roll_no"));
                  txtFromYear.setText(rs.getString("year1"));
                  txtToYear.setText(rs.getString("year2"));
                  txt_amount1.setText(rs.getString("amount"));
                  txt_total.setText(rs.getString("total_amount"));
                  txt_total_in_words.setText(rs.getString("total_in_words"));
                  txt_remark.setText(rs.getString("remark"));
                  
                  
                    
              }
              catch(Exception e)
              {
                 e.printStackTrace();
              }
          }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelParent = new javax.swing.JPanel();
        lbl_cheque_no = new javax.swing.JLabel();
        lbl_mode_of_pay = new javax.swing.JLabel();
        lbl_dd_no = new javax.swing.JLabel();
        txt_receipt_no = new javax.swing.JTextField();
        lbl_gstn = new javax.swing.JLabel();
        txt_cheque_no = new javax.swing.JTextField();
        lbl_receipt_no = new javax.swing.JLabel();
        date_c = new com.toedter.calendar.JDateChooser();
        lbl_date = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtFromYear = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtToYear = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_total_in_words = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_remark = new javax.swing.JTextArea();
        jSeparator6 = new javax.swing.JSeparator();
        btn_print = new javax.swing.JButton();
        lbl_rec_name = new javax.swing.JLabel();
        txt_rec_name = new javax.swing.JTextField();
        txt_total = new javax.swing.JTextField();
        txt_roll_no = new javax.swing.JTextField();
        txt_amount1 = new javax.swing.JTextField();
        combo_mode_of_pay = new javax.swing.JComboBox<>();
        txt_dd_no = new javax.swing.JTextField();
        lbl_bank_name1 = new javax.swing.JLabel();
        txt_bank_name1 = new javax.swing.JTextField();
        combo_mode_payment1 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        lblGSTN = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelParent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_cheque_no.setText("cheque no");
        panelParent.add(lbl_cheque_no, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 100, 20));

        lbl_mode_of_pay.setText("Mode of Payment");
        panelParent.add(lbl_mode_of_pay, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 100, 20));

        lbl_dd_no.setText("DD");
        panelParent.add(lbl_dd_no, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 80, 20));
        panelParent.add(txt_receipt_no, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 90, -1));

        lbl_gstn.setText("GSTN");
        panelParent.add(lbl_gstn, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 70, 60, 20));
        panelParent.add(txt_cheque_no, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 90, -1));

        lbl_receipt_no.setText("Receipt No");
        panelParent.add(lbl_receipt_no, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 80, 20));
        panelParent.add(date_c, new org.netbeans.lib.awtextra.AbsoluteConstraints(408, 30, 130, 30));

        lbl_date.setText("Date");
        panelParent.add(lbl_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, 30, 20));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Received from  for the given month :");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 200, 20));

        txtFromYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFromYearActionPerformed(evt);
            }
        });
        jPanel1.add(txtFromYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 120, -1));

        jLabel6.setText("to");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, 20, 20));

        txtToYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtToYearActionPerformed(evt);
            }
        });
        jPanel1.add(txtToYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 40, 120, -1));

        jLabel11.setText("Amount");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 110, 50, -1));

        jLabel9.setText("Roll No");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 50, 20));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, -1, -1));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, -1, -1));

        jSeparator3.setBackground(new java.awt.Color(255, 0, 0));
        jSeparator3.setForeground(new java.awt.Color(255, 51, 51));
        jSeparator3.setPreferredSize(new java.awt.Dimension(200, 10));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 350, 170, 10));

        jSeparator4.setBackground(new java.awt.Color(255, 0, 0));
        jSeparator4.setForeground(new java.awt.Color(255, 51, 51));
        jSeparator4.setPreferredSize(new java.awt.Dimension(200, 10));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 560, 10));

        jLabel13.setText("Sr no");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 106, -1, 20));

        jLabel14.setText("Remark");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 70, 20));
        jPanel1.add(txt_total_in_words, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 270, 240, -1));

        jLabel15.setText("Head");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, 50, -1));

        jSeparator5.setBackground(new java.awt.Color(255, 0, 0));
        jSeparator5.setForeground(new java.awt.Color(255, 51, 51));
        jSeparator5.setPreferredSize(new java.awt.Dimension(200, 10));
        jPanel1.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 560, 10));

        jLabel18.setText("Receiver Signature");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 290, 100, 20));
        jPanel1.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 240, -1));

        jLabel19.setText("Total in Words");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 90, 20));

        jLabel21.setText("TOTAL");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 260, 40, 20));

        txt_remark.setColumns(20);
        txt_remark.setRows(5);
        jScrollPane2.setViewportView(txt_remark);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 320, 240, -1));

        jSeparator6.setBackground(new java.awt.Color(255, 0, 0));
        jSeparator6.setForeground(new java.awt.Color(255, 51, 51));
        jSeparator6.setPreferredSize(new java.awt.Dimension(200, 10));
        jPanel1.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 260, 170, 0));

        btn_print.setBackground(new java.awt.Color(204, 204, 255));
        btn_print.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_print.setText("Print");
        btn_print.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(204, 204, 255), null, null));
        btn_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_printActionPerformed(evt);
            }
        });
        jPanel1.add(btn_print, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 400, 50, -1));

        lbl_rec_name.setText("Receiver Name");
        jPanel1.add(lbl_rec_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 100, 20));
        jPanel1.add(txt_rec_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 200, -1));
        jPanel1.add(txt_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 260, 110, -1));

        txt_roll_no.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_roll_noActionPerformed(evt);
            }
        });
        jPanel1.add(txt_roll_no, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, 110, -1));

        txt_amount1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_amount1ActionPerformed(evt);
            }
        });
        jPanel1.add(txt_amount1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 160, 110, -1));

        panelParent.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 550, 460));

        combo_mode_of_pay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "Cash", "cheque" }));
        combo_mode_of_pay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_mode_of_payActionPerformed(evt);
            }
        });
        panelParent.add(combo_mode_of_pay, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 90, -1));
        panelParent.add(txt_dd_no, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 90, -1));

        lbl_bank_name1.setText("Bank name");
        panelParent.add(lbl_bank_name1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 80, 20));
        panelParent.add(txt_bank_name1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, 200, -1));

        combo_mode_payment1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_mode_payment1ActionPerformed(evt);
            }
        });
        panelParent.add(combo_mode_payment1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 100, 110, -1));

        jLabel12.setText("Course");
        panelParent.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 100, -1, -1));

        lblGSTN.setText("ABCDE1234F");
        panelParent.add(lblGSTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 70, 90, 20));

        getContentPane().add(panelParent, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 560, 650));

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(255, 153, 153));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("HOME");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 255, 255), java.awt.Color.white, null, null));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 180, -1));

        jButton2.setBackground(new java.awt.Color(255, 153, 153));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("EDIT RECORD");
        jButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 255, 255), java.awt.Color.white, null, null));
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 165, 182, -1));

        jButton3.setBackground(new java.awt.Color(255, 153, 153));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("SEARCH RECORD");
        jButton3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 255, 255), java.awt.Color.white, null, null));
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 105, 182, -1));

        jButton4.setBackground(new java.awt.Color(255, 153, 153));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Print");
        jButton4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 255, 255), java.awt.Color.white, null, null));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 182, -1));

        jButton6.setBackground(new java.awt.Color(255, 153, 153));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("BACK HOME");
        jButton6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 255, 255), java.awt.Color.white, null, null));
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 182, -1));

        jButton7.setBackground(new java.awt.Color(255, 153, 153));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("LOGOUT");
        jButton7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 255, 255), java.awt.Color.white, null, null));
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton7MouseClicked(evt);
            }
        });
        jPanel3.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 182, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 650));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void combo_mode_of_payActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_mode_of_payActionPerformed
        // TODO add your handling code here:
        if(combo_mode_of_pay.getSelectedIndex()==0){
            lbl_dd_no.setVisible(true);
            txt_dd_no.setVisible(true);
            lbl_rec_name.setVisible(true);
            txt_rec_name.setVisible(true);
            lbl_bank_name1.setVisible(true);
            txt_bank_name1.setVisible(true);
            
            lbl_cheque_no.setVisible(false);
            txt_cheque_no.setVisible(false);
            
        }
        if(combo_mode_of_pay.getSelectedIndex()==1){
        lbl_dd_no.setVisible(false);
        txt_dd_no.setVisible(false);
        lbl_cheque_no.setVisible(false);
        txt_cheque_no.setVisible(false);
        lbl_rec_name.setVisible(true);
        txt_rec_name.setVisible(true); 
        lbl_bank_name1.setVisible(false);
        txt_bank_name1.setVisible(false);
        }
        
         if(combo_mode_of_pay.getSelectedIndex()==2){
        lbl_cheque_no.setVisible(true);
        txt_cheque_no.setVisible(true);
        lbl_dd_no.setVisible(false);
        txt_dd_no.setVisible(false);
        lbl_rec_name.setVisible(true);
        txt_rec_name.setVisible(true); 
        lbl_bank_name1.setVisible(true);
        txt_bank_name1.setVisible(true);
        }
    }//GEN-LAST:event_combo_mode_of_payActionPerformed

    private void btn_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_printActionPerformed
        // TODO add your handling code here:
        
        if(validation() == true)
        {
           String s=updateData();
           if(s.equals("success"))
           {
               JOptionPane.showMessageDialog(this,"Record updated successfully"); 
               PrintRec p = new PrintRec();
               p.setVisible(true);
               this.dispose();
           }
           else
               
                   {
                       JOptionPane.showMessageDialog(this,"Record udation failed");
                   }
        }
    }//GEN-LAST:event_btn_printActionPerformed

    private void txtToYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtToYearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtToYearActionPerformed

    private void txtFromYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFromYearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFromYearActionPerformed

    private void txt_roll_noActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_roll_noActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_roll_noActionPerformed

    private void txt_amount1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_amount1ActionPerformed

        
    }//GEN-LAST:event_txt_amount1ActionPerformed

    private void combo_mode_payment1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_mode_payment1ActionPerformed
        // TODO add your handling code here:
        String s1=combo_mode_payment1.getSelectedItem().toString();
        jTextField11.setText(s1);
    }//GEN-LAST:event_combo_mode_payment1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        HomePage home = new HomePage();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
        // TODO add your handling code here:
        HomePage backhome = new HomePage();
        backhome.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton6MouseClicked

    private void jButton7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseClicked
        Login logout = new Login();
        logout.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton7MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        SearchRecords searchrecord = new SearchRecords();
        searchrecord.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        EditCourse editrecord = new EditCourse();
        editrecord.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_jButton2MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UpdateFeesDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateFeesDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateFeesDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateFeesDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdateFeesDetails().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_print;
    private javax.swing.JComboBox<String> combo_mode_of_pay;
    private javax.swing.JComboBox<String> combo_mode_payment1;
    private com.toedter.calendar.JDateChooser date_c;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JLabel lblGSTN;
    private javax.swing.JLabel lbl_bank_name1;
    private javax.swing.JLabel lbl_cheque_no;
    private javax.swing.JLabel lbl_date;
    private javax.swing.JLabel lbl_dd_no;
    private javax.swing.JLabel lbl_gstn;
    private javax.swing.JLabel lbl_mode_of_pay;
    private javax.swing.JLabel lbl_rec_name;
    private javax.swing.JLabel lbl_receipt_no;
    private javax.swing.JPanel panelParent;
    private javax.swing.JTextField txtFromYear;
    private javax.swing.JTextField txtToYear;
    private javax.swing.JTextField txt_amount1;
    private javax.swing.JTextField txt_bank_name1;
    private javax.swing.JTextField txt_cheque_no;
    private javax.swing.JTextField txt_dd_no;
    private javax.swing.JTextField txt_rec_name;
    private javax.swing.JTextField txt_receipt_no;
    private javax.swing.JTextArea txt_remark;
    private javax.swing.JTextField txt_roll_no;
    private javax.swing.JTextField txt_total;
    private javax.swing.JTextField txt_total_in_words;
    // End of variables declaration//GEN-END:variables
}
