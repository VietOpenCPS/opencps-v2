<#include "init.ftl">

<div class="row MT20">

	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		
		  <div class="btn-group">

			<button type="button" class="btn btn-active" id="_dictCollection_formtemplate_btnScript">Script</button>
			<button type="button" class="btn btn-active" id="_dictCollection_formtemplate_btnHtml">View html</button>
			
		</div>

	</div>

	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 PT20">
		
		<textarea rows="20" class="form-control" id="_dictCollection_formtemplate_inp" placeholder="Mô tả chi tiết"  >${(dictCollection_dictCollection.dataForm)!}</textarea>

		<div class="PT50 PL50 PB50 PR50" id="_dictCollection_formtemplate_html" style="display: none;">
		</div>

	</div>

	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 PT20 text-center">
		
		<button class="btn btn-sm btn-active" id="_dictCollection_formtemplate_submit" name="_dictCollection_formtemplate_submit" type="button" >
			
			<i class="fa fa-check-circle"></i> 
			<span class="lfr-btn-label">Cập nhật thay đổi</span>

		</button> 

	</div>

</div>

<script type="text/javascript">
	
(function($) {

	$(document).on('click', '#_dictCollection_formtemplate_btnScript', function(event){
		
		event.preventDefault();
		event.stopPropagation();
		event.stopImmediatePropagation();

		$("#_dictCollection_formtemplate_inp").show("slow");
		$("#_dictCollection_formtemplate_html").hide("slow");
		
	});

	$(document).on('click', '#_dictCollection_formtemplate_btnHtml', function(event){
		
		event.preventDefault();
		event.stopPropagation();
		event.stopImmediatePropagation();


		$("#_dictCollection_formtemplate_inp").hide("slow");
		$("#_dictCollection_formtemplate_html").show("slow");

		var formTemplate = $("#_dictCollection_formtemplate_inp").val().trim();
		
		try {

			var dataForm = eval("(" + formTemplate + ")");
			$("#_dictCollection_formtemplate_html").alpaca(dataForm);

		} catch (e) {
			
			$("#_dictCollection_formtemplate_html").toggle("slow");
			showMessageToastr("error","Mẫu Alpaca không hợp lệ!!!");

		}
		
	});

	$(document).on('click', '#_dictCollection_formtemplate_submit', function(event){
		
		event.preventDefault();
		event.stopPropagation();
		event.stopImmediatePropagation();

		var _collectionSub_dataForm_BaseUrl_detail = "${api.endpoint}/temp/dictcollections/${(dictCollection_dictCollection.collectionCode)!}/dataform"

		$.ajax({
			url: _collectionSub_dataForm_BaseUrl_detail,
			headers: {
				"groupId": ${groupId},
			},
			data: {
				dataform: $("#_dictCollection_formtemplate_inp").val().trim()
			},
			type: 'PUT',
			dataType: 'json',
			
			contentType: 'application/x-www-form-urlencoded; charset=utf-8',
			success: function(data, textStatus, xhr) {
			
				showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
				
			},
			error: function(xhr, textStatus, errorThrown) {
				
				showMessageByAPICode(xhr.status);
			
			}
		});
		
	});

})(jQuery);

</script>
