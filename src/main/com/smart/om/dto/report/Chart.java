package com.smart.om.dto.report;

/**
 * Created by hxt on 2016/1/22.
 * 图例类
 */
public class Chart {
    /**
     * 图例属性
     */
    //表格主标题
    private String caption;
    //表格副标题
    private String subCaption;
    //X轴名称
    private String xAxisName;
    //y轴名称
    private String yAxisName;
    //数字前缀
    private String numberPrefix;
    //自定义图表颜色(循环)
    private String paletteColors;
    //背景颜色
    private String bgColor;
    //是否显示图表边框线(0,1)
    private String showBorder;
    //每一片的边框透明度
    private String plotBorderAlpha;
    //图例边框透明度
    private String legendBorderAlpha;
    //图例透明度
    private String legendBgAlpha;
    //是否启用悬停效果图表元素
    private String showHoverEffect;
    //指定字体颜色值
    private String valueFontColor;
    //把值旋转
    private String rotateValues;
    //'0/1'(图形上柱子的值是否显示在柱子里面)
    private String placeValuesInside;
    //分区线为虚线或实线(0,1)
    private String divLineDashed;
    //'Color'(网格线颜色)
    private String divlineColor;
    //'Number'(每个虚线的长度)
    private String divLineDashLen;
    //水平分割线是否使用虚线(0,1)
    private String divLineGapLen;
    //'Color'(画板背景颜色)
    private String canvasBgColor;
    //标题字体大小
    private String captionFontSize;
    //子标题字体大小
    private String subcaptionFontSize;
    //子标题是否加粗(0,1)
    private String subcaptionFontBold;
    //是否使用数字规格K(0,1,def:1)
    private String formatNumberScale;

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getSubCaption() {
        return subCaption;
    }

    public void setSubCaption(String subCaption) {
        this.subCaption = subCaption;
    }

    public String getxAxisName() {
        return xAxisName;
    }

    public void setxAxisName(String xAxisName) {
        this.xAxisName = xAxisName;
    }

    public String getyAxisName() {
        return yAxisName;
    }

    public void setyAxisName(String yAxisName) {
        this.yAxisName = yAxisName;
    }

    public String getNumberPrefix() {
        return numberPrefix;
    }

    public void setNumberPrefix(String numberPrefix) {
        this.numberPrefix = numberPrefix;
    }

    public String getPaletteColors() {
        return paletteColors;
    }

    public void setPaletteColors(String paletteColors) {
        this.paletteColors = paletteColors;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public String getShowBorder() {
        return showBorder;
    }

    public void setShowBorder(String showBorder) {
        this.showBorder = showBorder;
    }

    public String getPlotBorderAlpha() {
        return plotBorderAlpha;
    }

    public void setPlotBorderAlpha(String plotBorderAlpha) {
        this.plotBorderAlpha = plotBorderAlpha;
    }

    public String getLegendBorderAlpha() {
        return legendBorderAlpha;
    }

    public void setLegendBorderAlpha(String legendBorderAlpha) {
        this.legendBorderAlpha = legendBorderAlpha;
    }

    public String getLegendBgAlpha() {
        return legendBgAlpha;
    }

    public void setLegendBgAlpha(String legendBgAlpha) {
        this.legendBgAlpha = legendBgAlpha;
    }

    public String getShowHoverEffect() {
        return showHoverEffect;
    }

    public void setShowHoverEffect(String showHoverEffect) {
        this.showHoverEffect = showHoverEffect;
    }

    public String getValueFontColor() {
        return valueFontColor;
    }

    public void setValueFontColor(String valueFontColor) {
        this.valueFontColor = valueFontColor;
    }

    public String getRotateValues() {
        return rotateValues;
    }

    public void setRotateValues(String rotateValues) {
        this.rotateValues = rotateValues;
    }

    public String getPlaceValuesInside() {
        return placeValuesInside;
    }

    public void setPlaceValuesInside(String placeValuesInside) {
        this.placeValuesInside = placeValuesInside;
    }

    public String getDivLineDashed() {
        return divLineDashed;
    }

    public void setDivLineDashed(String divLineDashed) {
        this.divLineDashed = divLineDashed;
    }

    public String getDivlineColor() {
        return divlineColor;
    }

    public void setDivlineColor(String divlineColor) {
        this.divlineColor = divlineColor;
    }

    public String getDivLineDashLen() {
        return divLineDashLen;
    }

    public void setDivLineDashLen(String divLineDashLen) {
        this.divLineDashLen = divLineDashLen;
    }

    public String getDivLineGapLen() {
        return divLineGapLen;
    }

    public void setDivLineGapLen(String divLineGapLen) {
        this.divLineGapLen = divLineGapLen;
    }

    public String getCanvasBgColor() {
        return canvasBgColor;
    }

    public void setCanvasBgColor(String canvasBgColor) {
        this.canvasBgColor = canvasBgColor;
    }

    public String getCaptionFontSize() {
        return captionFontSize;
    }

    public void setCaptionFontSize(String captionFontSize) {
        this.captionFontSize = captionFontSize;
    }

    public String getSubcaptionFontSize() {
        return subcaptionFontSize;
    }

    public void setSubcaptionFontSize(String subcaptionFontSize) {
        this.subcaptionFontSize = subcaptionFontSize;
    }

    public String getSubcaptionFontBold() {
        return subcaptionFontBold;
    }

    public void setSubcaptionFontBold(String subcaptionFontBold) {
        this.subcaptionFontBold = subcaptionFontBold;
    }

    public String getFormatNumberScale() {
        return formatNumberScale;
    }

    public void setFormatNumberScale(String formatNumberScale) {
        this.formatNumberScale = formatNumberScale;
    }
}
