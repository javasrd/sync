package com.zc.base.web.ui.ext.tree;

import com.zc.base.core.mapper.JsonMapper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtTreeNode {
    private String id;
    private Boolean allowDrag;
    private Boolean allowDrop;
    private Boolean checked;
    private List<ExtTreeNode> children;
    private String cls;
    private Integer depth;
    private Boolean expandable;
    private Boolean expanded;
    private String href;
    private String hrefTarget;
    private String icon;
    private String iconCls;
    private String index;
    private Boolean isFirst;
    private Boolean isLast;
    private Boolean leaf;
    private Boolean loaded;
    private Boolean loading;
    private String parentId;
    private Integer qshowDelay;
    private String qtip;
    private String qtitle;
    private Boolean root;
    private String text;
    private Map<String, Object> attributes = new HashMap();

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getAllowDrag() {
        return this.allowDrag;
    }

    public void setAllowDrag(Boolean allowDrag) {
        this.allowDrag = allowDrag;
    }

    public Boolean getAllowDrop() {
        return this.allowDrop;
    }

    public void setAllowDrop(Boolean allowDrop) {
        this.allowDrop = allowDrop;
    }

    public Boolean getChecked() {
        return this.checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public List<ExtTreeNode> getChildren() {
        return this.children;
    }

    public void setChildren(List<ExtTreeNode> children) {
        this.children = children;
    }

    public String getCls() {
        return this.cls;
    }

    public void setCls(String cls) {
        this.cls = cls;
    }

    public Integer getDepth() {
        return this.depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public Boolean getExpandable() {
        return this.expandable;
    }

    public void setExpandable(Boolean expandable) {
        this.expandable = expandable;
    }

    public Boolean getExpanded() {
        return this.expanded;
    }

    public void setExpanded(Boolean expanded) {
        this.expanded = expanded;
    }

    public String getHref() {
        return this.href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getHrefTarget() {
        return this.hrefTarget;
    }

    public void setHrefTarget(String hrefTarget) {
        this.hrefTarget = hrefTarget;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconCls() {
        return this.iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getIndex() {
        return this.index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public Boolean getIsFirst() {
        return this.isFirst;
    }

    public void setIsFirst(Boolean isFirst) {
        this.isFirst = isFirst;
    }

    public Boolean getIsLast() {
        return this.isLast;
    }

    public void setIsLast(Boolean isLast) {
        this.isLast = isLast;
    }

    public Boolean getLeaf() {
        return this.leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    public Boolean getLoaded() {
        return this.loaded;
    }

    public void setLoaded(Boolean loaded) {
        this.loaded = loaded;
    }

    public Boolean getLoading() {
        return this.loading;
    }

    public void setLoading(Boolean loading) {
        this.loading = loading;
    }

    public String getParentId() {
        return this.parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getQshowDelay() {
        return this.qshowDelay;
    }

    public void setQshowDelay(Integer qshowDelay) {
        this.qshowDelay = qshowDelay;
    }

    public String getQtip() {
        return this.qtip;
    }

    public void setQtip(String qtip) {
        this.qtip = qtip;
    }

    public String getQtitle() {
        return this.qtitle;
    }

    public void setQtitle(String qtitle) {
        this.qtitle = qtitle;
    }

    public Boolean getRoot() {
        return this.root;
    }

    public void setRoot(Boolean root) {
        this.root = root;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Map<String, Object> toMap() throws IllegalArgumentException, IllegalAccessException {
        Map<String, Object> map = new HashMap();
        Field[] fields = ExtTreeNode.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if ((!"children".equals(field.getName())) && (!"attributes".equals(field.getName()))) {

                map.put(field.getName(), field.get(this));
            }
        }
        map.putAll(getAttributes());
        List<Map<String, Object>> childMaps = new ArrayList();
        if (this.children != null) {
            for (ExtTreeNode extTreeNode : this.children) {
                childMaps.add(extTreeNode.toMap());
            }
            map.put("children", childMaps);
        }
        return map;
    }

    public String toJson(JsonMapper jsonMapper) throws IllegalArgumentException, IllegalAccessException {
        return jsonMapper.toJson(toMap());
    }
}
