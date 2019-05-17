
DROP SEQUENCE ID_SEQ;



DROP SEQUENCE BOARD_ID_SEQ;



DROP SEQUENCE COMMENT_SEQ;



DROP TABLE Likes CASCADE CONSTRAINTS PURGE;



DROP TABLE Comments CASCADE CONSTRAINTS PURGE;



DROP TABLE Follower CASCADE CONSTRAINTS PURGE;



DROP TABLE Following CASCADE CONSTRAINTS PURGE;



DROP TABLE Profile CASCADE CONSTRAINTS PURGE;



DROP TABLE Reservation CASCADE CONSTRAINTS PURGE;



DROP TABLE Photo CASCADE CONSTRAINTS PURGE;



DROP TABLE Tags CASCADE CONSTRAINTS PURGE;



DROP TABLE RentalHistory CASCADE CONSTRAINTS PURGE;



DROP TABLE RentContract CASCADE CONSTRAINTS PURGE;



DROP TABLE Space CASCADE CONSTRAINTS PURGE;



DROP TABLE Branch CASCADE CONSTRAINTS PURGE;



DROP TABLE Code CASCADE CONSTRAINTS PURGE;



DROP TABLE QnAComments CASCADE CONSTRAINTS PURGE;



DROP TABLE QnA CASCADE CONSTRAINTS PURGE;



DROP TABLE Users CASCADE CONSTRAINTS PURGE;



CREATE TABLE Branch
(
	BranchID              NUMBER  NOT NULL ,
	BranchName            VARCHAR2(100)  NULL ,
	Address1              VARCHAR2(200)  NULL ,
  Address2              VARCHAR2(200)  NULL ,
	PostalCode            VARCHAR2(5)  NULL ,
	Phone                 VARCHAR2(20)  NULL ,
	Map_X                 NUMBER  NULL ,
	Map_Y                 NUMBER  NULL 
);



CREATE UNIQUE INDEX XPKBranch ON Branch
(BranchID  ASC);



ALTER TABLE Branch
	ADD CONSTRAINT  XPKBranch PRIMARY KEY (BranchID);



CREATE TABLE Code
(
	ClassCode             VARCHAR2(10)  NULL ,
	ClassName             VARCHAR2(50)  NULL ,
	Code                  VARCHAR2(10)  NULL ,
	CodeName              VARCHAR2(50)  NULL ,
	Orders                NUMBER  NULL 
);



CREATE TABLE Comments
(
	CommentID             INTEGER  NOT NULL ,
	Comments              VARCHAR2(300)  NULL ,
	CreateDate            DATE  NULL ,
	UpdateDate            DATE  NULL ,
	wasUser               VARCHAR2(1)  NULL ,
  PhotoID               NUMBER  NOT NULL ,
	UserID                VARCHAR2(50)  NOT NULL 
);



CREATE UNIQUE INDEX XPKComments ON Comments
(CommentID  ASC);



ALTER TABLE Comments
	ADD CONSTRAINT  XPKComments PRIMARY KEY (CommentID);



CREATE TABLE Follower
(
	FollowerID            VARCHAR2(20)  NOT NULL ,
	UserID                VARCHAR2(50)  NOT NULL 
);



CREATE UNIQUE INDEX XPKFollower ON Follower
(FollowerID  ASC,UserID  ASC);



ALTER TABLE Follower
	ADD CONSTRAINT  XPKFollower PRIMARY KEY (FollowerID,UserID);



CREATE TABLE Following
(
	FollowingID           VARCHAR2(20)  NOT NULL ,
	UserID                VARCHAR2(50)  NOT NULL 
);



CREATE UNIQUE INDEX XPKFollowing ON Following
(FollowingID  ASC,UserID  ASC);



ALTER TABLE Following
	ADD CONSTRAINT  XPKFollowing PRIMARY KEY (FollowingID,UserID);



CREATE TABLE Likes
(
	PhotoID               NUMBER  NOT NULL ,
	UserID                VARCHAR2(50)  NOT NULL ,
	LikeYN                VARCHAR2(1)  NULL ,
	wasUser               VARCHAR2(1)  NULL 
);



CREATE UNIQUE INDEX XPKLikes ON Likes
(PhotoID  ASC,UserID  ASC);



ALTER TABLE Likes
	ADD CONSTRAINT  XPKLikes PRIMARY KEY (PhotoID,UserID);



CREATE TABLE Photo
(
	PhotoID               NUMBER  NOT NULL ,
	FileName              VARCHAR2(50)  NOT NULL ,
	Description           VARCHAR2(300)  NULL ,
	CreateDate            DATE  NULL ,
	UpdateDate            DATE  NULL ,
  UserID                VARCHAR2(50)  NOT NULL
);



CREATE UNIQUE INDEX XPKPhoto ON Photo
(PhotoID  ASC);



ALTER TABLE Photo
	ADD CONSTRAINT  XPKPhoto PRIMARY KEY (PhotoID);



CREATE TABLE Profile
(
	UserID                VARCHAR2(50)  NOT NULL ,
	Introduction          VARCHAR2(300)  NULL ,
	Website               VARCHAR2(100)  NULL ,
	PhotoName             VARCHAR2(50)  NULL 
);



CREATE UNIQUE INDEX XPKProfile ON Profile
(UserID  ASC);



ALTER TABLE Profile
	ADD CONSTRAINT  XPKProfile PRIMARY KEY (UserID);



CREATE TABLE QnA
(
	BoardID               NUMBER  NOT NULL ,
	BoardName             VARCHAR2(20)  NULL ,
	BoardSubject          VARCHAR2(50)  NULL ,
	BoardContent          VARCHAR2(2000)  NULL ,
	FileName              VARCHAR2(50)  NULL ,
	ReplyRef              NUMBER  NULL ,
	ReplyDepth            NUMBER  NULL ,
	ReplySeq              NUMBER  NULL ,
  ReadCount             NUMBER  NULL ,
	Notice                VARCHAR2(10)  NULL ,
	CreateDate            DATE  NULL ,
	UpdateDate            DATE  NULL ,
	UserID                VARCHAR2(50)  NOT NULL 
);



CREATE UNIQUE INDEX XPKQnA ON QnA
(BoardID  ASC);



ALTER TABLE QnA
	ADD CONSTRAINT  XPKQnA PRIMARY KEY (BoardID);



CREATE TABLE QnAComments
(
	CommentID             NUMBER  NOT NULL ,
	Comments              VARCHAR2(300)  NULL ,
	CreateDate            DATE  NULL ,
	UpdateDate            DATE  NULL ,
	BoardID               NUMBER  NOT NULL ,
	UserID                VARCHAR2(50)  NOT NULL 
);



CREATE UNIQUE INDEX XPKQnAComments ON QnAComments
(CommentID  ASC);



ALTER TABLE QnAComments
	ADD CONSTRAINT  XPKQnAComments PRIMARY KEY (CommentID);



CREATE TABLE RentalHistory
(
	UserID                VARCHAR2(50)  NOT NULL ,
	SpaceID               NUMBER  NOT NULL ,
	RentID                NUMBER  NOT NULL ,
	BaseDate              VARCHAR2(8)  NOT NULL ,
	RentalRevenue         NUMBER  NULL ,
	Discount              NUMBER  NULL ,
	PayMethod             VARCHAR2(4)  NULL 
);



CREATE UNIQUE INDEX XPKRentalHistory ON RentalHistory
(UserID  ASC,SpaceID  ASC,RentID  ASC,BaseDate  ASC);



ALTER TABLE RentalHistory
	ADD CONSTRAINT  XPKRentalHistory PRIMARY KEY (UserID,SpaceID,RentID,BaseDate);



CREATE TABLE RentContract
(
	UserID                VARCHAR2(50)  NOT NULL ,
	SpaceID               NUMBER  NOT NULL ,
  RentID                NUMBER  NOT NULL ,
	StartDate             DATE  NULL ,
	EndDate               DATE  NULL ,
  Deposit               NUMBER  NULL ,
	MonthlyRental         NUMBER  NULL ,
	DiscountAmount        NUMBER  NULL ,
	CreateDate            DATE  NULL ,
	UpdateDate            DATE  NULL ,
	PayMethod             VARCHAR2(4)  NULL 
);



CREATE UNIQUE INDEX XPKRentContract ON RentContract
(UserID  ASC,SpaceID  ASC,RentID  ASC);



ALTER TABLE RentContract
	ADD CONSTRAINT  XPKRentContract PRIMARY KEY (UserID,SpaceID,RentID);



CREATE TABLE Reservation
(
	ReserveID             NUMBER  NOT NULL ,
	ServiceType           VARCHAR2(3) NULL ,
	StartDateTime         TIMESTAMP  NULL ,
  EndDateTime           TIMESTAMP  NULL ,
  CancelYN              VARCHAR2(1)  NULL ,
	CreateDate            DATE  NULL ,
	UpdateDate            DATE  NULL ,
	UserID                VARCHAR2(50)  NOT NULL ,
	SpaceID               NUMBER  NOT NULL ,	 
  PhotoID               NUMBER  NOT NULL 
);



CREATE UNIQUE INDEX XPKReservation ON Reservation
(ReserveID  ASC);



ALTER TABLE Reservation
	ADD CONSTRAINT  XPKReservation PRIMARY KEY (ReserveID);



CREATE TABLE Space
(
	SpaceID               NUMBER  NOT NULL ,
  SpaceName             VARCHAR2(100)  NULL ,
	SpaceType             VARCHAR2(2)  NULL ,
	MinNumber             INTEGER  NULL ,
	MaxNumber             INTEGER  NULL ,
  BranchID              NUMBER  NOT NULL 
);



CREATE UNIQUE INDEX XPKSpace ON Space
(SpaceID  ASC);



ALTER TABLE Space
	ADD CONSTRAINT  XPKSpace PRIMARY KEY (SpaceID);



CREATE TABLE Tags
(
	TagID                 INTEGER  NOT NULL ,
	TagName               VARCHAR2(300)  NULL 
);



CREATE UNIQUE INDEX XPKTags ON Tags
(TagID  ASC);



ALTER TABLE Tags
	ADD CONSTRAINT  XPKTags PRIMARY KEY (TagID);



CREATE TABLE Users
(
	UserID                VARCHAR2(50)  NOT NULL ,
	Passwords             VARCHAR2(50)  NOT NULL ,
  Username              VARCHAR2(50)  NULL ,
	Email                 VARCHAR2(50)  NULL ,
	Phone                 VARCHAR2(50)  NULL ,
	Gender                VARCHAR2(1)  NOT NULL ,
  UserType              VARCHAR2(1)  NOT NULL ,
	TermsOfUseYN          VARCHAR2(1)  NULL ,
	LoginYN               VARCHAR2(1)  NULL ,
	ReserveYN             VARCHAR2(1)  NULL ,
	UseSnsYN              VARCHAR2(1)  NULL ,
	CreateDate            DATE  NULL ,
	UpdateDate            DATE  NULL ,
  isActive              VARCHAR2(1)  NULL 
);



CREATE UNIQUE INDEX XPKUsers ON Users
(UserID  ASC);



ALTER TABLE Users
	ADD CONSTRAINT  XPKUsers PRIMARY KEY (UserID);



ALTER TABLE Comments
	ADD (CONSTRAINT  R_4 FOREIGN KEY (PhotoID) REFERENCES Photo(PhotoID) ON DELETE CASCADE);



ALTER TABLE Comments
	ADD (CONSTRAINT  R_22 FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE CASCADE);



ALTER TABLE Follower
	ADD (CONSTRAINT  R_5 FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE CASCADE);



ALTER TABLE Following
	ADD (CONSTRAINT  R_6 FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE CASCADE);



ALTER TABLE Likes
	ADD (CONSTRAINT  R_3 FOREIGN KEY (PhotoID) REFERENCES Photo(PhotoID) ON DELETE CASCADE);



ALTER TABLE Likes
	ADD (CONSTRAINT  R_23 FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE CASCADE);



ALTER TABLE Photo
	ADD (CONSTRAINT  R_2 FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE CASCADE);



ALTER TABLE Profile
	ADD (CONSTRAINT  R_7 FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE CASCADE);



ALTER TABLE QnA
	ADD (CONSTRAINT  R_17 FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE CASCADE);



ALTER TABLE QnA
	ADD (CONSTRAINT  R_20 FOREIGN KEY (BoardID) REFERENCES QnA(BoardID) ON DELETE CASCADE);



ALTER TABLE QnAComments
	ADD (CONSTRAINT  R_18 FOREIGN KEY (BoardID) REFERENCES QnA(BoardID) ON DELETE CASCADE);



ALTER TABLE QnAComments
	ADD (CONSTRAINT  R_19 FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE CASCADE);



ALTER TABLE RentalHistory
	ADD (CONSTRAINT  R_16 FOREIGN KEY (UserID,SpaceID,RentID) REFERENCES RentContract(UserID,SpaceID,RentID) ON DELETE CASCADE);



ALTER TABLE RentContract
	ADD (CONSTRAINT  R_11 FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE CASCADE);



ALTER TABLE RentContract
	ADD (CONSTRAINT  R_12 FOREIGN KEY (SpaceID) REFERENCES Space(SpaceID) ON DELETE CASCADE);


/*
ALTER TABLE Reservation
	ADD (CONSTRAINT  R_8 FOREIGN KEY (PhotoID) REFERENCES Photo(PhotoID) ON DELETE CASCADE);



ALTER TABLE Reservation
	ADD (CONSTRAINT  R_15 FOREIGN KEY (SpaceID) REFERENCES Space(SpaceID) ON DELETE CASCADE);



ALTER TABLE Reservation
	ADD (CONSTRAINT  R_21 FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE CASCADE);
*/


ALTER TABLE Space
	ADD (CONSTRAINT  R_10 FOREIGN KEY (BranchID) REFERENCES Branch(BranchID) ON DELETE CASCADE);



CREATE SEQUENCE ID_SEQ
  START WITH 1
  INCREMENT BY 1
;

CREATE SEQUENCE BOARD_ID_SEQ
  START WITH 1
  INCREMENT BY 1
;

CREATE SEQUENCE COMMENT_SEQ
  START WITH 1
  INCREMENT BY 1
;


