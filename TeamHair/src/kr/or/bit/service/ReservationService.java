package kr.or.bit.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.CodeDao;
import kr.or.bit.dao.ReservationDao;
import kr.or.bit.dao.SpaceDao;
import kr.or.bit.dto.CodeDto;
import kr.or.bit.dto.ReservationDto;
import kr.or.bit.dto.SearchSpaceDto;
import kr.or.bit.utils.TeamDate;
import kr.or.bit.utils.TeamFormat;

public class ReservationService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();

		try {

			String userName = request.getParameter("username");
			String userId = request.getParameter("userid");
			String phone = request.getParameter("phone");
			String designer = request.getParameter("designer");
			String servicetype = request.getParameter("servicetype");
			String start_date = request.getParameter("start_date");
			int hour = Integer.parseInt(request.getParameter("time"));

			String[] array = start_date.split("-");
			int year = Integer.parseInt(array[0]);
			int month = Integer.parseInt(array[1]);
			int day = Integer.parseInt(array[2]);

			ReservationDto dto = new ReservationDto();
			ReservationDao dao = new ReservationDao();

			dto.setServiceType(servicetype);
			dto.setStartDateTime(TeamDate.datetime(year, month, day, hour, 00));
			dto.setEndDateTime(TeamDate.datetime(year, month, day, hour + 1, 00));
			dto.setCancelYn("N");
			dto.setUserId(userId);
			dto.setSpaceId(1);
			dto.setPhotoId(1);

			dao.insertReservation(dto);
			
			CodeDao codeDao = new CodeDao();
			CodeDto codeDto = new CodeDto();
			
			codeDto = codeDao.getCodeListByCode(servicetype);
			
			request.setAttribute("userName",userName);
			request.setAttribute("designer", designer);
			request.setAttribute("start_date", TeamFormat.dateFormatKorean(TeamDate.date(start_date.replace("-", ""))));
			request.setAttribute("hour", hour);
			request.setAttribute("codeDto", codeDto);
			
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/branch/reservation_result.jsp");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}

}
