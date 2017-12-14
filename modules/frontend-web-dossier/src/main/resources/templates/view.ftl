
<#include "init.ftl">
	
<div class="application theme--light">
  
	<div id="dossierViewJX" style="width: 100%;"> </div>
  
</div>

<script type="text/javascript">
	
	document.addEventListener('DOMContentLoaded', function (event) {

		var dossierViewJX = new VueJX({
			el: 'dossierViewJX',
			pk: 1,
			groupid: themeDisplay.getScopeGroupId(),
			data: {
				stageFilterView: null,
				detailPage: false,
				viewmore: false,
				detailModel: {

				},
				xem_them: 'Không tìm thấy hồ sơ nào',
				hoso_title_table: 'Danh sách hồ sơ',
				start: 0,
				end: 8,
                processSteps: [],
                stepModel: {

                }
			},
			onScroll: 'onScroll',
			schema: {
				// TODO menu filter
				'navigationFilter': {
					'id': 'navigationFilter',
					'name': 'navigationFilter',
					"type": "navigation",
					"template": "menu_template",
					"template_content": "dossierViewJX_form_template",
					'events': {
                        changeProcessStep: function (data){
                            console.log(data);
                            var vm = this;
                            vm.stepModel = data;
                            vm.processAssignUserIdItems = data.toUsers
                        },
                        _initchangeProcessStep: function (){
                            var vm = this;
                            const config = {};
                            
                            var url = '/o/frontendwebdossier/json/steps.json';
                            
                            axios.get(url, config).then(function (response) {
                                var serializable = response.data;

                                vm.processSteps = serializable.data;

                            })
                                .catch(function (error) {
                                    console.log(error);
                                });
                        },
                        onScroll(e) {
						
						}
					}
				},
				'listgroupFilter': {
					'id': 'listgroupFilter',
					'name': 'listgroupFilter',
					"type": "listgroup",
					"child_items": "items",
					"data_title": "title",
					"data_value": "id",
					"onLoad": "_initlistgroupFilter",
					"onClick": "filterChange",
					"events": {
						filterChange: function(item){
							var vm = this;
							vm.detailPage = false;
							vm.listgroupFilterselected = item.id;
							if ( item.id !== 'tra_cuu' ){
								vm.stageFilterView = item.id;
								vm.hoso_title_table = item.title;
							}
							
							if (item.id == 'tra_cuu_hoso') {
								vm._initraCuuHoSoTable(false);
							} else if (item.id == 'tra_cuu_phuong_tien') {
								// TODO vm._inidanhSachHoSoTable(false);
							} else if (item.id == 'tra_cuu_thong_tin_doanh_nghiep') {
								vm._inithongTinDoanhNghiepTable(false);
							} else {
								vm._inidanhSachHoSoTable(false);
							}
							
						},
						_initlistgroupFilter: function(){
							this.stageFilterView = 'danh_sach';
							this.listgroupFilterItems = [
								{
									id: 'danh_sach',
									title: 'DANH SÁCH HỒ SƠ',
									items: [
									]
								},
								{
									id: 'tra_cuu',
									title: 'TRA CỨU',
									active: true,
									items: [
										{
											id: 'tra_cuu_hoso',
											title: 'Tra cứu hồ sơ'
										},
										{
											id: 'tra_cuu_phuong_tien',
											title: 'Tra cứu phương tiện sản xuất lắp rap'
										},
										{
											id: 'tra_cuu_thong_tin_doanh_nghiep',
											title: 'Tra cứu thông tin doanh nghiệp'
										}
									]
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
					"no-data-text": "Lua chon selected",
					"items": [],
					"onLoad": "_initServiceInfoFilterData",
					"onChange": "_filterDanhSachHoSoOnchange($event)",
					"onRemove": "_filterDanhSachHoSoOnRemove",
					'onClick': '_filterDanhSachHoSoOnClear',
					"events": {
						_initServiceInfoFilterData: function(){
							var vm = this;
							const config = {
								headers: {'groupId': themeDisplay.getScopeGroupId()}
							};
							
							//TODO: API
							var url = '/o/rest/v2/serviceinfos';
							
							axios.get(url, config).then(function (response) {
								var serializable = response.data;

								vm.serviceInfoFilterItems = serializable.data;

							})
								.catch(function (error) {
									console.log(error);
								});
							
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
					"loading": false,
					"no-data-text": "Lua chon selected",
					"items": [],
					"onLoad": "_initApplicantNameFilterData",
					"onChange": "_filterDanhSachHoSoOnchange2",
					"events": {
						_initApplicantNameFilterData: function(){
							var vm = this;
							const config = {
								headers: {'groupId': themeDisplay.getScopeGroupId()}
							};
							
							//TODO: API
							var url = '/o/rest/v2/applicants';
							
							axios.get(url, config).then(function (response) {
								var serializable = response.data;

								vm.applicantNameFilterItems = serializable.data;
								
							})
								.catch(function (error) {
									console.log(error);
								});
							
						},
						_filterDanhSachHoSoOnchange2: function(){
							var vm = this;
							vm._inidanhSachHoSoTable(false);
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
					'item_key': 'dossierNo',
					'headers': 'headers',
					'template': 'thong_tin_doanh_nghiep_table_template',
					'onLoad': '_inithongTinDoanhNghiepTable',
					'pagging': '_paggingThongTinDoanhNghiepTable',
					'next': '_nextThongTinDoanhNghiepTable',
					'previous': '_previousThongTinDoanhNghiepTable',
					'events': {
						_inithongTinDoanhNghiepTable: function (append) {
							console.log("--thongTinDoanhNghiepTable--");
							var vm = this;
							vm.viewmore = true;
							this.thongTinDoanhNghiepTableheaders = [
								{
									text: 'STT',
									align: 'left',
									sortable: false,
									value: 'stt'
								},
								{
									text: 'Tên, địa chỉ cơ sở sản xuất / Cơ sở nhập khẩu',
									align: 'left',
									sortable: true,
									value: 'dossierNo'
								},
								{
									text: 'Mã số thuế, điện thoại, fax, email',
									align: 'left',
									sortable: true,
									value: 'createDate'
								},
								{
									text: 'Người đại diện, chức danh',
									align: 'left',
									sortable: true,
									value: 'applicantName'
								},
								{
									text: 'Thông tin nhà xưởng (SL, địa chỉ, diện tích, nhân lực, công suất theo tháng)',
									align: 'center',
									sortable: true,
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
									align: 'center',
									sortable: true,
									value: 'action'
								}
							];

							const config = {};

							var url = '/o/frontendwebdossier/json/payment_files.json';
							
							axios.get(url, config).then(function (response) {
								var serializable = response.data;

								if (append) {
									vm.thongTinDoanhNghiepTableItems.push.apply(vm.thongTinDoanhNghiepTableItems, serializable.data);
								} else {
									vm.thongTinDoanhNghiepTableItems = serializable.data;
									vm.thongTinDoanhNghiepTableTotal = serializable.total;
								}

								vm.xem_them = 'Xem thêm 8+ bản ghi';
								if (serializable.data.length === 0) {
									vm.xem_them = 'Tổng số ( ' + vm.thongTinDoanhNghiepTableItems.length + ' ) bản ghi'
								}
								vm.viewmore = false;
							})
								.catch(function (error) {
									console.log(error);
								});

						},
						_paggingThongTinDoanhNghiepTable: function() {
							
							this._inidanhSachHoSoTable(false);
						},
						_nextThongTinDoanhNghiepTable: function() {
							
						},
						_previousThongTinDoanhNghiepTable: function() {
							
						}
					}
				},
				'traCuuHoSoTable': {
					'id': 'traCuuHoSoTable',
					'name': 'traCuuHoSoTable',
					'type': 'table',
					'item_key': 'dossierNo',
					'headers': 'headers',
					'template': 'tra_cuu_hoso_table_template',
					'onLoad': '_initraCuuHoSoTable',
					'pagging': '_paggingTraCuuHoSoTable',
					'next': '_nextTraCuuHoSoTable',
					'previous': '_previousTraCuuHoSoTable',
					'events': {
						_initraCuuHoSoTable: function (append) {
							console.log("--tra cuu ho so--");
							var vm = this;
							vm.viewmore = true;
							this.traCuuHoSoTableheaders = [
								{
									text: 'STT',
									align: 'left',
									sortable: false,
									value: 'stt'
								},
								{
									text: 'Tên thủ tục, cơ quan quản lý',
									align: 'left',
									sortable: true,
									value: 'dossierNo'
								},
								{
									text: 'Mã hồ sơ, số hồ sơ',
									align: 'left',
									sortable: true,
									value: 'createDate'
								},
								{
									text: 'Ngày gửi, ngày tiếp nhận, hạn xử lý',
									align: 'left',
									sortable: true,
									value: 'applicantName'
								},
								{
									text: 'Số chứng chỉ, ngày cấp',
									align: 'center',
									sortable: true,
									value: 'action'
								},
								{
									text: 'Nội dung',
									align: 'left',
									sortable: true,
									value: 'action'
								},
								{
									text: 'Ghi chú',
									align: 'center',
									sortable: true,
									value: 'action'
								}
							];
							
							const config = {};

							var url = '/o/frontendwebdossier/json/payment_files.json';
							
							axios.get(url, config).then(function (response) {
								var serializable = response.data;

								if (append) {
									vm.traCuuHoSoTableItems.push.apply(vm.traCuuHoSoTableItems, serializable.data);
								} else {
									vm.traCuuHoSoTableItems = serializable.data;
									vm.traCuuHoSoTableItemsTotal = serializable.total;
								}

								vm.xem_them = 'Xem thêm 8+ bản ghi';
								if (serializable.data.length === 0) {
									vm.xem_them = 'Tổng số ( ' + vm.traCuuHoSoTableItems.length + ' ) bản ghi'
								}
								vm.viewmore = false;
							})
								.catch(function (error) {
									console.log(error);
								});

						},
						_paggingTraCuuHoSoTable: function() {
							
							this._inidanhSachHoSoTable(false);
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
							console.log('--danhSachHoSoTable--');
							var vm = this;
							vm.viewmore = true;
							this.danhSachHoSoTableheaders = [
								{
									text: 'STT',
									align: 'left',
									sortable: false,
									value: 'stt'
								},
								{
									text: 'Tên thủ tục, tên doanh nghiệp',
									align: 'left',
									sortable: true,
									value: 'serviceName'
								},
								{
									text: 'Mã hồ sơ, số hồ sơ',
									align: 'left',
									sortable: true,
									value: 'dossierId'
								},
								{
									text: 'Ngày gửi, ngày tiếp nhận, hạn xử lý',
									align: 'left',
									sortable: true,
									value: 'submitDate'
								},
								{
									text: 'Số chứng chỉ, ngày cấp',
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
									align: 'center',
									sortable: true,
									value: 'lastActionNote'
								}
							];

							var paramsBuilder = {
								keyword: vm.keywordsSearch,
								owner: vm.applicantNameFilter.applicantIdNo,
								service: vm.serviceInfoFilter.serviceCode,
								follow: true,
								dossierNo: vm.dossierNoFilter,
								start: vm.start,
								end: vm.end,
							};
						
							
							
							if ( vm.keywordFilter != null ) {
								paramsBuilder['keyword'] = vm.keywordFilter;
							} else {
								paramsBuilder['keyword'] = vm.keywordsSearchDanhSachHoSo;
							}
							const config = {
								params: paramsBuilder,
								headers: {'groupId': themeDisplay.getScopeGroupId()}
								
							};

							var url = '/o/rest/v2/dossiers';
							
							axios.get(url, config).then(function (response) {
								var serializable = response.data;

								if (append) {
									vm.danhSachHoSoTableItems.push.apply(vm.danhSachHoSoTableItems, serializable.data);
								} else {
									vm.danhSachHoSoTableItems = serializable.data;
									vm.danhSachHoSoTableTotal = serializable.total;
								}

								vm.xem_them = 'Xem thêm 8+ bản ghi';
								if (serializable.data.length === 0) {
									vm.xem_them = 'Tổng số ( ' + vm.danhSachHoSoTableItems.length + ' ) bản ghi'
								}
								vm.viewmore = false;
							})
								.catch(function (error) {
									vm.danhSachHoSoTableItems = [];
								});

						},
						toDetailHoSo: function (item) {
							console.log(item);
							var vm = this;
							vm.detailModel = item;
							vm.detailPage = true;
							window.scrollBy(0, -99999);
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
					'onLoad': '_inilistDocumentIn',
					'events': {
						_inilistDocumentIn: function () {

							var vm = this;

							const config = {};

							var url = "/o/frontendwebpayment/json/payment_files.json";

							axios.get(url, config).then(function (response) {
								var serializable = response.data;
								vm.listDocumentInItems = serializable.data;
							})
							.catch(function (error) {
								console.log(error);
							});

						}
					}
				},
				'listDocumentOut': {
					'id': 'listDocumentOut',
					'name': 'listDocumentOut',
					'type': 'listview',
					'template': 'list_document_in_template',
					'onLoad': '_inilistDocumentOut',
					'events': {
						_inilistDocumentOut: function () {

							var vm = this;

							const config = {};

							var url = "/o/frontendwebpayment/json/payment_files.json";

							axios.get(url, config).then(function (response) {
								var serializable = response.data;
								vm.listDocumentOutItems = serializable.data;
							})
							.catch(function (error) {
								console.log(error);
							});

						}
					}
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

							const config = {};

							var url = "/o/frontendwebpayment/json/payment_files.json";

							axios.get(url, config).then(function (response) {
								var serializable = response.data;
								vm.listHistoryProcessingItems = serializable.data;
							})
							.catch(function (error) {
								console.log(error);
							});

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
                    'label': 'Lựa chọn cán bộ phân công xử lý ',
                    "item_text": "userName",
                    "item_value": "userId",
                    "single_line": true,
                    "hide_selected": true,
                    "chips": true,
                    "deletable_chips": true,
                    "loading": false,
                    "no-data-text": "Lua chon selected",
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
                }
			}
		});

		dossierViewJX._builder('dossierViewJX');
	});

</script>


<style>
	.card i,
	.card__text i {
		font-size: 16px;
	}

	.border-right-1 {
		border-right: 1px solid #e1e2e1;
	}
	code {
		cursor: pointer;
	}


	.border-right-1 {
		border-right: 1px solid #e1e2e1;
	}
	code {
		cursor: pointer;
	}

	.btn-view{
		min-height: 46px;
		box-shadow: none;
		border-radius: 0px;
	}

	html .theme--dark .navigation-drawer, html .application .theme--dark.navigation-drawer {
		background-color: #f6f6f6;
		padding: 25px 25px 0 0;
	}

	.navigation-drawer.theme--dark a{
		color: #757575;
		 height: 32px;
	}

	.navigation-drawer.theme--dark .list__tile__action:first-child .icon {
		color: #ffc107;
		padding-left: 8px;
	}

	.list--group {
		position: relative;
		padding: 0;
	}

	html .list__tile__action{
			min-width: 42px;
	}

	.theme--dark .list .list__tile--link:hover,
.application .theme--dark.list .list__tile--link:hover,
.theme--dark .list .list__tile--highlighted,
.application .theme--dark.list .list__tile--highlighted,
.theme--dark .list .list__group__header:hover,
.application .theme--dark.list .list__group__header:hover {
  background: rgb(246, 246, 246);
  text-decoration: none;
}

.navigation-drawer .list--group__header--active {
	background: rgb(236, 234, 234);
}

.navigation-drawer .list--group__header--active a {
	color: inherit;
}
.list__tile--highlighted, .list__tile--link:hover {
	background: rgb(236, 234, 234) !important;
	cursor: pointer;
}

.jx-content {
  width: -webkit-calc(100% - 300px);
  width: calc(100% - 300px);
}

.jx-content .jx-content-wrap {
  width: 100%;
}
.jx-content .jx-content-wrap .row-header {
  width: 100%;
}


.solo .input-group--switch_inline .switch-label {
	display: none;
}

.solo .input-group--switch_inline .switch-control .input-group__details,
.solo .radio-group--row .input-group__details{
	display: none;
}

.solo .input-group--switch_inline .switch-control .input-group--selection-controls,
.solo .radio-group--row .input-group--selection-controls{
	margin: 0;
}

.solo .input-group--switch_inline .switch-control .input-group--selection-controls > label{
	padding-left: 0;
}

.solo .radio-group--row {
	padding: 0 0 0 25px;
}

html object iframe {
	min-height: 600px;
}
</style>

<style>


.navigation-drawer .input-group--text-field{
  margin-top: -25px;
}

.navigation-drawer .input-group--text-field label {
	color: #757575 !important; 
	font-size: 16px;
	font-weight: normal;
}

.navigation-drawer .input-group__details:before, .navigation-drawer .input-group__details:before {
	background-color: rgba(0,0,0,0.42) !important;
}

html .application--wrap .menu {
	display: inline-block;
	position: relative;
	vertical-align: middle;
}

.theme--dark .input-group:not(.input-group--error):not(.input-group--focused):not(.input-group--disabled) .input-group__input .input-group__append-icon:not(:hover), 
.application .theme--dark.input-group:not(.input-group--error):not(.input-group--focused):not(.input-group--disabled) .input-group__input .input-group__append-icon:not(:hover), 
.theme--dark .input-group:not(.input-group--error):not(.input-group--focused):not(.input-group--disabled) .input-group__input .input-group__prepend-icon:not(:hover), 
.application .theme--dark.input-group:not(.input-group--error):not(.input-group--focused):not(.input-group--disabled) .input-group__input .input-group__prepend-icon:not(:hover) {
	color: rgba(0,0,0,0.54) !important;
}

.navigation-drawer .list--group__header {
	background: #4cbff1;
	margin-bottom: 10px;
	padding: 5px 0px 5px 0px;
}

.navigation-drawer .list--group__header li a {
  color: white;
}

.group_dossier_filter {
	margin-top: -15px;
}

.navigation-drawer .list__tile .list__tile__action:first-child {
  display: none;
}

html .navigation-drawer .list--group__header > li > .list__tile:hover {
  background: transparent !important;
  
}

.list__tile--highlighted, .list__tile--link:hover {
	background: rgb(236, 234, 234) !important;
	cursor: pointer;
}

.navigation-drawer.theme--dark .list__tile__action:last-child .icon {
	color: white;
}

.navigation-drawer.theme--dark .list--group .list__tile__action:last-child .icon{
  color: #757575;
}

.menu__content {
	margin-top: -10.4%;
    margin-left: -5.1%;
}

.navigation-drawer.theme--dark .input-group input, 
.navigation-drawer.theme--dark .input-group textarea{
	color: rgba(0,0,0,0.87);
}

input[type="text"]::-webkit-input-placeholder {color: #757575 !important; }
input[type="text"]:-moz-placeholder {color:#757575; opacity: 1;}
input[type="text"]::-moz-placeholder {color: #757575; opacity: 1;}
input[type="text"]:-ms-input-placeholder {color: #757575;}

.menu__content .btn--outline span {
	color: #757575;
}


</style>

<style>

.table__overflow .table > thead > tr > th {
	vertical-align: middle;
	border-bottom: 0px solid #ddd;
	float: unset;
	font-family: "Roboto-Bold";
	font-size: 13px;
}
.table__overflow .table{
	margin: 0;
}
html .elevation-1 {
	-webkit-box-shadow: 0px 2px 1px -1px rgba(0,0,0,0.2), 0px 2px 1px -1px rgba(0,0,0,0.2), 2px 2px 1px -1px rgba(0,0,0,0.1);
	box-shadow: 0px 2px 1px -1px rgba(0,0,0,0.2), 0px 2px 1px -1px rgba(0,0,0,0.2), 2px 2px 1px -1px rgba(0,0,0,0.1);
	border-left: 1px solid rgba(0,0,0,0.1);
}
table.table thead th {
	white-space: normal !important;
}
table.table thead th:not(:nth-child(1)), table.table tbody th:not(:nth-child(1)), 
table.table thead th:first-child, table.table tbody th:first-child {
	padding: 0 10px !important;
}

body .danhSachHoSoTable__class table.table th {
  padding: 0 10px;
  vertical-align: middle;
}

body .danhSachHoSoTable__class table.table th:nth-child(2) {
  width: 40px;
}
body .danhSachHoSoTable__class table.table th:nth-child(3) {
  width: 400px;
}
body .danhSachHoSoTable__class table.table th:nth-child(5) {
  width: 100px;
}
body .danhSachHoSoTable__class table.table th:nth-child(6) {
  width: 200px;
}
body .danhSachHoSoTable__class table.table th:nth-child(7) {
  width: 300px;
}

.grey-opencps-panel {
	background-color: #e1e2e1 !important;
	border-color: #E1E2DE !important;
}
.grey-opencps-panel .tabs__li a {
	color: rgba(0,0,0,0.87) !important;
}

.grey-opencps-panel .tabs__wrapper {
	margin: 0 !important;
}
.slide-y-transition-leave-active {
  display: none !important;
}
.align-center-flex{
	align-items: center;
}
.small-btn-x{
	width: 26px !important;
	height: 26px !important;
}
.row-list-style{
	border-bottom: 1px solid #e1e2e1;
}

.opencps_list_border_style .card {
	border-radius: 0;
	border: 0;
	margin: 0;
}

html .tabs.tabs--scroll-bars {
	-webkit-box-shadow: 0px 2px 1px -1px rgba(0,0,0,0.2), 0px 2px 1px -1px rgba(0,0,0,0.2), 2px 2px 1px -1px rgba(0,0,0,0.1) !important;
	box-shadow: 0px 2px 1px -1px rgba(0,0,0,0.2), 0px 2px 1px -1px rgba(0,0,0,0.2), 2px 2px 1px -1px rgba(0,0,0,0.1) !important;
	border-left: 1px solid rgba(0,0,0,0.1);
}
.opencps-dossier-info {
	border-left: 1px solid rgba(0,0,0,0.1);
}

.tabs__container{
	margin: 0;
}

.theme--dark .chip:not(.chip--outline) .chip__close, .application .theme--dark.chip:not(.chip--outline) .chip__close {
    color: rgba(0,0,0,0.54) !important;
    pointer-events: none !important;
	
}
.chip--removable .chip__content {
    cursor: pointer;
	
white-space: normal;
    height: auto;	
}

.pagination__more{
	    border: 0 !important;
    background: transparent !important;
    padding: 0 !important;
    margin-left: 4px !important;
}
</style>