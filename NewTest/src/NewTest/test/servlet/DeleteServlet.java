package NewTest.test.servlet;
/**
 * ���ɾ���󣬸���getParameter��ȡid
 * ��������id, ͨ��DELETE�Ӿ䣬����ɾ��
 */
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.NewTest.db.ConnDB;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	  
		request.setCharacterEncoding("UTF-8");
		int id = Integer.valueOf(request.getParameter("id"));

		String sql = "DELETE FROM tb_account WHERE id = " + id;
		System.out.print(sql);
		int rows = ConnDB.update(sql);
		request.setAttribute("fhref", "ɾ��");
		if (rows > 0) {
			request.getRequestDispatcher("RegResult.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("Index.jsp").forward(request, response);
		}
		ConnDB.close();
	}

}
