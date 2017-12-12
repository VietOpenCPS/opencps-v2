<#if (Request)??>
  <#include "init.ftl">
</#if>

<div class="row account-info">
  <div class="col-sm-2 col-xs-12">
    <img src="http://vuicuoi.com.vn/uploads/avatar/11025743_1050901561593820_6159841534685824102_n.jpg" class="img-responsive max-width-100 img-rounded">
    <div class="text-center"><a href="" class="text-light-gray">Thay đổi avatar</a></div>
    <p class="name text-bold text-center">Lương Thị Hạnh</p>
    <div>Số CMND/Hộ chiếu: <span class="text-bold">0123456789</span></div>
    <div>Ngày cấp: <span class="text-bold">26/09/2015</span></div>
    <div>Thư điện tử: <span class="text-bold">hanhlt@gmail.com</span></div>

    <ul class="nav nav-tabs" role="tablist">
      <li role="presentation" class="active"><a href="#tttk" role="tab" data-toggle="tab">Thông tin tài khoản</a></li>
      <li role="presentation"><a href="#dmk" role="tab" data-toggle="tab">Đổi mật khẩu</a></li>
    </ul> 
    <#-- <button class="btn btn-classic form-control">Thông tin tài khoản</button>
    <button class="btn btn-classic form-control">Đổi mật khẩu</button> -->
  </div>

  <div class="col-sm-10 col-xs-12">
    <div class="tab-content box">
      <div role="tabpanel" class="tab-pane active" id="tttk">
        <div class="row-header">
          <div class="background-triangle-big"><i class="fa fa-user" aria-hidden="true"></i></div> 
          <span class="text-bold">Thông tin tài khoản</span>
        </div>
        <div class="row-parts-content PB15">
          <div class="row MB15">
            <div class="col-sm-2">
             <p> Tên công ty</p>
           </div>
           <div class="col-sm-7">
             <span id="companyName" data-pk="1" data-toggle="#editCompanyName" data-original-title="Enter note" tabindex="-1" class="">pretty note..</span>
             <span class="pull-right">
               <a href="#" id="editCompanyName" style="float: right"><i class="fa fa-pencil"></i></a>
             </span>
           </div>
         </div>
         <div class="row MB15">
          <div class="col-sm-2">
            <p>Địa chỉ tổ chức</p>
          </div>
          <div class="col-sm-7">
            <span id="address" data-pk="1" data-toggle="#editAddress" data-original-title="Enter note" tabindex="-1" class="">Tầng 4 tòa nhà VinGroup, số 41 ngõ 3</span>
            <span class="pull-right">
              <a href="#" id="editAddress" style="float: right"><i class="fa fa-pencil"></i></a>
            </span>
          </div>
        </div>
        <div class="row MB15">
          <div class="col-sm-2">
            <p>Tỉnh/ Thành phố</p>
          </div>
          <div class="col-sm-7">
            <span id="city" data-pk="1" data-toggle="#editCity" data-original-title="Enter note" tabindex="-1" class="">Tầng 4 tòa nhà VinGroup, số 41 ngõ 3</span>
            <span class="pull-right">
              <a href="#" id="editCity" style="float: right"><i class="fa fa-pencil"></i></a>
            </span>
          </div>
        </div>
        <div class="row MB15">
          <div class="col-sm-2">
            <p>Quận/ Huyện</p>
          </div>
          <div class="col-sm-7">
            <span id="district" data-pk="1" data-toggle="#editDistrict" data-original-title="Enter note" tabindex="-1" class="">Tầng 4 tòa nhà VinGroup, số 41 ngõ 3</span>
            <span class="pull-right">
              <a href="#" id="editDistrict" style="float: right"><i class="fa fa-pencil"></i></a>
            </span>
          </div>
        </div>
        <div class="row MB15">
          <div class="col-sm-2">
            <p>Xã/ Phường</p>
          </div>
          <div class="col-sm-7">
            <span id="wards" data-pk="1" data-toggle="#editWards" data-original-title="Enter note" tabindex="-1" class="">Tầng 4 tòa nhà VinGroup, số 41 ngõ 3</span>
            <span class="pull-right">
              <a href="#" id="editWards" style="float: right"><i class="fa fa-pencil"></i></a>
            </span>
          </div>
        </div>
        <div class="row MB15">
          <div class="col-sm-2">
            <p>Điện thoại</p>
          </div>
          <div class="col-sm-7">
            <span id="phone" data-pk="1" data-toggle="#editPhone" data-original-title="Enter note" tabindex="-1" class="">Tầng 4 tòa nhà VinGroup, số 41 ngõ 3</span>
            <span class="pull-right">
              <a href="#" id="editPhone" style="float: right"><i class="fa fa-pencil"></i></a>
            </span>
          </div>
        </div>
        <div class="row MB15">
          <div class="col-sm-2">
            <p>Thư điện tử - Email</p>
          </div>
          <div class="col-sm-7">
            <span id="email" data-pk="1" data-toggle="#editEmail" data-original-title="Enter note" tabindex="-1" class="">Tầng 4 tòa nhà VinGroup, số 41 ngõ 3</span>
            <span class="pull-right">
              <a href="#" id="editEmail" style="float: right"><i class="fa fa-pencil"></i></a>
            </span>
          </div>
        </div>
        <div class="row MB15">
          <div class="col-sm-2">
            <p>Tên người đại diện</p>
          </div>
          <div class="col-sm-7">
            <span id="represntative" data-pk="1" data-toggle="#editRepresntative" data-original-title="Enter note" tabindex="-1" class="">Tầng 4 tòa nhà VinGroup, số 41 ngõ 3</span>
            <span class="pull-right">
              <a href="#" id="editRepresntative" style="float: right"><i class="fa fa-pencil"></i></a>
            </span>
          </div>
        </div>
      </div>
    </div>
    <div role="tabpanel" class="tab-pane" id="dmk">
      <div class="row-header">
        <div class="background-triangle-big"><i class="fa fa-user" aria-hidden="true"></i></div> 
        <span class="text-bold">Đổi mật khẩu</span>
      </div>
      <form id="fm">
        <div class="row-parts-content PB15">
          <div class="row">
            <div class="col-sm-2">
              <label class="with-input-sm">Mật khẩu hiện tại:</label>
            </div>
            <div class="col-sm-4">
              <div class="form-group"> 
                <input type="password" class="form-control" id="old_password"> 
              </div>
            </div>
            <div class="col-sm-6">
              <span><i class="fa fa-bolt"></i> <span class="message">Mật khẩu nhập bị sai</span></span>
            </div>
          </div>
          <div class="row">
            <div class="col-sm-2">
              <label class="with-input-sm">Mật khẩu mới:</label>
            </div>
            <div class="col-sm-4">
              <div class="form-group"> 
                <input type="password" class="form-control" id="new_password"> 
              </div>
            </div>
            <div class="col-sm-6">

            </div>
          </div>
          <div class="row">
            <div class="col-sm-2">
              <label class="with-input-sm">Nhập lại mật khẩu mới:</label>
            </div>
            <div class="col-sm-4">
              <div class="form-group"> 
                <input type="password" class="form-control" id="retype_new_password"> 
              </div>
              <div class="checkbox"> 
                <input type="checkbox" id="show_password"> <label>Hiển thị mật khẩu</label> 
              </div>
            </div>
            <div class="col-sm-6">

            </div>
          </div>
          <div class="row">
            <div class="col-sm-2">

            </div>
            <div class="col-sm-10">
              <button class="btn btn-active">Lưu thay đổi</button>
            </div>
          </div>
        </div>
      </form>
    </div>

  </div>
</div>
<span id="notification"></span>
</div>

<script type="text/javascript">
  $("#show_password").click(function(){
    if ($("#show_password").is(":checked")){
      $("#old_password")[0]['type'] = 'input';
      $("#new_password")[0]['type'] = 'input';
      $("#retype_new_password")[0]['type'] = 'input';
    } else {
      $("#old_password")[0]['type'] = 'password';
      $("#new_password")[0]['type'] = 'password';
      $("#retype_new_password")[0]['type'] = 'password';
    }
  });
  var notification = $("#notification").kendoNotification().data("kendoNotification");
  var validator = $("#fm").kendoValidator().data("kendoValidator");

  $("form").submit(function(event) {
   event.preventDefault();
   if (validator.validate()) {
     if ($("#new_password").val().length < 6 || $("#retype_new_password").val().length < 6){
       notification.show(" Xác nhận mật khẩu mới không đúng", "error");
     } else if ($("#new_password").val() != $("#retype_new_password").val()){
       notification.show(" Xác nhận mật khẩu mới không đúng", "error");
     } else if (!checkOldPassword(11111, $("#old_password").val())){
      notification.show(" Xác nhận mật khẩu mới không đúng", "error");
     } else {
       changePassword($("#old_password").val(), $("#new_password").val(), $("#retype_new_password").val());
     }
   }
 });

  function checkOldPassword(userId, oldPassword){
    var result = false;
    $.ajax({
     url: "${api.server}" + "/checkOldPassword",
     type: "GET",
     dataType: "json",
     data: {
       userId: userId,
       oldPassword: oldPassword,
     },
     success: function(res) {
       if (res.result == 'true'){
         result = true;
       }
     }
   });
    return result;
  };

  function changePassword(oldPassword, newPassword, retypeNewPassword){
    $.ajax({
     url: "${api.server}" + "/changePassword",
     type: "POST",
     dataType: "json",
     data: {
       oldPassword: oldPassword,
       newPassword: newPassword,
       retypeNewPassword: retypeNewPassword
     },
     success: function(res) {
       notification.show({
         message: "Đổi mật khẩu thành công"
       }, "success");
     },
     error: function(res) {
       notification.show({
         message: "Xẩy ra lỗi, vui lòng thử lại"
       }, "error");
     }
   });
  }

  $(function(){
    $.fn.editable.defaults.mode = 'inline';
    /*$('#username').editable({
      url:"edit.jsf",
      ajaxOptions: {
        type: 'post',
        dataType: 'json'
      },
      success: function(response, newValue) {
        if(!response.success) return response.msg;
      }
    });
    */
    $('#companyName').editable({
      url: 'post.php',
      ajaxOptions:{
        type:'post',
        dataType: "json"
      },
      success: function(data) {
       alert(data);
     },
     error:function(xhr) {
      if(xhr.status == 500) return 'Internal server error'; 
      console.log(xhr.status); 
    }
  });
    $('#address').editable({
      url: 'post.php',
      ajaxOptions:{
        type:'post',
        dataType: "json"
      },
      success: function(data) {
       alert(data);
     },
     error:function(xhr) {
      if(xhr.status == 500) return 'Internal server error';  
    }
  });
    $('#city').editable({
      url: 'post.php',
      ajaxOptions:{
        type:'post',
        dataType: "json"
      },
      success: function(data) {
       alert(data);
     },
     error:function(xhr) {
      if(xhr.status == 500) return 'Internal server error';  
    }
  });
    $('#district').editable({
      url: 'post.php',
      ajaxOptions:{
        type:'post',
        dataType: "json"
      },
      success: function(data) {
       alert(data);
     },
     error:function(xhr) {
      if(xhr.status == 500) return 'Internal server error';  
    }
  });
    $('#wards').editable({
      url: 'post.php',
      ajaxOptions:{
        type:'post',
        dataType: "json"
      },
      success: function(data) {
       alert(data);
     },
     error:function(xhr) {
      if(xhr.status == 500) return 'Internal server error';  
    }
  });
    $('#phone').editable({
      url: 'post.php',
      ajaxOptions:{
        type:'post',
        dataType: "json"
      },
      success: function(data) {
       alert(data);
     },
     error:function(xhr) {
      if(xhr.status == 500) return 'Internal server error';  
    }
  });
    $('#email').editable({
      url: 'post.php',
      ajaxOptions:{
        type:'post',
        dataType: "json"
      },
      success: function(data) {
       alert(data);
     },
     error:function(xhr) {
      if(xhr.status == 500) return 'Internal server error';  
    }
  });
    $('#represntative').editable({
      url: 'post.php',
      ajaxOptions:{
        type:'post',
        dataType: "json"
      },
      success: function(data) {
       alert(data);
     },
     error:function(xhr) {
      if(xhr.status == 500) return 'Internal server error';  
    }
  });

    $('#editCompanyName').click(function(e) {
      e.stopPropagation();
      $('#companyName').editable('toggle');
    });

    $('#editAddress').click(function(e) {
      e.stopPropagation();
      $('#address').editable('toggle');
    });

    $('#editCity').click(function(e) {
      e.stopPropagation();
      $('#city').editable('toggle');
    });

    $('#editDistrict').click(function(e) {
      e.stopPropagation();
      $('#district').editable('toggle');
    });

    $('#editWards').click(function(e) {
      e.stopPropagation();
      $('#wards').editable('toggle');
    });

    $('#editPhone').click(function(e) {
      e.stopPropagation();
      $('#phone').editable('toggle');
    });

    $('#editEmail').click(function(e) {
      e.stopPropagation();
      $('#email').editable('toggle');
    });

    $('#editRepresntative').click(function(e) {
      e.stopPropagation();
      $('#represntative').editable('toggle');
    });
  });
</script>
