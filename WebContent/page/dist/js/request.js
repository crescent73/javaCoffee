function request({url,type,data,dataType,contentType,processData}){
    let self = this;
    return new Promise(((resolve, reject) => {
        $.ajax({
            url:url,
            type:type,
            data:data,
            dataType:dataType,
            contentType:contentType,
            processData:processData,
            headers:{
                token:localStorage.getItem("token")
            },
            success(res){
                resolve.call(self,res);//执行成功函数
            },
            error(res){
                reject.call(res)
            }
        })
    }))
}
