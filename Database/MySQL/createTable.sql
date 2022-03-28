-- 选择数据库
use VClass;

-- test表
create table test(
                     `id` int PRIMARY KEY,
                     `name` varchar(50),
                     `age` int
);

-- User
create table vclassuser(
                           `username` VARCHAR(17) PRIMARY KEY,
                           `password` VARCHAR(17),
                           `status` VARCHAR(7),
                           CHECK (username<>'visitor')
);

-- User Activity
create table vcuser_activity(
                                `username` VARCHAR(17) REFERENCES vclassuser(username),
                                `date` VARCHAR(17),
                                `times` INTEGER,
                                PRIMARY KEY(username,date)
);

-- User Info
create table vcuser_info(
                            username VARCHAR(17) PRIMARY KEY,
                            link VARCHAR(255),
                            grade VARCHAR(7),
                            address VARCHAR(255),
                            status VARCHAR(7),
                            followers INT,
                            following INT,
                            name VARCHAR(17),
                            subtitle VARCHAR(70),
                            avatar VARCHAR(255)
);

-- Course
create table vcourse(
                        cid VARCHAR(70) PRIMARY KEY,
                        username VARCHAR(17),
                        cname VARCHAR(17),
                        ctype VARCHAR(17),
                        cdesc VARCHAR(255),
                        credit int,
                        cnum VARCHAR(17), -- 课程号
                        duration int,
                        teacher VARCHAR(17)
);

-- VCourse Chapter
create table vcourse_chapter(
                                cid VARCHAR(70) REFERENCES vcourse(cid),
                                chid VARCHAR(70) PRIMARY KEY,
                                chname VARCHAR(17),
                                chnum int,
                                chtag LONGTEXT
);

-- File
create table vcfile(
                       fid VARCHAR(70) PRIMARY KEY,
                       cid VARCHAR(70),
                       chid VARCHAR(70),
                       username VARCHAR(17),
                       fname VARCHAR(17),
                       ftype VARCHAR(7),
                       ftag LONGTEXT,
                       chnum VARCHAR(7)
);

-- Course Schedule
create table vcourse_schedule(
                                 cid VARCHAR(70),
                                 date VARCHAR(17),
                                 type VARCHAR(7),
                                 content VARCHAR(255),
                                 PRIMARY KEY(cid,date)
);

-- User Log
create table vclog(
                      lid VARCHAR(70) PRIMARY KEY,
                      cid VARCHAR(70),
                      name VARCHAR(17),
                      activity VARCHAR(255),
                      fname VARCHAR(17),
                      timestamp VARCHAR(70)
);

-- Course Subscriber
create table vcourse_subscribe(
                                  cid VARCHAR(70),
                                  username VARCHAR(17),
                                  PRIMARY KEY(cid,username)
);

-- Course Mindmap
create table vcourse_mindMap(
                                cid VARCHAR(17),
                                nodeId VARCHAR(17),
                                isRoot VARCHAR(7),
                                parentId VARCHAR(17),
                                topic VARCHAR(17),
                                direction VARCHAR(7),
                                expanded VARCHAR(7),
                                PRIMARY KEY(cid,nodeId)
);

-- File Topic
create table vtopic (
    `topicId` VARCHAR(70) PRIMARY KEY,
    `username` VARCHAR(17),
    `fid` VARCHAR(70),
    `content` LONGTEXT,
    `timestamp` VARCHAR(70),
    `likeNum` INTEGER,
    `title` VARCHAR(70),
    `description` VARCHAR(400),
    `cover` VARCHAR(170),
    `commentNum` INTEGER,
    `isTeacherReply` INTEGER,
    `userAvatar` VARCHAR(70)
);

-- Topic Liker
create table vtopic_liker (
    `topicId` VARCHAR(70),
    `username` VARCHAR(17),
    PRIMARY KEY(topicId,username)
)

-- Topic Comment
create table vcomment (
    `commentId` VARCHAR(70) PRIMARY KEY,
    `topicId` VARCHAR(70),
    `username` VARCHAR(17),
    `content` VARCHAR(170),
    `timestamp` VARCHAR(70),
    `likeNum` INTEGER,
    `type` VARCHAR(7), -- root / child
    `rootCommentId` VARCHAR(70)
);

-- Comment Liker
create table vcomment_liker (
    `commentId` VARCHAR(70),
    `username` VARCHAR(17),
    PRIMARY KEY(commentId,username)
);