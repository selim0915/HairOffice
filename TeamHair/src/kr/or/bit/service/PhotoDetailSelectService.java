package kr.or.bit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.PhotoDao;
import kr.or.bit.dto.UsersDto;

public class PhotoDetailSelectService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		try {
			System.out.println("PhotoDetailSelectService.java 진입");
			
			HttpSession session = request.getSession();
			UsersDto userDto = (UsersDto) session.getAttribute("usersdto");
			System.out.println("userDto: "+userDto);
			
			int photoId = Integer.parseInt(request.getParameter("photoId"));
			
			PhotoDao photoDao = new PhotoDao();
			photoDao.selectPhotoById(userDto.getUserId(), photoId);
			
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("/WEB-INF/instahairgram/instragram.jsp");
			
		} catch (Exception e) {	
			e.printStackTrace();
		}
		return forward;
	}

}
