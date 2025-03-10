package com.example.data.analysis.utils;

import com.alibaba.fastjson2.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author zt
 * @Description TODO
 * @Time 2024/11/20 17:59
 */
public abstract class BaseEntity<T extends BaseEntity<?>> extends Model<T> {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("唯一标识")
    @JsonSerialize(
            using = ToStringSerializer.class
    )
    @JSONField(
            serializeUsing = com.alibaba.fastjson.serializer.ToStringSerializer.class
    )
    @TableId(
            value = "id",
            type = IdType.ASSIGN_ID
    )
    private Long id;
    @ApiModelProperty(value = "创建者",hidden = true)
    @TableField(
            fill = FieldFill.INSERT
    )
    private String createBy;
    @JsonFormat(
            timezone = "GMT+8",
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @ApiModelProperty(value = "创建时间",hidden = true)
    @TableField(
            fill = FieldFill.INSERT
    )
    @JSONField(
            format = "yyyy-MM-dd HH:mm:ss"
    )
    private Date createTime;
    @ApiModelProperty(value = "更新者",hidden = true)
    @TableField(
            fill = FieldFill.INSERT_UPDATE
    )
    private String updateBy;
    @JsonFormat(
            timezone = "GMT+8",
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @ApiModelProperty(value = "更新时间",hidden = true)
    @TableField(
            fill = FieldFill.INSERT_UPDATE
    )
    @JSONField(
            format = "yyyy-MM-dd HH:mm:ss"
    )
    private Date updateTime;
    @ApiModelProperty(value = "删除标志 默认0",hidden = true)
    @TableLogic
    private String delFlag = "0";

    public BaseEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public String getCreateBy() {
        return this.createBy;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public String getUpdateBy() {
        return this.updateBy;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public String getDelFlag() {
        return this.delFlag;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setCreateBy(final String createBy) {
        this.createBy = createBy;
    }

    @JsonFormat(
            timezone = "GMT+8",
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateBy(final String updateBy) {
        this.updateBy = updateBy;
    }

    @JsonFormat(
            timezone = "GMT+8",
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    public void setUpdateTime(final Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setDelFlag(final String delFlag) {
        this.delFlag = delFlag;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof BaseEntity)) {
            return false;
        } else {
            BaseEntity<?> other = (BaseEntity) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$id = this.getId();
                Object other$id = other.getId();
                if (this$id == null) {
                    if (other$id != null) {
                        return false;
                    }
                } else if (!this$id.equals(other$id)) {
                    return false;
                }

                Object this$createBy = this.getCreateBy();
                Object other$createBy = other.getCreateBy();
                if (this$createBy == null) {
                    if (other$createBy != null) {
                        return false;
                    }
                } else if (!this$createBy.equals(other$createBy)) {
                    return false;
                }

                Object this$createTime = this.getCreateTime();
                Object other$createTime = other.getCreateTime();
                if (this$createTime == null) {
                    if (other$createTime != null) {
                        return false;
                    }
                } else if (!this$createTime.equals(other$createTime)) {
                    return false;
                }

                label62:
                {
                    Object this$updateBy = this.getUpdateBy();
                    Object other$updateBy = other.getUpdateBy();
                    if (this$updateBy == null) {
                        if (other$updateBy == null) {
                            break label62;
                        }
                    } else if (this$updateBy.equals(other$updateBy)) {
                        break label62;
                    }

                    return false;
                }

                label55:
                {
                    Object this$updateTime = this.getUpdateTime();
                    Object other$updateTime = other.getUpdateTime();
                    if (this$updateTime == null) {
                        if (other$updateTime == null) {
                            break label55;
                        }
                    } else if (this$updateTime.equals(other$updateTime)) {
                        break label55;
                    }

                    return false;
                }

                Object this$delFlag = this.getDelFlag();
                Object other$delFlag = other.getDelFlag();
                if (this$delFlag == null) {
                    if (other$delFlag != null) {
                        return false;
                    }
                } else if (!this$delFlag.equals(other$delFlag)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof BaseEntity;
    }

    public int hashCode() {
        int result = 1;
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $createBy = this.getCreateBy();
        result = result * 59 + ($createBy == null ? 43 : $createBy.hashCode());
        Object $createTime = this.getCreateTime();
        result = result * 59 + ($createTime == null ? 43 : $createTime.hashCode());
        Object $updateBy = this.getUpdateBy();
        result = result * 59 + ($updateBy == null ? 43 : $updateBy.hashCode());
        Object $updateTime = this.getUpdateTime();
        result = result * 59 + ($updateTime == null ? 43 : $updateTime.hashCode());
        Object $delFlag = this.getDelFlag();
        result = result * 59 + ($delFlag == null ? 43 : $delFlag.hashCode());
        return result;
    }

    public String toString() {
        return "BaseEntity(id=" + this.getId() + ", createBy=" + this.getCreateBy() + ", createTime=" + this.getCreateTime() + ", updateBy=" + this.getUpdateBy() + ", updateTime=" + this.getUpdateTime() + ", delFlag=" + this.getDelFlag() + ")";
    }
}
