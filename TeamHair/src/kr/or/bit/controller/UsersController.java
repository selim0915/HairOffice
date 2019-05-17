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
import kr.or.bit.service.ChangePwdOkService;
import kr.or.bit.service.LogOutService;
import kr.or.bit.service.LoginOkService;
import kr.or.bit.service.ModifyUserOkService;
import kr.or.bit.service.ModifyUserService;
import kr.or.bit.service.SignUpOkService;

@WebServlet("*.usr")
public class UsersController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UsersController() {
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlCommand = requestURI.substring(contextPath.length());

		Action action = null;
		ActionForward forward = new ActionForward();

		if (urlCommand.equals("/Login.usr")) { // 로그인
			try {

				forward.setRedirect(false);
				forward.setPath("/WEB-INF/login/log_in.jsp");
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
		} else if (urlCommand.equals("/Logout.usr")) { // 로그아웃
			try {

				action = new LogOutService();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (urlCommand.equals("/SignUp.usr")) { // 회원등록
			try {
				forward.setRedirect(false);
				forward.setPath("/WEB-INF/login/sign_up.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (urlCommand.equals("/SignUpOk.usr")) { // 회원등록
			try {

				action = new SignUpOkService();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (urlCommand.equals("/Modify.usr")) { // 회원정보 수정
			try {

				action = new ModifyUserService();
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (urlCommand.equals("/ModifyUserOk.usr")) { // 회원정보 수정
			try {

				action = new ModifyUserOkService();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (urlCommand.equals("/ChangePwd.usr")) { // 비밀번호 변경이동
			try {

				forward.setRedirect(false);
				forward.setPath("/WEB-INF/login/change_pwd.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (urlCommand.equals("/ChangePwdOk.usr")) { // 비밀번호 변경
			try {

				action = new ChangePwdOkService();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (urlCommand.equals("/ChangeProfilePicture.usr")) { // 프로필사진 변경
			try {

				forward.setRedirect(false);
				forward.setPath("/WEB-INF/login/popup_profile.jsp");
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
