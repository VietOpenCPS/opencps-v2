<div class="panel panel-default" id="DossiersDetailInfo">
	<div class="panel-heading"> 
		<span class="text-bold">THÔNG TIN HỒ SƠ | </span> <span data-bind="text:applicantName"> </span>
	</div>
	<div class="panel-body">
		<div class="col-sm-12">
            <span class="text-bold">Tên hồ sơ: </span> <span data-bind="text:serviceName"> </span>
        </div>
		<div class="col-sm-12">
            <span class="text-bold">Mã hồ sơ: </span> <span data-bind="text:serviceCode"> </span>
        </div>
        <div class="col-sm-12">
            <span class="text-bold">Mã tiếp nhận: </span> <span data-bind="text:dossierNo"> </span>
        </div>
        <div class="col-sm-12">
            <span class="text-bold">Cơ quan thực hiện: </span> <span data-bind="text:govAgencyName"> </span>
        </div>
        <div class="col-sm-12">
            <span class="text-bold">Người nộp hồ sơ: </span> <span data-bind="text:applicantName"> </span>
        </div>
        <div class="col-sm-12">
            <span class="text-bold">Ngày nộp: </span> <span data-bind="text:submitDate"> </span>
        </div>
        <div class="col-sm-12">
            <span class="text-bold">Ngày hẹn trả: </span> <span data-bind="text:receiveDate"> </span>
        </div>
        <div class="col-sm-12">
            <span class="text-bold">Tình trạng hồ sơ: </span> <span data-bind="text:dossierStatusText"> </span>
        </div>
	</div>
	<div class="panel-footer">
		<div class="col-sm-6">
			<p>Bạn muốn xem chi tiết thông tin hồ sơ >></p>
		</div>
		<div class="input-group MB15">
			<input id="input_search_serviceinfo" type="text" class="form-control" placeholder="Nhập mã bí mật">
			<div class="input-group-btn">
				<button class="btn btn-default" type="submit">Tra cứu</button>
			</div>
		</div>
	</div>
</div>
<script>
	var pullDataDetail= function(id){
        console.log(id);
        $.ajax({
            url : "http://hanoi.fds.vn:2281/api/dossiers/"+id,
            dataType : "json",
            type : "GET",
            success : function(result){
                var viewModel = kendo.observable({
	                applicantName: result.applicantName,
                    serviceName: result.serviceName,
                    dossierNo: result.dossierNo,
                    govAgencyName: result.govAgencyName,
                    submitDate: result.submitDate,
                    receiveDate: result.receiveDate,
                    dossierStatusText: result.dossierStatusText
                });
                kendo.bind($("#DossiersDetailInfo"), viewModel);
            },
            error : function(xhr){

            }
        });
}
</script>