
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
				offsetTop: 0,
				stageFilterView: null,
				detailPage: false,
				viewmore: false,
				detailModel: {

				},
				xem_them: 'Không tìm thấy hồ sơ nào',
				hoso_title_table: 'Danh sách hồ sơ',
                processSteps: [],
                stepModel: {

                },
                showContactDetail: false,
				dossierFiles: []
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
                            
                            var url = '/o/rest/v2/dossiers/'+vm.detailModel.dossierId+'/nextactions';
                            
                            axios.get(url, config).then(function (response) {
                                var serializable = response.data;

                                vm.processSteps = serializable.data;

                            })
                                .catch(function (error) {
                                    console.log(error);
                                });
                        },
						filterAllDossierWithOutStatus: function () {
							this.stageFilterView = 'danh_sach';
							this._inidanhSachHoSoTable(false);
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
							vm.detailPage = false;
							vm.listgroupHoSoFilterselected = item.id;
							
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
						_initlistgroupHoSoFilter: function(){
							var vm = this;
                            vm.stageFilterView = 'danh_sach';

                            vm.listgroupHoSoFilterItems = [];

                            const config = {};

                            var url = '/o/rest/v2/statistics/dossiers/todo';
                            
                            axios.get(url, config).then(function (response) {
                                var serializable = response.data;

                                for (var key in serializable.data) {
                                     vm.listgroupHoSoFilterItems.push({
										id: serializable.data[key].dossierSubStatus,
										idSub: serializable.data[key].dossierSubStatus,
										title: serializable.data[key].statusName,
										level: serializable.data[key].level,
										count: serializable.data[key].count,
										action: 'folder',
										action_active: 'folder_open',
										/**
										items: [
											{
												id: 'danh_sach_1',
												title: 'DANH SÁCH HỒ SƠ 1',
												count: serializable.data[key].count
											},
											{
												id: 'danh_sach_2',
												title: 'DANH SÁCH HỒ SƠ 2',
												count: serializable.data[key].count
											}
                                    	]
										*/
                                    });
                                }
                            })
                                .catch(function (error) {
                                    console.log(error);
                                });
							
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
							vm.listgroupTraCuuFilterselected = item.id;
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
									title: 'Phương tiện sản xuất lắp rap'
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
					"multiple": false,
					"tags": false, //custom input not avaliable in select
					"chips": true,
					"deletable_chips": true,
					"loading": false,
					"no-data-text": "Lua chon selected",
					"items": [],
					"onLoad": "_initApplicantNameFilterData",
					"onChange": "_applicantFilterDanhSachHoSoOnchange($event)",
					"onRemove": "_applicantFilterDanhSachHoSoOnRemove",
					'onClick': '_applicantFilterDanhSachHoSoOnClear',
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
									vm.xem_them = 'Tổng số ( ' + serializable.total + ' ) bản ghi'
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
									text: 'Tên thủ tục, tên doanh nghiệp',
									align: 'left',
									sortable: true,
									value: 'applicantName'
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
								start: vm.danhSachHoSoTablepage * 8 - 8,
								end: vm.danhSachHoSoTablepage * 8,
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
									vm.traCuuHoSoTableItems.push.apply(vm.traCuuHoSoTableItems, serializable.data);
								} else {
									vm.traCuuHoSoTableItems = serializable.data;
									
									vm.danhSachHoSoTableTotal = Math.ceil(serializable.total / 8);
								}

								vm.xem_them = 'Xem thêm 8+ bản ghi';
								if (serializable.data.length === 0) {
									vm.xem_them = 'Tổng số ( ' + serializable.total + ' ) bản ghi'
								}
								vm.viewmore = false;
							})
								.catch(function (error) {
									console.log(error);
									vm.traCuuHoSoTableItems = [];
								});

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
									value: 'applicantName'
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

							var statusParam = null;
							var substatusParam = null;

							if (vm.stageFilterView !== 'danh_sach') {
								statusParam = vm.stageFilterView;
								substatusParam = vm.stageFilterView;
							}

							var paramsBuilder = {
								keyword: vm.keywordsSearch,
								owner: vm.applicantNameFilter.applicantIdNo,
								service: vm.serviceInfoFilter.serviceCode,
								follow: true,
								dossierNo: vm.dossierNoFilter,
								start: vm.danhSachHoSoTablepage * 8 - 8,
								end: vm.danhSachHoSoTablepage * 8,
								status: statusParam,
								substatus: substatusParam
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

									vm.danhSachHoSoTableTotal = Math.ceil(serializable.total / 8);
									
								}

								vm.xem_them = 'Xem thêm 8+ bản ghi';
								if (serializable.data.length === 0) {
									vm.xem_them = 'Tổng số ( ' + serializable.total + ' ) bản ghi'
								}
								vm.viewmore = false;
							})
								.catch(function (error) {
									console.log(error);
									vm.danhSachHoSoTableItems = [];
								});

						},
						toDetailHoSo: function (item) {
							console.log(item);
							var vm = this;

							// call DetailAPI.
							const config = {
								headers: {'groupId': themeDisplay.getScopeGroupId()}
							};

							var url = '/o/rest/v2/dossiers/'+item.dossierId;
							
							axios.get(url, config).then(function (response) {
								var serializable = response.data;

								vm.detailModel = serializable;
								vm.detailPage = true;
								window.scrollBy(0, -99999);
								
								vm._inilistDocumentIn(vm.detailModel);

							})
								.catch(function (error) {
									console.log(error);
								});
							
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

							const config = {
								headers: {'groupId': themeDisplay.getScopeGroupId()}
							};

							var url = "/o/rest/v2/dossiertemplates/"+item.dossierTemplateNo;
							var urlFiles = "/o/rest/v2/dossiers/"+item.dossierId+"/files";

							axios.get(urlFiles, config).then(function (response) {
								var serializable = response.data;
								vm.dossierFiles = serializable.data;
								
								axios.get(url, config).then(function (response) {
									var serializable = response.data;
									
									var listIn = [], listOut = [];
									for(var key in serializable.dossierParts){
										
										var countData = 0;
										for(var keyFile in vm.dossierFiles){
											
											if ( vm.dossierFiles[keyFile].dossierPartNo === serializable.dossierParts[key].partNo ) {
												countData = countData + 1;
												
											}
											
											if ( vm.dossierFiles[keyFile].dossierPartType === 2 ) {
												serializable.dossierParts[key].referenceUid = vm.dossierFiles[keyFile].referenceUid;
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
									


								})
								.catch(function (error) {
									console.log(error);
								});
							})
							.catch(function (error) {
								console.log(error);
							});

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
							console.log(vm.listDocumentInPartNoItems);
							vm.popUpViewDossierFile = !vm.popUpViewDossierFile;
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
							const config = {
								headers: {'groupId': themeDisplay.getScopeGroupId()}
							};

							var url = '/o/rest/v2/dossiers/'+vm.detailModel.dossierId+'/files/'+item.referenceUid;
							
							axios.get(url, config).then(function (response) {
								var serializable = response.data;
								
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
							vm.listHistoryProcessingItems = [];

						},
						selectDossierActionTab(){
						
							console.log("___________-");
							console.log(this.detailModel.govAgencyName);
							var vm = this;

							const config = {
								headers: {
									'groupId': themeDisplay.getScopeGroupId()
								},
								data: {
									dossierId: this.detailModel.dossierId
								}
							};
							
							//var url = "/o/frontendwebdossier/json/dossier_logs.json?t=1";
							var url = "/o/rest/v2/dossiers/"+vm.detailModel.dossierId+"/logs";
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
						
						},
						downloadFile( fileAttachId ){
							console.log(fileAttachId);
							var url = "/o/rest/v2/employees/1401/photo";
							window.open(url); 
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
							this.popUpViewDossierFile = false;
						},
						popUpViewDossierFileSave: function () {
							console.log("save popup");
							var vm = this;
							vm.popUpViewDossierFile = false;
						},
						previewDossierPDF: function (item) {
							var vm = this;
							
							// TODO: call API lay file
							var url ="/o/rest/v2/employees/1303/photo" ;
							vm._showFile({
								config : {
									headers: {'groupId': themeDisplay.getScopeGroupId()},
									responseType: 'blob'
								},
								url : url
							});

						},
						_showFile: function (options) {
							
							axios.get(options.url, options.config).then(function (response) {
								
								var url = window.URL.createObjectURL(response.data);
								var iFrame = document.getElementById("dossierPDFView");
								
								iFrame.innerHTML = '<iframe src="'+url+'" width="100%" height="100%"> </iframe>';
							})
								.catch(function (error) {
									console.log(error);
								});
							
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
	padding: 5px 0px 5px 0px;
}

.navigation-drawer .list--group__header li a {
  color: white;
}

.group_dossier_filter {
	margin-top: -15px;
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

.setting_action_all {
	font-size: 20px;
	padding: 0 5px 0 12px;
	cursor: pointer;
}

</style>

<style>
.panel-dossier-navigation {
    font-size: 13px;
    box-shadow: none !important;
}
.panel-dossier-navigation > li {
    background-color: transparent !important;
}
.panel-dossier-navigation > li:first-child {
    margin-bottom: 10px;
}
.panel-dossier-navigation > li > div:first-child {
    background-color: #14bef0;
    color: white;
    padding: 6px 15px;
}
.panel-dossier-navigation > li > div:first-child .icon {
    color: white !important;
    padding-left: 0;
}

.panel-dossier-navigation .tracuu-dossier-navigation .list {
    border: 1px solid #ddd;
    border-top: 0;
}
.panel-dossier-navigation .tracuu-dossier-navigation .list .list__tile__action {
    display: none;
}
.panel-dossier-navigation .tracuu-dossier-navigation > ul > li:not(:last-child) {
    border-bottom: 1px solid #ddd;
}
.panel-dossier-navigation .tracuu-dossier-navigation ul {
    padding: 0;
}
.panel-dossier-navigation .tracuu-dossier-navigation a {
    color: #2a2a2a !important;
    font-size: 13px;
    padding: 6px 15px;
}

.panel-dossier-navigation .status-dossier-navigation ul {
    padding: 0;
}
.panel-dossier-navigation .status-dossier-navigation a {
    color: #2a2a2a !important;
    font-size: 13px;
    padding: 5px;
}
.panel-dossier-navigation .status-dossier-navigation a .status__counter {
    position: absolute;
    right: 7px;
    top: 7px;
    width: 40px;
    background: #f6f6f6;
    text-align: center;
}
.panel-dossier-navigation .status-dossier-navigation .list--group .list__tile__action .icon {
    color: #2a2a2a !important;
}
.control-menu {
    z-index: 200;
}
</style>