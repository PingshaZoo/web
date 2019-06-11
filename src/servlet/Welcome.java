package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.NetUtil;

/**
 * Servlet implementation class Welcome
 */
@WebServlet("/Welcome")
public class Welcome extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Welcome() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession(true); // 创建session
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setHeader("refresh", "3;URL=http://"+NetUtil.getLocalIp()+":8080/web/Welcome");
		PrintWriter out  = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"");
		out.println("<HTML>");
		out.println("<meta http-equiv=\"Content-Type\" content=\"text/html;charset=UTF-8\"/>");
		out.println("<head><title> Hotel California </title></head>");
		out.println("<body>");
		out.println(" <b>Welcome to Hotel California</b><br/>"); //
		out.println("<img style=\"-webkit-user-select: none\" src=\"http://"+NetUtil.getLocalIp()+":8080/web/Image\"><br/>");
		
		out.println(" <a href = \"http://"+NetUtil.getLocalIp()+":8080/web/Welcome\">Welcome</a><br/>"); 
		out.println(" <a href = \"http://"+NetUtil.getLocalIp()+":8080/web/FirstServlet\">FirstServlet</a><br/>"); 
		out.println(" <a href = \"http://"+NetUtil.getLocalIp()+":8080/web/Image\">Image</a><br/>"); 
		out.println(" <a href = \"http://"+NetUtil.getLocalIp()+":8080/web/ForwardReq\">ForwardReq</a><br/>"); 
		out.println(" <a href = \"http://"+NetUtil.getLocalIp()+":8080/web/UploadFile\">UploadFile</a><br/>"); 
		out.println(" <a href = \"http://"+NetUtil.getLocalIp()+":8080/web/DownloadFile\">DownloadFile</a><br/>"); 
		out.println(" <a href = \"http://"+NetUtil.getLocalIp()+":8080/web/FileBrowser\">FileBrowser</a><br/>"); 
		out.println(" <a href = \"http://"+NetUtil.getLocalIp()+":8080/web/RedirectReq\">RedirectReq</a><br/>"); 
		out.println(" <a href = \"http://"+NetUtil.getLocalIp()+":8080/web/RefreshWeb\">RefreshWeb</a><br/>"); 
		
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
