$(function () {
	var initIndexInfo=null;
    for(var j in vm.indexInfo){
        if(vm.indexInfo[j].selected){
        	initIndexInfo = vm.indexInfo[j];
        }
    }
    var headerCols=null;
    for (var i in vm.header){
    	if(vm.header[i].selected){
    		headerCols=vm.header[i];
    	}
    }
	vm.createTable(initIndexInfo,headerCols);
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			key: "",
			downloadFileds:"current",
			queryFileds:"product",
		},
		historyButton:{
			showButton:true
		},
		indexInfo:{
			"cn_fruitvegetable_index":{
				dataUrl:baseURL + 'pif/china/fruit/list',
				exportUrl:baseURL+'pif/china/fruit/export',
				multiselect:true,
				selected:true
			},
			"cn_fruitvegetable_index_history":{
				dataUrl:baseURL + 'pif/china/fruit/history',
				exportUrl:baseURL+'pif/china/fruit/exporthistory',
				multiselect:false,
				selected:false
			}  
		},
		header:{
			"product":{
				colModel:[
	                      {label:'产品名称', name:'doc.product.productName', width: 120 ,sortable:false},
	                      {label:'产品代码', name:'doc.product.productCode', width: 120 ,sortable:false},
	                      {label:'供应商名称', name:'doc.product.supplierName', width: 120 ,sortable:false},
	                      {label:'供应商代码', name:'doc.product.supplierCode', width: 120 ,sortable:false},
	                      {label:'条形码', name:'doc.product.barcode', width: 120 ,sortable:false},
	                      {label:'品种', name:'doc.product.variety', width: 120 ,sortable:false},
	                      {label:'产地', name:'doc.product.originOfPlace', width: 120 ,sortable:false},
	                      {label:'含油量', name:'doc.product.oilContent', width: 120 ,sortable:false},
	                      {label:'收货温度', name:'doc.product.receivingTemp', width: 120 ,sortable:false},
	                      {label:'储存温度', name:'doc.product.storageTemp', width: 120 ,sortable:false},
	                      {label:'压力', name:'doc.product.pressure', width: 120 ,sortable:false},
	                      {label:'供应季节', name:'doc.product.supplySeason', width: 120 ,sortable:false},
	                      {label:'产品描述', name:'doc.product.productDescription', width: 120 ,sortable:false},
	                      {label:'包装规格', name:'doc.product.packageSpecification', width: 120 ,sortable:false},
						  //
						  { label: '上传者', name: 'doc.operator', width: 80 },
						  { label: '版本', name: 'doc.version', width: 80 },
						  { label: '上传日期', name: 'doc.created', width: 80 }
				         ],
				placeholder:'产品信息',
				selected:true    
			},
			"defectDescription":{
				colModel:[
				          //产品信息
	                      {label:'产品名称', name:'doc.product.productName', width: 120 ,sortable:false},
	                      {label:'产品代码', name:'doc.product.productCode', width: 120 ,sortable:false},
	                      {label:'供应商代码', name:'doc.product.supplierCode', width: 120 ,sortable:false},
						  //缺陷描述					  
	                      {label:'严重缺陷', name:'doc.defectDescription.criticalDefect', width: 120 ,sortable:false},
	                      {label:'主要缺陷', name:'doc.defectDescription.majorDefect', width: 120 ,sortable:false},
	                      {label:'一般缺陷', name:'doc.defectDescription.minorDefect', width: 120 ,sortable:false},
						  //操作信息
						  { label: '上传者', name: 'doc.operator', width: 60 },
						  { label: '版本', name: 'doc.version', width: 40 },
						  { label: '上传时间', name: 'doc.created', width: 140 }					          
				],
				placeholder:'缺陷描述',
				selected:false  
			},			
			"authorisation":{
				colModel:[
				          //产品信息
	                      {label:'产品名称', name:'doc.product.productName', width: 120 ,sortable:false},
	                      {label:'产品代码', name:'doc.product.productCode', width: 120 ,sortable:false},
	                      {label:'供应商代码', name:'doc.product.supplierCode', width: 120 ,sortable:false},
						  //审批授权信息
						  { label: '供应商公司名称', name: 'doc.authorisation.supplierCompany', width: 100 },
						  { label: '供应商负责人', name: 'doc.authorisation.supplierName', width: 130 ,sortable:false},
						  { label: '供应商负责人职位', name: 'doc.authorisation.supplierJobTitle', width: 130 ,sortable:false},
						  { label: '供应商签署日期', name: 'doc.authorisation.supplierDate', width: 120 ,sortable:false},
						  { label: 'ALDI负责人', name: 'doc.authorisation.aldiName', width: 130 ,sortable:false},
						  { label: 'ALDI负责人职位', name: 'doc.authorisation.aldiJobTitle', width: 130 ,sortable:false},
						  { label: 'ALDI签署日期', name: 'doc.authorisation.aldiDate', width: 120 ,sortable:false},
						  //操作信息
						  { label: '上传者', name: 'doc.operator', width: 60 },
						  { label: '版本', name: 'doc.version', width: 40 },
						  { label: '上传时间', name: 'doc.created', width: 140 }						          
							],
				placeholder:'审批授权信息',
				selected:false  
			}
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		reload: function (event) {
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'key': vm.q.key},
                page:page
            }).trigger("reloadGrid");
		},
		exportdata: function(){
			var records = $("#jqGrid").jqGrid('getGridParam','records');//获取页面上显示总条数
			var indexData=null;
		    for(var j in vm.indexInfo){
		        if(vm.indexInfo[j].selected){
		        	indexData = vm.indexInfo[j];
		        }
		    }
			var exportUrl=indexData.exportUrl;
			//var queryStr="";
			//if()
			var params = "key="+vm.q.key+"&downloadFileds="+vm.q.downloadFileds+"&queryFileds="+vm.q.queryFileds+"&limit="+records+"&page=1&order=asc&sidx=";
			window.location.href = exportUrl+"?"+params;
		},
		queryHistory :function (){
			var itemId = getSelectedRow();
			if(itemId){
				var indexData=vm.indexInfo['cn_fruitvegetable_index_history'];
				vm.q.key=itemId;
				vm.historyButton.showButton=false;
				vm.indexInfo['cn_fruitvegetable_index'].selected=false;
				vm.indexInfo['cn_fruitvegetable_index_history'].selected=true;
			    var headerCols=null;
			    for (var i in vm.header){
			    	if(vm.header[i].selected){
			    		headerCols=vm.header[i];
			    	}
			    }
				this.createTable(indexData,headerCols);
			}
		},
		selectQueryFileds:function(ele){
		    for (var i in vm.header){
		    	vm.header[i].selected=false;
		    }
			var queryFileds=ele.target.value;
		    vm.header[queryFileds].selected=true;
			var indexData=null;
			var indexName=null;
		    for(var j in vm.indexInfo){
		        if(vm.indexInfo[j].selected){
		        	indexData = vm.indexInfo[j];
		        	indexName=j;
		        }
		    }
		    var headerCols=vm.header[queryFileds];
		    if(indexName=='cn_fruitvegetable_index'){
		    	 vm.$refs.queryfiled.placeholder=vm.header[queryFileds].placeholder;
		    }
		    this.createTable(indexData,headerCols);
		    this.reload();
		    
		},
		createTable:function(jqridData,headerCols){
			$.jgrid.gridUnload("jqGrid");
			$("#jqGrid").jqGrid({
		        url: jqridData.dataUrl,
		        datatype: "json",
		        postData:vm.q,
		        colModel: headerCols.colModel,
				viewrecords: true,
		        height: 385,
		        width:2000,
		        rowNum: 10,
				rowList : [10,30,50],
		        rownumbers: true, 
		        rownumWidth: 50, 
		        autowidth:true,
		        shrinkToFit:false,
		        multiselect: jqridData.multiselect,
		        pager: "#jqGridPager",
		        jsonReader : {
		            root: "page.list",
		            page: "page.currPage",
		            total: "page.totalPage",
		            records: "page.totalCount"
		        },
		        prmNames : {
		            page:"page", 
		            rows:"limit", 
		            order: "order"
		        },
		        gridComplete:function(){
		        	//隐藏grid底部滚动条
		        	//$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
		        }
		    });
		}
	}
});