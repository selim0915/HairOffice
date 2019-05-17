package kr.or.bit.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.LikesDao;
import kr.or.bit.dto.LikePhotoListDto;

public class InstaLikePhotoListService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();

		try {

			LikesDao Likesdao = new LikesDao();
			List<LikePhotoListDto> likephotolistdto = Likesdao.getLikePhotoList();
			request.setAttribute("likephotolistdto", likephotolistdto);

			forward.setRedirect(false);
			forward.setPath("/WEB-INF/insta/like_hair_list.jsp");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}

}
