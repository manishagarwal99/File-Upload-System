package com.ipl.filepload.filop;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Fetches the Login credentials of Admin and Field Officials and validates it
 * by looking up the database or prepolulated credentials
 * 
 * When the client sends the request to this servlet, it initiates the
 * validation procedure, the redirects the user to the desired page.
 */

@WebServlet("/LoginPage")
public class LoginPageServlet extends HttpServlet {

	/** use default serialVersionUID for interoperability */
	private static final long serialVersionUID = 1L;

	/**
	 * Called by the server to allow a servlet to handle a POST request.
	 *
	 * The HTTP POST method allows the client to send data of unlimited length to
	 * the Web server a single time and is useful when posting information.
	 *
	 * Fetches the login credentials. If validated ,redirects the user to desired
	 * page. Otherwise shows an error message.
	 *
	 * @param request  an {@link HttpServletRequest} object that contains the
	 *                 request the client has made of the servlet
	 *
	 * @param response an {@link HttpServletResponse} object that contains the
	 *                 response the servlet sends to the client
	 *
	 * @return Nothing.
	 * 
	 * @exception IOException      if an input or output error is detected when the
	 *                             servlet handles the request
	 *
	 * @exception ServletException if the request for the POST could not be handled
	 *
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			String name = request.getParameter("user");
			String password = request.getParameter("pass");

			HttpSession session = request.getSession(true);
			if (name.equals("patient1") && password.equals("12345")) {
				session.setAttribute("name", name);
				session.setAttribute("id", "1");
				response.sendRedirect("fileChoose.jsp");

			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
				rd.include(request, response);
				session.invalidate();
			}
		} finally {
			out.close();
		}

	}
}
