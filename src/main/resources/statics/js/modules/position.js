$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'PM/positions/list',
        datatype: "json",
        colModel: [			
			{ label: 'ID', name: 'id', width: 30, key: true },
			{ label: 'securityCode', name: 'securityCode', sortable: false, width: 60 },
			{ label: 'quantity', name: 'quantity', width: 100 }
        ],
		viewrecords: true,
        height: 560,
        rownumbers: true,
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        jsonReader : {
            root: "positions",
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
		showList: true,
		title: null,
		position: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		buy: function(){
			vm.showList = false;
			vm.title = "Buy";
			vm.position = {};
			vm.position.buyOrSell=vm.title
		},
		sell: function () {
				vm.showList = false;
    			vm.title = "Sell";
    			vm.position = {};
		},
		saveOrUpdate: function (event) {
		    vm.position.buyOrSell=vm.title;
		    console.log("XXX: "+vm.position.buyOrSell);
        	var url = "PM/positions/operation";
        	console.log(vm.position);
        	$.ajax({
        		type: "POST",
        	    url: baseURL + url,
                      contentType: "application/json",
        	    data: JSON.stringify(vm.position),

        	    success: function(r){
        	    	if(r.code === 0){
        				alert('SUCCESSFUL!', function(index){
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
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
            }).trigger("reloadGrid");
		}
	}
});