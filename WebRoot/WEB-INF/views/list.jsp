<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <!-- ��ȡ��ǰ��Ŀ·������/��ʼ������/��β -->
<%
  pageContext.setAttribute("APP_PATH",request.getContextPath());
 %>
 <title>Ա���б�</title> 
 <!-- 
 ����/��ʼ�����·��������Դ�Ե�ǰ��Դ��·��Ϊ��׼�����׳�����
 ��/��ʼ�����·��������Դ�Է�������·��Ϊ��׼(http://localhost:8080),��Ҫ������Ŀ����
  -->  
<script type="text/javascript" src="${APP_PATH }/static/js/jquery-3.0.0.min.js"></script>
<link href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<script src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
  </head>
  
  <body>
    <div class="container">
       <!-- ��ʾ������ -->
       <div class="row">
         <div class="col-md-12">
            <h1>SSM-CRUD</h1>
         </div>
       </div>
       <!-- ��ť -->
       <div class="row"></div>
       <!-- ��� -->
       <div class="row"></div>
       <!-- ��ҳ��Ϣ -->
       <div class="row"></div>
    </div>
  </body>
</html>
