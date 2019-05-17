package kr.or.bit.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.CommentsDao;
import kr.or.bit.dao.LikesDao;
import kr.or.bit.dao.PhotoDao;
import kr.or.bit.dao.ProfileDao;
import kr.or.bit.dao.UsersDao;
import kr.or.bit.dto.CommentsDto;
import kr.or.bit.dto.LikesDto;
import kr.or.bit.dto.PhotoDto;
import kr.or.bit.dto.ProfileDto;
import kr.or.bit.dto.UsersDto;

public class CommentsAllSeslectService implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		int photoid = Integer.parseInt(request.getParameter("photoid"));
		
		HttpSession session = request.getSession();
		UsersDto usersDto = (UsersDto) session.getAttribute("usersdto");
		String userid = usersDto.getUserId();
		
		try {
			
			CommentsDao commentsDao = new CommentsDao();
			List<CommentsDto> commentslist = commentsDao.selectCommentsAllList(photoid);
			request.setAttribute("commentslist", commentslist);
			
			PhotoDao photoDao = new PhotoDao();
			PhotoDto photolist = photoDao.selectPhotoById(photoid);
			request.setAttribute("photolist", photolist);
			
			ProfileDao profiledao = new ProfileDao();
			ProfileDto profiledto = profiledao.getProfilebyId(userid);
			request.setAttribute("profilelist", profiledto);
			
			LikesDao likesdao = new LikesDao();
			int likecount = likesdao.getLikeNumberByPhotoId(photoid);
			request.setAttribute("likecount", likecount);

			forward.setRedirect(false);
			forward.setPath("/WEB-INF/insta/instapopup.jsp");
			
		} catch (Exception e) {	
			e.printStackTrace();
		}
		return forward;
	}
	
}
