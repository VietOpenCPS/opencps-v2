<#include "init.ftl">

<div class="modal-header">

	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	<h4 class="modal-title">Thêm nhóm danh mục</h4>
	
</div>

<div class="modal-body">

	<form id="_collectionAdd_form">
			
		<div class="form-group" >
		
			<label for="_collectionAdd_collectionCode">Mã nhóm danh mục

				<span class="icon-asterisk text-warning"></span>

			</label>
			<input type="text" id="_collectionAdd_collectionCode" name="_collectionAdd_collectionCode" class="form-control"
				placeholder="Mã nhóm danh mục" required validationMessage="Nhập mã nhóm danh mục"  />
		
		</div>

		<div class="form-group" >
		
			<label for="_collectionAdd_collectionName">Tên nhóm danh mục

				<span class="icon-asterisk text-warning"></span>

			</label>
			<input type="text" id="_collectionAdd_collectionName" name="_collectionAdd_collectionName" class="form-control"
				placeholder="Tên nhóm danh mục" required validationMessage="Nhập tên nhóm danh mục"  />
		
		</div>

		<div class="form-group" >
		
			<label for="_collectionAdd_collectionNameEN">Tên Tên tiếng anh:</label>
			<input type="text" id="_collectionAdd_collectionNameEN" name="_collectionAdd_collectionNameEN" class="form-control"
				placeholder="Tên tiếng anh" />
		
		</div>
		
		<div class="form-group">
		
			<label for="description">Mô tả chi tiết:</label>
			<input type="text" class="form-control" id="_collectionAdd_description" placeholder="Mô tả chi tiết">
		
		</div>

		<div class="form-group">

			<div class="row">
				
				<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
				
					<button class="btn btn-sm btn-active pull-right" 
						id="_collectionAdd_submitBtn" name="_collectionAdd_submitBtn" type="button"
						data-loading-text="<i class='fa fa-spinner fa-spin '></i> Đang lưu thông tin...">
						<i class="fa fa-check-circle"></i>
						<span class="lfr-btn-label">Xác nhận</span>
					</button>

				</div>
			
				<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
		
					<button class="btn btn-default btn-sm pull-left" data-dismiss="modal" value="Quay lại">
						<i class="icon-undo"></i>
						<span class="lfr-btn-label">Quay lại</span>
					</button>

				</div>

			</div>

		</div>
		
	</form>

</div>


<script type="text/javascript">


(function($) {


	$(document).on('click', '#_collectionAdd_submitBtn', function(event){
		
		event.preventDefault();
		event.stopPropagation();
		event.stopImmediatePropagation();
		
		var _collectionAdd_form = $("#_collectionAdd_form").kendoValidator().data("kendoValidator");

		if (!_collectionAdd_form.validate()) {
			return;
		} else {

			var _collectionAdd_BaseUrl = "${api.endpoint}/dictcollections";

			$.ajax({
				url: _collectionAdd_BaseUrl,
				headers: {
					"groupId": ${groupId},
				},
				data: {
					collectionName: $( "#_collectionAdd_collectionName" ).val().trim(),
					collectionCode: $( "#_collectionAdd_collectionCode" ).val().trim(),
					description: $( "#_collectionAdd_description" ).val().trim(),
					collectionNameEN: $( "#_collectionAdd_collectionNameEN" ).val().trim(),
				},
				type: 'POST',
				dataType: 'json',
				contentType: 'application/x-www-form-urlencoded; charset=utf-8',
				success: function(data, textStatus, xhr) {
				
					var dataSource = $("#_collection_listView").getKendoListView().dataSource;
					
					$("#_collection_hidden_new_id").val(data.collectionCode);
					dataSource.read();
					//dataSource.pushCreate(data);
					//$('#dictCollectionCounterList').html(dataSource.total());
					$("#modal").trigger({ type: "click" });
					showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
					
				},
				error: function(xhr, textStatus, errorThrown) {
				
					$("#_collection_listView").getKendoListView().dataSource.error();
					showMessageByAPICode(xhr.status);
				
				}
			});

		}
				
	 
	});


})(jQuery);

</script>

