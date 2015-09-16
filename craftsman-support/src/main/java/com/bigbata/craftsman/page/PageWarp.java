package com.bigbata.craftsman.page;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FilenameFilter;
import java.util.*;

/**
 * Created by lixianghui on 15-3-4.
 */
public class PageWarp {
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public String getViewPath() {
        return viewPath;
    }

    public void setViewPath(String viewPath) {
        this.viewPath = viewPath;
    }

    public String getLocalRootPath() {
        return localRootPath;
    }

    public void setLocalRootPath(String localRootPath) {
        this.localRootPath = localRootPath;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public FilenameFilter getJsFileNameFilter() {
        return jsFileNameFilter;
    }

    public void setJsFileNameFilter(FilenameFilter jsFileNameFilter) {
        this.jsFileNameFilter = jsFileNameFilter;
    }

    public FilenameFilter getCssFileNameFilter() {
        return cssFileNameFilter;
    }

    public void setCssFileNameFilter(FilenameFilter cssFileNameFilter) {
        this.cssFileNameFilter = cssFileNameFilter;
    }

    public List<String> getjsArray() {
        return jsArray;
    }

    public void setjsArray(List<String> jsArray) {
        this.jsArray = jsArray;
    }

    public List<String> getcssArray() {
        return cssArray;
    }

    public void setcssArray(List<String> cssArray) {
        this.cssArray = cssArray;
    }

    private String uri;//页面请求全路径
    private String rootPath;//页面应用上下文路径
    private String viewPath;//访问页面路径
    private String localRootPath;//程序所在系统文件目录
    private String localPath;//服务器本地路径
    private HttpServletRequest request;
    private FilenameFilter jsFileNameFilter = new FilenameFilter() {
        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(".js");
        }
    };
    private FilenameFilter cssFileNameFilter = new FilenameFilter() {
        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(".css");
        }
    };
    private List<String> jsArray;//本地js文件名称
    private List<String> cssArray;//本地css文件名称

    public List<String> getJsPathArray() {
        Collections.sort(jsPathArray);
        return jsPathArray;
    }

    public void setJsPathArray(List<String> jsPathArray) {
        this.jsPathArray = jsPathArray;
    }

    public List<String> getCssPathArray() {
        return cssPathArray;
    }

    public void setCssPathArray(List<String> cssPathArray) {
        this.cssPathArray = cssPathArray;
    }

    private List<String> jsPathArray = new ArrayList<>();//js文件路径
    private List<String> cssPathArray = new ArrayList<>();//css文件路径

    public PageWarp(HttpServletRequest request) {
        this.request = request;
        this.uri = request.getRequestURI();
        this.rootPath = request.getContextPath();
        this.viewPath = uri.substring(rootPath.length(), uri.length());
        if (this.viewPath.startsWith("/web")) {
            this.viewPath = "/app" + this.viewPath.substring(4, this.viewPath.length());
        }
        this.localRootPath = request.getSession().getServletContext().getRealPath("");
        this.localPath = this.localRootPath + this.viewPath;
        loadFiles();
    }

    public void loadFiles() {
        File file = new File(this.localPath);
        if (file.exists() && file.isDirectory()) {
            jsArray = Arrays.asList(file.list(jsFileNameFilter));
            cssArray = Arrays.asList(file.list(cssFileNameFilter));
        }
        if (jsArray != null && jsArray.size() > 0) {
            for (String js : jsArray) {
                jsPathArray.add(viewPath + "/" + js);
            }
        }
        if (cssArray != null && cssArray.size() > 0) {
            for (String css : cssArray) {
                cssPathArray.add(viewPath + "/" + css);
            }
        }
    }
}
