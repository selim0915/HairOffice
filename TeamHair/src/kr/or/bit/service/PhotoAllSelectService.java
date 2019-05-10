package kr.or.bit.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.PhotoDao;
import kr.or.bit.dto.PhotoDto;
import kr.or.bit.dto.UsersDto;

public class PhotoAllSelectService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		try {
			System.out.println("PhotoBlogSelectService 진입");
			
			HttpSession session = request.getSession();
			UsersDto userDto = (UsersDto) session.getAttribute("usersdto");
			System.out.println("userDto: "+userDto);
			
			PhotoDao photoDao = new PhotoDao();
			List<PhotoDto> bloglist = photoDao.selectPhotoAllList(userDto.getUserId());
			request.setAttribute("bloglist", bloglist);
			System.out.println("bloglist: "+bloglist);
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/instahairgram/instragram.jsp");
			
		} catch (Exception e) {	
			e.printStackTrace();
		}
		return forward;
	}

}
