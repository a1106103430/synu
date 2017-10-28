<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<!-- ��ȡ��ǰ��Ŀ·������/��ʼ������/��β -->
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<title>Ա���б�</title>
<!-- 
 ����/��ʼ�����·��������Դ�Ե�ǰ��Դ��·��Ϊ��׼�����׳�����
 ��/��ʼ�����·��������Դ�Է�������·��Ϊ��׼(http://localhost:8080),��Ҫ������Ŀ����
  -->
<script type="text/javascript"
	src="${APP_PATH }/static/js/jquery-3.0.0.min.js"></script>
<link
	href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
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
		<div class="row">
			<div class="col-md-4 col-md-offset-8">
				<button class="btn btn-primary">����</button>
				<button class="btn btn-danger">ɾ��</button>
			</div>
		</div>
		<!-- ��� -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover" id="emps_table">
					<thead>
						<tr>
							<th>#</th>
							<th>empName</th>
							<th>gender</th>
							<th>email</th>
							<th>deptName</th>
							<th>����</th>
						</tr>
					</thead>
					<tbody>

					</tbody>
				</table>
			</div>
		</div>
		<!-- ��ҳ��Ϣ -->
		<div class="row">
			<div class="col-md-6" id="page_info_area"></div>
			<div class="col-md-6" id="page_nav_area"></div>
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			to_page(1)
		});
		function to_page(pn) {
			$.ajax({
				url : "${APP_PATH}/emps/emps",
				data : "pn=" + pn,
				type : "GET",
				success : function(result) {
					//console.log(result);
					//1.��������ʾԱ������
					build_emps_table(result);
					//2.��������ʾ��ҳ��Ϣ
					build_page_info(result);
					//3.������ʾ��ҳ������
					build_page_nav(result);
				}
			});
		}

		function build_emps_table(result) {
			$("#emps_table tbody").empty();
			var emps = result.extend.pageInfo.list;
			$
					.each(
							emps,
							function(index, item) {
								var checkboxTd = $("<td><input type ='checkbox' class='check_item'/></td>");
								//alert(item.empName);
								var empIdTd = $("<td></td>").append(item.empId);
								var empNameTd = $("<td></td>").append(
										item.empName);
								var genderTd = $("<td></td>").append(
										item.gender == 'M' ? "��" : "Ů");
								var emailTd = $("<td></td>").append(item.email);
								var deptNameTd = $("<td></td>").append(
										item.department.deptName);
								var editBtn = $("<button></button>")
										.addClass(
												"btn btn-primary btn-sm edit_btn")
										.append(
												$("<span></span>")
														.addClass(
																"glyphicon glyphicon-pencil"))
										.append("�༭");
								//Ϊ�༭��ť����Զ������ԣ���ʾ��ǰid
								editBtn.attr("edit-id", item.empId);
								var delBtn = $("<button></button>")
										.addClass(
												"btn btn-danger btn-sm delete_btn")
										.append(
												$("<span></span>")
														.addClass(
																"glyphicon glyphicon-trash"))
										.append("ɾ��");
								//Ϊɾ����ť����Զ������ԣ���ʾ��ǰid
								delBtn.attr("del-id", item.empId);
								var btnTd = $("<td></td>").append(editBtn)
										.append(" ").append(delBtn);
								$("<tr></tr>").append(checkboxTd).append(
										empIdTd).append(empNameTd).append(
										genderTd).append(emailTd).append(
										deptNameTd).append(btnTd).appendTo(
										"#emps_table tbody");
							});
		}

		function build_page_info(result) {
		    $("#page_info_area").empty();
			$("#page_info_area").append(
					"��ǰ" + result.extend.pageInfo.pageNum + "ҳ����"
							+ result.extend.pageInfo.pages + "ҳ����"
							+ result.extend.pageInfo.total + "����¼");
		}

		function build_page_nav(result) {
			$("#page_nav_area").empty();
			var ul = $("<ul></ul>").addClass("pagination");
			var firstPageLi = $("<li></li>").append(
					$("<a></a>").append("��ҳ").attr("href", "#"));
			var prePageLi = $("<li></li>").append(
					$("<a></a>").append("&laquo;"));
			if (result.extend.pageInfo.hasPreviousPage == false) {
				firstPageLi.addClass("disabled");
				prePageLi.addClass("disabled")
			} else {
				firstPageLi.click(function() {
					to_page(1);
				});
				prePageLi.click(function() {
					to_page(result.extend.pageInfo.pageNum - 1);
				});
			}
			var nextPageLi = $("<li></li>").append(
					$("<a></a>").append("&raquo;"));
			var lastPageLi = $("<li></li>").append(
					$("<a></a>").append("ĩҳ").attr("href", "#"));
			if (result.extend.pageInfo.hasNextPage == false) {
				nextPageLi.addClass("disabled");
				lastPageLi.addClass("disabled");
			} else {
				nextPageLi.click(function() {
					to_page(result.extend.pageInfo.pageNum + 1);
				});
				lastPageLi.click(function() {
					to_page(result.extend.pageInfo.pages);
				});
			}
			//�����ҳ��ǰһҳ
			ul.append(firstPageLi).append(prePageLi);
			//���1��2��3����ul�����ҳ��
			$.each(result.extend.pageInfo.navigatepageNums, function(index,
					item) {
				var numLi = $("<li></li>").append($("<a></a>").append(item));
				if (result.extend.pageInfo.pageNum == item) {
					numLi.addClass("active")
				}
				numLi.click(function() {
					to_page(item);
				});
				ul.append(numLi);
			});
			//���ĩҳ����һҳ
			ul.append(nextPageLi).append(lastPageLi);
			var navEl = $("<nav></nav>").append(ul);
			$("#page_nav_area").append(navEl);
			//navEl.appendTo("#page_nav_area");
		}
	</script>

</body>
</html>
