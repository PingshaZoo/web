package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constant.Constant;
import util.NetUtil;

/**
 * Servlet implementation class FileBrowser
 */
@WebServlet("/FileBrowser")
public class FileBrowser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileBrowser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("FileBrowser doGet");

		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		File file = null;
		HttpSession session = request.getSession(true);

		// 妫�鏌ュ鐞唃et鏂瑰紡浼犲叆鐨勫弬鏁癴ilename
		String fileName = request.getParameter("filename");
		if (fileName == null || "".equals(fileName)) {
			session.setAttribute("filePath", Constant.REAL_PATH);
		} else {
			String lastFilePath = (String) session.getAttribute("filePath");
			session.setAttribute("filePath", lastFilePath + File.separator + fileName);
		}

		// 妫�鏌ュ鐞唖ession涓殑鍙傛暟filePath
		String filePath = (String) session.getAttribute("filePath");
		if (!filePath.startsWith(Constant.REAL_PATH) || filePath.length() > Constant.MAX_PATH_LENGTH) { // 璺緞寮傚父鎴栬�呴暱搴﹁繃闀�
			session.setAttribute("filePath", "");
			request.getRequestDispatcher("/Welcome").forward(request, response);
			return;
		}

		// 楠岃瘉澶勭悊纾佺洏鏄惁瀛樺湪杩欎釜鏂囦欢
		file = new File(filePath);
		String fileCanonicalPath = file.getCanonicalPath();
		if (file == null || !file.exists() || !fileCanonicalPath.startsWith(Constant.REAL_PATH)) {
			session.setAttribute("filePath", "");
			request.getRequestDispatcher("/Welcome").forward(request, response);
			return;
		}

		// 濡傛灉鏄枃浠朵笉鏄洰褰曞氨杞埌DownloadFile
		if (!file.isDirectory()) {
			request.getRequestDispatcher("/DownloadFile").forward(request, response);
			return;
		}

		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"");
		out.println("<HTML>");
		out.println("<meta http-equiv=\"Content-Type\" content=\"text/html;charset=UTF-8\"/>");
		out.println("<head><title> Hotel California </title></head>");
		out.println("<body>");
		out.println(" <b>Welcome to Hotel California. Please Download File.</b><br/>"); //
		out.println(" <a href = \"http://"+NetUtil.getLocalIp()+":8080/web/Welcome\">go back hotel锛�</a><br/><br/>"); 
		out.println("<img style=\"-webkit-user-select: none\" src=\"http://" + NetUtil.getLocalIp()
				+ ":8080/web/Image\"><br/>");
		out.println(" <a href = \"http://" + NetUtil.getLocalIp() + ":8080/web/FileBrowser?filename=..\">..</a><br/>");

		File[] files = file.listFiles();
		for (File f : files) {
			if (f.isFile()) {
				out.println(" <a href = \"http://" + NetUtil.getLocalIp() + ":8080/web/FileBrowser?filename="
						+ f.getName() + "\"> File:   " + f.getName() + "</a><br/>");
			}
			if (f.isDirectory()) {
				out.println(" <a href = \"http://" + NetUtil.getLocalIp() + ":8080/web/FileBrowser?filename="
						+ f.getName() + "\"> Directory:   " + f.getName() + "</a><br/>");
			}
		}

		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
