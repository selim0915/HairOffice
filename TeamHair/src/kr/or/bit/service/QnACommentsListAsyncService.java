package kr.or.bit.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.QnADao;
import kr.or.bit.dto.QnACommentsDto;

public class QnACommentsListAsyncService implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
        JSONObject jsonObject = new JSONObject();
        //deptno의 JSON정보를 담을 Array 선언	
        JSONArray commentslist = new JSONArray();
		int boardid = Integer.parseInt(request.getParameter("boardid"));
		
		try {
  			QnADao qnadao = new QnADao();
  		  	List<QnACommentsDto> emplist = qnadao.QnACommentslist(boardid);

  		  for(int i = 0; i < emplist.size(); i++) {
				QnACommentsDto e = emplist.get(i);
		        //deptno의 한명 정보가 들어갈 JSONObject 선언
		        JSONObject commentsinfo = new JSONObject();
		        commentsinfo.put("commentsID", e.getCommentID());
		        commentsinfo.put("comments", e.getComments());
		        commentsinfo.put("boardID", e.getBoardID());
		        commentsinfo.put("createDate", e.getCreateDate().toString());
		        commentsinfo.put("updateDate", e.getUpdateDate().toString());
		        commentsinfo.put("userID",e.getUserID());
		        
		        commentslist.add(commentsinfo);
			}
			jsonObject.put("commentslist", commentslist);

			
  		  	
  		  	 request.setAttribute("commentslist", jsonObject);
		  		  
  		  	 forward = new ActionForward();
  			 forward.setRedirect(false); //forward 방식
  			 forward.setPath("/WEB-INF/QnA/QnAcommentsListAsync.jsp");

		  	}catch(Exception e){
		  		System.out.println(e.getMessage());
		  	}
		return forward;
	}
}
