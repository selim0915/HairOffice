package kr.or.bit.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.LikesDao;
import kr.or.bit.dto.LikesDto;

public class ModifyLikeService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
        JSONObject jsonObject = new JSONObject();
	
	try {
		
		String likesyn = request.getParameter("likeyn");

		int photoid = Integer.parseInt(request.getParameter("photoid"));
		String userid = request.getParameter("userid");
		
		LikesDto likeDto = new LikesDto();
		
		likeDto.setPhotoId(photoid);
		likeDto.setUserId(userid);
		likeDto.setLikeYn(likesyn);
			
		LikesDao likesdao = new LikesDao();
		likesdao.updateLikes(likeDto);
		
		LikesDto likeslist = likesdao.getLikesListByPhotoidUserid(photoid, userid);
		HttpSession session = request.getSession(); 
		session.setAttribute("likesdto", likeslist);
			
		jsonObject = new JSONObject();
		jsonObject.put("photoid", likeslist.getPhotoId());
		jsonObject.put("userid", likeslist.getUserId());
		jsonObject.put("likeyn", likeslist.getLikeYn());
		jsonObject.put("wasuser", likeslist.getWasUser());
		        
	 	request.setAttribute("likeslist", jsonObject);
  		  
	  	 forward = new ActionForward();
		 forward.setRedirect(false); //forward 방식
		 forward.setPath("/WEB-INF/json/LikesList.jsp");
	} catch (Exception e) {	
		e.printStackTrace();
	}
	return forward;
}

}
