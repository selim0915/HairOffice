package kr.or.bit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.ProfileDao;
import kr.or.bit.dao.UsersDao;
import kr.or.bit.dto.ProfileDto;
import kr.or.bit.dto.UsersDto;
import kr.or.bit.utils.TeamConvert;

public class SignUpOkService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();

		try {

			String id = request.getParameter("id");
			String pwd = request.getParameter("pass");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String introduction = request.getParameter("introduction");
			String website = request.getParameter("website");
			String photoname = "defaultphoto.png";
			String gender = request.getParameter("gender");

			String usertype = request.getParameter("usertype");
			String termsOfUseYn = request.getParameter("termsOfUseYn");
			String loginYn = request.getParameter("loginYn");

			UsersDto userDto = new UsersDto();

			userDto.setUserId(id);
			userDto.setPasswords(pwd);
			userDto.setUserName(name);
			userDto.setEmail(email);
			userDto.setPhone(phone);
			userDto.setGender(gender);
			userDto.setUserType(usertype);
			userDto.setTermsOfUseYn(TeamConvert.checkToYn(termsOfUseYn));
			userDto.setLoginYn(TeamConvert.checkToYn(loginYn));

			UsersDao userDao = new UsersDao();
			userDao.insertUsers(userDto);

			ProfileDto profileDto = new ProfileDto();

			profileDto.setUserId(id);
			profileDto.setIntroduction(introduction);
			profileDto.setWebsite(website);
			profileDto.setPhotoName(photoname);

			ProfileDao profileDao = new ProfileDao();
			profileDao.insertProfile(profileDto);

			forward.setRedirect(false);
			forward.setPath("index.jsp");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}

}
