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
import kr.or.bit.dto.LikesDto;
import kr.or.bit.dto.PhotoDto;

public class CommentsAllSeslectService implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		int photoid = Integer.parseInt(request.getParameter("photoid"));
		
		try {
			System.out.println("CommentsAllSeslectService 진입");
			
			CommentsDao commentsDao = new CommentsDao();
			List<CommentsDto> commentslist = commentsDao.selectCommentsAllList(photoid);
			request.setAttribute("commentslist", commentslist);
			System.out.println("commentslist: "+commentslist);
			
			PhotoDao photoDao = new PhotoDao();
			PhotoDto photolist = photoDao.selectPhotoById(photoid);
			request.setAttribute("photolist", photolist);
			
			System.out.println("photolist : " + photolist);
			
			LikesDao likesdao = new LikesDao();
			int likecount = likesdao.getLikeNumberByPhotoId(photoid);
			request.setAttribute("likecount", likecount);
			System.out.println(likecount);
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/insta/instapopup.jsp");
			
		} catch (Exception e) {	
			e.printStackTrace();
		}
		return forward;
	}
	
}
