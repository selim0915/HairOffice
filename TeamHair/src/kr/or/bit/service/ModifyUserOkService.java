package kr.or.bit.service;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.ProfileDao;
import kr.or.bit.dao.UsersDao;
import kr.or.bit.dto.ProfileDto;
import kr.or.bit.dto.UsersDto;
import kr.or.bit.utils.TeamConvert;

public class ModifyUserOkService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		ActionForward forward = new ActionForward();

		String uploadpath = request.getSession().getServletContext().getRealPath("upload");

		int size = 1024 * 1024 * 10; // 10M 네이버 계산기
		int result;

		MultipartRequest multi;

		try {

			multi = new MultipartRequest(request, uploadpath, size, "UTF-8", new DefaultFileRenamePolicy());
			Enumeration filenames = multi.getFileNames();

			HttpSession session = request.getSession();

			UsersDto usersDto = (UsersDto) session.getAttribute("usersdto");

			String id = usersDto.getUserId();

			String pwd = multi.getParameter("pass");
			String name = multi.getParameter("name");
			String email = multi.getParameter("email");
			String phone = multi.getParameter("phone");
			String introduction = multi.getParameter("introduction");
			String website = multi.getParameter("website");
			String gender = multi.getParameter("gender");

			String usertype = multi.getParameter("usertype");
			String loginYn = multi.getParameter("loginYn");
			String reserveYn = multi.getParameter("reserveYn");
			String useSnsYn = multi.getParameter("useSnsYn");

			UsersDto userDto = new UsersDto();

			userDto.setUserId(id);
			userDto.setPasswords(pwd);
			userDto.setUserName(name);
			userDto.setEmail(email);
			userDto.setPhone(phone);
			userDto.setGender(gender);
			;
			userDto.setUserType(usertype.trim());
			userDto.setLoginYn(TeamConvert.checkToYn(loginYn));
			userDto.setReserveYn(TeamConvert.checkToYn(reserveYn));
			userDto.setUseSnsYn(TeamConvert.checkToYn(useSnsYn));

			UsersDao userDao = new UsersDao();
			userDao.updateUsers(userDto);

			ProfileDto profileDto = new ProfileDto();

			profileDto.setUserId(id);
			profileDto.setIntroduction(introduction);
			profileDto.setWebsite(website);

			String file = (String) filenames.nextElement();
			String filename1 = multi.getFilesystemName(file);

			profileDto.setPhotoName(filename1);

			ProfileDao profileDao = new ProfileDao();

			profileDao.deleteProfile(profileDto.getUserId());
			profileDao.insertProfile(profileDto);

			forward.setRedirect(false);
			forward.setPath("index.jsp");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}
}
