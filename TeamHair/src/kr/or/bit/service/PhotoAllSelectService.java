package kr.or.bit.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.FollowingFollowerDao;
import kr.or.bit.dao.LikesDao;
import kr.or.bit.dao.PhotoDao;
import kr.or.bit.dao.ProfileDao;
import kr.or.bit.dto.FollowingFollowerListDto;
import kr.or.bit.dto.LikeListDto;
import kr.or.bit.dto.PhotoDto;
import kr.or.bit.dto.ProfileDto;
import kr.or.bit.dto.UsersDto;

public class PhotoAllSelectService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();

		try {
			
			HttpSession session = request.getSession();
			UsersDto userDto = (UsersDto) session.getAttribute("usersdto");
			String userid = userDto.getUserId();

			PhotoDao photoDao = new PhotoDao();
			List<PhotoDto> bloglist = photoDao.selectPhotoAllList(userDto.getUserId());
			request.setAttribute("bloglist", bloglist);

			LikesDao photolikeDao = new LikesDao();
			List<LikeListDto> bloglikedto = photolikeDao.getLikeNumberListByUserId(userid);
			request.setAttribute("bloglikedto", bloglikedto);

			ProfileDao profileDao = new ProfileDao();
			ProfileDto profileDto = profileDao.getProfilebyId(userDto.getUserId());
			request.setAttribute("profiledto", profileDto);

			int followerNumber = 0;
			int followingNumber = 0;
			List<FollowingFollowerListDto> followerList = new ArrayList<FollowingFollowerListDto>();
			List<FollowingFollowerListDto> followingList = new ArrayList<FollowingFollowerListDto>();

			FollowingFollowerDao followingfollowerDao = new FollowingFollowerDao();
			followerNumber = followingfollowerDao.getFollowerNumberByUserId(userDto.getUserId());
			followingNumber = followingfollowerDao.getFollowingNumberByUserId(userDto.getUserId());

			followerList = followingfollowerDao.getFollowerUserList(userDto.getUserId());
			followingList = followingfollowerDao.getFollowingUserList(userDto.getUserId());

			request.setAttribute("followerNumber", followerNumber);
			request.setAttribute("followingNumber", followingNumber);
			request.setAttribute("followerList", followerList);
			request.setAttribute("followingList", followingList);

			forward.setRedirect(false);
			forward.setPath("/WEB-INF/insta/instragram.jsp");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}

}
