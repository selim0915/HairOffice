package kr.or.bit.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.LikesDao;
import kr.or.bit.dao.PhotoDao;
import kr.or.bit.dao.ProfileDao;
import kr.or.bit.dao.SpaceDao;
import kr.or.bit.dto.LikeListDto;
import kr.or.bit.dto.PhotoDto;
import kr.or.bit.dto.ProfileDto;
import kr.or.bit.dto.SpaceDto;
import kr.or.bit.dto.UsersDto;
import kr.or.bit.utils.TeamDate;

public class SearchSpaceService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		try {
			System.out.println("SearchSpaceService");
			
//			HttpSession session = request.getSession();
//			UsersDto userDto = (UsersDto) session.getAttribute("usersdto");
//			String userid = userDto.getUserId();
//			System.out.println("userDto: "+ userDto);
			
			
			String userId = request.getParameter("userid");
			String persons = request.getParameter("persons");
			String start_date = request.getParameter("start_date");
			String end_date = request.getParameter("end_date");
					
			System.out.println("userid : " + userId);
			System.out.println("persons : " + Integer.parseInt(persons));
			System.out.println("start_date : " + start_date.replace("-", ""));
			System.out.println("end_date : " + end_date.replace("-", ""));
			
			
			List<SpaceDto> dtoList = new ArrayList<SpaceDto>();
			SpaceDao dao = new SpaceDao();
			
			dtoList=dao.getSpaceAvailableList(Integer.parseInt(persons), TeamDate.date(start_date.replace("-", "")), TeamDate.date(end_date.replace("-", "")));
			
			request.setAttribute("spaceList", dtoList);
			
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/space/rent_search_result.jsp");
			
		} catch (Exception e) {	
			e.printStackTrace();
		}
		return forward;
	}
}
