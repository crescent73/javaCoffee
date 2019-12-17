<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>欢迎使用</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta content="text/html;charset=utf-8">

<style type="text/css">
	/* 大标题属性 */
	.title_style{
		color:#A4D3EE;
		font-family:Wawati SC;
		font-size:20px;
		font-weight:bold;
		position:fixed;
		right:230px;
		top:100px;
	}
	
	/* 上方色块属性 */
	.top_block{
		background-color:#F0F8FF;
		height:60px;
		position:fixed;
		left:0px;right:0px;top:0px;
		border-style:none none solid none;
		border-width:1.5px;
		border-color:#D1EEEE;
	}
	
	/* 单词Blackboard属性 */
	.bb{
		color:#A4D3EE;
		font-family:Ink Free;
		font-size:35px;
		font-weight:bold;
		position:fixed;
		left:25px;top:-25px;
	}
	
	/* "查看帮助"属性 */
	.help{
		color:#A4D3EE;
		font-family:SimSun;
		font-size:20px;
		font-weight:bold;
		position:fixed;
		right:25px;
		top:-5px;
	}
	
	/* 登录板块属性 */
	.activityBox{
		border-style:solid solid solid solid;
		border-color:#D1EEEE;
		border-radius: 5px;
		position:fixed;
		right:200px;
		top:150px;
		height:280px;
		
	}
	
	
	.switch{
		 height:30px;
		 width:110px;
		 text-align:center;
		 padding-top:10px;
		 display:inline-block;
		 vertical-align:top;
	}
	
	.num{
		 padding-left:60px;
		 padding-top:2px;
		 height:25px;
		 width:250px;
	}
	
	/*修改输入框颜色背景色等*/
	input[type=text] {
	    height: 25px;
	    border: 1px solid #D1EEEE;
	    color: #202124;
	    font-size: 14px;
	    line-height: 48px;
	    border-radius: 3px;
    }
    input[type=password] {
	    height: 25px;
	    border: 1px solid #D1EEEE;
	    color: #202124;
	    font-size: 14px;
	    line-height: 48px;
	    border-radius: 3px;
	}

	#text{
		border: 1px solid #D1EEEE;
    	color: #202124;
    	border-radius: 3px;
    	width:115px;
    	line-height: 48px;
	}
	
	.submit {
	    background-color: #66CCFF; 
	    border: none;
	    color: white;
	    padding: 15px 32px;
	    text-align: center;
	    text-decoration: none;
	    display: inline-block;
	    font-size: 16px;
	    border-radius: 2px;
	}

	.clear {
	    background-color: #33FF66; 
	    border: none;
	    color: white;
	    padding: 15px 32px;
	    text-align: center;
	    text-decoration: none;
	    display: inline-block;
	    font-size: 16px;
	    border-radius: 2px;
	}

	a:link {text-decoration: none}

	.forget{
		font-size:13px;
		positon:fixed;
		padding-left:280px;
		padding-top:28px;
	}
	
</style>

<script type="text/javascript">
function setTab(name,cursel,n){   //方法 setTab()及其三个参数
    for(var i=1;i<=n;i++){              //循环变量 n是总共的切换数量
        var menu=document.getElementById(name+i); //menu取出各个标题li的id值
        var con=document.getElementById("con_"+name+"_"+i); //con是指内容里的id
        con.style.display=i==cursel?"block":"none"; //判断对应关系,
    }}
 
function change(id){
	var stu=document.getElementById("Student");
	var tea=document.getElementById("Teacher");
	var adm=document.getElementById("Admin");
	
	this.style.color="grey";
	
	if(id==1){
		tea.style.color="black";
		adm.style.color="black";
	}
	
	else if(id==2){
		stu.style.color="black";
		adm.style.color="black";
	}
	
	else{
		stu.style.color="black";
		adm.style.color="black";
	}
	/*switch id{
	case 1:
		tea.style.color="black";
		adm.style.color="black";
		break;
	case 2:
		stu.style.color="black";
		adm.style.color="black";
		break;
	case 3:
		stu.style.color="black";
		adm.style.color="black";
		break;
	}*/
}

</script>

</head>

<body style="background-image:url('pictures/tj.jpg');background-position:10% 50%;background-size:450px 280px;background-repeat:no-repeat;">
	<div>
		<!-- 上方色块 -->
		<div class="top_block">
			<p class="bb">Blackboard</p>
			<p class="help"><a href="http://www.baiu.com">查看帮助</a></p>
		</div>
		<br></br>
		<!-- 登录块 -->
		<div>
			<div class="title_style">欢迎使用Blackboard智慧教学系统</div>
			<div class="activityBox">
				<div class="activityTab">
	             		<div id="Student" class="switch" onmousemove="setTab('bb',1,3),this.style.color='grey'" onmouseout="this.style.color='black'">学生</div>
		           		<div id="Teacher"class="switch" onmousemove="setTab('bb',2,3),this.style.color='grey'" onmouseout="this.style.color='black'" >教师</div>
	              		<div id="Admin"  class="switch" onmousemove="setTab('bb',3,3),this.style.color='grey'" onmouseout="this.style.color='black'" >管理员</div>
       			</div>
       			
        		<div style="display: block;border-style:solid none none  none;border-color:#D1EEEE;" id="con_bb_1">
        			<p style="padding-left:60px;font-size:13px;color:grey;">学生登录</p>
        			<form action="/blackboard/system/login">
	        			<div class="num">
	        				账号:
	        				<input type="text" name="num" placeholder="输入账号" />
	        			</div>
	        			<br>
	        			<div class="num">
	        				密码: 
	        			<input type="password" name="pass" placeholder="输入密码"/>
	        			<input type="hidden" name="userType" value='3'/>
	        			</div>
	        			<br>
	        			<br>
	        			<div class="num">
	        				<input type="submit" class="submit" value='提交'/>
	        				&nbsp;&nbsp;
	        				<button type="button" class="clear">重置</button>
	        			</div>
        			</form>
        			
        		</div> 
    			<div style="display: none;border-style:solid none none  none;border-color:#D1EEEE;" id="con_bb_2">
    				<p style="padding-left:60px;font-size:13px;color:grey;">教师登录</p>
    				<form action="/blackboard/system/login">
	    				<div class="num">
	        				账号:
	        				<input type="text" name="num" placeholder="输入账号" />
	        			</div>
	        			<br>
	        			<div class="num">
	        				密码: 
	        			<input type="password" name="pass" placeholder="输入密码"/>
	        			<input type="hidden" name="userType" value='2'/>
	        			</div>
	        			<br>
	        			<br>
	        			<div class="num">
	        				<input type="submit" class="submit" value='提交'/>
	        				&nbsp;&nbsp;
	        				<button type="button" class="clear">重置</button>
	        			</div>
    				</form>
        			
    			</div>
    			
    			<div style="display: none;border-style:solid none none  none;border-color:#D1EEEE;" id="con_bb_3">
    				<p style="padding-left:60px;font-size:13px;color:grey;">管理员登录</p>	
        			<form action="/blackboard/system/login">
	        			<div class="num">
	        				账号:
	        				<input type="text" name="num" placeholder="输入账号" />
	        			</div>
	        			<br>
	        			<div class="num">
	        				密码: 
	        			<input type="password" name="pass" placeholder="输入密码"/>
	        			<input type="hidden" name="userType" value='1'/>
	        			</div>
	        			<br>
	        			<br>
	        			<div class="num">
	        				<input type="submit" class="submit" value='提交'/>
	        				&nbsp;&nbsp;
	        				<button type="button" class="clear">重置</button>
	        			</div>
        			</form>	
        			
    			</div>
    			<br>
    			<br>
    			
			</div>
			
		</div>
		<div style="font-size:15px;color:grey;position:fixed;top:520px;left:450px;">Copyright © 2019 计科1701 wyz. All rights reserved. </div>
	</div>

</body>


</html>