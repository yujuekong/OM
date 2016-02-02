jQuery(function ($) {
    $("#dataChart").datetimepicker({
        format: 'yyyy-mm',
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: true,
        todayHighlight: 1,
        startView: 3,
        forceParse: 0,
        "minView": 3
    });
});

FusionCharts.ready(function () {
    // Create a new instance of FusionCharts for rendering inside an HTML
    // `<div>` element with id `my-chart-container`.
    var myChart = new FusionCharts({
        type: 'column3d',
        renderAt: 'chart-container',
        dataFormat: 'json',
        "width": "1300",
        "height": "550",
        dataSource: {
            //    "chart": {
            //        "formatNumberScale":"0",
            //        "caption": "商品销售统计表",
            //        "subCaption": "2015年",
            //        "xAxisName": "月份",
            //        "yAxisName": "销售金额(元)",
            //        "numberPrefix": "$",
            //        "paletteColors": "#0075c2,#1aaf5d,#f2c500",
            //        "bgColor": "#ffffff",
            //        "showBorder": "0",
            //        "showCanvasBorder": "1",
            //        "usePlotGradientColor": "0",
            //        "plotBorderAlpha": "10",
            //        "legendBorderAlpha": "100",
            //        "legendBgAlpha": "0",
            //        "legendShadow": "0",
            //        "showHoverEffect": "1",
            //        "valueFontColor": "#ffffff",
            //        "rotateValues": "1",
            //        "placeValuesInside": "1",
            //        "divlineColor": "#999999",
            //        "divLineDashed": "1",
            //        "divLineDashLen": "1",
            //        "divLineGapLen": "1",
            //        "canvasBgColor": "#ffffff",
            //        "captionFontSize": "14",
            //        "subcaptionFontSize": "14",
            //        "subcaptionFontBold": "0",
            //
            //    },
        }
    });
    // Render the chart.

    //function ar() {
    //    alert(mydata);
    //    myChart.setChartData(mydata);
    //}
    var year = $("#year_report").val();
    var month = $("#month_report").val();
    var orgId = $("#orgId_report").val();
    var params={
        "year":year,
        "month":month,
        "orgId":orgId,
    }
    $.ajax({
        url: ROOT_PATH + "/view/report/goodsReport/goodsReportCharts.action",
        data: params,
        type: "post",
        dataType: "json",
        success: function (data) {
            myChart.setChartData(data);
        }

    });
    myChart.render();
    //
    //var mydata = {
    //
    //    "chart": {
    //        "caption": "12121商品销售统计表",
    //        "subCaption": "2015年",
    //    },
    //    "categories": [
    //        {
    //            "category": [
    //                {
    //                    "label": "一月"
    //                },
    //                {
    //                    "label": "二月"
    //                },
    //                {
    //                    "label": "三月"
    //                },
    //                {
    //                    "label": "四月"
    //                },
    //                {
    //                    "label": "五月"
    //                },
    //                {
    //                    "label": "六月"
    //                },
    //                {
    //                    "label": "七月"
    //                },
    //                {
    //                    "label": "八月"
    //                },
    //                {
    //                    "label": "九月"
    //                },
    //                {
    //                    "label": "十月"
    //                },
    //                {
    //                    "label": "十一月"
    //                },
    //                {
    //                    "label": "十二月"
    //                },
    //            ]
    //        }
    //    ],
    //    "dataset": [
    //        {
    //            "seriesname": "沈阳分公司",
    //            "data": [
    //                {
    //                    "value": "110000"
    //                },
    //                {
    //                    "value": "11500"
    //                },
    //                {
    //                    "value": "12500"
    //                },
    //                {
    //                    "value": "15000"
    //                },
    //                {
    //                    "value": "15000"
    //                },
    //                {
    //                    "value": "15000"
    //                },
    //                {
    //                    "value": "15000"
    //                },
    //                {
    //                    "value": "15000"
    //                },
    //                {
    //                    "value": "15000"
    //                },
    //                {
    //                    "value": "15000"
    //                },
    //                {
    //                    "value": "15000"
    //                },
    //                {
    //                    "value": "20000"
    //                }
    //            ]
    //        },
    //    ]
    //
    //}
    //document.getElementById('set_chart_data').addEventListener("click", ar);
    radio = document.getElementsByTagName('input');
    for (i = 0; i < radio.length; i++) {
        radElem = radio[i];
        if (radElem.type === 'radio') {
            radElem.onclick = function(){
                val = this.getAttribute('value');
                val && myChart.chartType(val);
            };
        }
    }
    $("#dataChart").change(function () {
        var chartData = $(this).val();
        var params = {
            "chartData": chartData
        };
        $.ajax({
            url: ROOT_PATH + "/view/report/goodsReport/goodsReportCharts.action",
            data: params,
            type: "post",
            dataType: "json",
            success: function (data) {
                myChart.setChartData(data);
            }

        });
    })
});
