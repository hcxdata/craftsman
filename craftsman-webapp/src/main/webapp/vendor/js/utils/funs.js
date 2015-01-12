Main.fun = {};
Main.fun.isEmpty = function(v) {
	return v === null || v === undefined
			|| (typeof v == 'string' && v.length() == 0);
}
// 创建一个绑定对象的执行函数
// 如果第三个参数不为空,则执行时使用该参数否则使用用户指定的参数
Main.fun.Fun = function(target, fun, args) {
	// 第二个参数为空,且第一个参数为function，则直接返回
	if (!fun && (typeof (target) == 'function'))
		return target;
	return function() {
		fun.apply(target, Main.fun.isEmpty(args) ? arguments : args)
	};
}
// 当值为空 的时候，返回默认值
Main.fun.nvl = function(v, df) {
	if (v === null || v === undefined)
		return Main.fun.nvl(df, '');
	return v;
}
/**
 * 尝试执行函数 check - function 执行的前提条件，如果不满足则循环尝试，否则直接执行 fun - function 需要循环执行的方法
 * times - 循环的最大次数 time - 每次循环的时间
 */
Main.fun.tryRun = function(check, fun, times, time) {
	if (Main.fun.isEmpty(check) || typeof check != 'function')
		return;
	if (check.call()) {
		if (!Main.fun.isEmpty(fun))
			fun.call();
		return;
	}
	if (Main.fun.isEmpty(fun))
		return;
	if (Main.fun.isEmpty(times))
		times = 0;
	if (Main.fun.isEmpty(time))
		time = 1000;
	var i = 0;
	var con = setInterval(function() {
		if (check.call() || (++i > times && times != -1))
			clearInterval(con);
		fun.call();
	}, time);
}
Main.fun.getTooltipObject = function(str) {
	return str || "";
}
Main.fun.getYesNoCnValue = function(id) {
	var types = net.uni.in1.boolean_yesnovalue;
	for (var i = 0; i < types.length; i++) {
		if (types[i][0] == id)
			return types[i][1];
	}
};
Main.fun.getApplyBillStatus = function(id) {
	var types = net.uni.in1.ApplyBill_status;
	for (var i = 0; i < types.length; i++) {
		if (types[i][0] == id)
			return types[i][1];
	}
};
Main.fun.getDateTemp = function(cur) {// 时间转换
	if (cur == null) {
		return null;
	} else {
		cur = cur.substring(0, 10);
		var datestr = cur.split("-");
		return new Date(datestr[0], datestr[1] - 1, datestr[2]).getTime();
	}
}
Main.fun.getYear = function() {// 至今年以后十年的年数
	var years = [];
	var year = new Date().getYear();
	for (var i = 0; i < 10; i++) {
		years[i] = [ year + i, year + i ];
	}
	return years;
};
// 从字节转换为兆
Main.fun.getMFromByte = function(value) {
	if (Main.fun.isEmpty(value))
		value = 0;
	else
		value = value / 1024 / 1024;
	return Main.fun.NumberFormat(value);
}
// 百分化 (本身就是百分比数)
Main.fun.getPerForpercent = function(value) {
	if (Main.fun.isEmpty(value))
		value == 0;
	return (value).toFixed(2) + "%";
}

// 百分化 (本身是整数)
Main.fun.getPercentage = function(value) {
	if (Main.fun.isEmpty(value))
		value == 0;
	return (value / 100).toFixed(2) + "%";
}
// 百分率 格式化 （本身是小数)
Main.fun.getPerByReal = function(value) {
	if (Main.fun.isEmpty(value))
		value == 0;
	return (value * 100).toFixed(2) + "%";
}

// 百分率 格式化 （本身是小数,保留四位)
Main.fun.getPerByRealForFour = function(value) {
	if (Main.fun.isEmpty(value))
		value == 0;
	return (value * 100).toFixed(4) + "%";
}

Main.fun.NumberFormat = function(v) {
	v = (Math.round((v - 0) * 100)) / 100;
	v = (v == Math.floor(v)) ? v + ".00" : ((v * 10 == Math.floor(v * 10)) ? v
			+ "0" : v);
	v = String(v);
	var ps = v.split('.');
	var whole = ps[0];
	var sub = ps[1] ? '.' + ps[1] : '.00';
	var r = /(\d+)(\d{3})/;
	while (r.test(whole)) {
		whole = whole.replace(r, '$1' + ',' + '$2');
	}
	v = whole + sub;
	if (v.charAt(0) == '-') {
		return '-' + v.substr(1);
	}
	return v;
}
Main.fun.DX = function(n) {// 小写金额转换为大写
	var strOutput = "";
	var strUnit = '千百拾亿千百拾万千百拾元角分';
	n += "00";
	var intPos = n.indexOf('.');
	if (intPos >= 0)
		n = n.substring(0, intPos) + n.substr(intPos + 1, 2);
	strUnit = strUnit.substr(strUnit.length - n.length);
	for (var i = 0; i < n.length; i++)
		strOutput += '零壹贰叁肆伍陆柒捌玖'.substr(n.substr(i, 1), 1)
				+ strUnit.substr(i, 1);
	return strOutput;
}
Main.fun.copy = function(o, c, defaults) { // 复制对象
	// no "this" reference for friendly out of scope calls
	if (defaults) {
		copy(o, defaults);
	}
	if (o && c && typeof c == 'object') {
		for ( var p in c) {
			if (typeof c[p] == 'object') {
				o[p] = Main.fun.copy({}, c[p]);
			} else {
				o[p] = c[p];
			}
		}
	}
	return o;
};
// 打印控制台信息
Main.fun.printLog = function() {
	try {
		console.log(arguments);
	} catch (e) {
		// not support console method (ex: IE)
	}
}
// 将url中的参数信息转换为json对象
Main.fun.params2Json = function(paramsValue) {
	var params = {};
	if (paramsValue) {
		var ar = paramsValue.split("&");
		for (var i = 0; i < ar.length; i++) {
			var va = ar[i].split('=');
			params[va[0]] = va[1];
		}
	}
	;

	return params;
}
// 将json转为url参数
Main.fun.json2Param = function(json, isFirst) {
	var params = "";
	for ( var p in json) {
		if (json[p])
			params += "&" + p + "=" + json[p].toString();
	}
	if (isFirst)
		params = "?" + params.substr(1, params.length);
	return params;
}