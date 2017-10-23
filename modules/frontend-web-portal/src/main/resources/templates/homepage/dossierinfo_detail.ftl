<#-- Template thông tin hồ sơ chi tiết -->
<div class="panel panel-default MB0" id="DossiersDetailFile">
    <div class="panel-heading"> 
        <span class="text-bold text-light-blue">Kết quả xử lý</span>
    </div>
    <div class="panel-body PL0">
        <div class="col-sm-12 MB10">
            <span class="text-bold">Tên hồ sơ: </span> <span data-bind="text:serviceName">Văn bản yêu cầu bổ sung</span>
        </div>
        <div class="col-sm-12">
            <span class="text-bold">Mã hồ sơ: </span> <span data-bind="text:serviceCode">Giấy chứng nhận bản quyền tác giả</span>
        </div>
    </div>
</div>
<div class="panel panel-default MB0" id="DossiersDetailLog">
    <div class="panel-heading"> 
        <span class="text-bold text-light-blue">Tiến trình xử lý hồ sơ</span>
    </div>
    <div class="panel-body PL0">
        <div class="col-sm-12 MB10">
            <span class="text-bold">Tên hồ sơ: </span> <span data-bind="text:serviceName"> </span>
        </div>
        <div class="col-sm-12">
            <span class="text-bold">Mã hồ sơ: </span> <span data-bind="text:serviceCode"> </span>
        </div>
    </div>
</div>
<script type="text/javascript">
    // Load thông tin chi tiết hồ sơ / dossierFile
    var pullDataDossierFile= function(password){
        console.log(password);
        $.ajax({
            url : "${api.server}/dossiers/"+password+"/files",
            dataType : "json",
            type : "GET",
            success : function(result){
                var viewModel = kendo.observable({
                    applicantName: result.applicantName,
                    serviceName: result.serviceName
                });
                kendo.bind($("#DossiersDetailFile"), viewModel);
            },
            error : function(xhr){

            }
        });
    }
    // Load thông tin chi tiết hồ sơ / dossierLog
    var pullDataDossierLog= function(password){
        console.log(password);
        $.ajax({
            url : "${api.server}/dossiers/"+password+"/files",
            dataType : "json",
            type : "GET",
            success : function(result){
                var viewModel = kendo.observable({
                    applicantName: result.applicantName,
                    serviceName: result.serviceName
                });
                kendo.bind($("#DossiersDetailLog"), viewModel);
            },
            error : function(xhr){
                
            }
        });
    }
</script>