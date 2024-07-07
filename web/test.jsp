<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Bootstrap Notify Example</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
  <div class="container mt-5">
    <h2>Form Example</h2>
    <form id="myForm">
      <div class="form-group">
        <label for="name">Name:</label>
        <input type="text" class="form-control" id="name" required>
      </div>
      <button type="submit" class="btn btn-primary">Submit</button>
    </form>
  </div>

    <script src="js/jquery.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrap-notify.min.js"></script>

  <script>
    $(document).ready(function(){
      $("#myForm").submit(function(event){
        event.preventDefault(); // Ngăn chặn submit form mặc định

        // Giả sử xử lý form thành công
        let success = true; // Thay đổi thành false để test thông báo lỗi

        if (success) {
          $.notify({
            message: '<strong>Thành công!</strong> Dữ liệu đã được gửi.' 
          },{
            type: 'success',
            placement: {
              from: "top",
              align: "right"
            },
            delay: 2000
          });
        } else {
          $.notify({
            message: '<strong>Lỗi!</strong> Vui lòng kiểm tra lại dữ liệu.'
          },{
            type: 'danger',
            placement: {
              from: "top",
              align: "right"
            },
            delay: 2000
          });
        }
      });
    });
  </script>
</body>
</html>