<%@ page language="java"  pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
  <head>
  		<link rel="stylesheet" type="text/css" href="assets/zTree/css/zTreeStyle.css" >
  </head>

<body>
	<div class="hideDiv">
		<input type="hidden" id="action" value="addOption" />
		<input type="hidden" id="prifex" value="role" />
		<input type="hidden" id="page" value="Role" />
		<input type="hidden" id="operate" value="<s:if test="${role != null}">modify</s:if>" />
		<input type="hidden" id="treeInitStat" value='${msg}' />
	</div>

	<section class="wrapper_top">
		<div class="row">
			<div class="col-lg-12">
				<!--breadcrumbs start -->
				<ul class="breadcrumb">
					<li><a href="javascript:void(0)" onclick="loadPage('main-content','common/loadContent','')"><i class="icon-home"></i><spring:message code="home" /></a></li>
					<li><a href="javascript:void(0)"><i class="icon-gear"></i><spring:message code="setting" /></a></li>
					<li class="active"><i class="icon-shield"></i><spring:message code="add_role" /></li>
				</ul>
				<!--breadcrumbs end -->
			</div>
		</div>
	</section>

	<section class="wrapper">

		<div class="row">
			<div class="col-lg-12">
				<div id="result" class="alert alert-block fade in hideDiv">
					<button data-dismiss="alert" class="close close-sm" type="button">
						<i class="icon-remove"></i>
					</button>
					<h4><i id="result_sign"></i><span id="result_title"></span></h4>
					<p id="result_msg"></p>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-lg-12">
				<section class="panel">
					<header class="panel-heading panel-bg-orgin">
						<i class="icon-book"></i><spring:message code="add_role" />
					</header>
					<div class="panel-body">
						<div class="form">
							<form class="cmxform form-horizontal tasi-form" id="dataForm" name="dataForm" novalidate="novalidate">
								<input type="hidden" id="anyOption" name="anyOption" />
								<input type="hidden" id="purview" name="purview" />
								<input type="hidden" id="anyId" name="anyId" value="<s:choose><s:when test="${role != null}">${role.id}</s:when><s:otherwise>0</s:otherwise></s:choose>"/>
								<div class="form-group ">
									<label for="name" class="control-label col-lg-2" style="text-align: right"><span class="form-req">* </span><spring:message code="system_role_roleName" /> :</label>
									<div class="col-lg-4">
										<input id="name" name="name" value="${role.name}" class="form-control" maxlength="30"  type="text" required data-rule-commTextIN="true"
											   data-msg-commTextIN="<spring:message code='system_role_roleNameFormat' />">
									</div>
								</div>

								<div class="form-group ">
									<label for="describe" class="control-label col-lg-2" style="text-align: right"><span class="form-req">&nbsp;</span><spring:message code="system_manager_roleDescription" /> :</label>								<div class="col-lg-4">
										<input id="describe" name="describe" value="${role.describe}" class="form-control" maxlength="250" type="text">
									</div>
								</div>

								<div class="form-group ">
									<label for="purview" class="control-label col-lg-2" style="text-align: right"><span class="form-req">* </span><spring:message code="system_role_allotRight" />:</label>
									<div class="col-lg-4">
										<div id="rolePreview" class="input" style="border: 1px solid #b3b3b3;">
											<ul id="roleTree" class="ztree"></ul>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="col-lg-offset-2 col-lg-4">
										<button class="btn btn-danger btn-form" type="button" id="button_add">
											<i class="icon-ok-sign"></i>
											<s:choose><s:when test="${role != null}"><spring:message code="system_code_update" /></s:when><s:otherwise><spring:message code="system_code_add" /></s:otherwise></s:choose>
										</button>
										<button class="btn btn-default btn-form" type="button" id="button_reset">
											<i class="icon-refresh"></i> <spring:message code="system_code_reset" />
										</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</section>
			</div>
		</div>
	</section>
	<script type="text/javascript" src="js/common/pageEvent.js"></script>
	<script type="text/javascript" src="assets/zTree/js/jquery.ztree.core-3.5.min.js"></script>
	<script type="text/javascript" src="assets/zTree/js/jquery.ztree.excheck-3.5.min.js"></script>
	<script type="text/javascript">
		var treeInitNodes;
		var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true,
					chkStyle: "checkbox",
					idKey: "code",
					pIdKey: "pcode"
				}
			}
		};

		$(function () {
			$.fn.zTree.init($("#roleTree"), setting, ${msg});
			treeInitNodes = $.fn.zTree.getZTreeObj("roleTree").getCheckedNodes(true);
		});
	</script>
</body>
</html>
