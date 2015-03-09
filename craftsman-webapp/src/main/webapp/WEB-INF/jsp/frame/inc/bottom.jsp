<script>
	var Main = {};
	Main.rootPath = '<%=request.getContextPath()%>';
</script>
<!-- PACE LOADER - turn this on if you want ajax loading to show (caution: uses lots of memory on iDevices)-->
<script data-pace-options='{ "restartOnRequestAfter": true }'
	src="<%=path%>/vendor/js/plugin/pace/pace.min.js"></script>

<!-- Link to Google CDN's jQuery + jQueryUI; fall back to local -->
<script src="<%=path%>/vendor/js/libs/jquery-2.1.1.min.js"></script>

<script src="<%=path%>/vendor/js/libs/jquery-ui-1.10.3.min.js"></script>
<!-- util -->
<script src="<%=path%>/vendor/js/plugin/moment/min/moment-with-locales.min.js"></script>
<script src="<%=path%>/vendor/js/plugin/moment-timezone/builds/moment-timezone.min.js"></script>

<!-- IMPORTANT: APP CONFIG -->
<script src="<%=path%>/vendor/js/app.config.js"></script>

<!-- BOOTSTRAP JS -->
<script src="<%=path%>/vendor/js/bootstrap/bootstrap.min.js"></script>

<!-- ANGULARJS -->
<script src="<%=path%>/vendor/js/plugin/angular/angular.min.js"></script>
<script src="<%=path%>/vendor/js/plugin/angular-route/angular-route.min.js"></script>
<script src="<%=path%>/vendor/js/plugin/angular-cookies/angular-cookies.min.js"></script>
<script src="<%=path%>/vendor/js/plugin/angular-resource/angular-resource.min.js"></script>
<script src="<%=path%>/vendor/js/plugin/angular-sanitize/angular-sanitize.min.js"></script>
<script src="<%=path%>/vendor/js/plugin/angular-animate/angular-animate.min.js"></script>
<script src="<%=path%>/vendor/js/plugin/angular-aria/angular-aria.min.js"></script>
<script src="<%=path%>/vendor/js/plugin/angular-bootstrap/ui-bootstrap-tpls.min.js"></script>
<script src="<%=path%>/vendor/js/plugin/angular-couch-potato/dist/angular-couch-potato.js"></script>
<script src="<%=path%>/vendor/js/plugin/angular-easyfb/angular-easyfb.min.js"></script>
<!-- <script src="<%=path%>/vendor/js/plugin/angular-google-maps/dist/angular-google-maps.min.js"></script>  -->
<script src="<%=path%>/vendor/js/plugin/angular-google-plus/dist/angular-google-plus.min.js"></script>
<script src="<%=path%>/vendor/js/plugin/angular-load/angular-loader.min.js"></script>
<script src="<%=path%>/vendor/js/plugin/angular-message/angular-messages.min.js"></script>
<script src="<%=path%>/vendor/js/plugin/angular-mock/angular-mocks.js"></script>
<script src="<%=path%>/vendor/js/plugin/angular-touch/angular-touch.min.js"></script>
<script src="<%=path%>/vendor/js/plugin/angular-xeditable/dist/js/xeditable.min.js"></script>
<!-- ANGULARJS PLUGIN -->
<script src="<%=path%>/vendor/js/plugin/pasvaz-bindonce/bindonce.min.js"></script>

<!-- CUSTOM NOTIFICATION -->
<script src="<%=path%>/vendor/js/notification/SmartNotification.min.js"></script>

<!-- JARVIS WIDGETS -->
<script src="<%=path%>/vendor/js/smartwidgets/jarvis.widget.min.js"></script>

<!-- EASY PIE CHARTS -->
<script
	src="<%=path%>/vendor/js/plugin/easy-pie-chart/jquery.easy-pie-chart.min.js"></script>

<!-- SPARKLINES -->
<script src="<%=path%>/vendor/js/plugin/sparkline/jquery.sparkline.min.js"></script>

<!-- JQUERY VALIDATE -->
<script src="<%=path%>/vendor/js/plugin/jquery-validate/jquery.validate.min.js"></script>

<!-- JQUERY BOOTSTRAP VALIDATE -->
<script
	src="<%=path%>/vendor/js/plugin/bootstrapvalidator/dist/js/bootstrapValidator.min.js"></script>

<!-- JQUERY MASKED INPUT -->
<script src="<%=path%>/vendor/js/plugin/masked-input/jquery.maskedinput.min.js"></script>

<!-- JQUERY SELECT2 INPUT -->
<script src="<%=path%>/vendor/js/plugin/select2/select2.min.js"></script>

<!-- JQUERY UI + Bootstrap Slider -->
<script
	src="<%=path%>/vendor/js/plugin/bootstrap-slider/bootstrap-slider.min.js"></script>

<!-- browser msie issue fix -->
<script src="<%=path%>/vendor/js/plugin/msie-fix/jquery.mb.browser.min.js"></script>

<!-- FastClick: For mobile devices -->
<script src="<%=path%>/vendor/js/plugin/fastclick/lib/fastclick.js"></script>

<!--[if IE 8]>

<h1>Your browser is out of date, please update your browser by going to www.microsoft.com/download</h1>

<![endif]-->


<!-- MAIN APP JS FILE -->
<script src="<%=path%>/vendor/js/app.js"></script>


<!-- SmartChat UI : plugin -->
<script src="<%=path%>/vendor/js/smart-chat-ui/smart.chat.ui.min.js"></script>
<script src="<%=path%>/vendor/js/smart-chat-ui/smart.chat.manager.min.js"></script>

<!-- PAGE RELATED PLUGIN(S) -->
<script
	src="<%=path%>/vendor/js/plugin/bootstrap-timepicker/bootstrap-timepicker.min.js"></script>
<script src="<%=path%>/vendor/js/plugin/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="<%=path%>/vendor/js/plugin/datatables-colvis/js/dataTables.colVis.js"></script>
<script src="<%=path%>/vendor/js/plugin/datatables-tabletools/js/dataTables.tableTools.js"></script>
<script src="<%=path%>/vendor/js/plugin/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.js"></script>
<script
	src="<%=path%>/vendor/js/plugin/datatable-responsive/datatables.responsive.min.js"></script>



<!-- Flot Chart Plugin: Flot Engine, Flot Resizer, Flot Tooltip -->
<script src="<%=path%>/vendor/js/plugin/flot/jquery.flot.cust.min.js"></script>
<script src="<%=path%>/vendor/js/plugin/flot/jquery.flot.resize.min.js"></script>
<script src="<%=path%>/vendor/js/plugin/flot/jquery.flot.time.min.js"></script>
<script src="<%=path%>/vendor/js/plugin/flot/jquery.flot.tooltip.min.js"></script>

<!-- Vector Maps Plugin: Vectormap engine, Vectormap language -->
<script
	src="<%=path%>/vendor/js/plugin/vectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script
	src="<%=path%>/vendor/js/plugin/vectormap/jquery-jvectormap-world-mill-en.js"></script>

<!-- Full Calendar -->
<script
	src="<%=path%>/vendor/js/plugin/fullcalendar/jquery.fullcalendar.min.js"></script>

<!-- morris -->
<script src="<%=path%>/vendor/js/plugin/morris/raphael.min.js"></script>
<script src="<%=path%>/vendor/js/plugin/morris/morris.min.js"></script>
<script src="<%=path%>/vendor/js/plugin/morris/morris-chart-settings.min.js"></script>

<!-- echart -->
<script src="<%=path%>/vendor/js/plugin/echart/echarts-all.js"></script>

<!-- common js -->
<script src="<%=path%>/frame/js/frame.js"></script>
