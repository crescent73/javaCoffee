var baseUrl = "localhost:8080";
const requestUrl = {
    addNotice: "/blackboard/teacher/addNotice",
    deleteNotice: "/blackboard/teacher/deleteNotice",
    searchNotice: "/blackboard/system/searchNotice",
    modifyNotice: "/blackboard/teacher/modifyNotice",
    searchCourse: "/blackboard/system/searchCourse",
    modifyCourse: "/blackboard/admin/modifyCourse",
    addFile:"/blackboard/teacher/addFile",
    searchFile:"/blackboard/system/searchFile",
    deleteFile:"/blackboard/teacher/deleteFile",
    downloadAttachment:"/blackboard/system/downloadAttachment",
    deleteAttachment:"/blackboard/teacher/deleteAttachment",
    logout: "/blackboard/system/logout"
};
const requestType = {
    post: "post",
    get: "get"
};

const requestHref = {
    courseNotice: "/blackboard/page/course/courseNotice.html",
    courseFile:"/blackboard/page/course/courseFile.html",
    login:"/blackboard/",
    course:"/blackboard/page/course/course.html"
};

const requestHeader = {
    'token': localStorage.getItem("token"),
    // 'Content-Type': 'application/x-www-form-urlencoded'
};

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) { return decodeURIComponent(r[2]); }
    return null; //返回参数值
}

const Toast = Swal.mixin({
    toast: true,
    position: 'top-end',
    showConfirmButton: false,
    timer: 1500,
    grow: 'row'
});

