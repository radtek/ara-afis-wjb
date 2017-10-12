<%@ page language="java"  pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
  <head></head>

<body>
	<div class="hideDiv">
		<input type="hidden" id="action" value="changeOption" />
		<input type="hidden" id="prifex" value="managerInfo" />
		<input type="hidden" id="page" value="Manager" />
		<input type="hidden" id="operate" value="changePwd" />
	</div>
	
	<section class="wrapper_top">
		<div class="row">
			<div class="col-lg-12">
				<!--breadcrumbs start -->
				<ul class="breadcrumb">
					<li><a href="javascript:void(0)" onclick="loadPage('main-content','common/loadContent','')"><i class="icon-home"></i><spring:message code="home" /></a></li>
					<li><a href="avascript:void(0)"><i class="icon-gear"></i><spring:message code="setting" /></a></li>
					<li><a href="avascript:void(0)"><i class="icon-group"></i><spring:message code="system_manager_manager" /></a></li>
					<li class="active"><i class="icon-unlink"></i><spring:message code="system_manager_changePassword" /></li>
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
						<i class="icon-book"></i><spring:message code="system_manager_changePassword" />
					</header>
					<div class="panel-body">
						<div class="form">
							<form class="cmxform form-horizontal tasi-form" id="dataForm" name="dataForm" novalidate="novalidate">
								<input type="hidden" id="anyOption" name="anyOption" />
								<input type="hidden" id="anyId" name="anyId" value="<s:choose><s:when test="${manager != null}">${manager.id}</s:when><s:otherwise>0</s:otherwise></s:choose>"/>
								<!--
                                <div class="form-group ">
									<label for="password" class="control-label col-lg-1"><span class="form-req">*</span>旧密码 ：</label>
									<div class="col-lg-4">
										<input id="password" name="password" class="form-control" minlength="6" maxlength="30" type="password" required>
									</div>
								</div>
								-->
								<div class="form-group " id="passwd">
										<label for="newPasswd" class="control-label col-lg-1"><span class="form-req">*</span><spring:message code="system_manager_newPassword" /> :</label>
										<div class="col-lg-4">
											<input id="newPasswd" name="newPasswd" class="form-control"  minlength="6" maxlength="30" type="password" required>
										</div>
								</div>

								<div class="form-group ">
									<label for="newPasswdCopy" class="control-label col-lg-1"><span class="form-req">*</span><spring:message code="system_manager_confirmNewPassword" /> ：</label>
										<div class="col-lg-4">
											<input id="newPasswdCopy" name="newPasswdCopy" class="form-control" minlength="6" maxlength="30" type="password" required data-rule-equalTo="#newPasswd"
												   data-msg-equalTo="× <spring:message code='system_manager_reSetPasswordNotMatch' />">
										</div>
								</div>
								<div class="form-group">
									<div class="col-lg-offset-1 col-lg-4">
										<button class="btn btn-danger btn-form" type="button" id="button_change">
											<i class="icon-ok-sign"></i><spring:message code="system_manager_commit" />
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

		<div class="row">
                  <div class="col-lg-12">
                      <section class="panel">
                          <table class="table table-striped table-advance table-hover">
                              <thead>
                              <tr>
                                  <th class="col-lg-2"><i class="icon-bullhorn"></i> <spring:message code="system_manager_mangerAcount" /></th>
                                   <th class="col-lg-1"><i class="icon-bookmark"></i> <spring:message code="system_manager_role" /></th>
                                  <th class="col-lg-1"><i class="icon-question-sign"></i> <spring:message code="system_code_stat" /></th>
                                  <th class="col-lg-1"><i class="icon-off"></i> <spring:message code="system_manager_lastLoginTime" /></th>
                                  <th class="col-lg-2"><i class=" icon-calendar"></i> <spring:message code="system_code_crateDateTime" /></th>
                              </tr>
                              </thead>
                              <tbody id="tabBody">
                              		<tr>
										<td title="${manager.account}"> ${manager.account} </td>
										<td>${manager.role.name}</td>
										<td>${manager.statu}</td>
										<td>${manager.lastLogin}</td>
										<td>${manager.createOn}</td>
									</tr>
                              </tbody>
                          </table>
                      </section>
                  </div>
              </div>
	</section>
	<script type="text/javascript" src="js/common/pageEvent.js"></script>
</body>
</html>
