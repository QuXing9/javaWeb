package com.xing.util;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: PageSupport
 * @Desc: 用户管理业分页支持工具类
 * @package com.shme.util
 * @project smbms
 * @date 2020/7/7 19:26
 */
public class PageSupport {

    //数据库中数据总条目数
    private int totalDataNumber;

    //当前页码
    private int currentPageCode;

    //每页显示条目数(页面大小)
    private int pageSize;

    //总页数
    private int PageNumber;


    //页码getter
    public int getCurrentPageCode() {
        return currentPageCode;
    }

    //页码setter
    public void setCurrentPageCode(int currentPageCode) {
        //判断输入页码是否大于0
        if (currentPageCode > 0 && currentPageCode <= PageNumber) {
            this.currentPageCode = currentPageCode;
        }
    }


    //页面大小getter
    public int getPageSize() {
        return pageSize;
    }

    //页面大小setter
    public void setPageSize(int pageSize) {
        //判断页面大小是否大于零
        if (pageSize > 0) {
            this.pageSize = pageSize;
        }
    }


    //页数getter
    public int getPageNumber() {
        return PageNumber;
    }

    //页数setter
    public void setPageNumber(int pageNumber) {
        PageNumber = pageNumber;
    }


    //数据总数getter
    public int getTotalDataNumber() {
        return totalDataNumber;
    }

    //数据总数setter
    public void setTotalDataNumber(int totalDataNumber) {
        //判断totalDataNumber是否大于零
        if (totalDataNumber > 0) {
            this.totalDataNumber = totalDataNumber;
            setPageNumberByRs();
        }
    }


    //计算查询的数据能够显示的总页数
    public void setPageNumberByRs() {

        //判断是否定义了pageSize
        if (pageSize > 0) {
            /**
             * 能走到这里说明`totalDataNumber>0&&pageSize>0`
             * 那么相除的结果:要么为零,要么大于零
             */
            if (totalDataNumber % pageSize == 0) { //刚好除尽
                PageNumber = totalDataNumber / pageSize;
            } else/* (totalDataNumber % pageSize > 0) */{ //有余数,总页数加一页
                PageNumber = (totalDataNumber / pageSize) + 1;
            }
        } else {
            PageNumber = 0;
        }
    }
}
