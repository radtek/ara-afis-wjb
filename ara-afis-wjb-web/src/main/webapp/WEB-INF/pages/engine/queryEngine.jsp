<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
	<%--<script href="../header.jsp"></script>--%>
</head>

<body>
	<div class="hideDiv">
		<input type="hidden" id="action" value="toAddOption" />
		<input type="hidden" id="prifex" value="engine" />
		<input type="hidden" id="page" value="Engine" />
		<input type="hidden" id="operate"  />
	</div>
	<form id="dataForm" name="dataForm">
		<input type="hidden" id="anyOption" name="anyOption" />
		<input type="hidden" id="anyId" name="anyId" value="${anyId}"/>
	</form>

	<section class="wrapper_main">
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
		<!-- page start-->
		<div class="row">
			<aside class="profile-nav col-lg-3">
				<section class="panel">
					<div class="user-heading round">
						<h1><spring:message code="engine_fingerEngineList"/> </h1>
					</div>
					<ul class="nav nav-pills nav-stacked">
						<s:if test="${fn:length(engineList) > 0}">
							<s:forEach items="${engineList}" var='engine' varStatus="status">
								<li id="node_${engine.id}" <s:if test="${anyId == engine.id}">class='active'</s:if>>
									<a href="javascript:void(0)" onclick="reloadEngineData(${engine.id});">
										<s:choose>
											<s:when test="${engine.engineType == '0'}"><i
													class="icon-cloud text-success"></i><spring:message code="engine_masterNode"/></s:when>
											<s:otherwise><i class="icon-laptop text-primary"></i><spring:message code="engine_engineNode"/></s:otherwise>
										</s:choose>: ${engine.engineCode}
										<s:choose>
										<s:when test="${engine.workStationStatus == '0'}">
                                            <span class="label label-success pull-right r-activity"><i class="icon-ok-sign"></i>
                                                </s:when>
										<s:when test="${engine.workStationStatus == '1'}"><span
												class="label label-warning pull-right r-activity"><i
												class="icon-exclamation-sign"></i></s:when>
											<s:otherwise><span class="label label-danger pull-right r-activity"><i
													class="icon-remove-sign"></i></s:otherwise>
                                          </s:choose>
									</a>
								</li>
							</s:forEach>
						</s:if>
					</ul>
				</section>
			</aside>
			<aside class="profile-info col-lg-9">
				<section class="panel">
					<div class="bio-graph-heading"><spring:message code="engine_fingerEngineInfo"/>
						<span class="pull-right">
							<button type="button" class="btn btn-shadow btn-danger button_modify"><spring:message code="engine_updateEngineInfo"/></button>
							<button type="button" class="btn btn-shadow btn-default button_del"><spring:message code="engine_deleteEngineInfo"/></button>
						</span>
					</div>
					<div class="panel-body bio-graph-info">
						<h1><spring:message code="engine_physicalInfo"/> </h1>
						<div class="row">
							<div class="bio-row">
								<p>
									<span>
										<s:choose>
											<s:when test="${engine.engineType == '0'}"><spring:message code="engine_masterNode"/></s:when>
											<s:otherwise><spring:message code="engine_engineNode"/></s:otherwise>
										</s:choose>
									</span>： ${engine.engineCode}
								</p>
							</div>
							<s:if test='engine.engineType != "0"'>
							<div class="bio-row">
								<p>
									<span><spring:message code="engine_masterNodeName"/></span>： ${engine.clusterCode}
								</p>
							</div>
							</s:if>
							<div class="bio-row">
								<p>
									<span><spring:message code="engine_ipAddress"/> </span>： ${engine.ip}
								</p>
							</div>
							<div class="bio-row">
								<p>
									<span><spring:message code="engine_macAddress"/> </span>： ${engine.mac}
								</p>
							</div>
							<div class="bio-row">
								<p>
									<span><spring:message code="engine_serverPort"/></span>： ${engine.enginePort}
								</p>
							</div>
							<div class="bio-row">
								<p>
									<span><spring:message code="engine_cpuCoreNum"/> </span>： ${engine.cpuNum} <spring:message code="engine_core"/>
								</p>
							</div>
							<div class="bio-row">
								<p>
									<span><spring:message code="engine_operationStat"/> </span>：
									<s:choose>
										<s:when test="${engine.workStationStatus == '0'}"><spring:message code="engine_normal"/></s:when>
										<s:when test="${engine.workStationStatus == '1'}">
											<spring:message code="engine_warning"/>
											<%--(<s:choose>--%>
											<%--<s:when test="${engine.warnField == '1'}" >引擎服务器硬盘使用率超标</s:when>--%>
											<%--<s:when test="${engine.warnField == '2'}" >引擎服务器内存使用率超标</s:when>--%>
											<%--<s:when test="${engine.warnField == '3'}" >引擎服务器CPU使用率超标</s:when>--%>
											<%--<s:when test="${engine.warnField == '4'}" >比对引擎License状态异常</s:when>--%>
											<%--<s:when test="${engine.warnField == '5'}" >AFIS系统表空间使用率超标</s:when>--%>
											<%--<s:when test="${engine.warnField == '6'}" >AFIS系统归档日志空间使用率超标</s:when>--%>
											<%--<s:when test="${engine.warnField == '7'}" >AFIS系统主从同步机制失效</s:when>--%>
											<%--<s:otherwise></s:otherwise>--%>
											<%--</s:choose>)--%>

										</s:when>
										<s:otherwise><spring:message code="engine_fault"/></s:otherwise>
									</s:choose>
								</p>
							</div>
							<div class="bio-row">
								<p>
									<span><spring:message code="engine_networkStat"/> </span>：
									<s:choose>
										<s:when test="${engine.networkStatus == '0'}"><spring:message code="engine_networkClear"/></s:when>
										<s:otherwise><spring:message code="engine_networkNotWork"/></s:otherwise>
									</s:choose>
								</p>
							</div>
							<div class="bio-row">
								<p>
									<span><spring:message code="engine_loadFingerScanNum"/> </span>： ${engine.loadFingerNum} (<spring:message code="engine_enum"/>)
								</p>
							</div>
							<div class="bio-row">
								<p>
									<span><spring:message code="engine_registerThreadNum"/> </span>： ${engine.loadFingerNum}
								</p>
							</div>
							<div class="bio-row">
								<p>
									<span><spring:message code="engine_1to1CompareThreadNum"/> </span>： ${engineNode.verifyThread}
								</p>
							</div>
							<div class="bio-row">
								<p>
									<span><spring:message code="engine_1toNCompareThreadNum"/>  </span>： ${engineNode.identifyThread}
								</p>
							</div>
							<div class="bio-row">
								<p>
									<span><spring:message code="engine_dataModuleList"/> </span>： ${engineNode.dataZone}
								</p>
							</div>
							<div class="bio-row">
								<p>
									<span><spring:message code="engine_operationTimeLong"/> </span>： ${engine.runningTime} (h)
								</p>
							</div>
						</div>
					</div>
				</section>
				<section>
					<div class="row">
						<div class="col-lg-4">
							<div class="panel">
								<div class="panel-body">
									<div class="bio-chart">
										<input id="cpu_percent" class="knob" data-width="90" data-height="90" data-displayPrevious=true data-thickness=".2" value="0"
											data-fgColor="#ff766c" data-bgColor="#e8e8e8">
									</div>
									<div class="bio-desk">
										<h4 class="red"><spring:message code="engine_cpuUseRate"/></h4>
										<p><spring:message code="engine_unitPercent"/></p>
										<p id="cpu_date"><spring:message code="engine_time"/> : </p>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-4">
							<div class="panel">
								<div class="panel-body">
									<div class="bio-chart">
										<input id="disk_percent" class="knob" data-width="90" data-height="90" data-displayPrevious=true data-thickness=".2" value="0"
											data-fgColor="#4CC5CD" data-bgColor="#e8e8e8">
									</div>
									<div class="bio-desk">
										<h4 class="terques"><spring:message code="engine_diskSpaceUseRate"/></h4>
										<p ><spring:message code="engine_unitPercent"/></p>
										<p id="disk_date"><spring:message code="engine_time"/> : </p>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-4">
							<div class="panel">
								<div class="panel-body">
									<div class="bio-chart">
										<input id="memory_percent"  class="knob" data-width="90" data-height="90" data-displayPrevious=true data-thickness=".2" value="0"
											data-fgColor="#96be4b" data-bgColor="#e8e8e8">
									</div>
									<div class="bio-desk">
										<h4 class="green"><spring:message code="engine_memoryUseRate"/></h4>
										<p><spring:message code="engine_unitPercent"/></p>
										<p id="memory_date"><spring:message code="engine_time"/> : </p>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12">
							<div class="panel">
								<div class="panel-body" id="fingerChart" style="height:400px;"></div>
							</div>
						</div>
					</div>
				</section>
			</aside>
		</div>

		<!-- page end-->
	</section>
	<script type="text/javascript" src="js/common/pageEvent.js"></script>
	<script type="text/javascript" src="assets/jquery-knob/js/jquery.knob.js"></script>
	<script>
		var cpuT = new Array();
		var diskT = new Array();
		var memT = new Array();

		//knob
      $(".knob").knob({
    	    'readOnly':true
      });

		$(function(){
				var anyId = $("#anyId").val();
  				$.ajax({
			   			type: "POST",
			  	 		url: convertURL("engine/queryChartData"),
			  	 		dataType: "json",
			  	 		data: "anyId="+anyId,
			   			success: function(data){
			     			if(1 == data.anyStatu){
			     				$.each(data.cpuList,function(idx,item){
									cpuT[idx] = setTimeout(function () {
			     						$("#cpu_percent").val(item.totalValue).trigger('change');
				     					$("#cpu_date").html("<spring:message code='engine_time'/> : "+item.createOn);
			     				    }, idx*500);
			     				});
			     				$.each(data.diskList,function(idx,item){
									diskT[idx] = setTimeout(function () {
			     					  	$("#disk_percent").val(item.totalValue).trigger('change');
				     				    $("#disk_date").html("<spring:message code='engine_time'/> : "+item.createOn);
			     				  }, idx*500);
			     				});
			     				$.each(data.memoryList,function(idx,item){
									memT[idx] = setTimeout(function () {
			     					  $("#memory_percent").val(item.totalValue).trigger('change');
				     				    $("#memory_date").html("<spring:message code='engine_time'/> : "+item.createOn);
			     				    }, idx*500);
			     				});
			     			}
			   			}
				});
  			});

   	// 基于准备好的dom，初始化echarts实例
     var fingerChart = echarts.init(document.getElementById('fingerChart'));

      var option_finger = {
		    title : {
		        text: "<spring:message code="jspage_content_fingerprintCompareNumToday"/>"
		    },
		    tooltip : {
		    	formatter: '{a} <br/>{b} <spring:message code="jspage_content_hour21"/> : {c}<spring:message code="jspage_content_times2"/>'
		    },
		    legend: {
		        data:['<spring:message code="jspage_content_1toNCompareNum"/>','<spring:message code="jspage_content_1to1CompareNum"/>']
		    },
		    toolbox: {
		        show : true,
		        feature : {
 		            magicType : {
 		                show: true,
						type: ['line', 'bar', 'stack', 'tiled'],
                        title: {
                            line: '<spring:message code="echarts_switchToLine_title"/>',
                            bar: '<spring:message code="echarts_switchTobar_title"/>',
                            stack: '<spring:message code="echarts_switchToStack_title"/>',
                            tiled: '<spring:message code="echarts_switchToTiled_title"/>',

                        },
 		            },
                    restore: {
                        show: true,
                        title: '<spring:message code="echarts_restore"/>'
                    },
                    saveAsImage: {
                        show: true,
                        title: '<spring:message code="echarts_saveAsImage_title"/>'
                    }
		        }
		    },
		    xAxis: [
				       {
				    	   type : "category",
				           boundaryGap : false,
				    	   name: '(<spring:message code="jspage_content_hour2"/>)',
				    	   nameLocation: "end",
				    	   splitLine: {show: false},
				           data: ["0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23"]
				       }
			],
			yAxis: [
				       {
				    	   name: '(<spring:message code="jspage_content_number"/>)',
				    	   nameLocation: "end"
				       }
			],
			calculable: true,
		    series : [
		        {
		        	 name:'<spring:message code="jspage_content_1toNCompareNum"/>',
			         type:'line',
			         stack: '<spring:message code="jspage_content_total"/>',
			         data:[${identifyNumArray}]
		        },
		        {
		        	 name:'<spring:message code="jspage_content_1to1CompareNum"/>',
			         type:'line',
			         stack: '<spring:message code="jspage_content_total"/>',
			         data:[${verifyNumArray}]
		        }
		    ],
         	noDataLoadingOption : {
				text: '<spring:message code="echarts_noData"/>',
				effect:'bubble',
              	effectOption : {
				    effect: {
                      n: 10 //气泡个数为0
                  }
                }
          	}
		};


       // 为echarts对象加载数据
       fingerChart.setOption(option_finger);

       function reloadEngineData(anyId){
		   $("#anyId").val(anyId);
		   clearTimer();
    	   loadPage("main-content","engine/toGetList?anyId="+anyId,"");
       }
		//清除定时器
		function clearTimer() {
			$.each(cpuT, function (idx, item) {
				clearTimeout(cpuT[idx]);
			});
			$.each(diskT, function (idx, item) {
				clearTimeout(diskT[idx]);
			});
			$.each(memT, function (idx, item) {
				clearTimeout(memT[idx]);
			});
		}
  </script>
</body>
</html>
