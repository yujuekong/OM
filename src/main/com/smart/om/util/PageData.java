package com.smart.om.util;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * <p>
 * <h2>PageData类实现分页信息的封装，可实现分页数据查询和显示。</h2>
 * </p>
 * 
 * <p>
 * 分页查询数据时，查询前要指定每页数据行数和当前要查询的页码，然后DAO类查询出
 * 可查到的总数据行数，并记录在PageData类中，据此可以计算出总页数。如果
 * PageData的没有得到总行数和总页数，则在DAO查询时需要计算总函数和总页数，
 * 否则，不再计算总函数和总页数。
 * </p>
 * 
 */
public final class PageData {

        //查询得到的总行数，-1表示未执行查询
        private int rowCount = -1;
        
        //查询得到的总页数
        private int pageCount = -1;
        
        //页面大小，查询数据时，一次查询处的最大行数
        private int pageSize = 10;
        
        //当前页码，当前要查询的页码
        private int currentPage = 1;
        
        private int sEcho = 1;
        
        /**构造缺省的分页信息，当前页码为：1，页面大小
         * 为：SysConfig.getPageSize()配置的值，缺省为：10*/
        public PageData() {
                this(1);
        }
        
        /**用指定的当前页码构造分页信息，当前页码不能小于1，页面大小
         * 为：SysConfig.getPageSize()配置的值，缺省为：10*/
        public PageData(int currentPage) {
                this(currentPage, 10, 1);
        }

        /**用指定的当前页码和页面大小构造分页信息，当前页码不能小于1，页面大小
         * 不能小于1*/
        public PageData(int currentPage, int pageSize, int sEcho) {
                if (currentPage > 0) {
                        this.currentPage = currentPage;
                }
                if (pageSize > 0) {
                        this.pageSize = pageSize;
                }
                this.sEcho = sEcho;
        }
        
        /**用指定的当前页码和页面大小构造分页信息，当前页码不能小于1，页面大小
         * 不能小于1*/
        public PageData(int currentPage, int pageSize, int sEcho,String type) {
                if (currentPage > 0) {
                        this.currentPage = currentPage;
                }
                if (pageSize > 0) {
                        this.pageSize = pageSize;
                }
                this.sEcho = sEcho;
        }

        /**是否需要计算总行数和总页数*/
        public boolean isFirstPage() {
                return (rowCount < 0 && pageCount < 0);
        }

        /**取得当前页面*/
        public int getCurrentPage() {
                return currentPage;
        }

        /**设置当前页码，不能小于1*/
        public void setCurrentPage(int currentPage) {
                if (currentPage > 0) {
                        this.currentPage = currentPage;
                }
        }

        /**取得总页数*/
        public int getPageCount() {
                if (pageCount >= 0) {
                        return pageCount;
                } else {
                        return 0;
                }
        }

        /**取得页面大小*/
        public int getPageSize() {
                return pageSize;
        }

        /**设置页面大小，不能小于1*/
        public void setPageSize(int pageSize) {
                if (pageSize > 0) {
                        this.pageSize = pageSize;
                }
        }

        /**取得总行数*/
        public int getRowCount() {
                if (rowCount >= 0) {
                        return rowCount;
                } else {
                        return 0;
                }
        }

        /**设置总行数，不能小于1，不要随意设置总行数*/
        public void setRowCount(int rowCount) {
                if (rowCount >= 0) {
                        this.rowCount = rowCount;
                        this.pageCount = Math.round(rowCount / pageSize) + (rowCount%pageSize>0?1:0);
                        int pageCnt = getPageCount();
                        if (currentPage > pageCnt) {
                                if (pageCnt <= 0) {
                                        this.currentPage = 1;
                                } else {
                                        this.currentPage = pageCnt;
                                }
                        }
                }
        }

        /**
         * 重置分页信息至初始状态,与resetAll的不同之处在于保留currentPage
         *
         */
        public void reset() {
                rowCount = -1;
                pageCount = -1;
        }
        
        /**重置分页信息至初始状态，当前页码回到第1页，并将引发重新计算总行数和总页数*/
        public void resetAll() {
                rowCount = -1;
                pageCount = -1;
                currentPage = 1;
        }
        
    /**将分页信息对象转换成字符串*/
        public String toString() {
        return ToStringBuilder.reflectionToString(this,
                        ToStringStyle.MULTI_LINE_STYLE);
    }

	public int getsEcho() {
		return sEcho;
	}

	public void setsEcho(int sEcho) {
		this.sEcho = sEcho;
	}
        
}
