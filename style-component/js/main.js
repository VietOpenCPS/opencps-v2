function showMessageToastr(type, message){
	toastr.options = {
		"closeButton": true,
		"debug": false,
		"progressBar": true,
		"positionClass": "toast-top-right",
		"onclick": null,
		"showDuration": "400",
		"hideDuration": "1000",
		"timeOut": "4000",
		"extendedTimeOut": "1000",
		"showEasing": "swing",
		"hideEasing": "linear",
		"showMethod": "fadeIn",
		"hideMethod": "fadeOut"
	};
   toastr[type](message);
}

function showMessageByAPICode (code) {

	var message,status;

	switch (code) {

		case 200:
			message = "Yêu cầu của bạn được xử lý thành công!";
			status = "success";
			break;

		case 401:
			message = "Yêu cầu của bạn xử lý thất bại, chưa đăng nhập vào hệ thống!!!";
			status = "error";
			break;

		case 403:
			message = "Yêu cầu của bạn xử lý thất bại, không có quyền thay đổi dữ liệu!!!";
			status = "error";
			break;

		case 404:
			message = "Yêu cầu của bạn xử lý thất bại, không tìm thấy tài nguyên!!!";
			status = "error";
			break;

		case 405:
			message = "Yêu cầu không được phép xử lý!!!";
			status = "error";
			break;

		case 409:
			message = "Yêu cầu của bạn xử lý thất bại, xung đột dữ liệu";
			status = "error";
			break;

		case 500:
			message = "Yêu cầu của bạn xử lý thất bại, lỗi hệ thống";
			status = "error";
			break;

		default:
			message = "Lỗi kết nối!!!";
			status = "error";
			break;
	}

	showMessageToastr(status, message);

}