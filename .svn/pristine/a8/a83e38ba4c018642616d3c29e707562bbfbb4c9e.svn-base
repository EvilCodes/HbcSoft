var FormEditable = function () {

    $.mockjaxSettings.responseTime = 500;

    var log = function (settings, response) {
        var s = [],
            str;
        s.push(settings.type.toUpperCase() + ' url = "' + settings.url + '"');
        for (var a in settings.data) {
            if (settings.data[a] && typeof settings.data[a] === 'object') {
                str = [];
                for (var j in settings.data[a]) {
                    str.push(j + ': "' + settings.data[a][j] + '"');
                }
                str = '{ ' + str.join(', ') + ' }';
            } else {
                str = '"' + settings.data[a] + '"';
            }
            s.push(a + ' = ' + str);
        }
        s.push('RESPONSE: status = ' + response.status);

        if (response.responseText) {
            if ($.isArray(response.responseText)) {
                s.push('[');
                $.each(response.responseText, function (i, v) {
                    s.push('{value: ' + v.value + ', text: "' + v.text + '"}');
                });
                s.push(']');
            } else {
                s.push($.trim(response.responseText));
            }
        }
        s.push('--------------------------------------\n');
        $('#console').val(s.join('\n') + $('#console').val());
    }

    var initAjaxMock = function () {
        //ajax mocks

        $.mockjax({
            url: '/post',
            response: function (settings) {
                log(settings, this);
            }
        });

        $.mockjax({
            url: '/error',
            status: 400,
            statusText: 'Bad Request',
            response: function (settings) {
                this.responseText = 'Please input correct value';
                log(settings, this);
            }
        });

        $.mockjax({
            url: '/status',
            status: 500,
            response: function (settings) {
                this.responseText = 'Internal Server Error';
                log(settings, this);
            }
        });

        $.mockjax({
            url: '/groups',
            response: function (settings) {
                this.responseText = [{
                        value: 0,
                        text: 'Guest'
                    }, {
                        value: 1,
                        text: 'Service'
                    }, {
                        value: 2,
                        text: 'Customer'
                    }, {
                        value: 3,
                        text: 'Operator'
                    }, {
                        value: 4,
                        text: 'Support'
                    }, {
                        value: 5,
                        text: 'Admin'
                    }
                ];
                log(settings, this);
            }
        });

    }

    var initEditables = function () {

        //set editable mode based on URL parameter
        if (App.getURLParameter('mode') == 'inline') {
            $.fn.editable.defaults.mode = 'inline';
            $('#inline').attr("checked", true);
            jQuery.uniform.update('#inline');
        } else {
            $('#inline').attr("checked", false);
            jQuery.uniform.update('#inline');
        }

        //global settings 
        $.fn.editable.defaults.inputclass = 'form-control';
        $.fn.editable.defaults.url = '/post';

        
        for(var i=0;i<60;i++){
        	var zhi = $("#dw"+i).val();
        	var num=0;
        	$("[name='kmbm']").each(function(){
        	    var zz = $(this).val();
        	    if(zz.indexOf(zhi)>=0&&zhi!=zz){
        	    	num=1
        	    }
        	    });
        	if(num==0){
        		$('#dd'+i).parent().append("<i onclick='clicka(this)' id='bi' class='fa fa-pencil'></i>");
        		$('#dd'+i).editable({
                    url: '/post',
                    type: 'text',
                    pk: 1,
                    name: 'username',
                    title: 'Enter username',
                    validate: function (value) {
                        if ($.trim(value) == '') return '输入框不能为空';
                        var reg = /^\d+(\.\d+)?$/;
                        if(!reg.test(value)) return '只能输入正整数或小数';
	        			var oldv = $(this).next().next().val();//获取旧值
	        			var id = $(this).next().next().next().val();
	        			var xzkmbm = $(this).prev().val();//获取当前编码值
	        			if(Number(value)!=Number(oldv)){//判断值是否修改
	        				
	        				$("[name='kmbms']").each(function(){
	        	        	    var zz = $(this).val();
	        	        	    var dqzhi = $(this).next().html();
	        	        	    if(xzkmbm.indexOf(zz)>=0&&xzkmbm!=zz){
	        	        	    	$(this).next().html(addNum(addNum(Number(dqzhi),-Number(oldv)),Number(value)));
	        	        	    	$(this).next().next().val(addNum(addNum(Number(dqzhi),-Number(oldv)),Number(value)));
	        	        	    }
	        	        	 });
	        				$(this).next().next().val(value);
	        				ajax(id,value);
	        			}
        			    
                    }
                });
        		
        		$("#dw"+i).parent().parent().find('td').css("background","rgb(252,249,215)");
        		
        	}
        }
       
      

       

       

       

        
        

       


    }

    return {
        //main function to initiate the module
        init: function () {

            // inii ajax simulation
            initAjaxMock();

            // init editable elements
            initEditables();
            
            // init editable toggler
            $('#enable').click(function () {
                $('#user .editable').editable('toggleDisabled');
            });

            // init 
            $('#inline').on('change', function (e) {
                if ($(this).is(':checked')) {
                    window.location.href = 'form_editable.html?mode=inline';
                } else {
                    window.location.href = 'form_editable.html';
                }
            });

            // handle editable elements on hidden event fired
            $('#user .editable').on('hidden', function (e, reason) {
                if (reason === 'save' || reason === 'nochange') {
                    var $next = $(this).closest('tr').next().find('.editable');
                    if ($('#autoopen').is(':checked')) {
                        setTimeout(function () {
                            $next.editable('show');
                        }, 300);
                    } else {
                        $next.focus();
                    }
                }
            });


        }

    };

}();