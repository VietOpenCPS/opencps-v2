<!-- Template thông tin hồ sơ cơ bản -->
<div class="panel panel-default MB15" id="DossiersDetailInfo">
	<div class="panel-heading"> 
		<span class="text-bold text-light-blue">THÔNG TIN HỒ SƠ | </span> <span data-bind="text:applicantName"> </span>
	</div>
	<div class="panel-body PL0">
		<div class="col-sm-12 MB10">
            <span class="text-bold">Tên hồ sơ: </span> <span data-bind="text:serviceName"> </span>
        </div>
		<div class="col-sm-12 MB10">
            <span class="text-bold">Mã hồ sơ: </span> <span data-bind="text:serviceCode"> </span>
        </div>
        <div class="col-sm-12 MB10">
            <span class="text-bold">Mã tiếp nhận: </span> <span data-bind="text:dossierNo"> </span>
        </div>
        <div class="col-sm-12 MB10">
            <span class="text-bold">Cơ quan thực hiện: </span> <span data-bind="text:govAgencyName"> </span>
        </div>
        <div class="col-sm-12 MB10">
            <span class="text-bold">Người nộp hồ sơ: </span> <span data-bind="text:applicantName"> </span>
        </div>
        <div class="col-sm-12 MB10">
            <span class="text-bold">Ngày nộp: </span> <span data-bind="text:submitDate"> </span>
        </div>
        <div class="col-sm-12 MB10">
            <span class="text-bold">Ngày hẹn trả: </span> <span data-bind="text:dueDate"> </span>
        </div>
        <div class="col-sm-12">
            <span class="text-bold">Tình trạng hồ sơ: </span> <span data-bind="text:dossierStatusText"> </span>
        </div>
	</div>
    <hr class="P0 M0 MB15">
	<div class="clearfix">
		<div class="form-group col-sm-6">
			<p>Bạn muốn xem chi tiết thông tin hồ sơ >></p>
		</div>
		<div class="form-group col-sm-6">
            <div class="col-sm-10">
                <input id="input_dossier_detail" type="text" class="form-control" placeholder="Nhập mã bí mật">
            </div>
			<button class="btn btn-border-color col-sm-2" id="btn_dossierinfo_detail">Tra cứu</button>
		</div>
	</div>
</div>
<script>
	var pullDataDetail= function(id){
        console.log(id);
        $.ajax({
            url : "${api.server}/dossiers/"+id,
            dataType : "json",
            type : "GET",
            beforeSend: function(req) {
                        req.setRequestHeader('groupId', ${groupId});
                    },
            success : function(result){
                var viewModel = kendo.observable({
	                applicantName: result.applicantName,
                    serviceName: result.serviceName,
                    dossierNo: result.dossierNo,
                    govAgencyName: result.govAgencyName,
                    submitDate: result.submitDate,
                    dueDate: result.dueDate,
                    dossierStatusText: result.dossierStatusText
                });
                kendo.bind($("#DossiersDetailInfo"), viewModel);
            },
            error : function(xhr){

            }
        });
    };
    //dataSource chi tiết thông tin hồ sơ
    var dataSourceDossierFileDetail = new kendo.data.DataSource({
        type: "json",
        transport: {
            read: function (options) {
                $.ajax({
                    url: "${api.server}/dossiers/"+dataItem.dossierId+"/files",
                    dataType: "json",
                    type: 'GET',
                    data: {
                        password: options.data.password
                    },
                    beforeSend: function(req) {
                        req.setRequestHeader('groupId', ${groupId});
                    },
                    success: function (result) {
                        options.success(result);
                    },
                    error : function(xhr){
                        $("#DossierDetailFile").html("<span>Không có dữ liệu</span>")
                    }
                });
             }
        },
        schema : {
            total : "total",
            data : "data"
        }
    });
    var dataSourceDossierLogDetail = new kendo.data.DataSource({
        type: "json",
        transport: {
            read: function (options) {
                $.ajax({
                    url: "${api.server}/dossiers/"+dataItem.dossierId+"/logs",
                    // url:"http://localhost:3000/dossierlogs",
                    dataType: "json",
                    type: 'GET',
                    data: {
                        password: options.data.password
                    },
                    beforeSend: function(req) {
                        req.setRequestHeader('groupId', ${groupId});
                    },
                    success: function (result) {
                        options.success(result);
                        var index = 0;
                        $("#DossierDetailLog .orderNo").each(function(){
                            index+=1;
                            $(this).html(index) 
                        })
                    },
                    error : function(xhr){
                        $("#DossierDetailLog").html("<span>Không có dữ liệu</span>")
                    }
                })
            }
        },
        schema : {
            total : "total",
            data : "data"
        }
    });
    $("#DossierDetailFile").kendoListView({
        dataSource : dataSourceDossierFileDetail,
        template : kendo.template($("#tempDossierDetailFile").html()),
        navigatable: false,
        selectable: false,
        autoBind:false
    });
    $("#DossierDetailLog").kendoListView({
        dataSource : dataSourceDossierLogDetail,
        template : kendo.template($("#tempDossierDetailLog").html()),
        navigatable: false,
        selectable: false,
        autoBind:false
    });
    var evenDataDossierDetail = function(){
        var paraValue2 = $("#input_dossier_detail").val(); 
        dataSourceDossierFileDetail.read({password: paraValue2});
        dataSourceDossierLogDetail.read({password: paraValue2})
    };
    $("#btn_dossierinfo_detail").click(
                            function(){
                                $("#detailView2").show();
                                evenDataDossierDetail()
                            }
                        )   
</script>