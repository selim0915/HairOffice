package kr.or.bit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.CommentsDao;
import kr.or.bit.dao.LikesDao;
import kr.or.bit.dao.PhotoDao;
import kr.or.bit.dao.QnADao;
import kr.or.bit.dto.UsersDto;

public class InstaPhotoDeleteService implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		int photoid = Integer.parseInt(request.getParameter("photoid"));
		
		int result = 0;
		
		try {
			PhotoDao dao = new PhotoDao();
			
			LikesDao likesdao = new LikesDao();
			result = likesdao.deleteLikes(photoid);
			
			CommentsDao commentsdao = new CommentsDao();
			result = commentsdao.deleteComments(photoid);	
			
			result = dao.deletePhoto(photoid);
			
			if (result > 0) {
				System.out.println("글 삭제 성공");
			} else { // -1 (제약, 컬럼길이 문제)
				System.out.println("글 삭제 실패");
			}
			

			forward = new ActionForward(); 
			forward.setRedirect(false);
			forward.setPath("Insta.insta"); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return forward;
	}
}
