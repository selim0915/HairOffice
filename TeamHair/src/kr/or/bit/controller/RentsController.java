package kr.or.bit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.service.LoginOkService;

@WebServlet("*.rent")
public class RentsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RentsController() {
		super();
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlCommand = requestURI.substring(contextPath.length());

		Action action = null;
		ActionForward forward = new ActionForward();

		if (urlCommand.equals("/Login.usr")) { // 로그인
			try {
				forward.setRedirect(false);
				forward.setPath("/WEB-INF/login/log-in.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (urlCommand.equals("/LoginOk.usr")) { // 로그인 OK
			try {
				action = new LoginOkService();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (forward != null) {
			if (forward.isRedirect()) { // true
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}
