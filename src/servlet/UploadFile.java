package servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import constant.Constant;
import util.NetUtil;

/**
 * Servlet implementation class UploadFile
 */
@WebServlet("/UploadFile")
public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadFile() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("UploadFile doGet");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"");
		out.println("<HTML>");
		out.println("<meta http-equiv=\"Content-Type\" content=\"text/html;charset=UTF-8\"/>");
		out.println("<head><title> A SERVLET </title></head>");
		out.println("<body>");
		out.println(" <b>Welcome to Hotel California, please uploadFile</b><br/><br/>"); //
		out.println(" <a href = \"http://"+NetUtil.getLocalIp()+":8080/web/Welcome\">go back hotel！</a><br/><br/>"); 
		out.println("<div align = \"center\" ></br>\r\n" + "<form action = \"http://" + NetUtil.getLocalIp()
				+ ":8080/web/UploadFile\" method = \"post\" enctype = \"multipart/form-data\"></br>\r\n"
				+ "<div class = \"line\">\r\n" + "<div align = \"left\" class = \"leftDiv\">upload file </div>\r\n"
				+ "<div align = \"left\" class = \"rightDiv\">\r\n"
				+ "	<input type = \"file\" name = \"file\" class = \"text\">\r\n" + " </div>\r\n" + "</div>\r\n"
				+ "\r\n" + "<div align = \"left\" class = \"rightDiv\">\r\n"
				+ "	<input type = \"submit\" name = \"upload\" class = \"button\">\r\n" + " </div>\r\n" + " </div>\r\n"
				+ "</form>");

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
		System.out.println("UploadFile doPost");

		PrintWriter webWriter = response.getWriter();
		if (!ServletFileUpload.isMultipartContent(request)) {
			webWriter.println("Error:表单提交必须是二进制数据！");
			webWriter.flush();
			doGet(request, response);
			return;
		}

		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		diskFileItemFactory.setSizeThreshold(Constant.MEMORY_THRESHOLD);
		diskFileItemFactory.setRepository(new File(Constant.TEMP_PATH));

		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		servletFileUpload.setFileSizeMax(Constant.MAX_FILE_SIZE);
		servletFileUpload.setSizeMax(Constant.MAX_REQUEST_SIZE);

		File uploadDir = new File(Constant.REAL_PATH);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}

		try {
			Map<String, List<FileItem>> map = servletFileUpload.parseParameterMap(request);
			for (String str : map.keySet()) {
				System.out.println("key: " + str);
			}
			List<FileItem> list = map.get("file");
			for (FileItem item : list) {
				String fileName = new File(item.getName()).getName();
				String filePath = Constant.REAL_PATH + File.separator + fileName;
				File storeFile = new File(filePath);
				System.out.println("新建文件： " + filePath);
				item.write(storeFile);
			}

			doGet(request, response);
		} catch (FileUploadException e) {

		} catch (Exception e) {

		}

		// doGet(request, response);
	}

}
