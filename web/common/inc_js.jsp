<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="/static/res/jquery/jquery.js"></script>
<script type="text/javascript" src="/static/res/jquery/jquery.form.js"></script>
<script type="text/javascript" src="/static/res/jquery/jquery-ui.min.js"></script>
<script type="text/javascript" src="/static/res/bs/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/static/res/bs/js/bootstrap-table.js"></script>
<script type="text/javascript" src="/static/res/bs/js/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="/static/res/bs/js/bootstrap-table-fixed-columns.js"></script>
<script type="text/javascript" src="/static/res/AdminLTE/plugins/pace/pace.min.js"></script>
<script type="text/javascript" src="/static/res/chok/js/chok.base.js"></script>
<script type="text/javascript" src="/static/res/chok/js/chok.validator.js"></script>
<script type="text/javascript" src="/static/res/chok/js/chok.form.js"></script>
<script type="text/javascript" src="/static/res/chok/js/chok.view.js"></script>
<script type="text/javascript" src="/static/res/chok/js/chok.nav.js"></script>
<script type="text/javascript">
var $ctx="${ctx}";
function getGlobalHeight(type) {
	if (type=='table') {
		var minH = 300;
		var newH = $(window).height() - 373;
	   	return newH<minH?minH:newH;
	}
	else if (type=='tree') {
		return $(window).height() - 35;
	}
}
function ajaxOnLoadError(status, data) {
    if(status == '0') {  
        if (confirm('会话已过期, 请重新登录。')) {  
            window.location.href = $ctx+'/login.jsp';              
        }  
    } 
}
</script>