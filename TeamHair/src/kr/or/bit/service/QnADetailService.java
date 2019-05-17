package kr.or.bit.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.QnADao;
import kr.or.bit.dto.QnACommentsDto;
import kr.or.bit.dto.QnADto;

public class QnADetailService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;		
		
		try {
			int boardID = Integer.parseInt(request.getParameter("boardID"));
			int cpage =Integer.parseInt(request.getParameter("cpage"));
			
  		  	QnADao qnadao = new QnADao();
  		  	QnADto q = qnadao.searchQnA(boardID);
  		  	List<QnACommentsDto> c = qnadao.QnACommentslist(boardID);
  		  	
  		  	request.setAttribute("detail",q);
  		  	request.setAttribute("cpage", cpage);
  		  	request.setAttribute("comment", c);
		  		  
  		  	int readCount = q.getReadCount()+1;
  		  	q.setReadCount(readCount);
  		  	qnadao.updateQnA(q);
  		  	 		  	
  		  	forward = new ActionForward();
  			forward.setRedirect(false); //forward 방식
  			forward.setPath("/WEB-INF/QnA/QnAdetail.jsp");

		  	}catch(Exception e){
		  		System.out.println(e.getMessage());
		  	}

		return forward;
	}

}
