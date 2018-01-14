$.fn.MyDataTable = function (columns, url,colmDef,paramData,fnDrawCallback) {
    //提示信息
    var lang = {
        "sProcessing": "处理中...",
        "sLengthMenu": "每页 _MENU_ 项",
        "sZeroRecords": "没有匹配结果",
        "sInfo": "当前显示第 _START_ 至 _END_ 项，共 _TOTAL_ 项。",
        "sInfoEmpty": "当前显示第 0 至 0 项，共 0 项",
        "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
        "sInfoPostFix": "",
        "sSearch": "搜索:",
        "sUrl": "",
        "sEmptyTable": "表中数据为空",
        "sLoadingRecords": "载入中...",
        "sInfoThousands": ",",
        "paginate": {
            "previous": '上一页',
            "next": '下一页',
            "page": "第",
            "pageOf": "至"
        },
        "oAria": {
            "sSortAscending": ": 以升序排列此列",
            "sSortDescending": ": 以降序排列此列"
        }
    };

    //初始化表格
    var table = this.dataTable({
        language: lang,  //提示信息
        autoWidth: true,  //禁用自动调整列宽
        stripeClasses: ["odd", "even"],  //为奇偶行加上样式，兼容不支持CSS伪类的场合
        processing: true,//载入数据的时候是否显示“载入中”
        serverSide: true,  //启用服务器端分页
        searching: false,  //禁用原生搜索
        orderMulti: false,  //启用多列排序
        ordering: false,  //取消默认排序查询,否则复选框一列会出现小箭头
        stateSave: !0,
        destroy:true,
        //renderer: "bootstrap",  //渲染样式：Bootstrap和jquery-ui
        columnDefs: colmDef||[],
        lengthMenu: [[10, 20, 40], [10, 20, 40]],
        pageLength: 10,
        ajax: function (data, callback, settings) {
            //封装请求参数
            paramData = paramData||{};
            paramData.rows = data.length;//页面显示记录条数，在页面显示每页显示多少项的时候
            paramData.page = (data.start / data.length) + 1;//当前页码
            $.ajax({
                type: "GET",
                url: url,
                cache: false,  //禁用缓存
                dataType: "json",
                data: paramData  //传入组装的参数
            }).done(function (result) {
                if(result.flag){
                    //封装返回数据
                    var returnData = {};
                    returnData.recordsTotal = result.data.total;//返回数据全部记录
                    returnData.recordsFiltered = result.data.total;//后台不实现过滤功能，每次查询均视作全部结果
                    returnData.data = result.data.list?result.data.list:[];//返回的数据列表
                    //console.log(returnData);
                    //调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
                    //此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
                    callback(returnData);
                }else {
                    //封装返回数据
                    var returnData = {};
                    returnData.recordsTotal = 0;//返回数据全部记录
                    returnData.recordsFiltered = 0;//后台不实现过滤功能，每次查询均视作全部结果
                    returnData.data = [];//返回的数据列表
                    //console.log(returnData);
                    //调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
                    //此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
                    callback(returnData);
                }
            });
        },
        //列表表头字段
        aoColumns: columns,
        //在每次table被draw完后回调函数
        /*function(){
         var api = this.api();
         $('.make-switch').bootstrapSwitch();
         api.column(5).nodes().each(function(cell, i) {
         console.log($(cell).find(".make-switch").val() +'****'+i);
         // 给第一列加序号

         });
         console.log( );
         /!*!//获取到本页开始的条数 　　
         var startIndex= api.context[0]._iDisplayStart;

         api.column(1).nodes().each(function(cell, i) {
         // 给第一列加序号
         cell.innerHTML = startIndex + i + 1; 　　
         });*!/
         }*/
        fnDrawCallback: fnDrawCallback
    });
    //此处需调用api()方法,否则返回的是JQuery对象而不是DataTables的API对象

    this.find(".group-checkable").change(function () {
        var e = jQuery(this).attr("data-set"),
            t = jQuery(this).is(":checked");
        jQuery(e).each(function () {
            t ?
                $(this).prop("checked", !0)
                : $(this).prop("checked", !1)
        });
    });
    return table;
};
$.fn.dataTable.ext.errMode="throw";
