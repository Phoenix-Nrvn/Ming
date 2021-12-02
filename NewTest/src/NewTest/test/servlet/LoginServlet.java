package NewTest.test.servlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import userInfo.User;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		PrintWriter out = response.getWriter();
		
		
		if (username.equals("3190911") && password.equals("123456")) {
			//����user���󣬱����˺š�����
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			//��¼�ɹ�����תָ��ҳ��
			PrintWriter out1 = response.getWriter();
			// ����request�ı���
			request.setCharacterEncoding("utf-8");
			
			out1.println("�û���"+username+"<br>");
			out1.println("����"+password+"<br>");
			//����Session���󣬱���user����
			//request.getSession().setAttribute("user", user);
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			response.sendRedirect("IndexServlet");
			//out1.println("�������<a href='Manage.jsp'>�û�����</a>����");
			
		}
		else {
			out.println("�˺Ż�������󣬵��<a href='Index.jsp'>����</a>����......");
			//response.sendRedirect("Index.jsp");
		}
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
