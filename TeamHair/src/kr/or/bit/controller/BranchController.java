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
import kr.or.bit.service.ReservationService;
import kr.or.bit.service.SearchSpaceService;
import kr.or.bit.service.SearchSpaceTypeService;

@WebServlet("*.brh")
public class BranchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BranchController() {

	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlCommand = requestURI.substring(contextPath.length());

		Action action = null;
		ActionForward forward = new ActionForward();

		if (urlCommand.equals("/Branch.brh")) { // 지점 메인
			try {

				forward.setRedirect(false);
				forward.setPath("/WEB-INF/branch/branch_list.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (urlCommand.equals("/Space.brh")) { // 공간 메인 정보
			try {

				forward.setRedirect(false);
				forward.setPath("/WEB-INF/space/seoul_office.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (urlCommand.equals("/GangnamBranchInfo.brh")) { // 강남오피스 : 예약 서비스
			try {

				forward.setRedirect(false);
				forward.setPath("/WEB-INF/branch/salone_reserve_infor.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (urlCommand.equals("/Reservation.brh")) { // 강남오피스 : 예약결과
			try {

				action = new ReservationService();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (urlCommand.equals("/GangnamOfficeInfo.brh")) { // 강남오피스의 임대조건 검색
			try {

				forward.setRedirect(false);
				forward.setPath("/WEB-INF/space/seoul_gangnam_rent.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (urlCommand.equals("/SearchSpace.brh")) { // 강남오피스의 임대타입/기간별 임대조건 검색
			try {

				action = new SearchSpaceService();
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (urlCommand.equals("/SearchSpaceType.brh")) { // 임대타입별 검색
			try {

				action = new SearchSpaceTypeService();
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
