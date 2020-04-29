package com.tut.WebScrapYoutube;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Hello world!
 *
 */
public class App 
{
	
	public static String getData(String c) throws Exception {
		
		StringBuffer br= new StringBuffer();
		br.append("<html>" +
				"<body style='text-align:center;color:red'>");
		br.append(c.toUpperCase()+"<br>");
		System.out.println("you are about to get data.....");
		String url="https://www.worldometers.info/coronavirus/country/"+c+"/";
		Document doc= Jsoup.connect(url).get();
		//#maincounter-wrap
		Elements element=doc.select("#maincounter-wrap");
		
		element.forEach((e)->{
			String text= e.select("h1").text();
			String count= e.select(".maincounter-number>span").text();
			
			br.append(text).append(count).append("<br>");
			
		});
		br.append("</body>"+
				"</html>");
		return br.toString();
		
	}
	
    public static void main( String[] args ) throws Exception
    {
		/*
		 * System.out.println( "Hello World!" ); Scanner sc= new Scanner(System.in);
		 * System.out.println("Enter Country Name:"); String co= sc.nextLine();
		 * System.out.println(getData(co));
		 */
    	
    	
    	//creating GUI
    	JFrame root= new JFrame("Details of country");
    	root.setSize(500, 500);
    	
    	Font f= new Font("Arial", Font.BOLD, 32);
    	
    	//textfield
    	
    	JTextField field= new JTextField();
    	
    	//label
    	JLabel dataL= new JLabel();
    	
    	field.setFont(f);
    	dataL.setFont(f);
    	field.setHorizontalAlignment(SwingConstants.CENTER);
    	dataL.setHorizontalAlignment(SwingConstants.CENTER);
    	//button
    	JButton button= new JButton("GET");
    	
    	button.addActionListener((event)->{
    		//on click
    		try {
    			String maindata= field.getText();
        		String result= getData(maindata);
        		dataL.setText(result);
        		
			} catch (Exception e) {
				e.printStackTrace();
			}
    	});
    	button.setFont(f);
    	
    	root.setLayout(new BorderLayout());
    	root.add(field, BorderLayout.NORTH);
    	root.add(dataL, BorderLayout.CENTER);
    	root.add(button, BorderLayout.SOUTH);
    	
    	root.setVisible(true);
    }
}
