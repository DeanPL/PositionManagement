//jqGrid的配置信息
$.jgrid.defaults.width = 1000;
$.jgrid.defaults.responsive = true;
$.jgrid.defaults.styleUI = 'Bootstrap';

var baseURL = "../../";

// 工具集合Tools
window.T = {};

// 获取请求参数
// 使用示例
// location.href = http://localhost:8080/index.html?id=123
// T.p('id') --> 123;
var url = function(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
};
T.p = url;

// 全局配置
$.ajaxSetup({
	dataType : "json",
	cache : false
});

// 重写alert
window.alert = function(msg, callback) {
	parent.layer.alert(msg, function(index) {
		parent.layer.close(index);
		if (typeof (callback) === "function") {
			callback("ok");
		}
	});
}

// 重写confirm式样框
window.confirm = function(msg, callback) {
	parent.layer.confirm(msg, {
		btn : [ '确定', '取消' ]
	}, function() {// 确定事件
		if (typeof (callback) === "function") {
			callback("ok");
		}
	});
}

// 选择一条记录
function getSelectedRow() {
	var grid = $("#jqGrid");
	var rowKey = grid.getGridParam("selrow");
	if (!rowKey) {
		alert("请选择一条记录");
		return;
	}
	var selectedIDs = grid.getGridParam("selarrrow");
	if (selectedIDs.length > 1) {
		alert("只能选择一条记录");
		return;
	}

	return selectedIDs[0];
}

// 选择多条记录
function getSelectedRows() {
	var grid = $("#jqGrid");
	var rowKey = grid.getGridParam("selrow");
	if (!rowKey) {
		alert("请选择一条记录");
		return;
	}

	return grid.getGridParam("selarrrow");
}

// 判断是否为空
function isBlank(value) {
	return !value || !/\S/.test(value)
}
function formatTable(cellValue, options, rowObject) {
	var colName = options.colModel.name;
	var jsonStr = JSON.stringify(cellValue);
	if (jsonStr == "null") {
		return null;
	}
	if(jsonStr=="[]"){
		return null;
	}
	var hidden = '<input type="hidden" name="' + colName + '" value=\''
			+ jsonStr + '\'>';
	return '[点击查看详情]' + hidden;
}

function covertNumberToPercent(cellValue, options, rowObject) {
	if (cellValue == null) {
		return null;
	}
	if (cellValue == 0) {
		return 0;
	}
	var str = Number(cellValue * 100).toFixed();
	str += "%";
	return str;
}
function covertToInt(cellValue, options, rowObject) {
	if (cellValue == null) {
		return null;
	} else {
		return parseInt(cellValue);
	}
}
String.prototype.endWith = function(endStr) {
	var d = this.length - endStr.length;
	return (d >= 0 && this.lastIndexOf(endStr) == d);
}
String.prototype.startsWith = function(str) {
	return this.slice(0, str.length) == str;
}
