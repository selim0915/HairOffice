package kr.or.bit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.CommentsDao;
import kr.or.bit.dao.FollowingFollowerDao;
import kr.or.bit.dao.LikesDao;
import kr.or.bit.dao.PhotoDao;
import kr.or.bit.dto.FollowerDto;
import kr.or.bit.dto.FollowingDto;

public class InstaFollowingService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;

		String followingid = request.getParameter("userid");
		String userid = request.getParameter("sessionid");

		int result = 0;

		try {
			FollowingFollowerDao ffdao = new FollowingFollowerDao();

			FollowingDto fdto = new FollowingDto();

			fdto.setFollowingId(followingid);
			fdto.setUserId(userid);

			result = ffdao.deleteFollowingFollower(fdto);

			if (result > 0) {
				System.out.println("글 삭제 성공");
			} else { // -1 (제약, 컬럼길이 문제)
				System.out.println("글 삭제 실패");
			}

			forward = new ActionForward();

			forward.setRedirect(false);
			forward.setPath("Instauserid.insta?userid=" + followingid);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return forward;
	}

}
