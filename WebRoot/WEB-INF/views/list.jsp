<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <!-- 获取当前项目路径，以/开始，不以/结尾 -->
<%
  pageContext.setAttribute("APP_PATH",request.getContextPath());
 %>
 <title>员工列表</title> 
 <!-- 
 不以/开始的相对路径，找资源以当前资源的路径为基准，容易出问题
 以/开始的相对路径，找资源以服务器的路径为基准(http://localhost:8080),需要加上项目名称
  -->  
<script type="text/javascript" src="${APP_PATH }/static/js/jquery-3.0.0.min.js"></script>
<link href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<script src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
  </head>
  
  <body>
    <div class="container">
       <!-- 显示标题行 -->
       <div class="row">
         <div class="col-md-12">
            <h1>SSM-CRUD</h1>
         </div>
       </div>
       <!-- 按钮 -->
       <div class="row"></div>
       <!-- 表格 -->
       <div class="row"></div>
       <!-- 分页信息 -->
       <div class="row"></div>
    </div>
  </body>
</html>
