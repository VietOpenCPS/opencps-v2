

var funLoadVue = function(stateWindowParam, dossierIdParam, dossierPartNo, emailAddress){
	const config = {
		headers: {
			'groupId': themeDisplay.getScopeGroupId()
		}
	};

	var dossierViewJX = new VueJX({
		el: 'dossierViewJX',
		pk: 1,
		groupid: themeDisplay.getScopeGroupId(),
		data: {
			offsetTop: 0,
			stageFilterView: null,
			detailPage: false,
			detailRegistPage: false,
			viewmore: false,
			detailModel: {},
			detailRegistModel: {},
			xem_them: 'Không tìm thấy hồ sơ nào',
			hoso_title_table: 'Danh sách hồ sơ',
			processSteps: [],
			stepModel: null,
			showContactDetail: false,
			dossierFiles: [],
			statusParamFilter: null,
			substatusParamFilter: null,
			loadingAlpacajsForm: false,
			stepLoading: false,
			actionsSubmitLoading: false,
			popupResultFile: false,
			traCuuFilter: false,
			alapcaJSRei: {},
			menu: false,
			dateFormatted: null,
			advanced_filter: false,
			alpacaAssignUserId: 0,
			subUsers: [],
			currentCounter : 0,
			currentCounterTemp : 0,
			listgroupHoSoFilterselectedIndex: -1,
			follow : true
		},
		onScroll: 'onScroll',
		schema: {
				'navigationFilter': {
					'id': 'navigationFilter',
					'name': 'navigationFilter',
					"type": "navigation",
					'cssClass': 'pr-4 pt-0',
					"template": "menu_template",
					"template_content": "dossierViewJX_form_template",
					'events': {
						deleteDossierFileVersion: function (item) {
							var vm = this;
							
							vm.$dialog.confirm('Bạn có muốn xóa file toàn bộ file của thành phần này!', {
								html: true,
								loader: true,
								okText: 'Xác nhận',
								cancelText: 'Quay lại',
								animation: 'fade'
							})
							.then((dialog) => {
								console.log("Run delete");

									var urlFiles = "/o/rest/v2/dossiers/"+vm.detailModel.dossierId+"/files/"+item.referenceUid+"/resetformdata";
									
									$.ajax({
										url : urlFiles,
										dataType : "json",
										type : "PUT",
										headers : {
											groupId : themeDisplay.getScopeGroupId()
										},
										success : function(result){
											item.counter = 0;
										},
										error : function(xhr){
											console.log(xhr);
										}
									});
									
									dialog.close();
									return false; 
								})
							.catch((e) => {
								console.log(e)
							})
						},
						deleteDossierPDF: function(item, index) {
							var vm = this;

							vm.$dialog.confirm('Bạn có muốn xóa tài liệu này!', {
								html: true,
								loader: true,
								okText: 'Xác nhận',
								cancelText: 'Quay lại',
								animation: 'fade'
							})
							.then((dialog) => {
								$.ajax({
									url : "/o/rest/v2/dossiers/"+vm.detailModel.dossierId+"/files/"+item.referenceUid,
									dataType : "json",
									type : "DELETE",
									headers: {
										"groupId": themeDisplay.getScopeGroupId()
									},
									success : function(result){
										vm.snackbartextdossierViewJX = "Xoá dữ liệu thành phần hồ sơ thành công!";
										vm.snackbardossierViewJX = true;
										vm.listDocumentOutStepItems.splice(index, 1);
										vm.stepModel.createFiles[item.index].counter = vm.stepModel.createFiles[item.index].counter - 1;
									},
									error : function(result){
										vm.snackbartextdossierViewJX = "Xoá dữ liệu thành phần hồ sơ thất bại!";
										vm.snackbarerordossierViewJX = true;
									}
								});
								dialog.close();
							})
							.catch((e) => {
								console.log(e)
							})
						},
						submitAlpacajsForm: function (item) {
							var vm = this;
							vm.loadingAlpacajsForm = true;
							try{
								console.log("$('#alpacajs_form_'+item.partNo + ' .formType').val()============",$("#alpacajs_form_"+item.partNo + " .formType").val());
								if( $("#alpacajs_form_"+item.partNo + " .formType").val() == null || 
									$("#alpacajs_form_"+item.partNo + " .formType").val() == 'undefined' ) {
									console.log("formType 1");
								vm.loadingAlpacajsForm = true;

								var control = $("#alpacajs_form_"+item.partNo).alpaca("get");
								var formData = control.getValue();

								$.ajax({
									url : "/o/rest/v2/dossiers/"+vm.detailModel.dossierId+"/files/"+item.referenceUid+"/formdata",
									dataType : "json",
									type : "PUT",
									headers: {
										"groupId": themeDisplay.getScopeGroupId(),
										Accept : "application/json"
									},
									data : {
										formdata: JSON.stringify(formData)
									},
									success : function(result){
										vm.snackbartextdossierViewJX = "Lưu form thành công!";
										vm.snackbardossierViewJX = true;
										vm.loadingAlpacajsForm = false;

										try{
											
											if(item.hasSubmit){

											}else {

												item.counter ++;
												item.hasSubmit = true;
											}


										}catch(e){

										}
									},
									error : function(result){
										vm.snackbartextdossierViewJX = "Lưu form thất bại!";
										vm.snackbarerordossierViewJX = true;
										vm.loadingAlpacajsForm = false;
									}
								});

							} else if( $("#alpacajs_form_"+item.partNo + " .formType").val() != null && 
								$("#alpacajs_form_"+item.partNo + " .formType").val() === 'assign' ) {
								console.log("formType 2");
								vm.loadingAlpacajsForm = true;

								var control = $("#alpacajs_form_"+item.partNo).alpaca("get");
								var formData = control.getValue();


								vm.subUsers = formData['subUsers'];
								vm.alpacaAssignUserId = formData['userAction'];

								$.ajax({
									url : "/o/rest/v2/dossiers/"+vm.detailModel.dossierId+"/files/"+item.referenceUid+"/formdata",
									dataType : "json",
									type : "PUT",
									headers: {
										"groupId": themeDisplay.getScopeGroupId(),
										Accept : "application/json"
									},
									data : {
										formdata: JSON.stringify(formData)
									},
									success : function(result){
										vm.snackbartextdossierViewJX = "Lưu form thành công!";
										vm.snackbardossierViewJX = true;
										vm.loadingAlpacajsForm = false;

										try{
											if(item.hasSubmit){

											}else {

												item.counter ++;
												item.hasSubmit = true;
											}

										}catch(e){

										}
									},
									error : function(result){
										vm.snackbartextdossierViewJX = "Lưu form thất bại!";
										vm.snackbarerordossierViewJX = true;
										vm.loadingAlpacajsForm = false;
									}
								});


							} else {
								vm.loadingAlpacajsForm = true;
								setTimeout(
									function(){ 
										try{
											vm.loadingAlpacajsForm = false;
											if(item.hasSubmit){

											}else {

												item.counter ++;
												item.hasSubmit = true;
											}


										}catch(e){

										}
										
									}, 
									3000);
							}
						}catch(e){
							vm.loadingAlpacajsForm = false;
						}
					},
					showAlpacaJSFORM: function (item) {
							//alapcajs Form
							var alpacajsForm = document.getElementById("alpacajs_form_"+item.partNo);
							if (alpacajsForm.innerHTML == '' && item.eform) {
								console.log(item);
								var alapcaJS = eval('('+item.formScript+')');
								alapcaJS['data'] = item.formData;
								
								$("#alpacajs_form_"+item.partNo).alpaca(alapcaJS);
							}
							
						},
						viewOnNewTab : function(item){
							var vm = this;
							if(item.counter > 0){
								console.log("vm.detailModel========",vm.detailModel);

								var url = "/group/cong-xu-ly/xu-ly-ho-so?stateWindow=stateWindow&dossierId="+vm.detailModel.dossierId+
								"&dossierPartNo="+item.partNo;

								console.log("url============",url);

								window.open(url, "_blank");

							}

						},
						parseDateUtc : function(date){
							return moment(String(date)).utc().format('DD/MM/YYYY HH:mm:ss');
						},
						showAlpacaJSFORMRegist: function (item) {
							var vm = this;
							//alapcajs Form
							var url = '/o/rest/v2/registrations/'+vm.detailRegistModel.registrationId+'/forms/' + item.referenceUid +"/formscript";
                            // var url = '/o/frontendwebdossier/json/steps.json';
                            vm.alapcaJSRei = {};
                            axios.get(url, config).then(function (response) {
                            	var serializable = response.data;

                            	vm.alapcaJSRei = eval('('+serializable+')');

                            	axios.get('/o/rest/v2/registrations/'+vm.detailRegistModel.registrationId+'/forms/' + item.referenceUid +"/formdata", 
                            		config).then(function (responseFormData) {

                            			vm.alapcaJSRei['data'] = responseFormData.data;
                            			$("#regist_form_"+item.referenceUid).alpaca(vm.alapcaJSRei);

                            		})
                            		.catch(function (error) {
                            			console.log(error);

                            		});

                            	})
                            .catch(function (error) {
                            	console.log(error);
                            	vm.stepLoading = false;

                            });
                            return false; 

                        },
                        changeProcessStep: function (item){
                        	var vm = this;
                        	console.log(item);
                        	var status = vm.statusParamFilter;
                        	var subStatus = vm.substatusParamFilter;
                        	vm.stepModel = null;
                        	$("#alpacajs_form_plugin").empty();

                        	if(item.type === 1){
                        		$("textarea#processActionNote").val("");

                        		if (item.hasOwnProperty('createFiles') && !(item.createFiles instanceof Array)) {
                        			var createFilesTemp = item.createFiles;
                        			item.createFiles = [];
                        			item.createFiles.push(createFilesTemp);
                        		}

                        		if (item.autoEvent !== 'submit' && item.autoEvent !== 'timer') {
                        			vm.stepModel = item;
                        		} else {
                        			vm.stepModel = null;
                        		}

                        		vm.processAssignUserIdItems = item.toUsers;

                        	}else {

                        		var urlPluginFormData = '/o/rest/v2/dossiers/'+vm.detailModel.dossierId+'/plugins/'+item.processActionId+'/formdata';
                        		var urlPluginFormScript = '/o/rest/v2/dossiers/'+vm.detailModel.dossierId+'/plugins/'+item.processActionId+'/formscript';
                        		var config_plugins = {
                        			headers: {
                        				'groupId': themeDisplay.getScopeGroupId()
                        			},
                        			dataType : "text"
                        		};

                        		axios.all([
                        			axios.get(urlPluginFormScript, config_plugins),
                        			axios.get(urlPluginFormData, config_plugins)
                        			]).then( axios.spread(function (urlResponesFormScript, urlResponesFormData) {
                        				var responseScript = urlResponesFormScript.data;
                        				var responseData = urlResponesFormData.data;

                        				console.log("responseScript==============",responseScript);
                        				item.plugin = true;

                        				if(responseScript.indexOf("#preview@pdf") !== -1){
                        					console.log("view pdf");
                        					var url ="/o/rest/v2/dossiers/"+vm.detailModel.dossierId+"/plugins/"+item.processActionId+"/preview" ;

                        					var config_blob =  {
                        						headers: {
                        							'groupId': themeDisplay.getScopeGroupId(),
                        						},
                        						responseType: 'blob'
                        					};

                        					axios.get(url, config_blob).then(function (response) {
                        						var urlblob = window.URL.createObjectURL(response.data);
                        						item.pdf = true;
                        						item.url = urlblob;
                        						item.no_pdf = '';
                        						vm.stepModel = item;
                        					})
                        					.catch(function (error) {
                        						console.log(error);
                        						item.pdf = true;
                        						item.url = '';
                        						item.no_pdf = 'Tài liệu đính kèm không tồn tại!';
                        						vm.stepModel = item;
                        					});

                        				}


                        				if(responseScript.indexOf("#preview@html") !== -1){
                        					console.log("view html");
                        					var config_view = {
                        						headers: {
                        							'groupId': themeDisplay.getScopeGroupId(),
                        						},
                        						dataType : "json"
                        					};
                        					item.html = true;
                        					item.no_html = "";
                        					vm.stepModel = item;

                        					var url = '/o/rest/v2/dossiers/'+vm.detailModel.dossierId+'/plugins/'+item.processActionId+'/previewhtml';
                        					axios.get(url, config_view).then(function (response) {
                        						item.no_html = "";
                        						vm.stepModel = item;

                        						var serializable = response.data ;

                        						var partNo = serializable.partNo;
                        						var dossierFileId = serializable.dossierFileId;

                        						$(".dossierFilePartNo").val("");
                        						$(".dossierFilePartNo").attr('id','dossierFileId'+partNo);
                        						$(".dossierFilePartNo").val(dossierFileId);

                        						var formReport = eval('('+serializable.formReport+')');
                        						var formData = eval('('+serializable.formData+')');

                        						console.log("formReport======",formReport);
                        						console.log("formData======",formData);
                        						/*var formData = serializable.formData;*/


                        						formReport.data = formData;
                        						console.log("formReport_____FINAL=======",formReport);
                        						$("#alpacajs_form_plugin").alpaca(formReport);
                        						

                        					})
                        					.catch(function (error) {
                        						console.log(error);
                        						item.html = true;
                        						item.no_html = "Tài liệu đính kèm không tồn tại!";
                        						vm.stepModel = item;
                        					});
                        				}

                        			}))
                        			.catch(function (error) {
                        				console.log(error);

                        			});
                        		}



                        	},
                        	refreshProcess : function(){
                        		var vm = this;
                        		vm._initchangeProcessStep();
                        		vm.stepModel = null;
                        	}
                    },
                    'listgroupHoSoFilter': {
                    	'id': 'listgroupHoSoFilter',
                    	'name': 'listgroupHoSoFilter',
                    	"type": "listgroup",
                    	'show_counter': true,
                    	'counter': 'count',
                    	"child_items": "items",
                    	"data_title": "title",
                    	"data_value": "id",
                    	"onLoad": "_initlistgroupHoSoFilter",
                    	"onClick": "groupHoSoFilter",
                    	"events": {
                    		groupHoSoFilter: function(item){
                    			var vm = this;

                    			console.log("item status",item);

                    			vm.reloadCounter();
                    			
                    			
                    			vm.detailPage = false;
                    			vm.detailRegistPage = false;
                    			vm.listgroupHoSoFilterselected = item.id;
                    			vm.danhSachHoSoTablepage = 1;

                    			vm.listgroupHoSoFilterselectedIndex = item.index - 1;

                    			if ( item.id !== 'tra_cuu' ){
                    				vm.stageFilterView = item.id;
                    				vm.hoso_title_table = item.title;
                    				if (item.level === 1) {
                    					vm.statusParamFilter = item.idSub;
                    					vm.substatusParamFilter = item.id;
                    				} else {
                    					vm.statusParamFilter = item.id;
                    					vm.substatusParamFilter = item.idSub;
                    				}

                    			} 



                    			if (item.id == 'tra_cuu_hoso') {
                    				vm._initraCuuHoSoTable(false);
                    			} else if (item.id == 'tra_cuu_phuong_tien') {
								
							} else if (item.id == 'tra_cuu_thong_tin_doanh_nghiep') {
								vm._inithongTinDoanhNghiepTable(false);
								vm.detailRegistPage = false;
							} else {
								vm._inidanhSachHoSoTable(false);
							}
							
							vm.onScrollTop();

						},
						reloadCounter : function(){
							var vm = this;

							vm.stageFilterView = 'danh_sach';

							var url = '/o/rest/v2/statistics/dossiers/todo';
							$.ajax({
								url : url,
								dataType : "json",
								type : "GET",
								headers : {
									'groupId': themeDisplay.getScopeGroupId(),
								},
								success : function(result){
									var serializable = result;

									var indexTree = -1;
									var index = 0;
									for (var key in serializable.data) {
										for(var i in vm.listgroupHoSoFilterItems){
											if ( serializable.data[key].level === 0) {

												if (serializable.data[key].dossierStatus === 'cancelling' ||
													serializable.data[key].dossierStatus === 'cancelled' ||
													serializable.data[key].dossierStatus === 'processing' ||
													serializable.data[key].dossierStatus === 'paid') {
													serializable.data[key].items = [];

												if(serializable.data[key].dossierStatus === vm.listgroupHoSoFilterItems[i].id){
													vm.listgroupHoSoFilterItems[i].count = serializable.data[key].count;
												}
											}

										} else {

											if(serializable.data[key].dossierSubStatus === vm.listgroupHoSoFilterItems[i].id){
												vm.listgroupHoSoFilterItems[i].count = serializable.data[key].count;
											}
										}
									}

								}

							},
							error : function(result){

							}
						});	
							
						},
						_initlistgroupHoSoFilter: function(select){
							var vm = this;

							vm.newTabOrNewWindown();
							vm.setStateOnlyFollow(emailAddress);
							vm._inidanhSachHoSoTable(false);

							vm.stageFilterView = 'danh_sach';

							vm.listgroupHoSoFilterItems = [];

							var url = '/o/rest/v2/statistics/dossiers/todo';
							
							axios.get(url, config).then(function (response) {
								var serializable = response.data;

								var indexTree = -1;
								var index = 0;
								for (var key in serializable.data) {
									
									if ( serializable.data[key].level === 0) {
										
										if (serializable.data[key].dossierStatus === 'cancelling' ||
											serializable.data[key].dossierStatus === 'cancelled' ||
											serializable.data[key].dossierStatus === 'processing' ||
											serializable.data[key].dossierStatus === 'paid') {
											serializable.data[key].items = [];

										vm.listgroupHoSoFilterItems.push({
											id: serializable.data[key].dossierStatus,
											idSub: serializable.data[key].dossierSubStatus,
											title: serializable.data[key].statusName,
											level: serializable.data[key].level,
											count: serializable.data[key].count,

											action: 'folder',
											action_active: 'folder_open',
											items: [],
											index: index
										});
									}

								} else {
									
									vm.listgroupHoSoFilterItems.push({
										id: serializable.data[key].dossierSubStatus,
										idSub: serializable.data[key].dossierStatus,
										title: serializable.data[key].statusName,
										level: serializable.data[key].level,
										count: serializable.data[key].count,
										action: 'folder',
										action_active: 'folder_open',
										items: [],
										index: index
									});
								}
								
							}

							if(select){
								console.log("selected========",select);
								vm.listgroupHoSoFilterselected = select;
							}


						}).catch(function (error) {
								console.log(error);
								
							});
							return false; 
						}
					}
				},
				'listgroupTraCuuFilter': {
					'id': 'listgroupTraCuuFilter',
					'name': 'listgroupTraCuuFilter',
					"type": "listgroup",
					'cssClass': 'pb-5',
					"child_items": "items",
					"data_title": "title",
					"data_value": "id",
					"onLoad": "_initlistgroupTraCuuFilter",
					"onClick": "filterChange",
					"events": {
						filterChange: function(item){
							var vm = this;
							vm.detailPage = false;
							vm.detailRegistPage = false;
							vm.listgroupTraCuuFilterselected = item.id;
							if ( item.id !== 'tra_cuu' ){
								vm.stageFilterView = item.id;
								vm.hoso_title_table = item.title;
							}
							
							if (item.id == 'tra_cuu_hoso') {
								/*vm._initraCuuHoSoTable(false);
								vm._initAdvanced_filter_serviceInfo();
								vm._initAdvanced_filter_loaiSanPham();
								vm._initAdvanced_filter_nhanHieu();
								vm._initAdvanced_filter_dossierStatus();*/
								
							} else if (item.id == 'tra_cuu_phuong_tien') {
								// TODO vm._inidanhSachHoSoTable(false);
							} else if (item.id == 'tra_cuu_thong_tin_doanh_nghiep') {
								//vm._inithongTinDoanhNghiepTable(false);
							}else if(item.id === 'tat_ca_hoso'){

								vm.detailPage = false;
								vm.detailRegistPage = false;
								vm.listgroupHoSoFilterselected = item.id;
								vm.danhSachHoSoTablepage = 1;

								vm.listgroupHoSoFilterselectedIndex = item.index - 1;

								vm.statusParamFilter = '';
								vm.substatusParamFilter = '';

								vm.follow = false;

								vm._inidanhSachHoSoTable(false);

								vm.onScrollTop();
							}
							
						},
						_initlistgroupTraCuuFilter: function(){
							var vm = this;

							vm.listgroupTraCuuFilterItems = [
							{
								id: 'tat_ca_hoso',
								title: 'Tất cả hồ sơ'
							},
							{
								id: 'tra_cuu_hoso',
								title: 'Tra cứu kết quả'
							},
							{
								id: 'tra_cuu_phuong_tien',
								title: 'Phương tiện sản xuất lắp ráp'
							},
							{
								id: 'tra_cuu_thong_tin_doanh_nghiep',
								title: 'Thông tin doanh nghiệp'
							}
							];
							
						}
					}
				},
				"serviceInfoFilter": {
					'id': 'serviceInfoFilter',
					'name': 'serviceInfoFilter',
					"type": "select",
					'label': 'Lựa chọn thủ tục hành chính ',
					"item_text": "serviceName",
					"item_value": "serviceCode",
					"single_line": true,
					"hide_selected": true,
					"multiple": false,
					"tags": false, //custom input not avaliable in select
					"chips": true,
					"deletable_chips": true,
					"loading": false,
					"no_data_text": "Lua chon selected",
					"items": [],
					"onLoad": "_initServiceInfoFilterData",
					"onChange": "_filterDanhSachHoSoOnchange($event)",
					"onRemove": "_filterDanhSachHoSoOnRemove",
					'onClick': '_filterDanhSachHoSoOnClear',
					"events": {
						_initServiceInfoFilterData: function(){
							var vm = this;
							
							//TODO: API
							var url = '/o/rest/v2/serviceinfos';
							
							axios.get(url, config).then(function (response) {
								var serializable = response.data;

								vm.serviceInfoFilterItems = serializable.data;

							})
							.catch(function (error) {
								console.log(error);

							});
							return false; 
							
						},
						_filterDanhSachHoSoOnchange: function(data){
							var vm = this;
							vm.serviceInfoFilter = data;
							vm._inidanhSachHoSoTable(false);
							vm.putStateUserInfo();
						},
						_filterDanhSachHoSoOnRemove: function(){
							var vm = this;
							vm.serviceInfoFilter = [];
							vm.putStateUserInfo();
							vm._inidanhSachHoSoTable(false);
						},
						_filterDanhSachHoSoOnClear: function(){
							this.serviceInfoFilter = [];
						}

					}
				},
				"dossierNoFilter": {
					'id': 'dossierNoFilter',
					'name': 'dossierNoFilter',
					"type": "text",
					'label': 'Nhập mã tiếp nhận (Số hồ sơ)',
					"onChange": "_filterDanhSachHoSoOnchange3",
					"events": {
						_filterDanhSachHoSoOnchange3: function(){
							var vm = this;
							vm._inidanhSachHoSoTable(false);
							vm.putStateUserInfo();
						}
					}
				},
				'danhSachHoSoTable': {
					'id': 'danhSachHoSoTable',
					'name': 'danhSachHoSoTable',
					'type': 'table',
					'no_data_text': 'Không tìm thấy hồ sơ nào!',
					'cssClass': 'danhSachHoSoTable__class',
					'select_all': true,
					'item_key': 'dossierId',
					'headers': 'headers',
					'template': 'danh_sach_hoso_table_template',
					/*'onLoad': '_inidanhSachHoSoTable',*/
					'pagging': '_paggingDanhSachHoSo',
					'next': '_nextDanhSachHoSo',
					'previous': '_previousDanhSachHoSo',
					'events': {
						_inidanhSachHoSoTable: function (append) {
							var vm = this;
							vm.traCuuFilter = false;
							vm.viewmore = true;

							this.danhSachHoSoTableheaders = [
							{
								text: 'STT',
								align: 'left',
								sortable: false,
								value: 'stt'
							},
							{
								text: 'Tên thủ tục. Tên doanh nghiệp',
								align: 'left',
								sortable: true,
								value: 'applicantName'
							},
							{
								text: 'Mã hồ sơ. Số hồ sơ',
								align: 'left',
								sortable: true,
								value: 'dossierId'
							},
							{
								text: 'Ngày gửi. Ngày tiếp nhận. Hạn xử lý',
								align: 'left',
								sortable: true,
								value: 'submitDate'
							},
							{
								text: 'Số chứng chỉ. Ngày cấp',
								align: 'center',
								sortable: false,
								value: 'action'
							},
							{
								text: 'Nội dung',
								align: 'left',
								sortable: false,
								value: 'action'
							},
							{
								text: 'Ghi chú',
								align: 'left',
								sortable: true,
								value: 'lastActionNote'
							}
							];

							var statusParam = vm.statusParamFilter;
							var substatusParam = vm.substatusParamFilter;

								var paramsBuilder = {
									keywordSearchLike: vm.keywordsSearch,
									owner: vm.applicantNameFilter.applicantIdNo,
									service: vm.serviceInfoFilter.serviceCode,
									follow: vm.follow,
									dossierNo: vm.dossierNoFilter,
									start: vm.danhSachHoSoTablepage * 15 - 15,
									end: vm.danhSachHoSoTablepage * 15,
									status: statusParam,
									substatus: substatusParam,
									sort: 'modified',
									order: 'false'
								};

								
								if ( vm.keywordFilter != null ) {
									paramsBuilder['keywordSearchLike'] = vm.keywordFilter;
								} else {
									paramsBuilder['keywordSearchLike'] = vm.keywordsSearchDanhSachHoSo;
								}
								const config_dossiers = {
									params: paramsBuilder,
									headers: {
										'groupId': themeDisplay.getScopeGroupId(),
									}
								};

								var url = '/o/rest/v2/dossiers/dossiersTest';
								
								axios.get(url, config_dossiers).then(function (response) {
									var serializable = response.data;
									
									vm.follow = true;

									if (append) {
										vm.danhSachHoSoTableItems.push.apply(vm.danhSachHoSoTableItems, serializable.data);
									} else if(serializable.data){

										vm.danhSachHoSoTableItems = serializable.data;

										vm.danhSachHoSoTableTotal = Math.ceil(serializable.total / 15);
										
									}else {
										vm.danhSachHoSoTableItems = [];

										vm.danhSachHoSoTableTotal = 0;
									}
									
									if (vm.listgroupHoSoFilterselectedIndex >= 0) {
										console.log('reindex counting ...');
										vm.listgroupHoSoFilterItems[vm.listgroupHoSoFilterselectedIndex]['count'] = serializable.total;
									}
									
									vm.xem_them = 'Xem thêm 8+ bản ghi';
									if (!serializable.data) {
										vm.xem_them = 'Tổng số ( ' + serializable.total + ' ) bản ghi'
									}
									vm.viewmore = false;
									
									// temp fix header
									$('.danhSachHoSoTable__class th[role="columnheader"]').each(function( index ) {
										if ($( this ).attr('aria-label').indexOf("Activate") > 0) {
											$( this ).html($( this ).attr('aria-label').substring(0, $( this ).attr('aria-label').indexOf(":")).replace(/\./g,"<br/>") + ' <i aria-hidden="true" class="material-icons icon">arrow_upward</i>');
										} else {
											$( this ).html($( this ).attr('aria-label').substring(0, $( this ).attr('aria-label').indexOf(":")).replace(/\./g,"<br/>"));
										}
									});
									
								})
								.catch(function (error) {
									console.log(error);
									vm.danhSachHoSoTableItems = [];

								});
								return false; 
							},
							toDetailHoSo: function (item) {
								
								var vm = this;
								vm.stepModel = null;

								var url = '/o/rest/v2/dossiers/'+item.dossierId;
								
								axios.get(url, config).then(function (response) {
									var serializable = response.data;

									vm.detailModel = serializable;
									vm.detailModel.dossierIdCTN = item.dossierIdCTN;
									vm.detailPage = true;
									window.scrollBy(0, -99999);
									
									vm._inilistDocumentIn(vm.detailModel);
									vm._inilistDocumentOut(vm.detailModel);

								})
								.catch(function (error) {
									console.log(error);

								});
								return false; 
							},
							_paggingDanhSachHoSo: function() {
								this._inidanhSachHoSoTable(false);
							},
							_nextDanhSachHoSo: function() {
								
							},
							_previousDanhSachHoSo: function() {
								
							}
						}
				},
				'listDocumentIn': {
					'id': 'listDocumentIn',
					'name': 'listDocumentIn',
					'type': 'listview',
					'template': 'list_document_in_template',
					'events': {
						_inilistDocumentIn: function (item) {
							var vm = this;

								var url = "/o/rest/v2/dossiertemplates/"+item.dossierTemplateNo;
								var urlFiles = "/o/rest/v2/dossiers/"+item.dossierId+"/files";

								axios.all([
									axios.get(url, config),
									axios.get(urlFiles, config)
									])
								.then(axios.spread(function (urlRespones, urlFilesRespones) {
							    // Both requests are now complete
							    vm.dossierFiles = urlFilesRespones.data.data;

							    var serializable = urlRespones.data;

							    var listIn = [], listOut = [], listAll = [];

							    for(var key in serializable.dossierParts){

							    	var countData = 0;
							    	for(var keyFile in vm.dossierFiles){

							    		if ( vm.dossierFiles[keyFile].dossierPartNo === serializable.dossierParts[key].partNo ) {
							    			countData = countData + 1;
							    			serializable.dossierParts[key].referenceUid = vm.dossierFiles[keyFile].referenceUid;
							    			serializable.dossierParts[key].fileEntryId = vm.dossierFiles[keyFile].fileEntryId;
							    			serializable.dossierParts[key].displayName = vm.dossierFiles[keyFile].displayName;

							    			listAll.push(serializable.dossierParts[key]);
							    		}

							    	}

							    	serializable.dossierParts[key].counter = countData;

							    	if ( serializable.dossierParts[key].partType === 2 ) {
							    		listOut.push(serializable.dossierParts[key]);
							    	} else {
							    		listIn.push(serializable.dossierParts[key]);
							    	}

							    }

							    vm.listDocumentInItems = listIn;
							    vm.listDocumentOutItems = listOut;
								// TEMP
								vm._initCbxDocumentNewTab(listAll);
								
								return Promise.reject();
								
							})).catch(function (error) {
								console.log(error);

							});
							return false;
						},
						viewDossierFileVersion: function (item) {
							var vm = this;

							vm.dossierViewJXTitle = item.partName;
							var listFilesUpload = [];
							for (var key in vm.dossierFiles){
								if (vm.dossierFiles[key].dossierPartNo === item.partNo) {
									if(vm.dossierFiles[key].fileSize > 0){
										listFilesUpload.push(vm.dossierFiles[key]);
									}
									
								}
							}
							vm.listDocumentInPartNoItems = listFilesUpload;
							if (item.counter > 0){
								vm.popUpViewDossierFile = !vm.popUpViewDossierFile;
							}
							
						},
						viewDossierFileVersionNewTabOrWindow: function (item) {
							var vm = this;
							if(item.counter > 0){
								console.log("vm.detailModel========",vm.detailModel);

								var url = "/group/cong-xu-ly/xu-ly-ho-so?stateWindow=stateWindow&dossierId="+vm.detailModel.dossierId+
								"&dossierPartNo="+item.partNo;

								vm.$dialog.confirm('Bạn muốn mở trong tab mới hay cửa sổ mới!', {
									html: true,
									loader: true,
									okText: 'Cửa sổ mới',
									cancelText: 'Tab mới',
									animation: 'fade'
								})
								.then((dialog) => {
									//console.log("dialog============",dialog);

									window.open(url, "_blank", "ccc");

									dialog.close();
									return false; 
								})
								.catch((e) => {
									console.log(e);
									window.open(url, "_blank");
								})
							}
							
						},
						viewDossierFileResult: function (item, index) {
							var vm = this;
							if (item.counter > 0){
								vm.popupResultFile = true;
								// call API get file by dossierId
								var urlFiles = "/o/rest/v2/dossiers/"+vm.detailModel.dossierId+"/files";
								
								axios.get(urlFiles, config).then(function (response) {
									var serializable = response.data;
									var resultsFiles = [];
									for (var key in serializable.data) {
										if (serializable.data[key].dossierPartType === 2 && serializable.data[key].dossierPartNo === item.partNo) {
											serializable.data[key].index = index;
											resultsFiles.push(serializable.data[key]);
										}
									}
									vm.listDocumentOutStepItems = resultsFiles;
									vm.popUpViewDossierFile = !vm.popUpViewDossierFile;
									
								})
								.catch(function (error) {
									console.log(error);
									
								});
							}
							return false; 
						}
					}
				},
				'listDocumentInPartNo': {
					'id': 'listDocumentInPartNo',
					'name': 'listDocumentInPartNo',
					'type': 'listview',
					'template': 'list_document_in_part_no_template'
				},
				'listDocumentOut': {
					'id': 'listDocumentOut',
					'name': 'listDocumentOut',
					'type': 'listview',
					'template': 'list_document_out_template',
					'events': {
						downloadReferenceFile: function (item) {
							// call DownloadFile.
							var vm = this;
							const config_blob = {
								headers: {
									'groupId': themeDisplay.getScopeGroupId(),
								},
								responseType: 'blob'
							};

							var url = '/o/rest/v2/dossiers/'+vm.detailModel.dossierId+'/files/'+item.referenceUid;
							
							axios.get(url, config_blob).then(function (response) {
								var url = window.URL.createObjectURL(response.data);
								//console.log(url);
								window.open(url);
							})
							.catch(function (error) {
								console.log(error);

							});
							return false; 
						},
						_inilistDocumentOut: function (item) {
							var vm = this;

							var url = "/o/rest/v2/dossiertemplates/"+item.dossierTemplateNo;
							var urlFiles = "/o/rest/v2/dossiers/"+item.dossierId+"/files";

							axios.all([
								axios.get(url, config),
								axios.get(urlFiles, config)
								])
							.then(axios.spread(function (urlRespones, urlFilesRespones) {
							    // Both requests are now complete
							    vm.dossierFiles = urlFilesRespones.data.data;

							    var serializable = urlRespones.data;

							    var listIn = [], listOut = [], listAll = [];

							    for(var key in serializable.dossierParts){

							    	var countData = 0;
							    	for(var keyFile in vm.dossierFiles){

							    		if ( vm.dossierFiles[keyFile].dossierPartNo === serializable.dossierParts[key].partNo ) {
							    			countData = countData + 1;
							    			serializable.dossierParts[key].referenceUid = vm.dossierFiles[keyFile].referenceUid;
							    			serializable.dossierParts[key].fileEntryId = vm.dossierFiles[keyFile].fileEntryId;
							    			serializable.dossierParts[key].displayName = vm.dossierFiles[keyFile].displayName;

							    			if(vm.dossierFiles[keyFile].fileSize <= 0){
							    				
							    				countData = countData -1;
							    				
							    			}

							    		}

							    	}

							    	serializable.dossierParts[key].counter = countData;					

							    	if ( serializable.dossierParts[key].partType === 2 ) {
							    		if(serializable.dossierParts[key].partNo.indexOf("KQ") !== -1){
							    			
							    			listOut.push(serializable.dossierParts[key]);
							    		}

							    	} 

							    }

							    vm.listDocumentOutItems = listOut;

							    return Promise.reject();

							})).catch(function (error) {
								console.log(error);

							});
							return false;
						}
					}
				},
				'listDocumentOutStep': {
					'id': 'listDocumentOutStep',
					'name': 'listDocumentOutStep',
					'type': 'listview',
					'template': 'list_document_out_part_no_template'
				},
				'listHistoryProcessing': {
					'id': 'listHistoryProcessing',
					'name': 'listHistoryProcessing',
					'type': 'table',
					'cssClass': 'table_history_style',
					'hide_headers': true,
					'template': 'list_history_processing_template',
					'onLoad': '_inilistHistoryProcessing',
					'events': {
						_inilistHistoryProcessing: function () {

							var vm = this;
							vm.listHistoryProcessingItems = [];

						},
						selectDossierActionTab(){
							
							var vm = this;
							
							//var url = "/o/frontendwebdossier/json/dossier_logs.json?t=1";
							var url = "/o/rest/v2/dossierlogs/"+vm.detailModel.dossierId+"/logs";
							vm.listHistoryProcessingItems = [];
							axios.get(url, config).then(function (response) {
								var serializable = response.data;
								for (var key in serializable.data) {
									if (serializable.data[key].notificationType === 'PROCESS_TYPE') {
										serializable.data[key].createDate = vm.parseDateUtc(serializable.data[key].createDate);
										var stepNameTmp = serializable.data[key].payload.stepName;
										if(stepNameTmp){
											if(stepNameTmp === "type_cancelling"){
												stepNameTmp = "Yêu cầu hủy";
											}else if(stepNameTmp === "type_submit"){
												stepNameTmp = "Yêu cầu sửa thành phần hồ sơ";
											}else if(stepNameTmp === "type_submitting"){
												stepNameTmp = "Yêu cầu sửa đổi bổ sung";
											}else if(stepNameTmp === "type_correcting"){
												stepNameTmp = "Yêu cầu chỉnh sửa kết quả";
											}else if(stepNameTmp === "type_reject_cancelling"){
												stepNameTmp = "Từ chối yêu cầu hủy";
											}else if(stepNameTmp === "type_reject_submit"){
												stepNameTmp = "Hủy yêu cầu sửa thành phần hồ sơ";
											}else if(stepNameTmp === "type_reject_submitting"){
												stepNameTmp = "Từ chối yêu cầu bổ sung";
											}else if(stepNameTmp === "type_reject_correcting"){
												stepNameTmp = "Hủy yêu cầu chỉnh sửa kết quả";
											}
										}
										serializable.data[key].payload.stepName = stepNameTmp;

										vm.listHistoryProcessingItems.push(serializable.data[key]);
									}
								}
							})
							.catch(function (error) {
								console.log(error);
								
							});
							return false; 
						},
						downloadFile( fileAttachId){
							var vm = this;
							var url = "/o/rest/v2/dossiers/"+vm.detailModel.dossierId+"/files/"+fileAttachId;
							const config_blob = {
								headers: {
									'groupId': themeDisplay.getScopeGroupId(),
								},
								responseType: 'blob'
							};
							axios.get(url, config_blob).then(function (response) {
								var url = window.URL.createObjectURL(response.data);
								window.open(url);
							})
							.catch(function (error) {
								console.log(error);

							});
							return false; 
						}
					}
				},
                "processAssignUserId": {
                	'id': 'processAssignUserId',
                	'name': 'processAssignUserId',
                	"type": "select",
                	'required': true,
                	'label': 'Lựa chọn cán bộ phân công xử lý ',
                	"item_text": "userName",
                	"item_value": "userId",
                	"hide_selected": true,
                	"chips": true,
                	"deletable_chips": true,
                	"loading": false,
                	"no_data_text": "Lua chon selected",
                	"items": [],
                	'onLoad': '_initprocessAssignUserId',
                	'events': {
                		_initprocessAssignUserId: function () {

                			this.processAssignUserIdItems = [
                			{
                				"userId": 1,
                				"userName": "userName1",
                				"moderator": false
                			},
                			{
                				"userId": 2,
                				"userName": "userName2",
                				"moderator": false
                			},
                			{
                				"userId": 3,
                				"userName": "userName3",
                				"moderator": false
                			},
                			{
                				"userId": 4,
                				"userName": "userName4",
                				"moderator": false
                			}
                			];
                		}
                	}
                },
				'popUpViewDossierFile' : {
					'id': 'popUpViewDossierFile',
					'name': 'popUpViewDossierFile',
					"type": "dialog",
					"type_dialog": "fullScreen",
					'icon_save': 'undo',
					'label_save': 'Quay lại',
					"color": "primary",
					"template": "popUpViewDossierFileTemplate",
					"events": {
						popUpViewDossierFileClose: function () {
							console.log("close popup");
							var iFrame = document.getElementById("dossierPDFView");
							var dossierPDFViewNotFound = document.getElementById("dossierPDFViewNotFound");
							iFrame.innerHTML = '';
							this.popUpViewDossierFile = false;
							this.popupResultFile = false;
						},
						popUpViewDossierFileSave: function () {
							console.log("save popup");
							var vm = this;
							var iFrame = document.getElementById("dossierPDFView");
							iFrame.innerHTML = '';
							dossierPDFViewNotFound.innerHTML = '';
							vm.popUpViewDossierFile = false;
							vm.popupResultFile = false;
						},
						previewDossierPDF: function (item) {
							var vm = this;
							vm.dossierViewJXTitle = item.displayName;
							// TODO: call API lay file
							var url ="/o/rest/v2/dossiers/"+vm.detailModel.dossierId+"/files/"+item.referenceUid ;
							// var url = "/o/rest/v2/dossiers/14203/files/a148ee9b-b1a7-b2e7-ca0e-e6503a65b8eb";
							vm._showFile({
								config : {
									headers: {
										'groupId': themeDisplay.getScopeGroupId(),
									},
									responseType: 'blob'
								},
								url : url
							});

						},
						_showFile: function (options) {
							var iFrame = document.getElementById("dossierPDFView");
							var dossierPDFViewNotFound = document.getElementById("dossierPDFViewNotFound");
							axios.get(options.url, options.config).then(function (response) {
								var url = window.URL.createObjectURL(response.data);
								iFrame.className = "";
								iFrame.innerHTML = '<iframe src="'+url+'" width="100%" height="100%"> </iframe>';
								dossierPDFViewNotFound.innerHTML = '';
							})
							.catch(function (error) {
								console.log(error);
								dossierPDFViewNotFound.innerHTML = 'Tài liệu đính kèm không tồn tại!';

							});
							return false; 
						},
						previewDossierPDFNewTab: function (item) {
							var vm = this;
							var url ="/o/rest/v2/dossiers/"+vm.detailModel.dossierId+"/files/"+item.referenceUid ;
							
							const config_blob = {
								headers: {
									'groupId': themeDisplay.getScopeGroupId(),
								},
								responseType: 'blob'
							};
							
							axios.get(url, config_blob).then(function (response) {
								var url = window.URL.createObjectURL(response.data);
								window.open(url);
							})
							.catch(function (error) {
								console.log(error);

							});
							return false; 
						}
					}
				},
				"cbxDocumentNewTab": {
					'id': 'cbxDocumentNewTab',
					'name': 'cbxDocumentNewTab',
					"type": "select",
					'label': 'Xem tài liệu trong tabs mới',
					"item_text": "displayName",
					"item_value": "referenceUid",
					"single_line": true,
					"hide_selected": true,
					"loading": false,
					"no_data_text": "Lua chon selected",
					"items": [],
					"onChange": "_newTabCbxDocumentNewTab($event)",
					"events": {
						_initCbxDocumentNewTab: function(listAll){
							var vm = this;
							
							vm.cbxDocumentNewTabItems = listAll;
						},
						_newTabCbxDocumentNewTab: function(data) {
							var vm = this;
							const config_blob = {
								headers: {
									'groupId': themeDisplay.getScopeGroupId(),
								},
								responseType: 'blob'
							};

							var url = '/o/rest/v2/dossiers/'+vm.detailModel.dossierId+'/files/'+data.referenceUid;
							
							axios.get(url, config_blob).then(function (response) {
								var url = window.URL.createObjectURL(response.data);
								//console.log(url);
								window.open(url);
							})
							.catch(function (error) {
								console.log(error);

							});
							return false; 
						}

					}
				},
                // TODO ADVANCED SEARCH
                "advanced_filter_serviceInfo": {
                	'id': 'advanced_filter_serviceInfo',
                	'name': 'advanced_filter_serviceInfo',
                	"type": "select",
                	'cssClass': 'no-wrap',
                	"item_text": "deliverableName",
                	"item_value": "deliverableType",
                	'label': 'Lựa chọn loại chứng chỉ',
                	"hide_selected": true,
                	"combobox": true,
                	"loading": false,
                	"no_data_text": "Lua chon selected",
                	"items": [],
                	"onChange": "_filterAdvancedServiceInfoOnchange($event)",
                	'events': {
                		_initAdvanced_filter_serviceInfo: function () {
                			var vm = this;
                			var url = "/o/rest/v2/deliverabletypes";
                			axios.get(url, config).then(function (response) {
                				var serializable = response.data;
                				vm.advanced_filter_serviceInfoItems = serializable.data;
                				console.log(vm.advanced_filter_serviceInfoItems);
                			})
                			.catch(function (error) {
                				console.log(error);

                			});
                		},
                		_filterAdvancedServiceInfoOnchange : function(data){
                			var vm = this;
                			vm.advancedFilterServiceInfo = data;
                		}
                	}
                }
            }
        });

		dossierViewJX._builder('dossierViewJX');
}
