<#include "../init.ftl">
<div class="PL10 PR10">
    <div class="row">
        <div class="row-header align-middle-lg">
            <div class="background-triangle-big">
                Hồ sơ đã trả kết quả
            </div>
        </div>
    </div>
    <div class="row">
        <table class="table table-bordered M0">
            <thead>
                <th class="fieldDossier text-center hover-pointer" width="3%">STT</th>
                <<th class="fieldDossier text-center hover-pointer" width="10%">Mã hồ sơ</th>
                <th class="fieldDossier text-center hover-pointer" width="25%">Thủ tục</th>
                <th class="fieldDossier text-center hover-pointer" width="35%">Địa chỉ</th>
                <th class="fieldDossier text-center hover-pointer" width="10%">Ngày giờ tiếp nhận</th>
                <th class="fieldDossier text-center hover-pointer" width="10%">Ngày giờ hẹn trả</th>
                <th class="fieldDossier text-center hover-pointer" width="10%">Ghi chú</th>
            </thead>
            <tbody id="dossiers-list"></tbody>
        </table>
        <div id="pager" class="pull-right"></div>
    </div>
</div>

<script type="text/x-kendo-template" id="listTemplate">
    <tr class="rowTable">
        <td class="text-center">#=++count#</td>
        <td class="text-center">#=dossierIdCTN#</td>
        <td>#=serviceName#</td>
        <td>#=address#</td>
        <td class="text-center">#=receiveDate#</td>
        <td class="text-center">#=dueDate#</td>
        <td>#=dossierNote#</td>
    </tr>
</script>
<script type="text/javascript">
    console.log('test');
    $(function(){
       var doneDossiers = new kendo.data.DataSource({
        transport:{
            read: function (options) {
                $.ajax({
                    type : 'GET', 
                    dataType : "json",
                    url  : '${api.server}/dossiers',
                    headers: {"groupId": ${groupId}},
                    data: {
                        status: 'done'
                    },
                    success :  function(result){ 
                        options.success(result);
                    }
                })
            }
        },
        schema : {
            data : 'data',
            total: 'total'
        },
        pageSize: 10
    });
       $("#pager").kendoPager({
        dataSource: doneDossiers,
        buttonCount: 3,
        messages: {
            display: ""
        }
    });
       $("#dossiers-list").kendoListView({
        dataSource : doneDossiers,
        template: kendo.template($("#listTemplate").html()),
        dataBinding: function() {
            count = (this.dataSource.page() -1) * this.dataSource.pageSize();
        }

    });
   })

</script>