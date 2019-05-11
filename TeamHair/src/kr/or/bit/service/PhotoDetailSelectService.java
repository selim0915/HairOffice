package kr.or.bit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.PhotoDao;
import kr.or.bit.dto.PhotoDto;
import kr.or.bit.dto.UsersDto;

public class PhotoDetailSelectService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		//String photoId = request.getParameter("photoId");
		//System.out.println("photoId: "+photoId);
		
		try {
			System.out.println("PhotoDetailSelectService.java 진입");
			
			HttpSession session = request.getSession();
			UsersDto userDto = (UsersDto) session.getAttribute("usersdto");
			System.out.println("userDto: "+userDto);
			
			int photoId = Integer.parseInt(request.getParameter("photoId"));
			System.out.println("photoId: "+photoId);
			
			PhotoDao photoDao = new PhotoDao();
			PhotoDto photoDto = photoDao.selectPhotoById(userDto.getUserId(), photoId);
			session.setAttribute("photoDto", photoDto);
			System.out.println("photoDto: "+photoDto);
			
			forward.setRedirect(true);
			forward.setPath("InstaBlog.insta");
			
		} catch (Exception e) {	
			e.printStackTrace();
		}
		return forward;
	}

}
