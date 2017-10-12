(function ($) {
    $.fn.extend({
        "openDialog": function(type,title,id){
    		var url="";
    		if(type=="add_dialog"){
    			url="user/user_add.jsp";
    		}else if(type=="edit_dialog"){
    			url = "user/selectUserById?id="+id;
    		}
    		$('#dd').dialog({
    		    title: title,
    		    width: 500,
    		    height: 300,
    		    closed: false,
    		    cache: false,
    		    href: url,
    		    modal: true,
    		    resizable : true
    		});
    	}
    });
    //默认参数
//    var defaluts = {
//        foreground: 'red',
//        background: 'yellow'
//    };
})(window.jQuery);