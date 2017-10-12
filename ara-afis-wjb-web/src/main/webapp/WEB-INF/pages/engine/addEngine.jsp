<%@ page language="java"  pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
  <head></head>

<body>
	<div class="hideDiv">
		<input type="hidden" id="action" value="addOption" />
		<input type="hidden" id="prifex" value="engine" />
		<input type="hidden" id="page" value="Engine" />
		<input type="hidden" id="operate"  value="<s:if test="${engine != null}">modify</s:if>"/>
	</div>
	
	<section class="wrapper_top">
		<div class="row">
			<div class="col-lg-12">
				<!--breadcrumbs start -->
				<ul class="breadcrumb">
					<li><a href="javascript:void(0)" onclick="loadPage('main-content','common/loadContent','')"><i class="icon-home"></i><spring:message code="home" /></a></li>
					<li><a href="javascript:void(0)"><i class="icon-laptop"></i><spring:message code="server" /></a></li>
					<li class="active"><i class="icon-laptop"></i><spring:message code="add_server" /></li>
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
						<i class="icon-book"></i><spring:message code="add_server" />
					</header>
					<div class="panel-body">
						<div class="form">
							<form class="cmxform form-horizontal tasi-form" id="dataForm" name="dataForm" novalidate="novalidate">
								<input type="hidden" id="anyOption" name="anyOption" />
								<input type="hidden" id="oldMasterId" name="oldMasterId" value="${engine.clusterCode}"/>
								<input type="hidden" id="oldNodeId" name="oldNodeId" value="${engine.engineCode}"/>

								<div class="form-group ">
									<div class="col-lg-5">
										<label for="masterId" class="control-label col-lg-5" style="text-align: right"><span class="form-req">*</span><spring:message code="engine_masterNodeName" /> :</label>
										<div class="col-lg-7">
											<input id="masterId" name="masterId" value="${engine.clusterCode}" class="form-control" type="text" maxlength="7" required data-rule-onlyNumChar="true"
												   data-msg-onlyNumChar="<spring:message code="engine_masterNodeNameFormat" />">
										</div>
									</div>
									<div class="col-lg-7">
										<label for="nodeId" class="control-label col-lg-3" style="text-align: right"><span class="form-req">*</span><spring:message code="engine_engineNodeName" /> :</label>
										<div class="col-lg-5">
											<input id="nodeId" name="nodeId" value="${engineNode.nodeId}" class="form-control" type="text" maxlength="7" required data-rule-onlyNumChar="true"
												   data-msg-onlyNumChar="<spring:message code="system_code_codeIDFormat" />">
										</div>
									</div>
								</div>
								<div class="form-group ">
									<div class="col-lg-5">
										<label for="engineType" class="control-label col-lg-5" style="text-align: right"><span class="form-req">*</span><spring:message code="engine_engineType" /> :</label>
										<div class="col-lg-7">
											<select id="engineType" name="engineType" class="form-control" required data-msg-required="<spring:message code="system_code_require" />">
												<option  value="1" <s:if test="${1 == engine.engineType}">selected="selected"</s:if>><spring:message code="engine_engineNode" /></option>
												<option  value="0" <s:if test="${0 == engine.engineType}">selected="selected"</s:if>><spring:message code="engine_masterNode" /></option>
											</select>
										</div>
									</div>
									<div class="col-lg-7">
										<label for="biometricsModel" class="control-label col-lg-3" style="text-align: right"><span class="form-req">*</span><spring:message code="engine_biologicalRecognitionModel" /> :</label>
										<div class="col-lg-5">
											<select id="biometricsModel" name="biometricsModel" class="form-control" required data-msg-required="<spring:message code="system_code_require" />">
												<option  value="FP" <s:if test="${'FP' == engine.biometricsModel}">selected="selected"</s:if>><spring:message code="engine_finger" /></option>
												<option  value="FA" <s:if test="${'FA' == engine.biometricsModel}">selected="selected"</s:if>><spring:message code="engine_face" /></option>
												<option  value="FI" <s:if test="${'FI' == engine.biometricsModel}">selected="selected"</s:if>><spring:message code="engine_iris" /></option>
												<option  value="FV" <s:if test="${'FV' == engine.biometricsModel}">selected="selected"</s:if>><spring:message code="engine_venaDigitails" /></option>
											</select>
										</div>
									</div>
								</div>
								<div class="form-group ">
									<div class="col-lg-5">
										<label for="enrollThread" class="control-label col-lg-5" style="text-align: right"><span class="form-req">*</span><spring:message code="engine_registerThreadNum" /> :</label>
										<div class="col-md-7">
											<div id="spinner_enr">
												<div class="input-group spinner-input-length">
													<input id="enrollThread" name="enrollThread" type="text" class="spinner-input form-control"  readonly>
													<div class="spinner-buttons input-group-btn">
														<button type="button" class="btn btn-warning spinner-up" style="margin-right: -2px">
															<i class="icon-plus"></i>
														</button>
														<button type="button" class="btn btn-danger spinner-down">
															<i class="icon-minus"></i>
														</button>
													</div>
												</div>
											</div>
											<span class="help-block-exist"><spring:message code="engine_registerThreadNumMsg" /></span>

										</div>
									</div>
									<div class="col-lg-7">
										<label for="verifyThread" class="control-label col-lg-3" style="text-align: right"><span class="form-req">*</span><spring:message code="engine_1to1ThreadNum" /> :</label>
										<div class="col-md-5">
											<div id="spinner_ver">
												<div class="input-group spinner-input-length">
													<input type="text" id="verifyThread" name="verifyThread" class="spinner-input form-control"  readonly>
													<div class="spinner-buttons input-group-btn">
														<button type="button" class="btn btn-warning spinner-up" style="margin-right: -2px">
															<i class="icon-plus"></i>
														</button>
														<button type="button" class="btn btn-danger spinner-down">
															<i class="icon-minus"></i>
														</button>
													</div>
												</div>
											</div>
											<span class="help-block-exist"><spring:message code="engine_1to1ThreadNumMsg" /></span>
										</div>
									</div>
								</div>
								<div class="form-group ">
									<div class="col-lg-5">
										<label for="identifyThread" class="control-label col-lg-5" style="text-align: right"><span class="form-req">*</span><spring:message code="engine_1toNThreadNum" /> :</label>
										<div class="col-md-7">
											<div id="spinner_ide">
												<div class="input-group spinner-input-length">
													<input type="text" id="identifyThread" name="identifyThread" class="spinner-input form-control"  readonly>
													<div class="spinner-buttons input-group-btn">
														<button type="button" class="btn btn-warning spinner-up" style="margin-right: -2px">
															<i class="icon-plus"></i>
														</button>
														<button type="button" class="btn btn-danger spinner-down">
															<i class="icon-minus"></i>
														</button>
													</div>
												</div>
											</div>
											<span class="help-block-exist"><spring:message code="engine_1toNThreadNumMsg" /></span>
										</div>
									</div>
									<div class="col-lg-7">
										<label for="dataZone" class="control-label col-lg-3" style="text-align: right"><span class="form-req">*</span><spring:message code="engine_dataModuleList" /> :</label>
										<div class="col-lg-9">
											<input id="dataZone" name="dataZone" value="${engineNode.dataZone}" class="form-control"  type="text" maxlength="150" required data-rule-onlyNumComma="true"
												   data-msg-onlyNumComma="<spring:message code="engine_dataModuleListErrorMsg" />">
											<span class="help-block-exist"><spring:message code="engine_dataModuleListInfoMsg" /></span>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="col-lg-6">
										<div class="col-lg-offset-4 col-lg-12">
											<button class="btn btn-danger btn-form" type="button" id="button_add">
												<i class="icon-ok-sign"></i>
												<s:choose>
													<s:when test="${engine != null}"><spring:message code="system_code_update" /></s:when>
													<s:otherwise><spring:message code="system_code_add" /></s:otherwise>
												</s:choose>
											</button>
										</div>
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
							<th><i class="icon-th"></i> <spring:message code="engine_masterNodeName" /></th>
							<th><i class="icon-th-large"></i> <spring:message code="engine_engineNodeName" /></th>
							<th><i class="icon-cogs"></i> <spring:message code="engine_engineType" /></th>
							<th><i class="icon-eye-open"></i> <spring:message code="engine_biologicalRecognitionModel" /></th>
							<th><i class="icon-cloud-download"></i> <spring:message code="engine_registerThreadNum" /></th>
							<th><i class="icon-zoom-in"></i> <spring:message code="engine_1to1ThreadNum" /></th>
							<th><i class="icon-zoom-out"></i> <spring:message code="engine_1toNThreadNum" /></th>
							<th class="col-lg-2"><i class="icon-list-ol"></i> <spring:message code="engine_dataModuleList" /></th>
						</tr>
						</thead>
						<tbody id="tabBody"></tbody>
					</table>
				</section>
			</div>
		</div>
	</section>
	<script type="text/javascript" src="assets/fuelux/js/spinner.min.js"></script>
	<script type="text/javascript" src="js/common/pageEvent.js"></script>
	<script type="text/javascript">
        var engineType = $("#engineType").val();
        //区分主控节点和引擎节点的添加内容
        if( 0 == engineType){
            hideElement();
        }

        $("#spinner_enr").spinner({value:${engineNode.enrollThread}, min: 1});
		$("#spinner_ver").spinner({value:${engineNode.verifyThread}, min: 1});
		$("#spinner_ide").spinner({value:${engineNode.identifyThread}, min: 1});
        $("#engineType").change(function(){
            hideElement();
            $("#nodeId").val("");
        });

        function hideElement(){
            $("#nodeId").parent().parent().toggle();
            $("#enrollThread").parent().parent().parent().parent().parent().toggle();
            $("#dataZone").parent().parent().parent().toggle();
        }
	</script>
</body>
</html>
