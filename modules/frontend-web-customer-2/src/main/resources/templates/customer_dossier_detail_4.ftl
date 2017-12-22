<#if (Request)??>
<#include "init.ftl">
</#if>
<div id="detailDossier">
	<div class="box">

		<input type="hidden" name="dossierStatus" id="dossierStatus">
		
		<input type="hidden" name="dossierTemplateNo" id="dossierTemplateNo">
		<div class="row-header align-middle">
			<div class="background-triangle-big">Tên thủ tục</div> 
			<span class="text-bold" data-bind="text:serviceName"></span>
			<div class="pull-right group-icons">
				<a href="">
					<i class="fa fa-paper-plane" aria-hidden="true"></i> 
					Nộp hồ sơ
				</a>
			</div>
		</div>

		<div class="dossier-general-info P15 MB30">
			<div class="col-sm-4">
				<div class="row">
					<span class="text-bold">Tình trạng</span>: <i data-bind="text:dossierStatusText" class="red"></i>
				</div>
				<div class="row">
					<i data-bind="html:briefNote" class="text-light-gray"></i>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="row MB5" id="">
					<span class="text-bold">Mã tiếp nhận</span>: <span data-bind="text:dossierNo"></span>
					
				</div>
				<div class="row" id="">
					<span class="text-bold">Mã số hồ sơ</span>: <span data-bind="text : dossierId"></span>
				</div>
			</div>
			
			<div class="col-sm-4">
				<div class="row MB5" id="">
					<span class="text-bold">Thời gian gửi</span>: <span data-bind="text:submitDate"></span>
				</div>
				<div class="row" id="">
					<a href="javascript:;" class="text-light-blue text-underline">Thông tin chủ hồ sơ</a>
				</div>
			</div>
			
			
			<div class="col-sm-12">
				<span data-bind="attr : {actionNote1 : actionNote1}" id="actionNote1"></span> 
			</div>
		</div>

		<div class="guide-section PB0">
			<div class="head-part" data-toggle="collapse" data-target="#collapseDossierG">
				<div class="background-triangle-small">
					<i class="fa fa-star"></i>

				</div> 
				<span class="text-uppercase hover-pointer">Hướng dẫn</span> 
				<i class="fa fa-angle-down pull-right hover-pointer MR15" aria-hidden="true" style="font-size: 150%;"></i>
			</div>	

			<div class="content-part collapse PB15 toggle-hide" id="collapseDossierG">
				<span data-bind="html:dossierNote"></span>
				<#-- <p class="MB0 text-light-blue PB15"><a href="javascript:;" id="guide-toggle">Xem thêm >></a></p> -->
			</div>

		</div>

		<div class="row">
			<div class="col-sm-12">
				<form id="dossierFormSubmiting">
					<div class="dossier-parts">
						<div class="head-part align-middle MB5" data-toggle="collapse" data-target="#lsDossierTemplPart">
							<div class="background-triangle-small">I</div> 
							<div class="col-sm-12 PL0">
								<span class="text-uppercase hover-pointer">Thành phần hồ sơ</span> 
								<span class="hover-pointer pull-right"><i class="text-light-gray">Những thành phần hồ sơ có dấu 
									(<span class="red">*</span>) là thành phần bắt buộc</i>
									<span>
										<i class="fa fa-angle-down hover-pointer" aria-hidden="true" style="font-size: 150%;"></i>
									</span>
								</span>
							</div>
						</div>
						<div class="content-part collapse in" id="lsDossierTemplPart">
							<#-- <#include "customer_dossier_online_form.ftl"> -->
						</div>
						<script type="text/x-kendo-template" id="templateDossierPart">
							#if(partType == 1){#
							<div class="row-parts-head align-middle">
								<span class="text-bold MR5">#:itemIndex#.</span>
								<span  #if(hasForm){# data-toggle="collapse" data-target="\\#collapseDossierPart#:id#" #}# >
									#:partName# 
									#if(required){#
									<span class="red">*</span>
									#}#
								</span>

								<div class="actions">
									<a href="javascript:;" class="text-light-blue uploadfile-form-repository" data-toggle="tooltip" data-placement="top" title="Tải giấy tờ từ kho lưu trữ" part-no="#:id#>
										<i class="fa fa-archive" aria-hidden="true"></i>
									</a>

									<label class="MB0 ML10 hover-pointer" for="file#:id#" title="Tải file lên" >
										<i class="fa fa-upload text-light-blue"></i>
									</label>

									<input type='file' id="file#:id#" name="file#:id#" class="hidden dossier-file" #if(multiple){# multiple #}# part-no="#:id#" file-template-no="#:fileTemplateNo#">

									<a href="javascript:;" class="dossier-component-profile" data-toggle="tooltip" data-placement="top" title="Số tệp tin" data-partno="#:id#" data-number="#if(hasForm){# 1 #}else {# 0 #}#">
										<span class="number-in-circle" >#if(hasForm){# 1 #}else {# 0 #}#</span>
									</a>

									<a href="javascript:;" class="text-light-gray delete-dossier-file" data-toggle="tooltip" data-placement="top" title="Xóa" data-partno="#:id#">
										<i class="fa fa-trash-o" aria-hidden="true"></i> Xóa
									</a>
								</div>
							</div>

							#if(hasForm){
							var dossierFile =  getReferentUidFile(${dossierId},id);
							#

							<div class="collapse" id="collapseDossierPart#:id#">

								<div class="col-xs-12 col-sm-12 text-right">
									<button id="btn-save-formalpaca#:id#" class="btn btn-active MB10 MT10 MR20 saveForm saveFormAlpaca" 
									type="button" data-pk="#:id#" referenceUid="#:dossierFile.referenceUid#">Ghi lại</button>
									<input type="hidden" name="" id="dossierFileId#:id#" value="#:dossierFile.dossierFileId#">
								</div>

								<div class="col-sm-12" #if(dossierFile.referenceUid){# style="height:450px; width:100%;overflow:auto;" #}# >

									<form id="formPartNo#:id#">

									</form>

								</div>
							</div>
							
							#

							$.ajax({
							url : "${api.server}/dossiers/${dossierId}/files/"+dossierFile.referenceUid+"/formscript",
							dataType : "text",
							type : "GET",
							headers : {"groupId": ${groupId}},
							success : function(result){
							$("\\#formPartNo"+id).empty();
							var alpaca = eval("(" + result + ")");
							var formdata = fnGetFormData(${dossierId},dossierFile.referenceUid);
							if(formdata){
							$("\\#validPart"+id).val("1");
						}
						alpaca.data = formdata;

						$("\\#formPartNo"+id).alpaca(alpaca);

						<#-- $("\\#formPartNo"+id).append('<div class="row"><div class="col-xs-12 col-sm-12 "><button id="btn-save-formalpaca'+id+'" class="btn btn-active MB10 MT10 saveForm" type="button" data-pk="'+id+'" referentUid="'+referentUidFile+'">Ghi lại</button></div></div>'); -->

					},
					error : function(result){

				}
			});
		}#

		#}#
	</script>
</div>
</form>
</div>
</div>


<div class="dossier-parts">
	<div class="head-part align-middle" data-toggle="collapse" data-target="#collapseDossierResult">
		<div class="background-triangle-small">II</div> 
		<div class="col-sm-12 PL0">

			<span class="text-uppercase hover-pointer">Kết quả</span>
			<i class="fa fa-angle-down pull-right hover-pointer" aria-hidden="true" style="font-size: 150%;"></i>
		</div>
		
	</div>
	<div class="content-part collapse in" id="collapseDossierResult">
		<div class="row-parts-head P0">
			<ul class="ul-with-border">
				<div id="listViewDossiserFileTemplate"></div>
			</ul>
			<script type="text/x-kendo-template" id="templateDossiserFileTemplate">
				<li>
					<div class="row">
						<div class="col-sm-12">
							<a href="${api.server}/dossiers/${(dossierId)!}/files/#:referenceUid#" class="download-file-result" data-pk="#:referenceUid#">
								<i class="fa fa-download"></i> 
								#:displayName#
							</a>
						</div>
					</div>
				</li>
			</script>
		</div>
	</div>
</div>

<div class="dossier-parts">
    <div class="head-part align-middle" data-toggle="collapse" data-target="#collapseDossierPart">
        <div class="background-triangle-small">III</div> 
        <div class="col-sm-12 PL0">

            <span class="text-uppercase hover-pointer">Tiến trình xử lý</span>
            <i class="fa fa-angle-down pull-right hover-pointer" aria-hidden="true" style="font-size: 150%;"></i>
        </div>
        
    </div>
    <div class="content-part collapse in" id="collapseDossierPart">
        <div class="row-parts-head MT5">
            <ul id="listViewDossiserLog" class="ul-default mimic-table">
                
            </ul>
            <script type="text/x-kendo-template" id="templateDossiserLog">
                <li class="clearfix eq-height-lg P0">
                    <div class="orderNo col-sm-0 text-center center-all row-blue P15">
                        #:itemIndex#.
                    </div>

                    #
                    var jobposTitle = "";
                    var briefNote = "";
                    var dossier ;
                    try {
                    
                        var payLoadObj = payload;

                        jobPosName = payLoadObj.hasOwnProperty("jobPosName")?payLoadObj.jobPosName : "";
                        stepInstruction = payLoadObj.hasOwnProperty("stepInstruction")?payLoadObj.stepInstruction : "";
                        dossier = payLoadObj.hasOwnProperty("files")?payLoadObj.files : "";
						
	                }catch(e){
		                console.log(e);
		            }
		            #

		            <div class="col-sm-12 M0 P5 PL10">
		                <span class="text-bold">#:author# </span> <span class="text-light-gray">(#:jobPosName#) &nbsp;</span> <span class="text-light-blue">#:stepInstruction#</span> 
		                <p>
		                    #if ( createDate!="" && createDate!=null ) {#
		                        #= kendo.toString(kendo.parseDate(createDate, 'yyyy-MM-dd'), 'hh:mm - dd/MM/yyyy')#
		                    #}#
		                </p>

		                <p>Ý kiến: #:content#</p>

		                #
		                if(dossier){
			                for(var i = 0 ; i < dossier.length ; i++){
				                #
					                <p>
					                	<a target="_blank" href="${api.server}/dossiers/${dossierId}/files/#:dossier[i].dossierFileId#" class="text-greyy text-hover-blue">
					                		<i aria-hidden="true" class="fa fa-download PR5"></i>
					                		#:dossier[i].fileName#
					                	</a> 
					                </p>
				                #
			    			} 
						}
						#
					</div>
				</li>
			</script>

		</div>
	</div>
</div> 


<div class="row-parts-content" id="postal" data-bind="value: viaPostal">
	<div class="row">
		<div class="col-sm-12 MB10" >
			<span class="text-bold">Ông bà sử dụng phương thức nhận kết quả hồ sơ qua đường Bưu Điện qua địa chỉ: </span> <br>
			<span data-bind="text:postalAddress"></span> <span data-bind="text:postalCityName"></span> <span data-bind="text:postalTelNo"></span>
		</div>
	</div>
</div>

<div id="uploadFileTemplateDialog" class="modal fade" role="dialog">
	
</div>

<div id="profileDetail" class="modal fade" role="dialog">

</div>

</div>
<div class="button-row MT20">
	<button class="btn btn-active" id="btn-submit-dossier" data-bind="value:submitting"><i class="fa fa-paper-plane" data-loading-text="<i class='fa fa-spinner fa-spin '></i> Đang xử lý..."></i> Nộp hồ sơ</button>
</div>
</div>

<script type="text/javascript">

	$(function(){
		$( "body" ).data( "dossierFiles", [] );

		$(document).off("change",".dossier-file");
		$(document).on("change",".dossier-file",function(){

			var partNo = $(this).attr("part-no");
			var fileTemplateNo = $(this).attr("file-template-no");
			var dossierTemplateNo = $("#dossierTemplateNo").val();
			var hasform = $(this).attr("hasform");

			funUploadFile($(this),partNo,dossierTemplateNo+"",fileTemplateNo,hasform);
			$(this).val("");
		});

		$(document).off("click",".uploadfile-form-repository");
		$(document).on("click",".uploadfile-form-repository",function(){
			var dossierId = "${(dossierId)!}";
			var dossierTemplateNo = $("#dossierTemplateNo").val();
			var partNo = $(this).attr("part-no");
			$("#uploadFileTemplateDialog").load("${ajax.customer_dossier_detail_filetemplate}&${portletNamespace}dossierPartNo="+partNo+"&${portletNamespace}dossierId="+dossierId+"&${portletNamespace}dossierTemplateNo="+dossierTemplateNo,function(result){
				$(this).modal("show");
			});
		});

		$(document).off("click",".dossier-component-profile");
		$(document).on("click",".dossier-component-profile",function(){
			var partNo = $(this).attr("data-partno");
			var dossierId = "${(dossierId)!}";
			var dossierTemplateId = "${(dossierTemplateId)!}";
			$("#profileDetail").load("${ajax.customer_dossier_component_profiles}&${portletNamespace}dossierPartNo="+partNo+"&${portletNamespace}dossierId="+dossierId+"&${portletNamespace}dossierTemplateId="+dossierTemplateId,function(result){

			});
		});

		$(document).off("click",".delete-dossier-file");
		$(document).on("click",".delete-dossier-file",function(){
			var dossierId  = "${dossierId}";
			var dataPartNo = $(this).attr("data-partno");
			try{
				$("#formPartNo"+dataPartNo).alpaca('get').setValue({});
			}catch (e){

			}

			var cf = confirm("Bạn có muốn xóa file toàn bộ file của thành phần này!");
			if(cf){
				if(dossierId && dataPartNo){
					$.ajax({
						url : "${api.server}/dossiers/"+dossierId+"/files",
						dataType : "json",
						type : "GET",
						headers : {"groupId": ${groupId}},
						success : function(result) {
							var data = result.data;
							if(data){
								for (var i = 0; i < data.length; i++) {
									if(dataPartNo === data[i].dossierPartNo){
										removeDossierFile(dossierId, data[i].referenceUid);

									}
								}
								$(".dossier-component-profile").filter("[data-partno="+dataPartNo+"]").html('<span class="number-in-circle" >0</span>');

								$(".dossier-component-profile").filter("[data-partno="+dataPartNo+"]").attr("data-number",0);
								notification.show({
									message: "Yêu cầu được thực hiện thành công"
								}, "success");

							}
							$("#validPart"+dataPartNo).val("0");
						},
						error : function(result) {
							notification.show({
								message: "Xẩy ra lỗi, vui lòng thử lại"
							}, "error");
						}
					});
				}
			}
		});

		$("#btn-view-extendguide").click(function(){
			if($("#extend-guide").attr("status")=="none"){
				$("#extend-guide").show();
				$("#extend-guide").attr("status","show");
			}else{
				$("#extend-guide").hide();
				$("#extend-guide").attr("status","none");
			}
		});

		var dataSourceDossiserFileTemplate;
		var dataSourceDossiserLog;

		dataSourceDossiserFileTemplate=new kendo.data.DataSource({
			transport:{
				read:function(options){
					$.ajax({
						url : "${api.server}/dossiers/${dossierId}/files",
						dataType : "json",
						type : "GET",
						headers : {"groupId": ${groupId}},
						data : {
							
						},
						success : function(result){
							var arrResult = fnGetPartype2(result.data);
							options.success(arrResult);
						},
						error : function(result){
							options.error(result);
						}
					});
				}
			},
			schema : {
				model : {
					id : "dossierFileId"
				}
			}
		});

		dataSourceDossiserLog = new kendo.data.DataSource({
			transport:{
				read:function(options){
					$.ajax({
						url : "${api.server}/dossierlogs/${dossierId}/logs",
						dataType : "json",
						type : "GET",
						headers : {"groupId": ${groupId}},
						data:{

						},
						success : function(result){
							var data = result.hasOwnProperty("data")?result.data:[];
							var mmmm = {
    "total": 27,
    "data": [
        {
            "dossierLogId": "8307",
            "payload": {
                "stepName": "Lưu hồ sơ",
                "files": [
                    {
                        "fileName": "Bản thông tin Xe cơ giới trong Thẩm định thiết kế",
                        "dossierFileId": "18106",
                        "createDate": "2017-12-20T16:06:43.000Z"
                    }
                ],
                "jobPosName": "",
                "stepInstruction": ""
            },
            "author": "Công ty Trường Hải business",
            "notificationType": "PROCESS_TYPE",
            "content": "",
            "createDate": "2017-12-20T16:07:05.000Z"
        },
        {
            "dossierLogId": "8306",
            "payload": {
                "jobposTitle": "64603: Create Dossier",
                "dossier": [
                    {
                        "referenceUid": "cfd2231c-02de-48c1-a089-8af22b48495e"
                    }
                ],
                "briefNote": ""
            },
            "author": "Công ty Trường Hải business",
            "notificationType": "Dossier00",
            "content": "On Dossiser Created",
            "createDate": "2017-12-20T16:06:43.000Z"
        },
        {
            "dossierLogId": "8311",
            "payload": {
                "stepName": "Gửi hồ sơ",
                "files": [],
                "jobPosName": "",
                "stepInstruction": ""
            },
            "author": "Test Test",
            "notificationType": "PROCESS_TYPE",
            "content": "",
            "createDate": "2017-12-20T16:08:26.000Z"
        },
        {
            "dossierLogId": "8319",
            "payload": {
                "jobposTitle": "64603: Request Addition Dossier",
                "dossier": [
                    {
                        "referenceUid": "cfd2231c-02de-48c1-a089-8af22b48495e"
                    }
                ],
                "briefNote": "Nhãn hiệu: <br>\nTên thương mại: "
            },
            "author": "Công ty Trường Hải business",
            "notificationType": "DOSSIER04",
            "content": "On Dossiser Additional",
            "createDate": "2017-12-20T16:09:34.000Z"
        },
        {
            "dossierLogId": "8317",
            "payload": {
                "jobposTitle": "64603: Request Addition Dossier",
                "dossier": [
                    {
                        "referenceUid": "cfd2231c-02de-48c1-a089-8af22b48495e"
                    }
                ],
                "briefNote": "Nhãn hiệu: <br>\nTên thương mại: "
            },
            "author": "Công ty Trường Hải business",
            "notificationType": "DOSSIER01",
            "content": "On Dossiser Additional",
            "createDate": "2017-12-20T16:09:25.000Z"
        },
        {
            "dossierLogId": "8318",
            "payload": {
                "jobposTitle": "64603: Request Addition Dossier",
                "dossier": [
                    {
                        "referenceUid": "cfd2231c-02de-48c1-a089-8af22b48495e"
                    }
                ],
                "briefNote": "Nhãn hiệu: <br>\nTên thương mại: "
            },
            "author": "Công ty Trường Hải business",
            "notificationType": "DOSSIER01",
            "content": "On Dossiser Additional",
            "createDate": "2017-12-20T16:09:34.000Z"
        },
        {
            "dossierLogId": "8321",
            "payload": {
                "jobposTitle": "64603: Request Addition Dossier",
                "dossier": [
                    {
                        "referenceUid": "cfd2231c-02de-48c1-a089-8af22b48495e"
                    }
                ],
                "briefNote": "Nhãn hiệu: <br>\nTên thương mại: "
            },
            "author": "Công ty Trường Hải business",
            "notificationType": "DOSSIER04",
            "content": "On Dossiser Additional",
            "createDate": "2017-12-20T16:09:54.000Z"
        },
        {
            "dossierLogId": "8322",
            "payload": {
                "jobposTitle": "64603: Request Addition Dossier",
                "dossier": [
                    {
                        "referenceUid": "cfd2231c-02de-48c1-a089-8af22b48495e"
                    }
                ],
                "briefNote": "Nhãn hiệu: <br>\nTên thương mại: "
            },
            "author": "Công ty Trường Hải business",
            "notificationType": "DOSSIER01",
            "content": "On Dossiser Additional",
            "createDate": "2017-12-20T16:09:58.000Z"
        },
        {
            "dossierLogId": "8320",
            "payload": {
                "jobposTitle": "64603: Request Addition Dossier",
                "dossier": [
                    {
                        "referenceUid": "cfd2231c-02de-48c1-a089-8af22b48495e"
                    }
                ],
                "briefNote": "Nhãn hiệu: <br>\nTên thương mại: "
            },
            "author": "Công ty Trường Hải business",
            "notificationType": "DOSSIER01",
            "content": "On Dossiser Additional",
            "createDate": "2017-12-20T16:09:54.000Z"
        },
        {
            "dossierLogId": "8323",
            "payload": {
                "jobposTitle": "64603: Request Addition Dossier",
                "dossier": [
                    {
                        "referenceUid": "cfd2231c-02de-48c1-a089-8af22b48495e"
                    }
                ],
                "briefNote": "Nhãn hiệu: <br>\nTên thương mại: "
            },
            "author": "Công ty Trường Hải business",
            "notificationType": "DOSSIER04",
            "content": "On Dossiser Additional",
            "createDate": "2017-12-20T16:09:58.000Z"
        },
        {
            "dossierLogId": "8324",
            "payload": {
                "jobposTitle": "64603: Request Addition Dossier",
                "dossier": [
                    {
                        "referenceUid": "cfd2231c-02de-48c1-a089-8af22b48495e"
                    }
                ],
                "briefNote": "Nhãn hiệu: <br>\nTên thương mại: "
            },
            "author": "Công ty Trường Hải business",
            "notificationType": "DOSSIER01",
            "content": "On Dossiser Additional",
            "createDate": "2017-12-20T16:10:10.000Z"
        },
        {
            "dossierLogId": "8325",
            "payload": {
                "jobposTitle": "64603: Request Addition Dossier",
                "dossier": [
                    {
                        "referenceUid": "cfd2231c-02de-48c1-a089-8af22b48495e"
                    }
                ],
                "briefNote": "Nhãn hiệu: <br>\nTên thương mại: "
            },
            "author": "Công ty Trường Hải business",
            "notificationType": "DOSSIER04",
            "content": "On Dossiser Additional",
            "createDate": "2017-12-20T16:10:10.000Z"
        },
        {
            "dossierLogId": "8316",
            "payload": {
                "stepName": "Chờ bổ sung hồ sơ trước tiếp nhận",
                "files": [],
                "jobPosName": "",
                "stepInstruction": ""
            },
            "author": "Test Test",
            "notificationType": "PROCESS_TYPE",
            "content": "",
            "createDate": "2017-12-20T16:09:25.000Z"
        },
        {
            "dossierLogId": "8331",
            "payload": {
                "jobposTitle": "64603: Request Addition Dossier",
                "dossier": [
                    {
                        "referenceUid": "cfd2231c-02de-48c1-a089-8af22b48495e"
                    }
                ],
                "briefNote": "Nhãn hiệu: <br>\nTên thương mại: "
            },
            "author": "Công ty Trường Hải business",
            "notificationType": "DOSSIER04",
            "content": "On Dossiser Additional",
            "createDate": "2017-12-20T16:11:09.000Z"
        },
        {
            "dossierLogId": "8329",
            "payload": {
                "jobposTitle": "64603: Request Addition Dossier",
                "dossier": [
                    {
                        "referenceUid": "cfd2231c-02de-48c1-a089-8af22b48495e"
                    }
                ],
                "briefNote": "Nhãn hiệu: <br>\nTên thương mại: "
            },
            "author": "Công ty Trường Hải business",
            "notificationType": "DOSSIER04",
            "content": "On Dossiser Additional",
            "createDate": "2017-12-20T16:10:40.000Z"
        },
        {
            "dossierLogId": "8330",
            "payload": {
                "jobposTitle": "64603: Request Addition Dossier",
                "dossier": [
                    {
                        "referenceUid": "cfd2231c-02de-48c1-a089-8af22b48495e"
                    }
                ],
                "briefNote": "Nhãn hiệu: <br>\nTên thương mại: "
            },
            "author": "Công ty Trường Hải business",
            "notificationType": "DOSSIER01",
            "content": "On Dossiser Additional",
            "createDate": "2017-12-20T16:11:09.000Z"
        },
        {
            "dossierLogId": "8326",
            "payload": {
                "jobposTitle": "64603: Request Addition Dossier",
                "dossier": [
                    {
                        "referenceUid": "cfd2231c-02de-48c1-a089-8af22b48495e"
                    }
                ],
                "briefNote": "Nhãn hiệu: <br>\nTên thương mại: "
            },
            "author": "Công ty Trường Hải business",
            "notificationType": "DOSSIER01",
            "content": "On Dossiser Additional",
            "createDate": "2017-12-20T16:10:37.000Z"
        },
        {
            "dossierLogId": "8327",
            "payload": {
                "jobposTitle": "64603: Request Addition Dossier",
                "dossier": [
                    {
                        "referenceUid": "cfd2231c-02de-48c1-a089-8af22b48495e"
                    }
                ],
                "briefNote": "Nhãn hiệu: <br>\nTên thương mại: "
            },
            "author": "Công ty Trường Hải business",
            "notificationType": "DOSSIER04",
            "content": "On Dossiser Additional",
            "createDate": "2017-12-20T16:10:37.000Z"
        },
        {
            "dossierLogId": "8328",
            "payload": {
                "jobposTitle": "64603: Request Addition Dossier",
                "dossier": [
                    {
                        "referenceUid": "cfd2231c-02de-48c1-a089-8af22b48495e"
                    }
                ],
                "briefNote": "Nhãn hiệu: <br>\nTên thương mại: "
            },
            "author": "Công ty Trường Hải business",
            "notificationType": "DOSSIER01",
            "content": "On Dossiser Additional",
            "createDate": "2017-12-20T16:10:40.000Z"
        },
        {
            "dossierLogId": "8338",
            "payload": {
                "jobposTitle": "64603: Request Addition Dossier",
                "dossier": [
                    {
                        "referenceUid": "cfd2231c-02de-48c1-a089-8af22b48495e"
                    }
                ],
                "briefNote": "Nhãn hiệu: <br>\nTên thương mại: "
            },
            "author": "Công ty Trường Hải business",
            "notificationType": "DOSSIER04",
            "content": "On Dossiser Additional",
            "createDate": "2017-12-20T16:11:16.000Z"
        },
        {
            "dossierLogId": "8337",
            "payload": {
                "jobposTitle": "64603: Request Addition Dossier",
                "dossier": [
                    {
                        "referenceUid": "cfd2231c-02de-48c1-a089-8af22b48495e"
                    }
                ],
                "briefNote": "Nhãn hiệu: <br>\nTên thương mại: "
            },
            "author": "Công ty Trường Hải business",
            "notificationType": "DOSSIER01",
            "content": "On Dossiser Additional",
            "createDate": "2017-12-20T16:11:16.000Z"
        },
        {
            "dossierLogId": "8340",
            "payload": {
                "jobposTitle": "64603: Request Addition Dossier",
                "dossier": [
                    {
                        "referenceUid": "cfd2231c-02de-48c1-a089-8af22b48495e"
                    }
                ],
                "briefNote": "Nhãn hiệu: <br>\nTên thương mại: "
            },
            "author": "Công ty Trường Hải business",
            "notificationType": "DOSSIER04",
            "content": "On Dossiser Additional",
            "createDate": "2017-12-20T16:11:25.000Z"
        },
        {
            "dossierLogId": "8339",
            "payload": {
                "jobposTitle": "64603: Request Addition Dossier",
                "dossier": [
                    {
                        "referenceUid": "cfd2231c-02de-48c1-a089-8af22b48495e"
                    }
                ],
                "briefNote": "Nhãn hiệu: <br>\nTên thương mại: "
            },
            "author": "Công ty Trường Hải business",
            "notificationType": "DOSSIER01",
            "content": "On Dossiser Additional",
            "createDate": "2017-12-20T16:11:25.000Z"
        },
        {
            "dossierLogId": "8341",
            "payload": {
                "stepName": "Bổ sung trước tiếp nhận",
                "files": [],
                "jobPosName": "",
                "stepInstruction": ""
            },
            "author": "Test Test",
            "notificationType": "PROCESS_TYPE",
            "content": "a",
            "createDate": "2017-12-20T16:11:25.000Z"
        },
        {
            "dossierLogId": "8342",
            "payload": {
                "jobposTitle": "64603: Create Dossier",
                "dossier": [
                    {
                        "referenceUid": "cfd2231c-02de-48c1-a089-8af22b48495e"
                    }
                ],
                "briefNote": "Nhãn hiệu: <br>\nTên thương mại: "
            },
            "author": "Công ty Trường Hải business",
            "notificationType": "DOSSIER04",
            "content": "On Dossiser Additional",
            "createDate": "2017-12-20T16:11:25.000Z"
        },
        {
            "dossierLogId": "8354",
            "payload": {
                "stepName": "tự động chuyển sang đang xử lý",
                "files": [],
                "jobPosName": "",
                "stepInstruction": ""
            },
            "author": "Test Test",
            "notificationType": "PROCESS_TYPE",
            "content": "",
            "createDate": "2017-12-20T16:14:25.000Z"
        },
        {
            "dossierLogId": "8514",
            "payload": {
                "stepName": "Nhận kết quả hồ sơ",
                "files": [],
                "jobPosName": "",
                "stepInstruction": ""
            },
            "author": "Test Test",
            "notificationType": "PROCESS_TYPE",
            "content": "",
            "createDate": "2017-12-20T17:13:16.000Z"
        }
    ]
};
							var arrLogsResult = fnGetLogs(mmmm.data);
							options.success(arrLogsResult);
						},
						error : function(result){
							options.error(result);
						}
					});
				}
			},
			schema:{
				data:"data",
				total:"total",
				model:{
					id:"dossierLogId"
				}
			}
		});

		$("#listViewDossiserFileTemplate").kendoListView({
			dataSource:dataSourceDossiserFileTemplate,
			template:kendo.template($("#templateDossiserFileTemplate").html()),
			autoBind: true
		});

		var fnGetPartype2 = function(arrFile){
			var arrResult = new Array();
			if(arrFile){
				for (var i = 0; i < arrFile.length; i++) {
					if(arrFile[i].dossierPartType == 2){
						arrResult.push(arrFile[i]);
					}
				}
			}
			
			return arrResult;
		}

		var fnGetLogs = function(arrLogs){
			
			var arrLogsResult = new Array();
			var count = 0;
			var result = {};
			if(arrLogs){
				for (var i = 0; i < arrLogs.length; i++) {
					if(arrLogs[i].notificationType === 'PROCESS_TYPE'){
						arrLogsResult.push(arrLogs[i]);
						count++;
					}
				}
			}
			result["data"] = arrLogsResult;
			result["total"] = count;
			return result;
		}



		var indexDossiserLog = 0;
		$("#listViewDossiserLog").kendoListView({
			dataSource:dataSourceDossiserLog,
			autoBind: true,
			dataBound : function(){
				indexDossiserLog = 0;
			},
			template: function(data){

				indexDossiserLog ++;

				data.itemIndex = indexDossiserLog;

				return kendo.template($("#templateDossiserLog").html())(data);

			},
		});

		var dataSourceDossierTemplate = new kendo.data.DataSource({
			transport :{
				read : function(options){
					if(options.data.dossierTemplateNo){
						$.ajax({
							url : "${api.server}/dossiertemplates/"+options.data.dossierTemplateNo,
							dataType : "json",
							type : "GET",
							headers : {"groupId": ${groupId}},
							data : {

							},
							success : function(result){
								options.success(result.dossierParts);
								$("#dossierTemplateNo").val(result.templateNo);
							},
							error : function(result){
								options.error(result);
							}
						});
					}
				}
			},
			schema : {
				model : {
					id : "partNo"
				}
			}
		});

		var indexDossiserPart =0 ;
		$("#lsDossierTemplPart").kendoListView({
			dataSource : dataSourceDossierTemplate,
			autoBind : false,
			change : function(){

			},
			template : function(data){

				indexDossiserPart ++;

				data.itemIndex = indexDossiserPart;

				return kendo.template($("#templateDossierPart").html())(data);

			},
			dataBound : function(){
				indexDossiserPart = 0;

			//kiem tra dossier status, neu status thuoc new thi cho phep upoad hoac sua file
			fnCheckStatusAndHideUpload($("#dossierStatus").val());

			//gen number file cho icon thanh phan ho so
			
			var	arrFile = funDossierFile(${dossierId});
			funGenNumberFile(arrFile);
		}
	});

		var printDetailDossier = function(dossierId){
			if(dossierId){
				$.ajax({
					url : "${api.server}/dossiers/"+dossierId,
					dataType : "json",
					type : "GET",
					headers : {"groupId": ${groupId}},
					success : function(result){
						
						dataSourceDossierTemplate.read({
							dossierTemplateNo : result.dossierTemplateNo
						});

						var payment = fnLoadPayment(result.dossierId);
						if(!payment){
							$("#paymentDossierContent").html("");
						}

						var viewModel = kendo.observable({

							dossierId : result.dossierId,
							serviceName : result.serviceName,
							govAgencyName : result.govAgencyName,

							actionNote1 : function(e){

								if(result.actionNote){
									$("#actionNote1").html('<i class="fa fa-bolt" aria-hidden="true" style="color: red;"></i> <span>'+result.actionNote+'</span>');
								}else {
									$("#actionNote1").remove();
								}

								return;
							},

							applicantName : result.applicantName,
							address : result.address,
							briefNote : function(e){
								if(result.briefNote){
									return result.briefNote;
								}else {
									return "";
								}
							},
							cityCode : result.cityCode,
							districtCode : result.districtCode,
							wardCode : result.wardCode,
							contactTelNo : result.contactTelNo,
							contactEmail : result.contactEmail,
							dossierNo : result.dossierNo,
							dossierStatusText : result.dossierStatusText,
							stepInstruction : result.stepInstruction,
							dossierStatus : result.dossierStatus,
							postalAddress : result.postalAddress,
							postalCityName : result.postalCityName,
							postalTelNo : result.postalTelNo,
							dossierTemplateNo : result.dossierTemplateNo,
							viaPostal : function(e){
								
								if(result.viaPostal === 0){
									$("#postal").remove();
								}
							},
							submissionNote : function(e){
								if(result.submissionNote){
									$("#guideDossier").show();
									return result.submissionNote;
								}else {
									$("#guideDossier").hide();
									return "";
								}
							},
							paymentDossier : payment,
							paymentFee : function(e){
								
								if(this.get('paymentDossier').paymentFee){
									return this.get('paymentDossier').paymentFee;
								}
								return "";
							},
							paymentAmount : function(e){
								if(this.get('paymentDossier').paymentAmount){
									return this.get('paymentDossier').paymentAmount;
								}
								return "";
							},
							paymentGovAgencyName : function(e){
								if(this.get('paymentDossier').govAgencyName){
									return this.get('paymentDossier').govAgencyName;
								}
								return "";
							},
							paymentBankInfo : function(e){
								if(this.get('paymentDossier').bankInfo){
									return this.get('paymentDossier').bankInfo;
								}
								return "";
							},
							paymentStatus : function(e){
								if(this.get('paymentDossier').paymentStatus){
									return this.get('paymentDossier').paymentStatus;
								}
								return "";
							},
							paymentApproveDatetime : function(e){
								if(this.get('paymentDossier').approveDatetime){
									return this.get('paymentDossier').approveDatetime;
								}
								return "";
							},
							paymentConfirmNote : function(e){
								if(this.get('paymentDossier').confirmNote){
									return this.get('paymentDossier').confirmNote;
								}
								return "";
							},
							isPay : function(){
								if(this.get('paymentDossier').paymentStatus !== 2){
									$("#unpaid").show();
									$("#alreadyPaid").hide();
								}
							},
							submitting : function(){
								if(result.submitting){
									$("#btn-submit-dossier").hide();
								}
							}

						/*dossierTemplateNo : function(e){
							dataSourceDossierTemplate.read({
								dossierPart : 201 //result.dossierTemplateNo
							});	
						}*/

					});

						kendo.bind($("#detailDossier"), viewModel);
					},
					error : function(result){
						$("#paymentDossierContent").html("");
					}

				});
			}
		}

		printDetailDossier(${dossierId});

		var funUploadFile = function(file, partNo , dossierTemplateNo , fileTemplateNo){
			var data = new FormData();

			data.append( 'displayName', "");
			data.append( 'file', $(file)[0].files[0]);
			data.append('dossierPartNo', partNo);
			data.append('referenceUid', "");
			data.append('dossierTemplateNo', dossierTemplateNo);
			data.append('fileTemplateNo', fileTemplateNo);
			data.append('formData', "");
			data.append('isSync', "");
			data.append('fileType', "");

			$.ajax({
				type : 'POST', 
				url  : '${api.server}/dossiers/${dossierId}/files', 
				data : data,
				headers: {"groupId": ${groupId}},
				processData: false,
				contentType: false,
				cache: false,
				async : false,
				success :  function(result){ 
					var fileLength = $(file)[0].files.length;

					var currentFileNumber = $(".dossier-component-profile").filter("[data-partno="+partNo+"]").attr("data-number");

					var totalFile = fileLength + parseInt(currentFileNumber, 0);

					$(".dossier-component-profile").filter("[data-partno="+partNo+"]").html('<span class="number-in-circle" >'+totalFile+'</span>');

					$(".dossier-component-profile").filter("[data-partno="+partNo+"]").attr("data-number",totalFile);
					$("#uploadFileTemplateDialog").modal("hide");

					notification.show({
						message: "Yêu cầu được thực hiện thành công"
					}, "success");

				},
				error:function(result){
					notification.show({
						message: "Thêm không thành công do số biểu mẫu bị trùng."
					}, "error");
				}
			});
			
		}

		var fnCheckStatusAndHideUpload = function(dossierStatus){
			if(dossierStatus !== "New" || dossierStatus !== "Receiving"){
				$(".uploadfile-form-repository").remove();
				$(".lbl-dossier-flie").remove();
				$(".delete-dossier-file").remove();
			}
		}

		var fnLoadPayment = function(dossierId){

			var resultModel = null;
			if(dossierId){
				$.ajax({
					url : "${api.server}/dossiers/"+dossierId+"/payments",
					dataType : "json",
					type : "GET",
					headers : {"groupId": ${groupId}},
					async : false,
					data : {
						paymentStatus : 0
					},
					success : function(result){
						if(result.data){
							resultModel = result.data[0];
						}
					},
					error :  function(result){
						$("#paymentDossier").hide();
					}

				});
			}

			return resultModel;
		}

		var funDossierFile = function(dossierId){
			var arrFile = new Array();
			if(dossierId){
				$.ajax({
					url : "${api.server}/dossiers/"+dossierId+"/files",
					dataType : "json",
					type : "GET",
					headers : {"groupId": ${groupId}},
					async : false,
					success : function(result){
						if(result.data){
							arrFile = result.data;
						}else {
							arrFile = [];
						}

					},
					error : function(result){

					}
				});
			}
			$( "body" ).data( "dossierFiles", arrFile );
			return arrFile;
		}


		var funGenNumberFile = function(arrCount){
			
			$(".dossier-component-profile").each(function(index){
				var partNo = $(this).attr("data-partno");
				var found = $.grep(arrCount, function(v) {
					return v.dossierPartNo === partNo;
				});

				$(this).attr("data-number",found.length);
				$(this).html('<span class="number-in-circle" >'+found.length+'</span>');
			});
		}
	});

var getReferentUidFile = function(dossierId,dossierPartNo){
	var dossierFile;
	if(dossierId){
		$.ajax({
			type : 'GET', 
			dataType : "json",
			url  : '${api.server}/dossiers/${dossierId}/files', 
			headers: {"groupId": ${groupId}},
			async : false,
			success :  function(result){ 
				if(result.data){
					for (var i = 0; i < result.data.length; i++) {
						if(result.data[i].eForm){
							if(dossierPartNo == result.data[i].dossierPartNo){
								dossierFile = result.data[i];
								return ;
							}

						}
					}
				}

			},
			error:function(result){

			}
		});
	}

	return dossierFile;
}

var fnGetFormData = function(dossierId,referentUid){
	var value = null;
	if(dossierId && referentUid){
		$.ajax({
			url : "${api.server}/dossiers/"+dossierId+"/files/"+referentUid+"/formdata",
			type : "GET",
			dataType : "json",
			async : false,
			success : function(result){
				value = result;

			},
			error : function(result){

			}

		});
	}

	return value;
}

$(document).on("click","#btn-submit-dossier",function(event){
	var data = $('#dossierFormSubmiting').serialize();
	$.ajax({
		type : 'GET', 
		url  : '${api.server}/dossiers/${dossierId}/submitting', 
		data : data,
		headers : 
		{
			"groupId": ${groupId},
			Accept : "application/json"

		},
		success :  function(result){                       

		},
		error : function(result){

		}
	});
	
});


var fnSaveForm = function(id, value){
	var current = $("#btn-save-formalpaca"+id);
	var referentUid = current.attr("referenceUid");
	
	if(referentUid){
		$.ajax({
			url : "${api.server}/dossiers/${dossierId}/files/"+referentUid+"/formdata",
			dataType : "json",
			type : "PUT",
			headers: {
				"groupId": ${groupId},
				Accept : "application/json"
			},
			data : {
				formdata: JSON.stringify(value)
			},
			success : function(result){
				notification.show({
					message: "Yêu cầu được thực hiện thành công!"
				}, "success");
				
				$("#validPart"+id).val("1");
			},
			error : function(result){
				notification.show({
					message: "Thực hiện không thành công, xin vui lòng thử lại!"
				}, "error");
			}
		});
	}
}

$(document).off("click",".saveFormAlpaca");
$(document).on("click",".saveFormAlpaca",function(event){
	var id = $(this).attr("data-pk");
	var referentUidFile = $(this).attr("referenceUid");

	var formType = $("#formPartNo"+id+" .formType").val();
	var value ;

	if(formType !== "dklr"){
		value = $("#formPartNo"+id).alpaca('get').getValue();


		var errorMessage = '';
		$("#formPartNo"+id+' div[class*="has-error"] > label').each(function( index ) {

			errorMessage = "notValid";

		});

		if(errorMessage === '' && referentUidFile){
			
			$.ajax({
				url : "${api.server}/dossiers/${dossierId}/files/"+referentUidFile+"/formdata",
				dataType : "json",
				type : "PUT",
				headers: {
					"groupId": ${groupId},
					Accept : "application/json"
				},
				data : {
					formdata: JSON.stringify(value)
				},
				success : function(result){
					notification.show({
						message: "Yêu cầu được thực hiện thành công!"
					}, "success");
					
					$("#validPart"+id).val("1");
				},
				error : function(result){
					notification.show({
						message: "Thực hiện không thành công, xin vui lòng thử lại!"
					}, "error");
				}
			});

		}else {
			notification.show({
				message: "Vui lòng kiểm tra lại các thông tin bắt buộc trước khi ghi lại!"
			}, "error");
		}
	}

});
</script>