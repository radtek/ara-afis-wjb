<%@ page language="java"  pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
  <head></head>

<body>
	<section class="wrapper_main">
              <!--state overview start-->
              <div class="row state-overview">
                  <div class="col-lg-3 col-sm-6">
                      <section class="panel">
                          <div class="symbol blue">
                              <i class="icon-hand-right"></i>
                          </div>
                          <div class="value">
                              <h1 class="count">
                                  0
                              </h1>
                              <p><spring:message code="common_fingerScanNumInLibary"/> </p>
                          </div>
                      </section>
                  </div>
                  <div class="col-lg-3 col-sm-6">
                      <section class="panel">
                          <div class="symbol terques">
                              <i class="icon-user"></i>
                          </div>
                          <div class="value">
                              <h1 class="count2">
                                  0
                              </h1>
                              <p><spring:message code="common_userNumInLibary"/></p>
                          </div>
                      </section>
                  </div>
                  <div class="col-lg-3 col-sm-6">
                      <section class="panel">
                          <div class="symbol color-biometric">
                              <i class="icon-laptop"></i>
                          </div>
                          <div class="value">
                              <h1 class=" count3">
                                  0
                              </h1>
                              <p><spring:message code="common_engineNumOnLine"/></p>
                          </div>
                      </section>
                  </div>
                  <div class="col-lg-3 col-sm-6">
                      <section class="panel">
                          <div class="symbol yellow">
                              <i class="icon-tags"></i>
                          </div>
                          <div class="value">
                              <h1 class=" count4">
                                  0
                              </h1>
                              <p><spring:message code="common_businessNumToday"/></p>
                          </div>
                      </section>
                  </div>
              </div>
              <!--state overview end-->
			
			  <!--chart start-->
              <div class="row">
                  
                  <div class="col-lg-8">
                      <section class="panel">
                        <header class="panel-heading">
                            <h4><spring:message code="common_fingerLibIncreaseTendencyThisMonth"/></h4>
                        </header>
                        <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    					<div id="fp_add_Chart" style="height:350px;display: block;"></div>
                    </section>
                  </div>
                  
                  <div class="col-lg-4">
					 <section class="panel">
                        <header class="panel-heading">
                            <h4><spring:message code="common_businessTypeDistributeTendencyThisMonth"/></h4>
                        </header>
                        <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    					<div id="fp_type_Chart" style="height:350px;display: block;"></div>
                    </section>
                  </div>
              </div>
          	  <!--chart end-->
              
              <!--statu start-->
              <div class="row">
                  
                  <div class="col-sm-12 col-lg-4">
                      <section class="panel">
                          <div class="weather-bg color-biometric">
                              <div class="panel-body">
                                  <div class="row">
                                      <div class="col-xs-2">
                                        <i class="icon-laptop"></i>
                                      </div>
                                      <div class="col-xs-10">
                                          <div class="degree">
                                              <spring:message code="common_engineStat"/>
                                          </div>
                                      </div>
                                  </div>
                              </div>
                          </div>
                          <footer class="weather-category">
                              <ul id="server-status"></ul>
                          </footer>

                      </section>
                  </div>
                  
                  <div class="col-sm-12 col-lg-4">
                      <section class="panel">
                          <div class="weather-bg color-franchise">
                              <div class="panel-body">
                                  <div class="row">
                                      <div class="col-xs-2">
                                        <i class="icon-globe"></i>
                                      </div>
                                      <div class="col-xs-10">
                                          <div class="degree">
                                              <spring:message code="common_taskStat"/>
                                          </div>
                                      </div>
                                  </div>
                              </div>
                          </div>

                          <footer class="weather-category">
                              <ul id="task-status"></ul>
                          </footer>

                      </section>
                  </div>
                  
                  <div class="col-sm-12 col-lg-4">
                      <!--weather statement start-->
                      <section class="panel">
                          <div class="weather-bg color-device">
                              <div class="panel-body">
                                  <div class="row">
                                      <div class="col-xs-2">
                                        <i class="icon-cloud"></i>
                                      </div>
                                      <div class="col-xs-10">
                                          <div class="degree">
                                              <spring:message code="common_taskStatistics"/>
                                          </div>
                                      </div>
                                  </div>
                              </div>
                          </div>

                          <footer class="weather-category">
                              <ul id="bus-status"></ul>
                          </footer>

                      </section>
                      <!--weather statement end-->
                  </div>
              </div>
              <!--statu start-->
              
              <!--log start-->
              <div class="row">
                  <div class="col-lg-12">
                      <!--work progress start-->
                      <section class="panel">
                          <div class="panel-body progress-panel">
                              <div class="task-progress">
                                  <h1><spring:message code="common_latelyLogInfo"/></h1>
                              </div>
                              <div class="task-option">
                                  <select id="log-table" class="styled">
                                      <option><spring:message code="log_busLog"/></option>
                                      <option><spring:message code="log_sysLog"/></option>
                                  </select>
                                  <p></p>
                              </div>
                          </div>
                          <table id="bus-log-table" class="table personal-task">
                              <s:if test="${fn:length(busLogInfolist) > 0}">
								<s:forEach items="${busLogInfolist}" var='buslog' varStatus="status">
	                              <tr>
	                                  <td><s:out value="${status.index+1}"/></td>
	                                  <td>${buslog.content}</td>
	                                  <td>${buslog.actionType}</td>
	                                  <td>${buslog.resultFlag}</td>
	                                  <td>${buslog.resultCode}</td>
	                                  <td>${buslog.resultMSG}</td>
	                                  <td>${buslog.clientIp}</td>
	                                  <td>${buslog.createOn}</td>
	                              </tr> 
                              </s:forEach>
								</s:if>                           
                          </table>
                          <table id="sys-log-table" class="table personal-task hideDiv">
                              <s:if test="${fn:length(sysLogInfolist) > 0}">
                                  <s:forEach items="${sysLogInfolist}" var='syslog' varStatus="status">
                                      <tr>
                                          <td><s:out value="${status.index+1}"/></td>
                                          <td>${syslog.content}</td>
                                          <td><span class="badge bg-info">${syslog.operate}</span></td>
                                          <td>
                                              <div>${syslog.createOn}</div>
                                          </td>
                                      </tr>
                                  </s:forEach>
                              </s:if>
                          </table>
                      </section>
                      <!--work progress end-->
                  </div>
              </div>
			  <!--log start-->
          </section>
	<script src="js/chart/echarts-all.js"></script>
	<script type="text/javascript">
        //计数首页统计数据
        countUp('.count', ${homeData.fpNum});
        countUp('.count2', ${homeData.userNum});
        countUp('.count3', ${homeData.serverNum});
        countUp('.count4', ${homeData.busNum});

        // 初始化echarts图表对象
        var fpChart = echarts.init(document.getElementById('fp_add_Chart'));
        var busChart = echarts.init(document.getElementById('fp_type_Chart'));

        var fp_option = {
            tooltip: {
                formatter: "{b}<spring:message code="jspage_content_day2"/>  <br/>{a}: {c}<spring:message code="jspage_content_num2"/>"
            },
            xAxis: [
                {
                    name: "(<spring:message code="jspage_content_date"/>)",
                    nameLocation: "end",
                    splitLine: {show: false},
                    data: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"]
                }
            ],
            yAxis: [
                {
                    name: "(<spring:message code="jspage_content_number"/>)",
                    nameLocation: "end"
                }
            ],
            toolbox: {
                show: true,
                feature: {
                    magicType: {
                        show: true,
                        type: ['line', 'bar'],
                        title: {
                            line: '<spring:message code="echarts_switchToLine_title"/>',
                            bar: '<spring:message code="echarts_switchTobar_title"/>',
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
            calculable: true,
            series: [
                {
                    name: "<spring:message code="jspage_content_fingerprintIncreaseNum"/>",
                    type: "line",
                    data: [${homeData.fpAddData}]

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

        var bus_option = {
            tooltip: {
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            toolbox: {
                show: true,
                feature: {
                    restore: {show: true},
                    saveAsImage: {show: true}
                }
            },
            legend: {
                orient: 'vertical',
                x: 'right',
                y: 'center',
                data: ['<spring:message code="java_commonController_fingerprintRegister"/>', '<spring:message code="java_commonController_fingerprintCompare"/>', '<spring:message code="java_commonController_fingerprintExport"/>', '<spring:message code="java_commonController_fingerprintImport"/>', '<spring:message code="java_commonController_fingerprintLogout"/>']
            },
            calculable: true,
//          selectedOffset: 20,         // 选中是扇区偏移量
            series: [
                {
                    name: '<spring:message code="jspage_content_busType"/>',
                    type: 'pie',
                    radius: '55%',
                    center: ['50%', '60%'],
                    data: [${homeData.busTypeData}]
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
        }

        // 为echarts对象加载数据
        fpChart.setOption(fp_option);
        busChart.setOption(bus_option);


        var serverStatus = new Array("<spring:message code="engine_normal"/>", "<spring:message code="engine_warning"/>", "<spring:message code="engine_fault"/>")
        $.each(${homeData.serverStatuData}, function (i, n) {
            $("#server-status").append("<li class='moreLi'><h6>" + serverStatus[i] + "</h6>" + n + "</li>");
        });

        var taskStatus = new Array("<spring:message code="jspage_content_waitDispose"/>", "<spring:message code="jspage_content_disposing"/>", "<spring:message code="jspage_content_finish"/>")
        $.each(${homeData.taskStatuData}, function (i, n) {
            $("#task-status").append("<li class='moreLi'><h6>" + taskStatus[i] + "</h6>" + n + "</li>");
        });

        var busSucNum = 100 - ${homeData.busFailPercent};
        $("#bus-status").html("<li><h6><spring:message code="js_success"/></h6>" + busSucNum + " %</li><li><h6><spring:message code="js_failed"/></h6>" + ${homeData.busFailPercent}+" %</li>");

        $("#log-table").on("change", function () {
            $("#sys-log-table").toggle();
            $("#bus-log-table").toggle();
        });
	</script>
</body>
</html>
