package com.zc.base.web.ui.jquery.zTree;

import com.zc.base.core.mapper.JsonMapper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ZTreeNode {
    private Boolean checked;
    private List<ZTreeNode> children;
    private Boolean chkDisabled;
    private String click;
    private Boolean halfCheck;
    private String icon;
    private String iconClose;
    private String iconOpen;
    private String iconSkin;
    private Boolean isHidden;
    private Boolean isParent;
    private String name;
    private Boolean nocheck;
    private Boolean open;
    private String target;
    private String url;
    private Map<String, Object> diyAttributes = new HashMap();


    protected Map<String, Object> toMap()
            throws IllegalArgumentException, IllegalAccessException {
        Map<String, Object> map = new HashMap();
        Field[] fields = ZTreeNode.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if ((!"children".equals(field.getName())) && (!"diyAttributes".equals(field.getName()))) {

                map.put(field.getName(), field.get(this));
            }
        }
        map.putAll(getDiyAttributes());
        List<Map<String, Object>> childMaps = new ArrayList();
        if (this.children != null) {
            for (ZTreeNode extTreeNode : this.children) {
                childMaps.add(extTreeNode.toMap());
            }
            map.put("children", childMaps);
        }
        return map;
    }


    public String toJson(JsonMapper jsonMapper)
            throws IllegalArgumentException, IllegalAccessException {
        return jsonMapper.toJson(toMap());
    }

    public Boolean getChecked() {
        return this.checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public List<ZTreeNode> getChildren() {
        return this.children;
    }

    public void setChildren(List<ZTreeNode> children) {
        this.children = children;
    }

    public Boolean getChkDisabled() {
        return this.chkDisabled;
    }

    public void setChkDisabled(Boolean chkDisabled) {
        this.chkDisabled = chkDisabled;
    }

    public String getClick() {
        return this.click;
    }

    public void setClick(String click) {
        this.click = click;
    }

    public Boolean getHalfCheck() {
        return this.halfCheck;
    }

    public void setHalfCheck(Boolean halfCheck) {
        this.halfCheck = halfCheck;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconClose() {
        return this.iconClose;
    }

    public void setIconClose(String iconClose) {
        this.iconClose = iconClose;
    }

    public String getIconOpen() {
        return this.iconOpen;
    }

    public void setIconOpen(String iconOpen) {
        this.iconOpen = iconOpen;
    }

    public String getIconSkin() {
        return this.iconSkin;
    }

    public void setIconSkin(String iconSkin) {
        this.iconSkin = iconSkin;
    }

    public Boolean getIsHidden() {
        return this.isHidden;
    }

    public void setIsHidden(Boolean isHidden) {
        this.isHidden = isHidden;
    }

    public Boolean getIsParent() {
        return this.isParent;
    }

    public void setIsParent(Boolean isParent) {
        this.isParent = isParent;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getNocheck() {
        return this.nocheck;
    }

    public void setNocheck(Boolean nocheck) {
        this.nocheck = nocheck;
    }

    public Boolean getOpen() {
        return this.open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public String getTarget() {
        return this.target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Object> getDiyAttributes() {
        return this.diyAttributes;
    }

    public void setDiyAttributes(Map<String, Object> diyAttributes) {
        this.diyAttributes = diyAttributes;
    }
}
