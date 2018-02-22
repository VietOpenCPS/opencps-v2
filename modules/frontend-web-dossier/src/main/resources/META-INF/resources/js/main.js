document.addEventListener('DOMContentLoaded', function (event) {
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
				registForms: [],
				alapcaJSRei: {},
				menu: false,
				dateFormatted: null,
				advanced_filter: false,
				alpacaAssignUserId: 0,
				subUsers: [],
				currentCounter : 0,
				currentCounterTemp : 0,
				listgroupHoSoFilterselectedIndex: -1
			},
			onScroll: 'onScroll',
			schema: {
				// TODO menu filter
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
									// call API get file by dossierId

									var urlFiles = "/o/rest/v2/dossiers/"+vm.detailModel.dossierId+"/files/"+item.referenceUid+"/resetformdata";
									

									axios.put(urlFiles, config).then(function (response) {
										item.counter = 0;
										
									})
									.catch(function (error) {
										console.log(error);
										
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

							if( $("#alpacajs_form_"+item.partNo + " .formType").val() == null || 
									$("#alpacajs_form_"+item.partNo + " .formType").val() == 'undefined' ) {
								
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
							} else if( $("#alpacajs_form_"+item.partNo + " .formType").val() != null || 
									$("#alpacajs_form_"+item.partNo + " .formType").val() === 'assign' ) {
								
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
										vm.loadingAlpacajsForm = false;
									}, 
								3000);
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
                            if(item.type === 1){
                            	vm.processActionNote = '';

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
                            		]).then( axios.spread(function (urlResponesFormData, urlResponesFormScript) {
                            			var responseScript = urlResponesFormScript.data;
                            			var responseData = urlResponesFormData.data;

                            			if(responseScript.startsWith("#") || responseData.startsWith("#")){
                            				item.plugin = true;

                            				var url ="/o/rest/v2/dossiers/"+vm.detailModel.dossierId+"/plugins/"+item.processActionId+"/preview" ;

                            				var config_blob =  {
                            					headers: {
                            						'groupId': themeDisplay.getScopeGroupId(),
                            					},
                            					responseType: 'blob'
                            				};

                            				axios.get(url, config_blob).then(function (response) {
                            					var urlblob = window.URL.createObjectURL(response.data);
                            					item.url = urlblob;
                            					item.no_pdf = '';
                            					vm.stepModel = item;
                            				})
                            				.catch(function (error) {
                            					console.log(error);
                            					item.url = '';
                            					item.no_pdf = 'Tài liệu đính kèm không tồn tại!';
                            					vm.stepModel = item;
                            				});

                            			}
                            		}))
                            		.catch(function (error) {
                            			console.log(error);

                            		});
                            }
							
							
							
                        },
						postNextActions: function (item){
							console.log(item);
                           var vm = this;
							vm.actionsSubmitLoading = true;
							var fileArr = item.createFiles;
							var idArr = [];
							// var dossierFileId
							if (fileArr) {
								var length = fileArr.length;
								for (var i = 0; i < length; i++) {
									var fileDetail = fileArr[i];

									var dossierFileId = fileDetail.dossierFileId;
									var dossierPartId = fileDetail.dossierPartId;
									if (dossierFileId && dossierPartId) {
										var strId = dossierFileId + ',' + dossierPartId;
										idArr.push(strId);
									}
								}
							}

							console.log(idArr);

							var url = '/o/rest/v2/dossiers/'+vm.detailModel.dossierId+'/actions';

							var assignUserId = 0;
							var subUsers = '';
							
							if (vm.alpacaAssignUserId != 0) {
								
								assignUserId = vm.alpacaAssignUserId;
								subUsers = JSON.stringify(vm.subUsers);
								
							} else if (vm.processAssignUserId.userId > 0) {
								
								assignUserId = vm.processAssignUserId.userId;
								
							}

							
							var isKyOk = item.eSignature;
							//console.log("plugin().valid ==============>",plugin().valid);
							// TODO
							/*if (isKyOk && !plugin().valid) {
								alert("Plugin is not working :(");
				                vm.actionsSubmitLoading = false;
				                isKyOk = false;
				                return;
							} else if (!isKyOk) {
								isKyOk = true;
							} else if(){
								var x = plugin().Sign('tGp5xOELB59zprQeqh2ks89BgWE=');
								console.log(x);
								isKyOk = true;
							}*/
							if (isKyOk) {
								if (!plugin().valid) {
									alert("Plugin is not working :(");
					                vm.actionsSubmitLoading = false;
					                isKyOk = false;
					                return;
								}
							}
						/*	if(!plugin().valid === 0){
				                alert("Plugin is not working :(");
				                vm.actionsSubmitLoading = false;
				                isKyOk = false;
				                return;
				            } else {
				                var x = plugin().Sign('tGp5xOELB59zprQeqh2ks89BgWE=');
								console.log(x);
								isKyOk = true;
				            }*/
							
							if (!isKyOk) {
								$.ajax({
									url: url,
									headers: {
										"groupId": themeDisplay.getScopeGroupId()
									},
									data: {
										"actionCode": item.actionCode,
										"actionUser": themeDisplay.getUserName(),
										"actionNote": vm.processActionNote,
										"assignUserId": assignUserId,
										"subUsers": subUsers
									},
									type: 'POST',
									dataType: 'json',
									contentType: 'application/x-www-form-urlencoded; charset=utf-8',
									success: function(data, textStatus, xhr) {
									
										vm.snackbartextdossierViewJX = item.actionName + " thành công!";
										vm.snackbardossierViewJX = true;
										
										vm._inidanhSachHoSoTable();
										setTimeout(function(){ 
											vm._initlistgroupHoSoFilter();
										}, 1000);
										vm.detailPage = false;
										vm.actionsSubmitLoading = false;
									
									},
									error: function(xhr, textStatus, errorThrown) {
									
										vm.snackbartextdossierViewJX = item.actionName + " thất bại!";
	                      				vm.snackbarerordossierViewJX = true;
										vm.actionsSubmitLoading = false;
									}
								});
							} else {
								if (idArr) {
									var paramObj = {};
									paramObj.actionCode = item.actionCode;
									paramObj.actionUser = themeDisplay.getUserName();
									paramObj.actionNote = vm.processActionNote;
									paramObj.assignUserId = assignUserId;
									paramObj.subUsers = subUsers;

									var strIdArr = idArr.join(";");
									console.log(strIdArr);
									vm.kyDuyetYCGiamDinh(strIdArr,paramObj);
								}
							}

                        },
                        kyDuyetYCGiamDinh: function(strIdArr,paramObj) {

							var vm = this;
							var url = '/o/rest/v2/digitalSignature/'+vm.detailModel.dossierId+'/hashComputed';
							console.log(vm.detailModel.dossierId);
							
							$.ajax({
								type : 'POST',
								url : url,
								async: false,//bat dong bo = fale, dong bo voi client
								dataType: 'json',
								data : {
									// type: 'kyDuyetYCGiamDinh',
									strIdAr: strIdArr,
									actionCode: paramObj.actionCode
									// strDossierPartId: strDossierPartId
									/*id : controlRequirementId*/
								},
								success : function(result) {
									console.log(result);
									/*var jsonData = JSON.parse(result);*/
									var hashComputers = result.hashComputers;
									var signFieldNames = result.signFieldNames;
									var fileNames = result.fileNames;
									var msgs = result.msg;
									var fileEntryId = result.fileEntryId;
									console.log("hashComputers: "+hashComputers);
									console.log("signFieldNames: "+signFieldNames);
									console.log("fileNames: "+fileNames);
									console.log("msgs: "+msgs);			
									vm.actionsSubmitLoading = false;
									if(plugin().valid) {
										
										for ( var i = 0; i < hashComputers.length; i++) {
										
											var hashComputer = hashComputers[i];
											var code = plugin().Sign(hashComputer);
											
											if(code===0 || code===7){			
												var sign = plugin().Signature;
												var signFieldName = signFieldNames[i];
												var fileName = fileNames[i];
												console.log("sign: "+sign);
												console.log("signFieldName: "+signFieldName);
												console.log("fileName: "+fileName);
												var msg = msgs[i];
												if(msg == 'success') {
													try {
														vm.completeKyDuyetYCGiamDinh(sign, signFieldName, fileName, fileEntryId, paramObj);
													}
													catch(err) {
														console.log(err);
													}
												}else{
													alert(msg);
												}	
											}else{
												alert(plugin().ErrorMessage);
											}
										}
									} else {
										alert("Plugin is not working");
									}
								},
								error : function(result){
									vm.actionsSubmitLoading = false;
								}
							});
						},
						completeKyDuyetYCGiamDinh: function(sign, signFieldName, fileName, fileEntryId,paramObj) {
							var vm = this;
							var url = '/o/rest/v2/digitalSignature/'+vm.detailModel.dossierId+'/dossierFile';
							$.ajax({
								type : 'PUT',
								url : url,
								async: false,//bat dong bo = fale, dong bo voi client
								dataType : 'json',
								headers: {
										"groupId": themeDisplay.getScopeGroupId()
									},
								data: {
									actionCode: paramObj.actionCode,
									actionUser: paramObj.actionUser,
									actionNote: paramObj.actionNote,
									assignUserId: paramObj.assignUserId,
									subUsers: paramObj.subUsers,
									sign: sign,
									signFieldName: signFieldName,
									fileName: fileName,
									fileEntryId: fileEntryId
								},
								success : function(result) {
									console.log(result);
									/*var jsonData = JSON.parse(result);*/
									var msg = result.msg;
									if(msg == 'success'){
										alert('ký số thành công!');
										/*vm.snackbardossierViewJX = true;*/
										
										vm._inidanhSachHoSoTable();
										setTimeout(function(){ 
											vm._initlistgroupHoSoFilter();
										}, 1000);

										vm.detailPage = false;
										vm.actionsSubmitLoading = false;
									} else {
										alert(msg);
									}
								},
								error: function(){
									alert('ky so false');
								}
							})
						},
                        _initchangeProcessStep: function (){
                            var vm = this;
							vm.stepLoading = true;
                            
                            var url = '/o/rest/v2/dossiers/'+vm.detailModel.dossierId+'/nextactions';
                            var urlPlugin = '/o/rest/v2/dossiers/'+vm.detailModel.dossierId+'/plugins';
                            // var url = '/o/frontendwebdossier/json/steps.json';
                            
                            axios.all([
						        axios.get(url, config),
						        axios.get(urlPlugin, config)
						      	]).then( axios.spread(function (urlRespones, urlPluginsRespones) {

                                var serializable = urlRespones.data.data;
                                var serializablePlugins = urlPluginsRespones.data.data;
                                var serializablePluginsConvert = [];

                                if(serializable){
                                	for (var i = 0; i < serializable.length; i++) {
                                		serializable[i].type = 1;
                                	}
                                }else {
                                	serializable = [];
                                }

                              
                                if(serializablePlugins){
                                	for (var i = 0; i < serializablePlugins.length; i++) {
                                		var plugin = {
                                			type : 2,
                                			processActionId : serializablePlugins[i].processPluginId,
                                			actionName : serializablePlugins[i].pluginName
                                		};
                                		serializablePluginsConvert.push(plugin);
                                	}
                                }
                                

                                var nextactions = serializable;
                                var plugins = serializablePluginsConvert;


                                vm.processSteps = $.merge( nextactions, plugins );
								vm.stepLoading = false;
								console.log(vm.processSteps);
                            }))
                                .catch(function (error) {
                                    console.log(error);
									vm.stepLoading = false;
									
                                });
                            return false; 
                        },
						filterAllDossierWithOutStatus: function () {
							this.stageFilterView = 'danh_sach';
							this.listgroupHoSoFilterselectedIndex = -1;
							this.traCuuFilter = false;
							this.detailRegistPage = false;
							this._inidanhSachHoSoTable(false);
						},
						filterTraCuu: function () {
							this.traCuuFilter = true;
							this.detailRegistPage = false;
						},
						singleFileUpload: function (item) {
							var vm = this;
							vm.actionsSubmitLoading = true;
							
							document.getElementById('inputfile_'+item.dossierPartId).click();
							setTimeout(function(){ 
								vm.actionsSubmitLoading = false;
							}, 4000);
						},
						singleFileUploadLeave: function () {
							var vm = this;
							vm.actionsSubmitLoading = false;
						},
						singleFileUploadInput: function (e, item, index) {
							
							var files = e.target.files || e.dataTransfer.files;
							
							var file = files[0];
							
							var vm = this;
							vm.actionsSubmitLoading = true;

							var url = '/o/rest/v2/dossiers/'+vm.detailModel.dossierId+'/files';

							var data = new FormData();
							data.append( 'displayName', file.name );
							data.append( 'file', file );
							data.append( 'dossierPartNo', item.partNo );
							data.append( 'dossierTemplateNo', vm.detailModel.dossierTemplateNo );
							data.append( 'fileTemplateNo', item.templateFileNo );
							data.append( 'fileType', '' );
							data.append( 'isSync', item.returned );
							data.append( 'referenceUid', '' );
							data.append( 'formData', '' );
							
							$.ajax({
								type : 'POST', 
								url  : url, 
								data : data,
								headers: {"groupId": themeDisplay.getScopeGroupId()},
								processData: false,
								contentType: false,
								cache: false,
								async : false,
								success :  function(result){ 
									vm.actionsSubmitLoading = false;
									vm.snackbartextdossierViewJX = " Tải file thành công!";
                      				vm.snackbardossierViewJX = true;
									//vm._initchangeProcessStep();
									vm.stepModel.createFiles[index].counter = vm.stepModel.createFiles[index].counter + 1;
									
								},
								error:function(result){
									vm.actionsSubmitLoading = false;
									vm.snackbartextdossierViewJX = "Tải file thất bại!";
                      				vm.snackbarerordossierViewJX = true;
								}
							});
							/**
                            axios.post(url, postData, config).then(function (response) {
                                var serializable = response.data;

								vm._initchangeProcessStep();

                            })
                                .catch(function (error) {
                                    console.log(error);
									vm.snackbartextdossierViewJX = item.actionName + " thất bại!";
                      				vm.snackbarerordossierViewJX = true;
                                });
							*/
						},
						undoDetailPage: function () {
							
							this.detailPage = !this.detailPage;
							setTimeout(function(){ 
								// temp fix header
								$('.danhSachHoSoTable__class th[role="columnheader"]').each(function( index ) {
									if ($( this ).attr('aria-label').indexOf("Activate") > 0) {
										$( this ).html($( this ).attr('aria-label').substring(0, $( this ).attr('aria-label').indexOf(":")).replace(/\./g,"<br/>") + ' <i aria-hidden="true" class="material-icons icon">arrow_upward</i>');
									} else {
										$( this ).html($( this ).attr('aria-label').substring(0, $( this ).attr('aria-label').indexOf(":")).replace(/\./g,"<br/>"));
									}
								});
							}, 300);
						},
						undoDetailRegistPage: function () {
							this.detailRegistPage = !this.detailRegistPage;
							setTimeout(function(){ 
								// temp fix header
								$('.thongTinDoanhNghiepTable__class th[role="columnheader"]').each(function( index ) {
									if ($( this ).attr('aria-label').indexOf("Activate") > 0) {
										$( this ).html($( this ).attr('aria-label').substring(0, $( this ).attr('aria-label').indexOf(":")).replace(/\./g,"<br/>") + ' <i aria-hidden="true" class="material-icons icon">arrow_upward</i>');
									} else {
										$( this ).html($( this ).attr('aria-label').substring(0, $( this ).attr('aria-label').indexOf(":")).replace(/\./g,"<br/>"));
									}
								});
							}, 300);
						},
                        onScroll(e) {
							this.offsetTop = window.pageYOffset || document.documentElement.scrollTop
						},
						onScrollTop (e) {
							window.scrollBy(0, -99999)
						}
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
							vm._initlistgroupHoSoFilter();
							vm.detailPage = false;
							vm.detailRegistPage = false;
							vm.listgroupHoSoFilterselected = item.id;
							vm.danhSachHoSoTablepage = 1;
							
							vm.listgroupHoSoFilterselectedIndex = item.index - 1;
							
							console.log(item);
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
								// TODO vm._inidanhSachHoSoTable(false);
							} else if (item.id == 'tra_cuu_thong_tin_doanh_nghiep') {
								vm._inithongTinDoanhNghiepTable(false);
								vm.detailRegistPage = false;
							} else {
								vm._inidanhSachHoSoTable(false);
							}
							
							vm.onScrollTop();

						},
						_initlistgroupHoSoFilter: function(){
							var vm = this;
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

						})
							.catch(function (error) {
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
								vm._initraCuuHoSoTable(false);
								vm._initAdvanced_filter_serviceInfo();
								vm._initAdvanced_filter_loaiSanPham();
								vm._initAdvanced_filter_nhanHieu();
								
							} else if (item.id == 'tra_cuu_phuong_tien') {
								// TODO vm._inidanhSachHoSoTable(false);
							} else if (item.id == 'tra_cuu_thong_tin_doanh_nghiep') {
								vm._inithongTinDoanhNghiepTable(false);
							}
							
						},
						_initlistgroupTraCuuFilter: function(){
							var vm = this;

                            vm.listgroupTraCuuFilterItems = [
								{
									id: 'tra_cuu_hoso',
									title: 'Tra cứu hồ sơ'
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
				'keywordsSearchTraCuuHoSo': {
					'id': 'keywordsSearchTraCuuHoSo',
					'name': 'keywordsSearchTraCuuHoSo',
					'type': 'text',
					'cssClass': 'pt-1',
					'placeholder': 'Tìm kiếm theo từ khoá...',
					'solo': true,
					'append_icon': 'search',
					'onKeyup': 'filterTraCuuHoSoKeywords',
					'events': {
						filterTraCuuHoSoKeywords: function (event) {
							var vm = this;
							if (event.keyCode === 13 || vm.keywordsSearchTraCuuHoSo.length > 3 || vm.keywordsSearchTraCuuHoSo.length === 0) {
								vm._initraCuuHoSoTable(false);
							}

						}
					}
				},
				'keywordsSearchTraCuuPhuongTien': {
					'id': 'keywordsSearchTraCuuPhuongTien',
					'name': 'keywordsSearchTraCuuPhuongTien',
					'type': 'text',
					'cssClass': 'pt-1',
					'placeholder': 'Tìm kiếm theo từ khoá...',
					'solo': true,
					'append_icon': 'search',
					'onKeyup': 'filterTraCuuPhuongTienKeywords',
					'events': {
						filterTraCuuPhuongTienKeywords: function (event) {
							var vm = this;
							if (event.keyCode === 13 || vm.keywordsSearchTraCuuPhuongTien.length > 3 || vm.keywordsSearchTraCuuPhuongTien.length === 0) {
								
							}

						}
					}
				},
				'keywordsSearchTraCuuDoanhNghiep': {
					'id': 'keywordsSearchTraCuuDoanhNghiep',
					'name': 'keywordsSearchTraCuuDoanhNghiep',
					'type': 'text',
					'cssClass': 'pt-1',
					'placeholder': 'Tìm kiếm theo từ khoá...',
					'solo': true,
					'append_icon': 'search',
					'onKeyup': 'filterTraCuuDoanhNghiepKeywords',
					'events': {
						filterTraCuuDoanhNghiepKeywords: function (event) {
							var vm = this;
							if (event.keyCode === 13 || vm.keywordsSearchTraCuuDoanhNghiep.length > 3 || vm.keywordsSearchTraCuuDoanhNghiep.length === 0) {
								vm._inithongTinDoanhNghiepTable(false);
							}

						}
					}
				},
				'keywordsSearchDanhSachHoSo': {
					'id': 'keywordsSearchDanhSachHoSo',
					'name': 'keywordsSearchDanhSachHoSo',
					'type': 'text',
					'cssClass': 'pt-1',
					'placeholder': 'Tìm kiếm theo từ khoá...',
					'solo': true,
					'append_icon': 'search',
					'onKeyup': '_filterDanhSachHoSoKeyup($event)',
					'events': {
						_filterDanhSachHoSoKeyup: function (event) {
							var vm = this;
							if (event.keyCode === 13 || vm.keywordsSearchDanhSachHoSo.length > 3 || vm.keywordsSearchDanhSachHoSo.length === 0) {
								vm._inidanhSachHoSoTable(false);
							}

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
						},
						_filterDanhSachHoSoOnRemove: function(){
							var vm = this;
							vm.serviceInfoFilter = [];
							vm._inidanhSachHoSoTable(false);
						},
						_filterDanhSachHoSoOnClear: function(){
							this.serviceInfoFilter = [];
						}

					}
				},
				"applicantNameFilter": {
					'id': 'applicantNameFilter',
					'name': 'applicantNameFilter',
					"type": "select",
					'label': 'Chủ hồ sơ ',
					"item_text": "applicantName",
					"item_value": "applicantIdNo",
					"single_line": true,
					"hide_selected": true,
					"multiple": false,
					"tags": false, //custom input not avaliable in select
					"chips": true,
					"deletable_chips": true,
					"loading": false,
					"no_data_text": "Lua chon selected",
					"items": [],
					"onLoad": "_initApplicantNameFilterData",
					"onChange": "_applicantFilterDanhSachHoSoOnchange($event)",
					"onRemove": "_applicantFilterDanhSachHoSoOnRemove",
					'onClick': '_applicantFilterDanhSachHoSoOnClear',
					"events": {
						_initApplicantNameFilterData: function(){
							var vm = this;
							
							//TODO: API
							var url = '/o/rest/v2/applicants';
							
							axios.get(url, config).then(function (response) {
								var serializable = response.data;

								vm.applicantNameFilterItems = serializable.data;
								
							})
								.catch(function (error) {
									console.log(error);
									
								});
							return false; 
						},
						_applicantFilterDanhSachHoSoOnchange: function(data){
							var vm = this;
							vm.applicantNameFilter = data;
							vm._inidanhSachHoSoTable(false);
						},
						_applicantFilterDanhSachHoSoOnRemove: function(){
							var vm = this;
							vm.applicantNameFilter = [];
							vm._inidanhSachHoSoTable(false);
						},
						_applicantFilterDanhSachHoSoOnClear: function(){
							this.applicantNameFilter = [];
						}
					}
				},
				"dossierNoFilter": {
					'id': 'dossierNoFilter',
					'name': 'dossierNoFilter',
					"type": "text",
					'label': 'Nhập mã tiếp nhận ',
					"onChange": "_filterDanhSachHoSoOnchange3",
					"events": {
						_filterDanhSachHoSoOnchange3: function(){
							var vm = this;
							vm._inidanhSachHoSoTable(false);
						}
					}
				},
				'thongTinDoanhNghiepTable': {
					'id': 'thongTinDoanhNghiepTable',
					'name': 'thongTinDoanhNghiepTable',
					'type': 'table',
					'no_data_text': 'Không tìm thấy hồ sơ thương nhân nào!',
					'cssClass': 'thongTinDoanhNghiepTable__class',
					'item_key': 'registrationId',
					'headers': 'headers',
					'template': 'thong_tin_doanh_nghiep_table_template',
					'pagging': '_paggingThongTinDoanhNghiepTable',
					'next': '_nextThongTinDoanhNghiepTable',
					'previous': '_previousThongTinDoanhNghiepTable',
					'events': {
						_inithongTinDoanhNghiepTable: function (append) {
							
							var vm = this;
							vm.traCuuFilter = true;
							vm.viewmore = true;
							this.thongTinDoanhNghiepTableheaders = [
								{
									text: 'STT',
									align: 'left',
									sortable: false,
									value: 'stt'
								},
								{
									text: 'Tên, địa chỉ cơ sở sản xuất. Cơ sở nhập khẩu',
									align: 'left',
									sortable: true,
									value: 'address'
								},
								{
									text: 'Mã số thuế. điện thoại. fax. email',
									align: 'left',
									sortable: true,
									value: 'contactEmail'
								},
								{
									text: 'Người đại diện. chức danh',
									align: 'left',
									sortable: true,
									value: 'applicantName'
								},
								{
									text: 'Thông tin nhà xưởng. (SL, địa chỉ, diện tích, nhân lực, công suất theo tháng)',
									align: 'left',
									sortable: false,
									value: 'action'
								},
								{
									text: 'Tên sản phẩm',
									align: 'left',
									sortable: true,
									value: 'action'
								},
								{
									text: 'Tình trạng đăng ký',
									align: 'left',
									sortable: true,
									value: 'registrationState'
								}
							];

							var paramsBuilder = {
								keyword: vm.keywordsSearchTraCuuDoanhNghiep,
								start: vm.thongTinDoanhNghiepTablepage * 15 - 15,
								end: vm.thongTinDoanhNghiepTablepage * 15,
							};
							
							//TODO
							const config_registrations = {
								params: paramsBuilder,
								headers: {
									'groupId': themeDisplay.getScopeGroupId(),
								}
							};

							var url = '/o/rest/v2/registrations';
							
							axios.get(url, config_registrations).then(function (response) {
								var serializable = response.data;

								vm.thongTinDoanhNghiepTableItems = serializable.data;
								vm.thongTinDoanhNghiepTableTotal = Math.ceil(serializable.total / 15);
								
								// temp fix header
								$('.thongTinDoanhNghiepTable__class th[role="columnheader"]').each(function( index ) {
									if ($( this ).attr('aria-label').indexOf("Activate") > 0) {
										$( this ).html($( this ).attr('aria-label').substring(0, $( this ).attr('aria-label').indexOf(":")).replace(/\./g,"<br/>") + ' <i aria-hidden="true" class="material-icons icon">arrow_upward</i>');
									} else {
										$( this ).html($( this ).attr('aria-label').substring(0, $( this ).attr('aria-label').indexOf(":")).replace(/\./g,"<br/>"));
									}
								});
								
							})
								.catch(function (error) {
									console.log(error);
									vm.thongTinDoanhNghiepTableItems = [];
									
								});
							return false; 
						},
						_paggingThongTinDoanhNghiepTable: function() {
							
							this._inithongTinDoanhNghiepTable(false);
						},
						_nextThongTinDoanhNghiepTable: function() {
							
						},
						_previousThongTinDoanhNghiepTable: function() {
							
						},
						toDetailHoSoDoanhNghiep: function (item) {
							
							var vm = this;
							// call DetailAPI.

							var url = '/o/rest/v2/registrations/'+item.registrationId;
							
							axios.get(url, config).then(function (response) {
								var serializable = response.data;

								vm.detailRegistModel = serializable;
								vm.detailRegistPage = true;
								window.scrollBy(0, -99999);
								vm._getListForms();

							})
								.catch(function (error) {
									console.log(error);
									
								});
							return false; 
						},
						_getListForms: function () {
							var vm = this;
							// call DetailAPI.

							var url = '/o/rest/v2/registrations/'+vm.detailRegistModel.registrationId+'/forms';
							
							axios.get(url, config).then(function (response) {
								var serializable = response.data;

								vm.registForms = serializable.data;
								vm.detailRegistPage = true;
								
							})
								.catch(function (error) {
									console.log(error);
									
								});
							return false; 
						},
						formatDate (date) {
					        if (!date) {
					          return null
					        }
					        const [year, month, day] = date.split('-');
					        return '${month}/${day}/${year}';
					      },
					      parseDate (date) {
					        if (!date) {
					          return null;
					        }

					        const [month, day, year] = date.split('/');
					        return `${year}-${month.padStart(2, '0')}-${day.padStart(2, '0')}`;
					    },
						addRegistrations: function() {
							var vm = this;
							vm.$dialog.confirm('Bạn có muốn thêm mới hồ sơ Doanh Nghiệp!', {
								html: true,
								loader: true,
								okText: 'Xác nhận',
								cancelText: 'Quay lại',
								animation: 'fade'
							})
							.then((dialog) => {

								// call API get file by dossierId
								var url = "/o/rest/v2/registrations";
								
								$.ajax({
									url: url,
									headers: {
										"groupId": themeDisplay.getScopeGroupId()
									},
									data: {
										"applicantName": "",
										"applicantIdType": "",
										"applicantIdNo": "",
										"applicantIdDate": "",
										"address": "",
										"cityCode": "",
										"districtCode": "",
										"wardCode": "",
										"contactName": "",
										"contactTelNo": "",
										"contactEmail": "",
										"govAgencyCode": "",
										"registrationState": 2,
										"registrationClass": ""
									},
									type: 'POST',
									dataType: 'json',
									contentType: 'application/x-www-form-urlencoded; charset=utf-8',
									success: function(data, textStatus, xhr) {
										vm.detailRegistModel = data.data;
										console.log(vm.detailRegistModel);
									},
									error: function(xhr, textStatus, errorThrown) {
										vm.detailRegistModel = {};
									}
								});
								
								dialog.close();
								return false; 
							})
							.catch((e) => {
								console.log(e)
							})
						},
						registrationPheDuyet: function(registrationState) {
							var vm = this;
							var defaultMessage = 'Đồng ý phê duyệt hồ sơ doanh nghiệp này?';
							
							if (registrationState === 3) {
								defaultMessage = 'Gửi thông báo yêu cầu bổ sung thông tin doanh nghiệp?';
							}
							
							vm.$dialog.confirm(defaultMessage, {
								html: true,
								loader: true,
								okText: 'Xác nhận',
								cancelText: 'Quay lại',
								animation: 'fade'
							})
							.then((dialog) => {

								// call API get file by dossierId
								var url = "/o/rest/v2/registrations/"+vm.detailRegistModel.registrationId;
								
								$.ajax({
									url: url,
									headers: {
										"groupId": themeDisplay.getScopeGroupId()
									},
									data: {
										"registrationState": registrationState,
									},
									type: 'PUT',
									dataType: 'json',
									contentType: 'application/x-www-form-urlencoded; charset=utf-8',
									success: function(data, textStatus, xhr) {
										vm.detailRegistModel = data;
										console.log(vm.detailRegistModel);
										vm.snackbartextdossierViewJX = "Yêu cầu xử lý thành công thành công!";
										vm.snackbardossierViewJX = true;
										
									},
									error: function(xhr, textStatus, errorThrown) {
										vm.detailRegistModel = {};
										vm.snackbartextdossierViewJX = "Yêu cầu xử lý thành công thất bại!";
										vm.snackbarerordossierViewJX = true;
									}
								});
								
								dialog.close();
								return false; 
							})
							.catch((e) => {
								console.log(e)
							})
						}
					}
				},
				'traCuuHoSoTable': {
					'id': 'traCuuHoSoTable',
					'name': 'traCuuHoSoTable',
					'type': 'table',
					'no_data_text': 'Không tìm thấy hồ sơ nào!',
					'cssClass': 'danhSachHoSoTable__class',
					'select_all': true,
					'item_key': 'dossierId',
					'headers': 'headers',
					'template': 'tra_cuu_hoso_table_template',
					'pagging': '_paggingTraCuuHoSoTable',
					'next': '_nextTraCuuHoSoTable',
					'previous': '_previousTraCuuHoSoTable',
					'events': {
						_initraCuuHoSoTable: function (append) {
							var vm = this;
							vm.traCuuFilter = true;
							vm.viewmore = true;
							this.traCuuHoSoTableheaders = [
								{
									text: 'STT',
									align: 'left',
									sortable: false,
									value: 'stt'
								},
								{
									text: 'Tên chứng chỉ',
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
									align: 'left',
									sortable: false,
									value: 'action'
								},
								{
									text: 'Tên doanh nghiệp',
									align: 'left',
									sortable: false,
									value: 'action'
								},
								{
									text: 'Hành động',
									align: 'left',
									sortable: true,
									value: 'lastActionNote'
								}
							];
							
							var paramsBuilder = {
								keyword: vm.keywordsSearchTraCuuHoSo,
								start: vm.traCuuHoSoTablepage * 15 - 15,
								end: vm.traCuuHoSoTablepage * 15,
								sort: 'modified',
								order: 'false'
							};
							
							const config_dossiers = {
								params: paramsBuilder,
								headers: {
									'groupId': themeDisplay.getScopeGroupId(),
								}
								
							};

							var url = '/o/rest/v2/deliverables';
							
							axios.get(url, config_dossiers).then(function (response) {
								var serializable = response.data;

								if (append) {
									vm.traCuuHoSoTableItems.push.apply(vm.traCuuHoSoTableItems, serializable.data);
								} else {
									vm.traCuuHoSoTableItems = serializable.data;
									
									vm.traCuuHoSoTableTotal = Math.ceil(serializable.total / 15);
								}

								vm.xem_them = 'Xem thêm 8+ bản ghi';
								if (serializable.data.length === 0) {
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
									vm.traCuuHoSoTableItems = [];
									
								});


							var resData = vm.traCuuHoSoTableItems;
							if(resData){
								for (var i = 0; i < resData.length; i++) {
									$.ajax({
										url : "${api.server}/deliverables/"+resData[i].deliverableId+"/formdata",
										dataType : "json",
										type : "GET",
										headers: {"groupId": ${groupId}},
										success : function(result){
											$("#ma_ho_so"+resData[i].deliverableId).html(result.ma_ho_so);
											$("#so_ho_so"+resData[i].deliverableId).html(result.so_ho_so);
											$("#ngay_gui"+resData[i].deliverableId).html(result.ngay_gui);
											$("#ngay_tiep_nhan"+resData[i].deliverableId).html(result.ngay_tiep_nhan);
											$("#so_chung_chi"+resData[i].deliverableId).html(result.so_chung_chi);
											$("#ngay_ky_cc"+resData[i].deliverableId).html(result.ngay_ky_cc);
										},
										error : function(result){
											
										}
									});
								}
							}


							return false; 
						},
						_paggingTraCuuHoSoTable: function() {
							
							this._initraCuuHoSoTable(false);
						},
						_nextTraCuuHoSoTable: function() {
							
						},
						_previousTraCuuHoSoTable: function() {
							
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
					'onLoad': '_inidanhSachHoSoTable',
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
									align: 'left',
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

							var statusParam = null;
							var substatusParam = null;

							if (vm.stageFilterView !== 'danh_sach') {
								statusParam = vm.statusParamFilter;
								substatusParam = vm.substatusParamFilter;
							}

							var paramsBuilder = {
								keyword: vm.keywordsSearch,
								owner: vm.applicantNameFilter.applicantIdNo,
								service: vm.serviceInfoFilter.serviceCode,
								follow: true,
								dossierNo: vm.dossierNoFilter,
								start: vm.danhSachHoSoTablepage * 15 - 15,
								end: vm.danhSachHoSoTablepage * 15,
								status: statusParam,
								substatus: substatusParam,
								sort: 'modified',
								order: 'false'
							};
						
							
							
							if ( vm.keywordFilter != null ) {
								paramsBuilder['keyword'] = vm.keywordFilter;
							} else {
								paramsBuilder['keyword'] = vm.keywordsSearchDanhSachHoSo;
							}
							const config_dossiers = {
								params: paramsBuilder,
								headers: {
									'groupId': themeDisplay.getScopeGroupId(),
								}
							};

							var url = '/o/rest/v2/dossiers';
							//var url = "http://hanoi.fds.vn:2281/api/dossiers";
							
							axios.get(url, config_dossiers).then(function (response) {
								var serializable = response.data;

								if (append) {
									vm.danhSachHoSoTableItems.push.apply(vm.danhSachHoSoTableItems, serializable.data);
								} else {
									vm.danhSachHoSoTableItems = serializable.data;

									vm.danhSachHoSoTableTotal = Math.ceil(serializable.total / 15);
									
								}
								
								if (vm.listgroupHoSoFilterselectedIndex >= 0) {
									console.log('reindex counting ...');
									vm.listgroupHoSoFilterItems[vm.listgroupHoSoFilterselectedIndex]['count'] = serializable.total;
								}
								
								vm.xem_them = 'Xem thêm 8+ bản ghi';
								if (serializable.data.length === 0) {
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
								console.log(vm.danhSachHoSoTableItems);
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
							// call DetailAPI.

							var url = '/o/rest/v2/dossiers/'+item.dossierId;
							
							axios.get(url, config).then(function (response) {
								var serializable = response.data;

								vm.detailModel = serializable;
								vm.detailModel.dossierIdCTN = item.dossierIdCTN;
								vm.detailPage = true;
								window.scrollBy(0, -99999);
								
								vm._inilistDocumentIn(vm.detailModel);

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
				// TODO detail
				'listDocumentIn': {
					'id': 'listDocumentIn',
					'name': 'listDocumentIn',
					'type': 'listview',
					'template': 'list_document_in_template',
					'events': {
						_inilistDocumentIn: function (item) {
							var vm = this;
//							vm.listDocumentInItems = [];
//							vm.listDocumentOutItems = [];
							
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
									listFilesUpload.push(vm.dossierFiles[key]);
								}
							}
							vm.listDocumentInPartNoItems = listFilesUpload;
							if (item.counter > 0){
								vm.popUpViewDossierFile = !vm.popUpViewDossierFile;
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
					'template': 'list_document_in_template',
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
								console.log(url);
								window.open(url);
							})
								.catch(function (error) {
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
                // TODO PROCESS ACTION
                "processActionNote": {
                    'id': 'processActionNote',
                    'name': 'processActionNote',
                    "type": "text",
                    'placeholder': 'ý kiến cán bộ ... ',
                    'multi_line': true,
                    'textarea': true
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
				// TODO POPUP
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
								console.log(url);
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
                    "item_text": "serviceName",
					"item_value": "serviceCode",
                    'label': 'Lựa chọn thủ tục hành chính',
					"hide_selected": true,
					"combobox": true,
					"loading": false,
					"no_data_text": "Lua chon selected",
					"items": [],
                    'events': {
                    	_initAdvanced_filter_serviceInfo: function () {
                    		var vm = this;
                    		vm.advanced_filter_serviceInfoItems = vm.serviceInfoFilterItems;
                    	}
                    }
                },
                "advanced_filter_applicantName": {
                    'id': 'advanced_filter_applicantName',
                    'name': 'advanced_filter_applicantName',
                    "type": "text",
                    'label': 'Tên doanh nghiệp'
                },
                "advanced_filter_dossierIdCTN": {
                    'id': 'advanced_filter_dossierIdCTN',
                    'name': 'advanced_filter_dossierIdCTN',
                    "type": "text",
                    'label': 'Số hồ sơ'
                },
                "advanced_filter_dossierNo": {
                    'id': 'advanced_filter_dossierNo',
                    'name': 'advanced_filter_dossierNo',
                    "type": "text",
                    'label': 'Số chứng chỉ '
                },
                "advanced_filter_loaiSanPham": {
                    'id': 'advanced_filter_loaiSanPham',
                    'name': 'advanced_filter_loaiSanPham',
                    "type": "select",
                    'cssClass': 'no-wrap',
                    "item_text": "itemName",
					"item_value": "itemCode",
                    'label': 'Loại sản phẩm',
                    "hide_selected": true,
                    "combobox": true,
					"loading": false,
					"no_data_text": "Lua chon selected",
					"items": [],
                    'events': {
                    	_initAdvanced_filter_loaiSanPham: function () {
                    		var vm = this;
                    		if (vm.advanced_filter_loaiSanPhamItems.length <= 0) {

    							var url = '/o/rest/v2/dictcollections/VR03/dictitems?sort=sibling';
    							
    							axios.get(url, config).then(function (response) {
    								var serializable = response.data;
    								vm.advanced_filter_loaiSanPhamItems = serializable.data;
    								console.log(vm.advanced_filter_loaiSanPhamItems);
    							})
								.catch(function (error) {
									console.log(error);
									
								});
                    		}
                    		return false; 
                    	}
                    }
                },
                "advanced_filter_nhanHieu": {
                    'id': 'advanced_filter_nhanHieu',
                    'name': 'advanced_filter_nhanHieu',
                    "type": "select",
                    'cssClass': 'no-wrap',
                    "item_text": "itemName",
					"item_value": "itemCode",
                    'label': 'Nhãn hiệu',
                    "hide_selected": true,
					"combobox": true,
					"loading": false,
					"no_data_text": "Lua chon selected",
					"items": [],
                    'events': {
                    	_initAdvanced_filter_nhanHieu: function () {
                    		var vm = this;
                    		if (vm.advanced_filter_nhanHieuItems.length <= 0) {

    							var url = '/o/rest/v2/dictcollections/VR02/dictitems?sort=sibling';
    							
    							axios.get(url, config).then(function (response) {
    								var serializable = response.data;
    								vm.advanced_filter_nhanHieuItems = serializable.data;
    							})
								.catch(function (error) {
									console.log(error);
									
								});
                    		}
                    		return false; 
                    	}
                    }
                },
                "advanced_filter_tenThuongMai": {
                    'id': 'advanced_filter_tenThuongMai',
                    'name': 'advanced_filter_tenThuongMai',
                    "type": "text",
                    'label': 'Tên thương mại'
                },
                "advanced_filter_maKieuLoai": {
                    'id': 'advanced_filter_maKieuLoai',
                    'name': 'advanced_filter_maKieuLoai',
                    "type": "text",
                    'label': 'Mã kiểu loại'
                }
			}
		});

		dossierViewJX._builder('dossierViewJX');

		/*window.onload = function(event){
			var vm = dossierViewJX;
			setInterval(function(){

				vm._initlistgroupHoSoFilter();
				vm._inidanhSachHoSoTable();


			}, 10000);
		}*/
	});