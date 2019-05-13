package kr.or.bit.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.QnADao;
import kr.or.bit.dto.QnACommentsDto;
import kr.or.bit.dto.QnADto;

public class QnAdetailservice implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;		
		
		try {
			int boardID = Integer.parseInt(request.getParameter("boardID"));
			
  		  	QnADao qnaDao = new QnADao();
  		  	QnADto qnaDto = qnaDao.searchQnA(boardID);
  		  	List<QnACommentsDto> commentDtoList = qnaDao.QnACommentslist(boardID);
  		  	
  		  	request.setAttribute("detail",qnaDto);
  		  	request.setAttribute("comment", commentDtoList);
		  		  
  		  	int readCount = qnaDto.getReadCount()+1;
  		  	qnaDto.setReadCount(readCount);
  		  	qnaDao.updateQnA(qnaDto);
  		  	
  		  	System.out.println("servicce 디테일 q의 값 : " + qnaDto);
  		  	System.out.println("디테일service c값 : " + commentDtoList);
  		  	
  		  	forward = new ActionForward();
  			forward.setRedirect(false); //forward 방식
  			forward.setPath("/WEB-INF/QnA/QnAdetail.jsp");

		  	}catch(Exception e){
		  		System.out.println(e.getMessage());
		  	}

		return forward;
	}

}
