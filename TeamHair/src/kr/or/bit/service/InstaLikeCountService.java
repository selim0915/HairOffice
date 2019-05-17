package kr.or.bit.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.CommentsDao;
import kr.or.bit.dao.LikesDao;
import kr.or.bit.dao.PhotoDao;
import kr.or.bit.dto.CommentsDto;
import kr.or.bit.dto.PhotoDto;

public class InstaLikeCountService implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();

		int photoid = Integer.parseInt(request.getParameter("photoid"));

		try {

			LikesDao likesdao = new LikesDao();

			int likecount = likesdao.getLikeNumberByPhotoId(photoid);

			request.setAttribute("likecount", likecount);

			forward.setRedirect(false);
			forward.setPath("/WEB-INF/json/instalikecount.jsp");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}
}
