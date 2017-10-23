<#-- Template thông tin hồ sơ chi tiết -->
<div class="row">
    <div class="panel panel-default MB0">
        <div class="panel-heading"> 
            <span class="text-bold text-light-blue">Kết quả xử lý</span>
        </div>
        <div class="panel-body P5 PL15">
            <ul class="ul-default" id="DossierDetailFile"></ul>
            <script type="text/x-kendo-template" id="tempDossierDetailFile">
                <li><span><i class="fa fa-download"></i></span> <span class="ML10">#:displayName#</span></li>
            </script>
        </div>
    </div>
</div>
<!--Render thông tin chi tiết DossierLog-->
<div class="row">
    <div class="panel panel-default MB0">
        <div class="panel-heading"> 
            <span class="text-bold text-light-blue">Tiến trình xử lý hồ sơ</span>
        </div>
        <div class="panel-body P5 PL15">
            <ul class="ul-default" id="DossierDetailLog"></ul>
            <script type="text/x-kendo-template" id="tempDossierDetailLog">
                <li>
                    <span class="text-bold stt">STT</span>
                    <span>Văn bản yêu cầu bổ sung</span>
                </li>
            </script>
        </div>
    </div>
</div>
<script type="text/javascript">
    // Cấu hình dataSource thông tin chi tiết hồ sơ
    var dataSourceDossierDetail = new kendo.data.DataSource({
        type: "json",
        transport: {
            read: function (options) {
                $.ajax({
                    url: "${api.server}/dossiers/"+dataItem.dossierId+"/"+options.data.endpoint,
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
                        options.error(xhr);
                    }
                });
             }
        },
        schema : {
            total : "total",
            data : "data"
        }
    });
    $("#DossierDetailFile").kendoListView({
        dataSource : dataSourceDossierDetail,
        template : kendo.template($("#tempDossierDetailFile").html()),
        navigatable: false,
        selectable: false,
        autoBind:false
    });
    $("#DossierDetailLog").kendoListView({
        dataSource : dataSourceDossierDetail,
        template : kendo.template($("#tempDossierDetailLog").html()),
        navigatable: false,
        selectable: false,
        autoBind:false
    });
    // event truyền tham số vào read dataSource
    var evenDataDossierDetail = function(endpoint){
        var paraValue2 = $("#input_dossier_detail").val();
        console.log(paraValue2);
        dataSourceDossierDetail.read({password: paraValue2, endpoint: endpoint})
    };
    $("#btn_dossierinfo_detail").click(
                         function(){
                            evenDataDossierDetail("files");
                            evenDataDossierDetail("logs");
                        }
                    )
</script>