<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Venus Pet System</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="style.css" rel="stylesheet">

</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
					<a class="sidebar-brand d-flex align-items-center justify-content-center" href="#">
	                <div class="sidebar-brand-icon rotate-n-15">
	                    <i class="fas fa-laugh-wink"></i>
	                </div>
                	<div class="sidebar-brand-text mx-3">Venus Pet Salon</div>
           			</a>
                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                     <!-- Content Row -->
                    <div class="col">

                        <!-- Content Column -->
                        <div class="col mb-4">

                            <!-- Project Card Example -->
                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">Register Staff</h6>
                                </div>
                                <div class="card-body">
                                     <form class="user" action="AddServiceController" method="post">
                                
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-user" id="custName"
                                        placeholder="Name"y>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-user" id="custNum"
                                        placeholder="Phone Number">
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-user" id="custPassword"
                                        placeholder="Password">
                                </div>       
                                <div class="form-group">Admin<br>
	                                <input type="radio" id="male" name="gender" value="male">
									<label for="male">Yes</label><br>
									<input type="radio" id="female" name="gender" value="female">
									<label for="female">No</label><br>
                                </div>                        
                                	<input type="submit" value="Register" class="btn btn-primary btn-user btn-block">
                                <br>
                            </form>
                                </div>
                            </div>

                            
                        </div>
                    </div>

                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; Venus Pet System</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="login.html">Logout</a>
                </div>
            </div>
        </div>
    </div>

    <script src="js.js"></script>

</body>
</html>