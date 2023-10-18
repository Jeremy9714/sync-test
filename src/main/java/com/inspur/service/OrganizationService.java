package com.inspur.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 组织机构服务接口
 * @author 白凯
 * @date 2014-9-10
 * @version 
 * @description 组织机构服务管理
 */
public interface OrganizationService {
	/**
	 * 
	 * @param map
	 * map中参数{
	 * CODE:区划编码
	 * NAME:区划名称
	 * GRADE:区划等级(1:国家级,2:省级,3:市级,4:区县级,5:乡镇级,6:街道级)
	 * TYPE:区划类型(1:区划,0:省直，市直等)
	 * PARENT_CODE:上级编码
	 * SORT_ORDER:区划排序
	 * CREATOR:创建人
	 * CREATE_TIME:创建时间
	 * REMARK:备注信息
	 * }
	 * @return
	 */
	public Boolean saveRegionInfo(Map<String,Object> map);
	/**
	 * 获取所有区划数据
	 * @function 返回所有区划数据信息，主要包含区划编码，父级编码，区划名称;
	 * @example JSONObject regionArray = getAllRegion();
	 * @return [{code:"420000",parent:"#",name:"湖北省"},{code:"420100",parent:"420000",name:"武汉市"},{code:"421100",parent:"420000",name:"黄冈市"}...]
	 */
	/**
	 * 该接口在实现中没有方法体
	 * @return
	 */
	public JSONArray getAllRegion();
	/**
	 * 根据区划编码读取下级区划列表
	 * @param regionCode 父区划编码
	 * @param defaultTree 是否默认树（默认树显示市直、省直机关节点）
	 * @return [{CODE:"420000",PARENT_CODE:"#",NAME:"湖北省",PARENT_NAME:"",TYPE:"0|1",GARDE:"1|2|3|4|5|6",CHILDS:0}]
	 */
	public JSONObject getRegionByParentCode(String regionCode,boolean defaultTree);
	 /**
     * 通过字典类型获取字典基本信息
     *
     * @param dictKIND
     * @param app_code
     * @return {
     * 		"code": "响应码，成功200，失败300",
     *			"error": "失败错误信息，成功可为空",
     * 		"rows": [{
     * 			"id": "字典ID",
     * 			"code": "字典编码",
     * 			"name": "字典名称",
     *			"type": "字典类型",
     * 			"parent_code": "父级字典编码",
     *          "kind": "字典属性",
     *			"create_time": "创建时间",
     * 			"sort_order": "系统排序"
     *          
     *			}]
     * 	}
     */
	 public JSONObject getDictBasicInfo(String dictKIND,String app_code);
	 
	 /**
	     * 通过父级字典获取字典基本信息
	     *
	     * @param dictKIND 字典类型编码
	     * @param app_code 应用编码
	     * @param parent_code 父级字典编码
	     * @return {
	     * 		"code": "响应码，成功200，失败300",
	     *			"error": "失败错误信息，成功可为空",
	     * 		"rows": [{
	     * 			"id": "字典ID",
	     * 			"code": "字典编码",
	     * 			"name": "字典名称",
	     *			"type": "字典类型",
	     * 			"parent_code": "父级字典编码",
	     *          "kind": "字典属性",
	     *			"create_time": "创建时间",
	     * 			"sort_order": "系统排序"
	     *          
	     *			}]
	     * 	}
	     */
	 public JSONObject getDictBasicInfoByParent(String dictKIND,String app_code,String parent_code);
	 
	 /**
	     *  通过字典类型以及字典编码获取字典名称
	     *
	     * @param dictKIND，dictCODE
	     * @return {
	     * 		"code": "响应码，成功200，失败300",
	     *			"error": "失败错误信息，成功可为空",
	     * 		"rows": [{
	     * 			"name": "字典名称"
	     *			}]
	     * 	}
	     */
	 public JSONObject getDictName(String dictKIND,String dictCODE);
	 /**
	     *  通过字典类型以及字典编码获取字典名称
	     *
	     * @param dictKIND，dictCODE
	     * @return {
	     * 		"code": "响应码，成功200，失败300",
	     *			"error": "失败错误信息，成功可为空",
	     * 		"rows": [{
	     * 			"name": "字典名称"
	     *			}]
	     * 	}
	     */
	 public JSONObject getBaseDictName(String dictKIND,String dictCODE);
	 /**
	     *  通过字典属性获取字典类型
	     *
	     * @param type
	     * @return {
	     * 		"code": "响应码，成功200，失败300",
	     *			"error": "失败错误信息，成功可为空",
	     * 		"rows": [{
	     *          "code": "字典编码"
	     * 			"name": "字典名称"
	     *			}]
	     * 	}
	     */
	 public JSONObject getDictType(String type);
	 /**
	     * 通过APP_CODE获取字典类型
	     *
	     * @param app_code
	     * @return {
	     * 		"code": "响应码，成功200，失败300",
	     *			"error": "失败错误信息，成功可为空",
	     * 		"rows": [{
	     * 			"name": "字典类型名称"
	     *         "code" : "字典类型编码"
	     *			}]
	     * 	}
	     */
	 public JSONObject getDictKind(String app_code);
	 /**
	  * app_code 为空返回公共字典类型
	  * app_code不为空返回公共字典类型和这个系统的自定义字典类型
	  * @param app_code
	  * @return{
	  * 		"code": "响应码，成功200，失败300",
	  *			"error": "失败错误信息，成功可为空",
	  * 		"rows": [{
	  * 			"name": "字典类型名称"
	  *         	"code" : "字典类型编码"
	  *			}]
	  * 	}
	  */
	 public JSONObject getDictConfigKindByAppCode(String app_code);
	 /**
	  * 获取代码集树
	  * type:代码集属性0：国家标准；1：地方标准，2：行业标准，3：自定义，多个属性以逗号隔开
	  * @param type
	  * @return{
	  * 		"code": "响应码，成功200，失败300",
	  *			"error": "失败错误信息，成功可为空",
	  * 		"rows": [{
	  * 			"id":"代码集ID",
	  * 			"name": "代码集名称",
	  *         	"code" : "代码集编码",
	  *         	"parent_id":"父级ID",
	  *         	"indicate":"代码集表示",
	  *         	"instruction":"代码集说明",
	  *         	"remark":"备注",
	  *         	"type":"代码集属性",
	  *         	"status":"代码集状态"
	  *			}]
	  * 	}
	  */
	 public JSONObject getDictKindByType(String type);
	 /**
      * 根据APP_CODE,KIND获取字典项
      * @param kind
      * @param page
      * @param size
      * @param appCode
      * @return
      */
	 public JSONObject getDictByKindAppCode(String kind,int page, int size,String appCode);
	 /**
	  * 获取区划扩展信息
	  *
	  * @param region_code
	  * @return {
	  * 		"code": "响应码，成功200，失败300",
	  *			"error": "失败错误信息，成功可为空",
	  * 		"rows": [{
	  * 			
	  * 	}
	  */
	 public JSONObject getRegionMap(String region_code);
	 /**
	  * 获取组织机构基本信息
	  *
	  * @param appCode
	  * @return {
	  * 		"code": "响应码，成功200，失败300",
	  *			"error": "失败错误信息，成功可为空",
	  * 		"rows": [{
	  * 			"id": "系统内码",
      * 			"code": "机构代码",
      * 			"name": "机构名称"
	  * 	}
	  */
	 public JSONObject getOrganInfo(String appCode);
	 /**
	  * 根据区划编码获取组织机构基本信息
	  *
	  * @param regionCode,appCode
	  * @return {
	  * 		"code": "响应码，成功200，失败300",
	  *			"error": "失败错误信息，成功可为空",
	  * 		"rows": [{
	  * 			"id": "系统内码",
	  * 			"code": "机构代码",
  	  * 			"name": "机构名称"
  	  * 	}
  	  */
	 public JSONObject getOrganInfo(String regionCode,String appCode);
	 /**
	  * 根据父级机构编码获取组织机构基本信息
	  *
	  * @param parentCode
	  * @return {
	  * 		"code": "响应码，成功200，失败300",
	  *			"error": "失败错误信息，成功可为空",
	  * 		"rows": [{
	  * 			"id": "系统内码",
  	  * 			"code": "机构代码",
  	  * 			"name": "机构名称"
  	  * 	}
  	  */
	 public JSONObject getOrgan(String parentCode);
	 /** *
	     * 根据组织机构代码获取组织机构名字
	     *
	     * @param Code
	     * @return {
	     * 		"code": "响应码，成功200，失败300",
	     *			"error": "失败错误信息，成功可为空",
	     * 		"rows": [{
      * 			"name": "机构名称"
	     * 	}
	     */
	 public JSONObject getOrganName(String code);
	 /** *
	     * 根据行政区划代码获取行政区划名称
	     *
	     * @param Code
	     * @return {
	     * 		"code": "响应码，成功200，失败300",
	     *			"error": "失败错误信息，成功可为空",
	     * 		"rows": [{
         * 			"name": "机构名称"
	     * 	}
	     */
	 public JSONObject getRegionInfoByRegionCode(String code);
	 /** *
	     * 根据当前区划CODE获取下级区划
	     *
	     * @param CODE
	     * @return {
	     * 		"code": "响应码，成功200，失败300",
	     *		"error": "失败错误信息，成功可为空",
	     * 		"rows": [{
	     *          "code": "区划代码"
         * 			"name": "区划名称"
	     * 	}
	     */
	public JSONObject getChildRegionByRegionCode(String code);
	 /** *
	     * 根据区划查询当前区划下的部门信息
	     *
	     * @param region_code
	     * @param app_code 
	     * @return {
	     * 		"code": "响应码，成功200，失败300",
	     *		"error": "失败错误信息，成功可为空",
	     * 		"rows": [{
	     *          "code": "区划代码"
         * 			"name": "区划名称"
	     * 	}
	     */
	public JSONObject getRegionOrgenByRegionCode(String region_code,String app_code);
	
	/** *
     * 根据区划查询当前区划下的部门信息
     *
     * @param region_code
     * @param app_code 
     * @param organType
     * @return {
     * 		"code": "响应码，成功200，失败300",
     *		"error": "失败错误信息，成功可为空",
     * 		"rows": [{
     *          "code": "区划代码"
     * 			"name": "区划名称"
     * 	}
     */
	public JSONObject getRegionOrgenByRegionCode(String region_code,String app_code,String organType);
	
	/**
     * 根据区划查询当前区划下的区划以及部门信息
     *
     * @param region_code
     * @return {
     * 		"code": "响应码，成功200，失败300",
     *		"error": "失败错误信息，成功可为空",
     * 		"organ": [{
     *          "code": "部门代码"
     * 			"name": "部门名称"
     * 		}],
     * 		"region":[{
     * 			"code": "区划代码"
     * 			"name": "区划名称"
     * 		}]
     */
	public JSONObject getRegionOrgenByRegionCode(String region_code);

	 /** *
	     * 根据部门CODE获取下级部门
	     *
	     * @param CODE
	     * @return {
	     * 		"code": "响应码，成功200，失败300",
	     *		"error": "失败错误信息，成功可为空",
	     * 		"rows": [{
	     *          "code": "部门代码"
         * 			"name": "部门名称"
         *          "parent_code": "父级机构"
         *          
	     * 	}
	     */
	 public JSONObject getChildOrganByOrganCode(String code);
	 /**
	  * 根据sourceDate和day获取倒退工作日
	  *
	  * @param sourceDate
	  * @param day
	  * @return 
	  *          "targetDate": "倒退的工作日"
	  */
	 public Date getWorkBackDate(Date sourceDate,int day);
	 /**
	  * 根据sourceDate和day获取下一个工作日
	  *
	  * @param sourceDate
	  * @param day
	  * @return 
	  *          "targetDate": "下一个工作日"
	  */
	 public Date getWorkDate(Date sourceDate,int day);
	 /**
	  * 根据DateFirst和DayLast获取之间的工作日
	  *
	  * @param DateFirst,DateLast
	  * @param day
	  * @return 
	  *          "day": "工作日"
	  */
	 public int getWorkDay(Date DateFirst,Date DateLast);
	 /**
	  * 根据当前Date里面的日期判断是否工作日
	  * @param sourceDate
	  * @param day
	  * @return 
	  *          "targetDate": "下一个工作日"
	  */
	 public int isWorkDate(Date sourceDate);
	 /**
	  * 法制监督平台获取区划信息
	  */
	 public JSONObject getOrgancode(int status);
	public Map<String, Object> getOrgIdByBspCode(String bsp_code);
	 /**
	  * 根据区划代码获取默认组织机构视图
	  * @param app_code 系统编码
	  * @param regionCode 区划编码
	  * @return 
	  * 	{
	  * 		"code": "响应码，成功200，失败300",
	  *		"error": "失败错误信息，成功可为空",
	  * 		"organ": [{
	  *				"CODE": "编码"
      * 			"NAME": "简称"
      * 			"FULL_NAME":"全称"
      * 			"ORGAN_TYPE":"部门类型，0：部门，1：处室，2：科室"
      * 			"SHORT_CODE":"简码"
      *         	"PARENT_CODE": "父级机构",
      *         	"REGION_CODE": "区划代码",
      *         	"REGION_NAME": "区划名称",
      *         	"TYPE"："region|organ",
      *         	"ORGAN_LEVEL":"行政级别(1-6)",
      *             "REGION_TYPE":"区划类别，0:机关，1：区划，2：政府，3：党委，4：人大"
      *        }]
      *   }
	  */
	 public JSONObject getOrganTree(String app_code,String regionCode);
	 
	 /**
	  * 根据区划部门代码获取默认组织机构视图（模糊查询）
	  * @param app_code 系统编码
	  * @param regionCode 区划编码或部门编码
	  * @param type 模糊查询方式（"all":匹配部门区划；"region":匹配区划；"organ":"匹配部门"）
	  * @param name 名称
	  * @return 
	  * 	{
	  * 		"code": "响应码，成功200，失败300",
	  *		"error": "失败错误信息，成功可为空",
	  * 		"organ": [{
	  *				"CODE": "编码"
      * 			"NAME": "简称"
      * 			"FULL_NAME":"全称"
      * 			"ORGAN_TYPE":"部门类型，0：部门，1：处室，2：科室"
      * 			"SHORT_CODE":"简码"
      *         	"PARENT_CODE": "父级机构",
      *         	"REGION_CODE": "区划代码",
      *         	"REGION_NAME": "区划名称",
      *         	"TYPE"："region|organ",
      *         	"ORGAN_LEVEL":"行政级别(1-6)",
      *             "REGION_TYPE":"区划类别，0:机关，1：区划，2：政府，3：党委，4：人大"
      *         }]
      *    }
	  */
	 public JSONObject getOrganTree(String app_code,String regionCode,String type,String name);
	 
	 /**
	  * 根据区划代码获取默认组织机构视图
	  * @param app_code 系统编码
	  * @param regionCode 区划编码
	  * @param viewCode 视图编码
	  * @return 
	  * 	{
	  * 		"code": "响应码，成功200，失败300",
	  *		"error": "失败错误信息，成功可为空",
	  * 		"organ": [{
	  *				"CODE": "编码"
      * 			"NAME": "简称"
      * 			"FULL_NAME":"全称"
      * 			"ORGAN_TYPE":"部门类型，0：部门，1：处室，2：科室"
      * 			"SHORT_CODE":"简码"
      *         	"PARENT_CODE": "父级机构",
      *         	"REGION_CODE": "区划代码",
      *         	"REGION_NAME": "区划名称",
      *         	"TYPE"："region|organ",
      *         	"ORGAN_LEVEL":"行政级别(1-6)",
      *             "REGION_TYPE":"区划类别，0:机关，1：区划，2：政府，3：党委，4：人大"
      *   		}]
      *   }
	  */
	public JSONObject getOrganTree(String app_code, String regionCode, String viewCode);
	
	/**
	  * 根据组织机构视图获取机构
	  * @param app_code 系统编码
	  * @param viewCode 视图编码
	  * @return "code": "响应码，成功200，失败300",
	  *		    "error": "失败错误信息，成功可为空",
	  * 		"organ": [{
	  *				"CODE": "编码"
      * 			"NAME": "简称"
      * 			"FULL_NAME":"全称"
      * 			"ORGAN_TYPE":"部门类型，0：部门，1：处室，2：科室"
      * 			"SHORT_CODE":"简码"
      *         	"PARENT_CODE": "父级机构",
      *         	"REGION_CODE": "区划代码",
      *         	"REGION_NAME": "区划名称",
      *         	"TYPE"："region|organ",
      *         	"ORGAN_LEVEL":"行政级别(1-6)",
      *             "REGION_TYPE":"区划类别，0:机关，1：区划，2：政府，3：党委，4：人大"
      *         }]
	  */
	public JSONObject getOrganTreeByView(String app_code, String viewCode);
	 /**
	  * 获取机构类型信息
	  * @return "code": "响应码，成功200，失败300",
	  *		    "error": "失败错误信息，成功可为空",
	  * 		"rows": [{
	  * 		"ID": "类型ID"
	  *         "CODE": "类型编码"
      * 		"NAME": "类型名称"
      *         }]
	  */
	public JSONObject getOrganType();
	/**
	 * 保存机构信息
	 * @param map
	 * @return
	 */
	public boolean saveOrganInfo(Map<String,Object> map,List<Map<String,Object>> list);
	/**
	 * 更新机构信息
	 * @param map
	 * @return
	 */
	public boolean updateOrganInfo(Map<String,Object> map);
	/**
	 * 删除机构信息
	 * @param id
	 * @return
	 */
	public boolean delOrganInfo(Map<String,Object> map);
	 /**
	  * 获取机构类型信息
	  * @param id 机构ID
	  * @return "code": "响应码，成功200，失败300",
	  *		    "error": "失败错误信息，成功可为空",
	  * 		"rows": [{
	  * 		"ID": "机构ID"
	  *         "CODE": "机构编码"
      * 		"NAME": "机构名称"
      * 		"SHORT_NAME": "机构简称"
      *  		"ICON": "机构图标"
      *  		"TYPE": "机构类型"
      *    		"SORT_ORDER": "排序"
      *     	"PHONE": "机构电话"
      *      	"EMAIL": "机构邮箱"
      *       	"ADDRESS": "机构地址"
      *        	"HOME_PAGE": "机构网址"
      *         "ZIP_CODE": "邮政编码"
      *         "LEADER": "负责人"
      *         "REMARK": "职能描述"
      *         "STATUS": "状态"
      *         }]
	  */
	public JSONObject getOrganMap(String id);
	 /**
	  * 获取机构信息
	  * @param pid 上级编码
	  * @return "code": "响应码，成功200，失败300",
	  *		    "error": "失败错误信息，成功可为空",
	  * 		"rows": [{
	  * 		"ID":"机构ID"
	  *         "CODE": "机构编码"
	  * 		"SHORT_NAME": "机构简称"
	  *         }]
	  */
	public JSONObject getOrganByPid(String pid);
	/**
	  * 验证机构编码是否存在
	  * @param code 机构编码
	  * @param id      机构ID
	  * @return "code": "响应码，成功200，失败300",
	  *		    "error": "失败错误信息，成功可为空",
	  * 		"rows": [{
	  * 		"ID":"机构ID"
	  *         "NAME": "机构名称"
	  * 		"CODE": "机构编码"
	  *         }]
	  */
	public JSONObject checkOrganAccount(String code,String id);
	/**
	  * 获取部门下用户
	  * @param org_code 机构编码
	  * @return "code": "响应码，成功200，失败300",
	  *		    "error": "失败错误信息，成功可为空",
	  * 		"rows": [{
	  * 		"ID":"用户ID"
	  *         "NAME": "用户名称"
	  * 		"PARENT_CODE": "机构编码"
	  * 		"PARENT_NAME": "机构名称"
	  *         }]
	  */
	public JSONObject getPostUserTree(String org_code);
	/**
	 * 获取部门信息
	 * @param pid 机构编码
	 * @return 
	 * 	{
	 * 		"code": "响应码，成功200，失败300",
	 *		"error": "失败错误信息，成功可为空",
	 * 		"rows": [{
	 * 			"ID":"机构ID"
	 *         	"NAME": "机构名称"
	 * 			"CHILDS": "子集个数"
	 * 			"ORGAN_TYPE": "机构类型"
	 * 			"ORGAN_LEVEL": "机构级别"
	 * 			"REGION_CODE": "区划编码"
	 * 			"REGION_NAME": "区划名称"
	 *         }]
	 *   }
	 */
	public JSONObject getOrganInfoById(String pid);
	/**
	 * @param regionCode 
	 *  根据regionCode返回具体区划,flag为true时返回当前区划及区划下的机构，否则返回以regionCode为父级的区划及区划下的机构
	 */
	public List<Map<String,Object>> findOrganRegionTree(String regionCode,boolean flag);
	/**
	 * @param regionCode 
	 *  根据regionCode分页返回当前区划及区划下的机构
	 */
	public JSONObject findOrganRegionList(int page,int rows,String regionCode,String organ_name);
	/**
	 * 获取岗位分页信息
	 * @param region_code 区划编码
	 * @param org_code 机构编码
	 * @param name 岗位名称
	 * @param type 岗位类型(0：私有，1：公有)
	 * @param status 岗位状态(0：无效，1：有效)
	 * @param page 页码
	 * @param size 一页显示条数
	 * @return 
	 * 	{
	 * 	"code": "响应码，成功200，失败300",
	 *		"error": "失败错误信息，成功可为空",
	 * 	"rows": [{
	 * 			"ID":"岗位ID"
	 *         	"NAME": "岗位名称"
	 * 			"PARENT_CODE": "父级岗位ID"
	 * 			"TYPE": "岗位类型"
	 * 			"REGION_CODE":"所属区划"
	 * 			"ORGAN_CODE": "所属机构"
	 * 			"REMARK": "岗位描述"
	 * 			"STATUS": "状态"
	 *         }]
	 *   }
	 */
	public JSONObject getPostPage(String region_code,String org_code,String name, String type, String status,int page, int size);

	/**
	 * 获取部门变更合并记录信息
	 * @param org_code 机构编码
	 * @return 
	 * 	{
	 * 	"code": "响应码，成功200，失败300",
	 *		"error": "失败错误信息，成功可为空",
	 * 	"rows": [{
	 * 			"ID":"岗位ID"
	 *         	"ORG_SRC_CODE": "部门原编码"
	 * 			"ORG_NEW_CODE": "部门新编码"
	 * 			"SORT_ORDER":"排序"
	 * 			"CREATOR": "变更人"
	 *             "CREATE_TIME": "变更时间"
	 * 			"REMARK": "备注"
	 * 			"STATUS": "状态"
	 *         }]
	 *   }
	 */
	public JSONObject getOrganHistory(String org_code);
	/**
	 * 根据部门ID返回部门信息
	 * @param organ
	 * @return
	 * {
	 * 	"code": "响应码，成功200，失败300",
	 *		"error": "失败错误信息，成功可为空",
	 * 	"rows": [{
	 * 			"ID":"部门ID"
	 *         	"CODE": "部门编码"
	 * 			"NAME": "部门名称"
	 * 			"ICON":"部门图标"
	 * 			"SHORT_NAME": "部门简称"
	 *         }]
	 * }
	 */
	public JSONObject getOrganInfoListByOrganIdList(String organ);
	/**
	 * 根据部门ID返回部门信息
	 * @param organ 
	 * @return
	 * {
	 * 	"code": "响应码，成功200，失败300",
	 *		"error": "失败错误信息，成功可为空",
	 * 	"rows": [{
	 * 			"ID":"部门ID"
	 *         	"CODE": "部门编码"
	 * 			"NAME": "部门名称"
	 * 			"ICON":"部门图标"
	 * 			"SHORT_NAME": "部门简称"
	 *         }]
	 * }
	 */
	public JSONObject getOrganInfoListByOrganCodeList(String organ);
	/**
	 * 分页获取区划信息列表
	 * @param page
	 * @param size
	 * @param map
	 * @return
	 */
	public JSONObject getRegionListByPage(int page, int size,Map<String,Object> map );
	/**
	 * 分页查询组织机构列表
	 * @param page
	 * @param size
	 * @param map
	 * @return
	 */
	public JSONObject getOrganListByPage(int page, int size,Map<String,Object> map);
	/**
	 * 获取机构类型
	 * @param status
	 * @return
	 */
	public JSONObject getOrganTypeList(String status);
	/**
	 * 通过organ的id获取父亲节点的信息
	 * @param id
	 * @return
	 */
	public JSONObject findOrganParent(String id);
	/**
	 * 保存组织机构信息
	 * @param organ
	 * @param organMap
	 * @param parent_code
	 * @param parent_type
	 * @param userCode
	 */
	public JSONObject saveOrganInfo(Object organ, Object organMap,
			String parent_code, String parent_type, String userCode);
	public JSONObject importListOrganInfo(Object organ, Object organMap,Map<String,Object> map) ;
	/**
	 * 删除组织机构
	 * @param id
	 * @param type
	 * @param parents
	 * @return
	 */
	public JSONObject deleteOrgan(String id, String type, String parents);
	/**
	 * 更新组织机构信息
	 * @param organBean
	 * @param organMapBean
	 * @param oldCode
	 * @return
	 */
	public JSONObject updateOrganInfo(Object organBean, Object organMapBean,
			String oldCode);
	/**
	 * 根据组织机构代码获取组织机构信息(SHORT_CODE为组织机构代码,ORGAN_CODE为统一社会信用代码)
	 * @param map
	 * @return
	 */
	public JSONObject getOrganInfoByOrganShortCode(Map<String,Object> map);
	/**
	 * 获取视图列表
	 * @param systemOn
	 * @return
	 */
	public JSONObject getViewList(String systemOn);
	/**
	 * 获取组织机构视图
	 * @param code
	 * @return
	 */
	public JSONObject findOrganView(String code);
	/**
	 * 更正组织机构信息
	 * @param oldCode
	 * @param organType
	 * @param name
	 * @param shortName
	 * @return
	 */
	public JSONObject alterOrganName(String oldCode, String organType,
			String name, String shortName);
	/**
	 * 组织机构合并
	 * @param map
	 * @param userId
	 * @return
	 */
	public JSONObject mergeOrgan(Map<String, Object> map, String userId);
	
	@SuppressWarnings("rawtypes")
	public JSONObject getOrganOrRegionList(Map map);
	
	/**
	 * 获取一级部门
     * @param map
	 * @return
	 */
	public JSONObject getOrganListByType(Map<String, Object> param);
	/**
	 * 获取区划和部门（省市对接接口用）
     * @param param
	 * @return
	 */
	public JSONObject getInterfaceRegionAndOrd(Map<String,Object> param);
	
	public List<Map<String,Object>> findIndexOrganRegionTree(String pid);
	/**
	 * <p>Title: OrganizationService.java</p> <br>
	 * <p>
	 * Description:  信息维护插入pub_organ_map表<br>
	 * @author<a href=mailto:jiangchunxiao@inspur.com></a><br>
	 * <p>
	 * @date 2018年09月05日 15:07:06<br/>
	 * <p>
	 * 
	 * @param 
	 *            
	 * @param 
	 * @return 
	 */
	public boolean insertOrganMap(Map<String,Object> param);
	/**
	 * <p>Title: OrganizationService.java</p> <br>
	 * <p>
	 * Description:  信息维护更新<br>
	 * @author<a href=mailto:jiangchunxiao@inspur.com></a><br>
	 * <p>
	 * @date 2018年09月05日 15:08:58<br/>
	 * <p>
	 * 
	 * @param 
	 *            
	 * @param 
	 * @return 
	 */
	public boolean updateOranMapByMap(Map<String,Object> param);
	/**
	 * <p>Title: OrganizationService.java</p> <br>
	 * <p>
	 * Description:  根据id查询组织机构Map对象<br>
	 * @author<a href=mailto:jiangchunxiao@inspur.com></a><br>
	 * <p>
	 * @date 2018年09月05日 15:25:21<br/>
	 * <p>
	 * 
	 * @param 
	 *            
	 * @param 
	 * @return 
	 */
	public JSONObject findOrganMap(String id);

	/**
	 * <p>Title: OrganizationService.java</p> <br>
	 * <p>
	 * Description:  根据区划查询下面所有的组织机构<br>
	 * @author<a href=mailto:lilianwei@inspur.com></a><br>
	 * <p>
	 * @date 2018年09月12日 14:44:21<br/>
	 * <p>
	 *
	 * @param
	 *
	 * @param
	 * @return
	 */
	public JSONObject getAllChildrenOrganByRegion(Map paramMap,int page,int size);

	/**
	 * <p>Title: OrganizationService.java</p> <br>
	 * <p>
	 * Description:  分页查询下面所有的组织机构<br>
	 * @author<a href=mailto:lilianwei@inspur.com></a><br>
	 * <p>
	 * @date 2018年11月26日 21:29:21<br/>
	 * <p>
	 *
	 * @param
	 *
	 * @param
	 * @return
	 */
	public JSONObject getAllChildrenOrganByPage(Map paramMap,int page,int size);

	/***
	 * 
	 * @Title: getOrgCodeByREGION_CODE   
	 * @Description: TODO(省级一下区划和卫士通组织机构合并后根据区划和orgcode查询)   
	 * @param: @param REGION_CODE
	 * @param: @param OrgCode
	 * @param: @return      
	 * 创建人：chen_ll   
	 * 创建时间：2018年9月22日 下午4:50:38 
	 * @return: Map<String,Object>      
	 * @throws
	 */
	public Map<String, Object> getOrgCodeByREGION_CODE(String REGION_CODE,String OrgCode);
	/***
	 * 
	 * @Title: findTREECODE   
	 * @Description: TODO(根据区划获取机构树)   
	 * @param: @param REGION_CODE
	 * @param: @return      
	 * 创建人：chen_ll   
	 * 创建时间：2018年9月22日 下午4:50:25 
	 * @return: JSONObject      
	 * @throws
	 */
	public JSONObject findTREECODE(String REGION_CODE);
	
	/***
	 * 
	 * @Title: getRegionCodeByShareorgid   
	 * @Description: TODO(根据机构id查询区划)   
	 * @param: @param share_org_id
	 * @param: @return      
	 * 创建人：chen_ll   
	 * 创建时间：2018年9月26日 下午5:04:50 
	 * @return: Map<String,Object>      
	 * @throws
	 */
	public Map<String, Object> getRegionCodeByShareorgid(String share_org_id);
	
	/***
	 * 
	 * @Title: getOrgCodeByregion_code   
	 * @Description: TODO(根据rid和市级以下区划合并查询)   
	 * @param: @param REGION_CODE
	 * @param: @param OrgCode
	 * @param: @return      
	 * 创建人：chen_ll   
	 * 创建时间：2018年9月22日 下午4:50:38 
	 * @return: Map<String,Object>      
	 * @throws
	 */
	public Map<String, Object> getOrgCodeByregion_code(String region_code,String OrgCode);
	/**
	 * 根据organid获取组织机构信息
	 * @param id
	 * @return
	 */
	public JSONObject getOrganById(String id);
	/**
	 * 获取行政区划树
	 * @param parent_code
	 * @param defaultTree 
	 * @return
	 */
	public List<Map<String, Object>> findRegionTree(String parent_code, boolean defaultTree);
}
