'use strict';

angular.module('Main.filters').filter('dictCode2Name', function () {
        return function (dictCode, typeCode) {
            var types = Main.dict.data[typeCode];
            if (!types && !types.length)
                throw new Exception("找不到字典: " + code);
            for (var i = 0; i < types.data.length; i++) {
                if (types.data[i].code == dictCode)
                    return types.data[i].name;
            }
        };
    }
)
    .filter('dictName2Code', function () {
        return function (dictName, typeCode) {
            var types = Main.dict.data[typeCode];
            if (!types && !types.length)
                throw new Exception("找不到字典: " + code);
            for (var i = 0; i < types.data.length; i++) {
                if (types.data[i].name == dictName)
                    return types.data[i].code;
            }
        };
    }
)
;
