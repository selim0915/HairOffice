package kr.or.bit.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.CommentsDao;
import kr.or.bit.dao.LikesDao;
import kr.or.bit.dao.PhotoDao;
import kr.or.bit.dto.CommentsDto;
import kr.or.bit.dto.LikesDto;
import kr.or.bit.dto.PhotoDto;

public class CommentsInsertService implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		try {
			String comments = request.getParameter("comments");
			int photoid = Integer.parseInt(request.getParameter("photoid"));
			String userid = request.getParameter("userid");

			CommentsDto commentsdto = new CommentsDto();
			
			commentsdto.setComments(comments);
			commentsdto.setPhotoId(photoid);
			commentsdto.setUserId(userid);
			
			CommentsDao CommentsDao = new CommentsDao();
			CommentsDao.insertComments(commentsdto);
			
			CommentsDao commentsDao = new CommentsDao();
			List<CommentsDto> commentslist = commentsDao.selectCommentsAllList(photoid);
			request.setAttribute("commentslist", commentslist);
			
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/json/commentsList.jsp");

		} catch (Exception e) {	
			e.printStackTrace();
		}
		return forward;
	}
	
}
