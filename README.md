# 项目简介
   本项目是一个为**移动端**设计的**预约及核销系统**，可点击以下超链接浏览：   
   [该项目已上线，点击访问该预约系统](http://42.192.188.85:8080/login.html)
    
## 技术与功能
 本项目是一个**Web全栈**开发项目，主要技术与功能如下：
   1. 基于**腾讯云**的**SMS短信**服务；
   2. 基于**Cookies**的长时间登录保持；
   3. 基于**Freemarker**的前端H5预约页面设计；
   4. 基于**jQuery**的JavaScript前端编程；
   5. 基于**SpringBoot**的服务端架构设计；
   6. 基于**MySQL**的数据库设计；
   7. 基于**MyBatis**的数据JDBC操作；
   8. 基于腾讯云的**Windows10服务器**；
   9. 基于**Cookies**和**WebMvcConfigurer**的登录拦截系统和用户管理员两层权限及应用体系；
   10. 基于**Google Zxing**的二维码生成与解析技术；
   11. 基于**IDEA**的**JAVA**开发技术；
   
## 使用
   本项目可以在以下基础上**直接使用**，并且**上线**。
   1. 本地环境
   **Java**环境：JDK，JRE，版本为 **8**.0_261
   2. 数据库
   **MySQL**数据库：版本为 **8.0.21**；  
   venue库：userfirst, visitorder 两张数据表  
   
    CREATE TABLE `userfirst` (  
      `id` int NOT NULL AUTO_INCREMENT,
      `uPhone` varchar(255) DEFAULT NULL,
      `uCode` varchar(255) DEFAULT NULL,
      `uName` varchar(255) DEFAULT NULL,
      `uIdNumber` varchar(255) DEFAULT NULL,
      `uCreateTime` date DEFAULT NULL,
      `uUpdateTime` date DEFAULT NULL,
      `remark` varchar(255) DEFAULT NULL,
      PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

    CREATE TABLE `visitorder` (
      `id` int NOT NULL AUTO_INCREMENT,
      `uName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
      `uPhone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
      `uIdNumber` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
      `uDate` date DEFAULT NULL,
      `uPeriod` tinyint DEFAULT NULL,
      `uCreateTime` datetime DEFAULT NULL,
      `uUpdateTime` datetime DEFAULT NULL,
      `uChildNum` tinyint DEFAULT NULL,
      `uuid` varchar(255) DEFAULT NULL,
      `checked` tinyint DEFAULT NULL,
      `remark` varchar(255) DEFAULT NULL,
      PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

   3. 开通腾讯云SMS服务，并补充 **MsgServiceImpl**.Class 类的以下代码：  
       public static String secretId = "";
       public static String secretKey = "";
       public static String appid = "";
       
   4. 在 **DoLoginServiceImpl**.Class 补充管理员帐户信息  
           if(uPhone.equals("") && uMsg.equals("")){
               res.put("code", 2);
               res.put("msg", "欢迎管理员登录！");
               
   5. 在 **AdminInterceptor**.Class 补充管理员拦截器的管理员Cooies名称，内容同4中uPhone  
               if(!cookie_username.equals("")){

   6. 本地开发就绪后，即可打包成**Jar包**，上传到服务器  
       在服务器**Jar包所在目录**，打开cmd，输入 **java -jar 该Jar包包名** 即可。
       
## 前端页面介绍：
   前端主要包括 **用户** 和 **管理员** 两个层级。
   用户级有如下页面：
   1. **login 登录注册页面**：用户**手机号短信验证码**登录；
        该页面有以下功能点：**手机号校验、验证码发送、验证码发送60s倒计时、验证码校验**等
   2. **home 预约页面**，包括**日期组件及开始结束日期设定、姓名校验、身份证校验**等功能；
        该预约系统仅可预约**一次**，但可以修改全部预约信息，具体见4
   3. **order 预约单页面**，显示**用户有效期内的预约信息，包括日期、时间段、人数、核销二维码**等；
        在该页面底部有“**预约修改**”按钮，点击该按钮，进入修改页面，修改后提交便预约修改成功。
        
   管理员层级如下：
   1. **login 登录页面**，同用户级，管理员输入账号密码即可登录；
   2. **admin 管理页面**，包括**查询**和**核销**两个按钮跳转；
   3. **orderDateQuery 日期查询页面**，显示**可预约日期内每天的预约和核销**情况；
   4. **orderPeriodQuery** 时间段查询页面，点击日期查询页面的某一个日期即可进入，显示**某一个日期内各个时间段的预约和核销**情况；
   5. **orderVerifi 预约核销页面**，点击“拍照”即可给用户二维码拍照，确定后将返回核销结果。
        核销失败则显示**核销失败**，成功将显示**用户预约信息**，包括用户姓名、手机号、时间段、日期等信息；


   

