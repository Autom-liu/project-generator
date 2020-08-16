<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${basePackage}.${moduleName}.mapper.${domainObjectName}Mapper">
    <resultMap id="BaseResultMap" type="${basePackage}.${moduleName}.bean.${domainObjectName}">
        <#list columns as field>
        <#if field.isPrimary>
            <id column="${field.column}" jdbcType="${field.dataType}" property="${field.javaField}" />
        <#else >
            <result column="${field.column}" jdbcType="${field.dataType}" property="${field.javaField}" />
        </#if>
        </#list>
    </resultMap>
    <sql id="Where_Clause">
        <where>
            <trim prefix="(" prefixOverrides="and" suffix=")">
                <foreach collection="criteria.critertions" item="criterion">
                    <choose>
                        <when test="criterion.noValue">
                            and ${r'${criterion.fieldName}'} ${r'${criterion.operation}'}
                        </when>
                        <when test="criterion.betweenValue">
                            and ${r'${criterion.fieldName}'} ${r'${criterion.operation}'} ${r'#{criterion.value}'} and ${r'#{criterion.secondValue}'}
                        </when>
                        <when test="criterion.listValue">
                            and ${r'${criterion.fieldName}'} ${r'${criterion.operation}'}
                            <foreach close=")" collection="criterion.value" item="listItem" open="(" separator=",">
                                ${r'#{listItem}'}
                            </foreach>
                        </when>
                        <otherwise>
                            and ${r'${criterion.fieldName}'} ${r'${criterion.operation}'} ${r'#{criterion.value}'}
                        </otherwise>
                    </choose>
                </foreach>
            </trim>
        </where>
    </sql>

    <sql id="Select_Column_List">
        <if test="selectClauses != null">
            <foreach collection="selectClauses" item="selected" separator=",">
                <if test="selected.isDistinct">
                    distinct
                </if>
                ${r'${selected.fieldName}'}
            </foreach>
        </if>
    </sql>

    <sql id="Order_Column_List">
        <foreach collection="orderClauses" item="orderByClause" separator=",">
            ${r'${orderByClause.fieldName}'}
            <if test="orderByClause.isDesc">
                desc
            </if>
        </foreach>
    </sql>

    <select id="selectByExample" parameterType="com.edu.scnu.common.query.QueryBuilder" resultMap="BaseResultMap">
        select
        <include refid="Select_Column_List" />
        from idnt_app
        <if test="_parameter != null">
            <include refid="Where_Clause" />
        </if>
        <if test="orderClauses != null">
            order by <include refid="Order_Column_List" />
        </if>
        <if test="limitClause != null">
            limit ${r'#{limitClause.beginNum}'}, ${r'#{limitClause.fetchNum}'}
        </if>
    </select>

    <delete id="deleteByExample" parameterType="com.edu.scnu.common.query.QueryBuilder">
        delete from idnt_app
        <if test="_parameter != null">
            <include refid="Where_Clause" />
        </if>
    </delete>
    <select id="countByExample" parameterType="com.edu.scnu.common.query.QueryBuilder" resultType="java.lang.Long">
        select count(*) from idnt_app
        <if test="_parameter != null">
            <include refid="Where_Clause" />
        </if>
    </select>

</mapper>