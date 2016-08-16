package print;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DoorService extends HttpServlet {

	public DoorService() {
		super();
	}
	public void destroy() {
		super.destroy(); 
		
	}
	public void init() throws ServletException {
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String  user = request.getParameter("user") == null ? "" : request.getParameter("user");
		String  pwd = request.getParameter("pwd")  == null ? "" : request.getParameter("pwd");
		String  name = request.getParameter("name") == null ? "" : request.getParameter("name");
		String  studentno = request.getParameter("studentno") == null ? "" : request.getParameter("studentno");
		String  cardno = request.getParameter("cardno") == null ? "" : request.getParameter("cardno");
		String  classno = request.getParameter("classno") == null ? "" : request.getParameter("classno");
		String  time = request.getParameter("time") == null ? "" : request.getParameter("time");//刷卡时间：time （时间格式yyyy-MM-ddHH:mm:ss）
		String  addr = request.getParameter("addr") == null ? "" : request.getParameter("addr");
		String  pass = request.getParameter("pass") == null ? "" : request.getParameter("pass");
		String  stuNumber = request.getParameter("stuNumber") == null ? "" : request.getParameter("stuNumber");
		String  psnDayInCount = request.getParameter("psnDayInCount") == null ? "" : request.getParameter("psnDayInCount");
		String  dayCount = request.getParameter("dayCount") == null ? "" : request.getParameter("dayCount");
		String  allCount = request.getParameter("allCount") == null ? "" : request.getParameter("allCount");
		
		DataBean d = new DataBean();
		
		d.setUser(user);
		d.setPwd(pwd);
		d.setName(name);
		d.setStudentno(studentno);
		d.setCardno(cardno);
		d.setClassno(classno);
		d.setTime(time);
		d.setAddr(addr);
		d.setPass(pass);
		d.setStuNumber(stuNumber);
		d.setPsnDayInCount(psnDayInCount);
		d.setDayCount(dayCount);
		d.setAllCount(allCount);
		
		//借阅信息
		d = loadData(d);
		PrintMain.reloadData(d);
		
		response.getWriter().print("0");
		response.getWriter().flush();
				
	}
	/**
	 * 取得其他数据
	 * @throws SQLException 
	 */
	public DataBean loadData(DataBean db){
		String sql = "select count(*) count from T_TS_JY where DZTM = '"+db.getStuNumber()+"'";
		//String sql2 = "select count(*) count from T_TS_JYLS where DZTM = '"+db.getStudentno()+"'";
		String sql2 = "select count(*) count from T_TS_JY where DZTM = '"+db.getStuNumber()+"' and YHRQ < '"+DateUtil.getDate(new Date())+"'";
		DataBean d = db;
		
		String readtotail;//借阅总数
		String school;//学院
		String currentcount;//当前借阅书
		String major;//专业
		String noreturn;//未归还
		
		try {
			
			Connection cnn = DBToolkit.getConnection();
			ResultSet res =  DBToolkit.executeQuery(cnn, sql);
			ResultSet res2 =  DBToolkit.executeQuery(cnn, sql2);
			res.next();
			res2.next();
			readtotail = String.valueOf(res.getInt("count"));
			noreturn = String.valueOf(res2.getInt("count"));
			
			d.setReadtotail(readtotail);
			d.setCurrentcount(readtotail);
			d.setNoreturn(noreturn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return d;
	}

	public static void main(String[] args) throws Exception {
		DoorService d = new DoorService();
		DataBean db = new DataBean();
		db.setStudentno("201520012");
		db = d.loadData(db);
		System.out.println(db.getReadtotail());
		System.out.println(db.getNoreturn());
	}

}
