function getFormData(id) {
    var formData = {};
    var t = $('#' + id).serializeArray();
    $.each(t, function () {
        formData[this.name] = this.value;
    });
    return JSON.stringify(formData);
}

$(document).ready(function () {

    $('ul.sidebar-menu li').click(function () {
        var li = $('ul.sidebar-menu li.active');
        li.removeClass('active');
        $(this).addClass('active');
    });

    $('.myLeftMenu').click(function (e) {
        var url = $(this).attr('data');
        $('#container').load(url);
    });

    loadtableData();

    function loadtableData() {
        $('#emps').dataTable({
            "language": {
                "sProcessing": "处理中...",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            iDisplayLength: 5,
            bDeferRender: true,
            bLengthChange: false,
            bFilter: false,
            createdRow: function (row, data, dataIndex) {
            },
            ajax: function (data, callback, settings) {
                var pageNum = (data.start / data.length) + 1;
                var param = {};
                param.pageSize = data.length;
                param.pageNum = pageNum;
                $.ajax({
                    url: "/user/page/list",
                    type: "get",
                    dataType: "json",
                    data: param,
                    success: function (result) {
                        var returnData = {};
                        returnData.draw = data.draw;
                        returnData.recordsTotal = result.total;
                        returnData.recordsDisplay = result.pageNum;
                        returnData.data = result.list;
                        returnData.iTotalRecords = result.total;
                        returnData.iTotalDisplayRecords = result.total;
                        callback(returnData);
                    }
                });
            },
            pagingType: 'full_numbers',
            processing: true,
            serverSide: true,//使用server时为true
            columns: [{
                title: "<input align=\"center\" type=\"checkbox\" class=\"checkAll\"  id=\"checkAll\"/>",
                data: 'id',
                render: function (data, type) {
                    return "<input type=\"checkbox\"   name=\"ckb-id\" value=" + data + ">";
                }
            }, {
                name: "id",
                title: "ID",
                data: "id",
                render: function (data, type, row) {
                    return data;
                }
            }, {
                name: "userName",
                title: "姓名",
                data: "userName"
            }, {
                name: "telphone",
                title: "手机号码",
                data: "telphone",
                render: function (data, type, row) {
                    return data;
                }
            }, {
                name: "email",
                title: "EMAIL",
                data: "email"

            }, {
                name: "gender",
                title: "性别",
                data: "gender",
                render: function (data, type, row) {
                    if (data == '1') {
                        return '男';
                    } else {
                        return '女';
                    }
                }
            }, {
                name: "department.deptName",
                title: "部门",
                data: "department.deptName",
                render: function (data, type, row) {
                    return data;
                }
            }],
            "fnRowCallback": function (nRow, aData, iDisplayIndex) {

            },
            bPaginate: true,
            columnDefs: [{
                orderable: false,// 禁用排序
                targets: "_all"// 指定的列
            }],
            'aaSorting': [],
            'initComplete': function (settings, json) {

            }
        });
    }

    /**
     *全选
     */
    $("#checkAll").on("click", function () {
        if (this.checked) {
            $(this).attr('checked', 'checked');
            $("input[name='ckb-id']").each(function () {
                this.checked = true;
            });
        } else {
            $(this).removeAttr('checked');
            $("input[name='ckb-id']").each(function () {
                this.checked = false;
            });
        }
    });
    $("#btn_delete").click(function () {
        var ids = '';
        $("input[name='ckb-id']").each(function () {
            if (this.checked) {
                ids += this.value + ',';
            }
        });
        if (ids != '') {
            ids = ids.substr(0, ids.length - 1);
            msgConfirm('删除用户','确认删除该条数据？',delUser,ids);
        } else {
            msgAlert("删除用户","请选择删除的数据！");
        }
    });
    function delUser(ids) {
        var params = {
            id:ids,
            _method:"DELETE"
        }
        $.ajax({
            url: "/user",
            type: "post",
            dataType: "json",
            data: params,
            success: function (result) {
                if(result.code == 200) {
                    msgAlert("删除用户",result.msg);
                    var start = $("#emps").dataTable().fnSettings()._iDisplayStart;
                    var length = $("#emps").dataTable().fnSettings()._iDisplayLength;
                    var page=(start / length); //得到页数值  比页码小1
                    $("#emps").dataTable().fnPageChange(page);//加载跳转
                }
            }
        });
    }
});