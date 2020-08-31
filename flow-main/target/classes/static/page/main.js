function getFormData(id) {
    var formData = {};
    var t = $('#' + id).serializeArray();
    $.each(t, function () {
        formData[this.name] = this.value;
    });
    return JSON.stringify(formData);
}

$(document).ready(function () {
    loadtableData();

    function loadtableData() {
        $('#emps').dataTable({
            "language": {
                "sProcessing": "处理中...",
                "sZeroRecords": "没有匹配结果",
                "sInfo":  "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
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
            createdRow: function (row, data, dataIndex) {
            },
            ajax:function(data,callback,settings) {
                var pageNum = (data.start / data.length) + 1;
                var param = {};
                param.pageSize = data.length;
                param.pageNum = pageNum;
                $.ajax({
                    url: "/emp/page/list",
                    type: "get",
                    dataType:"json",
                    data:param,
                    success:function (result) {
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
                name: "empName",
                title: "姓名",
                data: "empName"
            }, {
                name: "email",
                title: "EMAIL",
                data: "email"

            }, {
                name: "address",
                title: "地址",
                data: "address",
                render: function (data, type, row) {
                    return data;
                }
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
                name: "deptId",
                title: "部门",
                data: "deptId",
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
     *
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

});