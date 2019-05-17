package kr.or.bit.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.CommentsDao;
import kr.or.bit.dao.LikesDao;
import kr.or.bit.dao.PhotoDao;
import kr.or.bit.dao.ProfileDao;
import kr.or.bit.dao.UsersDao;
import kr.or.bit.dto.CommentsDto;
import kr.or.bit.dto.PhotoDto;
import kr.or.bit.dto.ProfileDto;
import kr.or.bit.dto.UsersDto;

public class InstaPopupAllSeslectService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();

		int photoid = Integer.parseInt(request.getParameter("photoid"));
		String userid = request.getParameter("userid");
		try {

			UsersDao usersdao = new UsersDao();
			UsersDto usersdto = usersdao.getUserbyId(userid);
			request.setAttribute("userid", usersdto);

			ProfileDao profiledao = new ProfileDao();
			ProfileDto profiledto = profiledao.getProfilebyId(userid);
			request.setAttribute("profilelist", profiledto);

			CommentsDao commentsDao = new CommentsDao();
			List<CommentsDto> commentslist = commentsDao.selectCommentsAllList(photoid);
			request.setAttribute("commentslist", commentslist);

			PhotoDao photoDao = new PhotoDao();
			PhotoDto photolist = photoDao.selectPhotoById(photoid);
			request.setAttribute("photolist", photolist);

			LikesDao likesdao = new LikesDao();
			int likecount = likesdao.getLikeNumberByPhotoId(photoid);
			request.setAttribute("likecount", likecount);

			forward.setRedirect(false);
			forward.setPath("/WEB-INF/insta/instapopup_Id.jsp");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}

}
