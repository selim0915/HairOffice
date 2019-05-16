package kr.or.bit.service;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.LikesDao;
import kr.or.bit.dto.LikesDto;

public class InstaLikeService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
        JSONObject jsonObject = new JSONObject();
		
		try {
			int photoid = Integer.parseInt(request.getParameter("photoid"));
			String userid = request.getParameter("userid");
			String likeyn = request.getParameter("likeyn");
			String wasuser = request.getParameter("wasuser");

			LikesDto LikesDto = new LikesDto();
			
			LikesDto.setPhotoId(photoid);
			LikesDto.setUserId(userid);
			LikesDto.setLikeYn(likeyn);
			LikesDto.setWasUser(wasuser);
			
			LikesDao LikesDao = new LikesDao();
			LikesDao.insertlikes(LikesDto);
			
			LikesDto likeslist = LikesDao.getLikesListByPhotoidUserid(photoid, userid);
			HttpSession session = request.getSession(); 
			session.setAttribute("likesdto", likeslist);
				
  		        //deptno의 한명 정보가 들어갈 JSONObject 선언
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