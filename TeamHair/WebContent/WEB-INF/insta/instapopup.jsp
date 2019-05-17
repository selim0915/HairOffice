<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	
<c:set var="photolist" value="${requestScope.photolist}" />
<c:set var="commentslist" value="${requestScope.commentslist}" />
<c:set var="likecount" value="${requestScope.likecount }" />

<fmt:formatDate var="photodate" value="${photolist.updateDate}" pattern="yyyy년MM월dd일"/>

<div class="popup animationClose">
	<div class="col-lg-8 col-md-7 col-xs-7 col-sm-7 imgsession">
		<img src="./upload/${photolist.fileName }" alt="프로필 업로드 사진">
	</div>

	<div class="col-lg-4 col-md-5 col-xs-5 col-sm-5 contentsession">
		<div class="insta-right">
			<div class="instagram-card">
				<div class="">
					<a class="btnclose" href="#" onclick="btnclose()"><i
						class="fas fa-times"></i></a>
				</div>
				<div class="instagram-card-header">
					<img class="instagram-card-user-image rounded-circle"
						src="img/signup.jpg"> <a class="instagram-card-user-name"
						href="https://www.instagram.com/pictoline/" target="_blank">${sessionScope.usersdto.userId }</a>
					<div class="instagram-card-content">
						<a class="reservepopup" href="GangnamBranchInfo.brh"><span>ㆍ예약하기</span></a>
					</div>
					<div class="btn-delete">
						<a href="#" onclick="deletephoto(${photolist.photoId });return false;"><i class="fas fa-trash"></i></a>
					</div>
				</div>
				

				<div class="instagram-card-content">

					<p>
						<a class="user-comments"
							href="https://www.instagram.com/p/BeTEpqiFku8/?taken-by=pictoline">${sessionScope.usersdto.userId }
						</a>${photolist.description}
					</p>
					<p class="comments">${photodate }</p>
					<div class="comment-append" id="comment-append">
						<c:forEach var="i" begin="0" end="${commentslist.size()}">
						<fmt:formatDate var="commentsdate" value="${commentslist[i].updateDate}" pattern="yyyy년MM월dd일"/>
							<p>
								<a class="user-comments" href="#">${commentslist[i].userId}</a>
								${commentslist[i].comments}
							</p>
							<p class="comments">${commentsdate }</p>
						</c:forEach>
					</div>
				</div>
				<a class="instagram-card-icon" href="#"
					onclick="like(${photolist.photoId });return false;"><i
					id="test" class="far fa-heart"></i></a> <a class="instagram-card-icon"
					href="#" onclick="commentfocus();return false;"><i
					class="far fa-comment"></i></a>


				<p id="likecount">
					<a class="user-comments" href="#">${likecount } 좋아요</a>
				</p>
				<p class="date">7일 전</p>
				<hr />

				<div class="instagram-card-footer">
					<input class="instagram-card-input" type="text"
						placeholder="댓글 달기..."> <a class="instagram-card-icon"
						href="#" onclick="comments(${photolist.photoId});return false;"><i
						class="fas fa-pencil-alt"></i></a>
				</div>
			</div>
		</div>

	</div>
</div>
