package NewTest.test.servlet;
/**
 * �������ݿ⣬SELECT�Ӿ䣬�ṩ�����ݿ��и���id������Ϣ�ķ���
 * ����ArrayList�������ݿ�����Ϣ������list���ر�أ�
 * 1. Ϊlist���"list"���ԣ��Ա�֮��������Manage2.jsp����ʾ
 * 2. ����search�������Ƿ�Ϊ�գ�ȷ���ǲ���ʱ���������Ϣ����ȫ����ȡ
 */
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import userInfo.User;
import cn.NewTest.db.ConnDB;

/**
 * Servlet implementation class FindServlet
 */
@WebServlet("/FindServlet")
public class FindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FindServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			String searchtext = request.getParameter("search");
			
			String sql = "SELECT * FROM tb_account";
			
			if(searchtext != null && !searchtext.equals(""))
			{
				sql = sql + " WHERE susername LIKE '%" + searchtext + "%' or name LIKE '%" + searchtext + "%'";
			}

			ResultSet rs = ConnDB.query(sql);
			
			List<User> list = new ArrayList<User>();
			// ��ȡ���ݿ��е���Ϣ������list
			while (rs.next()) {
				User userinfo = new User();
				userinfo.setId(rs.getString("id"));
				userinfo.setUsername(rs.getString("susername"));
				userinfo.setPassword(rs.getString("spassword"));
				userinfo.setUsertype(rs.getString("usertype"));
				userinfo.setName(rs.getString("name"));
				userinfo.setEmail(rs.getString("email"));
				userinfo.setApart(rs.getString("apart"));
				userinfo.setTel(rs.getString("tel"));
				list.add(userinfo);
			}
			
			request.setAttribute("list", list);
			rs.close(); // �ر�ResultSet
			ConnDB.close(); // �ر����ݿ�
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//��ת���������
		request.getRequestDispatcher("Manage2.jsp").forward(request, response);
	}
}
