package kr.or.bit.service;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.PhotoDao;
import kr.or.bit.dao.QnADao;
import kr.or.bit.dto.PhotoDto;
import kr.or.bit.dto.QnADto;
import kr.or.bit.dto.UsersDto;

public class InstaPhotoWriteService implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		
		String uploadpath = request.getSession().getServletContext().getRealPath("upload");
		
		int size = 1024*1024*10;	//10M 네이버 계산기
		int result;
		
		MultipartRequest multi;
		try {
			PhotoDao dao = new PhotoDao();
			multi = new MultipartRequest(request,uploadpath, size, "UTF-8", new DefaultFileRenamePolicy());
			Enumeration filenames = multi.getFileNames();
			
			HttpSession session = request.getSession();
			UsersDto usersDto = (UsersDto) session.getAttribute("usersdto");
			
			String description = multi.getParameter("comments"); 
			String userid = usersDto.getUserId();
			
			PhotoDto photodto = new PhotoDto();
			
			photodto.setDescription(description);
			photodto.setUserId(userid);
			
			
			String file = (String)filenames.nextElement();
			String filename1 = multi.getFilesystemName(file);
			photodto.setFileName(filename1);
						
			result = dao.insertPhoto(photodto);

			if (result > 0) {
				System.out.println("등록성공");
			} else { // -1 (제약, 컬럼길이 문제)
				System.out.println("등록실패");
			}
			

			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("Insta.insta"); //리스트
			
			} catch (Exception e) {
			e.printStackTrace();
			}

		return forward;
	}
}
