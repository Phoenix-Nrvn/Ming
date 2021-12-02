package NewTest.test.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import userInfo.User;

@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@SuppressWarnings("deprecation")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//�����������
		response.setContentType("text/html; charset=UTF-8");
		//����Session���󱣴��û���Ϣ
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		PrintWriter out = response.getWriter();
		
		//����user���󣬱����˺š�����
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		//����Session���󣬱���user����
		request.getSession().setAttribute("user", user);
		//��¼�ɹ�����תָ��ҳ��
		PrintWriter out1 = response.getWriter();
		// ����request�ı���
		request.setCharacterEncoding("utf-8");
		
		out.println("�û���Ϣ����"+"<br>");
		out1.println("�û���"+username+"<br>");
		out1.println("����"+password+"<br>");
			
		HttpSession session = request.getSession();
		//�������еĲ���Ҫ��setAttribute()��һ��
		if (user.getUsername() == null) {
			response.getWriter().print("����δ��¼����<a href='Index.jsp'>��¼</a>");
		}
		else {
			response.setHeader("Refresh", "5;url=Manage.jsp?username1="+user.getUsername());
			Cookie c = new Cookie("JSESSIONID",session.getId());
			c.setMaxAge(30*60);
			c.setPath("Index.jsp");
			response.addCookie(c);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
