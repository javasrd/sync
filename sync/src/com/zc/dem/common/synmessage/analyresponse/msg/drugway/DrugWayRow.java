package com.zc.dem.common.synmessage.analyresponse.msg.drugway;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class DrugWayRow
{
    /**
     * 用药方法id
     */
    @XmlElement(name = "ID")
    private String id;
    
    /**
     * 用药方法编码
     */
    @XmlElement(name = "Code")
    private String code;
    
    /**
     * 用药方法名字
     */
    @XmlElement(name = "Name")
    private String name;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    
}
