package servlet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constant.Constant;
import util.NetUtil;

/**
 * Servlet implementation class DownloadFile
 */
@WebServlet("/DownloadFile")
public class DownloadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DownloadFile() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("DownloadFile doGet");
		File file = null;
		HttpSession session = request.getSession();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		// 检查处理session中的参数filePath
		String filePath = (String) session.getAttribute("filePath");
		// 路径异常或者长度过长
		if (filePath == null || !filePath.startsWith(Constant.REAL_PATH)
				|| filePath.length() > Constant.MAX_PATH_LENGTH) {
			session.setAttribute("filePath", "");
			request.getRequestDispatcher("/FileBrowser").forward(request, response);
			return;
		}
		System.out.println("DownloadFile   " + filePath);
		OutputStream webOut = null;
		FileInputStream fileInput = null;
		try {
			// 验证处理磁盘这个文件
			file = new File(filePath);
			String fileCanonicalPath = file.getCanonicalPath();
			if (file == null || !file.exists() || !fileCanonicalPath.startsWith(Constant.REAL_PATH)
					|| file.isDirectory()) {
				session.setAttribute("filePath", "");
				request.getRequestDispatcher("/FileBrowser").forward(request, response);
				return;
			}

			response.reset();
			response.addHeader("Content-Disposition",
					"attachment;filename=" + new String(file.getName().getBytes("GBK"), "ISO8859_1"));
			response.addHeader("Content-Length", "" + file.length());
			response.setContentType("application/octet-stream");
			fileInput = (new FileInputStream(file));
			byte[] buf = new byte[2048];
			int n = -1;
			webOut = response.getOutputStream();
			while ((n = fileInput.read(buf)) != -1) {
				webOut.write(buf, 0, n);
			}
			webOut.flush();
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null) {
				session.setAttribute("filePath", "");
			}
			if (webOut != null) {
				webOut.close();
			}
			if (fileInput != null) {
				fileInput.close();
			}

		}

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
