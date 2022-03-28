# CheeseburgerIM

## VClass

<center>个人主页：<a href="https://cheeseburgerim.space" target="_blank">https://cheeseburgerim.space</a></center>

<center>后端域名：<a href="http://vclass.api.cheeseburgerim.space" target="_blank">vclass.api.cheeseburgerim.sapce</a></center>

<center>接口文档：<a href="http://vclass.api.cheeseburgerim.space" target="_blank">http://vclass.api.cheeseburgerim.space</a></center>

<center>接口测试：<a href="https://cheeseburgerim.gitee.io/idea-on-pc/VClassServer/index.html" target="_blank">https://cheeseburgerim.gitee.io/idea-on-pc/VClassServer/</a></center>

```java
// UserController
{{domain}}/user/api/add/ 注册 新增用户
{{domain}}/user/api/update/username={username}/oldpwd={oldpwd}/newpwd={newpwd} 更改密码
{{domain}}/user/api/login/ 登录
{{domain}}/user/api/logout/ 登出
// ------- ------- -------
// UserInfoController
{{domain}}/user/api/getInfo/ 获取个人信息
{{domain}}/user/api/updateInfo/ 更新个人信息
{{domain}}/user/api/getAvatar/ 获取用户头像
{{domain}}/user/api/setAvatar/ 设置用户头像
// UserActivityController
{{domain}}/user/api/getAct/ 获取一年内个人活跃度记录
{{domain}}/user/api/setAct/ 根据用户活动更新活跃度信息
// CourseController
{{domain}}/course/api/updateCourseInfo/ 用于新建或修改课程相关信息
{{domain}}/course/api/getCourse/ 进入课程主页时获取课程信息
{{domain}}/course/api/getAllCourseByTeacherusername/ 获取老师创建的全部课程
{{domain}}/course/api/getAllCourseByStudentusername/ 获取学生加入的全部课程
{{domain}}/course/api/getAllCourse/ 获取整个虚拟教研室系统中的全部课程
{{domain}}/course/api/subscribe/ 学生用户调用此接口来订阅课程
{{domain}}/course/api/getAllSubscriber/ 获取某一课程的全部订阅者
// CourseScheduleController
{{domain}}/course/schedule/api/getSchedule/ 获取课程的课程安排
{{domain}}/course/schedule/api/setSchedule/ 设置课程的课程安排
{{domain}}/course/schedule/api/deleteSchedule/ 删除课程安排
// CourseMindMapController
// 课程思维导图由若干结点组成
{{domain}}/course/mindMap/api/addMindMapNode/ 新建思维导图结点
{{domain}}/course/mindMap/api/getMindMap/ 获取课程思维导图的全部结点
{{domain}}/course/mindMap/api/deketeMindMap/ 删除课程思维导图
// ChapterController
{{domain}}/course/chapter/api/updateChapter/ 新建或更新课程的章节以及详细信息
{{domain}}/course/chapter/api/deleteAllChapter/ 删除课程下的所有章节
{{domain}}/course/chapter/api/getAllChapter/ 获取课程全部章节及具体内容
// FileController
{{domain}}/file/api/uploadFile/ 用户上传文件时所用的接口
{{domain}}/file/api/downloadFile/ 用户下载文件时所用的接口
{{domain}}/file/api/OnlineBrowsing/ 用户在线预览文件时所用的接口
{{domain}}/file/api/getAllFile/ 按章节分类获取课程下的所有文件资源
{{domain}}/file/api/deleteFile/ 删除课程下某一章节的文件资源
// LogController
{{domain}}/log/api/addLog/ 新建课程的动态日志记录
{{domain}}/log/api/getAllLog/ 获取课程的全部动态日志
{{domain}}/log/api/getTopHundredLog/ 获取最近100条记录
```