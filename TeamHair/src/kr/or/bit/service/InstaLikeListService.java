package kr.or.bit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.LikesDao;
import kr.or.bit.dto.LikesDto;

public class InstaLikeListService implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = new ActionForward();
        JSONObject jsonObject = new JSONObject();
		
		try {
			int photoid = Integer.parseInt(request.getParameter("photoid"));
			String userid = request.getParameter("userid");

			LikesDao likesdao = new LikesDao();
			
			LikesDto likeslist = likesdao.getLikesListByPhotoidUserid(photoid, userid);
			
  		        //deptno의 한명 정보가 들어갈 JSONObject 선언
			jsonObject = new JSONObject();
			jsonObject.put("photoid", likeslist.getPhotoId());
			jsonObject.put("userid", likeslist.getUserId());
			jsonObject.put("likeyn", likeslist.getLikeYn());
			jsonObject.put("wasuser", likeslist.getWasUser());
  		        
   		 	request.setAttribute("likeslist", jsonObject);
		  		  
 		  	 forward = new ActionForward();
 		  	 
 			 forward.setRedirect(false); 
 			 forward.setPath("/WEB-INF/json/LikesList.jsp");

		} catch (Exception e) {	
			e.printStackTrace();
		}
		return forward;
	}
}
