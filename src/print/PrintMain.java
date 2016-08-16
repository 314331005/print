package print;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PrintMain extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static Properties props = new Properties(); 
	private static final Log log = LogFactory.getLog(DBToolkit.class); 
	
	final static String URL = "http://10.0.0.14:8080/WebSite/wish/update";
	static JFrame frame;
	static  Panel p2;
	
	static int FONG_SIZE = 20;
	static int FONG_SIZE_DETILE = 20;
	static String year;//年
	static String month;//月
	static String day;//日
	static String week;//星期
	static String time;//时间
	static String total;//进馆人数
	static String number;//第几位
	static String name;//姓名
	static String daycount;//今天进馆次数
	static String stunum;//学号
	static String readtotail;//借阅总数
	static String school;//学院
	static String currentcount;//当前借阅书
	static String major;//专业
	static String noreturn;//未归还
	
	static JTextArea textArea;
	static JTextArea textArea1;
	static JTextArea textArea2;
	static JTextArea textArea3;
	
	static JTextArea textArea5;
	static JTextArea textArea7;
	static JTextArea textArea9;
	static JTextArea textArea11;
	static JTextArea textArea13;
	static JTextArea textArea15;
	static JTextArea textArea17;
	static JTextArea textArea19;
	static JTextArea textArea21;
	static JTextArea textArea23;
	
	static{
		try { 
			props.load(DBToolkit.class.getResourceAsStream("/jdbc.properties"));
		} catch (IOException e) {
			e.printStackTrace();
			log.error("加载字体文件失败！", e);
		}
		String size = props.getProperty("font.size") == null ? "20" : props.getProperty("font.size");
		FONG_SIZE = Integer.valueOf(size);
		FONG_SIZE_DETILE = Integer.valueOf(size);
	}
	public static void main(String[] args) {
		init();
		PrintMain.startPanel();
		thread();
	}
	/**
	 * 初始化
	 */
	public static void init(){
		year = DateUtil.getYear(new Date());
		month = DateUtil.getMonth(new Date());
		day = DateUtil.getDay(new Date());
		week = DateUtil.getWeekOfDate(new Date());
		time = DateUtil.getTime(new Date());
		total="4564";//进馆人数
		number="112";//第几位
		name="测试";//姓名
		daycount="23";//今天进馆次数
		stunum="545";//学号
		readtotail="7";//借阅总数
		school="xxx学院";//学院
		currentcount="5";//当前借阅书
		major="设计";//专业
		noreturn="5";//未归还
	}
	
	/**
	 * 刷新数据
	 */
	public static void reloadData(DataBean d){
		Date date = DateUtil.getDate(d.getTime());
		textArea.setText(DateUtil.getYear(date) + " 年  " + DateUtil.getMonth(date) + " 月  " + DateUtil.getDay(date) + " 日"); 
		textArea2.setText(DateUtil.getWeekOfDate(date)); 
		textArea3.setText(DateUtil.getTime(date));
		textArea5.setText(d.getAllCount());
	    textArea7.setText (d.getDayCount()+ " 位读者");  
		textArea9.setText(d.getName());
		textArea11.setText(d.getPsnDayInCount());  
		textArea13.setText(d.getStuNumber());  
		textArea15.setText(d.getReadtotail());  
		//textArea17.setText(school);
		textArea19.setText(d.getCurrentcount());  
		//textArea21.setText(major);  
	    textArea23.setText(d.getNoreturn()); 
	}
	/**
	 * 启动面板
	 */
	public static void startPanel(){
		 frame = new JFrame();  
		 frame.getContentPane().setBackground(Color.yellow);
		 p2 = new Panel();
		 GridLayout gl =  new GridLayout(6, 4);
		 gl.setVgap(8);
		 p2.setLayout(gl);
		 p2.setBackground(new Color(255, 255, 136));
		 frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		 frame.addWindowListener(new WindowAdapter() {
			 public void windowClosing(WindowEvent e) {
				 System.exit(0);
			 }
		 });
		 
		 textArea = new JTextArea(); 
	     textArea.setFont(new Font(Font.DIALOG,Font.BOLD,FONG_SIZE));
	     textArea.setOpaque(false);
	     
	     textArea1 = new JTextArea(); 
	     textArea1.setFont(new Font(Font.DIALOG,Font.BOLD,FONG_SIZE));
	     textArea1.setOpaque(false);
	     
	     textArea2 = new JTextArea(); 
	     textArea2.setFont(new Font(Font.DIALOG,Font.BOLD,FONG_SIZE));
	     textArea2.setOpaque(false);
	     
	     textArea3 = new JTextArea();
	     textArea3.setFont(new Font(Font.DIALOG,Font.BOLD,FONG_SIZE));
	     textArea3.setText(time); 
	     textArea3.setOpaque(false);
	     
	     JTextArea textArea4 = new JTextArea();
	     textArea4.setFont(new Font(Font.DIALOG,Font.BOLD,FONG_SIZE_DETILE));
	     textArea4.setText("总进馆人数");  
	     textArea4.setOpaque(false);
	     
	     textArea5 = new JTextArea();
	     textArea5.setForeground(new Color(3,113,200));
	     textArea5.setFont(new Font(Font.DIALOG,Font.BOLD,FONG_SIZE_DETILE));
	     textArea5.setOpaque(false);
	     
	     JTextArea textArea6 = new JTextArea();
	     textArea6.setFont(new Font(Font.DIALOG,Font.BOLD,FONG_SIZE_DETILE));
	     textArea6.setText("您是第");  
	     textArea6.setOpaque(false);
	     
	     textArea7 = new JTextArea();
	     textArea7.setForeground(new Color(3,113,200));
	     textArea7.setFont(new Font(Font.DIALOG,Font.BOLD,FONG_SIZE_DETILE));
	     textArea7.setOpaque(false);
	     
	     JTextArea textArea8 = new JTextArea();
	     textArea8.setFont(new Font(Font.DIALOG,Font.BOLD,FONG_SIZE_DETILE));
	     textArea8.setText("欢迎您");  
	     textArea8.setOpaque(false);
	     
	     textArea9 = new JTextArea();
	     textArea9.setForeground(new Color(3,113,200));
	     textArea9.setFont(new Font(Font.DIALOG,Font.BOLD,FONG_SIZE_DETILE));
	     textArea9.setOpaque(false);
	     
	     JTextArea textArea10 = new JTextArea();
	     textArea10.setFont(new Font(Font.DIALOG,Font.BOLD,FONG_SIZE_DETILE));
	     textArea10.setText("今天进馆次数");  
	     textArea10.setOpaque(false);
	     
	     textArea11 = new JTextArea();
	     textArea11.setForeground(new Color(3,113,200));
	     textArea11.setFont(new Font(Font.DIALOG,Font.BOLD,FONG_SIZE_DETILE));
	     textArea11.setOpaque(false);
	     
	     JTextArea textArea12 = new JTextArea();
	     textArea12.setFont(new Font(Font.DIALOG,Font.BOLD,FONG_SIZE_DETILE));
	     textArea12.setText("学号");  
	     textArea12.setOpaque(false);
	     
	     textArea13 = new JTextArea();
	     textArea13.setForeground(new Color(3,113,200));
	     textArea13.setFont(new Font(Font.DIALOG,Font.BOLD,FONG_SIZE_DETILE));
	     textArea13.setOpaque(false);
	     
	     JTextArea textArea14 = new JTextArea();
	     textArea14.setFont(new Font(Font.DIALOG,Font.BOLD,FONG_SIZE_DETILE));
	     textArea14.setText("借阅总数");  
	     textArea14.setOpaque(false);
	     
	     textArea15 = new JTextArea();
	     textArea15.setForeground(new Color(3,113,200));
	     textArea15.setFont(new Font(Font.DIALOG,Font.BOLD,FONG_SIZE_DETILE));
	     textArea15.setOpaque(false);
	     
	     /*JTextArea textArea16 = new JTextArea();
	     textArea16.setFont(new Font(Font.DIALOG,Font.BOLD,FONG_SIZE_DETILE));
	     textArea16.setText("学院");  
	     textArea16.setOpaque(false);
	     
	     textArea17 = new JTextArea();
	     textArea17.setForeground(new Color(3,113,200));
	     textArea17.setFont(new Font(Font.DIALOG,Font.BOLD,FONG_SIZE_DETILE));
	     textArea17.setOpaque(false);*/
	     
	     JTextArea textArea18 = new JTextArea();
	     textArea18.setFont(new Font(Font.DIALOG,Font.BOLD,FONG_SIZE_DETILE));
	     textArea18.setText("当前借阅图书");  
	     textArea18.setOpaque(false);
	     
	     textArea19 = new JTextArea();
	     textArea19.setForeground(new Color(3,113,200));
	     textArea19.setFont(new Font(Font.DIALOG,Font.BOLD,FONG_SIZE_DETILE));
	     textArea19.setOpaque(false);
	     
	    /* JTextArea textArea20 = new JTextArea();
	     textArea20.setFont(new Font(Font.DIALOG,Font.BOLD,FONG_SIZE_DETILE));
	     textArea20.setText("专业");  
	     textArea20.setOpaque(false);
	     
	     textArea21 = new JTextArea();
	     textArea21.setForeground(new Color(3,113,200));
	     textArea21.setFont(new Font(Font.DIALOG,Font.BOLD,FONG_SIZE_DETILE));
	     textArea21.setOpaque(false);*/
	     
	     JTextArea textArea22 = new JTextArea();
	     textArea22.setFont(new Font(Font.DIALOG,Font.BOLD,FONG_SIZE_DETILE));
	     textArea22.setText("到期未还图书");  
	     textArea22.setOpaque(false);
	     
	     textArea23 = new JTextArea();
	     textArea23.setForeground(new Color(3,113,200));
	     textArea23.setFont(new Font(Font.DIALOG,Font.BOLD,FONG_SIZE_DETILE));
	     textArea23.setOpaque(false);
	     
	     p2.add(textArea);
		 p2.add(textArea1);
		 p2.add(textArea2);
		 p2.add(textArea3);
		 p2.add(textArea);
		 p2.add(textArea1);
		 p2.add(textArea2);
		 p2.add(textArea3);
		 
		 p2.add(textArea4);
		 p2.add(textArea5);
		 p2.add(textArea6);
		 p2.add(textArea7);
		 
		 p2.add(textArea8);
		 p2.add(textArea9);
		 p2.add(textArea14);
		 p2.add(textArea15);
		 
		 p2.add(textArea12);
		 p2.add(textArea13);
		 p2.add(textArea18);
		 p2.add(textArea19);
		 
		 //p2.add(textArea16);
		 //p2.add(textArea17);
		
		 p2.add(textArea10);
		 p2.add(textArea11);
		//p2.add(textArea20);
		//p2.add(textArea21);
		 p2.add(textArea22);
		 p2.add(textArea23);
		 
		 p2.setVisible(true);
		
	     frame.add(p2);
	     frame.setSize(770,300);  
	    // frame.setResizable(false);
	     frame.setVisible(true); 
	    
	}
	/**
	 * 启动线程
	 */
	public static void thread(){
		
		//Thread t1 = new TreadTime();
		Thread t2 = new TreadData();
		//t1.start();  
	    t2.start();  
	    
	}
	/**
	 * 时间线程
	 */
	static class TreadTime extends Thread {  
	    public void run() {  
	    	 try {
	    		 while(true){
	    			 Thread.sleep(1000);
	    			 System.out.println("RunnableTreadTime："+ new Date().getSeconds());
	    			 time = DateUtil.getTime(new Date());
	    			 textArea3.setText(time);
	    		 }
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }  
	  
	} 
	/**
	 * 加载数据线程
	 */
	static class TreadData extends Thread {  
	    public void run() {  
	    	 try {
	    		 while(true){
	    			 System.out.println("RunnableTreadData：" + new Date().getSeconds());
	    			// reloadData();
			         p2.invalidate();
			         frame.invalidate();
			         Thread.sleep(5000);
	    		 }
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }  
	  
	} 
	
}
