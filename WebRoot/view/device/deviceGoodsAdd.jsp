<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<style>
    .box {
        overflow: hidden;
        width: 440px;
        padding: 10px 0px 0px 10px;
        background: #CCC;
        margin-top: 10px;
        margin-bottom: 10px;
    }

    .box div {
        height: 120px;
        width: 200px;
        margin-right: 10px;
        border: 1px solid #0099FF;
        float: left;
        margin-bottom: 10px;
        background: #FFF;
        text-align: center;
    }
</style>
<html lang="en">
<jsp:include page="../commons/head.jsp"/>

<body class="no-skin">
<div class="main-container" id="main-container">
    <div>
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check('breadcrumbs', 'fixed')
                } catch (e) {
                }
            </script>
            <input id="tId" type="hidden">
            <ul id="uiId" class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">首页</a>
                </li>
                <li><a href="#">配送管理</a></li>
                <li><a href="#">设备存放商品管理</a></li>
                <li class="active">新增设备存放商品</li>
            </ul><!-- /.breadcrumb -->

            <div class="nav-search" id="nav-search">
                <a href="javascript:history.back()">
                    <i class="ace-icon fa fa-arrow-left"></i>返回
                </a>
            </div><!-- /.nav-search -->
        </div>

        <div class="page-content">
            <div class="row">
                <input type="hidden" id='userId' value="${account.orgId}"/>
                <input type="hidden" id="devIdStr"/>
                <form action="../deviceGoods/saveOrUpdateDeviceGoods.action" method="post" onsubmit="return check();">
                    <div class="form-group">
                        <div class="widget-header widget-header-small">
                            <h5>添加设备商品</h5>
                        </div>
                        <div class="widget-body" style="margin-top:20px;">
                            <div class="form-group">
                                <label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
                                    选择设备：
                                </label>
										<span class="input-icon input-icon-right">
											<input id="deviceIdStr" type="hidden" name="deviceGoods.deviceId"
                                                   value="${deviceInfo.deviceId}"/>
											<input id="deviceNoStr" type="hidden" name="deviceGoods.deviceNo"
                                                   value="${deviceInfo.deviceNo}"/>
											<input id="deviceNameStr" type="text" class="required" style="width:200px;"
                                                   value="${deviceInfo.deviceName}" onclick="choiseDevice(this)"/>
										</span>
                            </div>
                        </div>
                        <div class="col-xs-12">
                            <div class="widget-box">
                                <div class="widget-body">
                                    <div>
                                        <div class="row">
                                            <div style="overflow:hidden; width:220px; float:left;padding:10px 0 0 10px;background:#CCC;margin-right:-9px;margin-left:10px;
														margin-top:10px;margin-bottom:10px;">
                                                <div style="height:640px; width:200px; margin-right:10px; border:1px solid #0099FF; float:left; margin-bottom:10px;
														background:#FFF; text-align:center;">
                                                    <label id="lab0">0</label><br>
                                                    <input type="hidden" id="deviceGoodsId0"
                                                           name="goodsList[0].deviceGoodsId"
                                                           value="${dGoodsList[0].deviceGoodsId}"/>
                                                    <input type="hidden" id="goodsId0" name="goodsList[0].goodsId"
                                                           value="${dGoodsList[0].goodsId}"/>
                                                    <input type="text" id="goodsName0" name="goodsList[0].goodsName"
                                                           value="${dGoodsList[0].goodsName}" readonly class="center"/>
                                                    <input type="hidden" id="goodsUnit0" name="goodsList[0].goodsUnit"
                                                           value="${dGoodsList[0].goodsUnit}"/>
                                                    <input type="hidden" id="gridId0" name="goodsList[0].gridId"
                                                           value="${gridList[0].gridId}"/>
                                                    <input type="hidden" id="gridNo0" name="goodsList[0].gridNo"
                                                           value="${gridList[0].gridNo}"/>
                                                    <input type="hidden" id="gridBar0" name="goodsList[0].gridBar"
                                                           value="${gridList[0].gridBar}"/>
                                                    <Button type="button" class="btn btn-xs no-border btn-info"
                                                            style="margin-top:30px;"
                                                            id="0" onclick="btn(this)">
                                                        <i class="ace-icon fa glyphicon-plus bigger-120"></i>
                                                        存放商品
                                                    </Button>
                                                </div>
                                            </div>

                                            <div class="box">
                                                <div>
                                                    <label id="lab1">1</label><br>
                                                    <input type="hidden" id="deviceGoodsId1"
                                                           name="goodsList[1].deviceGoodsId"
                                                           value="${dGoodsList[1].deviceGoodsId}"/>
                                                    <input type="hidden" id="goodsId1" name="goodsList[1].goodsId"
                                                           value="${dGoodsList[1].goodsId}"/>
                                                    <input type="text" id="goodsName1" name="goodsList[1].goodsName"
                                                           value="${dGoodsList[1].goodsName}" readonly class="center"/>
                                                    <input type="hidden" id="goodsUnit1" name="goodsList[1].goodsUnit"
                                                           value="${dGoodsList[1].goodsUnit}"/>
                                                    <input type="hidden" id="gridId1" name="goodsList[1].gridId"
                                                           value="${gridList[1].gridId}"/>
                                                    <input type="hidden" id="gridNo1" name="goodsList[1].gridNo"
                                                           value="${gridList[1].gridNo}"/>
                                                    <input type="hidden" id="gridBar1" name="goodsList[1].gridBar"
                                                           value="${gridList[1].gridBar}"/>

                                                    <Button type="button" class="btn btn-xs no-border btn-info"
                                                            style="margin-top:30px;"
                                                            id="1" onclick="btn(this)">
                                                        <i class="ace-icon fa glyphicon-plus bigger-120"></i>
                                                        存放商品
                                                    </Button>
                                                    <Button type="button" class="btn btn-xs no-border btn-info"
                                                            style="margin-top:30px;"
                                                            id="1" onclick="delBtn(this)">
                                                        <i class="ace-icon fa glyphicon-minus bigger-120"></i>
                                                        删除商品
                                                    </Button>
                                                </div>
                                                <div>
                                                    <label id="lab2">2</label><br>
                                                    <input type="hidden" id="deviceGoodsId2"
                                                           name="goodsList[2].deviceGoodsId"
                                                           value="${dGoodsList[2].deviceGoodsId}"/>
                                                    <input type="hidden" id="goodsId2" name="goodsList[2].goodsId"
                                                           value="${dGoodsList[2].goodsId}"/>
                                                    <input type="text" id="goodsName2" name="goodsList[2].goodsName"
                                                           value="${dGoodsList[2].goodsName}" readonly class="center"/>
                                                    <input type="hidden" id="goodsUnit2" name="goodsList[2].goodsUnit"
                                                           value="${dGoodsList[2].goodsUnit}"/>
                                                    <input type="hidden" id="gridId2" name="goodsList[2].gridId"
                                                           value="${gridList[2].gridId}"/>
                                                    <input type="hidden" id="gridNo2" name="goodsList[2].gridNo"
                                                           value="${gridList[2].gridNo}"/>
                                                    <input type="hidden" id="gridBar2" name="goodsList[2].gridBar"
                                                           value="${gridList[2].gridBar}"/>

                                                    <Button type="button" class="btn btn-xs no-border btn-info"
                                                            style="margin-top:30px;"
                                                            id="2" onclick="btn(this)">
                                                        <i class="ace-icon fa glyphicon-plus bigger-120"></i>
                                                        存放商品
                                                    </Button>
                                                    <Button type="button" class="btn btn-xs no-border btn-info"
                                                            style="margin-top:30px;"
                                                            id="2" onclick="delBtn(this)">
                                                        <i class="ace-icon fa glyphicon-minus bigger-120"></i>
                                                        删除商品
                                                    </Button>
                                                </div>
                                                <div>
                                                    <label id="lab3">3</label><br>
                                                    <input type="hidden" id="deviceGoodsId3"
                                                           name="goodsList[3].deviceGoodsId"
                                                           value="${dGoodsList[3].deviceGoodsId}"/>
                                                    <input type="hidden" id="goodsId3" name="goodsList[3].goodsId"
                                                           value="${dGoodsList[3].goodsId}"/>
                                                    <input type="text" id="goodsName3" name="goodsList[3].goodsName"
                                                           value="${dGoodsList[3].goodsName}" readonly class="center"/>
                                                    <input type="hidden" id="goodsUnit3" name="goodsList[3].goodsUnit"
                                                           value="${dGoodsList[3].goodsUnit}"/>
                                                    <input type="hidden" id="gridId3" name="goodsList[3].gridId"
                                                           value="${gridList[3].gridId}"/>
                                                    <input type="hidden" id="gridNo3" name="goodsList[3].gridNo"
                                                           value="${gridList[3].gridNo}"/>
                                                    <input type="hidden" id="gridBar3" name="goodsList[3].gridBar"
                                                           value="${gridList[3].gridBar}"/>
                                                    <Button type="button" class="btn btn-xs no-border btn-info"
                                                            style="margin-top:30px;"
                                                            id="3" onclick="btn(this)">
                                                        <i class="ace-icon fa glyphicon-plus bigger-120"></i>
                                                        存放商品
                                                    </Button>
                                                    <Button type="button" class="btn btn-xs no-border btn-info"
                                                            style="margin-top:30px;"
                                                            id="3" onclick="delBtn(this)">
                                                        <i class="ace-icon fa glyphicon-minus bigger-120"></i>
                                                        删除商品
                                                    </Button>
                                                </div>
                                                <div>
                                                    <label id="lab4">4</label><br>
                                                    <input type="hidden" id="deviceGoodsId4"
                                                           name="goodsList[4].deviceGoodsId"
                                                           value="${dGoodsList[4].deviceGoodsId}"/>
                                                    <input type="hidden" id="goodsId4" name="goodsList[4].goodsId"
                                                           value="${dGoodsList[4].goodsId}"/>
                                                    <input type="text" id="goodsName4" name="goodsList[4].goodsName"
                                                           value="${dGoodsList[4].goodsName}" readonly class="center"/>
                                                    <input type="hidden" id="goodsUnit4" name="goodsList[4].goodsUnit"
                                                           value="${dGoodsList[4].goodsUnit}"/>
                                                    <input type="hidden" id="gridId4" name="goodsList[4].gridId"
                                                           value="${gridList[4].gridId}"/>
                                                    <input type="hidden" id="gridNo4" name="goodsList[4].gridNo"
                                                           value="${gridList[4].gridNo}"/>
                                                    <input type="hidden" id="gridBar4" name="goodsList[4].gridBar"
                                                           value="${gridList[4].gridBar}"/>
                                                    <Button type="button" class="btn btn-xs no-border btn-info"
                                                            style="margin-top:30px;"
                                                            id="4" onclick="btn(this)">
                                                        <i class="ace-icon fa glyphicon-plus bigger-120"></i>
                                                        存放商品
                                                    </Button>
                                                    <Button type="button" class="btn btn-xs no-border btn-info"
                                                            style="margin-top:30px;"
                                                            id="4" onclick="delBtn(this)">
                                                        <i class="ace-icon fa glyphicon-minus bigger-120"></i>
                                                        删除商品
                                                    </Button>
                                                </div>
                                                <div>
                                                    <label id="lab5">5</label><br>
                                                    <input type="hidden" id="deviceGoodsId5"
                                                           name="goodsList[5].deviceGoodsId"
                                                           value="${dGoodsList[5].deviceGoodsId}"/>
                                                    <input type="hidden" id="goodsId5" name="goodsList[5].goodsId"
                                                           value="${dGoodsList[5].goodsId}"/>
                                                    <input type="text" id="goodsName5" name="goodsList[5].goodsName"
                                                           value="${dGoodsList[5].goodsName}" readonly class="center"/>
                                                    <input type="hidden" id="goodsUnit5" name="goodsList[5].goodsUnit"
                                                           value="${dGoodsList[5].goodsUnit}"/>
                                                    <input type="hidden" id="gridId5" name="goodsList[5].gridId"
                                                           value="${gridList[5].gridId}"/>
                                                    <input type="hidden" id="gridNo5" name="goodsList[5].gridNo"
                                                           value="${gridList[5].gridNo}"/>
                                                    <input type="hidden" id="gridBar5" name="goodsList[5].gridBar"
                                                           value="${gridList[5].gridBar}"/>
                                                    <Button type="button" class="btn btn-xs no-border btn-info"
                                                            style="margin-top:30px;"
                                                            id="5" onclick="btn(this)">
                                                        <i class="ace-icon fa glyphicon-plus bigger-120"></i>
                                                        存放商品
                                                    </Button>
                                                    <Button type="button" class="btn btn-xs no-border btn-info"
                                                            style="margin-top:30px;"
                                                            id="5" onclick="delBtn(this)">
                                                        <i class="ace-icon fa glyphicon-minus bigger-120"></i>
                                                        删除商品
                                                    </Button>
                                                </div>
                                                <div>
                                                    <label id="lab6">6</label><br>
                                                    <input type="hidden" id="deviceGoodsId6"
                                                           name="goodsList[6].deviceGoodsId"
                                                           value="${dGoodsList[6].deviceGoodsId}"/>
                                                    <input type="hidden" id="goodsId6" name="goodsList[6].goodsId"
                                                           value="${dGoodsList[6].goodsId}"/>
                                                    <input type="text" id="goodsName6" name="goodsList[6].goodsName"
                                                           value="${dGoodsList[6].goodsName}" readonly class="center"/>
                                                    <input type="hidden" id="goodsUnit6" name="goodsList[6].goodsUnit"
                                                           value="${dGoodsList[6].goodsUnit}"/>
                                                    <input type="hidden" id="gridId6" name="goodsList[6].gridId"
                                                           value="${gridList[6].gridId}"/>
                                                    <input type="hidden" id="gridNo6" name="goodsList[6].gridNo"
                                                           value="${gridList[6].gridNo}"/>
                                                    <input type="hidden" id="gridBar6" name="goodsList[6].gridBar"
                                                           value="${gridList[6].gridBar}"/>
                                                    <Button type="button" class="btn btn-xs no-border btn-info"
                                                            style="margin-top:30px;"
                                                            id="6" onclick="btn(this)">
                                                        <i class="ace-icon fa glyphicon-plus bigger-120"></i>
                                                        存放商品
                                                    </Button>
                                                    <Button type="button" class="btn btn-xs no-border btn-info"
                                                            style="margin-top:30px;"
                                                            id="6" onclick="delBtn(this)">
                                                        <i class="ace-icon fa glyphicon-minus bigger-120"></i>
                                                        删除商品
                                                    </Button>
                                                </div>
                                                <div>
                                                    <label id="lab7">7</label><br>
                                                    <input type="hidden" id="deviceGoodsId7"
                                                           name="goodsList[7].deviceGoodsId"
                                                           value="${dGoodsList[7].deviceGoodsId}"/>
                                                    <input type="hidden" id="goodsId7" name="goodsList[7].goodsId"
                                                           value="${dGoodsList[7].goodsId}"/>
                                                    <input type="text" id="goodsName7" name="goodsList[7].goodsName"
                                                           value="${dGoodsList[7].goodsName}" readonly class="center"/>
                                                    <input type="hidden" id="goodsUnit7" name="goodsList[7].goodsUnit"
                                                           value="${dGoodsList[7].goodsUnit}"/>
                                                    <input type="hidden" id="gridId7" name="goodsList[7].gridId"
                                                           value="${gridList[7].gridId}"/>
                                                    <input type="hidden" id="gridNo7" name="goodsList[7].gridNo"
                                                           value="${gridList[7].gridNo}"/>
                                                    <input type="hidden" id="gridBar7" name="goodsList[7].gridBar"
                                                           value="${gridList[7].gridBar}"/>
                                                    <Button type="button" class="btn btn-xs no-border btn-info"
                                                            style="margin-top:30px;"
                                                            id="7" onclick="btn(this)">
                                                        <i class="ace-icon fa glyphicon-plus bigger-120"></i>
                                                        存放商品
                                                    </Button>
                                                    <Button type="button" class="btn btn-xs no-border btn-info"
                                                            style="margin-top:30px;"
                                                            id="7" onclick="delBtn(this)">
                                                        <i class="ace-icon fa glyphicon-minus bigger-120"></i>
                                                        删除商品
                                                    </Button>
                                                </div>
                                                <div>
                                                    <label id="lab8">8</label><br>
                                                    <input type="hidden" id="deviceGoodsId8"
                                                           name="goodsList[8].deviceGoodsId"
                                                           value="${dGoodsList[8].deviceGoodsId}"/>
                                                    <input type="hidden" id="goodsId8" name="goodsList[8].goodsId"
                                                           value="${dGoodsList[8].goodsId}"/>
                                                    <input type="text" id="goodsName8" name="goodsList[8].goodsName"
                                                           value="${dGoodsList[8].goodsName}" readonly class="center"/>
                                                    <input type="hidden" id="goodsUnit8" name="goodsList[8].goodsUnit"
                                                           value="${dGoodsList[8].goodsUnit}"/>
                                                    <input type="hidden" id="gridId8" name="goodsList[8].gridId"
                                                           value="${gridList[8].gridId}"/>
                                                    <input type="hidden" id="gridNo8" name="goodsList[8].gridNo"
                                                           value="${gridList[8].gridNo}"/>
                                                    <input type="hidden" id="gridBar8" name="goodsList[8].gridBar"
                                                           value="${gridList[8].gridBar}"/>
                                                    <Button type="button" class="btn btn-xs no-border btn-info"
                                                            style="margin-top:30px;"
                                                            id="8" onclick="btn(this)">
                                                        <i class="ace-icon fa glyphicon-plus bigger-120"></i>
                                                        存放商品
                                                    </Button>
                                                    <Button type="button" class="btn btn-xs no-border btn-info"
                                                            style="margin-top:30px;"
                                                            id="8" onclick="delBtn(this)">
                                                        <i class="ace-icon fa glyphicon-minus bigger-120"></i>
                                                        删除商品
                                                    </Button>
                                                </div>
                                                <div>
                                                    <label id="lab9">9</label><br>
                                                    <input type="hidden" id="deviceGoodsId9"
                                                           name="goodsList[9].deviceGoodsId"
                                                           value="${dGoodsList[9].deviceGoodsId}"/>
                                                    <input type="hidden" id="goodsId9" name="goodsList[9].goodsId"
                                                           value="${dGoodsList[9].goodsId}"/>
                                                    <input type="text" id="goodsName9" name="goodsList[9].goodsName"
                                                           value="${dGoodsList[9].goodsName}" readonly class="center"/>
                                                    <input type="hidden" id="goodsUnit9" name="goodsList[9].goodsUnit"
                                                           value="${dGoodsList[9].goodsUnit}"/>
                                                    <input type="hidden" id="gridId9" name="goodsList[9].gridId"
                                                           value="${gridList[9].gridId}"/>
                                                    <input type="hidden" id="gridNo9" name="goodsList[9].gridNo"
                                                           value="${gridList[9].gridNo}"/>
                                                    <input type="hidden" id="gridBar9" name="goodsList[9].gridBar"
                                                           value="${gridList[9].gridBar}"/>
                                                    <Button type="button" class="btn btn-xs no-border btn-info"
                                                            style="margin-top:30px;"
                                                            id="9" onclick="btn(this)">
                                                        <i class="ace-icon fa glyphicon-plus bigger-120"></i>
                                                        存放商品
                                                    </Button>
                                                    <Button type="button" class="btn btn-xs no-border btn-info"
                                                            style="margin-top:30px;"
                                                            id="9" onclick="delBtn(this)">
                                                        <i class="ace-icon fa glyphicon-minus bigger-120"></i>
                                                        删除商品
                                                    </Button>
                                                </div>
                                                <div id="div10">
                                                    <label id="lab10">10</label><br>
                                                    <input type="hidden" id="deviceGoodsId10"
                                                           name="goodsList[10].deviceGoodsId"
                                                           value="${dGoodsList[10].deviceGoodsId}"/>
                                                    <input type="hidden" id="goodsId10" name="goodsList[10].goodsId"
                                                           value="${dGoodsList[10].goodsId}"/>
                                                    <input type="text" id="goodsName10" name="goodsList[10].goodsName"
                                                           value="${dGoodsList[10].goodsName}" readonly class="center"/>
                                                    <input type="hidden" id="goodsUnit10" name="goodsList[10].goodsUnit"
                                                           value="${dGoodsList[10].goodsUnit}"/>
                                                    <input type="hidden" id="gridId10" name="goodsList[10].gridId"
                                                           value="${gridList[10].gridId}"/>
                                                    <input type="hidden" id="gridNo10" name="goodsList[10].gridNo"
                                                           value="${gridList[10].gridNo}"/>
                                                    <input type="hidden" id="gridBar10" name="goodsList[10].gridBar"
                                                           value="${gridList[10].gridBar}"/>
                                                    <Button type="button" class="btn btn-xs no-border btn-info"
                                                            style="margin-top:30px;"
                                                            id="10" onclick="btn(this)">
                                                        <i class="ace-icon fa glyphicon-plus bigger-120"></i>
                                                        存放商品
                                                    </Button>
                                                    <Button type="button" class="btn btn-xs no-border btn-info"
                                                            style="margin-top:30px;"
                                                            id="10" onclick="delBtn(this)">
                                                        <i class="ace-icon fa glyphicon-minus bigger-120"></i>
                                                        删除商品
                                                    </Button>
                                                </div>
                                                <%--<div >--%>
                                                <%--<label id="lab11">11</label><br>--%>
                                                <%--<input  type="hidden" id="deviceGoodsId11"   name="goodsList[11].deviceGoodsId"  value="${dGoodsList[11].deviceGoodsId}"/>--%>
                                                <%--<input  type="hidden" id="goodsId11"   name="goodsList[11].goodsId"  value="${dGoodsList[11].goodsId}"/>--%>
                                                <%--<input  type="text"   id="goodsName11" name="goodsList[11].goodsName"  value="${dGoodsList[11].goodsName}" readonly class="center"/>--%>
                                                <%--<input 	type="hidden" id="goodsUnit11" name="goodsList[11].goodsUnit"  value="${dGoodsList[11].goodsUnit}"/>--%>
                                                <%--<input  type="hidden" id="gridId11" name="goodsList[11].gridId"  value="${gridList[11].gridId}"/>--%>
                                                <%--<input 	type="hidden" id="gridNo11" name="goodsList[11].gridNo"  value="${gridList[11].gridNo}"/>--%>
                                                <%--<input 	type="hidden" id="gridBar11" name="goodsList[11].gridBar"  value="${gridList[11].gridBar}"/>--%>
                                                <%--<Button type="button" class="btn btn-xs no-border btn-info" style="margin-top:30px;"--%>
                                                <%--id = "11" onclick="btn(this)">--%>
                                                <%--<i class="ace-icon fa glyphicon-plus bigger-120"></i>--%>
                                                <%--存放商品--%>
                                                <%--</Button>--%>
                                                <%--<Button type="button" class="btn btn-xs no-border btn-info" style="margin-top:30px;"--%>
                                                <%--id = "11" onclick="delBtn(this)">--%>
                                                <%--<i class="ace-icon fa glyphicon-minus bigger-120"></i>--%>
                                                <%--删除商品--%>
                                                <%--</Button>--%>
                                                <%--</div>--%>
                                                <%--<div>--%>
                                                <%--<label id="lab12">12</label><br>--%>
                                                <%--<input  type="hidden" id="deviceGoodsId12"   name="goodsList[12].deviceGoodsId"  value="${dGoodsList[12].deviceGoodsId}"/>--%>
                                                <%--<input  type="hidden" id="goodsId12"   name="goodsList[12].goodsId"  value="${dGoodsList[12].goodsId}"/>--%>
                                                <%--<input  type="text"   id="goodsName12" name="goodsList[12].goodsName"  value="${dGoodsList[12].goodsName}" readonly class="center"/>--%>
                                                <%--<input 	type="hidden" id="goodsUnit12" name="goodsList[12].goodsUnit"  value="${dGoodsList[12].goodsUnit}"/>--%>
                                                <%--<input  type="hidden" id="gridId12" name="goodsList[12].gridId"  value="${gridList[12].gridId}"/>--%>
                                                <%--<input 	type="hidden" id="gridNo12" name="goodsList[12].gridNo"  value="${gridList[12].gridNo}"/>--%>
                                                <%--<input 	type="hidden" id="gridBar12" name="goodsList[12].gridBar"  value="${gridList[12].gridBar}"/>--%>
                                                <%--<Button type="button" class="btn btn-xs no-border btn-info" style="margin-top:30px;"--%>
                                                <%--id = "12" onclick="btn(this)">--%>
                                                <%--<i class="ace-icon fa glyphicon-plus bigger-120"></i>--%>
                                                <%--存放商品--%>
                                                <%--</Button>--%>
                                                <%--<Button type="button" class="btn btn-xs no-border btn-info" style="margin-top:30px;"--%>
                                                <%--id = "12" onclick="delBtn(this)">--%>
                                                <%--<i class="ace-icon fa glyphicon-minus bigger-120"></i>--%>
                                                <%--删除商品--%>
                                                <%--</Button>--%>
                                                <%--</div>--%>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 表单操作按钮开始 -->
                    <div class="row">
                        <div id="form_action_btns" class="col-xs-12" style="text-align: center">
                            <button id="save" class="btn btn-sm no-border btn-success">
                                <i class="ace-icon fa fa-floppy-o"></i>保存
                            </button>
                            &nbsp;&nbsp;&nbsp;
                            <a class="btn btn-sm no-border btn-default" onclick="history.back()">
                                <i class="ace-icon fa fa-times red2"></i>取消</a>
                        </div>
                    </div>
                </form>
            </div>
        </div><!-- /.row -->
    </div><!-- /.page-content -->
</div><!-- /.main-content -->


<!-- 设备选择弹出窗开始 -->
<div id="device_choise_modal" class="modal hiden fade"
     tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:860px;">
        <div class="modal-content">
            <div class="modal-header thicktitle">
                <a class="close" data-dismiss="modal" aria-hidden="true">×</a>
                <span class="modal-title" id="myModalLabel">选择设备</span>
            </div>
            <div class="modal-body thickcon"
                 style="width:858px;height: 650px; padding-left: 10px; padding-right: 10px;">
                <div class="row">
                    <div id="search-form" class="col-xs-12" style="padding-bottom: 5px;">
                        <div class="input-group" style="float:right;width:300px;">
                            <input type="text" id="keyword" class="form-control search-query" placeholder=""/>
									<span class="input-group-btn">
										<button id="searchGoodsBtn" type="button"
                                                class="btn btn-purple btn-sm no-border">
                                            <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
                                            搜索
                                        </button>
									</span>
                        </div>
                    </div>
                    <div class="col-xs-12">
                        <table id="device_list" class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th class="center">设备编号</th>
                                <th class="center">设备名称</th>
                                <th class="center">设备地址</th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 商品选择弹出窗开始 -->
<div id="goods_choise_modal" class="modal hiden fade"
     tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:860px;">
        <div class="modal-content">
            <div class="modal-header thicktitle">
                <a class="close" data-dismiss="modal" aria-hidden="true">×</a>
                <span class="modal-title" id="myModalLabel">选择商品</span>
            </div>
            <div class="modal-body thickcon"
                 style="width:858px;height: 650px; padding-left: 10px; padding-right: 10px;">
                <div class="row">
                    <div id="search-form" class="col-xs-12" style="padding-bottom: 5px;">
                        <div class="input-group" style="float:right;width:300px;">
                            <input type="text" id="keyword" class="form-control search-query" placeholder=""/>
									<span class="input-group-btn">
										<button id="searchGoodsBtn" type="button"
                                                class="btn btn-purple btn-sm no-border">
                                            <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
                                            搜索
                                        </button>
									</span>
                        </div>
                    </div>
                    <div class="col-xs-12">
                        <table id="goods_list" class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <!-- 	<th class="center">
                                            <label class="position-relative">
                                                <input type="checkbox" class="ace" />
                                                <span class="lbl"></span>
                                            </label>
                                        </th> -->
                                <th class="center">商品名称</th>
                                <th class="center">商品单位</th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 站点信息选择弹出窗结束 -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript">
    window.jQuery || document.write("<script src='${pageContext.request.contextPath}/js/jquery.min.js'>" + "<" + "/script>");
</script>
<!-- page specific plugin scripts -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.uniform.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.custom.min.js"></script>
<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.js"></script>
<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.zh-CN.js"></script>
<!-- ace scripts -->
<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.js"></script>
<script src="${pageContext.request.contextPath}/js/biz/device/deviceGoodsAdd.js"></script>
<script src="${pageContext.request.contextPath}/js/ycs.dateUtil.js"></script>
</body>
</html>



