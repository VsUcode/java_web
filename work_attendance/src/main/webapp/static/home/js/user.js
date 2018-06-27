/**
 * Created by Administrator on 2017/08/12.
 */

$(function(){
   $.ajax({
       type:"POST",
       url:"/user/userinfo",
       dataType:"json",
       contentType:"application/json",
       data:{},
       success:function (data) {
           $(".user_name").html(data.realName);

       }
   }) ;
});