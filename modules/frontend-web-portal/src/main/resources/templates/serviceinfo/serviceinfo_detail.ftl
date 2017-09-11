<#if (Request)??>
	<#include "init.ftl">
</#if>

<div class="row panel">
    <div class="row-header">
        <div class="background-triangle-big">CHI TIÊT THỦ TỤC HÀNH CHÍNH</div>
    </div>

    <div class="panel-body" id="dataDetailServiceInfo">
        <div class="row MB15">
            <div class="col-sm-12">
                <span class="" id="maxLevel" data-bind="text:maxLevel"> </span> <span data-bind="text:serviceName" class="text-bold" id="serviceName"> #:serviceName# </span>
            </div>
        </div>
        <div id="serviceinfo_detail_tabstrip">
            <ul class="nav nav-tabs">
                <li class="active">
                    <a data-toggle="tab" href="#ttc">
                        Thông tin chung
                    </a>
                </li>
                <li>
                    <a data-toggle="tab" href="#ttth">
                        Trình tự thực hiện
                    </a>
                </li>
                <li>
                    <a data-toggle="tab" href="#tphs">
                        Thành phần hồ sơ
                    </a>
                </li>
                <li>
                    <a data-toggle="tab" href="#ycdk">
                        Yêu cầu điều kiện
                    </a>
                </li>
            </ul>
            <div class="tab-content">
                <div id="ttc" class="tab-pane fade in active">
                    <div class="row-parts-content">
                        <div class="row">
                            <div class="col-sm-2">
                                <label>Cơ quan quản lý</label>
                            </div>
                            <div class="col-sm-10">
                                <p data-bind="text:administrationName" id="administrationName">#:administrationName#</p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>Lĩnh vực</label>
                            </div>
                            <div class="col-sm-10">
                                <p data-bind="text:domainName" id="domainName">#:domainName#</p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>Cách thực hiện</label>
                            </div>
                            <div class="col-sm-10">
                                <p>fffffffffffff</p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>Thời gian giải quyết</label>
                            </div>
                            <div class="col-sm-10">
                                <p>fffffffffff</p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>Đối tượng</label>
                            </div>
                            <div class="col-sm-10">
                                <p>ffffffffff</p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>Kết quả giải quyết</label>
                            </div>
                            <div class="col-sm-10">
                                <p data-bind="text:resultText" id="resultText">#:resultText#</p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>Lệ phí</label>
                            </div>
                            <div class="col-sm-10">
                                <p data-bind="text:feeText" id="feeText">#:feeText#</p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label>Căn cứ pháp lý</label>
                            </div>
                            <div class="col-sm-10">
                                <p>ffffffffffff</p>
                            </div>
                        </div>
                        <div class="col-sm-12 PL0 MT15">
                            <button type="button" class="btn btn-active" data-toggle="modal" data-target="#submitDossierModal">Nộp hồ sơ >></button>
                        </div>
                    </div>
                </div>
                <div id="ttth" class="tab-pane fade">
                    <p>Trips made by Northern Ireland's Democratic Unionist Party MP Ian Paisley come under scrutiny in the Telegraph.</p>
                    <p>The paper alleges that he accepted holidays worth £100,000 from the government of Sri Lanka - and that he is now helping the country to secure a post-Brexit trade deal.</p>

                    <div class="col-sm-12 PL0 MT15">
                        <button class="btn">Xem hướng dẫn >></button>
                    </div>
                </div>
                <div id="tphs" class="tab-pane fade">

                </div>
                <div id="ycdk" class="tab-pane fade">

                </div>
            </div>
        </div>
    </div>

    <div id="submitDossierModal" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Bạn hãy chọn cơ quan thực hiện</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-sm-12 text-center">
                        <input class="form-control" name="slAdministration" id="slAdministration">
                        </div>
                        <div class="col-sm-12 text-center MT15">
                            <button class="btn btn-active"> Đồng ý</button><button class="btn ML10" data-dismiss="modal"> Hủy bỏ</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">

    $(document).ready(function(){
       $("#slAdministration").kendoComboBox({
        dataTextField : "name",
        dataValueField : "value",
        dataSource : [
        { "name" : "ccc", "value" : 1},
        { "name" : "abc", "value" : 2},
        { "name" : "ddd", "value" : 3},
        { "name" : "eee", "value" : 4}
        ],
            /*dataSource : {
                transport : {
                    read : {
                        url : "",
                        dataType : "json",
                        type : "GET",
                        success : function(result){

                        },
                        error : function(xhr){

                        }
                    }
                },
                schema : {
                    data : "data",
                    total : "total",
                    model : {
                        id : "id"
                    }
                }
            },*/
            filter: "contains"
        });
   });

    var pullDataDetail= function(id){
        console.log(id);
        $.ajax({
            url : "${api.server}/serviceinfos/"+id,
            dataType : "json",
            type : "GET",
            success : function(result){
                var viewModel = kendo.observable({
                    maxLevel: function(){
                        if(result.maxLevel == 1){
                            $("#maxLevel").addClass("label label-info");
                        }else if(result.maxLevel == 2){
                            $("#maxLevel").addClass("label label-warning");
                        }else if(result.maxLevel == 3){
                            $("#maxLevel").addClass("label label-danger");
                        }else {
                            $("#maxLevel").addClass("label label-info");
                        }
                        return "Mức "+result.maxLevel;
                    },
                    serviceName: result.serviceName,
                    administrationName: result.administrationName,
                    domainName: result.domainName,
                    resultText: result.resultText,
                    feeText: result.feeText
                });

                kendo.bind($("#dataDetailServiceInfo"), viewModel);
            },
            error : function(xhr){

            }
        });
    }
</script>
