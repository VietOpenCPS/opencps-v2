<#if (Request)??>
	<#include "init.ftl">
</#if>

<div class="modal-dialog">
	<!-- Modal content-->
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">&times;</button>
			<h4 class="modal-title">Thông tin hồ sơ</h4>
		</div>
		<div class="modal-body">
			<div class="row">
				<div class="col-sm-12">
					<div class="form-group">
						<font class="control-label">Mã hồ sơ : 
						</font> 
						<input class="k-textbox" style="width: 100%;" id="dosierId" name="dosierId" readonly value="" />
					</div> 
				</div>
				<div class="col-sm-12">
					<div class="form-group">
						<font class="control-label">Chủ hồ sơ : 
							<span class="icon-asterisk text-warning"> 
								<span class="hide-accessible">*</span> 
							</span>
						</font> 
						<input class="k-textbox" style="width: 100%;" id="contactName" name="contactName" 
						required validationMessage="" value=""/>
						<span data-for='contactName' class='k-invalid-msg'></span>
					</div> 
				</div>
				<div class="col-sm-12">
					<div class="form-group">
						<font class="control-label">Mã định danh : 
						</font> 
						<input class="k-textbox" style="width: 100%;" id="referenceUid" name="referenceUid" value=""/>
					</div> 
				</div>
				<div class="col-sm-12">
					<div class="form-group">
						<font class="control-label">Địa chỉ : 
							<span class="icon-asterisk text-warning"> 
								<span class="hide-accessible">*</span> 
							</span>
						</font> 
						<input class="k-textbox" style="width: 100%;" id="address" name="address" 
						required validationMessage="" value="" />
						<span data-for='address' class='k-invalid-msg'></span>
					</div> 
				</div>
				<div class="col-sm-12">
					<div class="form-group">
						<font class="control-label">Tỉnh/Thành phố : 
						</font> 
						<input class="k-textbox" style="width: 100%;" id="cityName" name="cityName" value=""/>
					</div> 
				</div>
				<div class="col-sm-12">
					<div class="form-group">
						<font class="control-label">Huyện/Quận : 
						</font> 
						<input class="k-textbox" style="width: 100%;" id="districtName" name="districtName" value=""/>
					</div> 
				</div>
				<div class="col-sm-12">
					<div class="form-group">
						<font class="control-label">Xã/Phường : 
						</font> 
						<input class="k-textbox" style="width: 100%;" id="wardName" name="wardName" value=""/>
					</div> 
				</div>
				<div class="col-sm-12">
					<div class="form-group">
						<font class="control-label">Người liên lạc : 
							<span class="icon-asterisk text-warning"> 
								<span class="hide-accessible">*</span> 
							</span>
						</font> 
						<input class="k-textbox" style="width: 100%;" id="applicantName" name="applicantName" value=""/>
					</div> 
				</div>
				<div class="col-sm-12">
					<div class="form-group">
						<font class="control-label">Số điện thoại liên hệ : 
						</font> 
						<input class="k-textbox" style="width: 100%;" id="contactTelNo" name="contactTelNo" value=""/>
					</div> 
				</div>
				<div class="col-sm-12">
					<div class="form-group">
						<font class="control-label">Email : 
						</font> 
						<input class="k-textbox" style="width: 100%;" id="contactEmail" name="contactEmail" value=""/>
					</div> 
				</div>
				<div class="col-sm-12 text-center">
					<button class="k-button btn-blue"><i class="glyphicon glyphicon-edit"></i> Sửa</button>
					<button class="k-button btn-blue">Quay lại</button>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$("#cityName").kendoComboBox({
		dataTextField:"",
		dataValueField:"",
		/*dataSource:{
			transport:{
				read:{
					url:"",
					dataType:"json",
					type:"GET"
				}
			},
			schema:{
				data:"data",
				total:"total",
				model:{
					id:"id"
				}
			}
		}*/
	});
	$("#districtName").kendoComboBox({
		dataTextField:"",
		dataValueField:"",
		/*dataSource:{
			transport:{
				read:{
					url:"",
					dataType:"json",
					type:"GET"
				}
			},
			schema:{
				data:"data",
				total:"total",
				model:{
					id:"id"
				}
			}
		}*/
	});
	$("#wardName").kendoComboBox({
		dataTextField:"",
		dataValueField:"",
		/*dataSource:{
			transport:{
				read:{
					url:"",
					dataType:"json",
					type:"GET"
				}
			},
			schema:{
				data:"data",
				total:"total",
				model:{
					id:"id"
				}
			}
		}*/
	});
</script>