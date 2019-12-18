var baseUrl = "localhost:8080";
var requestUrl = {
    addNotice:"/blackboard/teacher/addNotice",
    deleteNotice:"/blackboard/teacher/deleteNotice",
    searchNotice:"/blackboard/system/searchNotice",
    modifyNotice:"/blackboard/teacher/modifyNotice",
    searchCourse:"/blackboard/system/searchCourse",
    modifyCourse:"/blackboard/admin/modifyCourse"
};
var requestType = {
    post:"post",
    get:"get"
};
console.log(requestUrl.addNotice);
