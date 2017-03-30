package com.hbcsoft.zdy.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name="verifyCode",urlPatterns="/verifyCode")
public class VerifyCode extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static int width = 60;
	private static int height = 20;
	private static int codeCount = 4;
	private static int xint = 0;
	private static int fontHeight;
	private static int codeY;
	private static char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H','J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R',
			'S', 'T', 'U', 'V', 'W', 'X', 'Y', '1', '3', '4', '5', '6', '7', '8', '9' };

	public void initxuan() throws ServletException {
		xint = width / (codeCount + 1);
		fontHeight = height - 2;
		codeY = height - 4;
	}

	protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		initxuan();
		final BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		final Graphics2D grap = buffImg.createGraphics();
		grap.setColor(Color.WHITE);
		grap.fillRect(0, 0, width, height);
		final Font font = new Font("Fixedsys", Font.PLAIN, fontHeight);
		grap.setFont(font);
		grap.setColor(Color.BLACK);
		grap.drawRect(0, 0, width - 1, height - 1);
		grap.setColor(Color.BLACK);
		for (int i = 0; i < 10; i++) {
			drawLine(grap);
		}
		final StringBuffer randomCode = new StringBuffer();
		for (int i = 0; i < codeCount; i++) {
			final String strRand = getRand();
			
			grap.setColor(getColor());
			grap.drawString(strRand, (i + 1) * xint - 2, codeY);
			randomCode.append(strRand);
		}
		final HttpSession session = request.getSession();
		session.setAttribute("validateCode", randomCode.toString());
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		final ServletOutputStream sos = response.getOutputStream();
		ImageIO.write(buffImg, "jpeg", sos);
		sos.close();
	}
	
	private void drawLine(final Graphics2D grap)
	{
		final Random random = new Random();
		final int xValue = random.nextInt(width);
		final int yValue = random.nextInt(height);
		final int xl = random.nextInt(12);
		final int yl = random.nextInt(12);
		grap.drawLine(xValue, yValue, xValue + xl, yValue + yl);
	}
	
	private String getRand()
	{
		final Random random = new Random();
		return String.valueOf(codeSequence[random.nextInt(30)]);
	}
	
	private Color getColor()
	{
		final Random random = new Random();
		final int red = random.nextInt(255);
		final int green = random.nextInt(255);
		final int blue = random.nextInt(255);
		return new Color(red, green, blue);
	}

	protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
