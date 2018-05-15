<#include "../init.ftl">
<div class="PL10 PR10">
    <div class="row">
        <div class="row-header align-middle-lg">
            <div class="background-triangle-big">
                Hồ sơ mới tiếp nhận
            </div>
            <div class="text-light-gray MLA">
                <button class="btn btn-next" onclick="submitDossiers()"><i class="fa fa-paper-plane" aria-hidden="true"></i> Gửi tiếp nhận</button>
            </div>
        </div>
    </div>
    <div class="row">
        <table class="table table-bordered M0">
            <thead>
                <th class="fieldDossier text-center hover-pointer" width="3%">
                    <input type="checkbox" id="chooseAll" onclick="chooseAll()" value="1">
                </th>
                <th class="fieldDossier text-center hover-pointer" width="3%">STT</th>
                <th class="fieldDossier text-center hover-pointer" width="10%">Mã hồ sơ</th>
                <th class="fieldDossier text-center hover-pointer" width="20%">Họ và tên</th>
                <th class="fieldDossier text-center hover-pointer" width="39%">Thủ tục</th>
                <th class="fieldDossier text-center hover-pointer" width="10%">Ngày hẹn trả</th>
                <th class="fieldDossier text-center hover-pointer" width="15%">Ghi chú</th>
            </thead>
            <tbody id="dossiers-list"></tbody>
        </table>
        <div id="pager" class="pull-right"></div>
    </div>
</div>

<script type="text/x-kendo-template" id="listTemplate">
    <tr class="rowTable">
        <td class="text-center"><input type="checkbox" class="dossierId" value="#=dossierId#"></td>
        <td class="text-center">#=++count#</td>
        <td class="text-center">#=dossierIdCTN#</td>
        <td>#=applicantName#</td>
        <td>#=serviceName#</td>
        <td class="text-center">#=dueDate#</td>
        <td>#=dossierNote#</td>
    </tr>
</script>
<script type="text/javascript">
    console.log('test');
    var newDossiers;
    $(function(){
        newDossiers = new kendo.data.DataSource({
        transport:{
            read: function (options) {
                $.ajax({
                    type : 'GET', 
                    dataType : "json",
                    url  : '${api.server}/dossiers',
                    headers: {"groupId": ${groupId}},
                    data: {
                        status: 'new'
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
        dataSource: newDossiers,
        buttonCount: 3,
        messages: {
            display: ""
        }
    });
     $("#dossiers-list").kendoListView({
        dataSource : newDossiers,
        template: kendo.template($("#listTemplate").html()),
        dataBinding: function() {
            count = (this.dataSource.page() -1) * this.dataSource.pageSize();
        }

    });
 })

    function submitDossiers(){
        $(".dossierId:checked:checked").each(function() {
            $.ajax({
                url: '${api.server}/dossiers/'+ $(this).val() +'/submitting',
            })
        });

        newDossiers.read();
    }

    function chooseAll() {
        if($("#chooseAll").prop("checked"))
        {
            $(".dossierId").prop("checked","true");
        } else {
            $(".dossierId").removeAttr('checked');
        }
        $(".dossierId").trigger('change');

    }
</script>