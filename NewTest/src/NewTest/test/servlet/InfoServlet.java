package NewTest.test.servlet;
/**
 * һ����ͨ������Cookie�����û���Ϣ���м���
 * ͬʱ��һ���н���ת������
 */
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.NewTest.db.ConnDB;

/**
 * Servlet implementation class InfoServlet
 */
@WebServlet("/InfoServlet")
public class InfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InfoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		try {
			String sql = "SELECT * FROM tb_account WHERE susername='" + username + "' and spassword='" + password + "'";
			ResultSet rs = ConnDB.query(sql); // mysqlִ�к󷵻ص��������

			if (rs.next()) { // �жϹ������ƶ������ж��Ƿ���Ч
				HttpSession session = request.getSession();
				session.setAttribute("username", username);// �����û�����session������
				System.out.println(username);
				String[] r = request.getParameterValues("isChecked");
				if (r != null && r.length > 0) {
					// �½�cookie����
					Cookie usernameCookie = new Cookie("usernameCookie", username);
					Cookie passwordCookie = new Cookie("passwordCookie", password);
					// ����cookie����Ч��7�죬��λΪ��
					usernameCookie.setMaxAge(604800);
					passwordCookie.setMaxAge(604800);
					// д��cookie����
					response.addCookie(usernameCookie);
					response.addCookie(passwordCookie);
				} else {
					// ����û�ȡ���˼�ס�˻���Ϣ����Ӧ�ö�cookie�����Ϣ��������
					Cookie[] cookies = request.getCookies();
					if (cookies != null && cookies.length > 0) {
						for (Cookie c : cookies) {
							if (c.getName().equals("usernameCookie") || c.getName().equals("passwordCookie")) {
								// ʹcookie����
								c.setMaxAge(0);
								response.addCookie(c);
							}
						}
					}
				}
				rs.close(); // �ر�ResultSet
				ConnDB.close(); // �ر����ݿ�
				request.getRequestDispatcher("FindServlet").forward(request, response);
			} else {
				rs.close(); // �ر�ResultSet
				System.out.println("Ouch!");
				request.getRequestDispatcher("LoginError.jsp").forward(request, response);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
