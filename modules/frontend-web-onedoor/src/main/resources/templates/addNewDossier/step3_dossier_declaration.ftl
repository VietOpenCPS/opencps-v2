<#if (Request)??>
<#include "../init.ftl">
</#if>
<div class="P0">
	<div class="row">
		<div class="row-header align-middle-lg">
			<div class="background-triangle-big">
				Khai báo hồ sơ
			</div>
			<div class="text-light-gray MLA">
				<button class="btn btn-next" onclick="saveDossier()"><i class="fa fa-sign-in fa-lg" aria-hidden="true"></i> Tiếp theo</button>
			</div>
		</div>
	</div>
	<div class="row heading-item">
		<div class="block-arrow">I</div><b>THÔNG TIN CHỦ HỒ SƠ</b>
	</div>
	<div class="row heading-item">
		<div class="col-xs-12 form-horizontal info-content" id="info-content">
			
		</div>

	</div>
	<div class="row heading-item">
		<div class="block-arrow">II</div>
		<b>THÀNH PHẦN HỒ SƠ</b>
		<i class="pull-right MR10 text-light-gray">Những thành phần hồ sơ có dấu (<i class="red">*</i>) là thành phần bắt buộc</i>
	</div>
	<div id="dossier-files"></div>
	<div class="row heading-item">
		<div class="block-arrow">III</div>
		<b>DỊCH VỤ CHUYỂN PHÁT KẾT QUẢ</b>
	</div>
	<div class="row heading-item">
		<div class="col-xs-6">
			<div class="col-xs-3">
				<input type="checkbox" onchange="changeViaPostStt(this)" name="viaPost" id="viaPost" class="pull-right">
			</div>
			<label class="col-xs-9">Đăng ký kết quả tại nhà</label>
		</div>
		<div class="col-xs-6">
			<label class="col-xs-4 text-right">Chọn dịch vụ</label>
			<div class="col-xs-8">
				<select class="choose-paper form-control" disabled="disabled" id="chooserPostService">
					<option></option>
				</select>
			</div>
		</div>
	</div>
	<div class="row receiver-heading hidden" id="post-info">
		<label class="col-xs-4 text-left">Thông tin người nhận</label>
		<b class="col-xs-2 pull-right">Giống người nộp <input type="radio" checked="checked" name="likeAplicant" onclick="isLikeAplicant(true)" value="true" />
		</b>
		<b class="col-xs-2 pull-right">Khác <input type="radio" onclick="isLikeAplicant(false)" name="likeAplicant" value="false" /></b>
	</div>
	<div class="row heading-item hidden" id="post-detail">
		<div class="col-xs-12 form-horizontal info-content" id="info-content">
			<div class="row MB10">
				<label class="control-label col-xs-2">Họ và tên</label>
				<div class="col-xs-4">
					<input type="text" id="postalName" class="form-control">
				</div>
				<label class="control-label col-xs-2">Điện thoại</label>
				<div class="col-xs-4">
					<input type="text" id="postalTelNo" class="form-control">
				</div>
			</div>
			<div class="row MB10">
				<label class="control-label col-xs-2">Địa chỉ trả kết quả</label>
				<div class="col-xs-10">
					<input type="text" id="postalAddress" class="form-control">
				</div>
			</div>
			<div class="row MB10">
				<label class="control-label col-xs-2">Mã bưu cục</label>
				<div class="col-xs-4">
					<select name="postalCityCode" id="postalCityCode" class="form-control">
						<option value="" selected>Tỉnh/ Thành phố</option>
					</select>
				</div>
			</div>
		</div>
	</div>
</div>
<!--Thông tin người nộp-->
<script type="text/x-kendo-tmpl" id="info-content-tmpl">
	<div class="row">
		<label class="control-label col-xs-3">Họ và tên</label>
		<p class="col-xs-9">#:applicantName#
			<a href="\#/buoc2" class="edit-info"><i class="fa fa-pencil"></i></a>
		</p>
	</div>
	<div class="row">
		<label class="control-label col-xs-3">Số CMND/ Hộ chiếu/ Mã số thuế</label>
		<p class="col-xs-9" >#:applicantIdNo#
			<a href="\#/buoc2" class="edit-info"><i class="fa fa-pencil"></i></a>
		</p>
	</div>
	<div class="row">
		<label class="control-label col-xs-3">Điện thoại</label>
		<p class="col-xs-9">#:contactTelNo#
			<a href="\#/buoc2" class="edit-info"><i class="fa fa-pencil"></i></a>
		</p>
	</div>
	<div class="row">
		<label class="control-label col-xs-3">Địa chỉ email</label>
		<p class="col-xs-9">#:contactEmail#
			<a href="\#/buoc2" class="edit-info"><i class="fa fa-pencil"></i></a>
		</p>
	</div>
	<div class="row">
		<label class="control-label col-xs-3">Địa chỉ</label>
		<p class="col-xs-9">#:address#
			<a href="\#/buoc2" class="edit-info"><i class="fa fa-pencil"></i></a>
		</p>
	</div>
</script>
<!--Thành phần hồ sơ-->
<script type="text/x-kendo-tmpl" id="dossier-files-tmpl">
	<div class="row part-border row-flex">
		<div class="col-xs-8"><b>#=itemIndex#.</b> #=partName# #if(required){#
			<i class="red">*</i>
			<input type="hidden" id="validPart#:partNo#" name="validPart#:partNo#" data-pk="#:partNo#" class="validPart" value="0">
			#}#
		</div>

		<div class="col-xs-4 part-action row-flex">
			<label class="col-xs-2 bg-grey"><input type="checkbox" name="" #if(required){# checked="checked" #}# id="hasPart#:partNo#"/>
			</label>
			<div class="col-xs-5" style="padding: 5px">
				<select class="choose-paper">
					<option value="">Chọn loại giấy</option>
					<option value="0">Ảnh chụp</option>
					<option value="1">Bản sao (công chứng)</option>
					<option value="2">Bản gốc</option>
				</select>
			</div>
			<div class="col-xs-5">
				<label class="MB0 ML10 hover-pointer" for="file#:partNo#" title="Tải file lên" >
					<i class="fa fa-upload text-light-blue"></i>
				</label>

				<input type='file' id="file#:partNo#" name="file#:partNo#" class="hidden dossier-file" #if(multiple){# multiple #}# part-no="#:partNo#" file-template-no="#:fileTemplateNo#" hasform="#if(hasForm){# true #}#" onchange="upLoadFile(this)">

				<a href="javascript:;" class="dossier-component-profile" data-toggle="tooltip" data-placement="top" title="Số tệp tin" data-partno="#:partNo#" data-number="#if(hasForm){# 1 #}else {# 0 #}#">
					<span class="number-in-circle" >#if(hasForm){# 1 #}else {# 0 #}#</span>
				</a>

				<a href="javascript:;" class="text-light-gray delete-dossier-file" data-toggle="tooltip" data-placement="top" title="Xóa" data-partno="#:partNo#" onclick="deleteDossierFile(this)">
					<i class="fa fa-trash-o" aria-hidden="true"></i> Xóa
				</a>
			</div>
		</div>
	</div>
	#
	if(hasForm){
	var dossierFile =  getReferentUidFile(dossierObject.dossierId,partNo);
	if(dossierFile){
	#

	<div class="row part-border">
		<div class="col-xs-12 col-sm-12 text-right">
			<button id="btn-save-formalpaca#:partNo#" class="btn btn-active MB10 MT10 MR20 saveForm"  onclick="saveFormAlpaca(this)" 
			type="button" data-pk="#:partNo#" referenceUid="#:dossierFile.referenceUid#" >Ghi lại</button>
			<input type="hidden" name="" id="dossierFileId#:partNo#" value="#:dossierFile.dossierFileId#">
		</div>
		<div class="col-sm-12" #if(dossierFile.referenceUid){# style="height:450px;width:100%;overflow:auto;" #}#>
			<form id="formPartNo#:partNo#">

			</form>
		</div>
	</div>
	#
		$.ajax({
			url : "${api.server}/dossiers/"+dossierObject.dossierId+"/files/"+dossierFile.referenceUid+"/formscript",
			dataType : "text",
			type : "GET",
			headers : {"groupId": ${groupId}},
			success : function(result){
				$("\\#formPartNo"+partNo).empty();

				var alpaca = eval("(" + result + ")");
				var formdata = fnGetFormData(dossierObject.dossierId,dossierFile.referenceUid);
				if(formdata){
					$("\\#validPart"+partNo).val("1");
				}
				alpaca.data = formdata; 

				$("\\#formPartNo"+partNo).alpaca(alpaca);
			},
			error : function(result){

			}
		});
	}}#

</script>
<script type="text/javascript">
	$(function() {
		var model = kendo.observable(dossierObject);
		var view = new kendo.View("info-content-tmpl", {model: model, evalTemplate: true});
		view.render($("#info-content"));

		getDossierPart();

		renderDictItems();

		isLikeAplicant(true);
	})

	function getDossierPart()
	{
		var dossierPartDatasource = new kendo.data.DataSource({
			transport:{
				read: function (options) {
					$.ajax({
						type : 'GET', 
						dataType : "json",
						url  : '${api.server}/dossiertemplates/'+dossierObject.dossierTemplateNo,
						headers: {"groupId": ${groupId}},
						success :  function(result){ 
							options.success(result.dossierParts);
						}
					})
				}
			}
		})
		var indexProcess = 0;
		$("#dossier-files").kendoListView({
			dataSource : dossierPartDatasource,
			template: function(data){
				indexProcess ++;
				data.itemIndex = indexProcess;
				return kendo.template($("#dossier-files-tmpl").html())(data);
			}
		});
		
	}

	function renderDictItems()
	{
		$.ajax({
			url : "${api.server}/dictcollections/VNPOST_CODE/dictitems?parent=0",
			dataType : "json",
			headers: {"groupId": 55217},
			type : "GET",
			success : function(result){
				for (var i = 0; i < result.data.length; i++) {
					var option = $("<option/>");
					option.val(result.data[i].itemCode);
					option.text(result.data[i].itemName);
					$("#postalCityCode").append(option);
				}
			},
		});
	}

	function getReferentUidFile(dossierId,dossierPartNo){
		var dossierFile;
		if(dossierId){
			$.ajax({
				type : 'GET', 
				dataType : "json",
				url  : '${api.server}/dossiers/'+dossierId+'/files', 
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
				}
			});
		}
		return value;
	};
	function saveFormAlpaca(thisBtn){
		var id = $(thisBtn).attr("data-pk");
		var referentUidFile = $(thisBtn).attr("referenceUid");

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
					url : "${api.server}/dossiers/"+dossierObject.dossierId+"/files/"+referentUidFile+"/formdata",
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
	};

	function upLoadFile(fileInput){
		var partNo = $(fileInput).attr("part-no");
		var fileTemplateNo = $(this).attr("file-template-no");
		var hasform = $(fileInput).attr("hasform");
		funUploadFile($(fileInput),partNo,dossierObject.dossierTemplateNo,fileTemplateNo,hasform);
	};

	function funUploadFile(file, partNo , dossierTemplateNo , fileTemplateNo, hasForm){
		var data = new FormData();
		data.append( 'displayName', $(file)[0].files[0].name);
		data.append( 'file', $(file)[0].files[0]);
		data.append('dossierPartNo', partNo);
		data.append('referenceUid', "");
		data.append('dossierTemplateNo', dossierTemplateNo);
		data.append('fileTemplateNo', fileTemplateNo);
		data.append('formData', "");
		data.append('fileType', "");
		data.append('isSync', "true");

		$.ajax({
			type : 'POST', 
			url  : '${api.server}/dossiers/'+dossierObject.dossierId+'/files', 
			data : data,
			headers: {"groupId": ${groupId}},
			processData: false,
			contentType: false,
			cache: false,
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

				if(!hasForm){
					$("#validPart"+partNo).val("1");
				}


			},
			error:function(result){
				notification.show({
					message: "Thực hiện không thành công, xin vui lòng thử lại"
				}, "error");
			}
		});
	}

	function deleteDossierFile(btnDelete){

		var dossierId  = dossierObject.dossierId;
		var dataPartNo = $(btnDelete).attr("data-partno");
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
	};

	function removeDossierFile(dossierId, fileId){
		$.ajax({
			url : "${api.server}/dossiers/"+dossierId+"/files/"+fileId,
			dataType : "json",
			type : "DELETE",
			headers : {"groupId": ${groupId}},
		});
	}

	function checkValidTemplate(){
		var valid = true;
		try {

			$(".validPart").each(function(index){
				var hasPart = $('#hasPart'+ $(this).attr("data-pk")).prop("checked");
				console.log(hasPart);
				if(hasPart)
				{
					if($(this).val() === "0"){
					valid = false;
					}
				}
			});

		}catch(e){
			valid = false;
		}

		return valid;
	}
	function saveDossier(){
		var validator = $("#portalDetail").kendoValidator().data("kendoValidator");
		var validateDossierTemplate = checkValidTemplate();
		if(dossierObject.viaPost === 2)
		{
			dossierObject.postalAddress = $("#postalAddress").val();
			dossierObject.contactName = $("#postalName").val();
			dossierObject.postalTelNo = $("#postalTelNo").val();
			dossierObject.postalCityCode = $("#postalCityCode").val();
		}
		if(validateDossierTemplate ){
			addDossierRouter.navigate("/buoc4");
		}else {
			notification.show({
				message: "Vui lòng kiểm tra lại các thông tin bắt buộc trước khi lưu!"
			}, "error");
		}
	}

	function changeViaPostStt(viaPost)
	{
		if($("#viaPost").prop('checked'))
		{
			$("#chooserPostService").removeAttr('disabled');
			$("#post-info").removeClass('hidden');
			$("#post-detail").removeClass('hidden');
			dossierObject.viaPost = 2;
		}
		else
		{
			$("#chooserPostService").attr('disabled','disabled');
			$("#post-info").addClass('hidden');
			$("#post-detail").addClass('hidden');
			dossierObject.viaPost = 1;
		}
	}	

	function isLikeAplicant(isLike){
		if (isLike)
		{
			$("#postalName").val(dossierObject.applicantName);
			$("#postalTelNo").val(dossierObject.contactTelNo);
			$("#postalAddress").val(dossierObject.address);
		} else {
			$("#postalName").val("");
			$("#postalTelNo").val("");
			$("#postalAddress").val("");
		}
	}
</script>