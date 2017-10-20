<#-- Template thông tin hồ sơ chi tiết -->
<div class="panel panel-default" id="DossiersDetailInfo2">
	<div class="panel-heading"> 
		<span class="text-bold">Kết quả xử lý</span>
	</div>
	<div class="panel-body">
		<div class="col-sm-12">
            <span class="text-bold"></span>
        </div>
		<div class="col-sm-12">
            <span data-bind="text:serviceCode"> </span>
        </div>
        <div class="col-sm-12">
            <span data-bind="text:dossierNo"> </span>
        </div>
        
	</div>
    <div class="panel-heading"> 
        <span class="text-bold">Tiến trình xử lý hồ sơ</span>
    </div>
    <div class="panel-body">
        <div class="col-sm-12">
            <span class="text-bold"></span>
        </div>
        <div class="col-sm-12">
            <span data-bind="text:serviceCode"> </span>
        </div>
        <div class="col-sm-12">
            <span data-bind="text:dossierNo"> </span>
        </div>
        
    </div>
</div>
<script type="text/javascript">
    // function đổ dữ liệu theo password
    var pullDataDetail2= function(password){
        console.log(password);
        $.ajax({
            url : "http://hanoi.fds.vn:2281/api/dossiers/"+password+"/files",
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
                kendo.bind($("#DossiersDetailInfo2"), viewModel);
            },
            error : function(xhr){

            }
        });
    }
</script>