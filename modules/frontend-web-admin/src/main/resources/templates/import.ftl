<#if (Request)??>
<#include "init.ftl">
</#if>

<div class="row box MT10" style="min-height: 150px;">
	<div class="col-xs-12 col-sm-12 MT10">
		<input type="file" style="display: none;" name="input_import_service_process" id="input_import_service_process">
		<button id="btn_import_service_process" class="k-button btn-primary MR10" title="Import"><i class="glyphicon glyphicon-plus"></i> Import</button>
		<input type="file" style="display: none;" name="input_export_service_process" id="input_export_service_process">
		<button id="btn_export_service_process" class="k-button btn-primary" title="Export"><i class="glyphicon glyphicon-plus"></i> Export</button>
	</div>
	<div class="col-sm-12 col-xs-12 MT10">
		<div id="statusImport" name="statusImport" class="MB5" style="text-transform: uppercase; font-size: 15px; font-weight: bold;"></div>
		<span id="titleListFile" style="display: none; font-weight: bold;">Danh sách file: </span>
		<p class="ML20" id="messageImport" name="messageImport"></p>
	</div>
</div>

<script type="text/javascript">
	$("#btn_import_service_process").click(function (event) {
		$("#input_import_service_process").click();
		$("#titleListFile").hide();
	})
	$("#input_import_service_process").change(function (event) {
		$("#statusImport").html('Đang xử lý....');
		$("#messageImport").html('');
		var url = '/o/rest/v2/dossiers/import/files'
		var data = new FormData();
		data.append( 'file', $(this)[0].files[0]);
		$.ajax({
			type : 'POST', 
			url  : url, 
			data : data,
			dataType: 'html',
			headers: {"groupId": ${groupId}},
			processData: false,
			contentType: false,
			cache: false,
			success :  function(result){
				// notification.show({
				// 	message: "Yêu cầu được thực hiện thành công"
				// }, "success");
			},
			error:function(result){
				// notification.show({
				// 	message: "Thực hiện không thành công, xin vui lòng thử lại"
				// }, "error");
			},
			statusCode: {
				200: function(result) {
					console.log(result);
					$("#titleListFile").show();
					$("#statusImport").html('<span style="color: green;">Import thành công!</span>');
					$("#messageImport").html(result);
					$(this).val("");
				},
				500: function(result) {
					console.log(result);
					$("#titleListFile").show();
					$("#statusImport").html('<span style="color: red;">Import không thành công, Vui lòng thử lại!</span>');
					$("#messageImport").html(result);
					$(this).val("");
				},
				504: function(result) {
					console.log(result);
					// $("#titleListFile").show();
					$("#statusImport").html('<span style="color: red;">Quá thời gian, Vui lòng thử lại!</span>');
					$("#messageImport").html(result);
					$(this).val("");
				}
			}
		});
	})
</script>