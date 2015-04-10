var Fun = {};
Fun.merge = function(obj1, obj2) { // Our merge function
    var result = {}; // return result
    for (var i in obj1) { // for every property in obj1
        if ((i in obj2) && (typeof obj1[i] === "object") && (i !== null)) {
            result[i] = merge(obj1[i], obj2[i]); // if it's an object, merge
        } else {
            result[i] = obj1[i]; // add it to result
        }
    }
    for (i in obj2) { // add the remaining properties from object 2
        if (i in result) { //conflict
            continue;
        }
        result[i] = obj2[i];
    }
    return result;
};

Fun.notify = function(status, rejection) {
    if (/^(alert)|(warning)|(info)$/.test(status)) {
        var colors = {
            alert: "#d26911",
            warning: "#C46A69",
            info: "#1d9d74"
        };
        var icons = {
            alert: "fa fa-exclamation shake animated",
            warning: "fa fa-warning shake animated",
            info: "fa fa-info shake animated"
        };
        rejection = rejection || {
            data: {}
        };
        rejection = {
            status: rejection.status ? rejection.status : (status === "info" || status === "alert" ? "" : ""),
            statusText: rejection.statusText ? rejection.statusText : (status === "info" || status === "alert" ? "信息提示" : "错误提示"),
            data: {
                message: rejection.data.message ? rejection.data.message : (status === "info" || status === "alert" ? "操作成功!" : "操作失败!")
            }
        };
        $.bigBox({
            title: rejection.status + ' ' + rejection.statusText,
            content: rejection.data.message,
            color: colors[status],
            icon: icons[status],
            timeout: 6000
        });
    }
};

Fun.deleteMsgBox = function(yepoCallback , nopeCallback){
    $.SmartMessageBox({
        title: "操作提示!",
        content: "是否确定删除记录!",
        buttons: '[否][是]'
    }, function(ButtonPressed) {
        if (ButtonPressed === "是") {
            yepoCallback();
        }
        else if(ButtonPressed === "否")
        {
            nopeCallback();
        }
    });
};