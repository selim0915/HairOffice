package kr.or.bit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.PhotoDao;
import kr.or.bit.dto.PhotoDto;
import kr.or.bit.dto.UsersDto;

public class PhotoInsertService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		System.out.println("PhotoInsertService 진입");

		HttpSession session = request.getSession();
		UsersDto userDto = (UsersDto) session.getAttribute("usersdto");
		System.out.println("userDto: " + userDto);

		String fileName = request.getParameter("fileName");
		String description = request.getParameter("description");

		try {
			request.setCharacterEncoding("UTF-8");

			PhotoDto photoDto = new PhotoDto();
			photoDto.setFileName(fileName);
			photoDto.setDescription(description);
			photoDto.setUserId(userDto.getUserId());
			System.out.println("photoDto: " + photoDto.toString());

			PhotoDao photoDao = new PhotoDao();
			photoDao.insertPhoto(photoDto);
			request.setAttribute("photoDto", photoDto);

			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("InstaBlog.insta"); // 리스트

		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}

}
