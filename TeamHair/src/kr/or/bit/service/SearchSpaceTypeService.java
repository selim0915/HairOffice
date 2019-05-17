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

public class SearchSpaceTypeService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();

		try {

			String spaceType = request.getParameter("spacetype");
			List<SearchSpaceDto> dtoList = new ArrayList<SearchSpaceDto>();
			SpaceDao dao = new SpaceDao();

			dtoList = dao.getSpaceAvailableListBySpaceType(spaceType);

			request.setAttribute("spaceList", dtoList);

			forward.setRedirect(false);
			forward.setPath("/WEB-INF/space/rent_search_spacetype_result.jsp");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}
}
