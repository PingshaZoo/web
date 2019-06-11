package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.NetUtil;

/**
 * Servlet implementation class RefreshWeb
 */
@WebServlet("/RefreshWeb")
public class RefreshWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RefreshWeb() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession(true); // 创建session
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setHeader("refresh", "3;URL=http://"+NetUtil.getLocalIp()+":8080/web/FirstServlet");
		PrintWriter out  = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"");
		out.println("<HTML>");
		out.println("<meta http-equiv=\"Content-Type\" content=\"text/html;charset=UTF-8\"/>");
		out.println("<head><title> Hotel California </title></head>");
		out.println("<body>");
		out.println(" <b>Welcome to Hotel California,Refresh!"+System.currentTimeMillis()+"</b><br/>"); //
		out.println("<img style=\"-webkit-user-select: none\" src=\"http://"+NetUtil.getLocalIp()+":8080/web/Image\"><br/>");
		
		
		out.println("</body>");
		out.println("</html>");

		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
