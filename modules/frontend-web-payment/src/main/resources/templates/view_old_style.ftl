
<#include "init.ftl">
	
<div id="paymentViewJX"></div>

<!-- TODO paymentViewJX template one page view List detail template -->
<div id="activity_expand_list_template" class="hidden">

    <div class="flex xs12 ">

        <div class="layout wrap row-blue">

            <div class="flex xs3 dossier-number">

                <div class="order-number">
					<input type="checkbox" v-bind:value="index" v-model="paymentCheck" />
				</div>

                <div class="order-number-detail">
                    <span> {{index + 1}} | #</span>
                    <span class="text-normal">
                        {{item.dossierNo}}
                    <span>
                </div>

            </div>

            <div class="flex xs3 receive-number">

                <span class="text-normal">Ngày lập: {{item.createDate | date}}</span>

            </div>

            <div class="flex xs6 text-right">

				<span class="text-normal pr-2" style="position: relative;bottom: -2px;">Số tiền: </span>

				<span class="red--text text-bold" style="padding-right: 15px; position: relative; bottom: -2px; "> {{item.paymentAmount | money}} VNĐ </span>

                <v-btn flat class="mx-0 my-0 px-0 py-0 btn-border-left" color="grey darken-1" v-on:click.native="toPaymentDetail(index)">
                    Đi tới màn hình chi tiết
                    <v-icon>forward</v-icon>
                </v-btn>

            </div>

        </div>

        <div class="layout wrap panel">

            <div class="flex xs12">

                <v-card-text>

                    <div>
                        <v-icon>person</v-icon>
                        {{item.applicantName}}
                    </div>

					<div class="my-2">
                        <span class="text-bold">
                            Ngày yêu cầu nộp phí:
                        </span>
						{{item.modifiedDate | date}}
                    </div>

                </v-card-text>

            </div>

        </div>

    </div>

</div>

<!-- TODO Template Xây dựng navigation -->
<div id="menu_template">
    <div class="layout row wrap">
        <div class="flex xs12 ">
			<v-btn block color="blue darken-2 my-0 btn-view">QUẢN LÝ THU PHÍ LỆ PHÍ</v-btn>
		</div>
    	<div class="flex xs12 mb-3 px-0" jx-bind="listgroupFilter"></div>
	</div>
</div>

<!-- TODO paymentViewJX template one page -->
<div id="paymentViewJX_form_template" class="hidden">
    <div class="jx-content-wrap">
        <div class="layout wrap">
        	<!-- TODO detailTemplate page -->
			<div v-if="detailPage" style="width: 100%;">
				<div class="row-header">
					<div class="background-triangle-big"> Hình thức thanh toán </div>
					<div class="layout row wrap header_tools row-blue">

						<div class="flex xs6 solo">
							
							<v-radio-group v-model="paymentMethodSearch" row>
								<v-radio label="Tiền mặt" value="Nộp trực tiếp" ></v-radio>
								<v-radio label="Chuyển khoản" value="Chuyển khoản"></v-radio>
							</v-radio-group>

						</div>
						<div class="flex xs3 text-left">
							<v-btn flat @click.prevent.stop="paymentConfirmSingle(detailModel.index)">
								<v-icon>done</v-icon>
								Xác nhận
							</v-btn>
						</div>
						<div class="flex xs3 text-right">
	
							<v-btn flat class="mx-0 my-0 px-0 py-0 btn-border-left" color="grey darken-1" v-on:click.native="detailPage = !detailPage">
								Quay lại
								<v-icon>undo</v-icon>
							</v-btn>

						</div>

					</div>

				</div>
				<div class="panel">
						<object id="objectView2" data="" width="100%" height="100%">
							<iframe src="http://localhost:8080/documents/20147/0/20171208081357103ILHVICIQ..pdf/5b8fc3ea-a9c7-6b9c-1a45-9b737190aaa5" width="100%" height="100%">    </iframe>
						</object>
				</div>
			</div>

			<v-slide-x-transition>
				<div class="row-header" v-if="!detailPage">
					<div class="background-triangle-big"> Kết quả tìm kiếm</div>
					<div class="layout row wrap header_tools">
						<div style="margin-left: 15px;"><v-checkbox v-model="allPaymentCheck" class="solo" light color="primary"  @change="processPaymentAll(allPaymentCheck)" ></v-checkbox></div>
						<div class="flex xs5 sm5 solo" jx-bind="paymentMethodSearch">

						</div>
			
						<div class="flex xs5 sm2" jx-bind="paymentConfirm">

						</div>

						<div class="flex pr-3" jx-bind="keywordsSearch">

						</div>

					</div>
				</div>
			</v-slide-x-transition>
			<v-slide-x-transition>
				<div class="layout wrap" jx-bind="paymentList" v-if="!detailPage">

				</div>
			</v-slide-x-transition>
			<div class="text-center" style="width: 100%;" v-if="!detailPage">
				<v-scale-transition>

					<v-btn dark v-show="showmore" color="blue darken-2" :loading="viewmore" :disabled="viewmore">
						Tổng số ( {{paymentListItems.length}} ) bản ghi
						<span slot="loader">Đang tải ...</span>
					</v-btn>

				</v-scale-transition>
			</div>
        </div>
	</div>
</div>

<script type="text/javascript">

    document.addEventListener('DOMContentLoaded', function (event) {

        var paymentViewJX = new VueJX({
            el: 'paymentViewJX',
            jxtemplate: 'paymentViewJX_form_template',
            pk: 1,
            groupid: 20143,
            data: {
                stateScroll: 0,
                detailPage: false,
                viewmore: false,
                showmore: false,
				detailModel: {

				},
				allPaymentCheck: false,
				paymentCheck: []

            },
            onScroll: "onScroll",
            schema: {
				// TODO menu filter
                'navigationFilter': {
                    'id': 'navigationFilter',
                    'name': 'navigationFilter',
                    "type": "navigation",
                    "template": "menu_template",
                    "template_content": "paymentViewJX_form_template"
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
                        filterChange: function(value){
                            console.log(value);
                            this.listgroupFilterselected = value;
                        },
                        _initlistgroupFilter: function(){
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
                //TODO filter component
                'paymentMethodSearch': {
                    'id': 'paymentMethodSearch',
                    'name': 'paymentMethodSearch',
                    'type': 'radio',
                    "label": "",
					"labelClass": "",
					"controlClass": "xs12",
					"switch_inline": true,
					"tabindex": 0,
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
                    'onChange': 'changepaymentMethodSearch',
                    'events': {
                        _initpaymentMethodSearch: function () {

                            this.paymentMethodSearch = 'Nộp trực tiếp';

                        },
                        changepaymentMethodSearch: function () {
                            var vm = this;
                            vm._inipaymentList();
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
							
							if (vm.paymentCheck.length === 0) {
								alert('Chưa chọn phiếu thanh toán nào?. ');
								return;
							}

							var labelBtn = 'Xác nhận';
							
							var dateConfirmPayment = new Date();
							var month = dateConfirmPayment.getMonth() + 1;
							var day = dateConfirmPayment.getDate() ;
							var year = dateConfirmPayment.getFullYear();

							var dialogContent = '<strong>Ngày xác nhận thu phí: </strong> '+day + '/' +month + '/' +year+' <br/>';
							dialogContent += '<strong>Người thực hiện: </strong> '+themeDisplay.getUserName()+' <br/>';
							dialogContent += '<strong>Hình thức thanh toán: </strong> '+vm.paymentMethodSearch+' <br/>';
							dialogContent += '<strong>Xác nhận thu phí cho: </strong> '+vm.paymentCheck.length+' phiếu thanh toán. <br/>';

							vm.$dialog.confirm(dialogContent, {
								html: true,
								loader: true,
								okText: labelBtn,
								cancelText: 'Quay lại',
								animation: 'fade'
							})
							.then((dialog) => {
								

								for( var i=vm.paymentListItems.length - 1; i>=0; i--){
									for( var j=0; j<vm.paymentCheck.length; j++){
										if(vm.paymentListItems[i] && (i === vm.paymentCheck[j])){
											vm.paymentListItems.splice(i, 1);
										}
									}
								}

								dialog.close();
							})
							.catch(() => {
								console.log('process aborted')
							})
						}, 
						paymentConfirmSingle: function (index) {
							var vm = this;
							console.log(vm.paymentMethodSearch);

							var labelBtn = 'Xác nhận';
							
							var dateConfirmPayment = new Date();
							var month = dateConfirmPayment.getMonth() + 1;
							var day = dateConfirmPayment.getDate() ;
							var year = dateConfirmPayment.getFullYear();

							var dialogContent = '<strong>Ngày xác nhận thu phí: </strong> '+day + '/' +month + '/' +year+' <br/>';
							dialogContent += '<strong>Người thực hiện: </strong> '+themeDisplay.getUserName()+' <br/>';

							vm.$dialog.confirm(dialogContent, {
								html: true,
								loader: true,
								okText: labelBtn,
								cancelText: 'Quay lại',
								animation: 'fade'
							})
							.then((dialog) => {

								vm.paymentListItems.splice(index,1)

								dialog.close();
							})
							.catch(() => {
								console.log('process aborted')
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
                    'append_icon': 'search'
                },
                // TODO List Payment declare
                'paymentList': {
                    'id': 'paymentList',
                    'name': 'paymentList',
                    'type': 'listview',
                    'template': 'activity_expand_list_template',
                    'onLoad': '_inipaymentList',
                    'events': {
                        _inipaymentList: function () {

							var vm = this;

							const config = {};

							var url = "/o/frontendwebpayment/json/payment_files.json";

							axios.get(url, config).then(function (response) {
								var serializable = response.data;
								vm.paymentListItems = serializable.data;
							})
							.catch(function (error) {
								console.log(error);
							});

                        },
						toPaymentDetail: function (index) {
							var vm = this;
							vm.detailPage = !vm.detailPage;

							vm.detailModel = vm.paymentListItems[index];
							vm.detailModel.index = index;

							window.scrollBy(0, -99999);
						},
						processPaymentAll: function (value){
							var vm = this;
							
							if (!value) {
								vm.paymentCheck = [];
								return;
							}

							var allDataPaymentList = vm.paymentListItems;

							var index = 0;
							for ( var key in allDataPaymentList ) {
								vm.paymentCheck.push(index);
								index = index + 1;
							}

						},
						processPayment: function (index) {
							var vm = this;
							var labelBtn = 'Xác nhận';
							
							var dateConfirmPayment = new Date();
							var month = dateConfirmPayment.getMonth() + 1;
							var day = dateConfirmPayment.getDate() ;
							var year = dateConfirmPayment.getFullYear();

							var dialogContent = '<strong>Ngày xác nhận thu phí: </strong> '+day + '/' +month + '/' +year+' <br/>';
							dialogContent += '<strong>Người thực hiện: </strong> '+themeDisplay.getUserName()+' <br/>';

							vm.$dialog.confirm(dialogContent, {
								html: true,
								loader: true,
								okText: labelBtn,
								cancelText: 'Quay lại',
								animation: 'fade'
							})
							.then((dialog) => {
								dialog.close();
							})
							.catch(() => {
								console.log('process aborted')
							})
						},
                        onScroll(e) {
                            var onBottom = window.pageYOffset || document.documentElement.scrollTop;
                            var vm = this;
                            if (onBottom > (200 + vm.stateScroll)) {
                                vm.stateScroll += 250;
                                vm.viewmore = true;
                                vm.showmore = true;
 								console.log(onBottom);
								 console.log((200 + vm.stateScroll));
                                setTimeout(function () {
                                   
                                    vm.viewmore = false;
                                    vm.showmore = true;
                                }, 1000);

                            }

                        }
                    }
                }

            }
        });

        paymentViewJX._builder('paymentViewJX');
    });

</script>


<style>
	.solo.checkbox .input-group__details {
		display: none;
	}
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

	.btn-view{
		min-height: 46px;
		box-shadow: none;
		border-radius: 0px;
	}

    html .theme--dark .navigation-drawer, html .application .theme--dark.navigation-drawer {
        background-color: #f6f6f6;
    }

    .navigation-drawer.theme--dark a{
        color: #757575;
         height: 32px;
    }

    .navigation-drawer.theme--dark .icon {
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

