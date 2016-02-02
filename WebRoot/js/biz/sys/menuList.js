jQuery(function ($) {
    $("#submenu-menu-sys-menu").addClass("active");
    $("#menu-sys").addClass("active");
    $("#menu-sys").addClass("open");
    jQuery(function ($) {
        var oTable1 =
            $('#sample-table-2')
                //.wrap("<div class='dataTables_borderWrap' />")   //if you are applying horizontal scrolling (sScrollX)
                .dataTable({
                    bAutoWidth: false,
                    "bFilter": false,// 搜索栏
                    "aoColumns": [
                        {"bSortable": false}, {"bSortable": false}, {"bSortable": false}, {"bSortable": false},
                        {"bSortable": false}, {"bSortable": false}, {"bSortable": false}, {"bSortable": false}
                    ]

                });
        $(document).on('click', 'th input:checkbox', function () {
            var that = this;
            $(this).closest('table').find('tr > td:first-child input:checkbox')
                .each(function () {
                    this.checked = that.checked;
                    $(this).closest('tr').toggleClass('selected');
                });
        });

        $('[data-rel="tooltip"]').tooltip({placement: tooltip_placement});
        function tooltip_placement(context, source) {
            var $source = $(source);
            var $parent = $source.closest('table')
            var off1 = $parent.offset();
            var w1 = $parent.width();

            var off2 = $source.offset();
            //var w2 = $source.width();

            if (parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2)) return 'right';
            return 'left';
        }

    })
});

//修改菜单
function modifyMenu(id) {
    window.location.href = "../../view/sys/sysMenu/addOrModifyMenu.action?menuId=" + id;
}
//删除菜单
function deleteMenu(menuId) {
    if (confirm("您确认删除该菜单吗?")) {
        $.ajax({
            url: ROOT_PATH + '/view/sys/sysMenu/delMenu.action',
            type: 'POST',
            data: {"menuId": menuId},
            dataType: 'json',
            success: function (data) {
                if (data) {
                    alert("删除成功！");
                }
                else {
                    alert("删除失败！");
                }
                var url = ROOT_PATH + "/view/sys/sysMenu/querySysMenu.action";
                var gotoLink = document.createElement('a');
                gotoLink.href = url;
                gotoLink.id = "delMenu";
                document.body.appendChild(gotoLink);
                gotoLink.click();
                $("#delMenu").remove();
            }
        });
    }
}
