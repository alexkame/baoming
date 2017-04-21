/**
 * 
 */
package com.thinkgem.jeesite.common.utils.excel;

import java.io.Serializable;

public class SubTitle implements Serializable {

    private static final long serialVersionUID = 1L;

    private String            name;
    private int               begin;
    private int               end;

    public int getBegin() {
        return begin;
    }

    public int getEnd() {
        return end;
    }

    public String getName() {
        return name;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public void setName(String name) {
        this.name = name;
    }

}