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
import kr.or.bit.service.QnACommentsListAsyncService;
import kr.or.bit.service.QnACommentsDeleteService;
import kr.or.bit.service.QnACommentsInsertService;
import kr.or.bit.service.QnADeleteService;
import kr.or.bit.service.QnADetailService;
import kr.or.bit.service.QnAInsertService;
import kr.or.bit.service.QnAListService;
import kr.or.bit.service.QnAreInsertService;
import kr.or.bit.service.QnAReplyFormService;
import kr.or.bit.service.QnAUpdateFormService;
import kr.or.bit.service.QnAUpdateService;

@WebServlet("*.do")
public class QnAcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public QnAcontroller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url_Command = requestURI.substring(contextPath.length());

		Action action = null;
		ActionForward forward = new ActionForward();

		if (url_Command.equals("/QnA.do")) { // QnA메인 이동
			try {
				action = new QnAListService();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (url_Command.equals("/QnAwrite.do")) { // QnA 글쓰기페이지 이동
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/QnA/QnAwrite.jsp");
		} else if (url_Command.equals("/QnAinsert.do")) { // QnA 데이터 추가하기
			try {
				action = new QnAInsertService();
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (url_Command.equals("/QnAdetail.do")) { // QnA 글 상세보기
			try {

				action = new QnADetailService();
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (url_Command.equals("/QnAdelete.do")) { // QnA 데이터 삭제

			try {
				action = new QnADeleteService();
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (url_Command.equals("/QnAupdateform.do")) { // QnA 데이터 수정 페이지 이동

			action = new QnAUpdateFormService();
			forward = action.execute(request, response);
		} else if (url_Command.equals("/QnAupdate.do")) { // QnA 데이터 수정

			try {
				action = new QnAUpdateService();
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (url_Command.equals("/QnAcommentsinsert.do")) { // QnA 댓글 추가하기

			try {
				action = new QnACommentsInsertService();
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (url_Command.equals("/QnAcommentslistAsync.do")) { // 비동기 댓글보기

			action = new QnACommentsListAsyncService();
			forward = action.execute(request, response);
		} else if (url_Command.equals("/QnAcommentsdelete.do")) { // QnA 댓글 삭제하기

			try {
				action = new QnACommentsDeleteService();
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (url_Command.equals("/QnArewrite.do")) { // QnA 답글쓰기 페이지 이동
			action = new QnAReplyFormService();
			forward = action.execute(request, response);
		} else if (url_Command.equals("/QnAreinsert.do")) { // QnA 답글 추가하기

			try {
				action = new QnAreInsertService();
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

}
