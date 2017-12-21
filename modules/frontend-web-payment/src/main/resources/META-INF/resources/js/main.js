document.addEventListener('DOMContentLoaded', function (event) {

	var paymentViewJX = new VueJX({
		el: 'paymentViewJX',
		pk: 1,
		groupid: themeDisplay.getScopeGroupId(),
		data: {
			stageScroll: 0,
			detailPage: false,
			viewmore: false,
			showmore: false,
			detailModel: {

			},
			xem_them: 'Không tìm thấy phiếu thanh toán nào'

		},
		onScroll: 'onScroll',
		schema: {
			/** TODO menu filter */
			'navigationFilter': {
				'id': 'navigationFilter',
				'name': 'navigationFilter',
				'type': 'navigation',
				'template': 'menu_template',
				'template_content': 'paymentViewJX_form_template'
			},
			'listgroupFilter': {
				'id': 'listgroupFilter',
				'name': 'listgroupFilter',
				'type': 'listgroup',
				'child_items': 'items',
				'data_title': 'title',
				'data_value': 'id',
				'onLoad': '_initlistgroupFilter',
				'onClick': 'filterChange',
				'events': {
					filterChange: function (item) {
						var vm = this;
						
						vm.listgroupFilterselected = item.id;
						vm._inipaymentList(false);
						vm.detailPage = false;
					},
					_initlistgroupFilter: function () {
						this.listgroupFilterItems = [
							{
								action: 'folder',
								action_active: 'folder_open',
								id: '0',
								title: 'Xác nhận thanh toán',
								active: true
							},
							{
								action: 'folder',
								action_active: 'folder_open',
								id: '2',
								title: 'Đã thanh toán'
							},
							{
								action: 'folder',
								action_active: 'folder_open',
								id: '4',
								title: 'Đã cấp hoá đơn điện tử'
							}
						];

					}
				}
			},
			/** TODO filter component */
			'paymentMethodSearch': {
				'id': 'paymentMethodSearch',
				'name': 'paymentMethodSearch',
				'type': 'radio',
				'label': '',
				'labelClass': '',
				'controlClass': 'xs12',
				'switch_inline': true,
				'tabindex': 0,
				'items': [
					{
						'label': 'Tiền mặt',
						'color': 'blue darken-2',
						'value': 'Nộp trực tiếp',
					},
					{
						'label': 'Chuyển khoản',
						'color': 'blue darken-2',
						'value': 'Chuyển khoản',
					}
				],
				'onLoad': '_initpaymentMethodSearch',
				'events': {
					_initpaymentMethodSearch: function () {

						this.paymentMethodSearch = 'Nộp trực tiếp';

					}
				}
			},
			'paymentConfirm': {
				'id': 'paymentConfirm',
				'name': 'paymentConfirm',
				'cssClass': 'btn--flat ml-0 pl-0',
				'label': 'Xác nhận',
				'type': 'button',
				'left_icon': 'done_all',
				'onClick': 'paymentConfirmAction',
				'events': {
					paymentConfirmAction: function () {
						var vm = this;

						if (vm.paymentListselected.length === 0) {
							alert('Chưa chọn phiếu thanh toán nào?. ');
							return;
						}

						var labelBtn = 'Xác nhận';

						var dateConfirmPayment = new Date();
						var month = dateConfirmPayment.getMonth() + 1;
						var day = dateConfirmPayment.getDate();
						var year = dateConfirmPayment.getFullYear();

						var dialogContent = '<strong>Ngày xác nhận thu phí: </strong> ' + day + '/' + month + '/' + year + ' <br/>';
						dialogContent += '<strong>Người thực hiện: </strong> ' + themeDisplay.getUserName() + ' <br/>';
						dialogContent += '<strong>Hình thức thanh toán: </strong> ' + vm.paymentMethodSearch + ' <br/>';
						dialogContent += '<strong>Xác nhận thu phí cho: </strong> ' + vm.paymentListselected.length + ' phiếu thanh toán. <br/>';

						vm.$dialog.confirm(dialogContent, {
							html: true,
							loader: true,
							okText: labelBtn,
							cancelText: 'Quay lại',
							animation: 'fade'
						})
							.then((dialog) => {

								for (var i = vm.paymentListItems.length - 1; i >= 0; i--) {
									for (var j = 0; j < vm.paymentListselected.length; j++) {

										var currentItem = vm.paymentListItems[i];
										var currentItemSelected = vm.paymentListselected[j];

										if (currentItem != null && currentItemSelected != null
											&& currentItem.dossierId === currentItemSelected.dossierId) {

											var url = '/o/rest/v2/dossiers/' + vm.paymentListselected[j].dossierId + '/payments/' + vm.paymentListselected[j].referenceUid + '/confirm/noattachment';
										
											/* TODO: confirmPayload tam thoi khong truyen len*/
											var data = new FormData();
											data.append( 'file', $("#inputfile_temp")[0].files[0]);
											data.append( 'confirmNote', vm.paymentListItems[j].confirmNote );
											data.append( 'paymentMethod', vm.paymentMethodSearch );
											data.append( 'confirmPayload', "" );

											$.ajax({
												type : 'PUT', 
												url  : url, 
												data : data,
												headers: {"groupId": themeDisplay.getScopeGroupId()},
												processData: false,
												contentType: false,
												cache: false,
												async : false,
												success: function(data, textStatus, xhr) {
												
													vm.snackbartextpaymentViewJX = "Xác nhận thanh toán thành công!";
													vm.snackbarpaymentViewJX = true;

												
												},
												error: function(xhr, textStatus, errorThrown) {
												
													vm.snackbartextpaymentViewJX = "Xác nhận thanh toán thất bại!";
				                      				vm.snackbarerorpaymentViewJX = true;
												}
											});
											
											vm.paymentListItems.splice(i, 1);

										}

									}
								}
								
								vm.paymentListselected = [];
								dialog.close();
							})
							.catch((e) => {
								console.log(e)
							})
					},
					paymentConfirmSingle: function (index) {
						var vm = this;
						console.log(vm.paymentMethodSearch);

						var labelBtn = 'Xác nhận';
						console.log("call333333");
						var dateConfirmPayment = new Date();
						var month = dateConfirmPayment.getMonth() + 1;
						var day = dateConfirmPayment.getDate();
						var year = dateConfirmPayment.getFullYear();

						var dialogContent = '<strong>Ngày xác nhận thu phí: </strong> ' + day + '/' + month + '/' + year + ' <br/>';
						dialogContent += '<strong>Người thực hiện: </strong> ' + themeDisplay.getUserName() + ' <br/>';

						vm.$dialog.confirm(dialogContent, {
							html: true,
							loader: true,
							okText: labelBtn,
							cancelText: 'Quay lại',
							animation: 'fade'
						})
							.then((dialog) => {

								var url = '/o/rest/v2/dossiers/' + vm.paymentListItems[index].dossierId + '/payments/' + vm.paymentListItems[index].referenceUid + '/confirm/noattachment';
								
								/* TODO: confirmPayload tam thoi khong truyen len*/
								var data = new FormData();
								data.append( 'file', $("#inputfile_temp")[0].files[0]);
								data.append( 'confirmNote', vm.paymentListItems[index].confirmNote );
								data.append( 'paymentMethod', vm.paymentMethodSearch );
								data.append( 'confirmPayload', "" );

								$.ajax({
									type : 'PUT', 
									url  : url, 
									data : data,
									headers: {"groupId": themeDisplay.getScopeGroupId()},
									processData: false,
									contentType: false,
									cache: false,
									async : false,
									success: function(data, textStatus, xhr) {
									
										vm.snackbartextpaymentViewJX = "Xác nhận thanh toán thành công!";
										vm.snackbarpaymentViewJX = true;

									
									},
									error: function(xhr, textStatus, errorThrown) {
									
										vm.snackbartextpaymentViewJX = "Xác nhận thanh toán thất bại!";
	                      				vm.snackbarerorpaymentViewJX = true;
									}
								});
								
								vm.paymentListItems.splice(index, 1);

								dialog.close();
							})
							.catch(() => {
								console.log('process aborted');
							})
					}
				}
			},
			'keywordsSearch': {
				'id': 'keywordsSearch',
				'name': 'keywordsSearch',
				'type': 'text',
				'placeholder': 'Tìm kiếm theo từ khoá...',
				'solo': true,
				'append_icon': 'search',
				'onKeyup': 'filterTableKeywords',
				'events': {
					filterTableKeywords: function (event) {
						var vm = this;
						if (event.keyCode === 13 || vm.keywordsSearch.length > 3 || vm.keywordsSearch.length === 0) {
							vm._inipaymentList(false);
						}

					}
				}
			},
			/** TODO menu filter */
			'paymentList': {
				'id': 'paymentList',
				'name': 'paymentList',
				'type': 'table',
				'cssClass': 'danhSachPaymentTable__class',
				'select_all': true,
				'item_key': 'dossierId',
				'headers': 'headers',
				'template': 'activity_expand_list_template',
				'onLoad': '_inipaymentList',
				'events': {
					_inipaymentList: function (append) {

						var vm = this;
						
						vm.viewmore = true;
						this.paymentListheaders = [
							{
								text: 'STT',
								align: 'left',
								sortable: false,
								value: 'stt'
							},
							{
								text: 'Số phiếu tính tiền',
								align: 'left',
								sortable: true,
								value: 'dossierNo'
							},
							{
								text: 'Ngày lập phiếu',
								align: 'center',
								sortable: true,
								value: 'createDate'
							},
							{
								text: 'Tổng tiền',
								align: 'center',
								sortable: true,
								value: 'paymentAmount'
							},
							{
								text: 'Tên doanh nghiệp',
								align: 'center',
								sortable: true,
								value: 'applicantName'
							},
							{
								text: 'Thao tác',
								align: 'center',
								sortable: false,
								value: 'action'
							}
						];
						
						var paramsBuilder = {keyword: vm.keywordsSearch};
						
						if (vm.listgroupFilterselected === 4){
							// TODO
							paramsBuilder['statusTEMP'] = vm.listgroupFilterselected;
						} else {
							paramsBuilder['status'] = vm.listgroupFilterselected;
						}
						
						const config = {
							params: paramsBuilder,
							headers: {'groupId': themeDisplay.getScopeGroupId()}
							
						};

						var url = '/o/rest/v2/dossiers/paymentfiles';

						axios.get(url, config).then(function (response) {
							var serializable = response.data;

							if (append) {
								vm.paymentListItems.push.apply(vm.paymentListItems, serializable.data);
							} else {
								vm.paymentListItems = serializable.data;
							}

							vm.xem_them = 'Xem thêm 8+ bản ghi';
							if (serializable.data.length === 0) {
								vm.xem_them = 'Tổng số ( ' + vm.paymentListItems.length + ' ) bản ghi'
							}
							vm.viewmore = false;
						})
							.catch(function (error) {
								console.log(error);
								vm.paymentListItems = [];
								vm.viewmore = false;
							});

					},
					toPaymentDetail: function (index) {
						var vm = this;
						vm.detailPage = !vm.detailPage;

						vm.detailModel = vm.paymentListItems[index];
						vm.detailModel.index = index;
						
						// TODO: call API lay file
						var url ="/o/rest/v2/employees/1401/photo" ;
						vm._showFile({
							config : {
								headers: {'groupId': themeDisplay.getScopeGroupId()},
								responseType: 'blob'
							},
							url : url
						});

						window.scrollBy(0, -99999);
					},
					_showFile: function (options) {
						
						axios.get(options.url, options.config).then(function (response) {
							
							var url = window.URL.createObjectURL(response.data);
							var iFrame = document.getElementById("objectView2");
							
							iFrame.innerHTML = '<iframe src="'+url+'" width="100%" height="100%"> </iframe>';
						})
							.catch(function (error) {
								console.log(error);
							});
						
					},
					paggingPaymentList: function(){
						this.start = this.start + 8;
						this.end = this.end + 8;
						
						this._inipaymentList(true);
					},
					onScroll(e) {
						var onBottom = window.pageYOffset || document.documentElement.scrollTop;
						var vm = this;
						var btn_view_more = document.getElementById("btn_view_more");

						if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
							stageScroll = btn_view_more.offsetTop - 300;
							//vm._inipaymentList(true);
						}

					}
				}
			}

		}
	});

	paymentViewJX._builder('paymentViewJX');

});