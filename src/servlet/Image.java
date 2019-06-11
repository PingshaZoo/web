package servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import util.NetUtil;

/**
 * Servlet implementation class Image
 */
@WebServlet("/Image")
public class Image extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String CHARS = "QWERTYUPASFGHJKXCVBNM" + "3456789";
	public static final Random RAND = new Random();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Image() {
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
		response.setContentType("image/jpeg");
		String randStr = getRandomString();
		request.getSession(true).setAttribute("randStr", randStr);
		BufferedImage bufferedImage = grenarateGraphics2D(randStr);
		ServletOutputStream output = response.getOutputStream();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(output);
		encoder.encode(bufferedImage);
		output.flush();
		output.close();
//		response.getWriter().append("Served at: ").append(request.getContextPath());
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

	public static String getRandomString() {
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < 6; i++) {
			str.append(CHARS.charAt(RAND.nextInt(CHARS.length() - 1)));
		}
		return str.toString();
	}

	public static Color getRandColor() {
		return new Color(RAND.nextInt(255), RAND.nextInt(255), RAND.nextInt(255));
	}

	public static Color getReverseColor(Color c) {
		return new Color(255 - c.getBlue(), 255 - c.getGreen(), 255 - c.getRed());
	}

	public static BufferedImage grenarateGraphics2D(String str) {
		int width = 100;
		int height = 30;
		Color randColor = getRandColor();
		Color reverseColor = getReverseColor(randColor);
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = bufferedImage.createGraphics();
		graphics.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		graphics.setColor(randColor);
		graphics.fillRect(0, 0, width, height);
		graphics.setColor(reverseColor);
		graphics.drawString(str, 18, 20);
		for (int i = 0, n = RAND.nextInt(50); i < n; i++) {
			graphics.drawRect(RAND.nextInt(width), RAND.nextInt(height), 1, 1);
		}
		return bufferedImage;
	}

}
