$(function() {
	// 数据
	var proCity = {
		"dataKeyId:1431:province" : {
			name : '北京市',
			citys : {
				"0101" : '东城区',
				"0102" : '西城区',
				"0103" : '崇文区',
				"0104" : '宣武区',
				"0105" : '朝阳区',
				"0106" : '海淀区',
				"0107" : '丰台区',
				"0108" : '石景山'
			}
		},
		"dataKeyId:1432:province" : {
			name : '天津市',
			citys : {}
		},
		"dataKeyId:1433:province" : {
			name : '河北省',
			citys : {}
		},
		"dataKeyId:1434:province" : {
			name : '山西省',
			citys : {}
		},
		"dataKeyId:1435:province" : {
			name : '内蒙古自治区',
			citys : {}
		},
		"dataKeyId:1436:province" : {
			name : '辽宁省',
			citys : {}
		},
		"dataKeyId:1437:province" : {
			name : '吉林省',
			citys : {}
		},
		"dataKeyId:1438:province" : {
			name : '黑龙江省',
			citys : {}
		},
		"dataKeyId:1439:province" : {
			name : '上海市',
			citys : {}
		},
		"dataKeyId:1440:province" : {
			name : '江苏省',
			citys : {}
		},
		"dataKeyId:1441:province" : {
			name : '浙江省',
			citys : {}
		},
		"dataKeyId:1442:province" : {
			name : '安徽省',
			citys : {}
		},
		"dataKeyId:1443:province" : {
			name : '福建省',
			citys : {}
		},
		"dataKeyId:1444:province" : {
			name : '江西省',
			citys : {}
		},
		"dataKeyId:1445:province" : {
			name : '山东省',
			citys : {}
		},
		"dataKeyId:1446:province" : {
			name : '河南省',
			citys : {}
		},
		"dataKeyId:1447:province" : {
			name : '湖北省',
			citys : {}
		},
		"dataKeyId:1448:province" : {
			name : '湖南省',
			citys : {}
		},
		"dataKeyId:1449:province" : {
			name : '广东省',
			citys : {}
		},
		"dataKeyId:1450:province" : {
			name : '广西壮族自治区',
			citys : {}
		},
		"dataKeyId:1451:province" : {
			name : '海南省',
			citys : {}
		},
		"dataKeyId:1452:province" : {
			name : '重庆市',
			citys : {}
		},
		"dataKeyId:1453:province" : {
			name : '四川省',
			citys : {}
		},
		"dataKeyId:1454:province" : {
			name : '贵州省',
			citys : {}
		},
		"dataKeyId:1455:province" : {
			name : '云南省',
			citys : {}
		},
		"dataKeyId:1456:province" : {
			name : '西藏自治区',
			citys : {}
		},
		"dataKeyId:1457:province" : {
			name : '陕西省',
			citys : {}
		},
		"dataKeyId:1458:province" : {
			name : '甘肃省',
			citys : {}
		},
		"dataKeyId:1459:province" : {
			name : '青海省',
			citys : {}
		},
		"dataKeyId:1460:province" : {
			name : '宁夏回族自治区',
			citys : {}
		},
		"dataKeyId:1461:province" : {
			name : '新疆维吾尔自治区',
			citys : {}
		},
		"dataKeyId:1462:province" : {
			name : '香港特别行政区',
			citys : {}
		},
		"dataKeyId:1463:province" : {
			name : '澳门特别行政区',
			citys : {}
		},
		"dataKeyId:1464:province" : {
			name : '台湾省',
			citys : {}
		}
	};
	var allCitys = {};
	for ( var proCode in proCity) {
		var citys = proCity[proCode].citys;
		for ( var p in citys) {
			allCitys[p] = citys[p];
		}
	}

	// 创建省的列表
	function createProvince(proObj, value) {
		proObj.empty();
		var first;
		for ( var proCode in proCity) {
			var pro = proCity[proCode];
			if (!first)
				first = proCode;
			proObj.append("<option value='" + proCode + "'>" + pro.name
					+ "</option>");
		}
		proObj.val(value != undefined ? value : first);
	}
	// 创建市的列表
	function createCity(cityObj, proCode, value) {
		if (cityObj && proCode) {
			cityObj.empty();
			var citys = proCity[proCode]["citys"];
			var first;
			for ( var p in citys) {
				if (!first)
					first = p;
				cityObj.append("<option value='" + p + "'>" + citys[p]
						+ "</option>");
			}
			cityObj.val(value != undefined ? value : first);
		}
	}
	// 初始化省份及其联动
	$('select[data-dictCode=province]').each(function() {
		// 初始化省份
		createProvince($(this), $(this).attr("data-value"));
		var cityField = $(this).attr("data-code-cityField");
		if (cityField) {
			createCity($('#' + cityField), $(this).val());
			$(this).change(function() {
				createCity($('#' + cityField), $(this).val());
			})
		}
		// 初始化市
	});
	// 对显示内容进行转换
	$('span[data-dictCode=province]').each(function() {
		var code = $(this).text();
		if (proCity[code])
			$(this).text(proCity[code].name)
	});
	// 对显示内容进行转换
	$('span[data-dictCode=city]').each(function() {
		var code = $(this).text();
		if (allCitys[code])
			$(this).text(allCitys[code])
	});

})