package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.security.Principal;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.NetUtil;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet("/FirstServlet")
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirstServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out  = response.getWriter();
		
		String authType = request.getAuthType();
		Locale local = request.getLocale();
		String contextPath = request.getContextPath();
		String method = request.getMethod();
		String pathInfo = request.getPathInfo();
		String pathTranslated = request.getPathTranslated();
		String protocol = request.getProtocol();
		String queryString = request.getQueryString();
		String remoteUser = request.getRemoteUser();
		String sessionId = request.getSession().getId();
		String requestUrl = request.getRequestURL().toString();
		String scheme = request.getScheme();
		String servletPath = request.getServletPath();
		Principal userPrincipal = request.getUserPrincipal();
		String accept = request.getHeader("accept");
		String referer = request.getHeader("referer");
		String agent = request.getHeader("user-agent");
//		request.getSession().putValue(arg0, arg1);
		
		
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"");
		out.println("<HTML>");
		out.println("<meta http-equiv=\"Content-Type\" content=\"text/html;charset=UTF-8\"/>");
		out.println("<head><title> Hotel California </title></head>");
		out.println("<body>");
		String reqURL = request.getRequestURL().toString();
		out.println("<form action = '" +reqURL +"'method = 'get'>");
		out.println("输入名字：<input type = 'text' name = 'name' />");
		out.println("<input type = 'submit' />");
		out.println("</form>");
		out.println("");
		String name = request.getParameter("name");
		if(name != null && name.trim().length()>0) {
			out.println("您好，<b>"+name+"</b><br/>");
		}
		out.println(" <a href = \"http://"+NetUtil.getLocalIp()+":8080/web/Welcome\">go back hotel！</a><br/><br/>"); 
		out.println("authType  = <b>"+authType+"</b><br/>");
		out.println("local = <b>"+local+"</b><br/>"); // 用户语言环境
		out.println("contextPath = <b>"+contextPath+"</b><br/>"); // context路径
		out.println("method  = <b>"+method+"</b><br/>"); // post or get 请求方式
		out.println("pathInfo = <b>"+pathInfo+"</b><br/>");
		out.println("pathTranslated = <b>"+pathTranslated+"</b><br/>");
		out.println("protocol = <b>"+protocol+"</b><br/>"); // 协议
		out.println("queryString = <b>"+queryString+"</b><br/>"); // 查询字符串 即?后面的内容
		out.println("remoteUser = <b>"+remoteUser+"</b><br/>"); // 远程用户
		out.println("sessionId  = <b>"+sessionId+"</b><br/>");
		out.println("requestUrl =  <b>"+requestUrl+"</b><br/>"); // 请求URL，不包括？后面的信息
		out.println("scheme =  <b>"+scheme+"</b><br/>"); // 协议头
		out.println("servletPath  = <b>"+servletPath+"</b><br/>"); // servlet路径
		out.println("userPrincipal = <b>"+userPrincipal+"</b><br/>");
		out.println("accept = <b>"+accept+"</b><br/>"); // 浏览器支持的格式
		out.println("referer = <b>"+referer+"</b><br/>"); // 从那个页面点击进改页面
		out.println("agent = <b>"+agent+"</b><br/>"); // user agent 信息
		out.println("<img style=\"-webkit-user-select: none\" src=\"http://" + NetUtil.getLocalIp() + ":8080/web/Image\">");
		out.println("</body>");
		out.println("</html>");
		out.println("");
		out.println("");
		out.println("");
		
		out.flush();
		out.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
