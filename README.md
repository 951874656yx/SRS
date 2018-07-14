# SSR 学生在线选课系统
## 使用springMVC+spring+mybatis后台框架，前端使用easyUI+bootstrapTable框架
##
## 系统后台结构截图
  ![](screenshot/code/daimajiegou1.png)
  ![](screenshot/code/daimajiegou2.png)
##  
## 设计模式
### 1、工厂模式：使用spring工厂实现数据源的切换  
   ![](screenshot/code/qiehuanshujuyuan.png)
### 2、规约模式：将选课主要逻辑设计成不同的规则
   ![](screenshot/code/guiyue.png)
   ![](screenshot/code/guiyue1.png)
## 选课逻辑
  ![](screenshot/code/xuankeluoji1.png)
  ![](screenshot/code/xuankeluoji2.png)
## 使用mock数据源进行完整的选课过程测试
  ![](screenshot/code/test1.png)
  ![](screenshot/code/test2.png)
  ![](screenshot/code/test3.png)
## 类图
  ![](screenshot/code/uml.png)
## 数据库关系图
  ![](screenshot/code/er.png)
## 顺序图
  ![](screenshot/code/time.png)
## 前端功能截图
### 1、老师部分
#### 课程部分
  ![](screenshot/teacher/courseManager.png)
  ![](screenshot/teacher/addCourse.png)
  ![](screenshot/teacher/addCourse1.png)
#### 班次部分
  ![](screenshot/teacher/sectionManager.png)
  ![](screenshot/teacher/enrollStudent.png)
  ![](screenshot/teacher/addSection.png)
  ![](screenshot/teacher/addSection1.png)
  ![](screenshot/teacher/editSection.png)
#### 课表部分
  ![](screenshot/teacher/addTeach.png)
  ![](screenshot/teacher/teacherSchedule.png)
#### 成绩部分
  ![](screenshot/teacher/appointGeade1.png)
  ![](screenshot/teacher/appointGrade2.png)
  ![](screenshot/teacher/appointGrade3.png)
#### 学习计划
  ![](screenshot/teacher/planOfStudy.png)
  ![](screenshot/teacher/addPlan.png)
### 2、学生部分
#### 学生选课
  ![](screenshot/student/selectCourse.png)
  ![](screenshot/student/selectCourseSec.png)
  ![](screenshot/student/selectCourseFalse.png)
#### 学生课表
  ![](screenshot/student/studentSchedule.png)
#### 学生成绩
  ![](screenshot/student/transcript.png)
   
