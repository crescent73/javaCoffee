<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Blackboard</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <style type="text/css">
        input[type=password] {
            height: 25px;
            border: 1px solid #D4D4D4;
            color: #202124;
            font-size: 14px;
            line-height: 48px;
            border-radius: 3px;
        }
    </style>

    <!-- Font Awesome -->
    <link rel="stylesheet" href="/blackboard/page/plugins/fontawesome-free/css/all.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <!-- 引用阿里的字体图标库 -->
    <link rel="stylesheet" type="text/css" href="http://at.alicdn.com/t/font_1546252_v6mv4erx9zg.css">
    <link rel="stylesheet" type="text/css" href="http://at.alicdn.com/t/font_1554078_3veo6ro6da.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/blackboard/page/dist/css/adminlte.min.css">
    <!-- Google Font: Source Sans Pro -->
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
    <!-- ./wrapper -->
    <!-- 引用vue -->
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <!-- 模态框插件 -->
    <script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>

</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper" id="mainpage">

    <!-- Navbar -->
    <div style="border-style:none none double none;border-color:#D4D4D4;">
        <nav class="navbar navbar-expand navbar-white navbar-light">
            <!-- Left navbar links -->
            <span class="iconfont iconxiaoheiban" style="font-size: 30px;margin-left: 10px;"></span>
            <span class="brand-text" style="font-family:Ink Free;font-size: 30px;margin-left: 10px;">blackboard</span>
            <ul class="navbar-nav">
                <li class="nav-item d-none d-sm-inline-block">
                    <a href="#" class="nav-link">&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;Home</a>
                </li>
            </ul>
            <!-- Right navbar links -->
            <ul class="navbar-nav ml-auto">
                <li class="nav-item  nav-sidebar">
                    <a class="nav-link" data-toggle="dropdown" href="#">
                        <span class="iconfont iconyonghu"></span>
                        <p>用户名</p>
                    </a>
                    <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
                        <button class="dropdown-item" data-toggle="modal" data-target=".myModal">
                            <span class="iconfont iconchakanxinxi  mr-2"></span>查看信息
                        </button>

                        <div class="dropdown-divider"></div>
                        <button class="dropdown-item" data-toggle="modal" data-target=".myModal1">
                            <span class="iconfont iconxiugaixinxi  mr-2"></span>修改密码
                        </button>
                    </div>
                </li>
                <li class="nav-item nav-sidebar">
                    <a class="nav-link" data-widget="control-sidebar" data-slide="true" href="#">
                        <span class="iconfont iconicon-out" @click="logout"></span>
                    </a>
                </li>
            </ul>

        </nav>
    </div>
    <!-- /.navbar -->
    <!-- 模态框1 -->
    <div class="modal fade myModal" tabindex="-1" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">

                <!-- 模态框头部 -->
                <div class="modal-header">
                    <h4 class="modal-title">详细信息</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- 模态框主体 -->
                <div class="modal-body">
                    姓名:
                    <br>
                    工号:
                    <br>
                    邮箱:
                    <br>
                    职称:
                    <br>
                    学院:
                </div>

                <!-- 模态框底部 -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                </div>

            </div>
        </div>
    </div>

    <!-- 模态框2 -->
    <div class="modal fade myModal1" tabindex="-1" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">

                <!-- 模态框头部 -->
                <div class="modal-header">
                    <h4 class="modal-title">修改密码</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- 模态框主体 -->
                <div class="modal-body">
                    <div>
                        <form id="testForm">
                            &nbsp;&nbsp;请输入原密码：<input type="password" name="old" id="oldPass" placeholder="原密码"
                                                      required/>
                            <input type="hidden" name="userType" id='1' value="请输入原密码"/>
                            <br><br>
                            &nbsp;&nbsp;请输入新密码：<input type="password" name="new" id="newPass" placeholder="新密码"
                                                      required/>
                            <input type="hidden" name="userType" id='2' value="请输入原密码"/>
                            <br><br>
                            &nbsp;&nbsp;请确认新密码：<input type="password" name="new1" id="newPass1" placeholder="确认密码"
                                                      required/>
                            <input type="hidden" name="userType" id='3' value="请确认原密码"/>
                            <input type="hidden" name="userType" id='4' value="前后密码不一致！"/>
                            <br><br>
                        </form>
                    </div>
                </div>

                <!-- 模态框底部 -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="check()">提交</button>
                </div>

            </div>
        </div>
    </div>

    <!-- Content Wrapper. Contains page content -->
    <div style="background-color:#F7F7F7;" id="content">
        <!--  课程列表 -->
        <section class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1><font style="vertical-align: inherit;"><font
                                style="vertical-align: inherit;">课程列表</font></font></h1>
                    </div>
                </div>
            </div><!-- /.container-fluid -->
        </section>

        <section class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-3 col-6" v-for="notice in notices" v-bind:key="notice.id"
                         @click="click(notice,$event)" onclick="window.open('#')">
                        <!-- small card -->
                        <div class="small-box bg-info">
                            <div class="inner">

                                <h3>
                                    <i class="iconfont iconshuben"></i>
                                    <font style="vertical-align: inherit;"><font style="vertical-align: inherit;">{{
                                        notice.courseName}}</font>
                                    </font>
                                </h3>

                                <p><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">课程号：{{
                                    notice.courseId}}</font></font></p>
                                <p><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">课程类型：{{
                                    notice.courseType}}</font></font></p>

                            </div>
                            <div class="icon">

                            </div>
                            <a href="#" class="small-box-footer"><font style="vertical-align: inherit;"><font
                                    style="vertical-align: inherit;">
                            </font></font><i></i>
                            </a>
                        </div>
                    </div>
                </div>
            </div><!-- /.container-fluid -->
        </section>
    </div>
    <!-- Main Footer -->
    <footer class="">
        <!-- To the right -->
        <div class="float-right d-none d-sm-inline">
            Anything you want&nbsp;&nbsp;&nbsp;
        </div>
        <!-- Default to the left -->
        <strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Copyright &copy; 2014-2019 <a href="https://adminlte.io">AdminLTE.io</a>.</strong>
        All rights reserved.
    </footer>

</div>

<!-- jQuery -->
<script src="/blackboard/page/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="/blackboard/page/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="/blackboard/page/dist/js/adminlte.min.js"></script>
<script type="text/javascript">
    var app = new Vue({
        el: '#content',
        data() {
            return {
                notices: [
                    {
                        id: 1,
                        courseId: "1",
                        courseName: "title1",
                        courseType: "type1"
                    },
                    {
                        id: 2,
                        courseId: "2",
                        courseName: "title2",
                        courseType: "type2"
                    },
                    {
                        id: 3,
                        courseId: "3",
                        courseName: "title3",
                        courseType: "type3"
                    },
                    {
                        id: 4,
                        courseId: "4",
                        courseName: "title4",
                        courseType: "type4"
                    },
                    {
                        id: 5,
                        courseId: "5",
                        courseName: "title5",
                        courseType: "type5"
                    }
                ],
                noticeForm: {
                    noticeId: "",
                    courseId: "5",
                    courseName: "",
                    courseType: ""
                }
            }
        },
        methods: {
            init() {
                let that = this;
                $.ajax({
                    type: "post",
                    url: "/blackboard/system/searchNotice",
                    data: this.noticeForm,
                    success: function (res) {
                        console.log(res);
                        that.notices = res.data;
                    },
                    error: function (res) {
                        console.log(res)
                    }
                })
            },
            click(notice, event) {
                console.log(notice);
                console.log(event);

            }
        },
        created() {
        },
        mounted() {
        }
    });

    // var x = document.getElementById("oldPass");
    // var y = document.getElementById("newPass");
    // var z = document.getElementById("newPass1");
    // var x1 = document.getElementById("1");
    // var y1 = document.getElementById("2");
    // var z1 = document.getElementById("3");
    // var z2 = document.getElementById("4");
    // function check(){
    // 		if(x.value==""){
    // 			alert("请输入原密码！");
    // 			x.value=y.value=z.value="";
    // 		}
    //
    // 		else if(y.value==""){
    // 			alert("请输入新密码！");
    // 			x.value=y.value=z.value="";
    // 		}
    // 		else if(z.value==""){
    // 			alert("请确认密码！");
    // 			x.value=y.value=z.value="";
    // 		}
    // 		else if(y.value!=z.value){
    // 			alert("前后密码输入不一致！");
    // 			x.value=y.value=z.value="";
    // 		}
    //
    // 	}
    // function checkInput(input){
    // 	if (input.value=="") {
    //         input.setCustomValidity("请输入原密码！");
    //     } else {
    //         input.setCustomValidity("");
    //     }
    //
    // }
</script>
</body>
</html>