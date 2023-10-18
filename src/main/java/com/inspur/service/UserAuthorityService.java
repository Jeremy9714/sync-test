package com.inspur.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.scheduling.annotation.Async;

import com.alibaba.fastjson.JSONObject;

/**
 * 用户权限服务接口
 * @author 王震
 * @date 2016-9-12
 * @version 1.0
 * @description 用户权限服务管理
 */
public interface UserAuthorityService {
	/**
	 * 用户单点登录
	 * @param user
	 * @param appCode
	 * @return {
     * 		"state": "响应码，成功200，失败300",
     *		"error": "失败错误信息，成功可为空",
     * 		"menus":"用户菜单",
     * 		"role_code":"角色编码",
     * 		"role_value":"角色值",
     * 		"role_weight":"角色权重"
     * 	}
	 */
	public JSONObject userSSOLogin(String userId,String appCode);
	/**
	 * 用户登录
	 * @param account 登录账号
	 * @param password 登录密码（md5加密）
	 * @param appCode 应用编码
	 * @param flag 是否获取菜单
	 * @return
	 * @throws Exception 
	 */
	public JSONObject userLogin(String account,String password,String appCode,boolean flag);
	
	/**
	 * 用户登录[国际化支持]
	 * @param account 登录账号
	 * @param password 登录密码（md5加密）
	 * @param appCode 应用编码
	 * @param locale 国际化语言[默认zh_CN]
	 * 	中文：zh_CN
	 * 	英文：en_US
	 * 	西班牙语：es_ES
	 * 	俄语：ru_RU
	 * 	德语：de_DE
	 * 	法语：fr_FR
	 * 	意大利语：it_IT
	 * 	韩语：ko_KR
	 * 	日语：ja_JP
	 * 	阿拉伯语：ar_SA
	 * 	其他自定义语言支持
	 * @return
	 * @throws Exception 
	 */
	public JSONObject userNationalLogin(String account,String password,String appCode,String locale);
	/**
	 * 开发者账号登录
	 * @param account 登录账号
	 * @param password 登录密码（md5加密）
	 */
	public JSONObject developerLogin(String account,String password);
	/**
	 * 用户退出
	 * @param userId
	 * @return
	 */
	public JSONObject userLogout(String userId);
	
	/**
	 * 用户行为审计
	 * @param json
	 */
	@Async
	public void saveLogin(JSONObject json);
	
	/**
	 * 用户登录密码错误审计
	 * @param json
	 */
	@Async
	public void saveLoginLock(JSONObject json);
	
	/**
	 * 更新用户最后登录时间
	 * @param userId
	 */
	public void updateLoginTime(String userId);
	/**
	 * 返回用户应用资源信息
	 * @param roleCode 角色编码
	 * @param appCode 应用编码
	 * @return
	 */
	public JSONObject getUserAppResource(String roleCode,String appCode);
	/**
	 * 返回用户角色信息
	 * @param userId
	 * @return [响应码code:200成功,300失败]
	 *   成功返回：{code:"200",rows:[
	 *   												{id:"001",name:"管理员",value:"ROLE_ADMIN",app:"INSPUR-DZZW-SXGL"},
	 *   												{id:"002",name:"普通用户",value:"ROLE_USER",app:"INSPUR-DZZW-SXGL"}]}
	 *   失败返回：{code:"300",error:"错误信息!"}
	 */
	public JSONObject getUserRoles(String userId,int page,int size);
	
	/**
	 * 返回用户角色信息
	 * @param userId
	 * @return [响应码code:200成功,300失败]
	 *   成功返回：{code:"200",rows:[
	 *   												{id:"001",name:"管理员",value:"ROLE_ADMIN",app:"INSPUR-DZZW-SXGL"},
	 *   												{id:"002",name:"普通用户",value:"ROLE_USER",app:"INSPUR-DZZW-SXGL"}]}
	 *   失败返回：{code:"300",error:"错误信息!"}
	 */
	public JSONObject getUserRoles(String userId);
	
	/**
	 * 通过用户名称返回用户基本信息
	 * @param userId
	 * @return [响应码code:200成功,300失败]
	 *   成功返回：{code:"200",rows:[
	 *   												{id:"001",name:"管理员",value:"ROLE_ADMIN",app:"INSPUR-DZZW-SXGL"},
	 *   												{id:"002",name:"普通用户",value:"ROLE_USER",app:"INSPUR-DZZW-SXGL"}]}
	 *   失败返回：{code:"300",error:"错误信息!"}
	 */
	public JSONObject getUserInfoByUserName(String userName,int page,int size);
	
	/**
	 * 通过用户名称返回用户基本信息
	 * @param userId
	 * @return [响应码code:200成功,300失败]
	 *   成功返回：{code:"200",rows:[
	 *   												{id:"001",name:"管理员",value:"ROLE_ADMIN",app:"INSPUR-DZZW-SXGL"},
	 *   												{id:"002",name:"普通用户",value:"ROLE_USER",app:"INSPUR-DZZW-SXGL"}]}
	 *   失败返回：{code:"300",error:"错误信息!"}
	 */
	public JSONObject getUserInfoByUserName(String userName,String dept,int page,int size);
	
	
	/**
	 * 返回用户应用列表信息
	 * @param userId
	 * @return [响应码code:200成功,300失败]
	 * 	成功返回：{code:"200",rows:[
	 * 													{id:"001",code:"INSPUR-DZZW-SXGL",name:"事项管理系统",url:"http://127.0.0.1/sxgl",type:"0",join_code:"0",language:"java",logo:"sxgl.png"},
	 *														{id:"002",code:"INSPUR-DZZW-XZSP",name:"行政审批系统",url:"http://127.0.0.1/xzsp",type:"0",join_code:"0",language:"java",logo:"xzsp.png"},
	 * 													{id:"003",code:"INSPUR-DZZW-XZCF",name:"行政处罚系统",url:"http://127.0.0.1/xzcf",type:"0",join_code:"0",language:"java",logo:"xzcf.png"}]};
	 * 	失败返回：{code:"300",error:"错误信息!"}
	 */
	public JSONObject getUserApps(String userId,String type);
	
	/**
	 * 返回用户组件商店组件
	 * @param userId
	 * @return  [响应码code:200成功,300失败]
	 * 	成功返回：{code:"200",rows:[
	 * 													{ type:"XZZX",
	 * 															item:[{id:"001",code:"INSPUR-DZZW-ZWWP",name:"政务网盘",url:"http://127.0.0.1/zwwp",type:"0",join_code:"0",language:"java",logo:"zwwp.png"},
	 *															  			  {id:"001",code:"INSPUR-DZZW-ZWQD",name:"政务千度",url:"http://127.0.0.1/zwqd",type:"0",join_code:"0",language:"java",logo:"zwqd.png"},
	 * 														 			  {id:"002",code:"INSPUR-DZZW-ZWZD",name:"政务知道",url:"http://127.0.0.1/zwzd",type:"0",join_code:"0",language:"java",logo:"zwzd.png"}]},
	 * 													{ type:"JKZX",
	 * 															item:[{id:"001",code:"INSPUR-DZZW-YYJK",name:"应用监控",url:"http://127.0.0.1/yyjk",type:"0",join_code:"0",language:"java",logo:"yyjk.png"} }]}
	 * 	失败返回：{code:"300",error:"错误信息!"}
	 */
	public JSONObject getUserShops(String userId);
	
	 /**
     * 通过用户编码(系统内码)获取用户基本信息
     *
     * @param userID
     * @return {
     * 		"code": "响应码，成功200，失败300",
     *			"error": "失败错误信息，成功可为空",
     * 		"rows": [{
     * 			"id": "系统内码",
     * 			"account": "登录名",
     * 			"name": "名称",
     *			"identity_num": "身份证号码",
     * 			"birthday": "出生日期",
     * 			"gender": "性别",
     * 			"email": "邮箱地址",
     * 			"mobile": "联系电话",
     * 			"phone": "办公电话",
     *          "region_code":"区划代码",
     *          "region_name":"区划名称",
     *          "org_code":"所属机关代码",
     * 			"org_name":"所属机关",
     * 			"inner_org_name":"内部运行机构,处室或科室",
     * 			"position": "职位"
     *			}]
     * 	}
     */
    public JSONObject getUserBasicInfo(String userID);
    
    /**
     * 根据开发者编码获取开发者基本信息
     *  
     * @param userId
     * @return {
     * 		"code": "响应码，成功200，失败300",
     *			"error": "失败错误信息，成功可为空",
     * 		"rows": [{
     * 			"id": "系统内码",
     * 			"account": "登录名(账号)",
     * 			"name": "名称",
     * 			"email": "邮箱地址",
     * 			"mobile": "联系电话",
     * 			"phone": "办公电话",
     *          "region_code":"区划代码",
     *          "region_name":"区划名称",
     *			}]
     */
    public JSONObject getDeveloperBasicInfo(String userId);
    
    /**
     * 更新用户基本信息。
     * 修改内容只包括人的自然信息和社会属性，与系统、组织相关的信息，暂时不允许修改
     * @param userInfo {
     *                 "id": "系统内码",
     *                 "name": "名称",
     *                 "identity_num": "身份证号码",
     *                 "birthday": "出生日期",
     *                 "gender": "性别",
     *                 "email": "邮箱地址",
     *                 "mobile": "联系电话",
     *                 "phone": "办公电话",
     *                 }
     * @return {
     * "code": "响应码，成功200，失败300"
     * "error": "失败错误信息，成功可为空",
     * }
     */
    public JSONObject updateUserBasicInfo(JSONObject userInfo);
    
    /**
     * 更新用户信息
     * @param userInfo
     * ID:用户ID
     * PHONE:电话
     * @return
     */
    public Boolean updateUserBasicInfoById(Map<String,Object> userInfo);

    /**
     * 获取用户某个应用的角色集合
     * @param userId
     * @param appCode
     * @return
     * 	 {
     * 		"code": "响应码，成功200，失败300",
     *			"error": "失败错误信息，成功可为空",
     * 		"rows": [{
     * 			"ID": "角色编码",
     * 			"NAME": "角色名称",
     * 			"VALUE": "角色值",
     * 			"STATUS": "状态"
     *			}]
     * 	}
     */
    public JSONObject getUserAppRole(String userId,String appCode);

    /**
     * 读取用户履历信息
     * @param userId
     * @param page
     * @param size
     * @return
     * 	{
     * 		"code": "响应码，成功200，失败300",
     *			"error": "失败错误信息，成功可为空",
     *			"page":1,
     *			"total":10,
     * 		"rows": [{
     * 			"ID": "编码",
     * 			"NAME": "主题",
     * 			"CREATE_TIME": "评价时间",
     * 			"STATUS": "状态"
     *			}]
     * 	}
     */
    public JSONObject getUserResume(String userId,int page,int size);
    
    /**
     * 获取用户的评价(用户评价信息)
     * @param userId
     * @return
     * 	 {
     * 		"code": "响应码，成功200，失败300",
     *			"error": "失败错误信息，成功可为空",
     *			"page":1,
     *			"total":10,
     * 		"rows": [{
     * 			"ID": "编码",
     * 			"NAME": "主题",
     * 			"CREATE_TIME": "评价时间",
     * 			"STATUS": "状态"
     *			}]
     * 	}
     */
    public JSONObject getUserEvaluate(String userId,int page,int size);
    
    
    /**
     * 获取用户的反馈(用户反馈信息)
     * @param userId
     * @return
     * 	 {
     * 		"code": "响应码，成功200，失败300",
     *			"error": "失败错误信息，成功可为空",
     *			"page":1,
     *			"total":10,
     * 		"rows": [{
     * 			"ID": "编码",
     * 			"NAME": "主题",
     * 			"CREATE_TIME": "反馈时间",
     * 			"STATUS": "状态"
     *			}]
     * 	}
     */
    public JSONObject getUserFeedback(String userId,String type,int page, int size);
	/**
    *根据APP_CODE 获取用户的反馈(用户反馈信息)
    * @param userId
    * @return
    * 	 {
    * 		"code": "响应码，成功200，失败300",
    *			"error": "失败错误信息，成功可为空",
    *			"page":1,
    *			"total":10,
    * 		"rows": [{
    * 			"ID": "编码",
    * 			"NAME": "主题",
    * 			"CREATE_TIME": "反馈时间",
    * 			"STATUS": "状态"
    *			}]
    * 	}
    */
	public JSONObject getUserFeedbackByAppCode(String userId,String type,int page, int size,String appCode);
    
	/**
     * 获取用户的足迹(历史登录信息)
     * @param userId
     * @return
     * 	 {
     * 		"code": "响应码，成功200，失败300",
     *			"error": "失败错误信息，成功可为空",
     *			"page":1,
     *			"total":10,
     * 		"rows": [{
     * 			"ID": "编码",
     * 			"NAME": "名称",
     * 			"DEVICE": "访问设备",
     * 			"STATUS": "状态"
     *			}]
     * 	}
     */
    public JSONObject getUserHistory(String userId,int page,int size);
    
    /**
     * 导出用户的足迹(历史登录信息)
     * @param param
     * @return
     * 	 {
     * 		"code": "响应码，成功200，失败300",
     *		"error": "失败错误信息，成功可为空",
     * 		"rows": [{
     * 			
     *			}]
     * 	}
     */
    public JSONObject exportUserHistory(Map<String,Object> param);
    
    /**
     * 获取用户的足迹(历史登录信息)
     * @param time 登录年月
     * @param userId 用户ID
     * @return
     * 	 {
     * 		"code": "响应码，成功200，失败300",
     *		"error": "失败错误信息，成功可为空",
     * 		"day": ["日期",""]，
     *      "count":[{
     *      	"CREATE_TIME":"日期",
     *          "COUNT":"次数"
     *      }]，
     * 	}
     */
    public JSONObject getUserHistoryCount(String time,String userId);
    
    /**
     * 通过用户邮箱或用户电话获取用户基本信息
     *
     * @param userID
     * @return {
     * 		"code": "响应码，成功200，失败300",
     *		"error": "失败错误信息，成功可为空",
     * 		"rows": [{
     * 			"id": "系统内码",
     * 			"name": "名称",
     *			
     *			}]
     * 	}
     */
    public JSONObject getUserInfo(String userEmail,String userMobile);
    
    /**
     * 根据系统内码更新用户邮箱
     *
     * @param userID
     * @return {
     * 		"code": "响应码，成功200，失败300",
     *		"error": "失败错误信息，成功可为空",
     * 		"rows": [{
     * 			"id": "系统内码",
     * 			"name": "名称",
     *			
     *			}]
     * 	}
     */
    public JSONObject updateUserEmail(String userId,String userEmail);
    
    /**
     * 根据系统内码更新用户邮箱
     *
     * @param userID
     * @return {
     * 		"code": "响应码，成功200，失败300",
     *		"error": "失败错误信息，成功可为空",
     * 		"rows": [{
     * 			"id": "系统内码",
     * 			"name": "名称",
     *			
     *			}]
     * 	}
     */
    public JSONObject updateUserMobile(String userId,String userMobile);
    
    /**
     * 根据系统内码更新用户密码
     *
     * @param userID
     * @return {
     * 		"code": "响应码，成功200，失败300",
     *		"error": "失败错误信息，成功可为空",
     * 		"rows": [{
     * 			"password": "用户密码",
     * 		
     *			
     *			}]
     * 	}
     */
    public JSONObject updateUserPassword(String userId,String userPassword);
    
    /**
     * 添加用户常用意见信息
     * @param opinionInfo {
     *                 "ID": "意见编码",
     *                 "USER_CODE": "用户编码",
     *                 "TYPE": "意见类型",
     *                 "OPINION": "意见内容",
     *                 "SORT_ORDER": "排序",
     *                 "CREATOR": "创建人编码",
     *                 "CREATE_TIME": "创建时间",
     *                 "REMARK": "备注信息",
     *                 "STATUS":"状态"
     *                 }
     * @return
     * 	 {
     * 		"code": "响应码，成功200，失败300",
     *		"error": "失败错误信息，成功可为空",
     * 	}
     */
    public JSONObject  userOpinionAdd(JSONObject opinionInfo);
    
    /**
     * 更新用户常用意见
     * @param opinionInfo {
     *                 "ID": "意见编码",
     *                 "USER_CODE": "用户编码",
     *                 "TYPE": "意见类型",
     *                 "OPINION": "意见内容",
     *                 "SORT_ORDER": "排序",
     *                 "LAST_EDITOR": "最后修改人编码",
     *                 "LAST_TIME": "最后修改时间",
     *                 "REMARK": "备注信息",
     *                 "STATUS":"状态"
     *                 }
     * @return
     * 	 {
     * 		"code": "响应码，成功200，失败300",
     *		"error": "失败错误信息，成功可为空",
     * 	}
     */
    public JSONObject  userOpinionUpdate(JSONObject opinionInfo);
    
    /**
     * 删除用户常用意见
     * @param opinionId
     * @return
     * 	 {
     * 		"code": "响应码，成功200，失败300",
     *		"error": "失败错误信息，成功可为空",
     * 	}
     */
    public JSONObject  userOpinionDelById(String opinionId);
    
    /**
     * 获取用户常用意见
     * @param opinionId
     * @return
     * 	 {
     * 		"code": "响应码，成功200，失败300",
     *		"error": "失败错误信息，成功可为空",
     * 		"rows": [{
     * 			"ID": "编码",
     * 			"USER_CODE": "用户编码",
     * 			"TYPE":"意见类型",
     * 			"OPINION":"意见内容",
     * 			"SORT_ORDER":"排序",
     * 			"LAST_EDITOR":"最后修改人编码",
     * 			"LAST_TIME": "最后修改时间",
     * 			"STATUS": "状态"
     *			}]
     * 	}
     */
    public JSONObject  userOpinionShowById(String opinionId);
    
    /**
     * 获取用户常用意见
     * @return
     * 	 {
     * 		"code": "响应码，成功200，失败300",
     *		"error": "失败错误信息，成功可为空",
     *		"page":1,
     *		"total":10,
     * 		"rows": [{
     * 			"ID": "意见编码",
     * 			"USER_CODE": "用户编码",
     * 			"TYPE": "意见类型",
     * 			"OPINION": "意见内容",
     * 			"CREATE_TIME": "创建时间",
     *			}]
     * 	}
     */
    public JSONObject getUserOpinion(String userId,int page,int size);
    
    /**
     * 获取用户应用信息
     * @param role_code
     * @return
     * 	 {
     * 		"code": "响应码，成功200，失败300",
     *		"error": "失败错误信息，成功可为空",
     * 		"rows": [{
     * 			"CODE": "应用编码",
     * 			"NAME": "应用名称",
     *			}]
     * 	}
     */
    public JSONObject getUserSso(String userId,int page,int size);
    
    /**
     * 参数化查询用户信息
     * @param map
     * @return
     * {
     * 		"code": "响应码，成功200，失败300",
     *		"error": "失败错误信息，成功可为空",
     * 		"user": [{
     * 			"ID": "系统内码",
     * 			"ACCOUNT": "登录名",
     * 			"NAME": "名称",
     *			"IDENTITY_NUM": "身份证号码",
     * 			"BIRTHDAY": "出生日期",
     * 			"GENDER": "性别",
     * 			"EMAIL": "邮箱地址",
     * 			"MOBILE": "联系电话",
     * 			"PHONE": "办公电话",
     * 			"ORG_NAME":"所属机关",
     * 			"INNER_ORG_NAME":"内部运行机构,处室或科室",
     * 			"POSITION": "职位"
     *			}]
     * 	}
     */
    public JSONObject findUserByQueryCriteria(Map<String,Object> map);

    
    /**
     * 清除用户登录足迹
     * @param userId
     * @return
     *  {
     * 		"code": "响应码，成功200，失败300",
     *			"error": "失败错误信息，成功可为空"
     *   }
     */
    public JSONObject deleteUserHistory(String userId);

    /**
     * 删除用户反馈信息
     * @param Id
     * @return
     *  {
     * 		"code": "响应码，成功200，失败300",
     *			"error": "失败错误信息，成功可为空"
     *   }
     */
    public JSONObject  deleteUserFeedback(String Id);
  
 	
    /**
     * 获取用户反馈详细 信息
     * @throws Exception 
     */
    public JSONObject findUserFeedbackInfo(String id);
    
    
    /**
     * 根据用户ID返回用户所在的组（岗位和角色）
     * @param userId 用户ID
     * @return
     */
	public JSONObject findGroupsByUser(String userId);
	
	 /**
     * 参数化查询岗位信息
     * @param map
     * @return
     */
	public JSONObject findGroupByQueryCriteria(Map<String, Object> query);
	
	/**
     * 根据组织机构代码获取该组织机构下的用户名称
     *
     * @param org_code
     * @param app_code
     * @return {
     * 		"code": "响应码，成功200，失败300",
     *			"error": "失败错误信息，成功可为空",
     * 		"rows": [{
     *      "id": "用户ID"
     * 	 "name": "用户名称"
     * 	}
     */
	 public JSONObject getOrgUserName(String org_code,String app_code);
	 
	/**
     * 根据组织机构代码获取该组织机构下的用户名称（模糊查询）
     *
     * @param org_code
     * @param app_code
     * @param userName
     * @return {
     * 		"code": "响应码，成功200，失败300",
     *			"error": "失败错误信息，成功可为空",
     * 		"rows": [{
     *      "id": "用户ID"
     * 	 "name": "用户名称"
     * 	}
     */
	 public JSONObject getOrgUserName(String org_code,String app_code,String userName);
		 
	 /** *
	  * 根据用户ID，获取用户分配的角色
	  *
	  * @param UserId
	  * @return {
	  * 		"code": "响应码，成功200，失败300",
	  *			"error": "失败错误信息，成功可为空",
	  * 		"rows": [{
	  *             "role_code": "角色ID"
	  * 			"role_value": "角色Value"
	  * 	}
	  */
	public JSONObject getUserRoleByUserId(String userId);
	/** *
	 * 获取用户角色信息
	 *
	 * @param 
	 * @return {
	 * 		"code": "响应码，成功200，失败300",
	 *			"error": "失败错误信息，成功可为空",
	 * 		"rows": [{
	 * 			"id": "角色编码",
	 * 			"name": "角色名称",
	 * 			"value": "角色值",
	 *          "type":"角色类型"
	 * 	}
	 */
	public JSONObject getUserRoleInfo();
	
	/**
	 * 通过角色ID和机构编码获取用户基本信息
	 *
	 * @param role_code,org_code
	 * @return {
	 * "code": "响应码，成功200，失败300",
	 * "error": "失败错误信息，成功可为空",
	 * "rows": [{
	 * "id": "系统内码",
	 * "account": "登录名",
	 * "name": "名称",
	 * "identity_num": "身份证号码",
	 * "birthday": "出生日期",
	 * "gender": "性别",
	 * "email": "邮箱地址",
	 * "mobile": "联系电话",
	 * "phone": "办公电话",
	 * "region_code":"区划代码",
	 * "region_name":"区划名称",
	 * "org_code":"所属机关代码",
	 * "org_name":"所属机关",
	 * "inner_org_name":"内部运行机构,处室或科室",
	 * "position": "职位"
	 * }]
	 * }
	 */
	public JSONObject getUserInfoByOrgRole(String codeStr);
	
	/**
	 * 
	 * @param uids
	 * @return {
	 *		 "code": "响应码，成功200，失败300",
	 *		 "error": "失败错误信息，成功可为空",
	 *		 "rows": [{
	 * 			"id": "系统内码",
	 * 			"account": "登录名",
	 * 			"name": "名称",
	 * 			"identity_num": "身份证号码",
	 * 			"birthday": "出生日期",
	 * 			"gender": "性别",
	 * 			"email": "邮箱地址",
	 * 			"mobile": "联系电话",
	 * 			"phone": "办公电话",
	 * 			"region_code":"区划代码",
	 * 			"region_name":"区划名称",
	 * 			"org_code":"所属机关代码",
	 * 			"org_name":"所属机关",
	 *				"position": "职位"
	 * 		}]
	 *	 }
	 */
	public JSONObject getUserByUids(String uids);

	/** *
	 * 更新用户操作APP频率
	 *
	 * @param 
	 * @return {
	 * 		"code": "响应码，成功200，失败300",
	 *			"error": "失败错误信息，成功可为空",
	 * 	}
	 */
	public JSONObject updateAppCounter(String userId,String appCode);
	
	/**
	 * 通讯录，人员信息获取
	 * @param regionCode
	 * @return
	 */
	public JSONObject personInfo(String regionCode,int page,int size);	
	
	/**
	 * 根据APP_CODE读取应用角色列表[简易流程]
	 * @param app_code
	 * @return
	 */
	public JSONObject getFlowRoleTree(String app_code);
	/**
	 * 根据APP_CODE读取应用用户列表[简易流程]
	 * @param app_code
	 * @return
	 */
	public JSONObject getFlowUserTree(String app_code);
	/**
	 * OAuth用户认证
	 * @param user
	 * @param pwd
	 * @return
	 */
	public JSONObject getUserCode(int status);
	/**
	 * 法制监督平台获取用户code、name
	 * @param user
	 * @param pwd
	 * @return
	 */
	public int loginForPhone(String user,String pwd);
	
	/**
	 * 读取用户登录信息
	 * @param account
	 * @param password
	 * @return
	 */
	public JSONObject getLoginUserInfo(String account, String password);
	
	/**
	 * OAuth获得用户基本信息
	 * @param param
	 * @return
	 */
	public Map<String, Object> getUserBasic(Map<String, Object> param);
	/**
	 * 插入用户信息（事项系统）
	 * @param map
	 * @return
	 */
	public boolean insertUserInfo(Map<String,Object> map,List<Map<String,Object>> roleList,List<Map<String,Object>> postList);
	
	/**
	 * 更新用户信息（事项系统）
	 * @param map
	 * @param postList
	 * @return
	 */
	public boolean updateUserInfo(Map<String,Object> map,List<Map<String,Object>> roleList,List<Map<String,Object>> postList);
	/**
	 * 根据部门查用户
	 * @param org_code
	 * @return
	 */
	public List<Map<String,Object>> findUserByOrg(String org_code);
	
	/**
	 * 根据部门查岗位
	 * @param org_code
	 * @return
	 */
	public List<Map<String,Object>> findPostByOrg(String org_code);
	/**
	 * 根据应用编码获取用户角色信息
	 * @param app_code
	 * @param weight 角色权重
	 * @return
	 */
	public List<Map<String,Object>> findAppRoles(String app_code,int weight);
	/**
	 * 根据用户获取用户角色信息
	 * @param app_code
	 * @return
	 */
	public List<Map<String,Object>> findUserRoleByUser(String user_id);
	/**
	 * 查询用户信息（事项系统）
	 * @param id
	 * @return
	 */
	public Map<String,Object> findUserInfo(String id);
	/**
	 * 查询用户扩展信息（事项系统）
	 * @param id
	 * @return
	 */
	public Map<String,Object> findUserMapInfo(String id);
	/**
	 * 查询用户上级信息
	 * @param id
	 * @return
	 */
	public Map<String,Object> findUserFollowInfo(String id);
	/**
	 * 查询用户岗位信息
	 * @param id
	 * @return
	 */
	public List<Map<String,Object>> findUserPostInfo(String id);
	/**
	 * 删除用户信息
	 * @param id
	 * @return
	 */
	public boolean delUserInfo(String id);
	/**
	 * 保存用户角色关系
	 * @param list
	 * @return
	 */
	public boolean saveUserRoleRel(List<Map<String,Object>> list,String uid);
	/**
	 * 保存用户岗位关系
	 * @param list
	 * @return
	 */
	public boolean saveUserPostRel(List<Map<String,Object>> list);
	 /**
	  * 验证用户账号是否存在
	  * @param account 用户账户
	  * @param id      用户ID
	  * @return "code": "响应码，成功200，失败300",
	  *		    "error": "失败错误信息，成功可为空",
	  * 		"rows": [{
	  * 		"ID":"用户ID"
	  *         "NAME": "用户名称"
	  * 		"ACCOUNT": "用户账户"
	  *         }]
	  */
	public JSONObject checkUserAccount(String account,String id);
	 /**
	  * 验证通讯录要添加的联系人是否已经存在
	  * @param uid 要添加的联系人ID
	  * @param creator      用户ID
	  * @return "flag":no(已存在)也是(不存在)"
	  */
	public String checkUserContact(String uid,String creator);
	
	 /**
	  * 更具id查询用户
	  * list用户ID集合
	  */
	public List<Map<String,Object>> findUsers (List<Object> list);
	/**
	  * 保存用户反馈信息
	  * @param map 用户反馈信息
	  */
	public boolean saveUserFeedback(Map<String,Object> map);
	/**
	 * 更新用户反馈信息状态
	 * @param id
	 * @return
	 */
	public boolean updateUserFeedback(String id);
	/**
	 * 获取通讯录分组信息
	 * @param creator
	 * @return
	 */
	public List<Map<String,Object>> findContactGroup (String creator);
	/**
	 * 获取通讯录分组每组人数
	 * @param creator
	 * @return
	 */
	public Map<String,Object> findContactGroupCount (String creator);
	/**
	 * 通讯录点击群组搜索联系人
	 * @param creator
	 * @param group_flag
	 * @return
	 */
	public List<Map<String,Object>> findpersonSearchInfo(String creator,String group_flag);
	/**
	 * 搜索框搜索联系人信息
	 * @param creator
	 * @param uname
	 */
	public  List<Map<String, Object>> searchInfo(String creator,String uname);
	/**
	 * 保存通讯录分组信息
	 * @param id
	 * @param group_name
	 * @param creator
	 * @param now
	 * @param remark
	 * @param status
	 * @return
	 */
	public boolean saveContactGroupInfo(String id,String group_name,String creator,Date now,String remark,String status);
	/**
	 * 编辑通讯录分组信息查询
	 * @param id
	 * @return
	 */
	public  Map<String, Object> findContactGroupCountInfo(String id);
	/**
	 * 更改通讯录分组信息
	 * @param id
	 * @param group_name
	 * @param remark
	 * @param status
	 * @return
	 */
	public boolean updateContactGroupInfo(String id,String group_name,String remark,String status);
	/**
	 * 删除通讯录群组
	 * @param id
	 * @return
	 */
	public boolean deleteContactGroupInfo(String id);
	/**
	 * 删除联系人信息
	 * @param id
	 * @return
	 */
	public boolean deleteContactUserInfo(String id);
	/**
	 * 新建联系人信息
	 * @param id
	 * @param group_id
	 * @param uid
	 * @param person_name
	 * @param creator
	 * @param now
	 * @param remark
	 * @param status
	 * @return
	 */
	public boolean saveContactUserInfo(String id,String group_id,String uid,String person_name,String creator,Date now,String remark,String status);
	/**
	 * 私信获取联系人信息
	 * @param id
	 * @return
	 */
	public  List<Map<String, Object>> findContacterList(String id);
	/**
	 * 查找对象
	 * @param sql sql语句
	 * @param paras 参数
	 * @return
	 */
	public List<Map<String,Object>> find(String sql,Object[] paras);
	/**
	 * 返回用户数据ID
	 * @param uid
	 * @return
	 */
	public List<Map<String,Object>> getUserDataList(String uid);
	/**
	 * 保存用户数据信息
	 * @param userId
	 * @param name
	 * @param url
	 * @param appCode
	 * @param icon
	 * @return
	 */
	public int saveUserData(String userId,String name,String url,String appCode,String icon);
	/**
	 * 删除用户数据信息
	 * @param id
	 * @return
	 */
	public int delUserData(String id);
	/**
	 * 流程根据nodeGroup查询候选人
	 */
	public List<Map<String,Object>> findCandidateListByNodeGroup(String nodeGroup);
	/**
	 * 流程根据nodeUser查询候选人
	 */
	public List<Map<String,Object>> findCandidateListByNodeUser(String nodeUser);
	
	/**
	 * 根据用户ID和AppCode读取用户当前应用的常用意见
	 * @param uid
	 * @param appCode
	 * @return
	 */
	public JSONObject getUserOpinionList(String uid,String appCode);
	
	/**
	 * 记录用户点击菜单的行为
	 * @param resId 菜单ID
	 * @param appCode 应用编码
	 * @param userId 用户ID
     * @return {
	 * 		"code": "响应码，成功200，失败300",
	 *			"error": "失败错误信息，成功可为空",
	 * 	}
	 */
	public JSONObject addResourceAudit(String resId,String appCode,String userId);
	
	 /**
	  * 统计菜单的点击次数
	  * @param appCode 应用编码，可为空
	  * @param userId 用户ID，可为空
	  * @return "code": "响应码，成功200，失败300",
	  *		    "error": "失败错误信息，成功可为空",
	  * 		"rows": [{
	  * 		"RES_ID":"菜单ID",
	  *         "RES_NAME": "菜单名称",
	  * 		"APP_CODE": "应用编码",
	  *  		"APP_NAME": "应用名称",
	  *   		"USER_ID": "用户ID",
	  *    		"USER_NAME": "用户名称",
	  *     	"COUNT": "点击次数",
	  *         }]
	  */
	public JSONObject getResourceAudit(String appCode,String userId);
	
	/**
	 * 用户组织机构
	 * @param userId
	 * @return
	 */
	public JSONObject getUserRealOrgById(String userId);
	/**
	 * 判断用户是否存在
	 * @param account
	 * @return
	 */
	public Map<String,Object> checkUserExist(String type,String account);
	/**
	 * 判断开发者是否存在
	 * @param account
	 * @return
	 */
	public Map<String,Object> checkDeveloperExist(String type,String account,String id);
	
	/**
	 * 判断用户是否存在
	 * @param account
	 * @return
	 */
	public JSONObject updateUserStyle(String userId,String appCode,Map<String,Object> param);
	/**
	 * 根据角色ID获取角色信息
	 * @param roleId
	 * @return
	 */
	public JSONObject getRoleInfoById(String roleId);
	/**
	 * 根据用户账号读取用户信息
	 * @param account
	 * @return
	 */
	public JSONObject getUserInfoByAccount(String account);
	/**
	 * 绑定BSP用户第三方认证信息
	 * @param map
	 * @return
	 */
	public JSONObject bindUserAuthInfo(Map<String,Object> map);
	/**
	 * 解除绑定BSP用户的第三方认证信息
	 * @return
	 */
	public JSONObject unbindUserAuthInfo(String id,Map<String,Object> map);
	/**
	 * 根据CA编号返回绑定的用户名和密码
	 * @param id
	 * @return
	 */
	public JSONObject getUserAccountAndPasswordByAuthId(String id);
	/**
	 * 分页获取用户认证列表
	 * @param page
	 * @param size
	 * @param map
	 * @return
	 */
	public JSONObject getUserAuthInfoPageList(int page, int size,Map<String,Object> map);
	/**
	 * 检查证书ID是否已经使用
	 * @param authValue
	 * @return
	 */
	public JSONObject getUserAuthCheckExist(String authValue);
	/**
     * 获取用户常用意见
     * @return
     * 	 {
     * 		"code": "响应码，成功200，失败300",
     *		"error": "失败错误信息，成功可为空",
     * 		"rows": [{
     * 			"ID": "意见编码",
     * 			"USER_CODE": "用户编码",
     * 			"APP_CODE": "项目编码",
     * 			"TYPE": "意见类型",
     * 			"OPINION": "意见内容",
     * 			"CREATE_TIME": "创建时间",
     *			}]
     * 	}
     */
    public JSONObject getUserOpinionByApp(String userId,String appCode);
    
    /**
	 * 查询单点登录次数(天/月/年)，未登录账号个数(天/月/年)，有效登录账号总数，有效CA账号个数
	 * @param map{loginCountStatus:"1"表示天，“2”表示月，“3”表示年,loginNotStatus,accountStatus:“1”表示有效,caAccountStatus}
	 * @return
	 */
	public JSONObject findLoginCount(Map<String,Object> map);
	
    /**
	 * 查询每一种登录方式每个月登录次数变化
	 * @param
	 * @return
	 */
	public JSONObject findLoginHistory();
	
	/**
	 * 查询登录所用浏览器种类
	 * @param
	 * @return
	 */
	public List<Map<String,Object>> findBrowerType();
	
	/**
	 * 查询访问系统种类
	 * @param
	 * @return
	 */
	public List<Map<String,Object>> findComputerList();
	/**
	 * 查询这一年访问量变化（总访问量/个人访问量）
	 * @param
	 * @return
	 */
	public JSONObject findLoginThisYear(Map<String,Object> param);
	/**
	 * 根据区划编码获取对应的CA证书信息
	 * @param regionCode
	 * @return
     * 	 {
     * 		"code": "响应码，成功200，失败300",
     *		"error": "失败错误信息，成功可为空",
     * 		"rows": [{
     * 			"NAME": "CA厂商名称",
     * 			"CODE": "CA厂商编码",
     * 			"JS_PATH": "脚本路径",
     * 			"TYPE": "0:CA,1:RA,2:其他",
     * 			"BIND_TIME": "绑定时间"
     *			}]
     * 	}
	 */
	public JSONObject findCAByRegionCode(String regionCode);
	/**
	 * 获取用户令牌信息
	 * @param region：区划编码/机构编码/用户账户
	 * @param password:口令
	 * @param ipAddr:请求IP
	 * @return
     * 	 {
     * 		"code": "状态码",
     *		"message": "操作结果描述信息",
     * 		"token": "临时令牌"
     * 	}
	 */
	public JSONObject queryUserToken(String region,String password,String ipAddr);
	/**
	 * 修改登录口令服务
	 * @param region：区划编码/机构编码/用户账户
	 * @param token:临时访问令牌
	 * @param oldPassword:旧口令
	 * @param newPassword:新口令
	 * @param ipAddr:请求IP
	 * @return
     * 	 {
     * 		"code": "状态码",
     *		"message": "操作结果描述信息",
     * 	}
	 */
	public JSONObject changeUserPassword(String region,String oldPassword,String newPassword);
	/**
	 * 刷新令牌服务
	 * @param region：区划编码/机构编码/用户账户
	 * @param token:临时访问令牌
	 * @param ipAddr:请求IP
	 * @return
     * 	 {
     * 		"code": "状态码",
     *		"message": "操作结果描述信息",
     * 		"token": "新的临时令牌"
     * 	}
	 */
	public JSONObject refreshUserToken(String region,String token,String ipAddr);
	/**
	 * 验证令牌的有效性
	 * @param region：区划编码/机构编码/用户账户
	 * @param token：临时访问令牌
	 * @param ipAddr：请求IP
	 * @return
	 *  {
     * 		"code": "状态码",
     *		"message": "操作结果描述信息"
     * 	}
	 */
	public JSONObject validateToken(String region,String token,String ipAddr);
	/**
	 * 修改地市最新一次下载省资源目录时间
	 * @param date:当前时间
	 * @return
	 *  {
     * 		"code": "状态码",
     *		"message": "操作结果描述信息"
     * 	}
	 */
	public JSONObject modifyUserDownloadTime(String region);
	
	/**
	 * 保存用户认证头像
	 * @param param
	 * @return
	 */
	public JSONObject saveUserFace(Map<String, Object> param);
	
	/**
	 * 获取用户认证头像
	 * @param param
	 * @return
	 */
	public JSONObject getUserFace(Map<String, Object> param);
	/**
	 * 根据区划分页获取用户信息
	 * @param page
	 * @param size
	 * @param map
	 * @return
	 */
	public JSONObject getUsersPageListByRegion(int page, int size,Map<String,Object> map);
	/**
	 * 获取用户信息
	 * @param map
	 * @return
	 */
	public JSONObject getUserList(Map<String, Object> map);
	/**
	 * 保存用户信息
	 * @param map
	 * @param roleList
	 * @param postList
	 * @return
	 */
	public JSONObject addUserInfo(Map<String,Object> map,List<Map<String,Object>> roleList,List<Map<String,Object>> postList);
	/**
	 * 获取默认密码
	 * @return
	 */
	public JSONObject getDefaultPassword();
	/**
	 * 删除用户信息(逻辑删除)
	 * @param id
	 * @return
	 */
	public JSONObject deleteUserById(String id);
	/**
	 * 更新用户信息
	 * @param map
	 * @param roleList
	 * @return
	 */
	public JSONObject updateUserInfoFromConsole(Map<String, Object> map, List<Map<String, Object>> roleList);
	/**
	 * 获取某应用下某区划某角色用户
     * @param param
	 * @return
	 */
	public JSONObject getUserByRole(Map<String, Object> param);
	/**
	 * 获取某组织机构、某角色的用户
	 * <br>
	 * <p>Description: 
	 * <br>
	 * li_jie<br>
	 * <p>Date: 2018-6-4 下午2:07:01<br/>
	 * <p>
	 * @param roleid
	 * @param orgid
	 * @return   
	 * @see JSONObject
	 */
	public JSONObject getUserByRoleAndOrg(String roleid,String orgid);
}

