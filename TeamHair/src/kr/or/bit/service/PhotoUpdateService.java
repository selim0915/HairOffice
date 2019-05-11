package kr.or.bit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.PhotoDao;
import kr.or.bit.dto.PhotoDto;

public class PhotoUpdateService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		try {
			System.out.println("PhotoUpdateService.java 진입");
			
			request.setCharacterEncoding("UTF-8");
			
			String fileName = request.getParameter("fileName");
			String description = request.getParameter("description");
			
			PhotoDto photoDto = new PhotoDto();
			photoDto.setFileName(fileName);
			photoDto.setDescription(description);

			PhotoDao photoDao = new PhotoDao();
			photoDao.updatePhoto(photoDto);

			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("/WEB-INF/instahairgram/instragram.jsp");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}

}
