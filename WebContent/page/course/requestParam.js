var baseUrl = "localhost:8080";
const requestUrl = {
    addNotice: "/blackboard/teacher/addNotice",
    deleteNotice: "/blackboard/teacher/deleteNotice",
    searchNotice: "/blackboard/system/searchNotice",
    modifyNotice: "/blackboard/teacher/modifyNotice",
    searchCourse: "/blackboard/system/searchCourse",
    modifyCourse: "/blackboard/admin/modifyCourse",
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
    courseNotice: "/blackboard/page/course/courseNotice.html"
};

const requestHeader = {
    'token': localStorage.getItem("token"),
    // 'Content-Type': 'application/x-www-form-urlencoded'
};
