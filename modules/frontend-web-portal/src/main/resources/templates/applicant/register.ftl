<#if (Request)??>
<#include "init.ftl">
</#if>

<div class="row row eq-height-lg" id="register_container">
	<div class="col-xs-12 col-sm-8 align-middle-lg">
		<div class="col-xs-12 bg-gif-register"></div>
	</div>
	<div class="col-xs-12 col-sm-4 box register-wrapper">
   <form id="fm">
    <div class="row">
      <div class="box-title">
        <b>ĐĂNG KÝ TÀI KHOẢN</b>
      </div>
    </div>
     <input type="hidden" name="applicantIdType" value="business" >
    <#-- <div class="row MT10 MB10">
      <div class="col-xs-12 col-sm-12 text-center">
       <div class="radio-inline"> <input type="radio" name="applicantIdType" value="citizen" checked> <label>Công dân</label> </div>
       <div class="radio-inline"> <input type="radio" name="applicantIdType" value="business"> <label>Doanh nghiệp</label> </div>
     </div>
   </div> -->
   <div class="row MT15">
     <div class="col-xs-12 col-sm-12" id="lblApplicantName">Tên tổ chức</div>
     <div class="col-xs-12 col-sm-12 MT5">
      <input type="text" id="applicantName" name="applicantName" class="form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" placeholder="Tên tổ chức"/>
    </div>
  </div>
  <div class="row MT15">
    <div class="col-xs-12 col-sm-12" id="lblApplicantIdNo">Mã số thuế</div>
    <div class="col-xs-12 col-sm-12 MT5">
      <input type="text" id="applicantIdNo" name="applicantIdNo" class="form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" placeholder="Mã số thuế"/>
    </div>
  </div>
  <div class="row MT15">
   <div class="col-xs-12 col-sm-12">Ngày cấp</div>
   <div class="col-xs-12 col-sm-12 MT5">
    <input id="applicantIdDate" name="applicantIdDate" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" placeholder="Ngày/ tháng/ năm"/>
  </div>
</div>
<div class="row MT15">
  <div class="col-xs-12 col-sm-12">Thư điện tử</div>
  <div class="col-xs-12 col-sm-12 MT5">
    <input type="text" id="contactEmail" name="contactEmail" class="form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" placeholder="Địa chỉ thư điện tử" />
  </div>
</div>
<div class="row MT15">
  <div class="col-xs-12 col-sm-12">Số điện thoại</div>
  <div class="col-xs-12 col-sm-12 MT5">
    <input type="text" id="contactTelNo" name="contactTelNo" class="form-control" />
  </div>
</div>
<div class="row MT15">
 <div class="col-xs-12 col-sm-12">Mật khẩu</div>
 <div class="col-xs-12 col-sm-12 MT5">
  <input type="password" id="password" name="password" class="form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" placeholder="Gồm các ký tự 0-9, a-z, ít nhất 6 ký tự"/>
</div>
</div>
<div class="row MT15">
 <div class="col-xs-12 col-sm-12">Nhập lại mật khẩu</div>
 <div class="col-xs-12 col-sm-12 MT5">
  <input type="password" id="repassword" name="repassword" class="form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc"/>
</div>
</div>

<div class="row MT15">
 <div class="col-xs-12 col-sm-12">
   <div class="checkbox-inline"> <input type="checkbox" id="agreement" name="agreement"> <label class="text-normal">Tôi đồng ý với điều khoản sử dụng. </label> </div> <span><a href="javascript:;" id="viewRules" class="text-light-blue">Chi tiết</a></span>
 </div>
</div>
<div class="row MT15 MB15 text-center">
 <button class="btn btn-active" title="Đăng ký" id="btn-register" disabled="true">Đăng ký</button>
</div>
</form>
</div>
</div>

<script type="text/javascript">
  (function($){
    var validator = $("#fm").kendoValidator().data("kendoValidator");
   /* $("form").submit(function(event) {
       event.preventDefault();
       if (validator.validate()) {
         if (!$("#agreement").is(':checked')){
           alert('Bạn chưa đồng ý với điều khoản sử dụng!!!')
           return;
         }
         // TODO wait api
         console.log("sing up success");
         return true;
       }
     });*/

     $("#applicantIdDate").kendoDatePicker({
      format: "dd/MM/yyyy",
      culture: "vi-VN"
    });

     var datepicker = $("#applicantIdDate").data("kendoDatePicker");

     $("#applicantIdDate").click(function() {
       datepicker.open();
     });

     $("#btn-register").click(function(e){
      e.preventDefault();
      if(validator.validate()){
        if ($("#password").val().length < 6 || $("#repassword").val().length < 6){
         notification.show({ message:"Mật khẩu gồm các ký tự 0-9, a-z, ít nhất 6 ký tự" }, "error");
       } else if ($("#password").val() != $("#repassword").val()){
         notification.show({ message: "Xác nhận mật khẩu mới không đúng"}, "error");
       } else if (!$("#agreement").is(':checked')) {
        notification.show({ message: "Bạn chưa đồng ý với điều khoản sử dụng!!!"}, "error");
      }else{
        register();
      }
    }
  });

     var register = function(){
      var data = $('#fm').serialize();
      $.ajax({
        url: '${api.server}/applicants',
        type: 'POST',
        data: data,
        dataType : "json",
        headers: {
          "groupId": ${groupId}
        },
        success: function(result){

        },
        error: function(result){

        },
        statusCode: {
         200: function(result) {
          notification.show({
           title: "Success",
           message: "Đăng ký thành công."
         }, "success");

          setTimeout(function(){
           window.location.href = "${portalURL}/confirm-account?active_user_id=" + result.applicantId;
         }, 2000);
        },
        500: function(result) {
          if (JSON.parse(result.responseText).description == 'DuplicateContactEmailException'){
           notification.show({
            title: "Error",
            message: "Email bạn sử dụng đã tồn tại trong hệ thống."
          }, "error");
         } else if (JSON.parse(result.responseText).description == 'DuplicateApplicantIdException'){
           notification.show({
            title: "Error",
            message: "Số CMDN đã tồn tại trong hệ thống."
          }, "error");
         } else if (JSON.parse(result.responseText).description == 'DuplicateContactTelNoException'){
           notification.show({
            title: "Error",
            message: "Số điện thoại đã được sử dụng trong hệ thống."
          }, "error");
         } else {
           notification.show({
            title: "Error",
            message: "Đăng ký không thành công. Vui lòng thử lại."
          }, "error");
         }
       }
     }
   });
    }
  // ++++++++++++++++++ fix 27/12 congtrinh0209>
    // $('input[type=radio][name=applicantIdType]').change(function() {
    //   if (this.value == 'citizen') {
    //     $(this).closest('form').find("input[type=text],input[type=password], textarea").val("");
    //     $("#lblApplicantName").text("Họ và tên");
    //     $("#lblApplicantIdNo").text("Số CMND/ Hộ chiếu");
    //     $("#applicantName").attr("placeholder","Họ và tên");
    //     $("#applicantIdNo").attr("placeholder","Số CMND/ Hộ chiếu");
    //   }
    //   else  {
    //     $(this).closest('form').find("input[type=text],input[type=password], textarea").val("");
    //     $("#lblApplicantName").text("Tên tổ chức");
    //     $("#lblApplicantIdNo").text("Mã số thuế");
    //     $("#applicantName").attr("placeholder","Tên tổ chức");
    //     $("#applicantIdNo").attr("placeholder","Mã số thuế");
    //   }
    // });
  // +++++++++++++++++++++++++++
    $("#applicantIdDate").focusout(function(){
       setTimeout(function(){
        if ($("#applicantIdDate").hasClass("k-invalid")){
         $("#applicantIdDate").parent().addClass("MB25");
        } else {
           $("#applicantIdDate").parent().removeClass("MB25");
        }
      }, 100);
    });

    $("#agreement").click(function(){
      if($(this).is(':checked')){
        $("#btn-register").prop("disabled",false);
      }else {
        $("#btn-register").prop("disabled",true);
      }
    });

    $("#viewRules").click(function(e){
      e.preventDefault();

    });

  })(jQuery);


</script>
<#-- Phần nội dung điều khoản sử dụng -->
<div id="rules" style="background-color: #ffffff; color: black" class="MT20">
    <div><p style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); margin-left: 40px; color: rgb(85, 85, 85); text-align: center;"><span style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); font-size: 18px;"><strong style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);">CHÍNH SÁCH VỀ QUYỀN RIÊNG TƯ</strong></span></p> <p style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); color: rgb(85, 85, 85); text-align: justify;">Cổng thông tin điện tử Bộ Giao thông vận tải long trọng công bố dưới đây chính sách đảm bảo quyền riêng tư hợp pháp của người sử dụng Cổng thông tin điện tử Bộ Giao thông vận tải tại địa chỉ&nbsp;<a href="http://www.mt.gov.vn/" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); color: rgb(0, 154, 229);">www.mt.gov.vn</a>.</p> <p style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); color: rgb(85, 85, 85); text-align: justify;">Xin hãy đọc kỹ những quy định trong bản chính sách này. Nếu quý vị không đồng ý với chính sách về quyền riêng tư của chúng tôi, xin đừng sử dụng Cổng thông tin điện tử Bộ Giao thông vận tải tại địa chỉ&nbsp;<a href="http://www.mt.gov.vn/" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); color: rgb(0, 154, 229);">www.mt.gov.vn</a>.</p> <p style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); color: rgb(85, 85, 85); text-align: justify;">Bằng việc viếng thăm và sử dụng Cổng thông tin điện tử Bộ Giao thông vận tải tại địa chỉ nói trên, quý vị đã đồng ý những điều khoản của chính sách của Cổng thông tin điện tử Bộ Giao thông vận tải về quyền riêng tư được trình bày sau đây.</p> <p style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); color: rgb(85, 85, 85); text-align: justify;"><strong style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);">Tự động thu thập các thông tin không thuộc cá nhân</strong></p> <p style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); color: rgb(85, 85, 85); text-align: justify;">Cổng thông tin điện tử Bộ Giao thông vận tải cam kết bảo vệ quyền riêng tư của những người viếng thăm Cổng thông tin điện tử Bộ Giao thông vận tải.</p> <p style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); color: rgb(85, 85, 85); text-align: justify;">Xin lưu ý rằng Cổng thông tin điện tử Bộ Giao thông vận tải có thể cung cấp các số liệu thống kê tổng hợp về các khách viếng thăm, hình thức kết nối vào Cổng thông tin điện tử Bộ Giao thông vận tải và các thông tin liên quan đến Cổng thông tin điện tử Bộ Giao thông vận tải cho những bên thứ ba có uy tín, nhưng những thống kê này sẽ không chứa các thông tin nhận diện cá nhân.</p> <p style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); color: rgb(85, 85, 85); text-align: justify;"><strong style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);">Thu thập thông tin cá nhân với sự đồng ý của người dùng</strong></p> <p style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); color: rgb(85, 85, 85); text-align: justify;">Để đáp ứng yêu cầu của quý vị, hoặc quản lý những chương trình tương tác với người sử dụng như góp ý, Cổng thông tin điện tử Bộ Giao thông vận tải có thể cần phải hỏi những thông tin cá nhân của quý vị như tên, địa chỉ, và địa chỉ email.</p> <p style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); color: rgb(85, 85, 85); text-align: justify;">Cổng thông tin điện tử&nbsp;Bộ Giao thông vận tải có thể sử dụng những thông tin này để đáp ứng các yêu cầu của quý vị, hoặc liên lạc với quý vị bằng email để gửi thông tin về hoạt động và các sự kiện của chúng tôi.</p> <p style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); color: rgb(85, 85, 85); text-align: justify;">Ngoài những thông tin cá nhân mà quý vị có thể cung cấp cho Cổng thông tin điện tử Bộ Giao thông vận tải, Cổng thông tin điện tử Bộ Giao thông vận tải có thể sử dụng công nghệ cho phép thu thập một số thông tin kỹ thuật của quý vị như địa chỉ giao thức Internet, hệ điều hành trên máy tính, kiểu trình duyệt, các kiểu lưu thông mạng và địa chỉ của bất kỳ trang web liên quan nào.</p> <p style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); color: rgb(85, 85, 85); text-align: justify;"><strong style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);">Bảo mật</strong></p> <p style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); color: rgb(85, 85, 85); text-align: justify;">Xin lưu ý rằng luôn có những rủi ro gắn với việc cung cấp dữ liệu cá nhân một cách trực tiếp, qua điện thoại hoặc qua Internet và không có hệ thống công nghệ nào tuyệt đối an toàn để chống lại sự quấy nhiễu hoặc hacker.</p> <p style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); color: rgb(85, 85, 85); text-align: justify;">Tuy nhiên, Cổng thông tin điện tử Bộ Giao thông vận tải luôn cố gắng sử dụng các biện pháp thích hợp để ngăn cản và giảm thiểu những rủi ro của việc truy cập trái phép, sử dụng không đúng đắn và không chính xác các thông tin cá nhân của quý vị.</p> <p style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); color: rgb(85, 85, 85); text-align: justify;"><strong style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);">Tính chính xác của thông tin thu thập</strong></p> <p style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); color: rgb(85, 85, 85); text-align: justify;">Cổng thông tin điện tử Bộ Giao thông vận tải có thể bổ sung, sửa chữa hoặc xóa bất kỳ dữ liệu cá nhân nào không hoàn chỉnh, không chính xác hoặc lạc hậu mà Cổng thông tin điện tử Bộ Giao thông vận tải có được liên quan đến hoạt động của Cổng thông tin điện tử Bộ Giao thông vận tải.</p> <p style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); color: rgb(85, 85, 85); text-align: justify;"><strong style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);">Nhận diện khách viếng thăm</strong></p> <p style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); color: rgb(85, 85, 85); text-align: justify;">Đôi khi thông tin có thể được đưa vào máy tính của quý vị để cho phép chúng tôi nhận diện quý vị. Thông tin này thường được gọi là các “cookies”. Qua việc cho biết cách thức và thời gian mà các khách hàng viếng thăm Cổng thông tin điện tử Bộ Giao thông vận tải này, thông tin này có thể giúp Cổng thông tin điện tử Bộ Giao thông vận tải tiếp tục cải thiện Cổng thông tin điện tử Bộ Giao thông vận tải.</p> <p style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); color: rgb(85, 85, 85); text-align: justify;">Chúng tôi sẽ chỉ sử dụng cookies để xem thông tin trên đĩa cứng của quý vị mà những thông tin này được một cookies từ Cổng thông tin điện tử Bộ Giao thông vận tải này đặt vào đó. Việc sử dụng cookies là một tiêu chuẩn được công nhận rộng rãi và nhiều Cổng thông tin điện tử, website khác cũng sử dụng chúng. Các cookies được lưu trên máy tính của quý vị chứ không phải trên Cổng thông tin điện tử Bộ Giao thông vận tải.</p> <p style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); color: rgb(85, 85, 85); text-align: justify;">Nếu quý vị không muốn nhận các cookies, hoặc muốn được thông báo khi chúng được sử dụng, thì quý vị cần thiết lập chế độ cho trình duyệt của quý vị làm điều này, nếu trình duyệt của quý vị cho phép.</p> <p style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); color: rgb(85, 85, 85); text-align: justify;"><strong style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);">Thay đổi chính sách riêng tư</strong></p> <p style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); color: rgb(85, 85, 85); text-align: justify;">Cổng thông tin điện tử Bộ Giao thông vận tải có thể thay đổi chính sách về quyền riêng tư này, hoặc thay đổi, sửa đổi hoặc rút lại sự truy cập đến Cổng thông tin điện tử Bộ Giao thông vận tải, hoặc nội dung của những trang này vào bất kỳ lúc nào, có hoặc không có thông báo trước.</p> <p style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); color: rgb(85, 85, 85);">&nbsp;</p> <p align="right" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); color: rgb(85, 85, 85);"><em style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);">Cổng thông tin điện tử Bộ Giao thông vận tải</em></p> <div>&nbsp;</div></div>

</div>

<div class="responsive-message"></div>

<script>
  $(document).ready(function() {
    var viewRules = $("#rules");
    $("#viewRules").click(function() {
        viewRules.data("kendoWindow").open();
        $("div.k-widget.k-window").css({"background-color":"#14bef0","color":"#ffffff"})
    });
    viewRules.kendoWindow({
        title: "ĐIỀU KHOẢN SỬ DỤNG :",
        width: "50%",
        height: "500px",
        visible: false,
        actions: [
          "Maximize",
          "Close"
        ],
        modal: true,
        position: {
          top: "50px"
        },
        scrollable: true
    }).data("kendoWindow").center().close();
  });
</script>




