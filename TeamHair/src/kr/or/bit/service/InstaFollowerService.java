package kr.or.bit.service;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.FollowingFollowerDao;
import kr.or.bit.dto.FollowerDto;

public class InstaFollowerService implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		String userid = request.getParameter("userid");
		String followerid = request.getParameter("sessionid");
		
		int result = 0;
		
		System.out.println("아이디 값 2개 들어옴 : " + userid + followerid);
		
		try {
			
			FollowingFollowerDao ffdao = new FollowingFollowerDao();
			FollowerDto fdto = new FollowerDto();
			fdto.setFollowerId(userid);
			fdto.setUserId(followerid);
			
			result = ffdao.addFollowingFollower(fdto);
			
			if (result > 0) {
				System.out.println("등록성공");
			} else { // -1 (제약, 컬럼길이 문제)
				System.out.println("등록실패");
			}
			 
			

			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("Instauserid.insta?userid=" + userid); //리스트
			
			} catch (Exception e) {
			e.printStackTrace();
			}

		return forward;
	}
}
