<#if (Request)??>
<#include "../init.ftl">
</#if>
<div class="P0">
	<div class="row">
		<div class="row-header align-middle-lg">
			<div class="background-triangle-big">
				Thông tin người nộp
			</div>
			<div class="text-light-gray MLA">
				<button class="btn btn-next" onclick="createDossier()"><i class="fa fa-sign-in fa-lg" aria-hidden="true"></i> Tiếp theo</button>
			</div>
		</div>
	</div>
	<form class="form-horizontal MT20 col-xs-offset-1" id="dossierForm" enctype="application/json">
		<div class="form-group col-xs-10">
			<label class="control-label col-xs-3" for="applicantName">Tên đầy đủ</label>
			<div class="col-xs-9">
				<input type="text" class="form-control" id="applicantName" name="applicantName" placeholder="Nhập họ tên">
			</div>
		</div>
		<div class="form-group col-xs-10">
			<label class="control-label col-xs-3" for="applicantIdNo">Số CMND/ Hộ chiếu/ Mã số thuế</label>
			<div class="col-xs-9"> 
				<input type="text" class="form-control" id="applicantIdNo" name="applicantIdNo" placeholder="Nhập số CMND/Hộ chiếu/Mã số thuế">
			</div>
		</div>
		<div class="form-group col-xs-10">
			<label class="control-label col-xs-3" for="contactTelNo">Số điện thoại</label>
			<div class="col-xs-9"> 
				<input type="text" class="form-control" id="contactTelNo" name="contactTelNo" placeholder="Nhập số điện thoại">
			</div>
		</div>
		<div class="form-group col-xs-10">
			<label class="control-label col-xs-3" for="contactEmail">Email</label>
			<div class="col-xs-9"> 
				<input type="text" class="form-control" id="contactEmail" name="contactEmail" placeholder="Nhập địa chỉ email">
			</div>
		</div>
		<div class="form-group col-xs-10">
			<label class="control-label col-xs-3" for="address">Địa chỉ liên hệ</label>
			<div class="col-xs-9"> 
				<input type="text" class="form-control" id="address" name="address" placeholder="Nhập thông tin đầy đủ số nhà, tên ngõ, tên đường">
			</div>
		</div>
		<div class="form-group col-xs-10">
			<div class="col-xs-9 col-xs-offset-3">
				<div class="col-xs-4 PL0"> 
					<select class="form-control" id="cityCode" onchange="renderDictItems($('#districtCode'),$(this).val())" name="cityCode"> 
						<option value="" selected>Tỉnh/ TP</option>
					</select>
				</div>
				<div class="col-xs-4"> 
					<select class="form-control" id="districtCode" onchange="renderDictItems($('#wardCode'),$(this).val())" name="districtCode"> 
						<option value="" selected>Quận/ Huyện</option>
					</select>
				</div>
				<div class="col-xs-4 PR0"> 
					<select class="form-control" id="wardCode" name="wardCode"> 
						<option value="" selected>Xã/ Phường</option>
					</select>
				</div>
			</div>
		</div>
	</form>					
</div>

<script type="text/javascript">
	$(function(){
		renderDictItems($("#cityCode"),0);

		if(dossierObject != null)
		{
			$("#applicantName").val(dossierObject.applicantName);
			$("#applicantIdNo").val(dossierObject.applicantIdNo);
			$("#contactTelNo").val(dossierObject.contactTelNo);
			$("#contactEmail").val(dossierObject.contactEmail);
			$("#address").val(dossierObject.address);
			console.log("hahahaha");
			if(dossierObject.cityCode != null)
			{
				$("#cityCode").val(dossierObject.cityCode).change();
			}
			if(dossierObject.districtCode != null)
			{
				$("#districtCode").val(dossierObject.districtCode).change();
			}
			if(dossierObject.districtCode != null){
				$("#wardCode").val(dossierObject.wardCode);
			}
		}
	})
	function checkApplicantId(id){
		if(id != "")
		{
			$.ajax({
				url : "${api.server}/dictcollections/ADMINISTRATIVE_REGION/dictitems?parent="+parentCode,
				dataType : "json",
				type : "GET",
				success : function(result){
					for (var i = 0; i < result.data.length; i++) {
						var option = $("<option/>");
						option.val(result.data[i].itemCode);
						option.text(result.data[i].itemName);
						selectElement.append(option);
					}
				},
			});
		}
	}
	function renderDictItems(selectElement,parentCode)
	{
		if(parentCode !== "")
		{
			$.ajax({
				url : "${api.server}/dictcollections/ADMINISTRATIVE_REGION/dictitems?parent="+parentCode,
				dataType : "json",
				type : "GET",
				success : function(result){
					for (var i = 0; i < result.data.length; i++) {
						var option = $("<option/>");
						option.val(result.data[i].itemCode);
						option.text(result.data[i].itemName);
						selectElement.append(option);
					}
				},
			});
		} else {
			selectElement.children('option[value!=""]').remove();
		}
	}
	function createDossier(){
		var dataArray = $("#dossierForm").serializeArray();
		var dataObject = {};
		dataObject = dossierObject;
		$.each(dataArray, function () {
			if(this.value != "")
			{
				dataObject[this.name] = this.value;
			}
		});

		dataObject.referenceUid = "";
		dataObject.applicantIdType = "1";
		dataObject.applicantIdDate = "01/01/2017 00:00:00";
		$.ajax({
			url : "${api.server}/dossiers",
			dataType : "json",
			type : "POST",
			data : dataObject,
			headers : {"groupId": ${groupId}},
			success : function(result){
				dossierObject = result;
				addDossierRouter.navigate("/buoc3");
			}
		});
	}
</script>

