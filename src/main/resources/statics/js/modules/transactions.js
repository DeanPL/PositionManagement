$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'PM/transactions/list',
        datatype: "json",
        colModel: [
			{ label: 'TransactionID', name: 'transactionID', sortable: false, width: 30 },
			{ label: 'TradeID', name: 'tradeID', width: 30 },
			{ label: 'Version', name: 'version', width: 30 },
			{ label: 'SecurityCode', name: 'securityCode', width: 30 },
			{ label: 'Quantity', name: 'quantity', width: 30 },
			{ label: 'INSERT/UPDATE/CANCEL', name: 'insert_update_cancel', width: 60 },
			{ label: 'Buy/Shell', name: 'buy_sell', width: 60 }
			//{ label: 'remark', name: 'remark', width: 30 },

        ],
		viewrecords: true,
        height: 385,
        rownumbers: true,
        rownumWidth: 25,
        autowidth:true,
        multiselect: true,
        jsonReader : {
            root: "transactions",
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
            paramKey: null
		},
		showList: true,
		title: null,
		transaction: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		modify: function(){
            var transactionID = getSelectedRow();
            if(transactionID == null){
                return ;
            }
            console.log(transactionID);
			vm.showList = false;
			vm.title = "Modify";
			$.get(baseURL + "PM/transactions/info/"+transactionID, function(r){
				vm.transaction=r.transaction;
			});
			
		},
		saveOrUpdate: function (event) {
			var url = "PM/transaction/operation";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.transaction),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		reload: function (event) {
			vm.showList = true;
			$("#jqGrid").jqGrid('setGridParam',{}).trigger("reloadGrid");
		}
	}
});