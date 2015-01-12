<!-- Left panel : Navigation area -->
<!-- Note: This width of the aside area can be adjusted through LESS variables -->
<aside id="left-panel">
	<!-- User info -->
			<div class="login-info">
				<span> <!-- User image size is adjusted inside CSS, it should stay as it --> 
					
					<a href="javascript:void(0);" id="show-shortcut" data-action="toggleShortcut">
						<span>
							系统管理员
						</span>
					</a> 
					
				</span>
			</div>
			<!-- end user info -->
	<nav>
		<ul>
			<li><a href="#"><i class="fa fa-lg fa-fw fa-inbox"></i> <span
					class="menu-item-parent">网元管理</span></a>
				<ul>
					<li><a href="<%= path%>/deviceMgr">网络设备配置</a></li>
					<li><a href="#">端口配置</a></li>
				</ul></li>
			<li><a href="#"><i class="fa fa-lg fa-fw fa-desktop"></i> <span
					class="menu-item-parent">业务分析</span></a>
				<ul>
					<li><a href="#">IDC流量分析</a>
						<ul>
							<li><a href="<%= path%>/allBusAnaly?busName=IDC&pid=dataKeyId:1477:business"><i class="fa fa-plane"></i>IDC流量管理(全国)</a></li>
							<li><a href="<%= path%>/proBusAnaly?busName=IDC&pid=dataKeyId:1477:business"><i class="glyphicon glyphicon-plane"></i>IDC流量管理(分省)</a>
							</li>

						</ul></li>
					<li><a href="#">缓存流量分析</a>
						<ul>
							<li><a href="<%= path%>/allBusAnaly?busName=缓存&pid=dataKeyId:1481:business"><i class="fa fa-plane"></i>缓存流量管理(全国)</a></li>
							<li><a href="<%= path%>/proBusAnaly?busName=缓存&pid=dataKeyId:1481:business"><i class="glyphicon glyphicon-plane"></i>缓存流量管理(分省)</a>
							</li>
						</ul></li>
					<li><a href="#">CDN流量分析</a>
						<ul>
							<li><a href="<%= path%>/allBusAnaly?busName=CDN&pid=dataKeyId:1486:business"><i class="fa fa-plane"></i>CDN流量管理(全国)</a></li>
							<li><a href="<%= path%>/proBusAnaly?busName=CDN&pid=dataKeyId:1486:business"><i class="glyphicon glyphicon-plane"></i>CDN流量管理(分省)</a>
							</li>
						</ul></li>
					<li><a href="#">直连流量分析</a>
						<ul>
							<li><a href="<%= path%>/allBusAnaly?busName=直连&pid=dataKeyId:1489:business"><i class="fa fa-plane"></i>直连流量管理(全国)</a></li>
							<li><a href="<%= path%>/proBusAnaly?busName=直连&pid=dataKeyId:1489:business"><i class="glyphicon glyphicon-plane"></i>直连流量管理(分省)</a>
							</li>
						</ul></li>
				</ul></li>
			<li><a href="#"><i class="fa fa-lg fa-fw fa-table"></i> <span
					class="menu-item-parent">统计表报</span></a>
				<ul>
					<li><a href="<%=path%>/staReport/portFlowPreTOP">端口流速TopN</a></li>
					<li><a href="#">端口资源统计表</a></li>
					<li><a href="#">设备各项状态明细</a></li>
					<li><a href="#">流量报表查询</a></li>
				</ul></li>
			<li><a href="#"><i class="fa fa-lg fa-fw fa-pencil-square-o"></i>
					<span class="menu-item-parent">系统管理</span></a>
				<ul>
					<li><a href="#">数据字典</a></li>
				</ul></li>
			<li><a href="#"><i class="fa fa-lg fa-fw fa fa-group"></i> <span
					class="menu-item-parent">用户管理</span></a>
				<ul>
					<li><a href="#"> 用户角色关系</a></li>
					<li><a href="blog.html">系统用户管理</a></li>
					<li><a href="gallery.html">菜单管理</a></li>

					<li><a href="#">角色管理</a></li>
					<li><a href="profile.html">角色菜单管理</a></li>
				</ul></li>
		</ul>
	</nav>

	<span class="minifyme" data-action="minifyMenu"> <i
		class="fa fa-arrow-circle-left hit"></i>
	</span>

</aside>
<!-- END NAVIGATION -->