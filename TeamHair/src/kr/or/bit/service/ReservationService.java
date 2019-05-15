package kr.or.bit.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.SpaceDao;
import kr.or.bit.dto.SearchSpaceDto;
import kr.or.bit.utils.TeamDate;
import kr.or.bit.utils.TeamFormat;

public class ReservationService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		try {
			System.out.println("ReservationService");
			
			String userName = request.getParameter("username");
			String userId = request.getParameter("userid");
			String phone = request.getParameter("phone");
			String designer = request.getParameter("designer");
			String servicetype = request.getParameter("servicetype");
			String start_date = request.getParameter("start_date");
			String time = request.getParameter("time");

			System.out.println(userName);
			System.out.println(userId);
			System.out.println(phone);
			System.out.println(designer);
			System.out.println(servicetype);
			System.out.println(start_date);
			System.out.println(time);
			
			
//			List<SearchSpaceDto> dtoList = new ArrayList<SearchSpaceDto>();
//			SpaceDao dao = new SpaceDao();
			
//			dtoList=dao.getSpaceAvailableList(Integer.parseInt(persons), TeamDate.date(start_date.replace("-", "")), TeamDate.date(end_date.replace("-", "")));
			
//			request.setAttribute("spaceList", dtoList);
//			request.setAttribute("start_date", TeamFormat.dateFormatKorean(TeamDate.date(start_date.replace("-", ""))));
//			request.setAttribute("end_date", TeamFormat.dateFormatKorean(TeamDate.date(end_date.replace("-", ""))));
			
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/branch/reservation_result.jsp");
			
		} catch (Exception e) {	
			e.printStackTrace();
		}
		return forward;
	}

}
