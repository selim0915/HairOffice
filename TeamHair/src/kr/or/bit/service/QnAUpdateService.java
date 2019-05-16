package kr.or.bit.service;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.QnADao;
import kr.or.bit.dto.QnADto;

public class QnAUpdateService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		String uploadpath = request.getSession().getServletContext().getRealPath("upload");
		
		
		int size = 1024*1024*50;	//10M 네이버 계산기
		int result;
		
		MultipartRequest multi;
		try {
			QnADao dao = new QnADao();
			multi = new MultipartRequest(request,uploadpath, size, "UTF-8", new DefaultFileRenamePolicy());
			Enumeration filenames = multi.getFileNames();
			
			
			
			int boardID = Integer.parseInt(multi.getParameter("boardid"));

			String userid = multi.getParameter("userid");
			String boardSubject = multi.getParameter("boardsubject"); 
			String boardContent = multi.getParameter("boardcontent");
			String fileName = multi.getParameter("filename");
			int cpage = Integer.parseInt(multi.getParameter("cpage"));
			
			QnADto qna = new QnADto();
			
			qna.setBoardID(boardID);
			qna.setUserID(userid);
			qna.setBoardSubject(boardSubject);
			qna.setBoardContent(boardContent);
			qna.setFileName(fileName);
			

			
			String file = (String)filenames.nextElement();
			String filename = multi.getFilesystemName(file);
			qna.setFileName(filename);
						
			result = dao.updateQnA(qna);

			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/QnA.do"); //리스트
			
			} catch (Exception e) {
			e.printStackTrace();
			}

		return forward;
	}

}
